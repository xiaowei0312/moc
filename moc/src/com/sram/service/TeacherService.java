/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service;

import java.util.List;
import java.util.Set;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.Course;
import com.sram.entity.Teacher;


/**
 * Service - 教师
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface TeacherService extends BaseService<Teacher, Long> {
	
	/**
	 * 根据条件查询教师
	 * @param name
	 * @param hasCourse
	 * @param recommended
	 * @param pageable
	 * @return
	 */
	Page<Teacher> findPage(String name,Set<Long> teacherIds,String recommended,Pageable pageable);

	List<Course> findCourseByTeacher(Teacher teacher);
	
	/**
	 * 查该课程以外的教师
	 * @param ids
	 * @return
	 */
	List<Teacher> findTeachersNotIds(Course course);

	/**
	 * 查找该课程的教师
	 * @param course
	 * @return
	 */
	List<Teacher> findListByCourse(Course course);

	/**
	 * 验证用户名是否存在
	 * @param idcard
	 * @return
	 */
	boolean idcardExists(String idcard);

	/**
	 * id用户的手机是否重复
	 * @param mobile
	 * @param id 
	 * @return
	 */
	boolean mobileExists(String mobile, Long id);
    /**
     * 改变教师状态
     */
	void changeStatus(Teacher teacher);

	List<Teacher> findAll(Integer first,Integer count);
}