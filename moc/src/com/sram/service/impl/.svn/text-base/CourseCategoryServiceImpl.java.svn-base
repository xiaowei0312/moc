/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sram.dao.CourseCategoryDao;
import com.sram.dao.CourseDao;
import com.sram.entity.CourseCategory;
import com.sram.service.CourseCategoryService;

/**
 * Service - 课程分类
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("courseCategoryServiceImpl")
public class CourseCategoryServiceImpl extends
		BaseServiceImpl<CourseCategory, Long> implements CourseCategoryService {

	@Resource(name = "courseCategoryDaoImpl")
	private CourseCategoryDao courseCategoryDao;
	
	@Resource(name = "courseDaoImpl")
	private CourseDao courseDao;

	@Resource(name = "courseCategoryDaoImpl")
	public void setBaseDao(CourseCategoryDao courseCategoryDao) {
		super.setBaseDao(courseCategoryDao);
	}

	@Transactional(readOnly = true)
	public List<CourseCategory> findRoots() {
		return courseCategoryDao.findRoots(null);
	}

	@Override
	@Transactional
	@CacheEvict(value = {"courseCategory"}, allEntries = true)
	public void save(CourseCategory entity) {
		super.save(entity);
	}

	@Override
	@Transactional
	@CacheEvict(value = {"courseCategory"}, allEntries = true)
	public CourseCategory update(CourseCategory entity,String... ignoreProperties) {
		//根据课程类别TreePath更改课程TreePath
		String oldTreePath=entity.getTreePath();
		String newTreePath="";
		if(entity.getParent()==null){
			newTreePath=",";
		}else{
			newTreePath=entity.getParent().getTreePath()+entity.getParent().getId()+",";
		}
		courseDao.updateTreePath(oldTreePath,newTreePath);
		return super.update(entity,ignoreProperties);
	}

	@Override
	@Transactional
	@CacheEvict(value = {"courseCategory"}, allEntries = true)
	public void delete(CourseCategory entity) {
		super.delete(entity);
	}
	@Override
	@Transactional
	@CacheEvict(value = { "courseCategory" }, allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}
	@Override
	@Transactional
	@CacheEvict(value = "courseCategory", allEntries = true)
	public void delete(Long... ids) {
		// TODO Auto-generated method stub
		super.delete(ids);
	}

	@Transactional(readOnly = true)
	public List<CourseCategory> findRoots(Integer count) {
		return courseCategoryDao.findRoots(count);
	}

	@Transactional(readOnly = true)
	@Cacheable("courseCategory")
	public List<CourseCategory> findRoots(Integer count, String cacheRegion) {
		return courseCategoryDao.findRoots(count);
	}

	@Transactional(readOnly = true)
	public List<CourseCategory> findParents(CourseCategory courseCategory) {
		return courseCategoryDao.findParents(courseCategory, null);
	}

	@Transactional(readOnly = true)
	public List<CourseCategory> findParents(CourseCategory courseCategory,
			Integer count) {
		return courseCategoryDao.findParents(courseCategory, count);
	}

	@Transactional(readOnly = true)
	public List<CourseCategory> findParents(CourseCategory courseCategory,
			Integer count, String cacheRegion) {
		return courseCategoryDao.findParents(courseCategory, count);
	}

	@Transactional(readOnly = true)
	public List<CourseCategory> findTree() {
		return courseCategoryDao.findChildren(null, null);
	}

	@Transactional(readOnly = true)
	public List<CourseCategory> findChildren(CourseCategory courseCategory) {
		return courseCategoryDao.findChildren(courseCategory, null);
	}

	@Transactional(readOnly = true)
	public List<CourseCategory> findChildren(CourseCategory courseCategory,
			Integer count) {
		return courseCategoryDao.findChildren(courseCategory, count);
	}

	@Transactional(readOnly = true)
	public List<CourseCategory> findChildren(CourseCategory courseCategory,
			Integer count, String cacheRegion) {
		return courseCategoryDao.findChildren(courseCategory, count);
	}

	public List<Object[]> findHotThread() {
		// TODO Auto-generated method stub
		return courseCategoryDao.findHotThread();
	}

	public List<CourseCategory> findSecondCategory() {
		// TODO Auto-generated method stub
		return courseCategoryDao.findSecondCategory();
	}
}