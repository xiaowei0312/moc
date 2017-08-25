package com.sram.dao.impl;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;

import com.sram.dao.GeneratorQuestionConfigDao;
import com.sram.entity.GeneratorQuestionConfig;

/** 
 * <p>功能:</p> 
 * @author 杨楷
 * @date 2015-3-18 下午04:36:32
 */
@Repository("generatorQuestionConfigDaoImpl")
public class GeneratorQuestionConfigDaoImpl extends BaseDaoImpl<GeneratorQuestionConfig, Long> implements GeneratorQuestionConfigDao {

	public void deleteById(Long id){
		String jpql="delete from GeneratorQuestionConfig c where c.generatorStrategy.id=:configId";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("configId", id).executeUpdate();
	}
}
