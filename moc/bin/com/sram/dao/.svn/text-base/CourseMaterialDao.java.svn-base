/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.CourseLesson;
import com.sram.entity.CourseMaterial;

/**
 * Dao - 课程资料
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface CourseMaterialDao extends BaseDao<CourseMaterial, Long> {

	public List<CourseMaterial> findListByLessonId(Long lessonId, Long courseId);

	/**
	 * 查找出courseId下所有的资料--课时下的，课程下的---
	 * @param courseId
	 * @param targetType---courselesson（视频），coursematerial（资料）
	 * @param pageable
	 * @return
	 */
	public List<CourseMaterial>  findList(Long courseId);
}