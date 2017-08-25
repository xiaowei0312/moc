package com.sram.controller.moc.member;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.Message;
import com.sram.Principal;
import com.sram.controller.moc.BaseController;
import com.sram.entity.IntegralRule;
import com.sram.entity.IntegralRuleTask;
import com.sram.entity.Member;
import com.sram.service.IntegralRuleService;
import com.sram.service.IntegralRuleTaskService;
import com.sram.service.MemberService;

/**
 * 积分任务
 * @author fulndon
 *
 */
@Controller("mocIntegralRuleController")
@RequestMapping("/member/integralRule")
public class IntegralRuleController extends BaseController{
	@Resource(name="integralRuleServiceImpl")
	private IntegralRuleService integralRuleService;
	@Resource(name="memberServiceImpl")
	private MemberService memberService;
	@Resource(name="integralRuleTaskServiceImpl")
	private IntegralRuleTaskService integralRuleTaskService;
	
	/**
	 * 前往积分任务列表
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(ModelMap model,HttpServletRequest request){
		
		Principal principal=(Principal)request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
		Long memberId=principal.getId();
		model.addAttribute("experienceCoin", memberService.getExperienceCoin(memberId));
		model.addAttribute("integralRules", integralRuleService.findAllTasks(memberId));
		model.addAttribute("headImage", principal.getHeadImage());
		return "moc/member/integral_rule/list";
	}
	
	/**
	 * 领取经验和学币
	 */
	@RequestMapping(value="/getExperienceCoin",method=RequestMethod.GET)
	public @ResponseBody
	Message getCoins(Long taskId,Integer experienceValue,Integer learningCoin,HttpServletRequest request){
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		Long memberId=principal.getId();
		memberService.updateExperienceCoin(experienceValue,learningCoin,memberId);
		integralRuleTaskService.updateIsReceive(taskId,true);
		return SUCCESS_MESSAGE;
	}
	/**
	 * 领取任务
	 */
	@RequestMapping(value="/getTasks",method=RequestMethod.GET)
	public @ResponseBody
	Message getTasks(IntegralRuleTask integralRuleTask,HttpServletRequest request){
		Principal principal=(Principal)request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
		Long memberId=principal.getId();
		
		integralRuleTask.setMemberId(memberId);
		integralRuleTask.setIsReceive(false);
		integralRuleTaskService.save(integralRuleTask);
		return SUCCESS_MESSAGE;
	}
}
