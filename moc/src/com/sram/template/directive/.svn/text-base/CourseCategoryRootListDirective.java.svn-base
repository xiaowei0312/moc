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

import com.sram.entity.CourseCategory;
import com.sram.service.CourseCategoryService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模板指令 - 顶级课程分类列表
 * 
 * @author Sram Team
 * @version 1.0
 */
@Component("courseCategoryRootListDirective")
public class CourseCategoryRootListDirective extends BaseDirective {

	/** 变量名称 */
	private static final String VARIABLE_NAME = "courseCategories";

	@Resource(name = "courseCategoryServiceImpl")
	private CourseCategoryService courseCategoryService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<CourseCategory> courseCategories;
		boolean useCache = useCache(env, params);
		String cacheRegion = getCacheRegion(env, params);
		Integer count = getCount(params);
		if (useCache) {
			courseCategories = courseCategoryService.findRoots(count,
					cacheRegion);
		} else {
			courseCategories = courseCategoryService.findRoots(count);
		}
		setLocalVariable(VARIABLE_NAME, courseCategories, env, body);
	}

}