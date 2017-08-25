/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.controller.moc;

import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sram.Message;
import com.sram.Setting;
import com.sram.Setting.CaptchaType;
import com.sram.entity.BaseEntity.Save;
import com.sram.entity.Member;
import com.sram.entity.SafeKey;
import com.sram.service.CaptchaService;
import com.sram.service.MailService;
import com.sram.service.MemberService;
import com.sram.util.PasswordUtils;
import com.sram.util.SettingUtils;

/**
 * Controller - 密码
 * 
 * @author Sram Team
 * @version 1.0
 */
@Controller("mocPasswordController")
@RequestMapping("/password")
public class PasswordController extends BaseController {

	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "mailServiceImpl")
	private MailService mailService;

	/**
	 * 找回密码
	 */
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public String find(Model model) {
		model.addAttribute("captchaId", UUID.randomUUID().toString());
		return "/moc/password/find";
	}

	/**
	 * 找回密码提交
	 */
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public @ResponseBody
	Message find(String captchaId, String captcha, String username, String email) {
		if (!captchaService.isValid(CaptchaType.findPassword, captchaId, captcha)) {
			return Message.error("moc.captcha.invalid");
		}
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(email)) {
			return Message.error("moc.common.invalid");
		}
		Member member = memberService.findByUsernameOrPhone(username);
		if (member == null) {
			return Message.error("moc.password.memberNotExist");
		}
		if (!member.getEmail().equalsIgnoreCase(email)) {
			return Message.error("moc.password.invalidEmail");
		}
		Setting setting = SettingUtils.get();
		SafeKey safeKey = new SafeKey();
		safeKey.setValue(UUID.randomUUID().toString() + DigestUtils.md5Hex(RandomStringUtils.randomAlphabetic(30)));
		safeKey.setExpire(setting.getSafeKeyExpiryTime() != 0 ? DateUtils.addMinutes(new Date(), setting.getSafeKeyExpiryTime()) : null);
		memberService.update(member);
		mailService.sendFindPasswordMail(member.getEmail(), member.getUsername(), safeKey);
		return Message.success("moc.password.mailSuccess");
	}

	/**
	 * 重置密码
	 */
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String reset(String username, String key, Model model) {
		Member member = memberService.findByUsernameOrPhone(username);
		if (member == null) {
			return ERROR_VIEW;
		}
		model.addAttribute("captchaId", UUID.randomUUID().toString());
		model.addAttribute("member", member);
		model.addAttribute("key", key);
		return "/moc/password/reset";
	}

	/**
	 * 重置密码提交
	 */
	@RequestMapping(value = "reset", method = RequestMethod.POST)
	public @ResponseBody
	Message reset(String captchaId, String captcha, String username, String newPassword, String key) {
		if (!captchaService.isValid(CaptchaType.resetPassword, captchaId, captcha)) {
			return Message.error("moc.captcha.invalid");
		}
		Member member = memberService.findByUsernameOrPhone(username);
		if (member == null) {
			return ERROR_MESSAGE;
		}
		if (!isValid(Member.class, "password", newPassword, Save.class)) {
			return Message.warn("moc.password.invalidPassword");
		}
		Setting setting = SettingUtils.get();
		if (newPassword.length() < setting.getPasswordMinLength() || newPassword.length() > setting.getPasswordMaxLength()) {
			return Message.warn("moc.password.invalidPassword");
		}
		if(member.getSalt()==null || ("").equals(member.getSalt())){
			String salt=UUID.randomUUID().toString().replace("-", "");
			member.setSalt(salt);
		}
		//member.setPassword(DigestUtils.md5Hex(newPassword));
		member.setPassword(PasswordUtils.encodePassword(newPassword, member.getSalt()));
		memberService.update(member);
		return Message.success("moc.password.resetSuccess");
	}

}