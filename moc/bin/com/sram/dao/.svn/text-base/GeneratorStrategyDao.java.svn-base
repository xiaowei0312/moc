package com.sram.dao;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.Constants.TestpaperType;
import com.sram.dao.impl.BaseDaoImpl;
import com.sram.entity.GeneratorStrategy;
import com.sram.entity.Testpaper;

/** 
 * <p>功能:</p> 
 * @author 杨楷
 * @date 2015-3-18 下午04:07:28
 */
public interface GeneratorStrategyDao  extends BaseDao<GeneratorStrategy, Long>{

	/**
	 * 查找配置策略分页
	 * 
	 */
	Page<GeneratorStrategy> findPage(Pageable pageable,String gneratorStrategyType);
	
	/**
	 * 查询所有策略
	 * @returng
	 */
	public List<GeneratorStrategy> findAll();
	
	public List<GeneratorStrategy> findByOutlineCategoryId(Long outlineCategoryId);
	
	public void updateGeneratorStrategy(GeneratorStrategy generatorStrategy);
	public GeneratorStrategy findByOutlineCategoryId(Long rootOutlineCategoryId,TestpaperType testpaperType);
}
