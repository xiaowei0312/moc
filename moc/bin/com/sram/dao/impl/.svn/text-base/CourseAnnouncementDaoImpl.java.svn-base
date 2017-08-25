package com.sram.dao.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.CourseAnnouncementDao;
import com.sram.entity.Course;
import com.sram.entity.CourseAnnouncement;


/*
 * 课程公告
 */
@Repository("courseAnnouncementDaoImpl")
public class CourseAnnouncementDaoImpl extends BaseDaoImpl<CourseAnnouncement,Long> implements CourseAnnouncementDao{

	public Page<CourseAnnouncement> findPage(Course course, Pageable pageable) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseAnnouncement> criteriaQuery = criteriaBuilder.createQuery(CourseAnnouncement.class);
		Root<CourseAnnouncement> root = criteriaQuery.from(CourseAnnouncement.class);
		criteriaQuery.select(root);
		
		Predicate restrictions = criteriaBuilder.conjunction();
		if(course!=null){
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("course"), course));
		}
		
		criteriaQuery.where(restrictions);
		return super.findPage(criteriaQuery, pageable);
	}

}
