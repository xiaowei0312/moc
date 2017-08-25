package com.sram.service;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sram.Constants;
import com.sram.Constants.ShareType;
import com.sram.entity.IntegralRule;
import com.sram.entity.IntegralRule.TaskType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/applicationContext-test.xml" })
public class IntegralRuleServiceImplTest {

	@Resource(name = "integralRuleServiceImpl")
	private IntegralRuleService integralRuleService;

	/**
	 * 初始化数据
	 */
	@Test
	public void testInit() {
		/**
		 * 新手任务
		 */
		//有头像有资料
		IntegralRule integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(1);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("有头像有资料");
		integralRule.setTaskCount(1);
		integralRule.setTaskType(TaskType.newTask);
		integralRule.setUrlPath(Constants.MEMBERINFO_ADD_URL);

		integralRuleService.save(integralRule);
		
		//分享课程给QQ好友
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(1);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(100);
		integralRule.setModifyDate(new Date());
		integralRule.setName("分享课程给QQ好友");
		integralRule.setTaskCount(1);
		integralRule.setTaskType(TaskType.newTask);
		integralRule.setUrlPath(Constants.COURSE_SHARE_URL+ShareType.sqq);
		
		integralRuleService.save(integralRule);
		
		//分享课程到朋友圈
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(1);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(200);
		integralRule.setModifyDate(new Date());
		integralRule.setName("分享课程到朋友圈");
		integralRule.setTaskCount(1);
		integralRule.setTaskType(TaskType.newTask);
		integralRule.setUrlPath(Constants.COURSE_SHARE_URL+ShareType.weixin);
		
		integralRuleService.save(integralRule);
		
		//分享课程到新浪webo
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(1);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(100);
		integralRule.setModifyDate(new Date());
		integralRule.setName("分享课程到新浪webo");
		integralRule.setTaskCount(1);
		integralRule.setTaskType(TaskType.newTask);
		integralRule.setUrlPath(Constants.COURSE_SHARE_URL+ShareType.tsina);
		
		integralRuleService.save(integralRule);
		
		//分享课程到QQ空间
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(1);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(200);
		integralRule.setModifyDate(new Date());
		integralRule.setName("分享课程到QQ空间");
		integralRule.setTaskCount(1);
		integralRule.setTaskType(TaskType.newTask);
		integralRule.setUrlPath(Constants.COURSE_SHARE_URL+ShareType.qzone);
		
		integralRuleService.save(integralRule);
		
		//首次登录
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(1);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("首次登录");
		integralRule.setTaskCount(1);
		integralRule.setTaskType(TaskType.newTask);
		integralRule.setUrlPath(Constants.LOGIN_URL);

		integralRuleService.save(integralRule);
		
		//首次收藏课程
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(1);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("首次收藏课程");
		integralRule.setTaskCount(1);
		integralRule.setTaskType(TaskType.newTask);
		integralRule.setUrlPath(Constants.COURSE_FAVORITE_URL);

		integralRuleService.save(integralRule);
		
		/**
		 * 日常任务
		 */
		
		//评测做做做
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(5);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(1);
		integralRule.setModifyDate(new Date());
		integralRule.setName("评测做做做");
		integralRule.setTaskCount(1);
		integralRule.setTaskType(TaskType.dailyTask);
		integralRule.setUrlPath(Constants.COURSE_EXERCISE_URL);
		
		integralRuleService.save(integralRule);
		
		//每日登录
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(5);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(1);
		integralRule.setModifyDate(new Date());
		integralRule.setName("每日登录");
		integralRule.setTaskCount(1);
		integralRule.setTaskType(TaskType.dailyTask);
		integralRule.setUrlPath(Constants.LOGIN_URL);
		
		integralRuleService.save(integralRule);
		
		//看个视频
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(5);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(1);
		integralRule.setModifyDate(new Date());
		integralRule.setName("看个视频");
		integralRule.setTaskCount(1);
		integralRule.setTaskType(TaskType.dailyTask);
		integralRule.setUrlPath(Constants.COURSE_SEE_VIDEO);
		
		integralRuleService.save(integralRule);
		
		//分享一个课程到朋友圈
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(5);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(1);
		integralRule.setModifyDate(new Date());
		integralRule.setName("分享一个课程到朋友圈");
		integralRule.setTaskCount(1);
		integralRule.setTaskType(TaskType.dailyTask);
		integralRule.setUrlPath(Constants.COURSE_SHARE_URL+ShareType.weixin);

		integralRuleService.save(integralRule);
		
		
		//分享一个课程到QQ空间
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(5);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(1);
		integralRule.setModifyDate(new Date());
		integralRule.setName("分享一个课程到QQ空间");
		integralRule.setTaskCount(1);
		integralRule.setTaskType(TaskType.dailyTask);
		integralRule.setUrlPath(Constants.COURSE_SHARE_URL+ShareType.qzone);
		
		integralRuleService.save(integralRule);

		/**
		 * 全局任务
		 */
		//分享3个课程给QQ好友
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(30);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("分享3个课程给QQ好友");
		integralRule.setTaskCount(3);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.COURSE_SHARE_URL+ShareType.sqq);
		
		integralRuleService.save(integralRule);
		
		//分享3个课程到朋友圈
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(30);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("分享3个课程到朋友圈");
		integralRule.setTaskCount(3);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.COURSE_SHARE_URL+ShareType.weixin);
		
		integralRuleService.save(integralRule);
		
		//分享3个课程给QQ空间
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(30);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("分享3个课程给QQ空间");
		integralRule.setTaskCount(3);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.COURSE_SHARE_URL+ShareType.qzone);
		
		integralRuleService.save(integralRule);
		
		//分享3个课程到新浪
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(30);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("分享3个课程到新浪");
		integralRule.setTaskCount(3);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.COURSE_SHARE_URL+ShareType.tsina);
		
		integralRuleService.save(integralRule);
		
		//分享5个课程给QQ好友
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(30);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("分享5个课程给QQ好友");
		integralRule.setTaskCount(5);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.COURSE_SHARE_URL+ShareType.sqq);
		
		integralRuleService.save(integralRule);
		
		//分享5个课程给QQ空间
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(30);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("分享5个课程给QQ空间");
		integralRule.setTaskCount(5);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.COURSE_SHARE_URL+ShareType.qzone);
		
		integralRuleService.save(integralRule);
		
		//分享5个课程到朋友圈
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(30);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("分享5个课程到朋友圈");
		integralRule.setTaskCount(5);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.COURSE_SHARE_URL+ShareType.weixin);
		
		integralRuleService.save(integralRule);
		
		//分享5个课程到新浪
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(30);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("分享5个课程到新浪");
		integralRule.setTaskCount(5);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.COURSE_SHARE_URL+ShareType.tsina);
		
		integralRuleService.save(integralRule);
		
		//分享7个课程给QQ好友
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(30);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("分享7个课程给QQ好友");
		integralRule.setTaskCount(7);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.COURSE_SHARE_URL+ShareType.sqq);
		
		integralRuleService.save(integralRule);
		
		//分享7个课程给QQ空间
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(30);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("分享7个课程给QQ空间");
		integralRule.setTaskCount(7);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.COURSE_SHARE_URL+ShareType.qzone);
		
		integralRuleService.save(integralRule);
		
		//分享7个课程到朋友圈
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(30);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("分享7个课程到朋友圈");
		integralRule.setTaskCount(7);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.COURSE_SHARE_URL+ShareType.weixin);
		
		integralRuleService.save(integralRule);
		
		//分享7个课程到新浪
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(30);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("分享7个课程到新浪");
		integralRule.setTaskCount(7);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.COURSE_SHARE_URL+ShareType.tsina);
		
		integralRuleService.save(integralRule);
		
		//做3个评测
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(30);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("做3个评测");
		integralRule.setTaskCount(3);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.COURSE_EXERCISE_URL);
		
		integralRuleService.save(integralRule);
		
		//做5个评测
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(30);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("做5个评测");
		integralRule.setTaskCount(5);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.COURSE_EXERCISE_URL);
		
		integralRuleService.save(integralRule);
		
		//做10个评测
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(30);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("做10个评测");
		integralRule.setTaskCount(10);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.COURSE_EXERCISE_URL);
		
		integralRuleService.save(integralRule);
		
		//连续登录7天
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(30);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(10);
		integralRule.setModifyDate(new Date());
		integralRule.setName("连续登录7天");
		integralRule.setTaskCount(7);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.LOGIN_URL);

		integralRuleService.save(integralRule);

		//连续登录30天
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(100);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(50);
		integralRule.setModifyDate(new Date());
		integralRule.setName("连续登录30天");
		integralRule.setTaskCount(30);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.LOGIN_URL);

		integralRuleService.save(integralRule);
		
		//连续登录60天
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(300);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(100);
		integralRule.setModifyDate(new Date());
		integralRule.setName("连续登录60天");
		integralRule.setTaskCount(60);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.LOGIN_URL);

		integralRuleService.save(integralRule);

		//连续登录100天
		integralRule = new IntegralRule();
		integralRule.setCreateDate(new Date());
		integralRule.setIsSetEvDailyLimit(false);
		integralRule.setExperienceValue(1000);
		integralRule.setIsSetLcDailyLimit(false);
		integralRule.setLearningCoin(200);
		integralRule.setModifyDate(new Date());
		integralRule.setName("连续登录100天");
		integralRule.setTaskCount(100);
		integralRule.setTaskType(TaskType.overallTask);
		integralRule.setUrlPath(Constants.LOGIN_URL);
		
		integralRuleService.save(integralRule);

	}
}
