/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.controller.moc.member;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.Principal;
import com.sram.Setting;
import com.sram.controller.moc.BaseController;
import com.sram.entity.Member;
import com.sram.service.MemberService;
import com.sram.util.PasswordUtils;
import com.sram.util.SettingUtils;

/**
 * Controller - 会员中心 - 密码
 * 
 * @author Sram Team
 * @version 1.0
 */
@Controller("mocpMemberPasswordController")
@RequestMapping("/member/password")
public class PasswordController extends BaseController {

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;

	/**
	 * 验证当前密码
	 */
	@RequestMapping(value = "/check_current_password", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkCurrentPassword(String oldpassword) {
		if (StringUtils.isEmpty(oldpassword)) {
			return false;
		}
		Member member = memberService.getCurrent();
		if (StringUtils.equals(PasswordUtils.encodePassword(oldpassword, member.getSalt()), member.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(HttpServletRequest request, ModelMap model) {
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		if(principal!=null){
			model.addAttribute("headImage",principal.getHeadImage());
		}
		return "moc/member/password/edit";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(String oldpassword, String password, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (StringUtils.isEmpty(password) || StringUtils.isEmpty(oldpassword)) {
			return ERROR_VIEW;
		}
		if (!isValid(Member.class, "password", password)) {
			return ERROR_VIEW;
		}
		Setting setting = SettingUtils.get();
		if (password.length() < setting.getPasswordMinLength() || password.length() > setting.getPasswordMaxLength()) {
			return ERROR_VIEW;
		}
		Member member = memberService.getCurrent();
		if (!StringUtils.equals(PasswordUtils.encodePassword(oldpassword, member.getSalt()), member.getPassword())) {
			return ERROR_VIEW;
		}
		member.setPassword(PasswordUtils.encodePassword(password, member.getSalt()));
		memberService.update(member);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:edit.jhtml";
	}

}