package com.sram.dao.impl;

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
import com.sram.dao.CourseReviewDao;
import com.sram.entity.CourseReview;
@Repository("courseReviewDaoImpl")
public class CourseReviewDaoImpl extends BaseDaoImpl<CourseReview,Long> implements CourseReviewDao{

	public Page<CourseReview> findPage(Long courseId, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseReview> criteriaQuery = criteriaBuilder.createQuery(CourseReview.class);
		Root<CourseReview> root = criteriaQuery.from(CourseReview.class);
		criteriaQuery.select(root);
		
		Predicate restrictions = criteriaBuilder.conjunction();
		String jpqlCondition = "from CourseReview  o " +
		"  join  fetch o.course "+
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
			}else if(searchProperty.equals("member")){
				jpqlCondition +=" and o.member.username like '%"+searchValue+"%'";
				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.join("member").<String> get("username"), "%" + searchValue + "%"));
			}else{
				jpqlCondition +=" and o."+searchProperty+" like '%"+searchValue+"%'";
				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.<String> get(searchProperty), "%" + searchValue + "%"));
			}
		}
		
		jpqlCondition +=" order by o.createDate desc";
		criteriaQuery.where(restrictions);
		long total = count(criteriaQuery, null);
		int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		TypedQuery<CourseReview> query = entityManager.createQuery("select o "+jpqlCondition,CourseReview.class).setFlushMode(FlushModeType.COMMIT);
		query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
		return new Page<CourseReview>(query.getResultList(), total, pageable);
	}

	public Page<CourseReview> findPageList(Long courseId, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseReview> criteriaQuery = criteriaBuilder.createQuery(CourseReview.class);
		Root<CourseReview> root = criteriaQuery.from(CourseReview.class);
		criteriaQuery.select(root);
		
		Predicate restrictions = criteriaBuilder.conjunction();
		
		if(courseId !=null){
			restrictions = criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("course"), courseId));
		}
		criteriaQuery.where(restrictions);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createDate")));
		
		TypedQuery<CourseReview> query=entityManager.createQuery(criteriaQuery)
		.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize())
		.setMaxResults(pageable.getPageSize())
		.setFlushMode(FlushModeType.COMMIT);
		
		//2是借用的代价
		return new Page<CourseReview>(query.getResultList(),2,pageable);
	}

}
