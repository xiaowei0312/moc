/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao;

import java.util.List;

import com.sram.entity.CourseCategory;


/**
 * Dao - 课程分类
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface CourseCategoryDao extends BaseDao<CourseCategory, Long> {

	/**
	 * 查找顶级课程分类
	 * 
	 * @param count
	 *            数量
	 * @return 顶级课程分类
	 */
	List<CourseCategory> findRoots(Integer count);

	/**
	 * 查找上级课程分类
	 * 
	 * @param CourseCategory
	 *            课程分类
	 * @param count
	 *            数量
	 * @return 上级课程分类
	 */
	List<CourseCategory> findParents(CourseCategory courseCategory, Integer count);

	/**
	 * 查找下级课程分类
	 * 
	 * @param CourseCategory
	 *            课程分类
	 * @param count
	 *            数量
	 * @return 下级课程分类
	 */
	List<CourseCategory> findChildren(CourseCategory courseCategory, Integer count);

	List<Object[]> findHotThread();

	List<CourseCategory> findSecondCategory();

}