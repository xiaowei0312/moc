package com.sram.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sram.Message;
import com.sram.Pageable;
import com.sram.entity.CourseThread;
import com.sram.service.CourseThreadPostService;

@Controller("adminCourseThreadPostController")
@RequestMapping("/admin/courseThreadPost")
public class CourseThreadPostController extends BaseController{
	@Resource(name="courseThreadPostServiceImpl")
	private CourseThreadPostService courseThreadPostService;

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		courseThreadPostService.delete(ids);
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
}
