/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.CourseLessonDao;
import com.sram.entity.Course;
import com.sram.entity.CourseLesson;
import com.sram.entity.CourseLesson.Status;

/**
 * Dao - 课时
 * 
 * @author Sram Team
 * @version 1.0
 */
@Repository("courseLessonDaoImpl")
public class CourseLessonDaoImpl extends BaseDaoImpl<CourseLesson, Long>
		implements CourseLessonDao {

	public Integer findMaxOrder(Long courseId, Long courseChapterId) {

		String jpql = null;
		if (courseChapterId != null) {
			// 课时属于小节
			jpql = "select max(c.order) from CourseLesson c where c.courseChapter.id = :courseChapterId";
		} else {
			// 课时属于章
			jpql = "select max(c.order) from CourseLesson c where c.course.id = :courseId";
		}
		TypedQuery<Integer> query = entityManager.createQuery(jpql,
				Integer.class).setFlushMode(FlushModeType.COMMIT);
		if (courseChapterId != null) {
			query.setParameter("courseChapterId", courseChapterId);
		} else {
			query.setParameter("courseId", courseId);
		}
		Integer number = query.getSingleResult();

		return number == null ? 0 : number;
	}

	public Long findFirstPublishedMediaId(Long courseId) {
		String jpql = "select c from CourseLesson c where c.course.id = :courseId and c.status = :status order by c.courseChapter, c.order ";
		TypedQuery<CourseLesson> query = entityManager.createQuery(jpql,
				CourseLesson.class).setFlushMode(FlushModeType.COMMIT);

		query.setParameter("courseId", courseId);
		query.setParameter("status", Status.published);
		query.setFirstResult(0);
		query.setMaxResults(1);

		CourseLesson courseLesson = query.getResultList().size() > 0 ? query
				.getSingleResult() : new CourseLesson();

		return courseLesson.getUploadFiles() == null ? 0 : courseLesson
				.getUploadFiles().getId();
	}

	public boolean changeStatus(Long lessonId,Status lessonStatus) {

		String jpql = "update CourseLesson c set c.status = :status where c.id = :id";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT)
				.setParameter("status", lessonStatus)
				.setParameter("id", lessonId)
				.executeUpdate();
		return true;
	}

	public Page<Object[]> findStaPage(Pageable pageable, Long courseId) {
		// TODO Auto-generated method stub
		List<Object> conditionList = new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer();
		/*
		 * count(cl.userId) 课时学习人数 avg(cl.learnTime) 课时平均学习时长 avg(cl.watchTime)
		 * 课时音视频平均观看时长
		 */
		jpql.append("SELECT m.id,m.title,count(mc.user_id),m.learned_num,avg(mc.learn_time),m.length,avg(mc.watch_time)");
		jpql.append(" FROM 	moc_course_lesson m LEFT JOIN moc_course_lesson_learn mc ");
		jpql.append(" ON m.id =  mc.lesson_id WHERE 1=1 ");
		if (courseId != null && courseId != 0) {
			jpql.append(" AND m.lesson_course = ?");
			conditionList.add(courseId);
		}
		jpql.append(" GROUP BY m.id");
		// 求课时总数
		long total = countStaData(courseId);
		int totalPages = (int) Math.ceil((double) total
				/ (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		List<Object[]> list = createNativeQueryPage(jpql.toString(),
				pageable.getPageNumber(), pageable.getPageSize(), conditionList);

		return new Page<Object[]>(list, total, pageable);
	}

	private long countStaData(Long courseId) {
		// TODO Auto-generated method stub
		List<Object> conditionList = new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer();
		jpql.append("select count(*) from CourseLesson c where 1=1");
		if (courseId != null && courseId != 0) {
			jpql.append(" and c.course.id=?");
			conditionList.add(courseId);
		}
		Query query = entityManager.createQuery(jpql.toString(), Long.class);
		for (int i = 0; i < conditionList.size(); i++) {
			query.setParameter(i + 1, conditionList.get(i));
		}
		long count = (Long) query.setFlushMode(FlushModeType.COMMIT)
				.getSingleResult();
		return count;
	}

	public Long getTotalLength(Course course,Status courseLessonStatus) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder
				.createQuery(Long.class);
		Root<CourseLesson> root = criteriaQuery.from(CourseLesson.class);
		criteriaQuery.multiselect(criteriaBuilder.sum(root
				.<Integer> get("length")));
		Predicate restrictions = criteriaBuilder.conjunction();
		restrictions = criteriaBuilder.and(restrictions,
				criteriaBuilder.equal(root.get("course"), course));
		if(courseLessonStatus!=null){
			restrictions=criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("status"), courseLessonStatus));
		}
		criteriaQuery.where(restrictions);
		try {
			return entityManager.createQuery(criteriaQuery)
					.setFlushMode(FlushModeType.COMMIT).getSingleResult();
		} catch (IllegalArgumentException e) {
			return (long) 0;
		}
	}

	public List<CourseLesson> findLessonByCourse(Long courseId) {
		String jpql = "select c from CourseLesson c  where c.course.id = :courseId order by c.number,c.order";
		TypedQuery<CourseLesson> query = entityManager
				.createQuery(jpql, CourseLesson.class)
				.setFlushMode(FlushModeType.COMMIT)
				.setParameter("courseId", courseId);
		return query.getResultList();
	}

	public String getVideoUri(Long lessonId) {
		String jpql = "select c.mediaUri from CourseLesson c  where c.id = :lessonId";
		TypedQuery<String> query = entityManager
				.createQuery(jpql, String.class)
				.setFlushMode(FlushModeType.COMMIT)
				.setParameter("lessonId", lessonId);
		return query.getSingleResult();
	}

	public void updateLearnedNum(CourseLesson courseLesson) {
		// TODO Auto-generated method stub
		String jpql = "update CourseLesson c set c.learnedNum=:learnedNum where c.id=:courseLessonId";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT)
				.setParameter("learnedNum", courseLesson.getLearnedNum())
				.setParameter("courseLessonId", courseLesson.getId())
				.executeUpdate();
	}

	public List<CourseLesson> findLessonByChapterMember(Long chapterId,
			Long memberId) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseLesson> query = criteriaBuilder
				.createQuery(CourseLesson.class);
		Root<CourseLesson> root = query.from(CourseLesson.class);
		query.select(root);

		Predicate restrictions = criteriaBuilder.conjunction();
		if (chapterId != null) {
			restrictions = criteriaBuilder
					.and(restrictions, criteriaBuilder.equal(
							root.get("courseChapter"), chapterId));
		}
		if (memberId != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder
					.equal(root.join("courseLessonLearns").get("userId"),
							memberId));
		}

		query.where(restrictions);
		return findList(query, null, null, null, null);
	}

	

	public List<CourseLesson> findPublishLesson(Long courseId, Long chapterId,
			Status status) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseLesson> criteriaQuery = criteriaBuilder
				.createQuery(CourseLesson.class);
		Root<CourseLesson> root = criteriaQuery.from(CourseLesson.class);
		criteriaQuery.select(root);

		Predicate restrictions = criteriaBuilder.conjunction();
		if (courseId != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("course"), courseId));
		}
		if (chapterId != null) {
			restrictions = criteriaBuilder
					.and(restrictions, criteriaBuilder.equal(
							root.get("courseChapter"), chapterId));
		}
		if (status != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("status"), status));
		}
		criteriaQuery.where(restrictions);
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("order")));
		return entityManager.createQuery(criteriaQuery)
				.setFlushMode(FlushModeType.COMMIT).getResultList();
	}

	public CourseLesson findFirstLesson(Long courseId) {
		StringBuffer jpql = new StringBuffer();
		jpql.append(" select c from CourseLesson c ")
				.append(" left join fetch c.courseChapter cc")
				.append(" where c.course.id =:courseId ")
				.append(" and c.status =:status").append(" order by cc.number,c.order");

		TypedQuery<CourseLesson> query = entityManager.createQuery(
				jpql.toString(), CourseLesson.class).setFlushMode(
				FlushModeType.COMMIT);

		query.setParameter("courseId", courseId);
		query.setParameter("status", Status.published);
		query.setFirstResult(0);
		query.setMaxResults(1);

		CourseLesson courseLesson;
		try {
			courseLesson= query.getSingleResult();
		} catch (Exception e) {
			courseLesson=null;
		}

		return courseLesson;
	}

	public Page<CourseLesson> findPage(Pageable pageable, Date beginDate,
			Date endDate) {
		// TODO Auto-generated method stub
		StringBuffer jpql = new StringBuffer();
		List<Object> conditionList=new ArrayList<Object>();
		jpql.append("from CourseLesson c where 1=1");
		if(beginDate!=null){
			jpql.append(" and c.createDate>=?");
			conditionList.add(beginDate);
		}
		if(endDate!=null){
			jpql.append(" and c.createDate<=?");
			conditionList.add(endDate);
		}
		jpql.append(" order by c.createDate desc");
		long total=countAnalyasisLesson(beginDate,endDate);
		int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		List<CourseLesson> list=createQueryPage(jpql.toString(),pageable.getPageNumber(),pageable.getPageSize(),conditionList);
		return new Page<CourseLesson>(list,total,pageable);
	}

	private long countAnalyasisLesson(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		StringBuffer jpql = new StringBuffer();
		List<Object> conditionList=new ArrayList<Object>();
		jpql.append("select count(*) from CourseLesson c where 1=1");
		if(beginDate!=null){
			jpql.append(" and c.createDate>=?");
			conditionList.add(beginDate);
		}
		if(endDate!=null){
			jpql.append(" and c.createDate<=?");
			conditionList.add(endDate);
		}
		return createQueryCount(jpql.toString(),conditionList);
	}

	public List<Object[]> findAnalySisList(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT date_format(m.create_date,'%Y-%m-%d')  sub,count(m.id)");
		sql.append(" from moc_course_lesson m");
		sql.append(" where 1=1");
		if(beginDate!=null){
			sql.append(" and m.create_date>=?");
		}
		if(endDate!=null){
			sql.append(" and m.create_date<=?");
		}
		sql.append(" group by sub");
		sql.append(" order by sub asc");
		
		if(beginDate!=null){
			conditionList.add(beginDate);
		}
		if(endDate!=null){
			conditionList.add(endDate);
		}
		return createNativeQuery(sql.toString(),conditionList);
	}

	public void updateRelevanceById(Long lessonId, Long outlineCategoryId) {
		// TODO Auto-generated method stub
		String jpql="update CourseLesson c set c.outlineCategory.id=? where c.id=?";
		Query query=entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT);
		query.setParameter(1, outlineCategoryId);
		query.setParameter(2, lessonId);
		query.executeUpdate();
	}

}