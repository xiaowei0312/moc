package com.sram.service;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.CourseThreadPost;

public interface CourseThreadPostService extends BaseService<CourseThreadPost,Long>{

	/**
	 * 根据条件查找回复
	 * @param threadId
	 * @param memberId
	 * @param courseId
	 * @param lessonId
	 * @param count--回复数
	 * @return
	 */
	List<CourseThreadPost> findPosts(Long threadId, Long memberId,
			Long courseId, Long lessonId, Integer count);

	/**
	 * 根据条件查找实体对象分页
	 * @param courseId
	 * @param lessonId
	 * @param threadId
	 * @param isElite
	 * @param memberId
	 * @param count
	 * @param pageable
	 * @return
	 */
	Page<CourseThreadPost> findPage(Long courseId, Long lessonId, Long threadId,
			Boolean isElite, Long memberId,Pageable pageable);


}
