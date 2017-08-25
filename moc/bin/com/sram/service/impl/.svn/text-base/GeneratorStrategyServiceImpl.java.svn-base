package com.sram.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sram.Constants.TestpaperType;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.GeneratorQuestionConfigDao;
import com.sram.dao.GeneratorStrategyDao;
import com.sram.entity.GeneratorQuestionConfig;
import com.sram.entity.GeneratorStrategy;
import com.sram.entity.GeneratorStrategy.GeneratorType;
import com.sram.entity.Testpaper;
import com.sram.service.GeneratorStrategyService;

/** 
 * <p>功能:</p> 
 * @author 杨楷
 * @date 2015-3-18 下午04:18:26
 */
@Service("generatorStrategyServiceImpl")
public class GeneratorStrategyServiceImpl extends BaseServiceImpl<GeneratorStrategy, Long>  implements GeneratorStrategyService{
	@Resource(name = "generatorStrategyDaoImpl")
	private GeneratorStrategyDao generatorStrategyDao;

	@Resource(name = "generatorStrategyDaoImpl")
	public void setBaseDao(GeneratorStrategyDao generatorStrategyDao) {
		super.setBaseDao(generatorStrategyDao);
	}
	@Autowired
	private GeneratorQuestionConfigDao generatorQuestionConfigDao;
	
	
	
	public List<GeneratorStrategy> findAll() {
		return generatorStrategyDao.findAll();
	}
	
	public Page<GeneratorStrategy> findPage(Pageable pageable, String generatorStrategyrType) {
		return generatorStrategyDao.findPage(pageable, generatorStrategyrType);
	}
	
	/**
	 * <p>功能:事务存储GeneratorStrategy</p> 
	 * @author 杨楷
	 * @date 2015-3-18 下午04:52:39 
	 * @param generatorStrategy
	 * @param generatorQuestionConfigs
	 * @return
	 */
	@Transactional
	public boolean save(GeneratorStrategy generatorStrategy,List<GeneratorQuestionConfig> generatorQuestionConfigs){
		try {
		generatorStrategyDao.persist(generatorStrategy);
		for (GeneratorQuestionConfig generatorQuestionConfig : generatorQuestionConfigs) {
			generatorQuestionConfig.setGeneratorStrategy(generatorStrategy);
			generatorQuestionConfigDao.persist(generatorQuestionConfig);
		}
		return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<GeneratorStrategy> findByOutlineCategoryId(Long outlineCategoryId){
		return generatorStrategyDao.findByOutlineCategoryId(outlineCategoryId);
		
	}
	@Transactional
	public boolean updateCascade(GeneratorStrategy generatorStrategy,List<GeneratorQuestionConfig> generatorQuestionConfigs) {
		try {
		//1.删除试题配置
		generatorQuestionConfigDao.deleteById(generatorStrategy.getId());
		GeneratorStrategy oldGeneratorStrategy = find(generatorStrategy.getId());
		if (oldGeneratorStrategy.getOutlineCategory()!=null) {
			generatorStrategy.setOutlineCategory(oldGeneratorStrategy.getOutlineCategory());
		}
		//2.update
		generatorStrategyDao.updateGeneratorStrategy(generatorStrategy);
		//3.save
		for (GeneratorQuestionConfig generatorQuestionConfig : generatorQuestionConfigs) {
			if (generatorStrategy.getTestpaperType()==TestpaperType.intellexercise||generatorQuestionConfig.getQuestionType()!=null) {
			generatorQuestionConfig.setGeneratorStrategy(generatorStrategy);
			generatorQuestionConfigDao.persist(generatorQuestionConfig);
			}
		}
		return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Transactional
	public void delete(Long[] ids){
		for (Long id : ids) {
			generatorStrategyDao.remove(generatorStrategyDao.find(id));
		}	
	}
	public GeneratorStrategy findByOutlineCategoryId(Long rootOutlineCategoryId,TestpaperType testpaperType){
		return generatorStrategyDao.findByOutlineCategoryId(rootOutlineCategoryId, testpaperType);
	}
}
