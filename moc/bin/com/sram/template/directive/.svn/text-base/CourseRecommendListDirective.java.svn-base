package com.sram.template.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sram.Filter;
import com.sram.entity.Course;
import com.sram.entity.Course.Status;
import com.sram.service.CourseService;
import com.sram.util.FreemarkerUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
@Component("courseRecommendListDirective")
public class CourseRecommendListDirective extends BaseDirective{

	/** 课程分类ID参数名称*/
	private static final String COURSE_CATEGORY_ID_PARAMETER_NAME="courseCategoryId";
	
	/**变量名称*/
	private static final String VARIABLE_NAME="courses";
	
	@Resource(name="courseServiceImpl")
	private CourseService courseService;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] arg2,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		
		Long courseCategoryId=FreemarkerUtils.getParameter(COURSE_CATEGORY_ID_PARAMETER_NAME, Long.class, params);
		
		List<Course> courses;
		boolean useCache = useCache(env,params);
		String cacheRegion = getCacheRegion(env, params);
		List<Filter> filters = getFilters(params, Course.class);
		Integer count = getCount(params);
		if(useCache){
			courses=courseService.findListRecommendCourse(courseCategoryId,count,filters,cacheRegion);
		}else{
			courses=courseService.findListRecommendCourse(courseCategoryId,count,Status.published,filters);
		}
		setLocalVariable(VARIABLE_NAME, courses, env, body);
	}

}
