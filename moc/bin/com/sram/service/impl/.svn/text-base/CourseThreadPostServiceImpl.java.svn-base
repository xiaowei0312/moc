package com.sram.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.CourseThreadDao;
import com.sram.dao.CourseThreadPostDao;
import com.sram.entity.CourseThread;
import com.sram.entity.CourseThreadPost;
import com.sram.service.CourseThreadPostService;
@Service("courseThreadPostServiceImpl")
public class CourseThreadPostServiceImpl extends BaseServiceImpl<CourseThreadPost,Long> implements CourseThreadPostService{
	@Resource(name="courseThreadPostDaoImpl")
	private CourseThreadPostDao courseThreadPostDao;
	
	@Resource(name="courseThreadDaoImpl")
	private CourseThreadDao courseThreadDao;

	
	@Resource(name="courseThreadPostDaoImpl")
	public void setBaseDao(CourseThreadPostDao courseThreadPostDao){
		super.setBaseDao(courseThreadPostDao);
	}
    
	@Override
	@Transactional
	public void save(CourseThreadPost courseThreadPost){
		CourseThread courseThread=courseThreadPost.getCourseThread();
		Long memberId=courseThreadPost.getMember().getId();
		courseThreadDao.updatePostNum(courseThread,memberId);
		super.save(courseThreadPost);
	}
	
	public List<CourseThreadPost> findPosts(Long threadId, Long memberId,
			Long courseId, Long lessonId, Integer count) {
		// TODO Auto-generated method stub
		return courseThreadPostDao.findPost(threadId,memberId,courseId,lessonId,count);
	}
	@Transactional(readOnly=true)
	public Page<CourseThreadPost> findPage(Long courseId, Long lessonId,
			Long threadId, Boolean isElite, Long memberId,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return courseThreadPostDao.findPage(courseId,lessonId,threadId,isElite,memberId,pageable);
	}
}
