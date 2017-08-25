package com.sram.job;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sram.service.IntegralRuleTaskService;

@Component("IntegralRuleTaskJob")
@Lazy(false)
public class IntegralRuleTaskJob {
	@Resource(name="integralRuleTaskServiceImpl")
	private IntegralRuleTaskService integralRuleTaskService;
	
	/**
	 * 重置日常任务
	 */
	@Scheduled(cron = "${job.integral_rule_task.cron}")
	public void resetDalilyTasks(){
		integralRuleTaskService.resetDalilyTasks();
	}
}
