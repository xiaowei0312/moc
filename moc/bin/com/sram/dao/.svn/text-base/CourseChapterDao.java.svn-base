package com.sram.dao;

import java.util.List;

import com.sram.entity.CourseChapter;
import com.sram.entity.CourseLesson.Status;

public interface CourseChapterDao extends BaseDao<CourseChapter, Long> {

	/**
	 * 获取章的最大号
	 * 
	 * @param courseId
	 * @return
	 */
	public Integer getNextChapterNumber(Long courseId);

	/**
	 * 获取课程的章的最大序号
	 * 
	 * @param courseId
	 * @return
	 */

	public Integer getNextCourseItemOrder(Long courseId);

	List<CourseChapter> findRoots(Long courseId);

	/**
	 * 根据章节类型 （rootid,,,null）来找对应的最大序号
	 * 
	 * @param courseChapter
	 * @return
	 */
	public Integer findMaxOrder(CourseChapter courseChapter,Long parentId);

	/**
	 * 找某一章节的小节
	 * 
	 * @param courseChapter
	 * @return
	 */

	List<CourseChapter> findUnit(CourseChapter courseChapter);

	/**
	 * 根据最大的排序号进入章节order的排序,返回要更新的chapters
	 * 
	 * @param maxOrder
	 * @return
	 */

	List<CourseChapter> sortChapterOrder(Integer maxOrder);

	/**
	 * 根据courseId找chapters
	 * 
	 * @param courseId
	 * @return
	 */

	List<CourseChapter> findChaptersByCourse(Long courseId);
}
