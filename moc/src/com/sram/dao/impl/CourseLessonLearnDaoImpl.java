package com.sram.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.CourseLessonLearnDao;
import com.sram.entity.Course;
import com.sram.entity.CourseLearn;
import com.sram.entity.CourseLessonLearn;
import com.sram.entity.CourseLessonLearn.Status;

@Repository("courseLessonLearnDaoImpl")
public class CourseLessonLearnDaoImpl extends BaseDaoImpl<CourseLessonLearn,Long> implements CourseLessonLearnDao{

	public CourseLessonLearn findByLessonId(Long lessonId,Long memberId) {
		// TODO Auto-generated method stub
		String jpql = "select courseLessonLearn from CourseLessonLearn courseLessonLearn where courseLessonLearn.courseLesson.id=:lessonId  and " +
				"courseLessonLearn.userId=:memberId";
		try {
		    return  entityManager.createQuery(jpql, CourseLessonLearn.class).setFlushMode(FlushModeType.COMMIT)
		    .setParameter("lessonId", lessonId).setParameter("memberId", memberId).getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}
    
	/**
	 * (non-Javadoc)
	 * @see com.sram.dao.CourseLessonLearnDao#findFinishCount(java.lang.Long, java.lang.Long)
	 * memberId 会员编号
	 * id 课程编号
	 */
	public Integer findFinishCount(Long memberId, Long id,Status finished) {
		// TODO Auto-generated method stub
		StringBuffer jpql=new StringBuffer();
		jpql.append("select count(*)");
		jpql.append(" from CourseLessonLearn cll");
		jpql.append(" where cll.course.id=:courseId  and");
		jpql.append(" cll.userId=:memberId  and cll.status= :status");
		Query query=entityManager.createQuery(jpql.toString(), Long.class).setFlushMode(FlushModeType.COMMIT);
		Long count = (Long) query.setParameter("courseId", id)
		.setParameter("memberId", memberId).setParameter("status", finished)
		.getSingleResult();
		return count.intValue();
	}

	public void updateStatu(CourseLessonLearn courseLessonLearn) {
		// TODO Auto-generated method stub
		String jpql ="update CourseLessonLearn c set c.status=:status,c.modifyDate=:modifyDate" +
				" where c.id=:id";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT)
		.setParameter("status", courseLessonLearn.getStatus())
		.setParameter("modifyDate", courseLessonLearn.getModifyDate())
		.setParameter("id", courseLessonLearn.getId()).executeUpdate();
	}

	public List<CourseLessonLearn> findListByMemberCourse(Long memberId,
			Long courseId) {
		Course course = new Course();
		course.setId(courseId);
		String jpql="select c from CourseLessonLearn c left join fetch c.courseLesson where c.userId =:memberId and c.course =:course";
		TypedQuery<CourseLessonLearn> query=entityManager.createQuery(jpql, CourseLessonLearn.class)
		.setFlushMode(FlushModeType.COMMIT)
		.setParameter("memberId", memberId)
		.setParameter("course", course);
		return query.getResultList();
	}

	public Page<Object[]> findPage(Pageable pageable, Date beginDate,
			Date endDate) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append("select ml.title,ml.type,m.username,mc.create_date");
		sql.append(" from moc_course_lesson_learn mc inner join moc_course_lesson ml");
		sql.append(" on mc.lesson_id=ml.id");
		sql.append(" inner join moc_member m");
		sql.append(" on mc.user_id=m.id");
		if(beginDate!=null){
			sql.append(" and mc.create_date>=?");
			conditionList.add(beginDate);
		}
		if(endDate!=null){
			sql.append(" and mc.create_date<=?");
			conditionList.add(endDate);
		}
		sql.append(" and mc.status=1");
		sql.append(" order by mc.create_date desc,ml.title asc");
		long total=countFinishedLesson(beginDate,endDate);
		int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		List<Object[]> list=createNativeQueryPage(sql.toString(),pageable.getPageNumber(),pageable.getPageSize(),conditionList);
		return new Page<Object[]>(list,total,pageable);
	}

	private long countFinishedLesson(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer();
		jpql.append("select count(*) from CourseLessonLearn c where 1=1");
		if(beginDate!=null){
			jpql.append(" and c.createDate>=?");
			conditionList.add(beginDate);
		}
		if(endDate!=null){
			jpql.append(" and c.createDate<=?");
			conditionList.add(endDate);
		}
		jpql.append(" and c.status=1");
		return createQueryCount(jpql.toString(),conditionList);
	}
    

	public List<Object[]> findFinUserByCourseId(Long courseId, Integer lessonNum) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer jpql=new StringBuffer();
		jpql.append("select mc.user_id,count(mc.id)");
		jpql.append(" from moc_course_lesson_learn mc");
		jpql.append(" where 1=1");
		jpql.append(" and mc.course_id=?");
		jpql.append(" and mc.status=1");
		jpql.append(" group by mc.user_id");
		jpql.append(" having count(mc.id)="+lessonNum);
		conditionList.add(courseId);
		return createNativeQuery(jpql.toString(),conditionList);
	}

	public List<CourseLessonLearn> findFinishLessonUser(Long LessonId) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer jpql=new StringBuffer();
		jpql.append("from CourseLessonLearn cll");
		jpql.append(" where 1=1");
		jpql.append(" and cll.status=?");
		jpql.append(" and cll.courseLesson.id=?");
		conditionList.add(Status.finished);
		conditionList.add(LessonId);
		return createQuery(jpql.toString(),conditionList);
	}

	public void deleteByLessonId(Long lessonId) {
		// TODO Auto-generated method stub
		String jpql="delete from CourseLessonLearn cll where cll.courseLesson.id=?";
		Query query=entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT);
		query.setParameter(1, lessonId);
		query.executeUpdate();
	}

	public List<Object[]> findAnalySisList(Date beginDate, Date endDate,
			Status statu) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT date_format(m.create_date,'%Y-%m-%d')  sub,count(m.id)");
		sql.append(" from moc_course_lesson_learn m");
		sql.append(" where 1=1");
		if(beginDate!=null){
			sql.append(" and m.create_date>=?");
		}
		if(endDate!=null){
			sql.append(" and m.create_date<=?");
		}
		sql.append(" and m.status=?");
		sql.append(" group by sub");
		sql.append(" order by sub asc");
		
		if(beginDate!=null){
			conditionList.add(beginDate);
		}
		if(endDate!=null){
			conditionList.add(endDate);
		}
		if(("finished").equals(statu)){
			conditionList.add(1);
		}else{
			conditionList.add(0);
		}
		return createNativeQuery(sql.toString(),conditionList);
	}

}
