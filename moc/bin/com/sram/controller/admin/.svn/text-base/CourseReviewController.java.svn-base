package com.sram.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sram.Message;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.CourseReview;
import com.sram.service.CourseReviewService;
import com.sram.service.CourseService;

@Controller("adminCourseReviewController")
@RequestMapping("/admin/courseReview")
public class CourseReviewController extends BaseController{
	@Resource(name="courseReviewServiceImpl")
	private CourseReviewService courseReviewService;
	@Resource(name="courseServiceImpl")
	private CourseService courseService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Long courseId,Pageable pageable,ModelMap model){
		Page<CourseReview> page = courseReviewService.findPage(courseId,pageable);
		model.addAttribute("courseId", courseId);
		model.addAttribute("page",page);
		model.addAttribute("courseTitle",courseId==null?"all":courseService.findCourseTitle(courseId));
		return "/admin/course_review/list";
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		courseReviewService.delete(ids);
		return SUCCESS_MESSAGE;
	}
}
