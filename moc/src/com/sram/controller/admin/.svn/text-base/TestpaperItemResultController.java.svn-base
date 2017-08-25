package com.sram.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.Constants.QuestionType;
import com.sram.Message;
import com.sram.Pageable;
import com.sram.controller.moc.BaseController;
import com.sram.dao.QuestionDao;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Question;
import com.sram.service.OutlineCategoryService;
import com.sram.service.QuestionService;
import com.sram.util.JsonUtils;

/**
 * 试卷题目做题结果 控制类
 */
@Controller("TestpaperItemResultController")
@RequestMapping("/admin/testpaper_item_result")
public class TestpaperItemResultController extends BaseController{
	@Resource(name = "questionServiceImpl")
	private QuestionService questionService;
	
	@Resource(name = "questionDaoImpl")
	private QuestionDao questionDao;
	@Resource(name = "outlineCategoryServiceImpl")
	private OutlineCategoryService outlineCategoryService;
	
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
		for (OutlineCategory category:outlineCategories) {
			options.add(category.getGrade()+","+category.getId()+","+category.getName());
		}
		return options;
	}
	/**
	 * 父列表
	 * findRoots查询所有父类
	 * parentList 父类列表
	 */
	@RequestMapping(value = "/questionList", method = RequestMethod.GET)
	public String questionList(ModelMap model,Pageable pageable
			,String stem,String questionType,String difficulty,Long outlineCategoryId
		) {
		
		OutlineCategory outlineCategory = outlineCategoryService.find(outlineCategoryId);
		String IdTreePath=null;
		if(outlineCategory !=null){
			IdTreePath=outlineCategory.getId()+outlineCategory.getTreePath();
		}
		model.addAttribute("page",
				questionService.findPage(pageable,stem,questionType,difficulty,IdTreePath));
		model.addAttribute("stem",stem);
		model.addAttribute("questionType",questionType);
		model.addAttribute("difficulty",difficulty);
		model.addAttribute("outlineCategoryId",outlineCategoryId);
		model.addAttribute("outlineCategoryTree", outlineCategoryService.findTree());
		model.addAttribute("questions",
				questionService.findAll());
		return "/admin/question/list";
	}
	/**
	 * 材料题列表
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/materialChildrenList", method = RequestMethod.GET)
	public String materialChildrenList(ModelMap model,Pageable pageable
			,String stem,String questionType,String difficulty,Long outlineCategoryId,Long id) {
		Question parentQuestion=questionService.find(id);
		model.addAttribute("parentQuestion",parentQuestion);
		model.addAttribute("stem",stem);
		model.addAttribute("questions",
				questionDao.findChildren(parentQuestion));
		return "/admin/question/materialChildrenList";
	}
	
	/**
	 * 添加选择题
	 * @param model
	 * @param outlineCategoryId
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(String submitType,
			Question question,
			String[] choices,Long outlineCategoryId,
			 RedirectAttributes redirectAttributes,Long materialID) {
		if (!isValid(question)) {
			return ERROR_VIEW;
		}
		OutlineCategory outlineCategory = outlineCategoryService.find(outlineCategoryId);
		question.setOutlineCategory(outlineCategory);
		question.setTreePath(outlineCategory.getTreePath());
		//选择题默认是多项选择题
		QuestionType questionType = question.getQuestionType();
		if(questionType ==QuestionType.uncertain_choice || questionType == QuestionType.choice ){
			if(questionType == QuestionType.choice){
				System.out.println(question.getAnswer().split(","));
				if(question.getAnswer().split(",").length>=2){
					question.setQuestionType(QuestionType.choice);
				}else{
					question.setQuestionType(QuestionType.single_choice);
				}
			}
			//为选择题添加选项信息
			String choice = JsonUtils.toJson(choices);
			question.setMetas(choice);
		} 
		if(materialID!=null){
			Question parentQuestion=questionService.find(materialID);
			question.setParent(parentQuestion);
			questionService.save(question);
			return "redirect:materialChildrenList.jhtml?id="+materialID;
		}
		questionService.save(question);
		if(questionType==QuestionType.material){
			return "redirect:materialChildrenList.jhtml?id="+question.getId();
		}
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		if(submitType!=null && submitType.equals("continueAdd")){
			if(questionType==QuestionType.determine){
				return "redirect:add.jhtml?outlineCategoryId="+outlineCategoryId
				+"&questionType="+QuestionType.determine.toString();
			}
			if(questionType==QuestionType.essay){
				return "redirect:add.jhtml?outlineCategoryId="+outlineCategoryId
				+"&questionType="+QuestionType.essay.toString();
			}
			if(questionType==QuestionType.blanks){
				return "redirect:add.jhtml?outlineCategoryId="+outlineCategoryId
				+"&questionType="+QuestionType.blanks.toString();
			}
			if(questionType==QuestionType.material){
				return "redirect:add.jhtml?outlineCategoryId="+outlineCategoryId
				+"&questionType="+QuestionType.material.toString();
			}
			return "redirect:add.jhtml?outlineCategoryId="+outlineCategoryId;
		}
		
		return "redirect:questionList.jhtml";
	}
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addMultipleAnswer(ModelMap model,String outlineCategoryId,Long materialID,
			String questionType) {
		
		OutlineCategory outlineCategory=null;
		if(outlineCategoryId!=null){
			outlineCategory=outlineCategoryService.find(Long.parseLong(outlineCategoryId));
		}
		model.addAttribute("materialID",materialID);
		model.addAttribute("outlineCategory",outlineCategory);
		if(questionType!=null){
			if(questionType.equals("blanks")){
				return "/admin/question/addBlanks";
			}
			if(questionType.equals("essay")){
				return "/admin/question/addShort";
			}
			if(questionType.equals("determine")){
				return "/admin/question/addJudge";
			}
			if(questionType.equals("material")){
				
				return "/admin/question/addMaterial";
			}
		}
		return "/admin/question/addMultiple";
	}
	
	/**
	 * 编辑题目
	 */
	@RequestMapping(value = "/editQuestion", method = RequestMethod.GET)
	public String editQuestion(Long id, ModelMap model,Long materialID) {
		Question question = questionService.find(id);
		if (!isValid(question)) {
			return ERROR_VIEW;
		}
		model.addAttribute("question", question);
		model.addAttribute("id",id);
		model.addAttribute("materialID",materialID);
		QuestionType questionType=question.getQuestionType();
		
		if(questionType==QuestionType.determine){
			return "/admin/question/editJudge";
		}
		if(questionType==QuestionType.blanks){
			return "/admin/question/editBlanks";
		}
		if(questionType==QuestionType.essay){
			return "/admin/question/editShort";
		}
		if(questionType==QuestionType.material){
			return "/admin/question/editMaterial";
		}
		List<String> choices = JsonUtils.toObject(question.getMetas(), List.class);
		Map<Integer, String> answers = JsonUtils.toObject(question.getAnswer(), Map.class);
		model.addAttribute("choices",choices);
		model.addAttribute("answers",answers);
		
		return "/admin/question/editMultiple";
	}
	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Question question,String questionAnswer,String[] choices,
			Long outlineCategoryId,RedirectAttributes redirectAttributes,Long materialID) {
		if (!isValid(question)) {
			return ERROR_VIEW;
		}
		OutlineCategory outlineCategory =outlineCategoryService.find(outlineCategoryId);
		question.setOutlineCategory(outlineCategory);
		question.setTreePath(outlineCategory.getTreePath());
		//判断更新的题是否为选择题
		QuestionType questionType = question.getQuestionType();
		if(questionType ==QuestionType.uncertain_choice || questionType == QuestionType.choice ){
			if(questionType == QuestionType.choice){
				System.out.println(question.getAnswer().split(","));
				if(question.getAnswer().split(",").length>=2){
					question.setQuestionType(QuestionType.choice);
				}else{
					question.setQuestionType(QuestionType.single_choice);
				}
			}
			//为选择题添加选项信息
			String choice = JsonUtils.toJson(choices);
			question.setMetas(choice);
		} 
		if(choices!=null && !choices.equals("")){
			String choice = JsonUtils.toJson(choices);
			question.setMetas(choice);
		}
		
		if(materialID!=null){
			Question question2 =new Question();
			question2.setId(materialID);
			question.setParent(question2);
			questionService.update(question);
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
			return "redirect:materialChildrenList.jhtml";
		}
		questionService.update(question);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:questionList.jhtml";
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		try {
			//判断要删除的题目是否子题
			Question question=null;
			for(Long id:ids){
				question = questionService.find(id);
				if(question==null){
					return ERROR_MESSAGE;
				}
			}
			questionService.delete(ids);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			//e.printStackTrace();
			return Message.error("admin.productCategory.deleteExistChildrenNotAllowed");
		}
		return SUCCESS_MESSAGE;
	}
}
