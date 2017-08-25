package com.sram.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sram.Message;
import com.sram.entity.UploadFiles;
import com.sram.entity.UploadFiles.ConvertStatus;
import com.sram.service.FileService;
import com.sram.service.UploadFilesService;
import com.sram.util.JsonUtils;
import com.sram.util.TokenGeneratorUtils;

@Controller("uploadFilesController")
@RequestMapping("/uploadFiles")
public class UploadFilesController extends BaseController {

	@Resource(name = "fileServiceImpl")
	private FileService fileService;

	@Resource(name = "uploadFilesServiceImpl")
	private UploadFilesService uploadFilesService;

	private int chunksCount = 0; // 上传文件计数器，用来判断多个文件上传是否是第一个文件

	/**
	 * 转到上传页面
	 * 
	 * @param tagetType
	 * @param targetId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toUpload", method = RequestMethod.GET)
	public String upload(String userId, String userName, String targetType,
			Long targetId, String fileType, ModelMap model) {
		model.addAttribute("userId", userId);
		model.addAttribute("targetType", targetType);
		model.addAttribute("targetId", targetId);

		if (userName != null) {
			model.addAttribute("token",
					TokenGeneratorUtils.generatorToken(userName));
		}
		if (null != fileType) {
			model.addAttribute("fileType", fileType);
		}

		return "/upload";
	}

	/**
	 * 转到多文件上传页面
	 * 
	 * @param tagetType
	 * @param targetId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toUploadMultifile", method = RequestMethod.GET)
	public String uploadMultilfile(String userId, String userName,
			String targetType, Long targetId, String fileType, ModelMap model) {
		model.addAttribute("userId", userId);
		model.addAttribute("targetType", targetType);
		model.addAttribute("targetId", targetId);

		if (userName != null) {
			model.addAttribute("token",
					TokenGeneratorUtils.generatorToken(userName));
		}
		if (null != fileType) {
			model.addAttribute("fileType", fileType);
		}

		return "/uploadMultifile";
	}

	/**
	 * 多个文件上传
	 * 
	 * @param targetId
	 * @param targetType
	 * @param userId
	 * @param name
	 * @param chunk
	 * @param chunks
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/upload")
	public void upload(Long targetId, String targetType, Long userId,
			String token, String name, Integer chunk, Integer chunks,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// 首先验证token
		boolean isValidateToken = TokenGeneratorUtils.validateToken(token);
		Map<String, Object> data = new HashMap<String, Object>();
		if (((null != chunks && chunk == 0) || null == chunks)
				&& !isValidateToken && chunksCount == 0) { // 不分块验证 或者分块第一次验证
			try {
				data.put("message", Message.error("没有上传权限"));
				response.setContentType("text/html; charset=UTF-8");
				JsonUtils.writeValue(response.getWriter(), data);
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		chunksCount++;//更改计数器，多文件上传时不需要验证后面的文件
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = mRequest.getFileMap();
		for (String key : fileMap.keySet()) {
			MultipartFile file = fileMap.get(key);
			File tempFile = null;
			if (null != chunks && chunks > 1) {// 分块上传
				String tempFilePath = System.getProperty("java.io.tmpdir")
						+ "/upload_" + name + ".tmp";
				tempFile = new File(tempFilePath);

				if (!tempFile.getParentFile().exists()) {
					tempFile.getParentFile().mkdirs();
				}
				// 文件已存在（上传了同名的文件）
				if (chunk == 0 && tempFile.exists()) {
					tempFile.delete();
					tempFile = new File(tempFilePath);
				}
				fileService.copy(file, tempFile);

				if (chunk == chunks - 1) {
					try {
						data = mediaInfo(tempFile, targetId, targetType,
								userId, name);
						response.setContentType("text/html; charset=UTF-8");
						JsonUtils.writeValue(response.getWriter(), data);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} else {// 整个上传

				String tempFilePath = System.getProperty("java.io.tmpdir")
						+ "/upload_" + UUID.randomUUID() + ".tmp";
				tempFile = new File(tempFilePath);
				if (!tempFile.getParentFile().exists()) {
					tempFile.getParentFile().mkdirs();
				}
				file.transferTo(tempFile);

				try {
					data = mediaInfo(tempFile, targetId, targetType, userId,
							name);
					response.setContentType("text/html; charset=UTF-8");
					JsonUtils.writeValue(response.getWriter(), data);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@RequestMapping(value = "/uploadTest")
	public void uploadTest(MultipartFile file, String name, Integer chunk,
			Integer chunks, HttpServletResponse response) {

		String tempFilePath = System.getProperty("java.io.tmpdir") + "/upload_"
				+ name + ".tmp";
		File tempFile = new File(tempFilePath);

		if (!tempFile.getParentFile().exists()) {
			tempFile.getParentFile().mkdirs();
		}
		System.out.println(tempFilePath);
		// 文件已存在（上传了同名的文件）
		if (chunk == 0 && tempFile.exists()) {
			tempFile.delete();
			tempFile = new File(tempFilePath);
		}
		fileService.copy(file, tempFile);

	}

	private Map<String, Object> mediaInfo(File tempfile, Long targetId,
			String targetType, Long userId, String originalFilename)
			throws Exception {
		Map<String, Object> data = new HashMap<String, Object>();

		UploadFiles uploadFiles = new UploadFiles();
		uploadFiles.setTargetId(targetId);
		uploadFiles.setTargetType(targetType);
		String extensionName = FilenameUtils.getExtension(originalFilename);
		uploadFiles.setFilename(originalFilename);
		uploadFiles.setExt(extensionName);
		uploadFiles.setIsPublic(false);
		uploadFiles.setCanDownload(false);
		uploadFiles.setUpdatedUserId(userId);
		uploadFiles.setCreateUserId(userId);
		uploadFiles.setConvertStatus(ConvertStatus.waiting);

		// 设置大小默认是0
		uploadFiles.setSize(tempfile.length());

		uploadFilesService.save(uploadFiles);

		try {
			String mediaDurationInfo = fileService.upload(tempfile,
					originalFilename, true, uploadFiles);
			data.put("mediaId", uploadFiles.getId());
			data.put("mediaName", originalFilename);
			data.put("duration", mediaDurationInfo);
			data.put("message", SUCCESS_MESSAGE);
		} catch (Exception e) {
			data.put("message", Message.warn("admin.upload.error"));
		}

		return data;
	}

}
