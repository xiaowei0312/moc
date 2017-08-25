package com.sram.controller.admin;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.Message;
import com.sram.Pageable;
import com.sram.entity.Admin;
import com.sram.entity.Course;
import com.sram.entity.CourseCategory;
import com.sram.entity.CourseChapter;
import com.sram.entity.IndustryCategory;
import com.sram.entity.OutlineCategory;
import com.sram.entity.OutlineCategory.Course_chapter_type;
import com.sram.entity.Question;
import com.sram.service.CourseCategoryService;
import com.sram.service.CourseChapterService;
import com.sram.service.CourseService;
import com.sram.service.IndustryCategoryService;
import com.sram.service.OutlineCategoryService;
import com.sram.service.QuestionService;

@Controller("adminOutlineCategoryController")
@RequestMapping("/admin/outline_category")
public class OutlineCategoryController extends BaseController {

	@Resource(name = "outlineCategoryServiceImpl")
	private OutlineCategoryService outlineCategoryService;

	@Resource(name = "questionServiceImpl")
	private QuestionService questionService;
	
	@Resource(name = "industryCategoryServiceImpl")
	private IndustryCategoryService industryCategoryService;
	@Resource(name="courseCategoryServiceImpl")
	private CourseCategoryService courseCategoryService;
	@Resource(name="courseServiceImpl")
	private CourseService courseService;
	@Resource(name="courseChapterServiceImpl")
	private CourseChapterService courseChapterService;
	/**
	 * 获取行业列表
	 */
	@RequestMapping(value = "/getIndustryCategoryChildren", method = RequestMethod.GET)
	public @ResponseBody
	List<String> getOutlineCategoryChildren(Long parentId) {
		List<IndustryCategory> list = new ArrayList<IndustryCategory>();
		IndustryCategory industryCategory = industryCategoryService.find(parentId);
		if (industryCategory != null) {
			list = industryCategoryService.findChildren(industryCategory, null);
		} else {
			list = industryCategoryService.findRoots();
		}
		List<String> industryCategorys = new ArrayList<String>();
		for (IndustryCategory industryCategory2:list) {
			industryCategorys.add(industryCategory2.getGrade()+","+industryCategory2.getId()+","+industryCategory2.getName());
		}
		return industryCategorys;
	}
	
	/**
	 * 父列表添加 findTree查询所有级别
	 */
	@RequestMapping(value = "/addParent", method = RequestMethod.GET)
	public String addParent(ModelMap model) {
		model.addAttribute("outlineCategoryTree",outlineCategoryService.findTree());
		return "/admin/outline_category/addParent";
	}

	/**
	 * 子列表添加 id 根ID
	 * parentId 大纲父节点
	 */
	@RequestMapping(value = "/addChildren", method = RequestMethod.GET)
	public String addChildren(ModelMap model, Long id,Long parentId) {
		OutlineCategory parent = outlineCategoryService.find(id);
		List<OutlineCategory> childrens = outlineCategoryService.findChildren(
				parent, null);
		childrens.add(0, parent);
		model.addAttribute("outlineCategoryTree", childrens);
		model.addAttribute("rootId", id);
		model.addAttribute("parentId",parentId);
		model.addAttribute("industryCategoryID",parent.getIndustryCategoryID());
		return "/admin/outline_category/addChildren";
	}

	/**
	 * 父列表 findRoots查询所有父类 parentList 父类列表
	 */
	@RequestMapping(value = "/parentList", method = RequestMethod.GET)
	public String parentList(Pageable pageable,ModelMap model,String firstIndustryName,String secondIndustryName) {
		model.addAttribute("page",
				outlineCategoryService.findRootsPage(pageable,firstIndustryName,secondIndustryName));
		model.addAttribute("firstIndustryName", firstIndustryName);
		model.addAttribute("secondIndustryName", secondIndustryName);
		return "/admin/outline_category/parentList";
	}

	/**
	 * 子列表 id父节点ID childrenList 子类列表 parent 直接父节点 children parent下对应的所有子节点
	 */
	@RequestMapping(value = "/childrenList", method = RequestMethod.GET)
	public String childrenList(ModelMap model, Long id,Long isOpen,Long currentId) {
		OutlineCategory parent = outlineCategoryService.find(id);
		List<OutlineCategory> children = outlineCategoryService.findChildren(
				parent,null);
		children.add(0, parent);
		model.addAttribute("outlineCategoryTree", children);
		model.addAttribute("rootId", id);
		model.addAttribute("isOpen", isOpen);
		model.addAttribute("currentId", currentId);
		return "/admin/outline_category/childrenList";
	}
	/**
	 * 更新大纲的排序号
	 * @param currentNodeId 当前大纲ID
	 * @param currentOrder 当前大纲排序号
	 * @param moveNodeId 移动大纲ID
	 * @param moveOrder 移动大纲排序号
	 * @return 
	 */
	@RequestMapping(value = "/updateOutlineOrder", method = RequestMethod.POST)
	@ResponseBody
	public String updateOutlineOrder(Long currentNodeId,Long currentOrder,Long moveNodeId,Long moveOrder) {
		if(outlineCategoryService.updateOutlineOrder(currentNodeId, currentOrder, moveNodeId, moveOrder)){
			return "Y";
		}else{
			return "N";
		}
	}
	/**
	 * 查询的修改大纲时选择的父大纲的treePath
	 * @param parentId 所选大纲ID
	 * @return 
	 */
	@RequestMapping(value = "/cheackParetTreePath", method = RequestMethod.POST)
	@ResponseBody
	public String cheackParetTreePath(Long parentId) {
		return outlineCategoryService.find(parentId).getTreePath();
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(OutlineCategory outlineCategory, Long parentId,
			Long rootId, RedirectAttributes redirectAttributes,String continueAdd) {
		outlineCategory.setParent(outlineCategoryService.find(parentId));
		if (!isValid(outlineCategory)) {
			return ERROR_VIEW;
		}
		Admin currentAdmin = super.currentAdmin();
		outlineCategory.setCreateAdmin(currentAdmin);
		outlineCategory.setUpdatedAdmin(currentAdmin);
		if(parentId!=null){
			outlineCategory.setOrder(Integer.parseInt(outlineCategoryService.findMaxChildrenOrder(parentId).toString()));	
		}
		outlineCategoryService.save(outlineCategory);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		if (rootId != null) {
			if(continueAdd==null){
				continueAdd="false";
			}
			if(("true").equals(continueAdd)){
				return "redirect:addChildren.jhtml?id="+rootId+"&parentId="+parentId;
			}else{
				return "redirect:childrenList.jhtml?isOpen=1&currentId="+outlineCategory.getId()+"&id=" + rootId;
			}
		}
		return "redirect:parentList.jhtml";
	}

	/**
	 * 编辑最大父节点 maxParent 最大父节点
	 */
	@RequestMapping(value = "/editParent", method = RequestMethod.GET)
	public String editParent(Long id, ModelMap model, Long rootId) {
		OutlineCategory outlinecategory = outlineCategoryService.find(id);
		IndustryCategory industryCategory=industryCategoryService.find(outlinecategory.getIndustryCategoryID());
		model.addAttribute("outlineCategory", outlinecategory);
		model.addAttribute("industryCategory", industryCategory);
		model.addAttribute("rootId", rootId);
		return "/admin/outline_category/editParent";
	}

	/**
	 * 编辑子节点
	 */
	@RequestMapping(value = "/editChildren", method = RequestMethod.GET)
	public String editChildren(Long id, ModelMap model, Long rootId) {
		// 当前编辑的节点
		OutlineCategory children = outlineCategoryService.find(id);

		// 所属的一棵树的根节点----找到这棵树的所有子节点与根节点一起返回
		OutlineCategory parent = outlineCategoryService.find(rootId);
		List<OutlineCategory> categories = outlineCategoryService.findChildren(
				parent, null);
		categories.add(0, parent);

		// 查找子节点中是否有question
		Boolean b = questionService.hasQuestionByOutlineCategory(categories);

		model.addAttribute("hasQuestion", b);
		model.addAttribute("outlineCategoryTree", categories);
		model.addAttribute("outlineCategory", children);
		model.addAttribute("rootId", rootId);
		return "/admin/outline_category/editChildren";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Message delete(Long id) {
		OutlineCategory outlineCategory = outlineCategoryService.find(id);
		if (outlineCategory == null) {
			return ERROR_MESSAGE;
		}
		List<OutlineCategory> childrens = outlineCategoryService.findChildren(
				outlineCategory, null);
		if (childrens != null && !childrens.isEmpty()) {
			return DELETE_PARENT_ERROR_MESSAGE;
		}
		//modify limin 2015/04/20 start
		Set<Question> allQuestions=outlineCategory.getAllQuestions();
		if(allQuestions!=null && !allQuestions.isEmpty()){
			return Message.error("该大纲已被试题引用无法删除");
		}
		outlineCategoryService.delete(id);//TODO
		return SUCCESS_MESSAGE;
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(OutlineCategory outlineCategory,
			Long rootId, RedirectAttributes redirectAttributes,Long parentId) {
		if (!isValid(outlineCategory)) {
			return ERROR_VIEW;
		}
		if(parentId!=null){
			OutlineCategory parent=outlineCategoryService.find(parentId);
			outlineCategory.setParent(parent);
			outlineCategory.setIndustryCategoryID(parent.getIndustryCategoryID());
		}
		Date date=new Date();
		Admin currentAdmin = super.currentAdmin();
		outlineCategory.setUpdatedAdmin(currentAdmin);
		outlineCategory.setModifyDate(date);
		//根据大纲treePath更新，同时问题列表的treePath也更新
		outlineCategoryService.updateQuestionTreePathByOutlineCategory(outlineCategory);//TODO
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);

		if (parentId != null) {
			return "redirect:childrenList.jhtml?isOpen=1&currentId="+outlineCategory.getId()+"&id=" + rootId;
		}
		return "redirect:parentList.jhtml";
	}

	
	/**
	 * 取得最大编码
	 * 
	 * @param response
	 * @param parentId
	 */
	@RequestMapping(value = "/chackOutlineCategory", method = RequestMethod.POST)
	@ResponseBody
	public  boolean chackOutlineCategory(Long industryCategoryID) {
		return outlineCategoryService.findIndustryCategory(industryCategoryID);
	}
	/**
	 * 验证编码是否被引用
	 * 
	 * @param response
	 * @param parentId
	 */
	@RequestMapping(value = "/chack_outlineCategory_code", method = RequestMethod.GET)
	@ResponseBody
	public  boolean chackOutlineCategoryCode(String code,Long id) {
		if(id!=null){
			OutlineCategory outlineCategory = outlineCategoryService.find(id);
			if(outlineCategory.getCode().equals(code)){
				return true;
			}
		}
		return outlineCategoryService.findOutLineCategoryCode(code);
	}
	
	/**
	 * 大纲关联的课程名或章节名
	 */
	@RequestMapping(value="/relateInfo",method=RequestMethod.GET)
	public @ResponseBody
	String relateInfo(OutlineCategory outlineCategory){
		if(outlineCategory.getCourse_chapter_type()==Course_chapter_type.course){
			String title=courseService.findCourseTitle(outlineCategory.getCourse_chapter_id());
			return "{\"title\":\""+title+"\"}";
		}
		CourseChapter courseChapter = courseChapterService.find(outlineCategory.getCourse_chapter_id());
		
		//三目需要加括号
		return "{\"title\":\""+(courseChapter!=null?courseChapter.getTitle():null)+"\"}";
	}
	
	/**
	 *对大纲进行关联 
	 */
	@RequestMapping(value="/related",method=RequestMethod.POST)
	public @ResponseBody
	void related(OutlineCategory outlineCategory){
		
		if(outlineCategory.getCourse_chapter_id()==null || outlineCategory.getCourse_chapter_type()==null){
			return;
		}
		outlineCategoryService.relateCourseChapter(outlineCategory);
	}
	
	/**
	 * 要关联类别课程章节jsonName---couresId
	 */
	@RequestMapping(value="/relateCourseChapter",method=RequestMethod.GET)
	public @ResponseBody
	List<String> categoryAndCourse(Long parentId,Long jsonName){
		List<String> categoryAndCourse = new ArrayList<String>();
		if(parentId==null && jsonName==null){
			
			//获取课程类别
			List<CourseCategory> courseCategories=courseCategoryService.findTree();
			for (CourseCategory courseCategory:courseCategories) {
				categoryAndCourse.add(courseCategory.getGrade()+","+courseCategory.getId()+","+courseCategory.getName());
			}
		}else if(jsonName==null){
			
			//获取课程
			List<Course> courses = courseService.findList(parentId, null, null, null, null, null, null, null, null, null);
			for(Course course:courses){
				categoryAndCourse.add("0,"+course.getId()+","+course.getTitle());
			}
		}else if(jsonName!=null && parentId==null){
			
			//获取章
			List<CourseChapter> chapters=courseChapterService.findRoots(jsonName);
			for(CourseChapter chapter:chapters){
				categoryAndCourse.add("0,"+chapter.getId()+","+chapter.getTitle());
			}
			
		}else if(parentId !=null){
			
			//获取节
			CourseChapter chapterParent=new CourseChapter();
			chapterParent.setId(parentId);
			List<CourseChapter> chapters = courseChapterService.findUnit(chapterParent);
			for(CourseChapter chapter:chapters){
				categoryAndCourse.add("0,"+chapter.getId()+","+chapter.getTitle());
			}
		}
		return categoryAndCourse;
	}
}
