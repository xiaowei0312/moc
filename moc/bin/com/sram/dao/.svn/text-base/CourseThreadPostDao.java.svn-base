package com.sram.dao;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.CourseThreadPost;

public interface CourseThreadPostDao extends BaseDao<CourseThreadPost,Long>{

	/**
	 * 根据条件查找回复
	 * @param threadId
	 * @param memberId
	 * @param courseId
	 * @param lessonId
	 * @param count
	 * @return
	 */
	List<CourseThreadPost> findPost(Long threadId, Long memberId,
			Long courseId, Long lessonId, Integer count);

	/**
	 * 根据实体对象查找对象分
	 * @param courseId
	 * @param lessonId
	 * @param threadId
	 * @param isElite
	 * @param memberId
	 * @param count
	 * @param pageable
	 * @return
	 */
	Page<CourseThreadPost> findPage(Long courseId, Long lessonId,
			Long threadId, Boolean isElite, Long memberId,
			Pageable pageable);

}
