/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.controller.admin;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sram.Message;
import com.sram.Pageable;
import com.sram.service.LogService;

/**
 * Controller - 管理日志
 * 
 * @author Sram Team
 * @version 1.0
 */
@Controller("adminLogController")
@RequestMapping("/admin/log")
public class LogController extends BaseController {

	@Resource(name = "logServiceImpl")
	private LogService logService;

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Pageable pageable, ModelMap model) {
		model.addAttribute("page", logService.findPage(pageable));
		return "/admin/log/list";
	}

	/**
	 * 查看
	 */
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String view(Long id, ModelMap model) {
		model.addAttribute("log", logService.find(id));
		return "/admin/log/view";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		logService.delete(ids);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 清空
	 */
	@RequestMapping(value = "/clear", method = RequestMethod.POST)
	public @ResponseBody
	Message clear() {
		logService.clear();
		return SUCCESS_MESSAGE;
	}

}