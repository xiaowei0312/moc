package com.sram.dao;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.CourseReview;

public interface CourseReviewDao extends BaseDao<CourseReview,Long>{

	/**
	 * 课程分页、可查找关联的属性
	 * @param courseId
	 * @param pageable
	 * @return
	 */
	Page<CourseReview> findPage(Long courseId, Pageable pageable);

	/**
	 * jquery.pagination.js----借用了pageable
	 * @param courseId
	 * @param pageable
	 * @return
	 */
	Page<CourseReview> findPageList(Long courseId, Pageable pageable);

}
