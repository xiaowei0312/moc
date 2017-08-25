package com.sram.service;

import java.util.List;

import com.sram.entity.CourseChapter;

public interface CourseChapterService extends BaseService<CourseChapter, Long> {


	/**
	 * 根据课程id找章节
	 * 
	 * @param courseId
	 * @return
	 */
	List<CourseChapter> findRoots(Long courseId);

	

	/**
	 * 找root的小章节
	 * 
	 * @param courseChapter
	 * @return
	 */
	List<CourseChapter> findUnit(CourseChapter courseChapter);

	/**
	 * 根据courseId找chapters
	 */
	List<CourseChapter> findChaptersByCourse(Long courseId);
	
	void delete(Long id,String ids);
	
	public void save(CourseChapter courseChapter, Long parentId) ;



	void sort(String ids);

}
