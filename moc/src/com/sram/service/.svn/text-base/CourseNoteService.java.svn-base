package com.sram.service;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.CourseNote;
/**
 * 笔记
 * @author 刚平
 *
 */
public interface CourseNoteService extends BaseService<CourseNote, Long>{

	/**
	 * 课程、课时、用户、笔记的查找
	 * count--显示笔记的个数
	 * @param memberId
	 * @param i0--private  1---public
	 * @param courseId
	 * @return
	 */
	List<CourseNote> findNotes(Long memberId, Integer i, Long courseId,Long lessonId,Integer count);

	/**
	 * 可以搜索关联属性的名称
	 * @param courseId 
	 */
	Page<CourseNote> findPage(Long courseId, Pageable pageable);

	/**
	 * 由lessonId删除笔记
	 * @param id
	 */
	void deleteByLessonId(Long lessonId);
	Page<CourseNote> findPageByUserId(Long memberId, Pageable pageable);

}
