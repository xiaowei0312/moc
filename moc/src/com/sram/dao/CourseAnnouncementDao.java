package com.sram.dao;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.Course;
import com.sram.entity.CourseAnnouncement;


/*
 * 课程公告
 */
public interface CourseAnnouncementDao extends BaseDao<CourseAnnouncement,Long>{

	Page<CourseAnnouncement> findPage(Course course, Pageable pageable);

}
