/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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
	public void setBaseDao(UploadFilesDao uploadFilesDao) {
		super.setBaseDao(uploadFilesDao);
	}

}