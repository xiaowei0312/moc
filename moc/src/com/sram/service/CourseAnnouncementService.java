package com.sram.service;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.Course;
import com.sram.entity.CourseAnnouncement;


/*
 * 课程公告
 */
public interface CourseAnnouncementService extends BaseService<CourseAnnouncement, Long>{
    /*
     * 查找courseId对应的所有公告
     */
	Page<CourseAnnouncement> findPage(Course course, Pageable pageable);

}
