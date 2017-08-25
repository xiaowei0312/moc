package com.sram.service;

import java.util.Date;
import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.CourseLearn;


public interface CourseLearnService  extends BaseService<CourseLearn, Long>{

	CourseLearn findByCourseId(Long courseId,Long memberId);

	void updateCourseLearn(Long memberId, Long id,
			com.sram.entity.CourseLearn.Status finished);

	void updateFinLessonNum(Integer finishLessonNum, Long id, Long memberId);

	void changeRemark(Long id, String description);
	
	/**
	 * 判断该会员是否已学习课程
	 * 
	 * @param memberId
	 *      会员编号
	 * @return 会员是否已学习课程
	 */
	boolean memberCourseLearn(Long memberId);

	Page<Object[]> findPage(Pageable pageable, Date beginDate, Date endDate);
	/**
	 * 指定时间区间加入学习数
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	List<Object[]> findAnalySisList(Date beginDate, Date endDate);

	boolean courseLearnExists(Long memberId, Long courseId);

}
