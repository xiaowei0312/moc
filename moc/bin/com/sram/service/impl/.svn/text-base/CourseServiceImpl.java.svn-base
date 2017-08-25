/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.sram.Filter;
import com.sram.Order;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.CourseDao;
import com.sram.entity.Course;
import com.sram.entity.Course.DeadlineNotify;
import com.sram.entity.Course.SerializeMode;
import com.sram.entity.Course.ShowStudentNumType;
import com.sram.entity.Course.Status;
import com.sram.entity.Course.UserType;
import com.sram.entity.CourseCategory;
import com.sram.entity.Member;
import com.sram.entity.Tag;
import com.sram.entity.Teacher;
import com.sram.service.CourseService;
import com.sram.service.StaticService;

/**
 * Service - 课程
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("courseServiceImpl")
public class CourseServiceImpl extends BaseServiceImpl<Course, Long> implements CourseService {

	@Resource(name = "courseDaoImpl")
	private CourseDao courseDao;
	@Resource(name = "staticServiceImpl")
	private StaticService staticService;

	@Resource(name = "courseDaoImpl")
	public void setBaseDao(CourseDao courseDao) {
		super.setBaseDao(courseDao);
	}

	@Transactional(readOnly = true)
	public Page<Course> findPage(CourseCategory courseCategory,Status status, Integer maxStudentNum, Integer recommended, Integer vipLevelId, Float price, UserType userType, SerializeMode serializeMode, ShowStudentNumType showStudentNumType, DeadlineNotify deadlineNotify, Pageable pageable){
		return courseDao.findPage(courseCategory,status,maxStudentNum,recommended,vipLevelId,price,userType,serializeMode,showStudentNumType,deadlineNotify,pageable);
	}
	@Transactional(readOnly = true)
	public List<Member> findLatestMember(Course course, Integer count) {
		return courseDao.findLatestMember(course,count);
	}
	@Transactional(readOnly = true)
	public Object[] getCourseReviewPoint(Long courseId) {
		// TODO Auto-generated method stub
		return courseDao.getCourseReviewPoint(courseId);
	}
	@Transactional(readOnly=true)
	public Page<Object[]> findRecommendCourse(Pageable pageable) {
		return courseDao.findRecommendCourse(pageable);
	}
	@Transactional(readOnly=true)
	public void changeRecommend(Course course) {
		courseDao.changeRecommend(course);
		
		if(course.getStatus()==Status.published){
			staticService.buildIndex();
		}
		//是推荐课程
		if(course.getIsRecommend()){
			
			//获取推荐序号
			Integer seq=courseDao.getMaxRecommendedSeq();
			if(seq==null){
				seq=0;
			}
			course.setRecommendedSeq(seq+1);
			courseDao.changeRecommendedSeq(course);
		}
	}
	@Transactional(readOnly = true)
	public void changeStatus(Course course) {
		courseDao.changeStatus(course);
		if(course.getStatus()==Status.published){
			
			//生成两个静态页面
			staticService.build(courseDao.find(course.getId()));
		}
	}
	@Transactional(readOnly = true)
	public void changeRecommendedSeq(Course course) {
		courseDao.changeRecommendedSeq(course);
	}
	@Override
	@Transactional
	public void save(Course course) {
		Assert.notNull(course);
		//生成课时数
		course.setLessonNum(0);
		course.setStudentNum(0);
		course.setFinishStuNum((long) 0);
		super.save(course);
		courseDao.flush();
	}
	
	@Override
	public Course update(Course course, String... ignoreProperties) {
		Assert.notNull(course);
		Course courseEntity=super.update(course,"createDate");
		if(courseEntity.getStatus()==Status.published){
			//staticService.build(courseEntity);
			//staticService.build(courseEntity);
			//这个方法执行完毕后，，数据库的值才会改变staticService.buildIndex();
		}
		return courseEntity;
	}

	@Override
	@Transactional
	public void delete(Course course) {
		if (course != null) {
			staticService.delete(course);
		}
		super.delete(course);
	}
	
	@Transactional(readOnly=true)
	public List<Course> findByTeacher(Teacher teacher) {
		return courseDao.findByTeacher(teacher);
	}
	@Transactional(readOnly=true)
	public List<Course> findList(Long courseCategoryId, List<Tag> tags,
			BigDecimal startPrice, BigDecimal endPrice,Date beginDate, Date endDate,Integer first,
			Integer count, List<Filter> filters, List<Order> orders) {
		return courseDao.findList(courseCategoryId, tags, startPrice, endPrice,beginDate,endDate,first,count,filters,orders);
	}


	public Page<Object[]> findStaPage(Pageable pageable,Long courseCategoryId) {
		// TODO Auto-generated method stub
		return courseDao.findStaPage(pageable,courseCategoryId);
	}

	public List<Object[]> findByMemberId(Long memberId,String tabFlag) {
		// TODO Auto-generated method stub
		return courseDao.findByMemberId(memberId,tabFlag);
	}

	public List<Object[]> findfavByMemberId(Long memberId,String courseIds) {
		// TODO Auto-generated method stub
		return courseDao.findfavByMemberId(memberId,courseIds);
	}

	public void updateFinishStuNum(Long finishStuNum, Long courseId) {
		// TODO Auto-generated method stub
		courseDao.updateFinishStuNum(finishStuNum,courseId);
	}

	public Page<Object[]> findPublishedPage(Status published, Pageable pageable,String courseName,Long categoryId) {
		// TODO Auto-generated method stub
		return courseDao.findPublishedPage(published,pageable,courseName,categoryId);
	}

	public List<Object[]> findByTeacherId(Long teacherId,Status courseStatus) {
		// TODO Auto-generated method stub
		return courseDao.findByTeacherId(teacherId,courseStatus);
	}

	public Page<Object[]> findMemsByCourId(Pageable pageable,Long courseId) {
		// TODO Auto-generated method stub
		return courseDao.findMemsByCourId(pageable,courseId);
	}
	@Transactional(readOnly=true)
	public List<Course> findListRecommendCourse(Long courseCategoryId,
			Integer count,Status status, List<Filter> filters) {
		return courseDao.findListRecommendCourse(courseCategoryId,count,status,filters);
	}

	public List<Course> findListRecommendCourse(Long courseCategoryId,
			Integer count, List<Filter> filters, String cacheRegion) {
		return courseDao.findListRecommendCourse(courseCategoryId,count,Status.published,filters);
	}

	public String findCourseTitle(Long courseId) {
		// TODO Auto-generated method stub
		return courseDao.findCourseTitle(courseId);
	}
	@Transactional(readOnly=true)
	public void updateRatingNum(Long courseId) {
		courseDao.updateRatingNum(courseId);
	}

	public Page<Course> findPage(Pageable pageable, Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		return courseDao.findPage(pageable,beginDate,endDate);
	}

	public List<Object[]> findAnalySisList(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		return courseDao.findAnalySisList(beginDate,endDate);
	}

	public Course findByChapterId(Long course_chapter_id) {
		return courseDao.findByChapter(course_chapter_id);
	}

	
}