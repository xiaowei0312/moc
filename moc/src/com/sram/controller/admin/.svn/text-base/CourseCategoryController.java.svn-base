/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.controller.admin;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.Message;
import com.sram.entity.Course;
import com.sram.entity.CourseCategory;
import com.sram.service.CourseCategoryService;

/**
 * Controller - 课程类别
 * 
 * @author Sram Team
 * @version 1.0
 */
@Controller("adminCourseCategoryController")
@RequestMapping("/admin/course_category")
public class CourseCategoryController extends BaseController {

	@Resource(name = "courseCategoryServiceImpl")
	private CourseCategoryService courseCategoryService;

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("courseCategoryRoot", courseCategoryService.findRoots());
		return "/admin/course_category/add";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(CourseCategory courseCategory,Long parentId,RedirectAttributes redirectAttributes) {
		courseCategory.setParent(courseCategoryService.find(parentId));
		if (!isValid(courseCategory)) {
			return ERROR_VIEW;
		}
		courseCategoryService.save(courseCategory);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, ModelMap model) {
		CourseCategory courseCategory = courseCategoryService.find(id);
		model.addAttribute("courseCategoryRoot", courseCategoryService.findRoots());
		model.addAttribute("courseCategory", courseCategory);
		model.addAttribute("children", courseCategoryService.findChildren(courseCategory));
		return "/admin/course_category/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(CourseCategory courseCategory,Long parentId,RedirectAttributes redirectAttributes) {
		courseCategory.setParent(courseCategoryService.find(parentId));
		if (!isValid(courseCategory)) {
			return ERROR_VIEW;
		}
		if (courseCategory.getParent() != null) {
			CourseCategory parent = courseCategory.getParent();
			if (parent.equals(courseCategory)) {
				return ERROR_VIEW;
			}
			List<CourseCategory> children = courseCategoryService.findChildren(parent);
			if (children != null && children.contains(parent)) {
				return ERROR_VIEW;
			}
		}
		
		courseCategory=courseCategoryService.update(courseCategory,"treePath", "grade", "children", "courses");
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap model) {
		model.addAttribute("courseCategoryTree", courseCategoryService.findTree());
		return "/admin/course_category/list";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long id) {
		try {
			courseCategoryService.delete(id);
		} catch (Exception e) {
			if(e instanceof DataIntegrityViolationException){
				return Message.error("亲，该类别有关联的数据---无法删除哈");
			}
			return Message.error("无法删除"
					+"\r\n"+e.toString());
		}
		return SUCCESS_MESSAGE;
	}

}