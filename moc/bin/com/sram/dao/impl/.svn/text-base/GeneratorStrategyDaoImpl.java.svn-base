package com.sram.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.Constants.TestpaperType;
import com.sram.dao.GeneratorStrategyDao;
import com.sram.entity.GeneratorStrategy;
import com.sram.entity.Testpaper;
import com.sram.entity.BaseEntity.Update;

/** 
 * <p>功能:</p> 
 * @author 杨楷
 * @date 2015-3-18 下午04:08:49
 */
@Repository("generatorStrategyDaoImpl")
public class GeneratorStrategyDaoImpl extends BaseDaoImpl<GeneratorStrategy, Long> implements GeneratorStrategyDao{
	
	public Page<GeneratorStrategy> findPage(Pageable pageable,String generatorStrategyType) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<GeneratorStrategy> criteriaQuery = criteriaBuilder.createQuery(GeneratorStrategy.class);
		Root<GeneratorStrategy> root = criteriaQuery.from(GeneratorStrategy.class);
		criteriaQuery.select(root);
		return super.findPage(criteriaQuery, pageable);
	}

	public List<GeneratorStrategy> findAll() {
		TypedQuery<GeneratorStrategy> query;
		String jpql = "select g from GeneratorStrategy g left join fetch g.outlineCategory ";
		query = entityManager.createQuery(jpql, GeneratorStrategy.class).setFlushMode(FlushModeType.COMMIT);
		return query.getResultList();
	}
	
	public List<GeneratorStrategy> findByOutlineCategoryId(Long outlineCategoryId) {
		String jpql="select g from GeneratorStrategy g  " +
				" left join fetch g.outlineCategory " +
				" where g.outlineCategory.id=:outlineCategoryId";
		String jpql1="select g from GeneratorStrategy g  " +
		" left join fetch g.outlineCategory " +
		" where g.outlineCategory.id is null";
		TypedQuery<GeneratorStrategy> query = outlineCategoryId!=-1?entityManager.createQuery(jpql, GeneratorStrategy.class).setParameter("outlineCategoryId", outlineCategoryId):entityManager.createQuery(jpql1, GeneratorStrategy.class);
		return query.getResultList();
	}
	public void updateGeneratorStrategy(GeneratorStrategy generatorStrategy){
		this.merge(generatorStrategy);
	}
	
	public GeneratorStrategy findByOutlineCategoryId(Long rootOutlineCategoryId,TestpaperType testpaperType){
		List<GeneratorStrategy> list;
		String jpql="select g from GeneratorStrategy g  left join fetch g.generatorQuestionConfigs where g.outlineCategory.id=:rootOutlineCategoryId and g.testpaperType=:testpaperType";
		String jpql1="select g from GeneratorStrategy g left join fetch g.generatorQuestionConfigs where g.testpaperType=:testpaperType and g.outlineCategory.id is null";
		if (rootOutlineCategoryId==null) {
			 list = entityManager.createQuery(jpql1, GeneratorStrategy.class).setFlushMode(FlushModeType.COMMIT).setParameter("testpaperType", testpaperType).getResultList();
		}else {
			list = entityManager.createQuery(jpql, GeneratorStrategy.class).setFlushMode(FlushModeType.COMMIT).setParameter("rootOutlineCategoryId", rootOutlineCategoryId).setParameter("testpaperType", testpaperType).getResultList();
		}
		 if (list==null||list.size()==0) {
				return null;
			}
			return list.get(0);
	}
}
