package com.sram.service.impl;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import com.sram.entity.Member;
import com.sram.service.MemberImageService;


/**
 * 会员图片
 * @author limin
 *
 */
@Service("memberImageServiceImpl")
public class MemberImageServiceImpl implements MemberImageService,ServletContextAware{
	
	/** 目标扩展名 */
	private static final String DEST_EXTENSION = "jpg";
	/** 目标文件类型 */

	/** servletContext */
	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public void deleteMemberHeadImage(Member member) {
		// TODO Auto-generated method stub
		//---/moc
		String contextPath = servletContext.getContextPath();
		
		//工程名的绝对路径
		String realPath = servletContext.getRealPath("/");
		//获得会员头像虚拟路径
		String headImagePath=member.getHeadImage();
		
		String uploadPath = null;
		File tempFile=null;
		if(headImagePath!=null && !("").equals(headImagePath)){
			//获取原图片工程名以后的位置
			String tempPath = headImagePath.substring(headImagePath.lastIndexOf(contextPath)+contextPath.length());
			
			//原图片完整的物理位置
			uploadPath = realPath+tempPath;
			tempFile = new File(uploadPath);
			tempFile.delete();
		}
	}

}
