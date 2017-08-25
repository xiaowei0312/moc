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
import com.sram.entity.CourseNote;
import com.sram.service.CourseNoteService;
import com.sram.service.CourseService;

@Controller("adminCourseNoteController")
@RequestMapping("/admin/courseNote")
public class CourseNoteController extends BaseController{
	@Resource(name="courseNoteServiceImpl")
	private CourseNoteService courseNoteService;
	@Resource(name="courseServiceImpl")
	private CourseService courseService;
	/**
	 * 列表
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Long courseId,Pageable pageable,ModelMap model){
		Page<CourseNote> page = courseNoteService.findPage(courseId,pageable);
		model.addAttribute("courseId", courseId);
		model.addAttribute("courseTitle", courseId==null?"all":courseService.findCourseTitle(courseId));
		model.addAttribute("page",page);
		return "/admin/course_note/list";
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		courseNoteService.delete(ids);
		return SUCCESS_MESSAGE;
	}
}
