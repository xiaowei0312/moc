package com.sram.controller.moc;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sram.Pageable;
import com.sram.controller.admin.BaseController;
import com.sram.service.TeacherService;


/**
 * Controller - 教师
 * 
 * @author Sram Team
 * @version 1.0
 */
@Controller("mocTeacherController")
@RequestMapping("/teacher")
public class TeacherController  extends BaseController{
	@Resource(name="teacherServiceImpl")
	private TeacherService teacherService;
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value = "/list")
	public String list(Pageable pageable,ModelMap model){
		model.addAttribute("page", teacherService.findPage(pageable));
		return "/moc/teacher/list";
	}
}
