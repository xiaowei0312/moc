/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service;

import java.util.List;

import com.sram.entity.CourseCategory;


/**
 * Service - 课程分类
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface CourseCategoryService extends BaseService<CourseCategory, Long> {

	/**
	 * 查找顶级课程分类
	 * 
	 * @return 顶级课程分类
	 */
	List<CourseCategory> findRoots();

	/**
	 * 查找顶级课程分类
	 * 
	 * @param count
	 *            数量
	 * @return 顶级课程分类
	 */
	List<CourseCategory> findRoots(Integer count);

	/**
	 * 查找顶级课程分类(缓存)
	 * 
	 * @param count
	 *            数量
	 * @param cacheRegion
	 *            缓存区域
	 * @return 顶级课程分类(缓存)
	 */
	List<CourseCategory> findRoots(Integer count, String cacheRegion);

	/**
	 * 查找顶级课程分类
	 * 
	 * @param CourseCategory
	 *            课程分类
	 * @return 上级课程分类
	 */
	List<CourseCategory> findParents(CourseCategory courseCategory);

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
	 * 查找上级课程分类(缓存)
	 * 
	 * @param CourseCategory
	 *            课程分类
	 * @param count
	 *            数量
	 * @param cacheRegion
	 *            缓存区域
	 * @return 上级课程分类(缓存)
	 */
	List<CourseCategory> findParents(CourseCategory courseCategory, Integer count, String cacheRegion);

	/**
	 * 查找课程分类树
	 * 
	 * @return 课程分类树
	 */
	List<CourseCategory> findTree();
	List<CourseCategory> findChildren(CourseCategory courseCategory);

	/**
	 * 查找下级课程分类
	 * 
	 * @param courseCategory
	 *            课程分类
	 * @param count
	 *            数量
	 * @return 下级课程分类
	 */
	List<CourseCategory> findChildren(CourseCategory courseCategory, Integer count);

	/**
	 * 查找下级课程分类(缓存)
	 * 
	 * @param courseCategory
	 *            课程分类
	 * @param count
	 *            数量
	 * @param cacheRegion
	 *            缓存区域
	 * @return 下级课程分类(缓存)
	 */
	List<CourseCategory> findChildren(CourseCategory courseCategory, Integer count, String cacheRegion);

	List<Object[]> findHotThread();

	List<CourseCategory> findSecondCategory();


}