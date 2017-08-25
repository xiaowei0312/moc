/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sram.dao.CourseMaterialDao;
import com.sram.entity.CourseLesson;
import com.sram.entity.CourseMaterial;

/**
 * Dao - 课程资料
 * 
 * @author Sram Team
 * @version 1.0
 */
@Repository("courseMaterialDaoImpl")
public class CourseMaterialDaoImpl extends BaseDaoImpl<CourseMaterial, Long>
		implements CourseMaterialDao {

	public List<CourseMaterial> findListByLessonId(Long lessonId,Long courseId) {
		String jpql = "select c from CourseMaterial c join fetch c.uploadFiles where (c.courseLesson.id =:lessonId) " +
				" or (c.course.id =:courseId and c.courseLesson.id is null)";
		return entityManager.createQuery(jpql, CourseMaterial.class)
				.setFlushMode(FlushModeType.COMMIT)
				.setParameter("lessonId", lessonId)
				.setParameter("courseId", courseId).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<CourseMaterial> findList(Long courseId) {
		StringBuffer jpql = new StringBuffer();
		jpql.append(" select m from CourseMaterial m ")
				.append(" left join m.courseLesson lesson")
				.append(" where lesson.status = "
						+ CourseLesson.Status.published.ordinal());
		if(courseId !=null){
			jpql.append(" and m.course.id =:courseId ");
		}
		jpql.append(" order by lesson.order ");

		
		TypedQuery<CourseMaterial> query=entityManager.createQuery(jpql.toString(),CourseMaterial.class).setFlushMode(FlushModeType.COMMIT);
		if(courseId !=null){
			query.setParameter("courseId", courseId);
		}
		return query.getResultList();
	}

}