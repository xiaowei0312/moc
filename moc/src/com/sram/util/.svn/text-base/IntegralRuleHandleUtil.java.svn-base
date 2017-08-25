package com.sram.util;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;

import com.sram.Constants;
import com.sram.Principal;
import com.sram.entity.IntegralRule;
import com.sram.entity.IntegralRule.TaskType;
import com.sram.entity.IntegralRuleTask;
import com.sram.entity.Member;
import com.sram.service.IntegralRuleService;
import com.sram.service.IntegralRuleTaskService;

public class IntegralRuleHandleUtil {
	private static IntegralRuleTaskService integralRuleTaskService = (IntegralRuleTaskService) SpringUtils
			.getBean("integralRuleTaskServiceImpl");

	private static IntegralRuleService integralRuleService = (IntegralRuleService) SpringUtils
			.getBean("integralRuleServiceImpl");

	public static void dispatchTask(HttpServletRequest request,
			HttpServletResponse response, String urlPath) {
		HttpSession session = request.getSession();
		Principal principal = (Principal) session
				.getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
		Long memberId = principal.getId();
		if (urlPath.contains(Constants.LOGIN_URL)) {
			
			// 拦截登录请求
			receiveTask(memberId, Constants.LOGIN_URL);
			handleLoginTask(memberId);
		} else if (urlPath.contains(Constants.COURSE_FAVORITE_URL)) {
			
			//拦截收藏请求
			receiveTask(memberId, Constants.COURSE_FAVORITE_URL);
			handleCourseFavoriteTask(memberId);
		}else if (urlPath.contains(Constants.COURSE_SHARE_URL)) {
			
			//拦截分享请求
			receiveTask(memberId,urlPath);
			handleCourseShareTask(memberId,urlPath);
		}else if(urlPath.contains(Constants.COURSE_SEE_VIDEO)){
			
			//拦截看视频请求
			receiveTask(memberId, Constants.COURSE_SEE_VIDEO);
			handleCourseShareTask(memberId,Constants.COURSE_SEE_VIDEO);
		}else if(urlPath.contains(Constants.MEMBERINFO_ADD_URL)){
			
			//拦截会员修改信息请求
			receiveTask(memberId, Constants.MEMBERINFO_ADD_URL);
			handleCourseShareTask(memberId,Constants.MEMBERINFO_ADD_URL);
		}else if(urlPath.contains(Constants.COURSE_EXERCISE_URL)){
			
			//拦截会员修改信息请求
			receiveTask(memberId, Constants.COURSE_EXERCISE_URL);
			handleCourseShareTask(memberId,Constants.COURSE_EXERCISE_URL);
		}
	}

	private static void handleCourseShareTask(Long memberId,String urlPath) {

		//接收的某一类型的所有任务（URLPATH一样）
		List<IntegralRuleTask> integralRuleTasks = integralRuleTaskService
				.findIntegralRuleTasks(memberId, urlPath);
		
		for (IntegralRuleTask integralRuleTask : integralRuleTasks) {
			IntegralRule integralRule = integralRuleTask.getIntegralRule();

			int days = DateUtils.getBetweenDays(new Date(),
					integralRuleTask.getModifyDate());
			
			//当前任务的完成情况
			int accuCount = integralRuleTask.getAccumulativeAcount();
			boolean isReceive = integralRuleTask.getIsReceive();

			// 全局任务、日常任务、新手任务 第一次做任务，(没有领取学币，完成任务数为0－－)积累数重置为1
			if ( (  TaskType.overallTask == integralRule.getTaskType()
					|| TaskType.dailyTask == integralRule.getTaskType() 
					|| TaskType.newTask == integralRule.getTaskType()
				) && accuCount == 0 && !isReceive) {
				integralRuleTask.setAccumulativeAcount(1);
			}
			// 全局任务，并且还没有领取学币，积累数加1
			else if (TaskType.overallTask == integralRule.getTaskType()
					 && !isReceive) {
				integralRuleTask.setAccumulativeAcount(accuCount + 1);
			}
			// 日常任务,并且以前已经领取过学币，领取学币重置为false
			else if (TaskType.dailyTask == integralRule.getTaskType()
					&& days >= 1 && isReceive) {
				integralRuleTask.setIsReceive(false);
			}

			integralRuleTask.setModifyDate(new Date());
			integralRuleTaskService.update(integralRuleTask);
		}
	}

	private static void receiveTask(Long memberId, String urlPath) {
		
		//urlpath是唯一的
		boolean isExistTask = integralRuleTaskService.isExistTask(memberId,
				urlPath);
		// 如果不存在任务，先获取任务
		if (!isExistTask) {
			List<IntegralRule> integralRules = integralRuleService
					.findIntegralRuleByUrlPath(urlPath);
			IntegralRuleTask task;
			for (IntegralRule integralRule : integralRules) {
				task = new IntegralRuleTask();
				task.setIntegralRule(integralRule);
				task.setAccumulativeAcount(0);
				task.setMemberId(memberId);
				task.setCreateDate(new Date());
				task.setModifyDate(new Date());
				task.setUrlPath(urlPath);
				task.setIsReceive(false);
				integralRuleTaskService.save(task);
			}
		}
	}

	private static void handleLoginTask(Long memberId) {
		List<IntegralRuleTask> integralRuleTasks = integralRuleTaskService
				.findIntegralRuleTasks(memberId, Constants.LOGIN_URL);
		for (IntegralRuleTask integralRuleTask : integralRuleTasks) {
			IntegralRule integralRule = integralRuleTask.getIntegralRule();

			int days = DateUtils.getBetweenDays(new Date(),
					integralRuleTask.getModifyDate());

			int accuCount = integralRuleTask.getAccumulativeAcount();
			boolean isReceive = integralRuleTask.getIsReceive();

			// 全局任务、日常任务、新手任务 第一次登录，并且还没有领取学币，积累数重置为1
			if ((TaskType.overallTask == integralRule.getTaskType()
					|| TaskType.dailyTask == integralRule.getTaskType() || TaskType.newTask == integralRule
					.getTaskType()) && accuCount == 0 && !isReceive) {
				integralRuleTask.setAccumulativeAcount(1);
			}
			// 全局任务，间隔1天，并且还没有领取学币，积累数加1
			else if (TaskType.overallTask == integralRule.getTaskType()
					&& days == 1 && !isReceive) {
				integralRuleTask.setAccumulativeAcount(accuCount + 1);
			}
			// 全局任务，间隔大于1天，并且还没有领取学币，积累数重置为1
			else if (TaskType.overallTask == integralRule.getTaskType()
					&& days > 1 && !isReceive) {
				integralRuleTask.setAccumulativeAcount(1);
			}
			// 日常任务,间隔大于等于1天,并且以前已经领取过学币，领取学币重置为false
			else if (TaskType.dailyTask == integralRule.getTaskType()
					&& days >= 1 && isReceive) {
				integralRuleTask.setIsReceive(false);
			}

			integralRuleTask.setModifyDate(new Date());
			integralRuleTaskService.update(integralRuleTask);
		}
	}

	private static void handleCourseFavoriteTask(Long memberId) {
		List<IntegralRuleTask> integralRuleTasks = integralRuleTaskService
				.findIntegralRuleTasks(memberId, Constants.COURSE_FAVORITE_URL);
		for (IntegralRuleTask integralRuleTask : integralRuleTasks) {
			IntegralRule integralRule = integralRuleTask.getIntegralRule();

			int accuCount = integralRuleTask.getAccumulativeAcount();
			boolean isReceive = integralRuleTask.getIsReceive();

			// 新手任务 第一次课程收藏，并且还没有领取学币，积累数重置为1
			if (TaskType.newTask == integralRule.getTaskType()
					&& accuCount == 0 && !isReceive) {
				integralRuleTask.setAccumulativeAcount(1);
			}
			integralRuleTask.setModifyDate(new Date());
			integralRuleTaskService.update(integralRuleTask);
		}
	}
}
