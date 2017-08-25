package com.sram;

import org.springframework.beans.factory.annotation.Value;

/**
 * <p>
 * 功能:
 * </p>
 * 
 * @author 杨楷
 * @date 2015-3-16 下午05:39:00
 */
public class Constants {
	
	public enum Difficulty {
		easy, normal, hard
	}

	/**
	 * 题目类型
	 * single_choice,choice,uncertain_singlechoice,uncertain_choicee(0,1,2,3)
	 * determine,material,essay,blanks,entry(4,5,6,7,8)
	 */
	public enum QuestionType {
		single_choice, choice, uncertain_singlechoice, uncertain_choice, determine, material, essay, blanks, entry
	}

	/**
	 * 试卷类型<br/>
	 * intellexercise(快速智能练习), specialexercis(考点专项练习), genrationexam(组卷模考),
	 * oldexam(真题试卷),munualsimulation(模考试卷),other(其他试卷)
	 **/
	public enum TestpaperType {
		intellexercise, specialexercis, genrationexam, oldexam, munualsimulation, other
	}

	/** 考试通过状态，none表示该考试没有 */
	public enum PassedStatus {
		none, passed, unpassed
	}

	/** 考试结果状态，doing（考试中）,paused（考试暂停）,reviewing(待批阅),finished(批阅结束) */
	public enum Status {
		doing, paused, reviewing, finished
	}

	/**
	 * 考试条目结果状态 none,right,partRight,wrong,noAnswer
	 * 
	 */
	public enum ItemStatus {
		none, right, partRight, wrong, noAnswer
	}

	public static final String RESPONSE = "response";

	
	public enum ShareType{
		qzone,tsina,tqq,weixin,
		/**QQ好友*/
		sqq
	}
	/**
	 * 积分规则拦截路径
	 * 
	 */
	public static final String LOGIN_URL = "/login/submit";
	
	/**收藏课程*/
	public static final String COURSE_FAVORITE_URL = "/member/coursefavorite/collectCourse";
	
	/**资料信息完善*/
	public static final String MEMBERINFO_ADD_URL = "/member/profile/update";
	
	/**分享课程*/
	public static final String COURSE_SHARE_URL = "/course/share/";
	
	/**评测做做做*/
	public static final String COURSE_EXERCISE_URL = "/member/question/saveOldexm";
	
	/**看个视频*/
	public static final String COURSE_SEE_VIDEO = "/member/course/toMedia";

}
