/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.CourseMaterialDao;
import com.sram.entity.CourseLesson;
import com.sram.entity.CourseMaterial;
import com.sram.service.CourseMaterialService;

/**
 * Service - 课程资料
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("courseMaterialServiceImpl")
public class CourseMaterialServiceImpl extends
		BaseServiceImpl<CourseMaterial, Long> implements CourseMaterialService {

	@Resource(name = "courseMaterialDaoImpl")
	private CourseMaterialDao courseMaterialDao;

	@Resource(name = "courseMaterialDaoImpl")
	public void setBaseDao(CourseMaterialDao courseMaterialDao) {
		super.setBaseDao(courseMaterialDao);
	}

	public List<CourseMaterial> findListByLessonId(Long lessonId,Long courseId) {
		return courseMaterialDao.findListByLessonId(lessonId,courseId);
	}

	public void saveCourseMaterials(List<CourseMaterial> courseMaterials) {
		for (CourseMaterial courseMaterial : courseMaterials) {
			super.save(courseMaterial);
		}
	}
	@Transactional(readOnly=true)
	public List<CourseMaterial> findList(Long courseId) {
		
		return courseMaterialDao.findList(courseId);
	}

}