package com.sram.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.AdminDao;
import com.sram.dao.CourseAnnouncementDao;
import com.sram.entity.Course;
import com.sram.entity.CourseAnnouncement;
import com.sram.service.CourseAnnouncementService;


/*
 * 课程公告
 */
@Service("courseAnnouncementServiceImpl")
public class CourseAnnouncementServiceImpl extends BaseServiceImpl<CourseAnnouncement,Long>  implements CourseAnnouncementService,DisposableBean{
    
	@Resource(name="courseAnnouncementDaoImpl")
	private CourseAnnouncementDao courseAnnouncementDao;
	
	@Resource(name = "courseAnnouncementDaoImpl")
	public void setBaseDao(CourseAnnouncementDao courseAnnouncementDao) {
		super.setBaseDao(courseAnnouncementDao);
	}

	
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public Page<CourseAnnouncement> findPage(Course course, Pageable pageable) {
		// TODO Auto-generated method stub
		return courseAnnouncementDao.findPage(course,pageable);
	}

}
