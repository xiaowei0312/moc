/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.sram.dao.IntegralRuleDao;
import com.sram.entity.IntegralRule;

/**
 * Dao - 积分规则
 * 
 * @author Sram Team
 * @version 1.0
 */
@Repository("integralRuleDaoImpl")
public class IntegralRuleDaoImpl extends BaseDaoImpl<IntegralRule, Long>
		implements IntegralRuleDao {

	public List<IntegralRule> findIntegralRuleByUrlPath(String urlPath) {
		List<Object> parameter = new ArrayList<Object>();
		String jpql = "select r from IntegralRule r where r.urlPath=?";
		parameter.add(urlPath);
		List<IntegralRule> integralRules = createQuery(jpql, parameter);
		return integralRules;
	}
	
	/**
	 * 0,name---1,task_type---2,experience---3,learncoin---4,description,---5,taskcount
	 * 6,accumulative_acount---7,is_receive---8,create_date---9,modify_date,
	 * 10,path---11,appUrlPath----12,ruleid
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> findAllTasks(Long memberId) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select ir.name,ir.task_type,ir.experience_value,ir.learning_coin,ir.description,ir.task_count ");
		
		sql.append(" ,irt.accumulative_acount,irt.is_receive,irt.id,irt.modify_date ");
		//领取任务时要用到
		sql.append(" ,ir.url_path,ir.app_url_path,ir.id as ww,irt.id as uu ");
		sql.append(" from moc_integral_rule ir ");
		sql.append(" left join moc_integral_rule_task irt on ir.id=irt.integral_rule_id ");
		sql.append(" where irt.id is null or irt.member_id ="+memberId);
		sql.append(" order by irt.is_receive asc,ir.task_type");
		Query query=entityManager.createNativeQuery(sql.toString()).setFlushMode(FlushModeType.COMMIT);
		return query.getResultList();
	}
}