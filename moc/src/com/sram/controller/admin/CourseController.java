/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.Message;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.Course;
import com.sram.entity.Course.DeadlineNotify;
import com.sram.entity.Course.SerializeMode;
import com.sram.entity.Course.ShowStudentNumType;
import com.sram.entity.Course.Status;
import com.sram.entity.Course.UserType;
import com.sram.entity.CourseCategory;
import com.sram.entity.Member;
import com.sram.service.CourseCategoryService;
import com.sram.service.CourseImageService;
import com.sram.service.CourseLearnService;
import com.sram.service.CourseService;
import com.sram.service.MemberService;
import com.sram.service.StaticService;
import com.sram.service.TeacherService;

/**
 * Controller - 课程
 * 
 * @author Sram Team
 * @version 1.0
 */
@Controller("adminCourseController")
@RequestMapping("/admin/course")
public class CourseController extends BaseController {
	@Resource(name = "courseServiceImpl")
	private CourseService courseService;
	@Resource(name = "courseCategoryServiceImpl")
	private CourseCategoryService courseCategoryService;
	@Resource(name = "courseImageServiceImpl")
	private CourseImageService courseImageService;
	@Resource(name="teacherServiceImpl")
	private TeacherService teacherService;
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "courseLearnServiceImpl")
	private CourseLearnService courseLearnService;
	@Resource(name="staticServiceImpl")
	private StaticService staticService;
	
	
	/**
	 * 推荐课程
	 */
	@RequestMapping(value="/changeRecommend")
	public @ResponseBody
	Message changeRecommend(Course course){
		courseService.changeRecommend(course);
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 设置推荐序号
	 */
	@RequestMapping(value="/changeRecommendedSeq")
	public @ResponseBody
	Message changeRecommendedSeq(Course course){
		courseService.changeRecommendedSeq(course);
		return SUCCESS_MESSAGE;
	}
	/**
	 * 改变异步课程状态
	 */
	@RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
	public @ResponseBody 
	Message changeStatus(Course course){
		courseService.changeStatus(course);
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 改变学生备注
	 */
	@RequestMapping(value = "/changeRemark", method = RequestMethod.POST)
	public @ResponseBody 
	Message  changeRemark(Long id,String description){
		courseLearnService.changeRemark(id,description);
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Long courseCategoryId, Pageable pageable,
			Status status,Integer maxStudentNum,Integer recommended,
			Integer vipLevelId,Float price,UserType userType,
			SerializeMode serializeMode,ShowStudentNumType showStudentNumType,
			DeadlineNotify deadlineNotify,
			ModelMap model) {
		CourseCategory courseCategory = courseCategoryService
				.find(courseCategoryId);
		Page<Course> courses = courseService.findPage(courseCategory,
				status,maxStudentNum,recommended,vipLevelId,
				price,userType,serializeMode,showStudentNumType,
				deadlineNotify,
				pageable);
		model.addAttribute("courseCategoryTree",
				courseCategoryService.findTree());
		model.addAttribute("enum", Course.DeadlineNotify.values());
		model.addAttribute("enum2", Course.SerializeMode.values());
		model.addAttribute("enum3", Course.ShowStudentNumType.values());
		model.addAttribute("courseCategory", courseCategory);
		model.addAttribute("courseCategoryId", courseCategoryId);
		model.addAttribute("status", status);
		model.addAttribute("maxStudentNum", maxStudentNum);
		model.addAttribute("recommended", recommended);
		model.addAttribute("vipLevelId", vipLevelId);
		model.addAttribute("price", price);
		model.addAttribute("userType", userType);
		model.addAttribute("serializeMode", serializeMode);
		model.addAttribute("showStudentNumType", showStudentNumType);
		model.addAttribute("deadlineNotify", deadlineNotify);
		model.addAttribute("page", courses);
		
		return "/admin/course/list";
	}
	
	/**
	 * 前往推荐课程列表
	 */
	@RequestMapping(value="/recommendList")
	public String recommendList(ModelMap model,Pageable pageable){
		Page<Object[]> page=courseService.findRecommendCourse(pageable);
		model.put("page", page);
		return "/admin/course/recommendList";
	}
	
	/**
	 * 学员管理 学员列表
	 */
	@RequestMapping(value = "/memberList", method = RequestMethod.GET)
	public String memberList(ModelMap model,Pageable pageable,Long courseId){
		//查找课程的学员列表
		Page<Object[]> page=courseService.findMemsByCourId(pageable,courseId);
		model.addAttribute("page", page);
		model.addAttribute("courseId",courseId);
		model.addAttribute("courseTitle", courseId==null?"all":courseService.findCourseTitle(courseId));
		return "/admin/course/memberList";
	}
	
	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		model.addAttribute("courseCategoryTree",
				courseCategoryService.findTree());
		model.addAttribute("enum", Course.DeadlineNotify.values());
		model.addAttribute("enum2", Course.SerializeMode.values());
		model.addAttribute("enum3", Course.ShowStudentNumType.values());
		model.addAttribute("enum4", Course.Status.values());
		
		model.addAttribute("teachers",teacherService.findAll());
		return "/admin/course/add";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Course course,
			Long[] teacherIds,
			HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		
		Long categoryId=course.getCourseCategory().getId();
		
		CourseCategory courseCategory=courseCategoryService.find(categoryId);
		course.setTreePath(courseCategory.getTreePath());

		//加入任课教师
		String temp="";
		if(teacherIds!=null && teacherIds.length>0){
			for(Long teacherId:teacherIds){
				temp += teacherId+",";
			}
		}else{
			temp = null;
		}
		
		course.setTeacherIds(temp);
		course.setAdmin(currentAdmin());
		courseService.save(course);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Course course,
			Long[] teacherIds, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		Long categoryId=course.getCourseCategory().getId();
		
		CourseCategory courseCategory=courseCategoryService.find(categoryId);
		course.setTreePath(courseCategory.getTreePath());
		//更新任课教师
		String temp="";
		if(teacherIds != null && teacherIds.length>0){
			for(Long teacherId:teacherIds){
				temp += teacherId+",";
			}
		}else{
			temp=null;
		}
		course.setTeacherIds(temp);
		course.setAdmin(currentAdmin());
		Course courseEntity=courseService.update(course,"createDate");
		staticService.build(courseEntity);
		staticService.buildIndex();
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		
		//判断要删除的课程是否有chapter 删除课程的图片
		Long courseId=null;
		Course course;
		try {
			for(Long id:ids){
				courseId=id;
				course=courseService.find(id);
				courseService.delete(course);
				courseImageService.deleteCourseImage(course);
			}
		} catch (Exception e) {
			if(e instanceof DataIntegrityViolationException){
				String courseName=courseService.findCourseTitle(courseId);
				return Message.error("亲，课程["+courseName+"]有关联的数据---无法删除哈");
			}
			return Message.error("无法删除"
					+"\r\n"+e.toString());
		}
		return SUCCESS_MESSAGE;
	}
	
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id,Long courseid, ModelMap model) {
		Course course = courseService.find(courseid);
		CourseCategory courseCategory = courseCategoryService.find(id);
		model.addAttribute("courseCategoryTree", courseCategoryService.findTree());
		model.addAttribute("courseCategory", courseCategory);
		model.addAttribute("enum", Course.DeadlineNotify.values());
		model.addAttribute("enum2", Course.SerializeMode.values());
		model.addAttribute("enum3", Course.ShowStudentNumType.values());
		model.addAttribute("enum4", Course.Status.values());
		model.addAttribute("course",course);
		model.addAttribute("children", courseCategoryService.findChildren(courseCategory));
		
		//找到该课程意外的教师
		model.addAttribute("selectedTeachers",teacherService.findTeachersNotIds(course));
		
		//找到该课程的教师
		model.addAttribute("teachers",teacherService.findListByCourse(course));
		return "/admin/course/edit";
	}
	
	/*
	 * 查看课程详细信息
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Long courseid,ModelMap model){
		Course course=courseService.find(courseid);
		model.addAttribute("course",course);
		//找到该课程意外的教师
		model.addAttribute("selectedTeachers",teacherService.findTeachersNotIds(course));
		//找到该课程的教师
		model.addAttribute("teachers",teacherService.findListByCourse(course));
		return "/admin/course/view";
	}
	
	/**
	 * 查看课程学员详细信息
	 */
	@RequestMapping(value = "/viewMember", method = RequestMethod.GET)
	public String viewMember(Long id,ModelMap model){
		Member member=memberService.find(id);
		model.addAttribute("member", member);
		return "/admin/course/memberView";
	}
}