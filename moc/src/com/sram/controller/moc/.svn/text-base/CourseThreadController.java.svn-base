package com.sram.controller.moc;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.Principal;
import com.sram.controller.admin.BaseController;
import com.sram.entity.Course;
import com.sram.entity.CourseCategory;
import com.sram.entity.CourseThread;
import com.sram.entity.CourseThreadPost;
import com.sram.entity.Member;
import com.sram.service.CourseCategoryService;
import com.sram.service.CourseService;
import com.sram.service.CourseThreadPostService;
import com.sram.service.CourseThreadService;
import com.sram.service.MemberService;


@Controller("mocCourseThreadController")
@RequestMapping("/courseThread")
public class CourseThreadController extends BaseController{
   
	@Resource(name="courseThreadServiceImpl")
	private CourseThreadService courseThreadService;
	@Resource(name="memberServiceImpl")
	private MemberService memberService;
	
	@Resource(name = "courseServiceImpl")
	private CourseService courseService;
	
	@Resource(name = "courseCategoryServiceImpl")
	private CourseCategoryService courseCategoryService;
	
	@Resource(name="courseThreadPostServiceImpl")
	private CourseThreadPostService courseThreadPostService;
	
	/**
	 * 全部问答List
	 */
	@RequestMapping(value="/questionList")
	public String questionList(ModelMap model,Pageable pageable,String threadContent,String tabFlag,Long categoryId){
		pageable.setPageSize(15);
		if(("请输入关键字").equals(threadContent)){
			threadContent="";
		}
		//查找所有的问答
		Page<Object[]> page=courseThreadService.findAllQuestionPage(pageable,threadContent,tabFlag,categoryId);
		//查找回复问题最多的三个用户
		List<Object[]> list=memberService.findHotMember();
		//查找一条热门话题
		List<Object[]> hotThreadList=courseCategoryService.findHotThread();
		//找到问题所有的二级分类
		//List<CourseCategory> cateGoryList=courseCategoryService.findSecondCategory();
		List<CourseCategory> cateGoryList=courseCategoryService.findTree();
		CourseCategory courseCategory=null;
		if(categoryId!=null && categoryId!=0){
		     courseCategory=courseCategoryService.find(categoryId);
		}		    
		model.addAttribute("page", page);
		model.addAttribute("threadContent",threadContent);
	    model.addAttribute("tabFlag", tabFlag);
	    model.addAttribute("list", list);
	    model.addAttribute("hotThreadList", hotThreadList);
	    model.addAttribute("cateGoryList", cateGoryList);
	    model.addAttribute("courseCategory", courseCategory);
	    model.addAttribute("categoryId", categoryId);
		return "/moc/course_thread/list";
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
	
	
	/**
	 * 提问问题页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(String tabFlag,String askContent,ModelMap model){
		List<CourseThread> list=courseThreadService.findHostThread();
		model.addAttribute("list", list);
		model.addAttribute("askContent", askContent);
		model.addAttribute("tabFlag",tabFlag);
		return "moc/course_thread/add";
	}
	
	/**
	 * 添加问答
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request,CourseThread courseThread,Long courseCategoryId,
			RedirectAttributes redirectAttributes){
		//获得当前登陆的用户Id
		Principal principal=(Principal) request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
		Long memberId=principal.getId();
		Member member=new Member();
		member.setId(memberId);
		courseThread.setMember(member);
		CourseCategory courseCategroy=new CourseCategory();
		courseCategroy.setId(courseCategoryId);
		courseThread.setCourseCategory(courseCategroy);
		courseThreadService.save(courseThread);
		return "redirect:questionList.jhtml?tabFlag=newestThread";
	}
	
	/**
	 * 添加回复
	 */
	@RequestMapping(value = "/saveThreadPost", method = RequestMethod.POST)
	public String  saveThreadPost(HttpServletRequest request,Long threadId,RedirectAttributes redirectAttributes
			,String threadContent){
		//获得当前登陆的用户Id
		Principal principal=(Principal) request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
		Long memberId=principal.getId();
		Member member=new Member();
		member.setId(memberId);
		CourseThreadPost courseThreadPost=new CourseThreadPost();
		courseThreadPost.setMember(member);
		CourseThread courseThread=courseThreadService.find(threadId);
		courseThreadPost.setCourseThread(courseThread);
		Course course=courseThread.getCourse();
		if(course!=null){
			courseThreadPost.setCourseId(course.getId());
		}
		if(courseThread.getCourseLesson()!=null){
		    courseThreadPost.setLessonId(courseThread.getCourseLesson().getId());
		}
		courseThreadPost.setContent(threadContent);
		courseThreadPostService.save(courseThreadPost);
		return "redirect:view.jhtml?threadId="+threadId;
	}
}
