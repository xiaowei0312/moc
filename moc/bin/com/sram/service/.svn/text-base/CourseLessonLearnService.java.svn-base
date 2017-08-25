package com.sram.service;

import java.util.Date;
import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.CourseLesson;
import com.sram.entity.CourseLessonLearn;
import com.sram.entity.CourseLessonLearn.Status;

public interface CourseLessonLearnService extends BaseService<CourseLessonLearn,Long>{

	CourseLessonLearn findByLessonId(Long lessonId,Long memberId);


	Integer findFinishCount(Long memberId, Long id, Status finished);


	void updateStatus(CourseLessonLearn courseLessonLearn);


	List<CourseLessonLearn> findListByMemberCourse(Long memberId, Long courseId);


	Page<Object[]> findPage(Pageable pageable, Date beginDate, Date endDate);

    /**
     * 指定时间范围完成课时情况
     * @param finished 完成情况
     * */
	List<Object[]> findAnalySisList(Date beginDate, Date endDate,Status finished);

}
