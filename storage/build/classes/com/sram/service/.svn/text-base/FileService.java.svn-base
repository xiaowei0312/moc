/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.sram.entity.UploadFiles;

/**
 * Service - 文件
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface FileService {

	public void copy(MultipartFile file, File dst);

	/**
	 * 文件上传
	 * 
	 * @param fileType
	 *            文件类型
	 * @param tempFile
	 * @param originalFilename
	 * @param async
	 * @param uploadFiles
	 * @return 返回媒介时长
	 * @throws Exception
	 */
	public String upload(File tempFile, String originalFilename, boolean async,
			UploadFiles uploadFiles) throws Exception;

	/**
	 * 获取文件路径
	 * @param targetType
	 * @param targetId
	 * @param fileName
	 * @return
	 */
	public String filePath(String targetType, Long targetId, String fileName);

}