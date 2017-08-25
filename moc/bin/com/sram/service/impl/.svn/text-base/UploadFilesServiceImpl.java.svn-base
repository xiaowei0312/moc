/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.UploadFilesDao;
import com.sram.entity.UploadFiles;
import com.sram.service.UploadFilesService;

/**
 * Service - 上传文件
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("uploadFilesServiceImpl")
public class UploadFilesServiceImpl extends BaseServiceImpl<UploadFiles, Long>
		implements UploadFilesService {

	@Resource(name = "uploadFilesDaoImpl")
	private UploadFilesDao uploadFilesDao;

	@Resource(name = "uploadFilesDaoImpl")
	public void setBaseDao(UploadFilesDao uploadFilesDao) {
		super.setBaseDao(uploadFilesDao);
	}

	public List<UploadFiles> findUploadFilesByTypeAndId(String targetType,
			Long targetId, String fileType) {
		return uploadFilesDao.findUploadFilesByTypeAndId(targetType, targetId,
				fileType);
	}

	public Page<UploadFiles> findPage(Long targetId, Pageable pageable,
			String targetType) {
		return uploadFilesDao.findPage(targetId, pageable, targetType);
	}
	public List<UploadFiles> findALLByTarget(Long targetId,	String targetType) {
		return uploadFilesDao.findALLByTarget(targetId,targetType);
	}

	public Page<Object[]> findPage(String courseName, Long courseCategoryId,
			Long targetId, Pageable pageable, String targetType) {
		return uploadFilesDao.findPage(courseName,courseCategoryId
				,targetId,pageable,targetType);
	}
}