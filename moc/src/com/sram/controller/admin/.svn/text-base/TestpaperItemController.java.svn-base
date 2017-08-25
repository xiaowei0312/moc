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
import com.sram.controller.moc.BaseController;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Question;
import com.sram.entity.Testpaper;
import com.sram.entity.TestpaperItem;
import com.sram.service.OutlineCategoryService;
import com.sram.service.QuestionService;
import com.sram.service.TestpaperChapterService;
import com.sram.service.TestpaperItemService;
import com.sram.service.TestpaperService;
import com.sram.util.JsonUtils;

/**
 * 试卷条目控制类
 */
@Controller("TestpaperItemController")
@RequestMapping("/admin/testpaper_item")
public class TestpaperItemController extends BaseController{
	@Resource(name = "testpaperItemServiceImpl")
	private TestpaperItemService testpaperItemService;
	@Resource(name = "testpaperChapterServiceImpl")
	private TestpaperChapterService testpaperChapterService;
	@Resource(name = "testpaperServiceImpl")
	private TestpaperService testpaperService;
	
	@Resource(name = "questionServiceImpl")
	private QuestionService questionService;
	
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
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap model,Long testpaperId,String stem,String questionType) {
		Testpaper testpaper = testpaperService.findTestpaperById(testpaperId);
		OutlineCategory parent = testpaper.getOutlineCategory();
		List<OutlineCategory> childrens = outlineCategoryService.findChildren(
				parent, null);
		childrens.add(0, parent);
		model.addAttribute("outlineCategoryTree", childrens);
		model.addAttribute("testpaper",	testpaper);//试卷对象
		model.addAttribute("testpaperChapters",testpaperChapterService.findTestpaperChapterListByTestpaperId(testpaperId));//章节的条目列表
		
		return "/admin/test_paper_item/list";
	}
	/**
	 * 题目列表
	 */
	@RequestMapping(value = "/questionList", method = RequestMethod.POST)
	public String questionList(ModelMap model,Integer page,Long outlineCategoryId,String idStr,String stem,String questionType) {
		model.addAttribute("questionList",	questionService.findAllQuestionOfChild(outlineCategoryId, idStr, questionType, stem, page));
		return "/admin/test_paper_item/questionList";
	}
	/**
	 * 题目列表总数
	 */
	@RequestMapping(value = "/questionCount", method = RequestMethod.POST)
	@ResponseBody
	public Long questionCount(ModelMap model,Long outlineCategoryId,String idStr,String questionType,String stem) {
		return questionService.findAllQuestionOfChildCount(outlineCategoryId, idStr, questionType, stem);
	}
	
	/**
	 * 添加选择题
	 * @param model
	 * @param outlineCategoryId
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody	Message save(RedirectAttributes redirectAttributes,Long testpaperId,String... testpaperItemStr) {
		if (!isValid(testpaperId)) {
			return ERROR_MESSAGE;
		}
		testpaperItemService.saveItem(testpaperId, testpaperItemStr);
		return SUCCESS_MESSAGE;
	}
}
