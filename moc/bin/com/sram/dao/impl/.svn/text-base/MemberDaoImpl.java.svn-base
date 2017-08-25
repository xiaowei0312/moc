/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.Principal;
import com.sram.dao.MemberDao;
import com.sram.entity.Member;
import com.sram.entity.MemberRank;

/**
 * Dao - 会员
 * 
 * @author Sram Team
 * @version 1.0
 */
@Repository("memberDaoImpl")
public class MemberDaoImpl extends BaseDaoImpl<Member, Long> implements MemberDao {

	public boolean usernameExists(String username) {
		if (username == null) {
			return false;
		}
		String jpql = "select count(*) from Member members where lower(members.username) = lower(:username)";
		try {
			return entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult()>0;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean emailExists(String email) {
		if (email == null) {
			return false;
		}
		String jpql = "select count(*) from Member members where lower(members.email) = lower(:email)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("email", email).getSingleResult();
		return count > 0;
	}

	public Member findByUsernameOrPhone(String username) {
		if (username == null || StringUtils.isEmpty(username)) {
			return null;
		}
		try {
			String jpql = "select m from Member m where lower(m.username) = lower(:username) or m.mobile =:mobile";
			return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT)
					.setParameter("username", username)
					.setParameter("mobile", username)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public List<Member> findListByEmail(String email) {
		if (email == null) {
			return Collections.<Member> emptyList();
		}
		String jpql = "select members from Member members where lower(members.email) = lower(:email)";
		return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter("email", email).getResultList();
	}

	public List<Object[]> findPurchaseList(Date beginDate, Date endDate, Integer count) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
		Root<Member> member = criteriaQuery.from(Member.class);
		Predicate restrictions = criteriaBuilder.conjunction();
		
		return null;
	}

	public Page<Member>  findPage(Pageable pageable, MemberRank memberRank) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
		Root<Member> root = criteriaQuery.from(Member.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (memberRank != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("memberRank"), memberRank));
		}
		criteriaQuery.where(restrictions);
		return super.findPage(criteriaQuery, pageable);
	}

	public List<Object[]> findHotMember() {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();

		StringBuffer sql=new StringBuffer();
        sql.append("select m.id,m.username,m.head_image,count(DISTINCT  mt.thread_id) num");
        sql.append(" from moc_member m left join moc_course_thread_post mt");
        sql.append(" on m.id=mt.user_id");
        sql.append(" group by m.id order by num desc");
        List<Object[]> list=createNativeQueryPage(sql.toString(),1,3,conditionList);
		return list;
	}

	public String findHeadImage(Principal principal) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("select m.headImage from Member m")
		.append(" where m.id =:id");
		TypedQuery<String> query = entityManager.createQuery(jpql.toString(),String.class)
		.setFlushMode(FlushModeType.COMMIT)
		.setParameter("id", principal.getId());
		return query.getSingleResult();
	}

	public List<Member> findList(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		StringBuffer jpql = new StringBuffer();
		jpql.append("from Member m where 1=1");
		if(beginDate!=null){
			jpql.append(" and m.createDate>=:beginDate");
		}
		if(endDate!=null){
			jpql.append(" and m.createDate<=:endDate");
		}
		jpql.append(" order by m.createDate desc");
		TypedQuery<Member> query=entityManager.createQuery(jpql.toString(),Member.class)
		.setFlushMode(FlushModeType.COMMIT);
		if(beginDate!=null){
			query.setParameter("beginDate", beginDate);
		}
		if(beginDate!=null){
			query.setParameter("endDate", endDate);
		}
		return query.getResultList();
	}

	public Page<Member> findPage(Pageable pageable, Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		StringBuffer jpql = new StringBuffer();
		jpql.append("from Member m where 1=1");
		if(beginDate!=null){
			jpql.append(" and m.createDate>=:beginDate");
		}
		if(endDate!=null){
			jpql.append(" and m.createDate<=:endDate");
		}
		jpql.append(" order by m.createDate desc");
		TypedQuery<Member> query=entityManager.createQuery(jpql.toString(),Member.class)
		.setFlushMode(FlushModeType.COMMIT);
		if(beginDate!=null){
			query.setParameter("beginDate", beginDate);
		}
		if(endDate!=null){
			query.setParameter("endDate", endDate);
		}
		long total=countAnalysisMember(beginDate,endDate);
		int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		query.setFirstResult((pageable.getPageNumber()-1)*pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
		List<Member> list=query.getResultList();
		return new Page<Member>(list,total,pageable);
	}

	private long countAnalysisMember(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		StringBuffer jpql = new StringBuffer();
		jpql.append("select count(*) from Member m where 1=1");
		if(beginDate!=null){
			jpql.append(" and m.createDate>=:beginDate");
		}
		if(endDate!=null){
			jpql.append(" and m.createDate<=:endDate");
		}
		Query query=entityManager.createQuery(jpql.toString(), Long.class).setFlushMode(FlushModeType.COMMIT);
		if(beginDate!=null){
			query.setParameter("beginDate", beginDate);
		}
		if(endDate!=null){
			query.setParameter("endDate", endDate);
		}
		return (Long) query.getSingleResult();
	}

	public List<Object[]> findAnalySisList(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT date_format(m.create_date,'%Y-%m-%d')  sub,count(m.id)");
		sql.append(" from moc_member m");
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

	public void updateExperienceCoin(Integer experienceValue,
			Integer learningCoin, Long memberId) {
		StringBuffer sb=new StringBuffer();
		sb.append(" update Member m set ");
		sb.append(" m.experienceValue=(ifnull(m.experienceValue, 0)+:experienceValue)");
		sb.append(" ,m.learningCoin = (ifnull(m.learningCoin, 0)+:learningCoin)");
		sb.append(" where m.id=:memberId ");
		entityManager.createQuery(sb.toString())
		.setParameter("experienceValue", experienceValue)
		.setParameter("learningCoin", learningCoin)
		.setParameter("memberId", memberId)
		.setFlushMode(FlushModeType.COMMIT)
		.executeUpdate();
	}

	public Object[] getExperienceCoin(Long memberId) {
		String jpql="select COALESCE(m.experienceValue, 0),COALESCE(m.learningCoin, 0) from Member m where m.id =:memberId";
		
		TypedQuery<Object[]> query = entityManager.createQuery(jpql,Object[].class).setFlushMode(FlushModeType.COMMIT);
		query.setParameter("memberId", memberId);
		try {
			return (Object[]) query.getSingleResult();
			
		} catch (Exception e) {
			e.printStackTrace();
			return new Object[]{0,0};
		}
	}

	public void updateCoinByUserId(Long memberId,
			Integer lessonCoin) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer();
		sb.append(" update Member m set ");
		sb.append(" m.learningCoin = m.learningCoin-:learningCoin");
		sb.append(" where m.id=:memberId ");
		entityManager.createQuery(sb.toString())
		.setParameter("learningCoin", lessonCoin)
		.setParameter("memberId", memberId)
		.setFlushMode(FlushModeType.COMMIT)
		.executeUpdate();
	}

	public void updateExamAreaAndExamOutlineCategory(String username,
			Long examOutlineCategoryId, Long examAreaId) {
		StringBuffer sb=new StringBuffer();
		sb.append(" update Member m set ");
		sb.append(" m.examOutlineCategoryId  =:examOutlineCategoryId ");
		sb.append(" ,m.examAreaId =:examAreaId ");
		sb.append(" where m.username =:username ");
		entityManager.createQuery(sb.toString())
		.setParameter("examOutlineCategoryId", examOutlineCategoryId)
		.setParameter("examAreaId", examAreaId)
		.setParameter("username", username.trim())
		.setFlushMode(FlushModeType.COMMIT)
		.executeUpdate();
		
	}


}