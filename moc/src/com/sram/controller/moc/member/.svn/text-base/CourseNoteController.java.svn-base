package com.sram.controller.moc.member;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sram.Message;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.Principal;
import com.sram.controller.admin.BaseController;
import com.sram.entity.CourseNote;
import com.sram.entity.Member;
import com.sram.service.CourseNoteService;
import com.sram.service.MemberService;

@Controller("mocCourseNoteController")
@RequestMapping("/member/courseNote")
public class CourseNoteController extends BaseController{
	@Resource(name="courseNoteServiceImpl")
	private CourseNoteService courseNoteService;
	
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	
	/**
	 * 我的笔记列表
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(HttpServletRequest request,Pageable pageable,ModelMap model){
		//获得当前登陆的用户
		Principal principal=(Principal) request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
		Long memberId=principal.getId();
		Page<CourseNote> page = courseNoteService.findPageByUserId(memberId,pageable);
		model.addAttribute("page", page);
		if(principal!=null){
			model.addAttribute("headImage",principal.getHeadImage());
		}
		return "moc/member/course_note/list";
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long courseNoteId){
		courseNoteService.delete(courseNoteId);
		return SUCCESS_MESSAGE;
	}
}
