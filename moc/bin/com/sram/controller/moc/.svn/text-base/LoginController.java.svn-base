/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.controller.moc;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import com.sram.Constants;
import com.sram.Filter;
import com.sram.Filter.Operator;
import com.sram.Message;
import com.sram.Principal;
import com.sram.Setting;
import com.sram.Setting.AccountLockType;
import com.sram.Setting.CaptchaType;
import com.sram.controller.moc.BaseController;
import com.sram.entity.Member;
import com.sram.entity.MemberRank;
import com.sram.service.CaptchaService;
import com.sram.service.MemberRankService;
import com.sram.service.MemberService;
import com.sram.service.RSAService;
import com.sram.util.IntegralRuleHandleUtil;
import com.sram.util.PasswordUtils;
import com.sram.util.SettingUtils;
import com.sram.util.WebUtils;

/**
 * Controller - 会员登录
 * 
 * @author Sram Team
 * @version 1.0
 */
@Controller("mocLoginController")
@RequestMapping("/login")
public class LoginController extends BaseController {

	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	
	@Resource(name = "memberRankServiceImpl")
	private MemberRankService memberRankService;
	
	@Resource(name = "rsaServiceImpl")
	private RSAService rsaService;

	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;

	/**
	 * 登录页面
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(String redirectUrl, HttpServletRequest request,
			ModelMap model) {
		Setting setting = SettingUtils.get();
		if (redirectUrl != null
				&& !redirectUrl.equalsIgnoreCase(setting.getSiteUrl())
				&& !redirectUrl.startsWith(request.getContextPath() + "/")
				&& !redirectUrl.startsWith(setting.getSiteUrl() + "/")) {
			redirectUrl = null;
		}
		model.addAttribute("redirectUrl", redirectUrl);
		model.addAttribute("captchaId", UUID.randomUUID().toString());
		return "/moc/login/index";
	}

	/**
	 * 三方登录
	 */
	@RequestMapping(value = "/other")
	public void other(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		response.setContentType("text/html;charset=utf-8");
		try {
			response.sendRedirect(new Oauth().getAuthorizeURL(request));
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新用户
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/test2", method = RequestMethod.GET)
	private void findList() {
		List<Member> findList = memberService.findList(null, null);
		for (Member member : findList) {
			if (member.getSalt() != null) {
				continue;
			} else {
				String salt = UUID.randomUUID().randomUUID().toString()
						.replace("-", "");
				member.setSalt(salt);
				member.setPassword(PasswordUtils.encodePassword("123456", salt));
				memberService.update(member);
			}
		}
		System.out.println(findList.size());
	}

	/**
	 * 三方qq登录成功后
	 */
	@RequestMapping(value = "/afterOtherLogin",method=RequestMethod.GET)
	public String afterOtherLogin(ModelMap model, HttpSession session,
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes redirectAttributes) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		System.out.println("进入了三方登录");

		PrintWriter out = response.getWriter();
		try {
			AccessToken accessTokenObj = (new Oauth())
					.getAccessTokenByRequest(request);
			String accessToken = null, openID = null;
			// long tokenExpireIn = 0L;

			if (accessTokenObj.getAccessToken().equals("")) {
				// 我们的网站被CSRF攻击了或者用户取消了授权
				// 做一些数据统计工作
				return Message.error("没有获取到响应参数").toString();
			} else {
				accessToken = accessTokenObj.getAccessToken();
				// tokenExpireIn = accessTokenObj.getExpireIn();

				// 利用accessToken获取当前用的openid
				OpenID openIDobj = new OpenID(accessToken);
				openID = openIDobj.getUserOpenID();

				System.out.println(openID + "-----------------");
				System.out.println(request.getRequestDispatcher("/").toString()+"---------");
				System.out.println(request.getPathInfo()+"--------");
				System.out.println(request.getRequestURL().toString());
				// 是否存在该账号
				Filter filter = new Filter();
				filter.setProperty("qq");
				filter.setValue(openID);
				filter.setOperator(Operator.eq);
				boolean b = memberService.exists(filter);
				Member member = new Member();
				if (!b) {

					// 新建用户openId----

					// 获取该用户的昵称
					UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
					UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
					if (userInfoBean.getRet() == 0) {

						SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMDD");
						int i=0;
						String username;
						while(true){
							username= "qq_"+userInfoBean.getNickname()+"_"+i+sdf.format(new Date());
							boolean existsName = memberService.usernameExists(username);
							if(!existsName){
								break;
							}
							i++;
						}
						member.setUsername(username);
					} else {

						// 很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
						return ERROR_VIEW;
					}
					
					System.out.println(userInfoBean.getGender());
					member.setQq(openID);
					member.setType("qq");
					member.setLoginIp(request.getRemoteAddr());
					member.setLoginDate(new Date());
					member.setLoginFailureCount(0);
					member.setIsEnabled(true);
					member.setAmount(BigDecimal.ZERO);
					member.setBalance(BigDecimal.ZERO);
					member.setLocked(false);
					member.setEmail("");
					member.setPassword("");
					member.setRegisterIp(request.getRemoteAddr());
					member.setMemberRank(memberRankService.find((long) 1));
					memberService.save(member);
				} else {
					List<Filter> filters = new ArrayList<Filter>();
					filters.add(filter);
					member = memberService.findList(1, filters, null).get(0);
				}

				session.invalidate();
				session = request.getSession();
				session.setAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME,
						new Principal(member.getId(), member.getUsername(),
								member.getHeadImage()));

				WebUtils.addCookie(request, response,
						Member.USERNAME_COOKIE_NAME, member.getUsername());
			}

		} catch (QQConnectException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//登录成功处理积分任务
		IntegralRuleHandleUtil.dispatchTask(request, response,
				Constants.LOGIN_URL);
		return "/moc/index";
	}

	/**
	 * 登录提交
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public @ResponseBody
	Message submit(String captchaId, String captcha, String username,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		String password = rsaService.decryptParameter("enPassword", request);
		rsaService.removePrivateKey(request);

		if (!captchaService
				.isValid(CaptchaType.memberLogin, captchaId, captcha)) {
			return Message.error("验证码输入错误");
		}
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			return Message.error("参数错误");
		}
		Member member;
		Setting setting = SettingUtils.get();
		if (setting.getIsEmailLogin() && username.contains("@")) {
			List<Member> members = memberService.findListByEmail(username);
			if (members.isEmpty()) {
				member = null;
			} else if (members.size() == 1) {
				member = members.get(0);
			} else {
				return Message.error("该账号不支持E-mail登录");
			}
		} else {
			member = memberService.findByUsernameOrPhone(username);
		}

		if (member == null) {
			return Message.error("此账号不存在");
		}

		if (member.getLocked()) {
			if (ArrayUtils.contains(setting.getAccountLockTypes(),
					AccountLockType.member)) {
				int loginFailureLockTime = setting.getAccountLockTime();
				if (loginFailureLockTime == 0) {
					return Message.error("此账号已被锁定");
				}
				Date lockedDate = member.getLockedDate();
				Date unlockDate = DateUtils.addMinutes(lockedDate,
						loginFailureLockTime);
				if (new Date().after(unlockDate)) {
					member.setLoginFailureCount(0);
					member.setLocked(false);
					member.setLockedDate(null);
					memberService.update(member);
				} else {
					return Message.error("此账号已被锁定");
				}
			} else {
				member.setLoginFailureCount(0);
				member.setLocked(false);
				member.setLockedDate(null);
				memberService.update(member);
			}
		}
		if (!PasswordUtils.encodePassword(password, member.getSalt()).equals(
				member.getPassword())) {
			int loginFailureCount = member.getLoginFailureCount() + 1;
			if (loginFailureCount >= setting.getAccountLockCount()) {
				member.setLocked(true);
				member.setLockedDate(new Date());
			}
			member.setLoginFailureCount(loginFailureCount);
			memberService.update(member);
			if (ArrayUtils.contains(setting.getAccountLockTypes(),
					AccountLockType.member)) {
				return Message.error("moc.login.accountLockCount",
						setting.getAccountLockCount());
			} else {
				return Message.error("用户名或密码错误");
			}
		}
		member.setLoginIp(request.getRemoteAddr());
		member.setLoginDate(new Date());
		member.setLoginFailureCount(0);
		memberService.update(member);

		session.invalidate();
		session = request.getSession();

		session.setAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME, new Principal(
				member.getId(), username, member.getHeadImage()));

		WebUtils.addCookie(request, response, Member.USERNAME_COOKIE_NAME,
				member.getUsername());

		//登录成功处理积分任务
		IntegralRuleHandleUtil.dispatchTask(request, response,
				Constants.LOGIN_URL);
		return SUCCESS_MESSAGE;
	}

}