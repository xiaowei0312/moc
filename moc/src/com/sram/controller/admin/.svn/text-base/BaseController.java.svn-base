/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.controller.admin;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.DateEditor;
import com.sram.Message;
import com.sram.Principal;
import com.sram.Setting;
import com.sram.entity.Admin;
import com.sram.entity.Log;
import com.sram.service.AdminService;
import com.sram.template.directive.FlashMessageDirective;
import com.sram.util.SettingUtils;
import com.sram.util.SpringUtils;

import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;

/**
 * Controller - 基类
 * 
 * @author Sram Team
 * @version 1.0
 */
public class BaseController {

	/** 错误视图 */
	protected static final String ERROR_VIEW = "/admin/common/error";

	/** 错误消息 */
	protected static final Message ERROR_MESSAGE = Message
			.error("admin.message.error");

	/** 错误消息 */
	protected static final Message DELETE_PARENT_ERROR_MESSAGE = Message
			.error("admin.message.deleteParentError");

	/** 成功消息 */
	protected static final Message SUCCESS_MESSAGE = Message
			.success("admin.message.success");

	/** "验证结果"参数名称 */
	private static final String CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME = "constraintViolations";

	@Resource(name = "validator")
	private Validator validator;

	@Resource(name = "adminServiceImpl")
	private AdminService adminService;

	/**
	 * 数据绑定
	 * 
	 * @param binder
	 *            WebDataBinder
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		binder.registerCustomEditor(Date.class, new DateEditor(true));
	}

	/**
	 * 数据验证
	 * 
	 * @param target
	 *            验证对象
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Object target, Class<?>... groups) {
		Set<ConstraintViolation<Object>> constraintViolations = validator
				.validate(target, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			RequestAttributes requestAttributes = RequestContextHolder
					.currentRequestAttributes();
			requestAttributes.setAttribute(
					CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations,
					RequestAttributes.SCOPE_REQUEST);
			return false;
		}
	}

	/**
	 * 数据验证
	 * 
	 * @param type
	 *            类型
	 * @param property
	 *            属性
	 * @param value
	 *            值
	 * @param groups
	 *            验证组
	 * @return 验证结果
	 */
	protected boolean isValid(Class<?> type, String property, Object value,
			Class<?>... groups) {
		Set<?> constraintViolations = validator.validateValue(type, property,
				value, groups);
		if (constraintViolations.isEmpty()) {
			return true;
		} else {
			RequestAttributes requestAttributes = RequestContextHolder
					.currentRequestAttributes();
			requestAttributes.setAttribute(
					CONSTRAINT_VIOLATIONS_ATTRIBUTE_NAME, constraintViolations,
					RequestAttributes.SCOPE_REQUEST);
			return false;
		}
	}

	/**
	 * 货币格式化
	 * 
	 * @param amount
	 *            金额
	 * @param showSign
	 *            显示标志
	 * @param showUnit
	 *            显示单位
	 * @return 货币格式化
	 */
	protected String currency(BigDecimal amount, boolean showSign,
			boolean showUnit) {
		Setting setting = SettingUtils.get();
		String price = setting.setScale(amount).toString();
		if (showSign) {
			price = setting.getCurrencySign() + price;
		}
		if (showUnit) {
			price += setting.getCurrencyUnit();
		}
		return price;
	}

	/**
	 * 获取国际化消息
	 * 
	 * @param code
	 *            代码
	 * @param args
	 *            参数
	 * @return 国际化消息
	 */
	protected String message(String code, Object... args) {
		return SpringUtils.getMessage(code, args);
	}

	/**
	 * 添加瞬时消息
	 * 
	 * @param redirectAttributes
	 *            RedirectAttributes
	 * @param message
	 *            消息
	 */
	protected void addFlashMessage(RedirectAttributes redirectAttributes,
			Message message) {
		if (redirectAttributes != null && message != null) {
			redirectAttributes
					.addFlashAttribute(
							FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME,
							message);
		}
	}

	/**
	 * 添加日志
	 * 
	 * @param content
	 *            内容
	 */
	protected void addLog(String content) {
		if (content != null) {
			RequestAttributes requestAttributes = RequestContextHolder
					.currentRequestAttributes();
			requestAttributes.setAttribute(Log.LOG_CONTENT_ATTRIBUTE_NAME,
					content, RequestAttributes.SCOPE_REQUEST);
		}
	}

	/**
	 * 当前登录用户
	 * 
	 * @return
	 */
	protected Admin currentAdmin() {
		Admin admin = null;
		Subject subject = SecurityUtils.getSubject();
		if (subject != null) {
			Principal principal = (Principal) subject.getPrincipal();
			if (principal != null) {
				admin = new Admin();
				admin.setId(principal.getId());
				admin.setUsername(principal.getUsername());
			}
		}

		return admin;
	}

	/**
	 * SpringMVC FTL访问静态方法
	 */
	private final static BeansWrapper wrapper = BeansWrapper
			.getDefaultInstance();
	private final static TemplateHashModel staticModels = wrapper
			.getStaticModels();

	protected static TemplateHashModel useStaticPacker(String packname) {
		TemplateHashModel fileStatics = null;
		try {
			fileStatics = (TemplateHashModel) staticModels.get(packname);
		} catch (TemplateModelException e) {
			e.printStackTrace();
		}
		return fileStatics;
	};
}