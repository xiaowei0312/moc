package com.sram.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sram.dao.GeneratorQuestionConfigDao;
import com.sram.entity.GeneratorQuestionConfig;
import com.sram.service.GeneratorQuestionConfigService;

/** 
 * <p>功能:</p> 
 * @author 杨楷
 * @date 2015-3-18 下午04:39:36
 */
@Service("generatorQuestionConfigServiceImpl")
public class GeneratorQuestionConfigServiceImpl extends BaseServiceImpl<GeneratorQuestionConfig, Long> implements GeneratorQuestionConfigService {
	@Resource(name = "generatorQuestionConfigDaoImpl")
	private GeneratorQuestionConfigDao  generatorQuestionConfigDao;

	@Resource(name = "generatorQuestionConfigDaoImpl")
	public void setBaseDao(GeneratorQuestionConfigDao generatorQuestionConfigDao) {
		super.setBaseDao(generatorQuestionConfigDao);
	}
}
