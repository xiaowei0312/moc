/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.template.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sram.entity.CourseCategory;
import com.sram.service.CourseCategoryService;
import com.sram.util.FreemarkerUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 模板指令 - 上级课程分类列表
 * 
 * @author Sram Team
 * @version 1.0
 */
@Component("courseCategoryParentListDirective")
public class CourseCategoryParentListDirective extends BaseDirective {

	/** "商品分类ID"参数名称 */
	private static final String COURSE_CATEGORY_ID_PARAMETER_NAME = "courseCategoryId";

	/** 变量名称 */
	private static final String VARIABLE_NAME = "courseCategories";

	@Resource(name = "courseCategoryServiceImpl")
	private CourseCategoryService courseCategoryService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		Long courseCategoryId = FreemarkerUtils.getParameter(COURSE_CATEGORY_ID_PARAMETER_NAME, Long.class, params);

		CourseCategory courseCategory = courseCategoryService.find(courseCategoryId);

		List<CourseCategory> courseCategories;
		if (courseCategoryId != null && courseCategory == null) {
			courseCategories = new ArrayList<CourseCategory>();
		} else {
			boolean useCache = useCache(env, params);
			String cacheRegion = getCacheRegion(env, params);
			Integer count = getCount(params);
			if (useCache) {
				courseCategories = courseCategoryService.findParents(courseCategory, count, cacheRegion);
			} else {
				courseCategories = courseCategoryService.findParents(courseCategory, count);
			}
		}
		setLocalVariable(VARIABLE_NAME, courseCategories, env, body);
	}

}