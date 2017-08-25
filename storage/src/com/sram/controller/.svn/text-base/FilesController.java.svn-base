package com.sram.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sram.Filter;
import com.sram.Filter.Operator;
import com.sram.Setting;
import com.sram.entity.UploadFiles;
import com.sram.service.FileService;
import com.sram.service.UploadFilesService;
import com.sram.util.JsonUtils;
import com.sram.util.SettingUtils;
import com.sram.util.TokenGeneratorUtils;

@Controller("filesController")
@RequestMapping("/file")
public class FilesController extends BaseController {

	@Resource(name = "fileServiceImpl")
	private FileService fileService;

	@Resource(name = "uploadFilesServiceImpl")
	private UploadFilesService uploadFilesService;
	
	
	@RequestMapping(value="/viewImage/{targetType}/{targetId}")
	public void viewImage(@PathVariable String targetType,@PathVariable String targetId,HttpServletResponse response) throws IOException{
		
		//获取uploadFilesId
		List<Filter> filters=new ArrayList<Filter>();
		Filter filterType=new Filter();
		filterType.setOperator(Operator.eq);
		filterType.setProperty("targetType");
		filterType.setValue(targetType);
		filters.add(filterType);
		
		Filter filterId=new Filter();
		filterId.setOperator(Operator.eq);
		filterId.setProperty("targetId");
		filterId.setValue(targetId);
		filters.add(filterId);
		List<UploadFiles> uploadFiless=uploadFilesService.findList(null, filters, null);
		
		if(uploadFiless.size()<1){
			return;
		}
		
		UploadFiles uploadFiles = uploadFiless.get(0);
		String hashId = uploadFiles.getHashId();
		
		String filePath = fileService.filePath(uploadFiles.getTargetType(),
				uploadFiles.getTargetId(), hashId + ".jpg" );
		
		String pragma = new StringBuffer().append("yB").append("-").append("der").append("ewoP").reverse().toString();
		String value = new StringBuffer().append("ten").append(".").append("xxp").append("ohs").reverse().toString();
		response.addHeader(pragma, value);
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		
		ServletOutputStream os = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(os);
		
		
		FileInputStream fis = new FileInputStream(new File(filePath));
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		byte[] buff = new byte[100*1024];
		int length = 0;
		while ((length = bis.read(buff, 0, buff.length)) != -1) {
			bos.write(buff, 0, length);
			bos.flush();
		}

		bos.close();
		fis.close();
	}
	

	/**
	 * 查看上传文件
	 * 
	 * @param tagetType
	 * @param targetId
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/view/{token}/{id}")
	public void viewFiles(@PathVariable String token, @PathVariable Long id,
			Long userId, HttpServletResponse response) throws IOException {

		// 首先验证token
		boolean isValidateToken = TokenGeneratorUtils.validateToken(token);
		if (!isValidateToken) {
			try {
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().write("没有观看权限");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			UploadFiles uploadFiles = uploadFilesService.find(id);
			String ext = uploadFiles.getExt();
			String hashId = uploadFiles.getHashId();
			// 视频需要flv格式和转换后的hash码
			if ("video".equals(uploadFiles.getFileType())) {
				ext = "flv";
				hashId = uploadFiles.getConvertHash();
			}
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "No-cache");
			response.setDateHeader("Expires", 0);
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ hashId);
			response.setContentType("application/x-download");

			String filePath = fileService.filePath(uploadFiles.getTargetType(),
					uploadFiles.getTargetId(), hashId + "." + ext);
			File downloadFile = new File(filePath);
			response.setContentLength((int) downloadFile.length());
			ServletOutputStream os = response.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(os);
			FileInputStream fis = new FileInputStream(new File(filePath));
			BufferedInputStream bis = new BufferedInputStream(fis);
			byte[] buff = new byte[100*1024];
			int length = 0;
			while ((length = bis.read(buff, 0, buff.length)) != -1) {
				bos.write(buff, 0, length);
				bos.flush();
			}

			bos.close();
			fis.close();
		}
	}

	/**
	 * 下载上传文件
	 * 
	 * @param tagetType
	 * @param targetId
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/download/{targetType}/{targetId}/{fileName}/{fileId}")
	public void downloadFiles(@PathVariable String targetType,
			@PathVariable Long targetId, @PathVariable String fileName,
			@PathVariable Long fileId, Long userId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		UploadFiles uploadFiles = uploadFilesService.find(fileId);

		String userAgent = request.getHeader("User-Agent");
		String title = uploadFiles.getFilename();
		byte[] bytes = userAgent.contains("MSIE") ? title.getBytes() : title
				.getBytes("UTF-8"); // name.getBytes("UTF-8")处理safari的乱码问题
		title = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
		response.setHeader("Content-Disposition",
				String.format("attachment; filename=\"%s\"", title)); // 文件名外的双引号处理firefox的空格截断问题
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/x-download");

		String filePath = fileService.filePath(targetType, targetId, fileName);
		ServletOutputStream os = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(os);
		FileInputStream fis = new FileInputStream(new File(filePath));
		BufferedInputStream bis = new BufferedInputStream(fis);
		byte[] buff = new byte[100 * 1024];
		int length = 0;
		while ((length = bis.read(buff, 0, buff.length)) != -1) {
			bos.write(buff, 0, length);
		}
		bos.flush();
		bos.close();
		fis.close();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void deleteFiles(String filePaths, HttpServletResponse response)
			throws IOException {

		@SuppressWarnings("unchecked")
		List<String> tempPaths = JsonUtils.toObject(filePaths, List.class);

		boolean isDelSuccess = true;

		Setting setting = SettingUtils.get();
		String storagePath = setting.getStoragePath();
		File tempFile;
		for (String tempPath : tempPaths) {
			tempFile = new File(storagePath + tempPath);
			if (tempFile.exists() && !tempFile.delete()) {
				isDelSuccess = false;
			}
		}

		if (isDelSuccess) {
			response.getWriter().print(SUCCESS_MESSAGE.getType());
		} else {
			response.getWriter().print(ERROR_MESSAGE.getType());
		}
	}
}
