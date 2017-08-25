/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.template.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sram.entity.Course;
import com.sram.service.CourseService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模板指令 - 课程列表
 * 
 * @author Sram Team
 * @version 1.0
 */
@Component("courseListDirective")
public class CourseListDirective extends BaseDirective {

	/** 变量名称 */
	private static final String VARIABLE_NAME = "courses";

	@Resource(name = "courseServiceImpl")
	private CourseService courseService;
	
	
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		List<Course> courses;
		courses=courseService.findAll();
		setLocalVariable(VARIABLE_NAME, courses, env, body);
	}

}