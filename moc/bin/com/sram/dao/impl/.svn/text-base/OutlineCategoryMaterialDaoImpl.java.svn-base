/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sram.dao.OutlineCategoryMaterialDao;
import com.sram.entity.OutlineCategoryMaterial;

/**
 * Dao - 课程资料
 * 
 * @author Sram Team
 * @version 1.0
 */
@Repository("outlineCategoryMaterialDaoImpl")
public class OutlineCategoryMaterialDaoImpl extends BaseDaoImpl<OutlineCategoryMaterial, Long>
		implements OutlineCategoryMaterialDao {

	public List<OutlineCategoryMaterial> findList(Long outlineCategoryId) {
		StringBuffer jpql = new StringBuffer();
			jpql.append(" select m from OutlineCategoryMaterial m ")
				.append(" where m.outlineCategory.id =:outlineCategoryId ");
				jpql.append(" order by m.createDate ");
		
		TypedQuery<OutlineCategoryMaterial> query=entityManager
											.createQuery(jpql.toString(),OutlineCategoryMaterial.class)
											.setParameter("outlineCategoryId", outlineCategoryId);
		return query.getResultList();
	}

}