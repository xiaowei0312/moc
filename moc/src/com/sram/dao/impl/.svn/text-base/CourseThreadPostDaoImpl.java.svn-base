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
import com.sram.dao.CourseThreadPostDao;
import com.sram.entity.CourseNote;
import com.sram.entity.CourseThreadPost;

@Repository("courseThreadPostDaoImpl")
public class CourseThreadPostDaoImpl extends BaseDaoImpl<CourseThreadPost,Long> implements CourseThreadPostDao {

	public List<CourseThreadPost> findPost(Long threadId, Long memberId,
			Long courseId, Long lessonId, Integer count) {
		CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseThreadPost> criteriaQuery = criteriaBuilder.createQuery(CourseThreadPost.class);
		Root<CourseThreadPost> root = criteriaQuery.from(CourseThreadPost.class);
		criteriaQuery.select(root);
		
		Predicate restrictions = criteriaBuilder.conjunction();
		if(threadId!=null){
			restrictions=criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("courseThread"),threadId));
		}
		if(memberId!=null){
			restrictions=criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("member"), memberId));
		}
		if(courseId!=null){
			restrictions=criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("courseId"), courseId));
		}
		if(lessonId!=null){
			restrictions=criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("lessonId"), lessonId));
		}
		criteriaQuery.where(restrictions);
		//criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createDate")));
		return super.findList(criteriaQuery, null, count, null, null);
	}

	public Page<CourseThreadPost> findPage(Long courseId, Long lessonId,
			Long threadId, Boolean isElite, Long memberId,
			Pageable pageable) {
		CriteriaBuilder criteriaBuilder=entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseThreadPost> criteriaQuery=criteriaBuilder.createQuery(CourseThreadPost.class);
		Root<CourseThreadPost> root=criteriaQuery.from(CourseThreadPost.class);
		criteriaQuery.select(root);
		
		Predicate restrictions=criteriaBuilder.conjunction();
		if(courseId !=null){
			restrictions=criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("courseId"), courseId));
		}
		if(lessonId !=null){
			restrictions=criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("lessonId"), lessonId));
		}
		if(threadId !=null){
			restrictions=criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("courseThread"), threadId));
		}
		if(isElite!=null){
			restrictions=criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("isElite"), isElite));
		}
		if(memberId!=null){
			restrictions=criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("member"), memberId));
		}
		
		String searchProperty = pageable.getSearchProperty();
		String searchValue = pageable.getSearchValue();
		if (StringUtils.isNotEmpty(searchProperty) && StringUtils.isNotEmpty(searchValue)) {
			if("member".equals(searchProperty)){
				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.join("member").<String> get("username"), "%" + searchValue + "%"));
			}else{
				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.<String> get(searchProperty), "%" + searchValue + "%"));
			}
		}
		
		criteriaQuery.where(restrictions);


		long total = count(criteriaQuery, null);
		int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		TypedQuery<CourseThreadPost> query = entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT);
		query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
		return new Page<CourseThreadPost>(query.getResultList(), total, pageable);
	}

}
