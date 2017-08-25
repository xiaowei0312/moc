package com.sram.controller.admin;

import javax.annotation.Resource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sram.Message;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.CourseThread;
import com.sram.entity.CourseThread.Type;
import com.sram.service.CourseLessonService;
import com.sram.service.CourseService;
import com.sram.service.CourseThreadPostService;
import com.sram.service.CourseThreadService;

@Controller("adminCourseThreadController")
@RequestMapping("/admin/courseThread")
public class CourseThreadController extends BaseController{
	@Resource(name="courseThreadServiceImpl")
	private CourseThreadService courseThreadService;
	@Resource(name="courseThreadPostServiceImpl")
	private CourseThreadPostService courseThreadPostService;
	@Resource(name="courseServiceImpl")
	private CourseService courseService;

	/**
	 * 异步关闭，打开问答
	 */
	@RequestMapping(value="/changeStatus")
	@ResponseBody
	public Message changeStatus(CourseThread courseThread){
		courseThreadService.changeStatus(courseThread);
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 前往list
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Integer courseThreadPosts,Type type,Integer isStick,
			Integer isElite,Integer isClosed,
			Long courseId,ModelMap model,Pageable pageable){
		Page<Object[]> page=courseThreadService.findPage(courseThreadPosts,
				type,isStick,isElite,isClosed,courseId,pageable);
		model.addAttribute("courseId", courseId);
		model.addAttribute("courseTitle",courseId==null?"all":courseService.findCourseTitle(courseId));
		model.addAttribute("courseThreadPosts",courseThreadPosts);
		model.addAttribute("type",type);
		model.addAttribute("isStick",isStick);
		model.addAttribute("isElite",isElite);
		model.addAttribute("isClosed",isClosed);
		model.addAttribute("page", page);
		return "/admin/course_thread/list";
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		courseThreadService.delete(ids);
		return SUCCESS_MESSAGE;
	}
	/**
	 * 查看该thread下的posts
	 * courseThread--id--member.username---title
	 */
	@RequestMapping(value="/lookPosts")
	public String lookPosts(CourseThread courseThread,Pageable pageable,ModelMap model){
		model.addAttribute("page",courseThreadPostService.findPage(null,null,courseThread.getId(),null,null,pageable));
		model.addAttribute("courseThread", courseThread);
		return "/admin/course_thread/post_list";
	}
	
	/**
	 * 删除回复
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/delete2")
	public @ResponseBody
	Message delPosts(Long[] ids){
		courseThreadPostService.delete(ids);
		return SUCCESS_MESSAGE;
	}
}
