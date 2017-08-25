/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.sram.Setting;
import com.sram.entity.Course;
import com.sram.service.CourseImageService;
import com.sram.util.FreemarkerUtils;
import com.sram.util.ImageUtils;
import com.sram.util.SettingUtils;

/**
 * Service - 课程图片
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("courseImageServiceImpl")
public class CourseImageServiceImpl implements CourseImageService,
		ServletContextAware {

	/** 目标扩展名 */
	private static final String DEST_EXTENSION = "jpg";
	/** 目标文件类型 */

	/** servletContext */
	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	/**
	 * 删除课程图片
	 * @param course
	 */
	public void deleteCourseImage(Course course){
		//---/moc
		String contextPath = servletContext.getContextPath();
		
		//工程名的绝对路径
		String realPath = servletContext.getRealPath("/");
		
		//获取图片的虚拟路径
		String sourcePath = course.getSourceImage();
		String largePath = course.getLargeImage();
		String middlePath = course.getMediumImage();
		String thumbnailPath = course.getThumbnailImage();
		String uploadPath = null;
		File tempFile=null;
		if(sourcePath !=null && !sourcePath.isEmpty()){
			
			//获取原图片工程名以后的位置
			String tempPath = sourcePath.substring(sourcePath.lastIndexOf(contextPath)+contextPath.length());
			
			//原图片完整的物理位置
			uploadPath = realPath+tempPath;
			tempFile = new File(uploadPath);
			tempFile.delete();
		}
		
		if(largePath!=null && !largePath.isEmpty()){
			
			//获取原图片工程名以后的位置
			String tempPath = largePath.substring(largePath.lastIndexOf(contextPath)+contextPath.length());
			
			//原图片完整的物理位置
			uploadPath = realPath+tempPath;
			tempFile = new File(uploadPath);
			tempFile.delete();
		}
		if(middlePath!=null && !middlePath.isEmpty()){
			
			//获取原图片工程名以后的位置
			String tempPath = middlePath.substring(middlePath.lastIndexOf(contextPath)+contextPath.length());
			
			//原图片完整的物理位置
			uploadPath = realPath+tempPath;
			tempFile = new File(uploadPath);
			tempFile.delete();
		}
		if(thumbnailPath!=null && !thumbnailPath.isEmpty()){
			
			//获取原图片工程名以后的位置
			String tempPath = thumbnailPath.substring(thumbnailPath.lastIndexOf(contextPath)+contextPath.length());
			
			//原图片完整的物理位置
			uploadPath = realPath+tempPath;
			tempFile = new File(uploadPath);
			tempFile.delete();
		}
		
		
	}
	public void build(Course course, String sourcePath) {
		try {

			Setting setting = SettingUtils.get();
			String realPath = servletContext.getRealPath("/");
			String contextPath = servletContext.getContextPath();
			
			//原图片的物理路径
			String sourceImage = realPath+
			sourcePath.substring(sourcePath.lastIndexOf(contextPath)+contextPath.length());
			
			//上传路径--自工程名后的路径
			String middlePath = setting.getImageUploadPath();
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("uuid", UUID.randomUUID().toString());
			String path = FreemarkerUtils.process(middlePath, model);
			
			//上传图片的物理路径
			String uploadPath = realPath+path;
			
			//原图片的ip及端口号
			String idport = sourcePath.substring(0,sourcePath.lastIndexOf(contextPath));
			
			//上传图片的虚拟路径
			String virtualPath = idport+contextPath+path;

			String uuid = UUID.randomUUID().toString();
			String largePath = uploadPath + uuid + "-large." + DEST_EXTENSION;
			String mediumPath = uploadPath + uuid + "-medium." + DEST_EXTENSION;
			String thumbnailPath = uploadPath + uuid + "-thumbnail."
					+ DEST_EXTENSION;

			// 缩放图片
			File sourceFile = new File(sourceImage);
			File largeTempFile = new File(largePath);
			File mediumTempFile = new File(mediumPath);
			File thumbnailTempFile = new File(thumbnailPath);
			ImageUtils.zoom(sourceFile, largeTempFile,
					setting.getLargeCourseImageWidth(),
					setting.getLargeCourseImageHeight());
			ImageUtils.zoom(sourceFile, mediumTempFile,
					setting.getMediumCourseImageWidth(),
					setting.getMediumCourseImageHeight());
			ImageUtils.zoom(sourceFile, thumbnailTempFile,
					setting.getThumbnailCourseImageWidth(),
					setting.getThumbnailCourseImageHeight());

			// 设置属性
			String largeVirtualPath = virtualPath + uuid + "-large."
					+ DEST_EXTENSION;
			String mediumVirtualPath = virtualPath + uuid + "-medium."
					+ DEST_EXTENSION;
			String thumbnailVirtualPath = virtualPath + uuid + "-thumbnail."
					+ DEST_EXTENSION;

			course.setLargeImage(largeVirtualPath);
			course.setMediumImage(mediumVirtualPath);
			course.setThumbnailImage(thumbnailVirtualPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}