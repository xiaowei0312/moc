package com.sram.controller.admin;


import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.Message;
import com.sram.Pageable;
import com.sram.Page;
import com.sram.entity.Course;
import com.sram.entity.CourseAnnouncement;
import com.sram.service.CourseAnnouncementService;
import com.sram.service.CourseService;

/*
 * 课程公告管理
 */
@Controller("adminCourseAnnouncementController")
@RequestMapping("/admin/courseannouncement")
public class CourseAnnouncementController  extends BaseController{
	
	@Resource(name = "courseServiceImpl")
	private CourseService courseService;
    
	@Resource(name = "courseAnnouncementServiceImpl")
	private CourseAnnouncementService courseAnnouncementService;
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Integer coursePageNumber,Long courseId,ModelMap model,Pageable pageable){
		Course course=null;
		if(courseId!=null){
			course=new Course();
			course.setId(courseId);
		}
		Page<CourseAnnouncement> announcementList=courseAnnouncementService.findPage(course,pageable);
		model.addAttribute("coursePageNumber",coursePageNumber);
		model.addAttribute("page", announcementList);
		model.addAttribute("courseId", courseId);
		model.addAttribute("courseTitle", courseId==null?"all":courseService.findCourseTitle(courseId));
		return "/admin/course_announcement/list";
	}
	
	
	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Long courseId,ModelMap model) {
		model.addAttribute("courseId", courseId);
		return "/admin/course_announcement/add";
	}
	
	/*
	 * 添加公告
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Long courseId,CourseAnnouncement courseAnnouncement,RedirectAttributes redirectAttributes){
	
		courseAnnouncement.setUserId(this.currentAdmin().getId());
		Course course=new Course();
		course.setId(courseId);
		courseAnnouncement.setCourse(course);
		courseAnnouncement.setCreateDate(new Date());
		courseAnnouncement.setModifyDate(null);
		courseAnnouncementService.save(courseAnnouncement);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}
	
	/*
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id,ModelMap model){
		CourseAnnouncement  courseAnnouncement=courseAnnouncementService.find(id);
		model.addAttribute("courseAnnouncement", courseAnnouncement);
		return "/admin/course_announcement/edit";
	}
	
	
	/*
	 * 更改公告
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(CourseAnnouncement courseAnnouncement,RedirectAttributes redirectAttributes){
		courseAnnouncement.setModifyDate(new Date());
		courseAnnouncementService.update(courseAnnouncement);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}
	
	/*
	 * 删除公告
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		courseAnnouncementService.delete(ids);
		return SUCCESS_MESSAGE;
	}	
}
