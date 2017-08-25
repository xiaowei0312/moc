/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.controller.admin;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.CommonAttributes;
import com.sram.FileInfo.FileType;
import com.sram.Message;
import com.sram.Setting;
import com.sram.Setting.AccountLockType;
import com.sram.Setting.CaptchaType;
import com.sram.Setting.ConsultationAuthority;
import com.sram.Setting.ReviewAuthority;
import com.sram.Setting.RoundType;
import com.sram.Setting.StockAllocationTime;
import com.sram.Setting.WatermarkPosition;
import com.sram.service.CacheService;
import com.sram.service.FileService;
import com.sram.service.MailService;
import com.sram.service.StaticService;
import com.sram.util.JsonUtils;
import com.sram.util.SettingUtils;
import com.sun.mail.smtp.SMTPSendFailedException;
import com.sun.mail.smtp.SMTPSenderFailedException;

/**
 * Controller - 系统设置
 * 
 * @author Sram Team
 * @version 1.0
 */
@Controller("adminstingController")
@RequestMapping("/admin/setting")
public class SettingController extends BaseController {

	@Resource(name = "fileServiceImpl")
	private FileService fileService;
	@Resource(name = "mailServiceImpl")
	private MailService mailService;
	@Resource(name = "cacheServiceImpl")
	private CacheService cacheService;
	@Resource(name = "staticServiceImpl")
	private StaticService staticService;

	/**
	 * 邮件测试
	 */
	@RequestMapping(value = "/mail_test", method = RequestMethod.POST)
	public @ResponseBody
	Message mailTest(String smtpFromMail, String smtpHost, Integer smtpPort, String smtpUsername, String smtpPassword, String toMail) {
		if (StringUtils.isEmpty(toMail)) {
			return ERROR_MESSAGE;
		}
		Setting setting = SettingUtils.get();
		if (StringUtils.isEmpty(smtpPassword)) {
			smtpPassword = setting.getSmtpPassword();
		}
		try {
			if (!isValid(Setting.class, "smtpFromMail", smtpFromMail) || !isValid(Setting.class, "smtpHost", smtpHost) || !isValid(Setting.class, "smtpPort", smtpPort) || !isValid(Setting.class, "smtpUsername", smtpUsername)) {
				return ERROR_MESSAGE;
			}
			mailService.sendTestMail(smtpFromMail, smtpHost, smtpPort, smtpUsername, smtpPassword, toMail);
		} catch (MailSendException e) {
			Exception[] messageExceptions = e.getMessageExceptions();
			if (messageExceptions != null) {
				for (Exception exception : messageExceptions) {
					if (exception instanceof SMTPSendFailedException) {
						SMTPSendFailedException smtpSendFailedException = (SMTPSendFailedException) exception;
						Exception nextException = smtpSendFailedException.getNextException();
						if (nextException instanceof SMTPSenderFailedException) {
							return Message.error("admin.setting.mailTestSenderFailed");
						}
					} else if (exception instanceof MessagingException) {
						MessagingException messagingException = (MessagingException) exception;
						Exception nextException = messagingException.getNextException();
						if (nextException instanceof UnknownHostException) {
							return Message.error("admin.setting.mailTestUnknownHost");
						} else if (nextException instanceof ConnectException) {
							return Message.error("admin.setting.mailTestConnect");
						}
					}
				}
			}
			return Message.error("admin.setting.mailTestError");
		} catch (MailAuthenticationException e) {
			return Message.error("admin.setting.mailTestAuthentication");
		} catch (Exception e) {
			return Message.error("admin.setting.mailTestError");
		}
		return Message.success("admin.setting.mailTestSuccess");
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(ModelMap model,String editType) {
		model.put("editType", editType);
		if("mailEdit".equals(editType)){
			return "/admin/setting/mailEdit";
		}
		if("otherEdit".equals(editType)){
			model.addAttribute("stockAllocationTimes", StockAllocationTime.values());
			model.addAttribute("consultationAuthorities", ConsultationAuthority.values());
			model.addAttribute("reviewAuthorities", ReviewAuthority.values());
			return "/admin/setting/otherEdit";
		}
		if("basicEdit".equals(editType)){
			return "/admin/setting/basicEdit";
		}
		if("shareEdit".equals(editType)){
			return "/admin/setting/shareEdit";
		}
		if("safeAndRegisterEdit".equals(editType)){
			model.addAttribute("accountLockTypes", AccountLockType.values());
			model.addAttribute("captchaTypes", CaptchaType.values());
			return "/admin/setting/safeAndRegisterEdit";
		}
		if("viewEdit".equals(editType)){
			
			//价格精确方式
			model.addAttribute("roundTypes", RoundType.values());
			model.addAttribute("watermarkPositions", WatermarkPosition.values());
			return "/admin/setting/viewEdit";
		}
		if("loginEdit".equals(editType)){
			Properties propery=new Properties();
			java.io.InputStream in = SettingController.class
			.getClassLoader().getResourceAsStream("qqconnectconfig.properties");  
			String app_ID=null,app_KEY=null;
			try {
				propery.load(in);
				app_ID=propery.getProperty("app_ID");
				app_KEY=propery.getProperty("app_KEY");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
            model.put("app_KEY", app_KEY);
            model.put("app_ID", app_ID);
			return "/admin/setting/loginEdit";
		}
		return ERROR_VIEW;
	}

	/**
	 * 更新
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Setting setting,String editType, MultipartFile watermarkImageFile, RedirectAttributes redirectAttributes) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		
	
		Setting srcSetting = SettingUtils.get();
		
		/**
		if("mailEdit".equals(editType)){
		
			//发件人邮件、smtp服务器、端口号、账号、密码
			srcSetting.setSmtpFromMail(setting.getSmtpFromMail());
			srcSetting.setSmtpHost(setting.getSmtpHost());
			srcSetting.setSmtpPort(setting.getSmtpPort());
			srcSetting.setSmtpUsername(setting.getSmtpUsername());
			srcSetting.setSmtpPassword(setting.getSmtpPassword());
		}
		if("otherEdit".equals(editType)){
		
			//云服务器地址
			srcSetting.setCloudServerSite(setting.getCloudServerSite());
			
			//货币符号
			srcSetting.setCurrencySign(setting.getCurrencySign());
			
			//货币单位
			srcSetting.setCurrencyUnit(setting.getCurrencyUnit());
			
			//库存警告数
			srcSetting.setStockAlertCount(setting.getStockAlertCount());
			
			//库存分配时间点
			srcSetting.setStockAllocationTime(setting.getStockAllocationTime());
			
			//默认积分换算比例
			srcSetting.setDefaultPointScale(setting.getDefaultPointScale());
			
			//是否开启开发模式
			srcSetting.setIsDevelopmentEnabled(setting.getIsDevelopmentEnabled());
			
			//是否开启评论
			srcSetting.setIsReviewEnabled(setting.getIsReviewEnabled());
			
			//是否审核评论
			srcSetting.setIsReviewCheck(setting.getIsReviewCheck());
			
			//评论权限
			srcSetting.setReviewAuthority(setting.getReviewAuthority());
			
			//是否开启咨询
			srcSetting.setIsConsultationEnabled(setting.getIsConsultationEnabled());
			
			//是否审核咨询
			srcSetting.setIsConsultationCheck(setting.getIsConsultationCheck());
			
			//咨询权限
			srcSetting.setConsultationAuthority(setting.getConsultationAuthority());
			
			//是否开启发票功能
			srcSetting.setIsInvoiceEnabled(setting.getIsInvoiceEnabled());
			
			//是否开启含税价
			srcSetting.setIsTaxPriceEnabled(setting.getIsTaxPriceEnabled());
			
			//税率
			srcSetting.setTaxRate(setting.getTaxRate());
			
			//cookie路径
			srcSetting.setCookiePath(setting.getCookiePath());
			
			//cookie作用域
			srcSetting.setCookieDomain(setting.getCookieDomain());
			
			//快递100授权KEY
			srcSetting.setKuaidi100Key(setting.getKuaidi100Key());
		}
		if("basicEdit".equals(editType)){
		
			//网站名称
			srcSetting.setSiteName(setting.getSiteName());
			
			//网站网址
			srcSetting.setSiteUrl(setting.getSiteUrl());
			
			//logo
			srcSetting.setLogo(setting.getLogo());
			
			//热门搜索
			srcSetting.setHotSearch(setting.getHotSearch());
			
			//联系地址
			srcSetting.setAddress(setting.getAddress());
			
			//联系电话
			srcSetting.setPhone(setting.getPhone());
			
			//邮政编码
			srcSetting.setZipCode(setting.getZipCode());
			
			//E-mail
			srcSetting.setEmail(setting.getEmail());
			
			//备案编号
			srcSetting.setCerttext(setting.getCerttext());
			
			//是否网站开启
			srcSetting.setIsSiteEnabled(setting.getIsSiteEnabled());
			
			//网站关闭消息
			srcSetting.setSiteCloseMessage(setting.getSiteCloseMessage());
		}
		if("shareEdit".equals(editType)){
		
			//课程分享内容
			srcSetting.setCourseShare(setting.getCourseShare());
		}
		if("safeAndRegisterEdit".equals(editType)){
			
			//是否开放注册
			srcSetting.setIsRegisterEnabled(setting.getIsRegisterEnabled());
			
			//是否允许E-mail重复注册
			srcSetting.setIsDuplicateEmail(setting.getIsDuplicateEmail());
			
			//禁用用户名
			srcSetting.setDisabledUsername(setting.getDisabledUsername());
			
			//用户名最小长度
			srcSetting.setUsernameMinLength(setting.getUsernameMinLength());
			
			//用户名最大长度
			srcSetting.setUsernameMaxLength(setting.getUsernameMaxLength());
			
			//密码最小长度
			srcSetting.setPasswordMinLength(setting.getPasswordMinLength());
			
			//密码最大长度
			srcSetting.setPasswordMaxLength(setting.getPasswordMaxLength());
			
			//注册初始积分
			srcSetting.setRegisterPoint(setting.getRegisterPoint());
			
			//注册协议
			srcSetting.setRegisterAgreement(setting.getRegisterAgreement());
			
			//是否允许E-mail登录
			srcSetting.setIsEmailLogin(setting.getIsEmailLogin());
			
			//验证码类型----枚举数组
			srcSetting.setCaptchaTypes(setting.getCaptchaTypes());
			
			//账号锁定类型---枚举数组
			srcSetting.setAccountLockTypes(setting.getAccountLockTypes());
			
			//连续登录失败最大次数
			srcSetting.setAccountLockCount(setting.getAccountLockCount());
			
			//自动解锁时间
			srcSetting.setAccountLockTime(setting.getAccountLockTime());
			
			//安全密匙有效时间
			srcSetting.setSafeKeyExpiryTime(setting.getSafeKeyExpiryTime());
			
			//上传文件最大限制
			srcSetting.setUploadMaxSize(setting.getUploadMaxSize());
			
			//允许上传图片、Flash、Media、file的扩展名及路径
			srcSetting.setUploadImageExtension(setting.getUploadImageExtension());
			srcSetting.setUploadFlashExtension(setting.getUploadFlashExtension());
			srcSetting.setUploadMediaExtension(setting.getUploadMediaExtension());
			srcSetting.setUploadFileExtension(setting.getUploadFileExtension());
			srcSetting.setImageUploadPath(setting.getImageUploadPath());
			srcSetting.setFlashUploadPath(setting.getFlashUploadPath());
			srcSetting.setMediaUploadPath(setting.getMediaUploadPath());
			srcSetting.setFileUploadPath(setting.getFileUploadPath());
		}
		if("viewEdit".equals(editType)){
		
			//大，中， 小图片的长度宽度
			srcSetting.setLargeCourseImageHeight(setting.getLargeCourseImageHeight());
			srcSetting.setLargeCourseImageWidth(setting.getLargeCourseImageWidth());
			srcSetting.setDefaultLargeCourseImage(setting.getDefaultLargeCourseImage());
			
			srcSetting.setMediumCourseImageHeight(setting.getMediumCourseImageHeight());
			srcSetting.setMediumCourseImageWidth(setting.getMediumCourseImageWidth());
			srcSetting.setDefaultMediumCourseImage(setting.getDefaultMediumCourseImage());
			
			srcSetting.setThumbnailCourseImageHeight(setting.getThumbnailCourseImageHeight());
			srcSetting.setThumbnailCourseImageWidth(setting.getThumbnailCourseImageWidth());
			srcSetting.setDefaultThumbnailCourseImage(setting.getDefaultMediumCourseImage());
			
			if (watermarkImageFile != null && !watermarkImageFile.isEmpty()) {
				if (!fileService.isValid(FileType.image, watermarkImageFile)) {
					addFlashMessage(redirectAttributes, Message.error("admin.upload.invalid"));
					return "redirect:edit.jhtml?editType=viewEdit";
				}
				String watermarkImage = fileService.uploadLocal(FileType.image, watermarkImageFile);
				setting.setWatermarkImage(watermarkImage);
			} else {
				setting.setWatermarkImage(srcSetting.getWatermarkImage());
			}
			
			//水印透明度
			srcSetting.setWatermarkAlpha(setting.getWatermarkAlpha());
			srcSetting.setWatermarkPosition(setting.getWatermarkPosition());
			
			//价格精确位数
			srcSetting.setPriceScale(setting.getPriceScale());
			
			//价格精确方式: 
			srcSetting.setPriceRoundType(setting.getPriceRoundType());
			
			//是否前台显示市场价:
			srcSetting.setIsShowMarketPrice(setting.getIsShowMarketPrice());
			
			//默认市场价换算比例:
			srcSetting.setDefaultMarketPriceScale(setting.getDefaultMarketPriceScale());
		}
		if("loginEdit".equals(editType)){
			//srcSetting
		}
		*/
		Map<String, String> map = JsonUtils.toObject(JsonUtils.toJson(setting), Map.class);
		
		Set<String> keys = map.keySet();
		Iterator<String> it = keys.iterator();
		String key;
		Object value;
		Class clazz=Setting.class; 
		Class returnType;
		String tempMethod;
		Method[] methods=clazz.getDeclaredMethods();
		while(it.hasNext()){
			key=it.next();
			value=map.get(key);
			if(value!=null){
				for(Method method:methods){
					tempMethod=key.substring(0, 1).toUpperCase().concat(key.substring(1));
					if(method.getName().equals("get"+tempMethod)){
						
						//get方法的返回值类型----set方法的参数列表
						returnType=method.getReturnType();
						if(returnType.isAssignableFrom(Integer.class)){
							
							//默认的是强转
							clazz.getMethod("set"+tempMethod, Integer.class)
							.invoke(srcSetting, value);
						}else if(returnType.isAssignableFrom(String.class)){
							clazz.getMethod("set"+tempMethod, String.class)
							.invoke(srcSetting, value);
						}else if(returnType.isAssignableFrom(Double.class)){
							clazz.getMethod("set"+tempMethod, Double.class)
							.invoke(srcSetting, value);
						}else if(returnType.isAssignableFrom(Long.class)){
							clazz.getMethod("set"+tempMethod, Long.class)
							.invoke(srcSetting, Long.parseLong(value.toString()));
						}else if(returnType.isAssignableFrom(Boolean.class)){
							clazz.getMethod("set"+tempMethod, Boolean.class)
							.invoke(srcSetting, Boolean.valueOf(value.toString())?true:false);
						}else if(returnType.isAssignableFrom(CaptchaType[].class)){
							
							//验证码类型 
							CaptchaType[] captchaTypes =new CaptchaType[((ArrayList<String>)value).size()];
							for (int i = 0; i < captchaTypes.length; i++) {
								captchaTypes[i]=CaptchaType.valueOf(((ArrayList<String>)value).get(i));
							}
							clazz.getMethod("set"+tempMethod, CaptchaType[].class)
							.invoke(srcSetting,new Object[]{captchaTypes});
						}else if(returnType.isAssignableFrom(AccountLockType[].class)){
							
							//账号锁定类型
							AccountLockType[] accountLockTypes=new AccountLockType[((ArrayList<String>)value).size()];
							for(int i=0;i<accountLockTypes.length;i++){
								accountLockTypes[i]=AccountLockType.valueOf(((ArrayList<String>)value).get(i));
							}
							clazz.getMethod("set"+tempMethod,AccountLockType[].class)
							.invoke(srcSetting, new Object[]{accountLockTypes});
						}else if(returnType.isAssignableFrom(WatermarkPosition.class)){
							
							//水印位置
							clazz.getMethod("set"+tempMethod, WatermarkPosition.class)
							.invoke(srcSetting, WatermarkPosition.valueOf(value.toString()));
						}else if(returnType.isAssignableFrom(RoundType.class)){
							
							//价格精确方式
							clazz.getMethod("set"+tempMethod, RoundType.class)
							.invoke(srcSetting, RoundType.valueOf(value.toString()));
						}else if(returnType.isAssignableFrom(ReviewAuthority.class)){
							
							//评论权限
							clazz.getMethod("set"+tempMethod, ReviewAuthority.class)
							.invoke(srcSetting, ReviewAuthority.valueOf(value.toString()));
						}else if(returnType.isAssignableFrom(StockAllocationTime.class)){
							
							//库存分配时间点
							clazz.getMethod("set"+tempMethod, StockAllocationTime.class)
							.invoke(srcSetting, StockAllocationTime.valueOf(value.toString()));
						}
					}
				}
			}
		}
		
		if (watermarkImageFile != null && !watermarkImageFile.isEmpty()) {
			if (!fileService.isValid(FileType.image, watermarkImageFile)) {
				addFlashMessage(redirectAttributes, Message.error("admin.upload.invalid"));
				return "redirect:edit.jhtml?editType=viewEdit";
			}
			String watermarkImage = fileService.uploadLocal(FileType.image, watermarkImageFile);
			srcSetting.setWatermarkImage(watermarkImage);
		} 
		
		SettingUtils.set(srcSetting);
		cacheService.clear();
		staticService.buildIndex();
		staticService.buildOther();
		
		OutputStream outputStream = null;
		try {
			org.springframework.core.io.Resource resource = new ClassPathResource(CommonAttributes.SRAM_PROPERTIES_PATH);
			Properties properties = PropertiesLoaderUtils.loadProperties(resource);
			String templateUpdateDelay = properties.getProperty("template.update_delay");
			String messageCacheSeconds = properties.getProperty("message.cache_seconds");
			if (srcSetting.getIsDevelopmentEnabled()) {
				if (!templateUpdateDelay.equals("0") || !messageCacheSeconds.equals("0")) {
					outputStream = new FileOutputStream(resource.getFile());
					properties.setProperty("template.update_delay", "0");
					properties.setProperty("message.cache_seconds", "0");
					properties.store(outputStream, "Sram PROPERTIES");
				}
			} else {
				if (templateUpdateDelay.equals("0") || messageCacheSeconds.equals("0")) {
					outputStream = new FileOutputStream(resource.getFile());
					properties.setProperty("template.update_delay", "3600");
					properties.setProperty("message.cache_seconds", "3600");
					properties.store(outputStream, "Sram PROPERTIES");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(outputStream);
		}
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:edit.jhtml?editType="+editType;
	}
	
	

}