/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao;

import java.util.List;

import com.sram.entity.IntegralRuleTask;

/**
 * Dao - 积分记录
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface IntegralRuleTaskDao extends BaseDao<IntegralRuleTask, Long> {

	public boolean isExistTask(Long memberId, String urlPath);
	
	public List<IntegralRuleTask> findIntegralRuleTasks(Long memberId,String urlPath);

	public void updateIsReceive(Long taskId, boolean isReceive);

	public void resetDalilyTasks();
}