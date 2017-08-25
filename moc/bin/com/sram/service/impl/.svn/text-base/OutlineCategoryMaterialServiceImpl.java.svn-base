/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sram.dao.OutlineCategoryMaterialDao;
import com.sram.entity.OutlineCategoryMaterial;
import com.sram.service.OutlineCategoryMaterialService;

/**
 * Service - 大纲资料
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("outlineCategoryMaterialServiceImpl")
public class OutlineCategoryMaterialServiceImpl extends
		BaseServiceImpl<OutlineCategoryMaterial, Long> implements OutlineCategoryMaterialService {

	@Resource(name = "outlineCategoryMaterialDaoImpl")
	private OutlineCategoryMaterialDao outlineCategoryMaterialDao;

	@Resource(name = "outlineCategoryMaterialDaoImpl")
	public void setBaseDao(OutlineCategoryMaterialDao outlineCategoryMaterialDao) {
		super.setBaseDao(outlineCategoryMaterialDao);
	}

	public void saveOutlineCategoryMaterials(List<OutlineCategoryMaterial> outlineCategoryMaterials) {
		for (OutlineCategoryMaterial outlineCategoryMaterial: outlineCategoryMaterials) {
			super.save(outlineCategoryMaterial);
		}
	}
	@Transactional(readOnly=true)
	public List<OutlineCategoryMaterial> findList(Long outlineCategoryId) {
		return outlineCategoryMaterialDao.findList(outlineCategoryId);
	}

}