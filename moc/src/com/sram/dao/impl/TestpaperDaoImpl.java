package com.sram.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sram.Constants.QuestionType;
import com.sram.Constants.TestpaperType;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.TestpaperDao;
import com.sram.entity.Testpaper;
import com.sram.entity.Testpaper.Status;
@Repository("testpaperDaoImpl")
public class TestpaperDaoImpl extends BaseDaoImpl<Testpaper, Long> implements TestpaperDao{
	
	public Page<Testpaper> findPage(Pageable pageable,String testpaperType,Long outlineCategoryId,String beginDateStr, String endDateStr) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Testpaper> criteriaQuery = criteriaBuilder.createQuery(Testpaper.class);
		Root<Testpaper> root = criteriaQuery.from(Testpaper.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if(testpaperType!=null && !("").equals(testpaperType)){
			restrictions =criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("testpaperType"), TestpaperType.valueOf(testpaperType).ordinal()));
		}
		if(outlineCategoryId!=null && outlineCategoryId!=0){
			restrictions = criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("outlineCategory"),outlineCategoryId));
		}
		if(beginDateStr!=null && !("").equals(beginDateStr)){
			restrictions =criteriaBuilder.and(restrictions,criteriaBuilder.greaterThanOrEqualTo(root.<String> get("oldYearMonth"), beginDateStr));
		}
		if(endDateStr!=null && !("").equals(endDateStr)){
			restrictions =criteriaBuilder.and(restrictions,criteriaBuilder.lessThanOrEqualTo(root.<String> get("oldYearMonth"), endDateStr));
		}
		criteriaQuery.where(restrictions);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createDate")));
		return super.findPage(criteriaQuery,pageable);
	}

	public List<Testpaper> findAll() {
		TypedQuery<Testpaper> query;
		String jpql = "select t from Testpaper t ";
		query = entityManager.createQuery(jpql, Testpaper.class).setFlushMode(FlushModeType.COMMIT);
		return query.getResultList();
	}

	public Testpaper findTestpaperById(Long testpaperId) {
		String jpql="select t from Testpaper t  " +
				" left join fetch t.outlineCategory " +
				" where t.id=:testpaperId";
		TypedQuery<Testpaper> query = entityManager.createQuery(jpql, Testpaper.class).setFlushMode(FlushModeType.COMMIT).setParameter("testpaperId", testpaperId);
		return query.getSingleResult();
	}
	public List<Testpaper> findOldexamList(Long outlineCategoryID, Long areaID,Integer page) {
		String jpql="select t from Testpaper t  where t.testpaperType=? and t.outlineCategory.id=? ";
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(TestpaperType.oldexam);
		list.add(outlineCategoryID);
		if(areaID!=null){
			jpql+=" and t.area.id=? ";
			list.add(areaID);
		}
		//2015 05 13 start 只查开放的试卷
		jpql+=" and t.status=?";
		list.add(Status.open);
		//2015 05 13 end
		return this.createQueryPage(jpql, page, 8, list);
	}

	public Long findOldexamCount(Long outlineCategoryID, Long areaID) {
		String jpql="select count(*) from Testpaper t  where t.testpaperType=? and t.outlineCategory.id=? ";
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(TestpaperType.oldexam);
		list.add(outlineCategoryID);
		if(areaID!=null){
			jpql+=" and t.area.id=? ";
			list.add(areaID);
		}
		//2015 05 13 start 只查开放的试卷
		jpql+=" and t.status=?";
		list.add(Status.open);
		//2015 05 13 end
		return this.createQueryCount(jpql, list);
	}
	
	public Testpaper findTestpaperWithChapters(Long testpaperId){
		String jpql = "select t from Testpaper t join fetch t.testpaperChapters where t.id=:testpaperId";
		TypedQuery<Testpaper> query = entityManager.createQuery(jpql, Testpaper.class).setParameter("testpaperId", testpaperId);
		
		//2015,5,11----荣刚平
		try {
			return query.getResultList().get(0);
		} catch (Exception e) {
			return null;
		}
	}

	public Long findTestpaperItemCount(Long testpaperId) {
		String jpql="select count(*) from TestpaperItem t" +
				"   where t.testpaper.id=? and t.questionType<>"+QuestionType.material.ordinal()+"";
		ArrayList<Object> list = new ArrayList<Object>();
		list.add(testpaperId);
		return this.createQueryCount(jpql, list);
	}

	public void updateStatusById(Long testPaperId, Status status) {
		// TODO Auto-generated method stub
		String jpql="update Testpaper t set t.status=? where t.id=?";
		Query query=entityManager.createQuery(jpql.toString()).setFlushMode(FlushModeType.COMMIT);
		query.setParameter(1, status);
		query.setParameter(2, testPaperId);
		query.executeUpdate();
	}
	
}
