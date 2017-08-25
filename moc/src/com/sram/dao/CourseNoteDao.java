package com.sram.dao;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.CourseNote;

public interface CourseNoteDao extends BaseDao<CourseNote, Long>{

	/**
	 * 根据会员、笔记状态、课程、笔记数目来查找笔记
	 * @param memberId
	 * @param status
	 * @param courseId
	 * @param count
	 * @return
	 */
	List<CourseNote> findNotes(Long memberId, Integer status, Long courseId,
			Long lessonId,Integer count);
	
	/**
	 * 查找对象实体分页--可以查找关联对象的属性
	 * @param courseId 
	 */
	Page<CourseNote> findPage(Long courseId, Pageable pageable);

	Page<CourseNote> findPageByUserId(Long memberId, Pageable pageable);

	void deleteByLessonId(Long lessonId);
}
