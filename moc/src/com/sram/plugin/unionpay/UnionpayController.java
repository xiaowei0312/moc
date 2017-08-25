/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.plugin.unionpay;

import java.math.BigDecimal;

import javax.annotation.Resource;


import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.Message;
import com.sram.controller.admin.BaseController;
import com.sram.entity.PluginConfig;
import com.sram.plugin.PaymentPlugin;
import com.sram.plugin.PaymentPlugin.FeeType;
import com.sram.service.PluginConfigService;

/**
 * Controller - 银联在线支付
 * 
 * @author Sram Team
 * @version 1.0
 */
@Controller("adminUnionpayController")
@RequestMapping("/admin/payment_plugin/unionpay")
public class UnionpayController extends BaseController {

	@Resource(name = "unionpayPlugin")
	private UnionpayPlugin unionpayPlugin;
	@Resource(name = "pluginConfigServiceImpl")
	private PluginConfigService pluginConfigService;

	/**
	 * 安装
	 */
	@RequestMapping(value = "/install", method = RequestMethod.POST)
	public @ResponseBody
	Message install() {
		if (!unionpayPlugin.getIsInstalled()) {
			PluginConfig pluginConfig = new PluginConfig();
			pluginConfig.setPluginId(unionpayPlugin.getId());
			pluginConfig.setIsEnabled(false);
			pluginConfigService.save(pluginConfig);
		}
		return SUCCESS_MESSAGE;
	}

	/**
	 * 卸载
	 */
	@RequestMapping(value = "/uninstall", method = RequestMethod.POST)
	public @ResponseBody
	Message uninstall() {
		if (unionpayPlugin.getIsInstalled()) {
			PluginConfig pluginConfig = unionpayPlugin.getPluginConfig();
			pluginConfigService.delete(pluginConfig);
		}
		return SUCCESS_MESSAGE;
	}

	/**
	 * 设置
	 */
	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public String setting(ModelMap model) {
		PluginConfig pluginConfig = unionpayPlugin.getPluginConfig();
		model.addAttribute("feeTypes", FeeType.values());
		model.addAttribute("pluginConfig", pluginConfig);
		return "/com/sram/plugin/unionpay/setting";
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(String paymentName, String partner, String key, FeeType feeType, BigDecimal fee, String logo, String description, @RequestParam(defaultValue = "false") Boolean isEnabled, Integer order, RedirectAttributes redirectAttributes) {
		PluginConfig pluginConfig = unionpayPlugin.getPluginConfig();
		pluginConfig.setAttribute(PaymentPlugin.PAYMENT_NAME_ATTRIBUTE_NAME, paymentName);
		pluginConfig.setAttribute("partner", partner);
		pluginConfig.setAttribute("key", key);
		pluginConfig.setAttribute(PaymentPlugin.FEE_TYPE_ATTRIBUTE_NAME, feeType.toString());
		pluginConfig.setAttribute(PaymentPlugin.FEE_ATTRIBUTE_NAME, fee.toString());
		pluginConfig.setAttribute(PaymentPlugin.LOGO_ATTRIBUTE_NAME, logo);
		pluginConfig.setAttribute(PaymentPlugin.DESCRIPTION_ATTRIBUTE_NAME, description);
		pluginConfig.setIsEnabled(isEnabled);
		pluginConfig.setOrder(order);
		pluginConfigService.update(pluginConfig);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:/admin/payment_plugin/list.jhtml";
	}

}