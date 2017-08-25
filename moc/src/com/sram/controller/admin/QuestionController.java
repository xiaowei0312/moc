package com.sram.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.sram.Constants.QuestionType;
import com.sram.Message;
import com.sram.Pageable;
import com.sram.Principal;
import com.sram.dao.QuestionDao;
import com.sram.entity.AccoutCatalog;
import com.sram.entity.Member;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Question;
import com.sram.service.AccoutCatalogService;
import com.sram.service.OutlineCategoryService;
import com.sram.service.QuestionService;
import com.sram.util.JsonUtils;
import com.sram.util.UnicodeConverter;

@Controller("adminQuestionController")
@RequestMapping("/admin/question")
public class QuestionController extends BaseController {
	@Resource(name = "questionServiceImpl")
	private QuestionService questionService;

	@Resource(name = "questionDaoImpl")
	private QuestionDao questionDao;
	@Resource(name = "outlineCategoryServiceImpl")
	private OutlineCategoryService outlineCategoryService;
	@Resource(name = "accoutCatalogServiceImpl")
	private AccoutCatalogService accoutCatalogService;

	/**
	 * 异步获取outlineCategory根据outlineCategoryId
	 */
	@RequestMapping(value = "/getOutlineCategoryChildren", method = RequestMethod.GET)
	public @ResponseBody
	List<String> getOutlineCategoryChildren(Long parentId) {
		List<OutlineCategory> outlineCategories = new ArrayList<OutlineCategory>();
		OutlineCategory outlineCategory = outlineCategoryService.find(parentId);
		if (outlineCategory != null) {
			outlineCategories = outlineCategoryService.findChildren(
					outlineCategory, null);
		} else {
			outlineCategories = outlineCategoryService.findRoots();
		}
		List<String> options = new ArrayList<String>();
		for (OutlineCategory category : outlineCategories) {
			options.add(category.getGrade() + "," + category.getId() + ","
					+ category.getName());
		}
		return options;
	}

	/**
	 * 父列表 findRoots查询所有父类 parentList 父类列表
	 */
	@RequestMapping(value = "/questionList", method = RequestMethod.GET)
	public String questionList(ModelMap model, Pageable pageable, String stem,
			String questionType, String difficulty, Long outlineCategoryId) {

		OutlineCategory outlineCategory = outlineCategoryService
				.find(outlineCategoryId);
		String IdTreePath = null;
		if (outlineCategory != null) {
			IdTreePath = outlineCategory.getId()
					+ outlineCategory.getTreePath();
		}
		model.addAttribute("page", questionService.findPage(pageable, stem,
				questionType, difficulty, IdTreePath));
		model.addAttribute("stem", stem);
		model.addAttribute("questionType", questionType);
		model.addAttribute("difficulty", difficulty);
		model.addAttribute("outlineCategoryId", outlineCategoryId);
		model.addAttribute("outlineCategoryTree",
				outlineCategoryService.findTree());
		return "/admin/question/list";
	}

	/**
	 * 材料题列表
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/materialChildrenList", method = RequestMethod.GET)
	public String materialChildrenList(ModelMap model, Pageable pageable,
			String stem, String questionType, String difficulty,
			Long outlineCategoryId, Long id) {
		Question parentQuestion = questionService.find(id);
		model.addAttribute("parentQuestion", parentQuestion);
		model.addAttribute("stem", stem);
		model.addAttribute("outlineCategoryId", outlineCategoryId);
		model.addAttribute("questions",
				questionDao.findChildren(parentQuestion));
		return "/admin/question/materialChildrenList";
	}

	/**
	 * 添加选择题
	 * @param model
	 * @param outlineCategoryId
	 * @version 0.2  2015年5月14日11:45:27 杨楷修改Unicode转码 
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(String submitType, ModelMap model, Question question,
			String[] choices, Long outlineCategoryId,
			RedirectAttributes redirectAttributes, Long materialID,
			HttpServletRequest request) {
		if (!isValid(question)) {
			return ERROR_VIEW;
		}
		String treePath = "";

		if (outlineCategoryId != null) {
			OutlineCategory outlineCategory = outlineCategoryService
					.find(outlineCategoryId);
			question.setOutlineCategory(outlineCategory);
			question.setTreePath(outlineCategory.getTreePath());
			treePath = outlineCategory.getTreePath();
		}

		// 选择题默认是多项选择题
		QuestionType questionType = question.getQuestionType();
		if (materialID != null) {// 添加材料题子题
			Question parentQuestion = questionService.find(materialID);
			question.setParent(parentQuestion);
			if (questionType == QuestionType.entry) {
				String an[] = request.getParameterValues("borrowanswer");
				String keys[] = request.getParameterValues("keys");
				String selects[] = request.getParameterValues("borrowselect");
				String answers[][] = new String[keys.length][3];
				for (int i = 0; i < an.length; i++) {
					answers[i][0] = keys[i];
					answers[i][1] = selects[i];
					answers[i][2] = an[i];
				}
				question.setOutlineCategory(parentQuestion.getOutlineCategory());
				question.setMetas(JsonUtils.toJson(keys));
				question.setAnswer(JsonUtils.toJson(answers));
			} else if (questionType == QuestionType.blanks) {
				Pattern p = Pattern.compile("(\\[.*?\\])");
				Matcher m = p.matcher(question.getStem());
				List<String> list = new ArrayList<String>();
				while (m.find()) {
					String answer = "";
					String dd = m.group();
					answer += dd
							.substring(dd.indexOf("[") + 1, dd.indexOf("]"));
					list.add(answer);
				}
				question.setAnswer(JsonUtils.toJson(list));
			} else if (questionType == QuestionType.uncertain_choice
					|| questionType == QuestionType.choice) {
				if (questionType == QuestionType.choice) {
					if (question.getAnswer().split(",").length >= 2) {
						question.setQuestionType(QuestionType.choice);
					} else {
						question.setQuestionType(QuestionType.single_choice);
					}
				}
				String[] meta = new String[choices.length];
				for (int i = 0; i < choices.length; i++) {
					meta[i] = choices[i];
				}
				String choice = JsonUtils.toJson(meta);
				question.setMetas(choice);
			} else {
				List<String> list = new ArrayList<String>();
				list.add(question.getAnswer());
				question.setAnswer(JsonUtils.toJson(list));
			}
			try {
				/**2015年5月14日13:36:45 杨楷 start**/
				if (StringUtils.isNotBlank(question.getMetas())) {
					question.setMetas(UnicodeConverter.toEncodedUnicode(question.getMetas(), false));
				}
				if (StringUtils.isNotBlank(question.getAnswer())) {
					question.setAnswer(UnicodeConverter.toEncodedUnicode(question.getAnswer(), false));
				}
				/**2015年5月14日13:36:45 杨楷 end**/
				questionDao.independentPersist(question);
				addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
			} catch (org.hibernate.exception.ConstraintViolationException e) {
				addFlashMessage(redirectAttributes,
						Message.error("添加失败,题目已存在", null));
			}

		} else {
			if (questionType == QuestionType.uncertain_choice
					|| questionType == QuestionType.choice) {
				if (questionType == QuestionType.choice) {
					if (question.getAnswer().split(",").length >= 2) {
						question.setQuestionType(QuestionType.choice);
					} else {
						question.setQuestionType(QuestionType.single_choice);
					}
				}
				// modify limin 2015/04/20 start
				String choice = JSON.toJSONString(choices);
				// modify 杨楷 2015年5月14日11:48:24 start
				//choice=UnicodeConverter.toEncodedUnicode(choice, true);
				// modify 杨楷 2015年5月14日11:48:29 end
				question.setMetas(choice);
				// modify limin 2015/04/20 end
				question.setMetas(choice);
			} else if (questionType == QuestionType.blanks) {
				Pattern p = Pattern.compile("(\\[.*?\\])");
				Matcher m = p.matcher(question.getStem());
				List<String> list = new ArrayList<String>();
				while (m.find()) {
					String answer = "";
					String dd = m.group();
					answer += dd
							.substring(dd.indexOf("[") + 1, dd.indexOf("]"));
					list.add(answer);
				}
				question.setAnswer(JsonUtils.toJson(list));
			} else  if(questionType!=QuestionType.material){
				List<String> list = new ArrayList<String>();
				list.add(question.getAnswer());
				question.setAnswer(JsonUtils.toJson(list));
			}
			try {
				/**2015年5月14日13:36:45 杨楷 start**/
				if (StringUtils.isNotBlank(question.getMetas())) {
					question.setMetas(UnicodeConverter.toEncodedUnicode(question.getMetas(), false));
				}
				if (StringUtils.isNotBlank(question.getAnswer())) {
					question.setAnswer(UnicodeConverter.toEncodedUnicode(question.getAnswer(), false));
				}
				/**2015年5月14日13:36:45 杨楷 end**/
				questionDao.independentPersist(question);
				addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
			} catch (org.hibernate.exception.ConstraintViolationException e) {
				addFlashMessage(redirectAttributes,
						Message.error("添加失败,题目已存在", null));
			}

		}

		// 材料题子题保存并继续添加
		// modify 2015/04/27 limin start
		String redirectUrl = "";
		if (submitType != null && submitType.equals("continueAdd")) {
			if (questionType == QuestionType.determine) {
				redirectUrl += "redirect:add.jhtml?questionType="
						+ QuestionType.determine.toString();
			} else if (questionType == QuestionType.essay) {
				redirectUrl += "redirect:add.jhtml?questionType="
						+ QuestionType.essay.toString();
			} else if (questionType == QuestionType.blanks) {
				redirectUrl += "redirect:add.jhtml?questionType="
						+ QuestionType.blanks.toString();
			}else if(questionType ==QuestionType.entry){
				redirectUrl += "redirect:add.jhtml?questionType="
					+ QuestionType.entry.toString();
			}else {
				redirectUrl += "redirect:add.jhtml?";
			}
			if (materialID != null) {
				redirectUrl += "&materialID=" + materialID;
			}
		} else {// 保存
			if (questionType == QuestionType.material) {// 添加材料题以后，保存并继续添加子题
				//题目重复，跳转至列表页面
				if(question.getId()==null){
					redirectUrl += "redirect:questionList.jhtml";
				}else{
					redirectUrl += "redirect:materialChildrenList.jhtml?id="
							+ question.getId() + "&outlineCategoryId="
							+ outlineCategoryId;
				}
			} else if(materialID != null) {//保存材料题子题
				redirectUrl += "redirect:materialChildrenList.jhtml?id="
						+ materialID + "&outlineCategoryId="
						+ outlineCategoryId;
			} else{
				redirectUrl += "redirect:questionList.jhtml";
			}
		}
		return redirectUrl;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addMultipleAnswer(ModelMap model, Long outlineCategoryId,
			Long materialID, String questionType) {

		OutlineCategory outlineCategory = null;
		if (outlineCategoryId != null) {
			outlineCategory = outlineCategoryService.find(outlineCategoryId);
		}
		model.addAttribute("materialID", materialID);
		model.addAttribute("outlineCategory", outlineCategory);
		model.addAttribute("outlineCategoryId", outlineCategoryId);
		String redirectUrl = "";
		if (questionType != null) {
			if (questionType.equals("blanks")) {
				redirectUrl += "/admin/question/addBlanks";
			}else if (questionType.equals("essay")) {
				redirectUrl += "/admin/question/addShort";
			}else if (questionType.equals("determine")) {
				redirectUrl += "/admin/question/addJudge";
			}else if (questionType.equals("entry")) {
				List<AccoutCatalog> accoutCatalogs = accoutCatalogService
						.findTree();
				model.addAttribute("accoutCatalogs", accoutCatalogs);
				redirectUrl += "/admin/question/addEntry";
			}else if (questionType.equals("material")) {
				redirectUrl += "/admin/question/addMaterial";
			}
		}else{
			redirectUrl += "/admin/question/addMultiple";
		}
		return redirectUrl;
	}

	/**
	 * 编辑题目
	 */
	@RequestMapping(value = "/editQuestion", method = RequestMethod.GET)
	public String editQuestion(Long id, ModelMap model, Long materialID,
			Long outlineCategoryId) {
		Question question = questionService.find(id);
		if (!isValid(question)) {
			return ERROR_VIEW;
		}
		model.addAttribute("question", question);
		model.addAttribute("id", id);
		model.addAttribute("materialID", materialID);
		model.addAttribute("outlineCategoryId", outlineCategoryId);
		QuestionType questionType = question.getQuestionType();
		String redirectUrl = "";
		if (questionType == QuestionType.determine) {
			List<String> an = JsonUtils.toObject(question.getAnswer(),
					List.class);
			question.setAnswer(UnicodeConverter.fromEncodedUnicode(an.get(0)
					.toCharArray(), 0, an.get(0).length()));
			redirectUrl +="/admin/question/editJudge";
		}else if (questionType == QuestionType.blanks) {
			redirectUrl +="/admin/question/editBlanks";
		}else if (questionType == QuestionType.essay) {
			List<String> answers = JsonUtils.toObject(question.getAnswer(),
					List.class);
			String str = UnicodeConverter.fromEncodedUnicode(answers.get(0)
					.toCharArray(), 0, answers.get(0).length());
			question.setAnswer(str);
			redirectUrl += "/admin/question/editShort";
		}else if (questionType == QuestionType.entry) {
			List<AccoutCatalog> accoutCatalogs = accoutCatalogService.findTree();
			String[][] arr = JsonUtils.toObject(question.getAnswer(),
					String[][].class);
			String[][] data = new String[arr.length][3];
			for (int i = 0; i < arr.length; i++) {
				data[i][0] = UnicodeConverter.fromEncodedUnicode
						(arr[i][0].toCharArray(), 0, arr[i][0].length());
				data[i][1] = UnicodeConverter.fromEncodedUnicode(
						arr[i][1].toCharArray(), 0, arr[i][1].length());
				data[i][2] = UnicodeConverter.fromEncodedUnicode(
						arr[i][2].toCharArray(), 0, arr[i][2].length());
			}
			model.addAttribute("arr", data);
			model.addAttribute("accoutCatalogs", accoutCatalogs);
			redirectUrl += "/admin/question/editEntry";
		}else if (questionType == QuestionType.material) {
			redirectUrl += "/admin/question/editMaterial";
		}else{
			List<String> choices = JsonUtils.toObject(question.getMetas(),
					List.class);
			List<String> mychoices = new ArrayList<String>();
			for (String string : choices) {
				mychoices.add(UnicodeConverter.fromEncodedUnicode(
						string.toCharArray(), 0, string.length()));
			}
			List<Integer> answers = JsonUtils.toObject(question.getAnswer(),
					List.class);
			model.addAttribute("choices", mychoices);
			model.addAttribute("answers", answers);
			redirectUrl += "/admin/question/editMultiple";
		}
		return redirectUrl;
	}

	/**
	 * 更新
	 *  @version 0.2  2015年5月14日11:45:27 杨楷修改Unicode转码 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Question question, String questionAnswer,
			String[] choices, Long outlineCategoryId,
			RedirectAttributes redirectAttributes, Long materialID,
			HttpServletRequest request) {
		if (!isValid(question)) {
			return ERROR_VIEW;
		}
		String redirectUrl = "";
		if (outlineCategoryId != null) {
			OutlineCategory outlineCategory = outlineCategoryService
					.find(outlineCategoryId);
			question.setOutlineCategory(outlineCategory);
			question.setTreePath(outlineCategory.getTreePath());
		}
		QuestionType questionType = question.getQuestionType();
		Question parentquestion=new Question();
		// 判断更新的题是否为材料题子题
		if (materialID != null && question.getParent() != null) {
			 parentquestion = questionService.find(materialID);
			question.setParent(parentquestion);
		}
		if (questionType == QuestionType.uncertain_choice
				|| questionType == QuestionType.choice) {
			if (choices != null && !choices.equals("")) {
				// modify limin 2015/04/20 start
				String choice = JSON.toJSONString(choices);
				choice=choice;
				question.setMetas(choice);
				// modify limin 2015/04/20 end
			} 
			if (questionType == QuestionType.choice) {
				if (question.getAnswer().split(",").length >= 2) {
					question.setQuestionType(QuestionType.choice);
				} else {
					question.setQuestionType(QuestionType.single_choice);
				}
			}
		} else if (questionType == QuestionType.blanks) {
			Pattern p = Pattern.compile("(\\[.*?\\])");
			Matcher m = p.matcher(question.getStem());
			List<String> list = new ArrayList<String>();
			while (m.find()) {
				String answer = "";
				String dd = m.group();
				answer += dd.substring(dd.indexOf("[") + 1, dd.indexOf("]"));
				list.add(answer);
			}
			question.setAnswer(JsonUtils.toJson(list));
		}else if (questionType == QuestionType.entry) {
			String an[] = request.getParameterValues("borrowanswer");
			String keys[] = request.getParameterValues("keys");
			String selects[] = request.getParameterValues("borrowselect");
			String answers[][] = new String[keys.length][3];
			for (int i = 0; i < an.length; i++) {
				answers[i][0] = keys[i];
				answers[i][1] = selects[i];
				answers[i][2] = an[i];
						
			}
			question.setOutlineCategory(parentquestion.getOutlineCategory());
			question.setMetas(JsonUtils.toJson(keys));
			question.setAnswer(JsonUtils.toJson(answers));
		}else{
			// modify limin 2015/04/20 start
			if(questionType!=QuestionType.material){
				List<String> list = new ArrayList<String>();
				list.add(question.getAnswer());
				question.setAnswer(JsonUtils.toJson(list));
			}
			// modify limin 2015/04/20 end
		}
		// 判断更新的题是否为材料题子题
		if (materialID != null && question.getParent() != null) {
			redirectUrl += "redirect:materialChildrenList.jhtml";
		}else{
			redirectUrl += "redirect:questionList.jhtml";
		}
		/**2015年5月14日13:36:45 杨楷 start**/
		if (StringUtils.isNotBlank(question.getMetas())) {
			question.setMetas(UnicodeConverter.toEncodedUnicode(question.getMetas(), false));
		}
		if (StringUtils.isNotBlank(question.getAnswer())) {
			question.setAnswer(UnicodeConverter.toEncodedUnicode(question.getAnswer(), false));
		}
		/**2015年5月14日13:36:45 杨楷 end**/
		questionService.update(question);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return redirectUrl;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		try {
			// 判断要删除的题目是否子题
			Question question = null;
			for (Long id : ids) {
				question = questionService.find(id);
				if (question == null) {
					return ERROR_MESSAGE;
				}
			}
			questionService.delete(ids);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			return Message
					.error("该题存在子题或者已被试卷引用无法删除");
		}
		return SUCCESS_MESSAGE;
	}
}
