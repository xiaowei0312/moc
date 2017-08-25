package com.sram.dao;

import java.util.Date;
import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.CourseLessonLearn;
import com.sram.entity.CourseLessonLearn.Status;

public interface CourseLessonLearnDao extends BaseDao<CourseLessonLearn,Long>{

	CourseLessonLearn findByLessonId(Long lessonId,Long memberId);

	Integer findFinishCount(Long memberId, Long id,Status finished);

	void updateStatu(CourseLessonLearn courseLessonLearn);

	List<CourseLessonLearn> findListByMemberCourse(Long memberId, Long courseId);

	Page<Object[]> findPage(Pageable pageable, Date beginDate, Date endDate);

	List<Object[]> findFinUserByCourseId(Long id, Integer lessonNum);

	List<CourseLessonLearn> findFinishLessonUser(Long id);

	void deleteByLessonId(Long lessonId);

	List<Object[]> findAnalySisList(Date beginDate, Date endDate, Status statu);


}
