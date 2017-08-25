/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao.impl;

import org.springframework.stereotype.Service;

import com.sram.dao.UploadFilesDao;
import com.sram.entity.UploadFiles;

/**
 * Dao - 上传文件
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("uploadFilesDaoImpl")
public class UploadFilesDaoImpl extends BaseDaoImpl<UploadFiles, Long>
		implements UploadFilesDao {

}