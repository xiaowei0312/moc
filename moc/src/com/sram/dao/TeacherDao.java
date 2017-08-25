/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao;

import java.util.List;
import java.util.Set;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.Teacher;


/**
 * Dao - 教师
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface TeacherDao extends BaseDao<Teacher, Long> {
	/**
	 * 查找教师分页
	 * @param deadlineNotify 
	 * @param showStudentNumType 
	 * @param serializeMode 
	 */
	Page<Teacher> findPage(
			String name,Set<Long> teacherIds,String recommended,	
			Pageable pageable
	);

	List<Teacher> findTeachersNotIds(String ids);

	/**
	 * 验证身份证号是否已存在
	 * @param idcard
	 * @return
	 */
	boolean idcardExists(String idcard);

	/**
	 * 验证id用户的手机号是否重复
	 * @param mobile
	 * @param id 
	 * @return
	 */
	boolean mobileExists(String mobile, Long id);

	void changeStatus(Teacher teacher);

	List<Teacher> findAll(Integer first,Integer count);
}