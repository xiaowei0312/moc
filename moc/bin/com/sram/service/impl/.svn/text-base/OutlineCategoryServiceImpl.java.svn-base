package com.sram.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.OutlineCategoryDao;
import com.sram.dao.QuestionDao;
import com.sram.entity.OutlineCategory;
import com.sram.service.OutlineCategoryService;
@Service("outlineCategoryServiceImpl")
public class OutlineCategoryServiceImpl extends BaseServiceImpl<OutlineCategory, Long> implements OutlineCategoryService{
	@Resource(name = "outlineCategoryDaoImpl")
	private OutlineCategoryDao outlineCategoryDao;
	@Resource(name = "questionDaoImpl")
	private QuestionDao questionDao;
	@Resource(name = "outlineCategoryDaoImpl")
	
	
	
	public void setBaseDao(OutlineCategoryDao outlineCategoryDao) {
		super.setBaseDao(outlineCategoryDao);
	}
	@Transactional(readOnly = true)
	public List<OutlineCategory> findRoots() {
		return outlineCategoryDao.findRoots(null);
	}
	@Transactional(readOnly = true)
	public List<OutlineCategory> findParents(OutlineCategory outlineCategory) {
		return outlineCategoryDao.findParents(outlineCategory, null);
	}
	@Transactional(readOnly = true)
	public List<OutlineCategory> findTree() {
		return outlineCategoryDao.findChildren(null, null);
	}
	@Transactional(readOnly = true)
	public List<OutlineCategory> findChildren(OutlineCategory outlineCategory,
			Integer count) {
		return outlineCategoryDao.findChildren(outlineCategory, count);
	}
	public String maxBm(Long id){
		return outlineCategoryDao.maxBm(id);
	}
	@CacheEvict(value = {"rootIndustryCategorys", "course", "courseCategory", "review", "consultation" }, allEntries = true)
	public void updateQuestionTreePathByOutlineCategory(OutlineCategory outlineCategory) {
		OutlineCategory old_category = this.find(outlineCategory.getId());
		String oldTreePath=old_category.getTreePath();
		String newTreePath="";
		if(outlineCategory.getParent()==null){
			newTreePath=",";
		}else{
			newTreePath=outlineCategory.getParent().getTreePath()+outlineCategory.getParent().getId()+",";
		}
		
		if(!oldTreePath.equals(newTreePath)){
			outlineCategory.setOrder(Integer.parseInt(outlineCategoryDao.findMaxChildrenOrder(outlineCategory.getParent().getId()).toString()));
		}
		
		//更新题目的TreePath
		questionDao.updateTreePath(oldTreePath, newTreePath);
		//更新问题的大纲
		super.update(outlineCategory,"createAdmin","createDate");
	}
	public boolean findIndustryCategory(Long industryCategoryID) {
		return outlineCategoryDao.findIndustryCategory(industryCategoryID);
	}
	public boolean findOutLineCategoryCode(String code) {
		return outlineCategoryDao.findOutLineCategoryCode(code);
	}
	
	public List<OutlineCategory> findRootsExcludeGenerator(){
		return outlineCategoryDao.findRootsExcludeGenerator();
	}
	@CacheEvict(value = {"rootIndustryCategorys", "course", "courseCategory", "review", "consultation" }, allEntries = true)
	public boolean updateOutlineOrder(Long currentNodeId, Long currentOrder,Long moveNodeId, Long moveOrder) {
		return outlineCategoryDao.updateOutlineOrder(currentNodeId, currentOrder, moveNodeId, moveOrder);
	}
	public Long findMaxParentOrder(Long industryCategoryID) {
		return outlineCategoryDao.findMaxParentOrder(industryCategoryID);
	}
	public Long findMaxChildrenOrder(Long parentId) {
		return outlineCategoryDao.findMaxChildrenOrder(parentId);
	}
	
	@Override
	@CacheEvict(value = {"rootIndustryCategorys", "course", "courseCategory", "review", "consultation" }, allEntries = true)
	public void save(OutlineCategory entity) {
		super.save(entity);
	}
	@Override
	@CacheEvict(value = {"rootIndustryCategorys", "course", "courseCategory", "review", "consultation" }, allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}
	
	public List<OutlineCategory> findAllChildren(Long outlineCategoryId) {
		return outlineCategoryDao.findAllChildren(outlineCategoryId);
	}

	public Page<OutlineCategory> findRootsPage(Pageable pageable,String firstIndustryName,String secondIndustryName) {
		return outlineCategoryDao.findRootsPage(pageable,firstIndustryName,secondIndustryName);
	}
	public void relateCourseChapter(OutlineCategory outlineCategory) {
		outlineCategoryDao.relateCourseChapter(outlineCategory);
	}
	
	
}
