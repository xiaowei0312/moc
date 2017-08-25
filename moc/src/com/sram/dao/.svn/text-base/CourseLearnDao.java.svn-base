package com.sram.dao;

import java.util.Date;
import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.CourseLearn;
import com.sram.entity.CourseLearn.Status;



public interface CourseLearnDao extends BaseDao<CourseLearn, Long>{

	CourseLearn findByCourseId(Long courseId,Long memberId);

	void updateCourseLearn(Long memberId, Long courseId, Status finished);

	void updateFinLessonNum(Integer finishLessonNum, Long courseId,
			Long memberId);

	void changeRemark(Long id, String description);

	boolean memberCourseLearn(Long memberId);

	Page<Object[]> findPage(Pageable pageable, Date beginDate, Date endDate);


	void updateLearnStatus(Long id,Status learning);
	
	//根据课程id更新学习状态
	void updateStatusByCourseId(Long courseId,
			com.sram.entity.CourseLearn.Status learning);

	void updateFinLessonNum(Long id, Long userId);

	List<Object[]> findAnalySisList(Date beginDate, Date endDate);

	boolean courseLearnExists(Long memberId, Long courseId);


}
