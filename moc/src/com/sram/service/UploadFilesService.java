/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.UploadFiles;

/**
 * Service - 上传文件
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface UploadFilesService extends BaseService<UploadFiles, Long> {

	/**
	 * 
	 * @param targetType---courselesson/coursematerail
	 * @param targetId---courseId
	 * @param fileType
	 * @return
	 */
	public List<UploadFiles> findUploadFilesByTypeAndId(String targetType, Long targetId,String fileType);
	

	public Page<UploadFiles> findPage(Long targetId,Pageable pageable,String targetType);
	/**
	 * 根据目标对象ID，目标类型查询所有文件
	 * @param targetId
	 * @param targetType
	 * @return
	 */
	public List<UploadFiles> findALLByTarget(Long targetId,	String targetType);

	/**
	 * 根据条件查找uploadfiles
	 * 0--id,1--文件名称,2--文件类型,3--大小,4--文件状态,5--上传人,6--更新时间,7--所属课程
	 * @param courseName
	 * @param courseCategoryId
	 * @param targetId
	 * @param pageable
	 * @param targetType
	 * @return
	 */
	public Page<Object[]> findPage(String courseName, Long courseCategoryId,
			Long targetId, Pageable pageable, String targetType);
	
}