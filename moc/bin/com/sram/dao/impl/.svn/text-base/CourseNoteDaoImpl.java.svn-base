package com.sram.dao.impl;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.CourseNoteDao;
import com.sram.entity.CourseNote;
import com.sram.entity.Member;
@Repository("courseNoteDaoImpl")
public class CourseNoteDaoImpl extends BaseDaoImpl<CourseNote, Long> implements CourseNoteDao{

	public Page<CourseNote> findPage(Long courseId,Pageable pageable){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseNote> criteriaQuery = criteriaBuilder.createQuery(CourseNote.class);
		Root<CourseNote> root = criteriaQuery.from(CourseNote.class);
		criteriaQuery.select(root);
		
		Predicate restrictions = criteriaBuilder.conjunction();
		String jpqlCondition = "from CourseNote  o " +
		"  join  fetch o.course "+
		"  join fetch o.courseLesson "+
		"  join fetch o.member "+
		" where 1=1 ";
		if(courseId!=null){
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("course"), courseId));
			jpqlCondition += " and o.course="+courseId;
		}
		
		String searchProperty = pageable.getSearchProperty();
		String searchValue = pageable.getSearchValue();
		if (StringUtils.isNotEmpty(searchProperty) && StringUtils.isNotEmpty(searchValue)) {
			if(searchProperty.equals("course")){
				jpqlCondition +=" and o.course.title like '%"+searchValue+"%'";
				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.join("course").<String> get("title"), "%" + searchValue + "%"));
			}else if(searchProperty.equals("courseLesson")){
				jpqlCondition +=" and o.courseLesson.title like '%"+searchValue+"%'";
				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.join("courseLesson").<String> get("title"), "%" + searchValue + "%"));
			}else if(searchProperty.equals("member")){
				jpqlCondition +=" and o.member.username like '%"+searchValue+"%'";
				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.join("member").<String> get("username"), "%" + searchValue + "%"));
			}else{
				jpqlCondition +=" and o."+searchProperty+" like '%"+searchValue+"%'";
				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.<String> get(searchProperty), "%" + searchValue + "%"));
			}
		}
		criteriaQuery.where(restrictions);
		jpqlCondition +=" order by o.createDate desc";
		long total = count(criteriaQuery, null);
		int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		TypedQuery<CourseNote> query = entityManager.createQuery("select o "+jpqlCondition,CourseNote.class).setFlushMode(FlushModeType.COMMIT);
		query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
		return new Page<CourseNote>(query.getResultList(), total, pageable);
	}
	public List<CourseNote> findNotes(Long memberId, Integer status,
			Long courseId,Long lessonId, Integer count) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseNote> criteriaQuery = criteriaBuilder.createQuery(CourseNote.class);
		Root<CourseNote> root = criteriaQuery.from(CourseNote.class);
		criteriaQuery.select(root);
		
		Predicate restrictions = criteriaBuilder.conjunction();
		if(memberId!=null){
			restrictions = criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("member"),memberId));
		}
		if(status!=null){
			restrictions = criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("status"),status));
		}
		if(courseId!=null){
			restrictions = criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("course"), courseId));
		}
		if(lessonId != null){
			restrictions = criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("courseLesson"),lessonId));
		}
		criteriaQuery.where(restrictions);
		return super.findList(criteriaQuery, null, count, null, null);
	}
	
	public Page<CourseNote> findPageByUserId(Long memberId, Pageable pageable) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseNote> criteriaQuery = criteriaBuilder.createQuery(CourseNote.class);
		Root<CourseNote> root = criteriaQuery.from(CourseNote.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if(memberId!=null && !("").equals(memberId)){
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("member"), memberId));
		}
		criteriaQuery.where(restrictions);
		return super.findPage(criteriaQuery, pageable);
	}
	public void deleteByLessonId(Long lessonId) {
		String jpql="delete from CourseNote c where c.courseLesson.id =:lessonId";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT)
		.setParameter("lessonId", lessonId).executeUpdate();
	}
	
}
