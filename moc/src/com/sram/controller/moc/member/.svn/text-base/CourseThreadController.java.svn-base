package com.sram.controller.moc.member;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.Principal;
import com.sram.controller.admin.BaseController;
import com.sram.entity.CourseCategory;
import com.sram.entity.CourseThread;
import com.sram.entity.Member;
import com.sram.service.CourseCategoryService;
import com.sram.service.CourseThreadService;


@Controller("mocMemberCourseThreadController")
@RequestMapping("/member/courseThread")
public class CourseThreadController extends BaseController{
	
	@Resource(name = "courseCategoryServiceImpl")
	private CourseCategoryService courseCategoryService;
    
	@Resource(name="courseThreadServiceImpl")
	private CourseThreadService courseThreadService;
	
	/**
	 * 我的问答list
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(HttpServletRequest request,ModelMap model,Pageable pageable,String questionType){
		//获得当前登陆的用户Id
		Principal principal=(Principal) request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
		Long memberId=principal.getId();
		
		if(("question").equals(questionType)){
			//查找我提问的问题
			Page<CourseThread> page=courseThreadService.findMyQuestionPage(pageable,memberId);
			model.addAttribute("page", page);
		}else if(("answer").equals(questionType)){
		   //查找我回答的问题
			Page<Object[]> page=courseThreadService.findMyAnswerPage(pageable,memberId);
			model.addAttribute("page", page);
		}
		if(principal!=null){
			model.addAttribute("headImage",principal.getHeadImage());
		}
		model.addAttribute("questionType", questionType);
		return "moc/member/course_thread/list";
	}
	
	
	/**
	 * 异步获取CourseCategory根据courseCategoryId
	 */
	@RequestMapping(value = "/getCourseCategoryChildren", method = RequestMethod.GET)
	public @ResponseBody
	List<String> getCourseCategoryChildren(Long parentId){
		List<CourseCategory> courseCategorys=new ArrayList<CourseCategory>();
		if(parentId!=null && parentId!=0){
			CourseCategory courseCategory=courseCategoryService.find(parentId);
			courseCategorys=courseCategoryService.findChildren(courseCategory,null);
		}else{
			courseCategorys=courseCategoryService.findRoots();
		}
		List<String> options = new ArrayList<String>();
		for(CourseCategory category:courseCategorys){
			options.add(category.getGrade()+","+category.getId()+","+category.getName());
		}
		return options;
	}

	/**
	 * 查看问题详细信息
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Long threadId,ModelMap model,String threadContent,String answerFlag){
		CourseThread courseThread=courseThreadService.find(threadId);
		Long categoryId=courseThread.getCourseCategory().getId();
		//每页显示多少个
		int pageSize=5;
		List<CourseThread> relateThreads=courseThreadService.findRelateThreads(categoryId,pageSize);
		model.addAttribute("courseThread", courseThread);
		model.addAttribute("threadContent",threadContent);
		model.addAttribute("answerFlag",answerFlag);
		model.addAttribute("relateThreads",relateThreads);
		return "moc/course_thread/view";
	}
	
}
