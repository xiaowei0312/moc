package com.sram.controller.moc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("mocConsultController")
@RequestMapping("/consult")
public class ConsultController extends BaseController{
	
	/**
	 * 关于我们
	 */
	@RequestMapping(value="/aboutUs")
	public String aboutUs(ModelMap model){
		
		return "/moc/consult/aboutUs";
	}
	
	/**
	 * 联系我们
	 */
	@RequestMapping(value="/contactUs")
	public String contactUs(ModelMap model){
		
		return "/moc/consult/contactUs";
	}
	
	/**
	 * 帮助中心
	 */
	@RequestMapping(value="/helpCenter")
	public String helpCenter(ModelMap model){
		
		return "/moc/consult/helpCenter";
	}
	
	/**
	 * 意见反馈
	 */
	@RequestMapping(value="/feedBack")
	public String feedBack(){
		return "/moc/consult/feedBack";
	}
	
	/**
	 * 加入我们
	 */
	@RequestMapping(value="/joinUs")
	public String joinUs(){
		
		return "/moc/consult/joinUs";
	}
}
