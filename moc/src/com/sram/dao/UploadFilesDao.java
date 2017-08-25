/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.UploadFiles;

/**
 * Dao - 上传文件
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface UploadFilesDao extends BaseDao<UploadFiles, Long> {

	public List<UploadFiles> findUploadFilesByTypeAndId(String targetType,
			Long targetId, String fileType);

	public Page<UploadFiles> findPage(Long targetId, Pageable pageable,String targetType);
	
	public List<UploadFiles> findALLByTarget(Long targetId,	String targetType);

	public Page<Object[]> findPage(String courseName, Long courseCategoryId,
			Long targetId, Pageable pageable, String targetType);

}