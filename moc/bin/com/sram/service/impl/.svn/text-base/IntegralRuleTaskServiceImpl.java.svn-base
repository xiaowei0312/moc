/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sram.dao.IntegralRuleTaskDao;
import com.sram.entity.IntegralRuleTask;
import com.sram.service.IntegralRuleTaskService;

/**
 * Service - 积分记录
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("integralRuleTaskServiceImpl")
public class IntegralRuleTaskServiceImpl extends
		BaseServiceImpl<IntegralRuleTask, Long> implements
		IntegralRuleTaskService {

	@Resource(name = "integralRuleTaskDaoImpl")
	private IntegralRuleTaskDao integralRuleTaskDao;

	@Resource(name = "integralRuleTaskDaoImpl")
	public void setBaseDao(IntegralRuleTaskDao integralRuleTaskDao) {
		super.setBaseDao(integralRuleTaskDao);
	}

	public boolean isExistTask(Long memberId, String urlPath) {
		return integralRuleTaskDao.isExistTask(memberId, urlPath);
	}

	public List<IntegralRuleTask> findIntegralRuleTasks(Long memberId,
			String urlPath) {
		return integralRuleTaskDao.findIntegralRuleTasks(memberId, urlPath);
	}

	public void updateIsReceive(Long taskId, boolean isReceive) {
		integralRuleTaskDao.updateIsReceive(taskId,isReceive);
	}

	public void resetDalilyTasks() {
		integralRuleTaskDao.resetDalilyTasks();
	}
}