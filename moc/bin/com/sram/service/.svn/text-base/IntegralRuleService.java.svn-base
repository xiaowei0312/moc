/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service;

import java.util.List;

import com.sram.entity.IntegralRule;

/**
 * Service - 积分规则
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface IntegralRuleService extends BaseService<IntegralRule, Long> {
	
	public List<IntegralRule> findIntegralRuleByUrlPath(String urlPath);
	
	/**
	 * 查找该会员下的所有任务，包括没有接收的
	 * @param memberId
	 * @return
	 */
	public List<Object[]> findAllTasks(Long memberId);

}