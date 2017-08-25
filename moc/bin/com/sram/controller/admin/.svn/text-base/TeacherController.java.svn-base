/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.controller.admin;

import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.CommonAttributes;
import com.sram.Message;
import com.sram.Pageable;
import com.sram.SramException;
import com.sram.entity.Admin;
import com.sram.entity.Area;
import com.sram.entity.Course;
import com.sram.entity.Role;
import com.sram.entity.Teacher;
import com.sram.service.AdminService;
import com.sram.service.AreaService;
import com.sram.service.CourseService;
import com.sram.service.RoleService;
import com.sram.service.TeacherImageService;
import com.sram.service.TeacherService;


/**
 * Controller - 教师
 * 
 * @author Sram Team
 * @version 1.0
 */
@Controller("adminTeacherController")
@RequestMapping("/admin/teacher")
public class TeacherController extends BaseController {
	@Resource(name="teacherServiceImpl")
	private TeacherService teacherService;
	@Resource(name="teacherImageServiceImpl")
	private TeacherImageService teacherImageService;
	@Resource(name="courseServiceImpl")
	private CourseService courseService;
	@Resource(name = "areaServiceImpl")
	private AreaService areaService;
	@Resource(name = "adminServiceImpl")
	private AdminService adminService;
	@Resource(name = "roleServiceImpl")
	private RoleService roleService;
	
	
	
	/**
	 * 改变异步课程状态
	 */
	@RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
	public @ResponseBody 
	Message changeStatus(Teacher teacher){
		teacherService.changeStatus(teacher);
		return SUCCESS_MESSAGE;
	}
	/**
	 * 检验idcard是否存在
	 * @param idcard
	 * @return
	 */
	@RequestMapping(value="/check_idcard",method=RequestMethod.GET)
	public @ResponseBody 
	boolean checkIdcard(String idcard){
		if(StringUtils.isEmpty(idcard)){
			return false;
		}
		if(teacherService.idcardExists(idcard)){
			return false;
		}
		return true;
	}
	
	/**
	 * 验证手机号是否已存在
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value="/check_mobile",method=RequestMethod.GET)
	public @ResponseBody
	boolean checkMobile(String mobile,Long id){
		if(StringUtils.isEmpty(mobile)){
			return false;
		}
		if(teacherService.mobileExists(mobile,id)){
			return false;
		}
		return true;
	}
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable,
			String truename,String hasCourses,String recommended,
			Teacher teacher,
			ModelMap model) {
		
		model.addAttribute("page", teacherService.
				findPage(truename,null,recommended,pageable));
		model.addAttribute("hasCourse", hasCourses);
		model.addAttribute("recommended",recommended);
		model.addAttribute("courses", courseService.findByTeacher(teacher));
		
		return "/admin/teacher/list";
	}
	
	/**
	 * 分配账号页面
	 */
	@RequestMapping(value = "/toAccount", method = RequestMethod.GET)
    public String toAccount(Long id,ModelMap model){
		model.addAttribute("roles", roleService.findAll());
		model.addAttribute("teacherId",id);
    	return "/admin/teacher/account";
    }

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model) {
		return "/admin/teacher/add";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Teacher teacher, HttpServletRequest request,
			MultipartFile file,String birthday,
			RedirectAttributes redirectAttributes) {
		try {
			Date birth = StringUtils.isNotEmpty(birthday) ? DateUtils.parseDate(birthday, CommonAttributes.DATE_PATTERNS) : null;
			teacher.setBirthday(birth);
		} catch (ParseException e) {
			return ERROR_VIEW;
		}
		teacherService.save(teacher);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}
	
	@RequestMapping(value = "/saveUserInfo", method = RequestMethod.POST)
	public String saveUserInfo(Admin admin, Long[] roleIds, Long teacherId,
			RedirectAttributes redirectAttributes){
		admin.setRoles(new HashSet<Role>(roleService.findList(roleIds)));
		if (adminService.usernameExists(admin.getUsername())) {
			return ERROR_VIEW;
		}
		admin.setPassword(DigestUtils.md5Hex(admin.getPassword()));
		admin.setIsLocked(false);
		admin.setLoginFailureCount(0);
		adminService.save(admin);
		Teacher teacher=teacherService.find(teacherId);
		Long id=admin.getId();
		teacher.setAdminId(id);
		teacherService.update(teacher);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";	
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Teacher teacher, 
			MultipartFile file,String birthday,
			RedirectAttributes redirectAttributes) {
		try {
			Date birth = StringUtils.isNotEmpty(birthday) ? DateUtils.parseDate(birthday, CommonAttributes.DATE_PATTERNS) : null;
			teacher.setBirthday(birth);
		} catch (ParseException e) {
			return ERROR_VIEW;
		}
			teacherService.update(teacher);
			addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		try {
			teacherService.delete(ids);
		} catch (SramException e) {
			return Message.error(""+e.getMessage()+"");
		}
		return SUCCESS_MESSAGE;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Teacher teacher, ModelMap model) {
		Teacher teacher2 = teacherService.find(teacher.getId());
		String city = teacher2.getCity();
		
		//获得给老师的地区实体
		Area area=null;
		if(city != null && !city.isEmpty()){
			area = areaService.find(Long.parseLong(city));
		}
		
		model.addAttribute("cityArea", area);
		model.addAttribute("teacher", teacher2);
		return "/admin/teacher/edit";
	}
}