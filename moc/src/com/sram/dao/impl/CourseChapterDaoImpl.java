package com.sram.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sram.dao.CourseChapterDao;
import com.sram.entity.CourseChapter;
import com.sram.entity.CourseChapter.Type;

@Repository("courseChapterDaoImpl")
public class CourseChapterDaoImpl extends BaseDaoImpl<CourseChapter, Long>
		implements CourseChapterDao {

	public List<CourseChapter> findRoots(Long courseId) {
		// TODO Auto-generated method stub
		String jpql = "select c from CourseChapter c where c.type ="+Type.chapter.ordinal()+" and c.course.id = :courseId";
		TypedQuery<CourseChapter> query = entityManager
				.createQuery(jpql, CourseChapter.class)
				.setFlushMode(FlushModeType.COMMIT)
				.setParameter("courseId", courseId);
		return query.getResultList();
	}

	public Integer getNextChapterNumber(Long courseId) {
		String jpql = "select max(c.number) from CourseChapter c where c.course.id = :courseId";
		TypedQuery<Integer> query = entityManager
				.createQuery(jpql, Integer.class)
				.setFlushMode(FlushModeType.COMMIT)
				.setParameter("courseId", courseId);
		Integer number = query.getSingleResult();

		return number==null?0:number;
	}

	public Integer getNextCourseItemOrder(Long courseId) {
		String jpql = "select max(c.order) from CourseChapter c where c.course.id = :courseId";
		TypedQuery<Integer> query = entityManager
				.createQuery(jpql, Integer.class)
				.setFlushMode(FlushModeType.COMMIT)
				.setParameter("courseId", courseId);
		Integer order = query.getSingleResult();
		
		return order==null?0:order;
	}

	/**
	 * 
	 * 添加的是章节的话---找最大序号（所有chapter） 添加的是小节的话---找所属章节的最大序号
	 */
	public Integer findMaxOrder(CourseChapter courseChapter, Long parentId) {
		String jpql = null;
		if (parentId == null) {
			
			// 添加的是章节
			jpql = "select max(c.order) from CourseChapter c where c.course.id = :courseId";
		} else {
			
			// 添加的是小节
			jpql = "select max(c.order) from CourseChapter c where c.parent.id = :parentId and c.course.id = :courseId";
		}
		TypedQuery<Integer> query = entityManager
				.createQuery(jpql, Integer.class)
				.setFlushMode(FlushModeType.COMMIT)
				.setParameter("courseId", courseChapter.getCourse().getId());
		if (parentId != null) {
			query.setParameter("parentId", parentId);
		}
		return query.getSingleResult();
	}

	public List<CourseChapter> findUnit(CourseChapter courseChapter) {
		// TODO Auto-generated method stub
		String jpql = "select c from CourseChapter c where c.type="+Type.unit.ordinal()+" and c.number in(select chapter.number from CourseChapter chapter where chapter =:parentChapter)"
				+ " and c.course.id in (select chapter2.course.id from CourseChapter chapter2 where  chapter2=:parentChapter)";
		TypedQuery<CourseChapter> query = entityManager
				.createQuery(jpql, CourseChapter.class)
				.setFlushMode(FlushModeType.COMMIT)
				.setParameter("parentChapter", courseChapter);
		return query.getResultList();
	}

	/**
	 * 更新章节的order,并返回列表
	 * 
	 * @return
	 */
	public List<CourseChapter> sortChapterOrder(Integer maxOrder) {
		
		// 找到比maxOrder大的章节列表
		String jpql = "select c from CourseChapter c where c.order > :maxOrder";
		TypedQuery<CourseChapter> query = entityManager
				.createQuery(jpql, CourseChapter.class)
				.setFlushMode(FlushModeType.COMMIT)
				.setParameter("maxOrder", maxOrder);
		List<CourseChapter> chapters = query.getResultList();
		
		// 更新后续章节的序号
		CourseChapter temp = null;
		for (int i = 0; i < chapters.size(); i++) {
			temp = chapters.get(i);
			temp.setOrder(temp.getOrder() + 1);
		}
		return chapters;
	}

	/**
	 * 根据courseId找chapters
	 */
	public List<CourseChapter> findChaptersByCourse(Long courseId) {
		// TODO Auto-generated method stub
		String jpql = "select c from CourseChapter c left join fetch c.courseLessons lesson where c.course.id = :courseId order by c.number,c.order";
		TypedQuery<CourseChapter> query = entityManager
				.createQuery(jpql, CourseChapter.class)
				.setFlushMode(FlushModeType.COMMIT)
				.setParameter("courseId", courseId);
		return query.getResultList();
	}
}