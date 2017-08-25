package com.sram.service;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.Constants.TestpaperType;
import com.sram.entity.GeneratorQuestionConfig;
import com.sram.entity.GeneratorStrategy;

/** 
 * <p>功能:</p> 
 * @author 杨楷
 * @date 2015-3-18 下午04:16:53
 */
public interface GeneratorStrategyService extends BaseService<GeneratorStrategy	, Long>{
	public boolean save(GeneratorStrategy generatorStrategy,List<GeneratorQuestionConfig> generatorQuestionConfigs);
	
	public List<GeneratorStrategy> findAll();
	
	public Page<GeneratorStrategy> findPage(Pageable pageable, String generatorStrategyrType);
	
	public List<GeneratorStrategy> findByOutlineCategoryId(Long outlineCategoryId);
	
	public boolean updateCascade(GeneratorStrategy generatorStrategy,List<GeneratorQuestionConfig> generatorQuestionConfigs);
	/**
	 * 根据顶级大纲id加载策略
	 */
	public GeneratorStrategy findByOutlineCategoryId(Long rootOutlineCategoryId,TestpaperType testpaperType);
}
