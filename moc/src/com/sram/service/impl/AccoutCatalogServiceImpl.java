package com.sram.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.AccoutCatalogDao;
import com.sram.entity.AccoutCatalog;
import com.sram.service.AccoutCatalogService;
@Service("accoutCatalogServiceImpl")
public class AccoutCatalogServiceImpl extends BaseServiceImpl<AccoutCatalog, Long> implements AccoutCatalogService{
	@Resource(name = "accoutCatalogDaoImpl")
	private AccoutCatalogDao accoutCatalogDao;
	@Resource(name = "accoutCatalogDaoImpl")
	
	
	public void setBaseDao(AccoutCatalogDao accoutCatalogDao) {
		super.setBaseDao(accoutCatalogDao);
	}
	
	
	@Transactional(readOnly = true)
	public List<AccoutCatalog> findRoots() {
		return accoutCatalogDao.findRoots(null);
	}
	@Transactional(readOnly = true)
	public List<AccoutCatalog> findParents(AccoutCatalog accoutCatalog) {
		return accoutCatalogDao.findParents(accoutCatalog, null);
	}
	@Transactional(readOnly = true)
	public List<AccoutCatalog> findTree() {
		return accoutCatalogDao.findChildren(null, null);
	}
	@Transactional(readOnly = true)
	public List<AccoutCatalog> findChildren(AccoutCatalog accoutCatalog,
			Integer count) {
		return accoutCatalogDao.findChildren(accoutCatalog, count);
	}
	public String maxBm(Long id){
		return accoutCatalogDao.maxBm(id);
	}
	
	public boolean findAccoutCatalogCode(String code) {
		return accoutCatalogDao.findAccoutCatalogCode(code);
	}
	/**
	 * <p>功能:查找所有节点</p> 
	 * @author 杨楷
	 * @date 2015年4月28日 下午2:12:05 
	 * @return
	 */
	@Transactional
	@Cacheable("accoutCatalogs")
	public List<AccoutCatalog> findAllChildren(){
		return super.findAll();
	}
	@Override
	@Transactional
	@CacheEvict(value = {"accoutCatalogs" }, allEntries = true)
	public void save(AccoutCatalog entity) {
		super.save(entity);
	}
	@Override
	@Transactional
	@CacheEvict(value = {"accoutCatalogs" }, allEntries = true)
	public void delete(Long id) {
		super.delete(id);
	}
	@Override
	@Transactional
	@CacheEvict(value = {"accoutCatalogs" }, allEntries = true)
	public AccoutCatalog update(AccoutCatalog entity,
			String... ignoreProperties) {
		return super.update(entity, ignoreProperties);
	}
	
	public Page<AccoutCatalog> findRootsPage(Pageable pageable) {
		return accoutCatalogDao.findRootsPage(pageable);
	}
	public boolean updateAccoutCatalogOrder(Long currentNodeId,
			Long currentOrder, Long moveNodeId, Long moveOrder) {
		return  accoutCatalogDao.updateAccoutCatalogOrder(currentNodeId, currentOrder, moveNodeId, moveOrder);
	}
	public Long findMaxChildrenOrder(Long parentId) {
		return accoutCatalogDao.findMaxChildrenOrder(parentId);
	}
}
