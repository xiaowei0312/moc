package com.sram.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sram.Filter;
import com.sram.Filter.Operator;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.CourseLearnDao;
import com.sram.dao.CourseLessonLearnDao;
import com.sram.dao.impl.CourseLessonLearnDaoImpl;
import com.sram.entity.CourseLessonLearn;
import com.sram.entity.CourseLessonLearn.Status;
import com.sram.service.CourseLessonLearnService;

@Service("courseLessonLearnServiceImpl")
public class CourseLessonLearnServiceImpl extends BaseServiceImpl<CourseLessonLearn,Long> implements CourseLessonLearnService{
    
	@Resource(name="courseLessonLearnDaoImpl")
	private CourseLessonLearnDao courseLessonLearnDao;
	
	  @Resource(name="courseLessonLearnDaoImpl")
		public void setBaseDao(CourseLessonLearnDaoImpl courseLessonLearnDaoImpl) {
			super.setBaseDao(courseLessonLearnDaoImpl);
		}
	public CourseLessonLearn findByLessonId(Long lessonId,Long memberId) {
		// TODO Auto-generated method stub
		return courseLessonLearnDao.findByLessonId(lessonId,memberId);
	}

	public Integer findFinishCount(Long memberId, Long id ,Status finished) {
		// TODO Auto-generated method stub
		return courseLessonLearnDao.findFinishCount(memberId,id,finished);
	}

	public void updateStatus(CourseLessonLearn courseLessonLearn) {
		// TODO Auto-generated method stub
		courseLessonLearnDao.updateStatu(courseLessonLearn);
	}

	@Transactional(readOnly=true)
	public List<CourseLessonLearn> findListByMemberCourse(Long memberId,
			Long courseId) {
		return courseLessonLearnDao.findListByMemberCourse(memberId,courseId);
	}
	public Page<Object[]> findPage(Pageable pageable, Date beginDate,
			Date endDate) {
		// TODO Auto-generated method stub
		return courseLessonLearnDao.findPage(pageable,beginDate,endDate);
	}
	public List<Object[]> findAnalySisList(Date beginDate, Date endDate,Status finished) {
		// TODO Auto-generated method stub
		return courseLessonLearnDao.findAnalySisList(beginDate,endDate,finished);
	}

}
