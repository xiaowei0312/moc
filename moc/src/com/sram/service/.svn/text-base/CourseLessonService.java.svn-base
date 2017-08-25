/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service;

import java.util.Date;
import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.Course;
import com.sram.entity.CourseLesson;
import com.sram.entity.CourseLesson.Status;

/**
 * Service - 课时
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface CourseLessonService extends BaseService<CourseLesson, Long> {

	/**
	 * 获取对应课程下的课时
	 * 
	 * @param course
	 * @return
	 */
	public Long getTotalLength(Course course,Status courseLessonStatus);

	/**
	 * 根据课程ID查找第一个课时对应的媒体ID
	 * 
	 * @param courseId
	 * @return
	 */
	public Long findFirstPublishedMediaId(Long courseId);

	/**
	 * lessonId---null---查该课程下的第一个课时
	 * 
	 * @param courseId
	 * @return
	 */
	public CourseLesson findCurrentLesson(Long courseId, Long lessonId);

	/**
	 * 改变课时状态 只在课程为发布状态时生成静态页面
	 * 
	 * @param courseLesson
	 */
	public void changeStatus(Long courseId,Long lessonId,Status lessonStatus);

	/**
	 * 根据chapterId,memberId查找课时
	 * 
	 * @param id
	 * @return
	 */
	public List<CourseLesson> findLessonByChapterMember(Long chapterId,
			Long memerId);

	public String getVideoUri(Long lessonId);

	public Page<Object[]> findStaPage(Pageable pageable, Long courseId);

	public void updateLearnedNum(CourseLesson courseLesson);

	/**
	 * 根据条件查找状态为status的课时
	 * 
	 * @param courseId
	 * @param chapterId
	 * @return
	 */
	public List<CourseLesson> findPublishLesson(Long courseId, Long chapterId,
			com.sram.entity.CourseLesson.Status status);
	public CourseLesson update(CourseLesson courseLesson);

	public Page<CourseLesson> findPage(Pageable pageable, Date beginDate,
			Date endDate);
    /**
     * 根据天数统计新增课时数
     * @param beginDate
     * @param endDate
     * @return
     */
	public List<Object[]> findAnalySisList(Date beginDate, Date endDate);
	
	/**
	 * 维护课时大纲关联关系
	 * @param lessonId
	 * @param outlineCategoryId
	 */
	public void updateRelevanceById(Long lessonId, Long outlineCategoryId);
}