/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao;

import java.util.Date;
import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.Course;
import com.sram.entity.CourseLesson;
import com.sram.entity.CourseLesson.Status;

/**
 * Dao - 课时
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface CourseLessonDao extends BaseDao<CourseLesson, Long> {

	public Integer findMaxOrder(Long courseId, Long courseChapterId);

	/**
	 * 根据课程ID查找第一个课时对应的媒体ID
	 * 
	 * @param courseId
	 * @return
	 */
	public Long findFirstPublishedMediaId(Long courseId);

	public boolean changeStatus(Long lessonId, Status lessonStatus);

	public Page<Object[]> findStaPage(Pageable pageable, Long courseId);

	/**
	 * 找到对应课程下的总时长
	 * 
	 * @param course
	 * @return
	 */
	public Long getTotalLength(Course course,Status courseLessonStatus);

	/**
	 * 根据课程找排好序的课时
	 * 
	 * @param courseId
	 * @return
	 */
	public List<CourseLesson> findLessonByCourse(Long courseId);

	public String getVideoUri(Long lessonId);

	public void updateLearnedNum(CourseLesson courseLesson);

	/**
	 * 根据chapterId,memberId找课时
	 * 
	 * @param chapterId
	 * @return
	 */
	public List<CourseLesson> findLessonByChapterMember(Long chapterId,
			Long memberId);

	/**
	 * 根据条件查找状态为status的课时
	 * 
	 * @param courseId
	 * @param chapterId
	 * @param status
	 * @return
	 */
	public List<CourseLesson> findPublishLesson(Long courseId, Long chapterId,
			Status status);

	/**
	 * 查找该课程下的第一个课时(已发布)
	 * 
	 * @param courseId
	 * @return
	 */
	public CourseLesson findFirstLesson(Long courseId);

	public Page<CourseLesson> findPage(Pageable pageable, Date beginDate,
			Date endDate);

	public List<Object[]> findAnalySisList(Date beginDate, Date endDate);

	public void updateRelevanceById(Long lessonId, Long outlineCategoryId);

}