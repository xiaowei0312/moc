package com.sram.controller.admin;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.entity.IntegralRule;
import com.sram.service.IntegralRuleService;

/**
 * 积分规则
 * @author fulndon
 *
 */
@Controller("adminIntegralRuleController")
@RequestMapping("/admin/integralRule")
public class IntegralRuleController extends BaseController{
	@Resource(name="integralRuleServiceImpl")
	private IntegralRuleService integralRuleService;
	
	/**
	 * 前往积分规则列表
	 */
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(ModelMap model){
		model.addAttribute("integralRules", integralRuleService.findAll());
		return "/admin/integral_rule/list";
	}
	
	/**
	 * 编辑积分规则页面
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit(Long integralRuleId,ModelMap model){
		model.addAttribute("integralRule",integralRuleService.find(integralRuleId));
		return "/admin/integral_rule/edit";
	}
	
	/**
	 * 更新积分规则
	 */
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(IntegralRule integralRule,RedirectAttributes redirectAttributes){
		
		integralRuleService.update(integralRule);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}
	
}
