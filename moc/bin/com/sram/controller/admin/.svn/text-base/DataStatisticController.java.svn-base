package com.sram.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.service.CourseCategoryService;
import com.sram.service.CourseLessonService;
import com.sram.service.CourseService;


/*
 * 数据统计controller
 */
@Controller("adminDataStatisticController")
@RequestMapping("/admin/datastatistic")
public class DataStatisticController extends BaseController{
	@Resource(name = "courseServiceImpl")
	private CourseService courseService;
	@Resource(name="courseLessonServiceImpl")
	private CourseLessonService  courseLessonService;
	@Resource(name = "courseCategoryServiceImpl")
	private CourseCategoryService courseCategoryService;
	
	/**
	 * 数据统计List
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(ModelMap model,Pageable pageable,Long courseCategoryId){
		Page<Object[]> page=courseService.findStaPage(pageable,courseCategoryId);
		model.addAttribute("courseCategoryTree",
				courseCategoryService.findTree());
		model.addAttribute("page", page);
		model.addAttribute("courseCategoryId", courseCategoryId);
		return "/admin/data_statistic/list";
	}
	
	/**
	 * 课时数据统计List
	 */
	@RequestMapping(value="/lesson_list",method=RequestMethod.GET)
	public String lessonList(ModelMap model,Pageable pageable,Long courseId){
		Page<Object[]> page=courseLessonService.findStaPage(pageable,courseId);
		model.addAttribute("page", page);
		model.addAttribute("courseId", courseId);
		return "/admin/data_statistic/lessonList";
	}
}
