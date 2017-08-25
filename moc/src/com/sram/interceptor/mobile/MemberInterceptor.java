/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.interceptor.mobile;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sram.Constants;
import com.sram.Principal;
import com.sram.entity.Member;
import com.sram.util.JsonUtils;

/**
 * Interceptor - 会员权限
 * 
 * @author Sram Team
 * @version 1.0
 */
public class MemberInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		Principal principal = (Principal) session
				.getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
		if (principal != null) {
			return true;
		} else {
			Map<String, Object> outMap = new HashMap<String, Object>();
			outMap.put(Constants.RESPONSE, "notlogin");
			JsonUtils.renderJson(response, outMap);
			return false;
		}
	}

}