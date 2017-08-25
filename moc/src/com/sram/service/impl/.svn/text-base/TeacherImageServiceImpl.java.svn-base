/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service.impl;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.sram.entity.Teacher;
import com.sram.service.TeacherImageService;

/**
 * Service - 讲师头像处理
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("teacherImageServiceImpl")
public class TeacherImageServiceImpl implements TeacherImageService,
		ServletContextAware {

	/** servletContext */
	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	/**
	 * 删除讲师头像
	 * @param course
	 */
	public void deleteTeacherImage(Teacher teacher){
		
		//---/moc
		String contextPath = servletContext.getContextPath();
		
		//获取图片的虚拟路径（相对路径）----原图片的名字
		String sourcePath = teacher.getImage();
		if(sourcePath!=null&& !sourcePath.isEmpty()){
			
			//获取原图的名称
			String sourceName = sourcePath.substring(sourcePath.lastIndexOf("/")+1);
			
			//获取图片上传路径自工程名以后的路径
			String uploadMiddlePath = sourcePath.substring(
					sourcePath.lastIndexOf(contextPath)+contextPath.length());
			
			//判断图片是不是放在工程名下
			int temp =uploadMiddlePath.lastIndexOf("/");
			if(temp!=(-1)){
				uploadMiddlePath = uploadMiddlePath.substring(1, temp+1)
				.replace("/", "\\");
			}
			
			//上传图片的物理路径（绝对路径）--realPath获得的是工程名的绝对路径
			String uploadPath = servletContext.getRealPath("/")+uploadMiddlePath;
			uploadPath=uploadPath.replace("\\", "//");
			
			//获取课程所有图片的物理路径
			String uploadSource = uploadPath + sourceName;
			File sourFile = new File(uploadSource);
			sourFile.delete();
		}
		
	}
}