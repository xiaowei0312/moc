package com.sram.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sram.util.TokenGeneratorUtils;

@Controller("tokenController")
@RequestMapping("/token")
public class TokenController extends BaseController {

	/**
	 * 根据用户名获取Token
	 * @param userName
	 * @param model
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/retrieveToken", method = RequestMethod.GET)
	public void retrieveToken(String userName, ModelMap model,
			HttpServletResponse response) throws IOException {
		String token = "";
		if (null != userName) {
			token = TokenGeneratorUtils.generatorToken(userName);
		}
		response.getWriter().print(token);
	}

}
