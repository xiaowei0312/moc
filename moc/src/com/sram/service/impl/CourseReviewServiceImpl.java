package com.sram.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.CourseReviewDao;
import com.sram.entity.CourseReview;
import com.sram.service.CourseReviewService;
import com.sram.service.CourseService;

@Service("courseReviewServiceImpl")
public class CourseReviewServiceImpl extends BaseServiceImpl<CourseReview,Long> implements CourseReviewService{

	@Resource(name="courseReviewDaoImpl")
	private CourseReviewDao courseReviewDao;
	@Resource(name="courseServiceImpl")
	private CourseService courseService;
	
	
	@Resource(name="courseReviewDaoImpl")
	public void setBaseDao(CourseReviewDao courseReviewDao) {
		super.setBaseDao(courseReviewDao);
	}
	@Transactional(readOnly=true)
	public Page<CourseReview> findPage(Long courseId, Pageable pageable) {
		return courseReviewDao.findPage(courseId,pageable);
	}
	@Transactional(readOnly=true)
	public Page<CourseReview> findPageList(Long courseId, Pageable pageable) {
		return courseReviewDao.findPageList(courseId,pageable);
	}
	
	@Override
	@Transactional
	public void save(CourseReview courseReview) {
		super.save(courseReview);
		courseService.updateRatingNum(courseReview.getCourse().getId());
	}
	
	
	
	
}
