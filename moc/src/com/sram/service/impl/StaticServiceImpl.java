/*
 * .

 * Support: http://www.moc.cc
 * 
 */
package com.sram.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.sram.Filter;
import com.sram.Filter.Operator;
import com.sram.Order;
import com.sram.Order.Direction;
import com.sram.Template;
import com.sram.dao.ArticleDao;
import com.sram.dao.CourseDao;
import com.sram.dao.TeacherDao;
import com.sram.entity.Area;
import com.sram.entity.Article;
import com.sram.entity.ArticleCategory;
import com.sram.entity.Course;
import com.sram.entity.Course.Status;
import com.sram.entity.CourseChapter;
import com.sram.entity.CourseLesson;
import com.sram.entity.CourseMaterial;
import com.sram.entity.IndustryCategory;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Teacher;
import com.sram.service.AreaService;
import com.sram.service.ArticleCategoryService;
import com.sram.service.ArticleService;
import com.sram.service.CourseChapterService;
import com.sram.service.CourseLessonService;
import com.sram.service.CourseMaterialService;
import com.sram.service.CourseService;
import com.sram.service.IndustryCategoryService;
import com.sram.service.OutlineCategoryService;
import com.sram.service.StaticService;
import com.sram.service.TeacherService;
import com.sram.service.TemplateService;

/**
 * Service - 静态化
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("staticServiceImpl")
public class StaticServiceImpl implements StaticService, ServletContextAware {

	/** Site map最大地址数 */
	@SuppressWarnings("unused")
	private static final Integer SITEMAP_MAX_SIZE = 40000;

	/** servletContext */
	private ServletContext servletContext;

	@Resource(name = "freeMarkerConfigurer")
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@Resource(name = "templateServiceImpl")
	private TemplateService templateService;
	@Resource(name = "articleDaoImpl")
	private ArticleDao articleDao;
	@Resource(name="courseDaoImpl")
	private CourseDao courseDao;
	@Resource(name="teacherDaoImpl")
	private TeacherDao teacherDao;
	@Resource(name = "courseChapterServiceImpl")
	private CourseChapterService courseChapterService;
	@Resource(name="teacherServiceImpl")
	private TeacherService teacherService;
	@Resource(name="courseServiceImpl")
	private CourseService courseService;
	@Resource(name="courseMaterialServiceImpl")
	private CourseMaterialService courseMaterialService;
	@Autowired
	private IndustryCategoryService industryCategoryService;
	@Autowired
	private OutlineCategoryService outlineCategoryService;
	@Resource(name="courseLessonServiceImpl")
	private CourseLessonService courseLessonService;
	@Resource(name="articleServiceImpl")
	private ArticleService articleService;
	@Resource(name="articleCategoryServiceImpl")
	private ArticleCategoryService articleCategoryService;
	@Resource(name = "areaServiceImpl")
	private AreaService areaService;
	
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Transactional(readOnly = true)
	public int build(String templatePath, String staticPath, Map<String, Object> model) {
		Assert.hasText(templatePath);
		Assert.hasText(staticPath);

		FileOutputStream fileOutputStream = null;
		OutputStreamWriter outputStreamWriter = null;
		Writer writer = null;
		try {
			
			//获取模板、生成静态文件、判断父目录是否存在（不存在创建）
			freemarker.template.Template template = freeMarkerConfigurer.getConfiguration().getTemplate(templatePath);
			File staticFile = new File(servletContext.getRealPath(staticPath));
			File staticDirectory = staticFile.getParentFile();
			if (!staticDirectory.exists()) {
				staticDirectory.mkdirs();
			}
			
			//文件写入流+数据model----生成完整的页面
			fileOutputStream = new FileOutputStream(staticFile);
			outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");
			writer = new BufferedWriter(outputStreamWriter);
			template.process(model, writer);
			writer.flush();
			
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(writer);
			IOUtils.closeQuietly(outputStreamWriter);
			IOUtils.closeQuietly(fileOutputStream);
		}
		return 0;
	}

	@Transactional(readOnly = true)
	public int build(String templatePath, String staticPath) {
		Map<String, Object> model=new HashMap<String,Object>();
		model.put("captchaId",UUID.randomUUID().toString());
		return build(templatePath, staticPath, model);
	}

	@Transactional(readOnly = true)
	public int build(Article article,Article preArticle,Article nextArticle) {
		Assert.notNull(article);

		delete(article);
		
		int buildCount = 0;
		if("底部导航".equals(article.getArticleCategory().getName())){
			Template templateConsult = templateService.get("articleConsult");
			if(article.getIsPublication()){
				Map<String, Object> model = new HashMap<String, Object>();
				model.put("article", article);
				for (int pageNumber = 1; pageNumber <= article.getTotalPages(); pageNumber++) {
					article.setPageNumber(pageNumber);
					buildCount += build(templateConsult.getTemplatePath(), article.getPath(), model);
				}
				article.setPageNumber(null);
			}
			return buildCount;
		}
		
		
		Template template = templateService.get("articleContent");
		if (article.getIsPublication()) {
			ArticleCategory allArticle=articleCategoryService.findRoots().get(0);
			preArticle=preArticle==null?articleService.findPrevArticle(article):preArticle.getArticleCategory()==article.getArticleCategory()?preArticle:null;
			nextArticle=nextArticle==null?articleService.findNextArticle(article):nextArticle.getArticleCategory()==article.getArticleCategory()?nextArticle:null;
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("article", article);
			model.put("allArticle",allArticle);
			model.put("prevArticle",preArticle);//上一篇文章
			model.put("nextArticle",nextArticle);//下一篇文章
			for (int pageNumber = 1; pageNumber <= article.getTotalPages(); pageNumber++) {
				article.setPageNumber(pageNumber);
				buildCount += build(template.getTemplatePath(), article.getPath(), model);
			}
			article.setPageNumber(null);
		}
		return buildCount;
	}

	
	@Transactional(readOnly = true)
	public int buildIndex() {
		Template template = templateService.get("index");
		return build(template.getTemplatePath(), template.getStaticPath());
	}

	@Transactional(readOnly = true)
	public int buildSitemap() {
		int buildCount = 0;
		
		return buildCount;
	}

	@Transactional(readOnly = true)
	public int buildOther() {
		int buildCount = 0;
		Template mocCommonJsTemplate = templateService.get("mocCommonJs");
		Template adminCommonJsTemplate = templateService.get("adminCommonJs");
		buildCount += build(mocCommonJsTemplate.getTemplatePath(), mocCommonJsTemplate.getStaticPath());
		buildCount += build(adminCommonJsTemplate.getTemplatePath(), adminCommonJsTemplate.getStaticPath());
		return buildCount;
	}

	@Transactional(readOnly = true)
	public int buildAll() {
		int buildCount = 0;
		for (int i = 0; i < articleDao.count(); i += 20) {
			Order category=new Order();
			category.setDirection(Direction.asc);
			category.setProperty("articleCategory");
			
			//上一篇是最新的--时间最大的----页面上第一条是最新的
			Order createDateOrder=new Order();
			createDateOrder.setDirection(Direction.desc);
			createDateOrder.setProperty("createDate");
			
			List<Order> orders=new ArrayList<Order>();
			orders.add(category);
			orders.add(createDateOrder);
			
			//list ,同一分类下按时间降序排
			List<Article> articles = articleDao.findList(i, 20, null, orders);
			for (int j=0;j<articles.size();j++) {
				
				//current --上一篇---下一篇
				buildCount += build(articles.get(j),j==0?null:articles.get(j-1),j==articles.size()-1?null:articles.get(j+1));
			}
			articleDao.clear();
		}
		List<Course> courses;
		for(int i=0;i<courseDao.count();i +=20){
			courses=courseDao.findList(i, 20, null, null);
			for(Course course:courses){
				buildCount +=build(course);
			}
			courseDao.clear();
		}
		List<Teacher> teachers;
		for(int i=0;i<teacherDao.count();i +=20){
			teachers=teacherDao.findList(i, 20, null, null);
			for(Teacher course:teachers){
				buildCount +=build(course);
			}
			courseDao.clear();
		}
		
		buildCount +=buildIndustry();
		buildCount +=buildIndex();
		buildCount +=buildSitemap();
		buildCount +=buildOther();
		return buildCount;
	}

	@Transactional(readOnly = true)
	public int delete(String staticPath) {
		Assert.hasText(staticPath);

		File staticFile = new File(servletContext.getRealPath(staticPath));
		if (staticFile.exists()) {
			staticFile.delete();
			return 1;
		}
		return 0;
	}

	@Transactional(readOnly = true)
	public int delete(Article article) {
		Assert.notNull(article);

		int deleteCount = 0;
		for (int pageNumber = 1; pageNumber <= article.getTotalPages() + 1000; pageNumber++) {
			article.setPageNumber(pageNumber);
			int count = delete(article.getPath());
			if (count < 1) {
				break;
			}
			deleteCount += count;
		}
		article.setPageNumber(null);
		return deleteCount;
	}



	@Transactional(readOnly = true)
	public int deleteIndex() {
		Template template = templateService.get("index");
		return delete(template.getStaticPath());
	}

	@Transactional(readOnly = true)
	public int deleteOther() {
		int deleteCount = 0;
		Template shopCommonJsTemplate = templateService.get("shopCommonJs");
		Template adminCommonJsTemplate = templateService.get("adminCommonJs");
		deleteCount += delete(shopCommonJsTemplate.getStaticPath());
		deleteCount += delete(adminCommonJsTemplate.getStaticPath());
		return deleteCount;
	}

	@SuppressWarnings("null")
	@Transactional(readOnly = true)
	public int build(Course course) {
		// TODO Auto-generated method stub
		Assert.notNull(course);

		delete(course);
		
		//获取模板--先生成第二个、再生成第一个
		Template template = templateService.get("courseContent");
		Template template2 = templateService.get("courseContent2");
		int buildCount = 0;
		if (course.getStatus()==com.sram.entity.Course.Status.published) {
			
			//获取对应课程排好序的章节
			List<CourseChapter> courseChapters = courseChapterService
			.findChaptersByCourse(course.getId());
			
			/**
			 * 没有章节和课时的课程不生成静态页并将其状态变为close
			 */
			boolean noLesson=true;
			List<CourseLesson> courseLessons;
			if(courseChapters==null){
				course.setStatus(Status.closed);
				courseService.changeStatus(course);
				return 0;
			}
			for(int i=0;i<courseChapters.size();i++){
				courseLessons=courseChapters.get(i).getCourseLessons();
				if(courseLessons!=null || courseLessons.size()>0){
					noLesson=false;
					break;
				}
			}
			if(noLesson){
				course.setStatus(Status.closed);
				courseService.changeStatus(course);
				return 0;
			}
			
			List<CourseMaterial> page=courseMaterialService.findList(course.getId());
			
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("courseMaterial", page);
			model.put("course", course);
			
			//该课程的评论数及评分
			Object[] a=courseService.getCourseReviewPoint(course.getId());
			model.put("reviewPoint", a);
			model.put("courseChapters", courseChapters);
			model.put("teachers", teacherService.findListByCourse(course));
			
			Filter filterCourseStatus=new Filter();
			filterCourseStatus.setProperty("status");
			filterCourseStatus.setValue(Status.published);
			filterCourseStatus.setOperator(Operator.eq);
			List<Filter> filters=new ArrayList<Filter>();
			filters.add(filterCourseStatus);
			model.put("relevantCourse", courseService.findList(course.getCourseCategory().getId(), null,  null, null,null,null,null, 3, filters, null));
			model.put("relevantMember",courseService.findLatestMember(course, 9));
			model.put("courseAnnouncements",course.getCourseAnnouncements());
			
			//要展示所有的课时，包括未发布的
			model.put("lessonLength", courseLessonService.getTotalLength(course,null));
			//模板路径/moc/course/content.ftl+访问路径（静态路径）+数据（model）-----生成html页面
			buildCount += build(template2.getTemplatePath(), course.getPath2(), model);
			buildCount += build(template.getTemplatePath(), course.getPath(), model);
		}
		return buildCount;
	}
	
	
	@Transactional(readOnly = true)
	public int build(Teacher teacher) {
		// TODO Auto-generated method stub
		Assert.notNull(teacher);

		delete(teacher);
		
		Template template = templateService.get("teacherContent");
		int buildCount = 0;
		if(teacher.getStatus()==com.sram.entity.Teacher.Status.published){
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("teacher", teacher);
			model.put("courses", courseService.findByTeacherId(teacher.getId(),com.sram.entity.Course.Status.published));
			
			buildCount += build(template.getTemplatePath(), teacher.getPath(), model);
		}
		return buildCount;
	}

	@Transactional(readOnly = true)
	private int delete(Teacher teacher) {
		// TODO Auto-generated method stub
		Assert.notNull(teacher);

		return delete(teacher.getPath());
	}

	@Transactional(readOnly = true)
	public int delete(Course course) {
		// TODO Auto-generated method stub
		Assert.notNull(course);

		return delete(course.getPath());
	}

	public int delete(CourseLesson courseLesson) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int buildIndustry() {
		int buildCount = 0;
		//行业
		Template industryTemplate = templateService.get("industry");
		Map<String, Object> industryModel = new HashMap<String, Object>();
		List<IndustryCategory> roots = industryCategoryService.findAllCategorys();
		//顶级行业
		industryModel.put("rootsIndustry", roots);
		buildCount += build(industryTemplate.getTemplatePath(), industryTemplate.getStaticPath(), industryModel);
		for (IndustryCategory root : roots) {
			Set<IndustryCategory> childrens = root.getChildren();
			for (IndustryCategory children : childrens) {
				buildCount +=this.buildTraining(children,roots);
			}
		}
		return buildCount;
	}
	public int buildTraining(IndustryCategory children,List<IndustryCategory> roots){
		int buildCount = 0;
		Template trainingTemplate = templateService.get("training");
		List<Area> areas = areaService.findRoots();
		List<OutlineCategory> OutlineCategorys =children.getOutlineCategories();
		Iterator<OutlineCategory> iterator = OutlineCategorys.iterator();
		int i=0;
		while (iterator.hasNext()) {
			OutlineCategory outlineCategory = iterator.next();
			//多生成一个页面方便连接
            Map<String, Object> trainingModel = new HashMap<String, Object>();
            trainingModel.put("current",children );
            trainingModel.put("rootsIndustry", roots);
            trainingModel.put("areas", areas);
            trainingModel.put("outlines", OutlineCategorys);
            trainingModel.put("outline", outlineCategoryService.find(outlineCategory.getId()));
            trainingModel.put("outlineCategorys", outlineCategoryService.findAllChildren(outlineCategory.getId()));
            trainingModel.put("selectedIndex", i);
            if (i==0) {
                buildCount += build(trainingTemplate.getTemplatePath(), children.getPath().replaceAll("/outlineCategoryId",""),trainingModel);
            }
            buildCount += build(trainingTemplate.getTemplatePath(), children.getPath().replaceAll("outlineCategoryId",  outlineCategory.getId().toString()),trainingModel);
            i++;
		}
		return buildCount;
	}

}