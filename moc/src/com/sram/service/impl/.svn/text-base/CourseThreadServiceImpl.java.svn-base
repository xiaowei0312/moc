package com.sram.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.CourseDao;
import com.sram.dao.CourseThreadDao;
import com.sram.entity.Course;
import com.sram.entity.CourseCategory;
import com.sram.entity.CourseThread;
import com.sram.entity.CourseThread.Type;
import com.sram.service.CourseThreadService;
@Service("courseThreadServiceImpl")
public class CourseThreadServiceImpl extends BaseServiceImpl<CourseThread,Long> implements CourseThreadService{
	@Resource(name="courseThreadDaoImpl")
	private CourseThreadDao courseThreadDao;
	
	@Resource(name = "courseDaoImpl")
	private CourseDao courseDao;

	@Resource(name="courseThreadDaoImpl")
	public void setBaseDao(CourseThreadDao courseThreadDao) {
		// TODO Auto-generated method stub
		super.setBaseDao(courseThreadDao);
	}
	@Transactional(readOnly=true)
	public List<CourseThread> getThreads(Long memberId, Long lessonId,
			Long courseId,Integer count) {
		return courseThreadDao.getThreads(memberId,lessonId,courseId,count);
	}
	
	/**
	 * 保存问答
	 */
	@Override
	@Transactional
	public void save(CourseThread courseThread) {
		Course cour=courseThread.getCourse();
		if(cour!=null){
			Long courseId=cour.getId();
			Course course=courseDao.find(courseId);
			courseThread.setCourseCategory(course.getCourseCategory());
		}
		super.save(courseThread);
		courseThreadDao.flush();
	}
	
	@Override
	@Transactional
	public CourseThread find(Long id){
		CourseThread courseThread=super.find(id);
		courseThreadDao.updateHitNum(courseThread);
		return courseThread;
	}
	
	@Transactional(readOnly=true)
	public Page<Object[]> findPage(Integer courseThreadPosts,Type type,Integer isStick,
			Integer isElite,Integer isClosed,Long courseId,Pageable pageable){
		return courseThreadDao.findPage(courseThreadPosts,type,isStick,isElite,isClosed,courseId,pageable);
	}
	@Transactional(readOnly=true)
	public void changeStatus(CourseThread courseThread){
		courseThreadDao.changeStatus(courseThread);
	}
	public Page<CourseThread> findMyQuestionPage(Pageable pageable,
			Long memberId) {
		// TODO Auto-generated method stub
		return courseThreadDao.findMyQuestionPage(pageable,memberId);
	}
	
	public Page<Object[]> findMyAnswerPage(Pageable pageable, Long memberId) {
		// TODO Auto-generated method stub
		return courseThreadDao.findMyAnswerPage(pageable,memberId);
	}
	
	public Page<Object[]> findAllQuestionPage(Pageable pageable,String threadContent,String tabFlag,Long categoryId) {
		// TODO Auto-generated method stub
		return courseThreadDao.findAllQuestionPage(pageable,threadContent,tabFlag,categoryId);
	}
	public List<CourseThread> findHostThread() {
		// TODO Auto-generated method stub
		return courseThreadDao.findHostThread();
	}
	@Transactional(readOnly=true)
	public Page<CourseThread> findList(Integer courseThreadPosts, Type type,
			Integer isStick, Integer isElite, Integer isClosed, Long courseId,
			Pageable pageable) {
		return courseThreadDao.findList(courseThreadPosts, type,
				isStick, isElite,isClosed,courseId,pageable);
	}
	public List<CourseThread> findRelateThreads(Long categoryId, int pageSize) {
		// TODO Auto-generated method stub
		return courseThreadDao.findRelateThreads(categoryId,pageSize);
	}
	public String findThreadTitle(Long threadId) {
		return courseThreadDao.findThreadTitle(threadId);
	}
	
}
