package com.sram.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.CourseLearnDao;
import com.sram.entity.CourseLearn;
import com.sram.entity.CourseLearn.Status;
import com.sram.service.CourseLearnService;

/*
 * 课程学习
 */
@Service("courseLearnServiceImpl")
public class CourseLearnServiceImpl extends BaseServiceImpl<CourseLearn, Long> implements CourseLearnService, DisposableBean {
    @Resource(name="courseLearnDaoImpl")
	private CourseLearnDao courseLearnDao;
	
    @Resource(name="courseLearnDaoImpl")
	public void setBaseDao(CourseLearnDao courseLearnDao) {
		super.setBaseDao(courseLearnDao);
	}

	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public CourseLearn findByCourseId(Long courseId,Long memberId) {
		// TODO Auto-generated method stub
		return courseLearnDao.findByCourseId(courseId,memberId);
	}

	public void updateCourseLearn(Long memberId, Long id, Status finished) {
		// TODO Auto-generated method stub
		courseLearnDao.updateCourseLearn(memberId,id,finished);
	}

	public void updateFinLessonNum(Integer finishLessonNum, Long courseId,
			Long memberId) {
		// TODO Auto-generated method stub
		courseLearnDao.updateFinLessonNum(finishLessonNum,courseId,memberId);
	}

	public void changeRemark(Long id, String description) {
		// TODO Auto-generated method stub
		courseLearnDao.changeRemark(id,description);
	}

	public boolean memberCourseLearn(Long memberId) {
		// TODO Auto-generated method stub
		return courseLearnDao.memberCourseLearn(memberId);
	}

	public Page<Object[]> findPage(Pageable pageable, Date beginDate,
			Date endDate) {
		// TODO Auto-generated method stub
		return courseLearnDao.findPage(pageable,beginDate,endDate);
	}

	public List<Object[]> findAnalySisList(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		return courseLearnDao.findAnalySisList(beginDate,endDate);
	}

	public boolean courseLearnExists(Long memberId, Long courseId) {
		// TODO Auto-generated method stub
		return courseLearnDao.courseLearnExists(memberId,courseId);
	}


}
