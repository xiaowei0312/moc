/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service;

import java.util.List;

import com.sram.entity.IntegralRuleTask;

/**
 * Service - 积分记录
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface IntegralRuleTaskService extends
		BaseService<IntegralRuleTask, Long> {

	public boolean isExistTask(Long memberId, String urlPath);
	
	public List<IntegralRuleTask> findIntegralRuleTasks(Long memberId,
			String urlPath);

	/**
	 * 该任务的积分是否已令
	 * @param taskId
	 * @param isReceive
	 */
	public void updateIsReceive(Long taskId, boolean isReceive);

	/**
	 * 重置日常任务
	 */
	public void resetDalilyTasks();
	
}