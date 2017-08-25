/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service;

import java.util.Map;

import com.sram.entity.Article;
import com.sram.entity.Course;
import com.sram.entity.CourseLesson;
import com.sram.entity.Teacher;

/**
 * Service - 静态化
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface StaticService {

	/**
	 * 生成静态
	 * 
	 * @param templatePath
	 *            模板文件路径
	 * @param staticPath
	 *            静态文件路径
	 * @param model
	 *            数据
	 * @return 生成数量
	 */
	int build(String templatePath, String staticPath, Map<String, Object> model);

	/**
	 * 生成静态
	 * 
	 * @param templatePath
	 *            模板文件路径
	 * @param staticPath
	 *            静态文件路径
	 * @return 生成数量
	 */
	int build(String templatePath, String staticPath);

	/**
	 * 生成静态
	 * 
	 * @param teacher
	 *            文章
	 * @return 生成数量
	 */
	
	int build(Teacher teacher);



	/**
	 * 生成静态
	 * 
	 * @param product
	 *            课程
	 * @return 生成数量
	 */
	int build(Course course);

	/**
	 * 生成首页静态
	 * 
	 * @return 生成数量
	 */
	int buildIndex();

	/**
	 * 生成Sitemap
	 * 
	 * @return 生成数量
	 */
	int buildSitemap();

	/**
	 * 生成其它静态
	 * 
	 * @return 生成数量
	 */
	int buildOther();

	/**
	 * 生成所有静态
	 * 
	 * @return 生成数量
	 */
	int buildAll();

	/**
	 * 删除静态
	 * 
	 * @param staticPath
	 *            静态文件路径
	 * @return 删除数量
	 */
	int delete(String staticPath);

	/**
	 * 删除静态
	 * 
	 * @param article
	 *            文章
	 * @return 删除数量
	 */
	int delete(Article article);



	/**
	 * 删除首页静态
	 * 
	 * @return 删除数量
	 */
	int deleteIndex();

	/**
	 * 删除其它静态
	 * 
	 * @return 删除数量
	 */
	int deleteOther();

	int delete(Course course);

	int delete(CourseLesson courseLesson);

	int buildIndustry();

	/**
	 * 当前文章、上一篇（时间较大）、下一篇（时间较小）
	 * @param article
	 * @param preArticle
	 * @param nextArticle
	 * @return
	 */
	int build(Article article,Article preArticle,Article nextArticle);

}