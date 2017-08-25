/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service.impl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.SramException;
import com.sram.dao.CourseDao;
import com.sram.dao.CourseLearnDao;
import com.sram.dao.CourseLessonDao;
import com.sram.dao.CourseLessonLearnDao;
import com.sram.entity.Course;
import com.sram.entity.CourseLearn;
import com.sram.entity.CourseLesson;
import com.sram.entity.CourseLesson.Status;
import com.sram.entity.CourseLessonLearn;
import com.sram.service.CourseLessonService;
import com.sram.service.StaticService;

/**
 * Service - 课时
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("courseLessonServiceImpl")
public class CourseLessonServiceImpl extends
		BaseServiceImpl<CourseLesson, Long> implements CourseLessonService {

	@Resource(name = "courseLessonDaoImpl")
	private CourseLessonDao courseLessonDao;
	@Resource(name = "courseDaoImpl")
	private CourseDao courseDao;
	@Resource(name = "staticServiceImpl")
	private StaticService staticService;
	@Resource(name="courseLessonLearnDaoImpl")
	private CourseLessonLearnDao courseLessonLearnDao;
	@Resource(name="courseLearnDaoImpl")
	private CourseLearnDao courseLearnDao;

	@Resource(name = "courseLessonDaoImpl")
	public void setBaseDao(CourseLessonDao courseLessonDao) {
		super.setBaseDao(courseLessonDao);
	}

	@Transactional(readOnly = true)
	public List<CourseLesson> findLessonByChapterMember(Long chapterId,
			Long memberId) {
		return courseLessonDao.findLessonByChapterMember(chapterId, memberId);
	}

	

	@Transactional(readOnly = true)
	public void changeStatus(Long courseId,Long lessonId,Status lessonStatus) {
		
		courseLessonDao.changeStatus(lessonId,lessonStatus);

		// 查找课程的状态
		com.sram.entity.Course.Status courseStatus=courseDao.findStatus(courseId);
		if (courseStatus == com.sram.entity.Course.Status.published) {
			Course course=courseDao.find(courseId);
			staticService.build(course);
		}
	}

	@Transactional
	public void save(CourseLesson courseLesson) {

		Integer order;
		if (null != courseLesson.getCourseChapter()) {
			order = courseLessonDao.findMaxOrder(courseLesson.getCourse()
					.getId(), courseLesson.getCourseChapter().getId());
		} else {
			order = courseLessonDao.findMaxOrder(courseLesson.getCourse()
					.getId(), null);
		}
		courseLesson.setLearnedNum(0);
		courseLesson.setOrder(order + 1);
		Course course=courseDao.find(courseLesson.getCourse().getId());
		if (null == course.getLessonNum()) {
			course.setLessonNum(1);
		} else {
			course.setLessonNum(course.getLessonNum() + 1);
		}
		course.setFinishStuNum((long)0);
		//添加课时，将CourseLearn所有记录中course id 为该课时所属课程id的记录学习状态变更为learning状态
		courseLearnDao.updateStatusByCourseId(course.getId(),com.sram.entity.CourseLearn.Status.learning);
		courseDao.updateStuNumLessonNum(course.getId(),course.getLessonNum());
		
		super.save(courseLesson);
		
		//是否重新发布课程
		if(courseLesson.getStatus()==Status.published){
			com.sram.entity.Course.Status courseStatus = course.getStatus();
			if(courseStatus==com.sram.entity.Course.Status.published){
				staticService.build(course);
			}
		}
	}
	@Transactional
	public CourseLesson update(CourseLesson courseLesson){
		courseLesson=super.update(courseLesson);
		//是否重新发布课程
		if(courseLesson.getStatus()==Status.published){
			com.sram.entity.Course.Status courseStatus = courseDao.findStatus(courseLesson.getCourse().getId());
			if(courseStatus==com.sram.entity.Course.Status.published){
				staticService.build(courseDao.find(courseLesson.getCourse().getId()));
			}
		}
		return courseLesson;
	}
	
	
	@Transactional
	public void delete(Long id) {
		CourseLesson courseLesson = courseLessonDao.find(id);
		Course course=courseDao.find(courseLesson.getCourse().getId());
		course.setLessonNum(course.getLessonNum() - 1);
		courseDao.updateLessonNum(course);
		//删除课时，如果用户完成了该课时的学习，则将courseLearn用户完成课时数finishLessonNum减1
		//查找所有完成该课时学习的用户id
		List<CourseLessonLearn> list=courseLessonLearnDao.findFinishLessonUser(id);
		for(CourseLessonLearn lessonLearn:list){
			Long userId=lessonLearn.getUserId();
			courseLearnDao.updateFinLessonNum(course.getId(), userId);
		}
		courseLessonLearnDao.deleteByLessonId(id);
		super.delete(id);
		//课程课时数
		Integer lessonNum=course.getLessonNum();
		//根据courseId找到完成所有课时的用户,如果没有lessonNum为0不执行下面逻辑
		if(lessonNum!=0){
			List<Object[]> userIdList=courseLessonLearnDao.findFinUserByCourseId(course.getId(),course.getLessonNum());
			//将完成所有课时的用户的课程学习状态改为finished
			for(Object[] obj:userIdList){
				courseLearnDao.updateCourseLearn(((BigInteger)obj[0]).longValue(), course.getId(), com.sram.entity.CourseLearn.Status.finished);
			}
			courseDao.updateFinishStuNum((long)userIdList.size(),course.getId());
			course.setFinishStuNum((long)userIdList.size());
		}
		
		//是否重新发布课程
		com.sram.entity.Course.Status courseStatus = courseDao.findStatus(course.getId());
		if(courseStatus==com.sram.entity.Course.Status.published){
			staticService.build(course);
		}
	}
	
	public Long findFirstPublishedMediaId(Long courseId) {
		// TODO Auto-generated method stub
		return courseLessonDao.findFirstPublishedMediaId(courseId);
	}

	public Page<Object[]> findStaPage(Pageable pageable, Long courseId) {
		// TODO Auto-generated method stub
		return courseLessonDao.findStaPage(pageable, courseId);
	}

	@Transactional(readOnly = true)
	public Long getTotalLength(Course course,Status courseLessonStatus) {
		// TODO Auto-generated method stub
		return courseLessonDao.getTotalLength(course,courseLessonStatus);
	}

	@Transactional(readOnly = true)
	public List<CourseLesson> findLessonByCourse(Long courseId) {
		// TODO Auto-generated method stub
		return courseLessonDao.findLessonByCourse(courseId);
	}

	@Transactional(readOnly = true)
	public String getVideoUri(Long lessonId) {
		// TODO Auto-generated method stub
		return courseLessonDao.getVideoUri(lessonId);
	}

	public void updateLearnedNum(CourseLesson courseLesson) {
		// TODO Auto-generated method stub
		courseLessonDao.updateLearnedNum(courseLesson);
	}

	@Transactional(readOnly = true)
	public List<CourseLesson> findPublishLesson(Long courseId, Long chapterId,
			Status status) {
		return courseLessonDao.findPublishLesson(courseId, chapterId, status);
	}

	@Transactional(readOnly = true)
	public CourseLesson findCurrentLesson(Long courseId, Long lessonId) {

		return lessonId != null ? find(lessonId) : courseLessonDao
				.findFirstLesson(courseId);
	}

	

	public Page<CourseLesson> findPage(Pageable pageable, Date beginDate,
			Date endDate) {
		// TODO Auto-generated method stub
		return courseLessonDao.findPage(pageable,beginDate,endDate);
	}

	public List<Object[]> findAnalySisList(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		return courseLessonDao.findAnalySisList(beginDate,endDate);
	}

	public void updateRelevanceById(Long lessonId, Long outlineCategoryId) {
		// TODO Auto-generated method stub
		courseLessonDao.updateRelevanceById(lessonId,outlineCategoryId);
	}
}