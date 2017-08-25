package com.sram.controller.moc;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sram.Constants;
import com.sram.Filter;
import com.sram.Constants.ShareType;
import com.sram.Filter.Operator;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.Principal;
import com.sram.entity.Course.Status;
import com.sram.entity.CourseThread;
import com.sram.entity.Member;
import com.sram.service.CourseLearnService;
import com.sram.service.CourseReviewService;
import com.sram.service.CourseService;
import com.sram.service.CourseThreadService;
import com.sram.util.IntegralRuleHandleUtil;



/**
 * Controller - 课程
 * 
 * @author Sram Team
 * @version 1.0
 */
@Controller("mocCourseController")
@RequestMapping("/course")
public class CourseController extends BaseController{
	@Resource(name = "courseServiceImpl")
	private CourseService courseService;
	@Resource(name="courseThreadServiceImpl")
	private CourseThreadService courseThreadService;
	@Resource(name="courseReviewServiceImpl")
	private CourseReviewService courseReviewService;
	@Resource(name = "courseLearnServiceImpl")
	private CourseLearnService courseLearnService;
	
	/**
	 * 显示课程列表
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/list")
	public String list(ModelMap model,Pageable pageable,String courseName,Long categoryId,String rootFlag
			, HttpServletRequest request) throws UnsupportedEncodingException{
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		boolean loginFlag=false;
		if(principal!=null){
			loginFlag=true;
		}
		
		pageable.setPageSize(12);

		if(("请输入关键字").equals(courseName)){
			 courseName="";
		}	  
        
		Page<Object[]> courses = courseService.findPublishedPage( 
			 Status.published,pageable,courseName,categoryId);
		model.addAttribute("page",courses);
		model.addAttribute("courseName",courseName);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("rootFlag",rootFlag);
		model.addAttribute("loginFlag",loginFlag);
		return "/moc/course/list";
	}
	
	/**
	 * 判断当前用户是否登录
	 */
	@RequestMapping(value = "/aleradyLogin")
	@ResponseBody
	public String aleradyLogin(ModelMap model, HttpServletRequest request) {
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);

		String aleradyLogin = principal == null ? "fail" : "success";
		String uuid = null;
		if (aleradyLogin.equals("fail")) {
			uuid = UUID.randomUUID().toString();
		}
		return "{\"aleradyLogin\":\"" + aleradyLogin + "\",\"captchaId\":\""
				+ uuid + "\"}";
	}

	
	/**
	 * count,1--评论、2--问答
	 */
	@RequestMapping(value = "/getCount")
	@ResponseBody
	public Long getCount(int index, Long courseId) {
		Filter filters = new Filter();
		filters.setProperty("course");
		filters.setValue(courseId);
		filters.setOperator(Operator.eq);
		Filter openFilter = new Filter();

		if (index == 1) {
			return courseReviewService.count(filters);
		}
		openFilter.setOperator(Operator.eq);
		openFilter.setProperty("isClosed");
		openFilter.setValue(false);
		return courseThreadService.count(filters, openFilter);
	}

	
	/**
	 * 异步获取数据page,1--评论、2--问答,page是当前页码，3--是资料 每次都查询10条
	 */
	@RequestMapping(value = "/getInfo")
	public String getInfo(int index, Pageable pageable, Long courseId,
			ModelMap model) {
		String pageStr = "";
		if (index == 1) {
			model.addAttribute("page",

			// 借用了pageable,分页是用的前台的jquery.pagination.js
					courseReviewService.findPageList(courseId, pageable));
			pageStr = "/moc/course/reviewInfo";
		} else if (index == 2) {

			// 0,关闭---1是开启---其它是所有(分页是前台的jquery.pagination.js)
			Page<CourseThread> threads = courseThreadService.findList(null,
					null, null, null, 1, courseId, pageable);
			model.addAttribute("page", threads);
			pageStr = "/moc/course/questionInfo";
		} else if (index == 3) {

			pageStr = "/moc/course/dataInfo";
		}
		return pageStr;
	}
	
	/**
	 * 用户是否已学习相应课程
	 * @throws IOException 
	 */
	@RequestMapping(value = "/alreadyLearn")
	public void alreadyLearn(HttpServletRequest request,HttpServletResponse respone,Long courseId
			,String createDateStr) throws IOException{
		// 获得当前登陆的用户Id
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		boolean learnFlag=false;
		
		if(principal!=null){
			Long memberId = principal.getId();
			learnFlag=courseLearnService.courseLearnExists(memberId,courseId);
			if(learnFlag){
				respone.sendRedirect(request.getContextPath()+"/course/content2/"+createDateStr+"/"+courseId+"2.html");
			}else{
				respone.sendRedirect(request.getContextPath()+"/course/content/"+createDateStr+"/"+courseId+".html");
			}
		}else{
			respone.sendRedirect(request.getContextPath()+"/course/content/"+createDateStr+"/"+courseId+".html");
		}
	}
	
	@RequestMapping(value="/shareCourse/{shareType}",method=RequestMethod.GET)
	public @ResponseBody
	void shareCourse(@PathVariable ShareType shareType,HttpServletRequest request,HttpServletResponse response){
		
		Principal principal=(Principal)request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
		if(principal==null){
			return;
		}
		
		//处理积分任务
		IntegralRuleHandleUtil.dispatchTask(request, response,
				Constants.COURSE_SHARE_URL+shareType);
	}
}
