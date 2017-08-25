/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.sram.Setting;
import com.sram.entity.UploadFiles;
import com.sram.entity.UploadFiles.ConvertStatus;
import com.sram.service.FileService;
import com.sram.service.UploadFilesService;
import com.sram.util.DateUtils;
import com.sram.util.FfmpegUtils;
import com.sram.util.SettingUtils;

/**
 * Service - 文件
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("fileServiceImpl")
public class FileServiceTestImpl implements FileService, ServletContextAware {

	/** servletContext */
	private ServletContext servletContext;

	@Resource(name = "taskExecutor")
	private TaskExecutor taskExecutor;

	@Resource(name = "uploadFilesServiceImpl")
	private UploadFilesService uploadFilesService;

	private static final int BUFFER_SIZE = 2 * 1024;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	/**
	 * 添加上传视频转换任务
	 * 
	 * @param path
	 *            上传路径
	 * @param tempFile
	 *            临时文件
	 * @param extensionName
	 * @param contentType
	 *            文件类型
	 */
	private void addVideoTask(final String path, final File tempFile,
			final UploadFiles uploadFiles) {
		taskExecutor.execute(new Runnable() {
			public void run() {
				try {
					// 移动文件
					String inputFile = upload(path, tempFile);
					String extensionName = path.substring(path.lastIndexOf(".") + 1);
					String hashId = path.substring(
							path.lastIndexOf(File.separator) + 1,
							path.lastIndexOf("."));
					String destPath = path.substring(0,
							path.lastIndexOf(File.separator) + 1);
					Setting setting = SettingUtils.get();
					if ("flv".equals(extensionName)) {// flv格式的文件不需要转换格式
						uploadFiles.setConvertHash(hashId);
						uploadFiles.setConvertStatus(ConvertStatus.success);
					} else {
						// 转换文件格式
						String formatDate = DateUtils.format(new Date(),
								DateUtils.TYPE_DATETIME,
								DateUtils.FORMAT_DATETIME2);
						String convertFileName = formatDate
								+ UUID.randomUUID().toString();
						// String outputFile = servletContext
						// .getRealPath(destPath);

						
						String outputFile = setting.getStoragePath() + destPath
								+ convertFileName + ".flv";

						boolean convertStatus = FfmpegUtils.convert(inputFile,
								outputFile);
						// 转换成功，更新UploadFiles表
						if (convertStatus) {
							uploadFiles.setConvertHash(convertFileName);
							uploadFiles.setConvertStatus(ConvertStatus.success);
						} else {
							uploadFiles.setConvertStatus(ConvertStatus.error);
						}
					}
					//抓取一帧图片
					String imageFile = setting.getStoragePath() + destPath + hashId + ".jpg";
					FfmpegUtils.catchImage(inputFile, imageFile);
					uploadFilesService.update(uploadFiles);
				} finally {
					FileUtils.deleteQuietly(tempFile);
				}
			}
		});
	}

	private void addTask(final String path, final File tempFile,
			final UploadFiles uploadFiles) {
		taskExecutor.execute(new Runnable() {
			public void run() {
				try {
					// 移动文件
					upload(path, tempFile);
					uploadFiles.setConvertStatus(ConvertStatus.success);
					uploadFilesService.update(uploadFiles);
				} finally {
					FileUtils.deleteQuietly(tempFile);
				}
			}
		});
	}

	public String upload(File tempFile, String originalFilename, boolean async,
			UploadFiles uploadFiles) throws Exception {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("uuid", UUID.randomUUID().toString());

		String formatDate = DateUtils.format(new Date(),
				DateUtils.TYPE_DATETIME, DateUtils.FORMAT_DATETIME2);
		String hashId = formatDate + UUID.randomUUID();
		String extensionName = FilenameUtils.getExtension(originalFilename);
		// String destPath = "upload" + File.separator
		String destPath = File.separator + uploadFiles.getTargetType()
				+ File.separator + uploadFiles.getTargetId() + File.separator
				+ hashId + "." + extensionName;

		//
		Setting setting = SettingUtils.get();
		String[] videoExtensions = setting.getUploadVideoExtensions();
		String[] audioExtensions = setting.getUploadAudioExtensions();
		String[] pptExtensions = setting.getUploadPPTExtensions();

		// 获取视频时长
		String mediaDuration = "";

		if (async) {
			uploadFiles.setHashId(hashId);
			uploadFiles.setConvertStatus(ConvertStatus.doing);
			// 如果是视频的话
			if (FilenameUtils.isExtension(originalFilename, videoExtensions)) {
				mediaDuration = FfmpegUtils.videoDurationInfo(tempFile
						.getCanonicalPath());
				uploadFiles.setFileType("video");
				uploadFiles.setLength(calculateDuration(mediaDuration));
				uploadFilesService.update(uploadFiles);
				addVideoTask(destPath, tempFile, uploadFiles);
			} else if (FilenameUtils.isExtension(originalFilename,
					audioExtensions)) {
				mediaDuration = FfmpegUtils.videoDurationInfo(tempFile
						.getCanonicalPath());
				uploadFiles.setFileType("audio");
				uploadFiles.setLength(calculateDuration(mediaDuration));
				uploadFilesService.update(uploadFiles);
				addTask(destPath, tempFile, uploadFiles);
			} else if (FilenameUtils.isExtension(originalFilename,
					pptExtensions)) {
				uploadFiles.setFileType("ppt");
				uploadFiles.setLength(0);
				uploadFilesService.update(uploadFiles);
				addTask(destPath, tempFile, uploadFiles);
			} else {
				uploadFiles.setFileType("other");
				uploadFiles.setLength(0);
				uploadFilesService.update(uploadFiles);
				addTask(destPath, tempFile, uploadFiles);
			}

		} else {
			try {
				upload(destPath, tempFile);
			} finally {
				FileUtils.deleteQuietly(tempFile);
			}
		}
		return mediaDuration;

	}

	public void copy(MultipartFile file, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			if (dst.exists()) {
				out = new BufferedOutputStream(new FileOutputStream(dst, true),
						BUFFER_SIZE);
			} else {
				out = new BufferedOutputStream(new FileOutputStream(dst),
						BUFFER_SIZE);
			}
			in = new BufferedInputStream(file.getInputStream(), BUFFER_SIZE);

			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private String upload(String path, File file) {
		// data---data2---data3
		// String destPath = servletContext.getRealPath(path);
		Setting setting = SettingUtils.get();
		String destPath = setting.getStoragePath() + path;
		File destFile = new File(destPath);

		try {
			FileUtils.moveFile(file, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return destPath;
	}

	private Integer calculateDuration(String duration) {
		String[] arrTime = duration.split(":");
		Integer hour = Integer.parseInt(arrTime[0]) * 60 * 60;
		Integer minute = Integer.parseInt(arrTime[1]) * 60;
		Integer seconds = Integer.parseInt(arrTime[2].substring(0, 2));
		return hour + minute + seconds;
	}

	public String filePath(String targetType, Long targetId, String fileName) {
		// String realPath = servletContext.getRealPath(File.separator +
		// "upload");
		Setting setting = SettingUtils.get();
		String storagePath = setting.getStoragePath();
		return storagePath + File.separator + targetType + File.separator
				+ targetId + File.separator + fileName;
	}

}