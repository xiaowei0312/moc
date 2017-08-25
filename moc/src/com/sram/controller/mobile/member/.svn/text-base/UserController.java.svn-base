package com.sram.controller.mobile.member;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.sram.Constants;
import com.sram.Message;
import com.sram.Principal;
import com.sram.Setting;
import com.sram.Setting.AccountLockType;
import com.sram.Setting.CaptchaType;
import com.sram.controller.mobile.BaseController;
import com.sram.entity.Member;
import com.sram.service.MemberRankService;
import com.sram.service.MemberService;
import com.sram.util.IntegralRuleHandleUtil;
import com.sram.util.PasswordUtils;
import com.sram.util.SettingUtils;
import com.sram.util.WebUtils;

/**
 * 手机端的用户管理
 * @author Administrator
 *
 */
@Controller("mobileUser")
@RequestMapping("/mobile/user")
public class UserController extends BaseController {
	@Resource(name = "memberServiceImpl")
	private MemberService memberService;
	
	@Resource(name = "memberRankServiceImpl")
	private MemberRankService memberRankService;
	
	/**
	 * 注册app用户,
	 * @param member
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public void register(Member member,HttpServletResponse response){
		PrintWriter out=null;
		try {
		    response.setContentType("text/json"); 
			response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
		 	String salt=UUID.randomUUID().toString().replace("-", "");
			member.setSalt(salt);
			member.setPassword(PasswordUtils.encodePassword(member.getPassword(), salt));
			member.setIsEnabled(true);
			member.setMemberRank(memberRankService.find((long) 1));
			member.setAmount(new BigDecimal("0.0"));
			member.setBalance(new BigDecimal("0.0"));
			
			memberService.save(member);
			Map<String,String> map = new HashMap<String,String>();
			map.put("status", "0");
			map.put("msg", "注册成功");
			out.print(JSON.toJSON(map).toString());
			if(out!=null){
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> map = new HashMap<String,String>();
			map.put("status", "1");
			map.put("msg", "注册失败");
			out.print(JSON.toJSON(map).toString());
			if(out!=null){
				out.close();
			}
		}
	}
	
	/**
	 * 手机端登录
	 * @param member
	 * @param response
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public void login(Member member,HttpServletResponse response){
			PrintWriter out=null;
			String password=member.getPassword();
			 try {
				 response.setContentType("text/json"); 
				 response.setCharacterEncoding("utf-8");
				 out = response.getWriter();
				 Setting setting = SettingUtils.get();
					if (setting.getIsEmailLogin() && member.getUsername().contains("@")) {
						List<Member> members = memberService.findListByEmail(member.getUsername());
						if (members.isEmpty()) {
							member = null;
						} else if (members.size() == 1) {
							member = members.get(0);
						} else {
							Map<String,String> map = new HashMap<String,String>();
							map.put("status", "0");
							map.put("msg", "该账号不支持E-mail登录");
							out.print(JSON.toJSON(map).toString());
							return;
						}
					} else {
						member = memberService.findByUsernameOrPhone(member.getUsername());
					}

					if (member == null) {
						Map<String,String> map = new HashMap<String,String>();
						map.put("status", "0");
						map.put("msg", "此账号不存在");
						out.print(JSON.toJSON(map).toString());
						return;
					}

					if (member.getLocked()) {
						if (ArrayUtils.contains(setting.getAccountLockTypes(),
								AccountLockType.member)) {
							int loginFailureLockTime = setting.getAccountLockTime();
							if (loginFailureLockTime == 0) {
								Map<String,String> map = new HashMap<String,String>();
								map.put("status", "0");
								map.put("msg", "此账号已被锁定");
								out.print(JSON.toJSON(map).toString());
								return;
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
								Map<String,String> map = new HashMap<String,String>();
								map.put("status", "0");
								map.put("msg", "此账号已被锁定");
								out.print(JSON.toJSON(map).toString());
								return;
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
						if (ArrayUtils.contains(setting.getAccountLockTypes(),AccountLockType.member)) {
							Map<String,String> map = new HashMap<String,String>();
							map.put("status", "0");
							map.put("msg", "密码错误，若连续5次密码错误账号将被锁定");
							out.print(JSON.toJSON(map).toString());
							return;
						} else {
							Map<String,String> map = new HashMap<String,String>();
							map.put("status", "0");
							map.put("msg", "用户名或密码错误");
							out.print(JSON.toJSON(map).toString());
							return;
						}
					}
					member.setLoginDate(new Date());
					member.setLoginFailureCount(0);
					memberService.update(member);
					Map<String,String> map = new HashMap<String,String>();
					map.put("status", "0");
					map.put("msg", "登录成功");
					out.print(JSON.toJSON(map).toString());
					return;
			} catch (Exception e) {
				e.printStackTrace();
				Map<String,String> map = new HashMap<String,String>();
				map.put("status", "1");
				map.put("msg", "网络异常");
				out.print(JSON.toJSON(map).toString());
				return;
			}finally{
				if(out!=null){
					out.close();
				}
			}
	}
	
	
	/**
	 * 检查app的userName是否存在
	 * @param userName
	 */
	@RequestMapping(value="/checkUserName",method=RequestMethod.GET)
	public void checkUserName(String userName,HttpServletResponse response){
		PrintWriter out=null;
		try {
		    response.setContentType("text/json"); 
			response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
			boolean flag = memberService.usernameExists(userName);
			Map<String,String> map = new HashMap<String,String>();
			if(flag){
				map.put("status", "0");
				map.put("msg", "已注册");
			}else{
				map.put("status", "0");
				map.put("msg", "未注册");
			}
			out.print( JSON.toJSON(map).toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> map = new HashMap<String,String>();
			map.put("status", "1");
			map.put("msg", "请求失败");
			out.print( JSON.toJSON(map).toString());
		}
	}
	/**
	 * 检查app的email是否存在
	 * @param email
	 */
	@RequestMapping(value="/checkEmail",method=RequestMethod.GET)
	public void checkEmail(String email,HttpServletResponse response){
		PrintWriter out=null;
		try {
		    response.setContentType("text/json"); 
			response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
			boolean flag = memberService.emailExists(email);
			Map<String,String> map = new HashMap<String,String>();
			if(flag){
				map.put("status", "0");
				map.put("msg", "已注册");
			}else{
				map.put("status", "0");
				map.put("msg", "未注册");
			}
			out.print( JSON.toJSON(map).toString());
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> map = new HashMap<String,String>();
			map.put("status", "1");
			map.put("msg", "请求失败");
			out.print( JSON.toJSON(map).toString());
		}
	}
	
	/**
	 * 手机端设置用户的报考大纲和报考地区
	 * @param member
	 * @param response
	 */
	@RequestMapping(value="/updateExamAreaAndOutline",method=RequestMethod.POST)
	public void login(String username,Long examOutlineCategoryId, Long examAreaId,HttpServletResponse response){
		PrintWriter out=null;
		try {
		    response.setContentType("text/json"); 
			response.setCharacterEncoding("utf-8");
			 out = response.getWriter();
			memberService.updateExamAreaAndExamOutlineCategory(username, examOutlineCategoryId, examAreaId);
			Map<String,String> map = new HashMap<String,String>();
			map.put("status", "0");
			map.put("msg", "更新成功");
			out.print(JSON.toJSON(map).toString());
			return;
		} catch (Exception e) {
			e.printStackTrace();
			Map<String,String> map = new HashMap<String,String>();
			map.put("status", "1");
			map.put("msg", "请求失败");
			out.print( JSON.toJSON(map).toString());
		}
	}
	
}
