package com.sram.service;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.CourseReview;

public interface CourseReviewService extends BaseService<CourseReview,Long> {
	/**
	 * 可以搜索关联属性的名称
	 * @param courseId 
	 */
	Page<CourseReview> findPage(Long courseId, Pageable pageable);

	/**
	 * jquery.pagination.js--借用了pageable
	 * @param courseId
	 * @param pageable
	 * @return
	 */
	Page<CourseReview> findPageList(Long courseId, Pageable pageable);
}
