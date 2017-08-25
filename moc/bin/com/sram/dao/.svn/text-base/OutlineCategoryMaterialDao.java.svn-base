/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao;

import java.util.List;

import com.sram.entity.OutlineCategoryMaterial;

/**
 * Dao - 大纲资料文件
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface OutlineCategoryMaterialDao extends BaseDao<OutlineCategoryMaterial, Long> {


	/**
	 * 查找出courseId下所有的资料--课时下的，课程下的---
	 * @param courseId
	 * @param targetType---courselesson（视频），OutlineCategoryMaterial（资料）
	 * @param pageable
	 * @return
	 */
	public List<OutlineCategoryMaterial>  findList(Long outlineCategoryId);
}