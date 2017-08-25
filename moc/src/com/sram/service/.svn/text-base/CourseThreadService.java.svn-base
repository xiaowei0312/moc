package com.sram.service;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.CourseThread;
import com.sram.entity.CourseThread.Type;

public interface CourseThreadService extends BaseService<CourseThread,Long>{
	/**
	 * 获取问答题目
	 * @param memberId
	 * @param lessonId
	 * @param courseId
	 * @return
	 */
	List<CourseThread> getThreads(Long memberId,Long lessonId,Long courseId,Integer count);
	
	/**
	 * 查找、筛选，实体对象分页，搜索方法关联查询
	 * @param isClosed ---0关闭、1开启、其它所有
	 * @param isElite 
	 * @param isStick 
	 * @param type 
	 * @param courseThreadPosts 
	 * @param courseId 
	 */
	Page<Object[]> findPage(Integer courseThreadPosts, Type type, Integer isStick, Integer isElite, Integer isClosed, Long courseId, Pageable pageable);
	
	/**
	 * 分页是前台的jquery.pagination.js
	 * @param courseThreadPosts
	 * @param type
	 * @param isStick
	 * @param isElite
	 * @param isClosed---1是开启，0是关闭，其它是所有
	 * @param courseId
	 * @param pageable
	 * @return
	 */
	Page<CourseThread> findList(Integer courseThreadPosts, Type type, Integer isStick, Integer isElite, Integer isClosed, Long courseId, Pageable pageable);

	/**
	 * 关闭或打开问答
	 * @param courseThread
	 */
	public void changeStatus(CourseThread courseThread);
	/**
	 * 
	 * @param pageable
	 * @param memberId
	 * @return
	 */
	Page<CourseThread> findMyQuestionPage(Pageable pageable, Long memberId);

	Page<Object[]> findMyAnswerPage(Pageable pageable, Long memberId);

	Page<Object[]> findAllQuestionPage(Pageable pageable,String threadContent,String tabFlag,Long categoryId);

	List<CourseThread> findHostThread();

	List<CourseThread> findRelateThreads(Long categoryId, int pageSize);

	/**
	 * 查找问答名
	 * @param threadId
	 * @return
	 */
	String findThreadTitle(Long threadId);
}
