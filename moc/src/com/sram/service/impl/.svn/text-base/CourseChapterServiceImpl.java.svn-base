package com.sram.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sram.dao.CourseChapterDao;
import com.sram.dao.CourseLessonDao;
import com.sram.entity.CourseChapter;
import com.sram.entity.CourseLesson;
import com.sram.service.CourseChapterService;

@Service("courseChapterServiceImpl")
public class CourseChapterServiceImpl extends
		BaseServiceImpl<CourseChapter, Long> implements CourseChapterService {
	@Resource(name = "courseChapterDaoImpl")
	private CourseChapterDao courseChapterDao;
	
	@Resource(name = "courseLessonDaoImpl")
	private CourseLessonDao courseLessonDao;

	@Resource(name = "courseChapterDaoImpl")
	public void setBaseDao(CourseChapterDao courseChapterDao) {
		super.setBaseDao(courseChapterDao);
	}

	public List<CourseChapter> findRoots(Long courseId) {
		// TODO Auto-generated method stub
		return courseChapterDao.findRoots(courseId);
	}

	public List<CourseChapter> findUnit(CourseChapter courseChapter) {
		// TODO Auto-generated method stub
		return courseChapterDao.findUnit(courseChapter);
	}

	@Transactional(readOnly = true)
	public List<CourseChapter> findChaptersByCourse(Long courseId) {
		// TODO Auto-generated method stub
		return courseChapterDao.findChaptersByCourse(courseId);
	}
	@Transactional(readOnly = true)
	public void update(List<CourseChapter> chapters) {
		for (CourseChapter chapter : chapters) {
			super.update(chapter);
		}

	}
	@Transactional
	public void save(CourseChapter courseChapter, Long parentId) {
		Long courseId = courseChapter.getCourse().getId();
		// 添加章
		if (courseChapter.getType() == CourseChapter.Type.chapter) {
			Integer number = courseChapterDao.getNextChapterNumber(courseId);
			courseChapter.setNumber(number + 1);
		} else {
			CourseChapter parent = courseChapterDao.find(parentId);
			courseChapter.setNumber(parent.getNumber());
		}
		Integer order = courseChapterDao.getNextCourseItemOrder(courseId);
		courseChapter.setOrder(order + 1);
		super.save(courseChapter);
	}

	@Transactional
	public void delete(Long id, String ids) {

		CourseChapter tempCourseChapter = courseChapterDao.find(id);
		sort(ids);

		super.delete(tempCourseChapter);
	}

	@Transactional
	public void sort(String ids) {
		System.out.println(ids);
		String[] strIds = ids.split(",");
		CourseChapter currentCourseChapter = null;
		CourseLesson currentCourseLesson = null;
		Integer number = 0, order = 0;
		for (int i = 0; i < strIds.length; i++) {
			String[] typeIds = strIds[i].split("-");
			String type = typeIds[0];
			Long id = Long.parseLong(typeIds[1]);
			order++;
			if ("chapter".equals(type)) {
				//持久化章节
				CourseChapter persistCourseChapter = courseChapterDao.find(id);

				if (persistCourseChapter.getType() == CourseChapter.Type.chapter)
					number++;

				
				persistCourseChapter.setOrder(order);
				persistCourseChapter.setNumber(number);
				currentCourseChapter = persistCourseChapter;
				super.update(persistCourseChapter);
				courseChapterDao.flush();
			}else if("lesson".equals(type)){
				
				currentCourseLesson = courseLessonDao.find(id);
				currentCourseLesson.setCourseChapter(currentCourseChapter);
				currentCourseLesson.setOrder(order);
				courseChapterDao.refresh(currentCourseChapter);
			}

			
		}
	}
}
