/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sram.dao.IntegralRuleTaskDao;
import com.sram.entity.IntegralRule.TaskType;
import com.sram.entity.IntegralRuleTask;

/**
 * Dao - 积分记录
 * 
 * @author Sram Team
 * @version 1.0
 */
@Repository("integralRuleTaskDaoImpl")
public class IntegralRuleTaskDaoImpl extends
		BaseDaoImpl<IntegralRuleTask, Long> implements IntegralRuleTaskDao {

	public boolean isExistTask(Long memberId, String urlPath) {
		String jpql = "select count(*) from IntegralRuleTask t where t.memberId = ? and t.urlPath = ?";
		List<Object> list = new ArrayList<Object>();
		list.add(memberId);
		list.add(urlPath);
		Long count = createQueryCount(jpql, list);
		return count > 0 ? true : false;
	}

	public List<IntegralRuleTask> findIntegralRuleTasks(Long memberId,
			String urlPath) {
		String jpql ="select t from IntegralRuleTask t left join fetch t.integralRule  where  t.memberId=:memberId and t.urlPath =:urlPath";
		TypedQuery<IntegralRuleTask> query = entityManager.createQuery(jpql, IntegralRuleTask.class)
			.setParameter("memberId", memberId).setParameter("urlPath",urlPath);
		return query.getResultList();
	}

	public void updateIsReceive(Long taskId, boolean isReceive) {
		String hql = "update IntegralRuleTask irt set irt.isReceive =:isReceive where irt.id =:id ";
		entityManager.createQuery(hql).setFlushMode(FlushModeType.COMMIT)
		.setParameter("id", taskId)
		.setParameter("isReceive", isReceive)
		.executeUpdate();
		
	}

	public void resetDalilyTasks() {
		StringBuffer sb=new StringBuffer();
		sb.append(" update moc_integral_rule_task irt ");
		sb.append(" set irt.accumulative_acount =0,irt.is_receive=false ");
		sb.append(" where irt.integral_rule.id in (select ir.id from moc_integral_rule ir where ir.task_type="+TaskType.dailyTask.ordinal()+")");
		
		entityManager.createNativeQuery(sb.toString()).executeUpdate();
	}
}