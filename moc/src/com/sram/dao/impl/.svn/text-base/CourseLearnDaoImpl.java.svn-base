package com.sram.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.CourseLearnDao;
import com.sram.entity.Course;
import com.sram.entity.CourseLearn;
import com.sram.entity.CourseLearn.Status;
import com.sram.entity.Member;


@Repository("courseLearnDaoImpl")
public class CourseLearnDaoImpl extends BaseDaoImpl<CourseLearn, Long> implements CourseLearnDao {

	public CourseLearn findByCourseId(Long courseId,Long memberId) {
		// TODO Auto-generated method stub
		String jpql = "select courseLearn from CourseLearn courseLearn where courseLearn.course.id=:courseId  and " +
				"courseLearn.member.id=:memberId";
		try {
		    return  entityManager.createQuery(jpql, CourseLearn.class).setFlushMode(FlushModeType.COMMIT).setParameter("courseId", courseId)
		    .setParameter("memberId", memberId).getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
	}

	public void updateCourseLearn(Long memberId, Long courseId, Status finished) {
		// TODO Auto-generated method stub
		String jpql="update CourseLearn c set c.status=:status where c.course.id=:courseId and c.member.id=:memberId";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT)
		.setParameter("status", finished).setParameter("courseId", courseId)
		.setParameter("memberId", memberId).executeUpdate();
	}
   
	
	public void updateFinLessonNum(Integer finishLessonNum, Long courseId,
			Long memberId) {
		// TODO Auto-generated method stub
		String jpql="update CourseLearn c set c.finishLessonNum=:finishLessonNum" +
				" where c.course.id=:courseId and c.member.id=:memberId";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT)
		.setParameter("finishLessonNum", finishLessonNum).setParameter("courseId", courseId)
		.setParameter("memberId", memberId).executeUpdate();
	}

	public void changeRemark(Long id, String description) {
		// TODO Auto-generated method stub
		String jpql="update CourseLearn c set c.description=:description"+
			" where c.id=:id";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT)
		.setParameter("description",description).setParameter("id", id)
		.executeUpdate();
	}

	public boolean memberCourseLearn(Long memberId) {
		// TODO Auto-generated method stub
		String jpql="select count(*) from CourseLearn cl where cl.member.id=:memberId";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("memberId", memberId).getSingleResult();
		return count>0;
	}

	public Page<Object[]> findPage(Pageable pageable, Date beginDate,
			Date endDate) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append("select  m.username,c.title,mc.create_date");
		sql.append(" from moc_course_learn mc inner join moc_member m");
		sql.append(" on mc.user_id=m.id");
		sql.append(" inner join moc_course c");
		sql.append(" on mc.course_id=c.id");
		sql.append(" where 1=1");
		if(beginDate!=null){
			sql.append(" and mc.create_date>=?");
			conditionList.add(beginDate);
		}
		if(endDate!=null){
			sql.append(" and mc.create_date<=?");
			conditionList.add(endDate);
		}
		sql.append(" order by mc.create_date desc,c.title asc");
		long total=countCourseLearn(beginDate,endDate);
		int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		List<Object[]> list=createNativeQueryPage(sql.toString(),pageable.getPageNumber(),pageable.getPageSize(),conditionList);
		return new Page<Object[]>(list,total,pageable);
	}

	private long countCourseLearn(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer();
		jpql.append("select count(*) from CourseLearn c where 1=1");
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


	public void updateLearnStatus(Long learnId, Status learning) {
		// TODO Auto-generated method stub
		StringBuffer jpql=new StringBuffer();
		jpql.append("update CourseLearn c set c.status=? where c.id=?");
		Query qurey=entityManager.createQuery(jpql.toString()).setFlushMode(FlushModeType.COMMIT);
		qurey.setParameter(1,learning);
		qurey.setParameter(2,learnId);
		qurey.executeUpdate();
	}

	public void updateStatusByCourseId(Long courseId, Status learning) {
		// TODO Auto-generated method stub
		String jpql="update CourseLearn c set c.status=? where c.course.id=?";
		Query qurey=entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT);
		qurey.setParameter(1,learning);
		qurey.setParameter(2,courseId);
		qurey.executeUpdate();
	}

	public void updateFinLessonNum(Long courseId, Long userId) {
		// TODO Auto-generated method stub
		String sql="update moc_course_learn m set m.finish_lesson_num =finish_lesson_num-1" +
				" where m.course_id=? and m.user_id=?";
		Query nativeQuery = entityManager.createNativeQuery(sql);
		nativeQuery.setParameter(1, courseId);
		nativeQuery.setParameter(2, userId);
		nativeQuery.executeUpdate();
	}

	public List<Object[]> findAnalySisList(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT date_format(m.create_date,'%Y-%m-%d')  sub,count(m.id)");
		sql.append(" from moc_course_learn m");
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

	public boolean courseLearnExists(Long memberId, Long courseId) {
		// TODO Auto-generated method stub
		String jpql="select count(*) from CourseLearn c where c.member.id=:memberId and c.course.id=:courseId";
		Query query=entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT);
		Long count=(Long) query.setParameter("memberId", memberId).setParameter("courseId", courseId).getSingleResult();
		return count > 0;
	}

	
}
