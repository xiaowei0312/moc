/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao.impl;

import java.util.List;
import java.util.Set;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.TeacherDao;
import com.sram.entity.Course;
import com.sram.entity.Teacher;

/**
 * Dao - 教师
 * 
 * @author Sram Team
 * @version 1.0
 */
@Repository("teacherDaoImpl")
public class TeacherDaoImpl extends BaseDaoImpl<Teacher, Long> implements TeacherDao {
	
	public Page<Teacher> findPage(
			String truename,Set<Long> teacherIds,String recommended,
			Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Teacher> criteriaQuery = criteriaBuilder.createQuery(Teacher.class);
		Root<Teacher> root = criteriaQuery.from(Teacher.class);
		criteriaQuery.select(root);
		
		Predicate restrictions = criteriaBuilder.conjunction();
		if (truename != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("truename"), truename));
		}
		if(teacherIds != null && !teacherIds.isEmpty()){
			for(Long id:teacherIds){
				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.<String>get("id"),  "%"+id+"%"));
			}
		}
		if(recommended != null && !recommended.isEmpty()){
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("recommended"), "1"));
		}
		criteriaQuery.where(restrictions);
		return super.findPage(criteriaQuery, pageable);
	}

	public List<Teacher> findTeachersNotIds(String ids) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Teacher> criteriaQuery = criteriaBuilder.createQuery(Teacher.class);
		Root<Teacher> root = criteriaQuery.from(Teacher.class);
		
		criteriaQuery.select(root);
		Predicate restrictions=criteriaBuilder.conjunction();
		for(String id:ids.split(",")){
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.notEqual(root.get("id"),  Long.parseLong(id)));
		}
		criteriaQuery.where(restrictions);
		TypedQuery<Teacher> query = entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT);
		System.out.println(query.getResultList().size());
		return query.getResultList();
	}

	public boolean idcardExists(String idcard) {
		String jpql = "select count(*) from Teacher t where t.idcard = :idcard";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("idcard", idcard).getSingleResult();
		return count > 0;
	}

	public boolean mobileExists(String mobile,Long id) {
		if(id==null){
			id=(long) 0;
		}
		
		String jpql = "select count(*) from Teacher t where t.mobile = :mobile and t.id != :id";
		Long count =entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT)
				.setParameter("id", id)
				.setParameter("mobile", mobile).getSingleResult();

		return count>0;
	}

	public void changeStatus(Teacher teacher) {
		// TODO Auto-generated method stub
		String jpql = "update Teacher t set t.status = :status where t.id = :id";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT)
		.setParameter("status", teacher.getStatus())
		.setParameter("id", teacher.getId()).executeUpdate();
	}

	public List<Teacher> findAll(Integer first,Integer count) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Teacher> criteriaQuery = criteriaBuilder.createQuery(Teacher.class);
		Root<Teacher> root = criteriaQuery.from(Teacher.class);
		criteriaQuery.select(root);
		return super.findList(criteriaQuery, first, count, null, null);
	}
}