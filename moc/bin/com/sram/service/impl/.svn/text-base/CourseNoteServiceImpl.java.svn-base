package com.sram.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.CourseNoteDao;
import com.sram.entity.CourseNote;
import com.sram.service.CourseNoteService;

@Service("courseNoteServiceImpl")
public class CourseNoteServiceImpl extends BaseServiceImpl<CourseNote, Long> implements CourseNoteService{

	@Resource(name="courseNoteDaoImpl")
	private CourseNoteDao courseNoteDao;
	
	@Resource(name = "courseNoteDaoImpl")
	public void setBaseDao(CourseNoteDao courseNoteDao) {
		super.setBaseDao(courseNoteDao);
	}
	
	@Transactional(readOnly=true)
	public List<CourseNote> findNotes(Long memberId, Integer status, Long courseId,Long lessonId,Integer count) {
		return courseNoteDao.findNotes(memberId,status,courseId,lessonId,count);
	}
	
	@Transactional(readOnly=true)
	public Page<CourseNote> findPage(Long courseId,Pageable pageable){
		return courseNoteDao.findPage(courseId,pageable);
	}

	public Page<CourseNote> findPageByUserId(Long memberId, Pageable pageable) {
		// TODO Auto-generated method stub
		return courseNoteDao.findPageByUserId(memberId,pageable);
	}

	public void deleteByLessonId(Long lessonId) {
		courseNoteDao.deleteByLessonId(lessonId);
	}
}
