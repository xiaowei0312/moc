package com.sram.controller.moc;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sram.Message;
import com.sram.Setting;
import com.sram.Setting.CaptchaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.controller.moc.BaseController;
import com.sram.entity.BaseEntity.Save;
import com.sram.entity.Member;
import com.sram.service.CaptchaService;
import com.sram.service.MemberRankService;
import com.sram.service.MemberService;
import com.sram.service.RSAService;
import com.sram.util.PasswordUtils;
import com.sram.util.SettingUtils;



/**
 * Controller - 会员注册
 * 
 * @author Sram Team
 * @version 1.0
 */
@Controller("mocRegisterController")
@RequestMapping("/register")
public class RegisterController extends BaseController{
     
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	
	@Resource(name = "rsaServiceImpl")
	private RSAService rsaService;
	
	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;
	
	@Resource(name = "memberRankServiceImpl")
	private MemberRankService memberRankService;
	
	/**
	 * 检查用户名是否被禁用或已存在
	 */
	@RequestMapping(value = "/check_username", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkUsername(String username) {
		if (StringUtils.isEmpty(username)) {
			return false;
		}
		if (memberService.usernameDisabled(username) || memberService.usernameExists(username)) {
			 return false;
		} else {
			return true;
		}
	}

	/**
	 * 注册提交
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public @ResponseBody
	Message submit(String captchaId, String captcha,String username, String email, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String password = rsaService.decryptParameter("enPassword", request);
		rsaService.removePrivateKey(request);

		if (!captchaService.isValid(CaptchaType.memberRegister, captchaId, captcha)) {
			return Message.error("moc.captcha.invalid");
		}
		Setting setting = SettingUtils.get();
		if (!setting.getIsRegisterEnabled()) {
			return Message.error("moc.register.disabled");
		}
		if (!isValid(Member.class, "username", username, Save.class) || !isValid(Member.class, "password", password, Save.class) || !isValid(Member.class, "email", email, Save.class)) {
			return Message.error("moc.common.invalid");
		}
		if (username.length() < setting.getUsernameMinLength() || username.length() > setting.getUsernameMaxLength()) {
			return Message.error("moc.common.invalid");
		}
		if (password.length() < setting.getPasswordMinLength() || password.length() > setting.getPasswordMaxLength()) {
			return Message.error("moc.common.invalid");
		}
		if (memberService.usernameDisabled(username) || memberService.usernameExists(username)) {
			return Message.error("moc.register.disabledExist");
		}
		if (!setting.getIsDuplicateEmail() && memberService.emailExists(email)) {
			return Message.error("moc.register.emailExist");
		}
		String salt=UUID.randomUUID().toString().replace("-", "");
		Member member=new Member();
		member.setUsername(username);
		member.setSalt(salt);
		//member.setPassword(DigestUtils.md5Hex(password));
		member.setPassword(PasswordUtils.encodePassword(password, salt));
		member.setEmail(email);
		member.setIsEnabled(true);
		member.setRegisterIp(request.getRemoteAddr());
		member.setMemberRank(memberRankService.find((long) 1));
		member.setAmount(new BigDecimal("0.0"));
		member.setBalance(new BigDecimal("0.0"));
		
		memberService.save(member);
		return Message.success("moc.register.success");
	}
	
	/**
	 * 检查E-mail是否存在
	 */
	@RequestMapping(value = "/check_email", method = RequestMethod.GET)
	public @ResponseBody
	boolean checkEmail(String email) {
		if (StringUtils.isEmpty(email)) {
			return false;
		}
		if (memberService.emailExists(email)) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 注册页面
	 */
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model){
		model.addAttribute("captchaId", UUID.randomUUID().toString());
		return "/moc/register/index";
	}
	
}
