/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.controller.admin;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sram.entity.Article;
import com.sram.entity.ArticleCategory;
import com.sram.entity.Course;
import com.sram.entity.Teacher;
import com.sram.service.ArticleCategoryService;
import com.sram.service.ArticleService;
import com.sram.service.CourseCategoryService;
import com.sram.service.CourseService;
import com.sram.service.StaticService;
import com.sram.service.TeacherService;

/**
 * Controller - 静态化
 * 
 * @author Sram Team
 * @version 1.0
 */
@Controller("adminStaticController")
@RequestMapping("/admin/static")
public class StaticController extends BaseController {

	/**
	 * 生成类型
	 */
	public enum BuildType {
		/**
		 * 首页
		 */
		index,
		/**
		 * 行业
		 */
		industry,
		/**
		 * 文章
		 */
		article,
		/**
		 * 课程
		 */
		course,
		/**
		 * 教师
		 */
		teacher,
		/**
		 * 其它
		 */
		other
	}

	@Resource(name = "articleServiceImpl")
	private ArticleService articleService;
	@Resource(name = "articleCategoryServiceImpl")
	private ArticleCategoryService articleCategoryService;
	@Resource(name="courseCategoryServiceImpl")
	private CourseCategoryService courseCategoryService;
	@Resource(name="courseServiceImpl")
	private CourseService courseService;
	@Resource(name = "staticServiceImpl")
	private StaticService staticService;
	@Resource(name="teacherServiceImpl")
	private TeacherService teacherService;

	/**
	 * 生成静态
	 */
	@RequestMapping(value = "/build", method = RequestMethod.GET)
	public String build(ModelMap model) {
		model.addAttribute("buildTypes", BuildType.values());
		model.addAttribute("defaultBeginDate",
				DateUtils.addDays(new Date(), -7));
		model.addAttribute("defaultEndDate", new Date());
		model.addAttribute("articleCategoryTree",
				articleCategoryService.findChildren(null, null));
		model.addAttribute("courseCategoryTree", courseCategoryService.findTree());
		return "/admin/static/build";
	}

	/**
	 * 生成静态
	 */
	@RequestMapping(value = "/build", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, Object> build(BuildType buildType, Long articleCategoryId,
			Long courseCategoryId, Date beginDate, Date endDate,
			Integer first, Integer count) {
		long startTime = System.currentTimeMillis();
		if (beginDate != null) {
			Calendar calendar = DateUtils.toCalendar(beginDate);
			calendar.set(Calendar.HOUR_OF_DAY,
					calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE,
					calendar.getActualMinimum(Calendar.MINUTE));
			calendar.set(Calendar.SECOND,
					calendar.getActualMinimum(Calendar.SECOND));
			beginDate = calendar.getTime();
		}
		if (endDate != null) {
			Calendar calendar = DateUtils.toCalendar(endDate);
			calendar.set(Calendar.HOUR_OF_DAY,
					calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE,
					calendar.getActualMaximum(Calendar.MINUTE));
			calendar.set(Calendar.SECOND,
					calendar.getActualMaximum(Calendar.SECOND));
			endDate = calendar.getTime();
		}
		if (first == null || first < 0) {
			first = 0;
		}
		if (count == null || count <= 0) {
			count = 50;
		}
		int buildCount = 0;
		boolean isCompleted = true;
		
		if (buildType==null){
			buildCount = staticService.buildAll();
		}else if(buildType == BuildType.index) {
			buildCount = staticService.buildIndex();
		} else if (buildType == BuildType.article) {
			ArticleCategory articleCategory = articleCategoryService
					.find(articleCategoryId);
			if(articleCategory.getGrade()==0){
				for(ArticleCategory category:articleCategory.getChildren()){
					//查找发布的文章
					first = 0;
					List<Article> articles = articleService.findList(category,
							beginDate, endDate, first, count);
					for (int i=0;i<articles.size();i++) {
						buildCount += staticService.build(articles.get(i),i==0?null:articles.get(i-1),i==articles.size()-1?null:articles.get(i+1));
					}
					first += articles.size();
					if (articles.size() == count) {
						isCompleted = false;
					}
				}
			}else{
				//查找发布的文章
				List<Article> articles = articleService.findList(articleCategory,
						beginDate, endDate, first, count);
				for (int i=0;i<articles.size();i++) {
					buildCount += staticService.build(articles.get(i),i==0?null:articles.get(i-1),i==articles.size()-1?null:articles.get(i+1));
				}
				first += articles.size();
				if (articles.size() == count) {
					isCompleted = false;
				}
			}
			
		} else if (buildType == BuildType.course) {
			List<Course> courses = courseService.findList(courseCategoryId, null, null, null,beginDate,endDate,first, count, null, null);
			for (Course course : courses) {
				buildCount += staticService.build(course);
			}
			first += courses.size();
			if (courses.size() == count) {
				isCompleted = false;
			}
		} else if (buildType == BuildType.other) {
			buildCount = staticService.buildOther();
		} else if (buildType == BuildType.industry) {
			buildCount = staticService.buildIndustry();
		} else if(buildType == BuildType.teacher){
			List<Teacher> teachers=teacherService.findAll(first,count);
			for(Teacher teacher:teachers){
				buildCount += staticService.build(teacher);
			}
			first +=teachers.size();
			if(teachers.size()==count){
				isCompleted = false;
			}
		}
		long endTime = System.currentTimeMillis();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("first", first);
		map.put("buildCount", buildCount);
		map.put("buildTime", endTime - startTime);
		map.put("isCompleted", isCompleted);
		return map;
	}

}