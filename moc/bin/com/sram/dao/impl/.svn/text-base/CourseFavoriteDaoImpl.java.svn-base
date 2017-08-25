package com.sram.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;

import com.sram.dao.CourseFavoriteDao;
import com.sram.entity.Course;
import com.sram.entity.CourseFavorite;

/**
 * Service - 课程收藏
 * 
 * @author Sram Team
 * @version 1.0
 */
@Repository("courseFavoriteDaoImpl")
public class CourseFavoriteDaoImpl extends BaseDaoImpl<CourseFavorite,Long> implements CourseFavoriteDao{

	public boolean courseFavoriteExists(Long courseId, Long memberId) {
		// TODO Auto-generated method stub
		String jpql="select count(*) from CourseFavorite c where c.course.id=:courseId and c.member.id=:memberId";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("courseId", courseId).setParameter("memberId", memberId).getSingleResult();
		return count>0;
	}
    

	public void deleteCourFav(Long courseId, Long memberId) {
		// TODO Auto-generated method stub
		StringBuffer jpql=new StringBuffer();
		jpql.append("delete from CourseFavorite as c where c.member.id=:memberId");
		jpql.append(" and c.course.id=:courseId");
		entityManager.createQuery(jpql.toString()).setFlushMode(FlushModeType.COMMIT)
		.setParameter("memberId", memberId).setParameter("courseId", courseId)
		.executeUpdate();
	}

	public List<CourseFavorite> findFavoriteByUser(Long userId) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer jpql=new StringBuffer();
		jpql.append("from CourseFavorite cf");
		jpql.append(" where cf.member.id=?");
		conditionList.add(userId);
		return createQuery(jpql.toString(),conditionList);
	}

}
