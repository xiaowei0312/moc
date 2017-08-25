package com.sram.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.IndustryCategoryDao;
import com.sram.entity.IndustryCategory;
import com.sram.entity.OutlineCategory;
import com.sram.service.IndustryCategoryService;
@Service("industryCategoryServiceImpl")
public class IndustryCategoryServiceImpl extends BaseServiceImpl<IndustryCategory, Long> implements IndustryCategoryService{
	@Resource(name = "industryCategoryDaoImpl")
	private IndustryCategoryDao industryCategoryDao;
	@Resource(name = "industryCategoryDaoImpl")
	public void setBaseDao(IndustryCategoryDao industryCategoryDao) {
		super.setBaseDao(industryCategoryDao);
	}
	@Transactional(readOnly = true)
	public List<IndustryCategory> findRoots() {
		return industryCategoryDao.findRoots(null);
	}
	@Transactional(readOnly = true)
	public List<IndustryCategory> findParents(IndustryCategory outlineCategory) {
		return industryCategoryDao.findParents(outlineCategory, null);
	}
	@Transactional(readOnly = true)
	public List<IndustryCategory> findTree() {
		return industryCategoryDao.findChildren(null, null);
	}
	@Transactional(readOnly = true)
	public List<IndustryCategory> findChildren(IndustryCategory outlineCategory,
			Integer count) {
		return industryCategoryDao.findChildren(outlineCategory, count);
	}
	@Override
	@Transactional
	@CacheEvict(value = {"rootIndustryCategorys", "course", "courseCategory", "review", "consultation" }, allEntries = true)
	public IndustryCategory update(IndustryCategory industryCategory, String... ignoreProperties) {
		return super.update(industryCategory, ignoreProperties);
	}
	
	@Override
	@CacheEvict(value = {"rootIndustryCategorys", "course", "courseCategory", "review", "consultation" }, allEntries = true)
	public void save(IndustryCategory entity) {
		super.save(entity);
	}
	@Override
	@Transactional
	@CacheEvict(value = {"rootIndustryCategorys", "course", "courseCategory", "review", "consultation" }, allEntries = true)
	public IndustryCategory update(IndustryCategory entity) {
		return super.update(entity);
	}
	@Override
	@Transactional
	@CacheEvict(value = {"rootIndustryCategorys", "course", "courseCategory", "review", "consultation" }, allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}
	@Transactional(readOnly = true)
	@Cacheable("rootIndustryCategorys")
	public List<IndustryCategory> findAllCategorys() {
		//手动组装树关系
		List<IndustryCategory> allCategorys = industryCategoryDao.findAllCategorys();
		List<IndustryCategory> childrenWithOutlineCategories=new ArrayList<IndustryCategory>();
		Iterator<IndustryCategory> iterator = allCategorys.iterator();
		while (iterator.hasNext()) {
			IndustryCategory element = iterator.next();
			if (element.getGrade()!=0) {
				childrenWithOutlineCategories.add(element);
				iterator.remove();
			}
		}
		List<IndustryCategory> children=new ArrayList<IndustryCategory>();
		List<Long> secondIndustryCategoryIds=new ArrayList<Long>();
		Iterator<IndustryCategory> childrenWithOutlineCategoriesIterator = childrenWithOutlineCategories.iterator();
		while (childrenWithOutlineCategoriesIterator.hasNext()) {
			IndustryCategory industryCategory = childrenWithOutlineCategoriesIterator.next();
			if (secondIndustryCategoryIds.contains(industryCategory.getId())) {
			}else {
				children.add(industryCategory);
				childrenWithOutlineCategoriesIterator.remove();
			}
		}
		Iterator<IndustryCategory> iterator2;
		for (IndustryCategory firstChild : children) {
			iterator2 = childrenWithOutlineCategories.iterator();
			while (iterator2.hasNext()) {
				IndustryCategory category = iterator2.next();
				if (firstChild.getId().equals(category.getId())) {
					List<OutlineCategory> outlineCategories = firstChild.getOutlineCategories();
					outlineCategories.addAll(category.getOutlineCategories());
					firstChild.setOutlineCategories(outlineCategories);
					iterator2.remove();
				}
			}
		}
		
		Iterator<IndustryCategory> childrenIterator;
		for (IndustryCategory rootIndustryCategory : allCategorys) {
			Set<IndustryCategory> childrenSet=new HashSet<IndustryCategory>();
			childrenIterator = children.iterator();
			while (childrenIterator.hasNext()) {
				IndustryCategory child = childrenIterator.next();
				if (rootIndustryCategory.getId().equals(child.getParent().getId())) {
					childrenSet.add(child);
					childrenIterator.remove();
				}
			}
			if (childrenSet.size()!=0) {
				rootIndustryCategory.setChildren(childrenSet);
			}
		}
		return allCategorys;
	}
	@CacheEvict(value = {"rootIndustryCategorys", "course", "courseCategory", "review", "consultation" }, allEntries = true)
	public boolean updateIndustryOrder(Long currentNodeId, Long currentOrder,
			Long moveNodeId, Long moveOrder) {
		return industryCategoryDao.updateIndustryOrder(currentNodeId, currentOrder, moveNodeId, moveOrder);
	}
	public Long findMaxOrder() {
		return industryCategoryDao.findMaxOrder();
	}
	public Long findMaxOrder(Long parentId) {
		return industryCategoryDao.findMaxOrder(parentId);
	}
	
	public Page<IndustryCategory> findRootsPage(Pageable pageable) {
		// TODO Auto-generated method stub
		return industryCategoryDao.findRootsPage(pageable);
	}
}
