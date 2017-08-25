/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 系统设置
 * 
 * @author Sram Team
 * @version 1.0
 */
public class Setting implements Serializable {

	private static final long serialVersionUID = -1478999889661796840L;

	/**
	 * 水印位置
	 */
	public enum WatermarkPosition {

		/** 无 */
		no,

		
		/** 左上 */
		topLeft,

		/** 右上 */
		topRight,

		/** 居中 */
		center,

		/** 左下 */
		bottomLeft,

		/** 右下 */
		bottomRight
	}

	/**
	 * 小数位精确方式
	 */
	public enum RoundType {

		/** 四舍五入 */
		roundHalfUp,

		/** 向上取整 */
		roundUp,

		/** 向下取整 */
		roundDown
	}

	/**
	 * 验证码类型
	 */
	public enum CaptchaType {

		/** 会员登录 */
		memberLogin,

		/** 会员注册 */
		memberRegister,

		/** 后台登录 */
		adminLogin,

		/** 课程评论 */
		review,

		/** 课程咨询 */
		consultation,

		/** 找回密码 */
		findPassword,

		/** 重置密码 */
		resetPassword,

		/** 其它 */
		other
	}

	/** 缓存名称 */
	public static final String CACHE_NAME = "setting";

	/** 缓存Key */
	public static final Integer CACHE_KEY = 0;

	/** 分隔符 */
	private static final String SEPARATOR = ",";

	/** 网站名称 */
	private String siteName;

	/** 网站网址 */
	private String siteUrl;

	/** logo */
	private String logo;

	/** 热门搜索 */
	private String hotSearch;

	/** 联系地址 */
	private String address;

	/** 联系电话 */
	private String phone;

	/** 邮政编码 */
	private String zipCode;

	/** E-mail */
	private String email;

	/** 备案编号 */
	private String certtext;

	/** 是否网站开启 */
	private Boolean isSiteEnabled;

	/** 网站关闭消息 */
	private String siteCloseMessage;

	/** 课程图片(大)宽度 */
	private Integer largeCourseImageWidth;

	/** 课程图片(大)高度 */
	private Integer largeCourseImageHeight;

	/** 课程图片(中)宽度 */
	private Integer mediumCourseImageWidth;

	/** 课程图片(中)高度 */
	private Integer mediumCourseImageHeight;

	/** 课程缩略图宽度 */
	private Integer thumbnailCourseImageWidth;

	/** 课程缩略图高度 */
	private Integer thumbnailCourseImageHeight;

	/** 默认课程图片(大) */
	private String defaultLargeCourseImage;

	/** 默认课程图片(小) */
	private String defaultMediumCourseImage;

	/** 默认缩略图 */
	private String defaultThumbnailCourseImage;

	/** 水印透明度 */
	private Integer watermarkAlpha;

	/** 水印图片 */
	private String watermarkImage;

	/** 水印位置 */
	private WatermarkPosition watermarkPosition;

	/** 价格精确位数 */
	private Integer priceScale;

	/** 价格精确方式 */
	private RoundType priceRoundType;

	/** 是否前台显示市场价 */
	private Boolean isShowMarketPrice;

	/** 默认市场价换算比例 */
	private Double defaultMarketPriceScale;

	/** 禁用用户名 */
	private String disabledUsername;

	/** 用户名最小长度 */
	private Integer usernameMinLength;

	/** 用户名最大长度 */
	private Integer usernameMaxLength;

	/** 密码最小长度 */
	private Integer passwordMinLength;

	/** 密码最大长度 */
	private Integer passwordMaxLength;

	/** 注册初始积分 */
	private Long registerPoint;

	/** 注册协议 */
	private String registerAgreement;

	/** 是否允许E-mail登录 */
	private Boolean isEmailLogin;

	/** 验证码类型 */
	private CaptchaType[] captchaTypes;

	/** 连续登录失败最大次数 */
	private Integer accountLockCount;

	/** 自动解锁时间 */
	private Integer accountLockTime;

	/** 安全密匙有效时间 */
	private Integer safeKeyExpiryTime;

	/** 上传文件最大限制 */
	private Integer uploadMaxSize;

	/** 允许上传图片扩展名 */
	private String uploadImageExtension;

	/** 允许上传Flash扩展名 */
	private String uploadFlashExtension;

	/** 允许上传媒体扩展名 */
	private String uploadVideoExtension;

	/** 允许上传音频扩展名 */
	private String uploadAudioExtension;

	/** 允许上传文件扩展名 */
	private String uploadFileExtension;

	/** 允许上传ppt文件扩展名 */
	private String uploadPPTExtension;
	
	/**文件上传路径*/
	private String storagePath;

	/** 图片上传路径 */
	private String imageUploadPath;

	/** Flash上传路径 */
	private String flashUploadPath;

	/** 媒体上传路径 */
	private String mediaUploadPath;

	/** 文件上传路径 */
	private String fileUploadPath;

	/** Cookie路径 */
	private String cookiePath;

	/** Cookie作用域 */
	private String cookieDomain;

	/**
	 * 获取网站名称
	 * 
	 * @return 网站名称
	 */
	@NotEmpty
	@Length(max = 200)
	public String getSiteName() {
		return siteName;
	}

	/**
	 * 设置网站名称
	 * 
	 * @param siteName
	 *            网站名称
	 */
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	/**
	 * 获取网站网址
	 * 
	 * @return 网站网址
	 */
	@NotEmpty
	@Length(max = 200)
	public String getSiteUrl() {
		return siteUrl;
	}

	/**
	 * 设置网站网址
	 * 
	 * @param siteUrl
	 *            网站网址
	 */
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = StringUtils.removeEnd(siteUrl, "/");
	}

	/**
	 * 获取logo
	 * 
	 * @return logo
	 */
	@NotEmpty
	@Length(max = 200)
	public String getLogo() {
		return logo;
	}

	/**
	 * 设置logo
	 * 
	 * @param logo
	 *            logo
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}

	/**
	 * 获取热门搜索
	 * 
	 * @return 热门搜索
	 */
	@Length(max = 200)
	public String getHotSearch() {
		return hotSearch;
	}

	/**
	 * 设置热门搜索
	 * 
	 * @param hotSearch
	 *            热门搜索
	 */
	public void setHotSearch(String hotSearch) {
		if (hotSearch != null) {
			hotSearch = hotSearch.replaceAll("[,\\s]*,[,\\s]*", ",")
					.replaceAll("^,|,$", "");
		}
		this.hotSearch = hotSearch;
	}

	/**
	 * 获取联系地址
	 * 
	 * @return 联系地址
	 */
	@Length(max = 200)
	public String getAddress() {
		return address;
	}

	/**
	 * 设置联系地址
	 * 
	 * @param address
	 *            联系地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取联系电话
	 * 
	 * @return 联系电话
	 */
	@Length(max = 200)
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置联系电话
	 * 
	 * @param phone
	 *            联系电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取邮政编码
	 * 
	 * @return 邮政编码
	 */
	@Length(max = 200)
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * 设置邮政编码
	 * 
	 * @param zipCode
	 *            邮政编码
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * 获取E-mail
	 * 
	 * @return E-mail
	 */
	@Email
	@Length(max = 200)
	public String getEmail() {
		return email;
	}

	/**
	 * 设置E-mail
	 * 
	 * @param email
	 *            E-mail
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取备案编号
	 * 
	 * @return 备案编号
	 */
	@Length(max = 200)
	public String getCerttext() {
		return certtext;
	}

	/**
	 * 设置备案编号
	 * 
	 * @param certtext
	 *            备案编号
	 */
	public void setCerttext(String certtext) {
		this.certtext = certtext;
	}

	/**
	 * 获取是否网站开启
	 * 
	 * @return 是否网站开启
	 */
	@NotNull
	public Boolean getIsSiteEnabled() {
		return isSiteEnabled;
	}

	/**
	 * 设置是否网站开启
	 * 
	 * @param isSiteEnabled
	 *            是否网站开启
	 */
	public void setIsSiteEnabled(Boolean isSiteEnabled) {
		this.isSiteEnabled = isSiteEnabled;
	}

	/**
	 * 获取网站关闭消息
	 * 
	 * @return 网站关闭消息
	 */
	@NotEmpty
	public String getSiteCloseMessage() {
		return siteCloseMessage;
	}

	/**
	 * 设置网站关闭消息
	 * 
	 * @param siteCloseMessage
	 *            网站关闭消息
	 */
	public void setSiteCloseMessage(String siteCloseMessage) {
		this.siteCloseMessage = siteCloseMessage;
	}

	/**
	 * 获取课程图片(大)宽度
	 * 
	 * @return 课程图片(大)宽度
	 */
	@NotNull
	@Min(1)
	public Integer getLargeCourseImageWidth() {
		return largeCourseImageWidth;
	}

	/**
	 * 设置课程图片(大)宽度
	 * 
	 * @param largeProductImageWidth
	 *            课程图片(大)宽度
	 */
	public void setLargeCourseImageWidth(Integer largeCourseImageWidth) {
		this.largeCourseImageWidth = largeCourseImageWidth;
	}

	/**
	 * 获取课程图片(大)高度
	 * 
	 * @return 课程图片(大)高度
	 */
	@NotNull
	@Min(1)
	public Integer getLargeCourseImageHeight() {
		return largeCourseImageHeight;
	}

	/**
	 * 设置课程图片(大)高度
	 * 
	 * @param largeProductImageHeight
	 *            课程图片(大)高度
	 */
	public void setLargeCourseImageHeight(Integer largeCourseImageHeight) {
		this.largeCourseImageHeight = largeCourseImageHeight;
	}

	/**
	 * 获取课程图片(中)宽度
	 * 
	 * @return 课程图片(中)宽度
	 */
	@NotNull
	@Min(1)
	public Integer getMediumCourseImageWidth() {
		return mediumCourseImageWidth;
	}

	/**
	 * 设置课程图片(中)宽度
	 * 
	 * @param mediumProductImageWidth
	 *            课程图片(中)宽度
	 */
	public void setMediumCourseImageWidth(Integer mediumCourseImageWidth) {
		this.mediumCourseImageWidth = mediumCourseImageWidth;
	}

	/**
	 * 获取课程图片(中)高度
	 * 
	 * @return 课程图片(中)高度
	 */
	@NotNull
	@Min(1)
	public Integer getMediumCourseImageHeight() {
		return mediumCourseImageHeight;
	}

	/**
	 * 设置课程图片(中)高度
	 * 
	 * @param mediumProductImageHeight
	 *            课程图片(中)高度
	 */
	public void setMediumCourseImageHeight(Integer mediumCourseImageHeight) {
		this.mediumCourseImageHeight = mediumCourseImageHeight;
	}

	/**
	 * 获取课程缩略图宽度
	 * 
	 * @return 课程缩略图宽度
	 */
	@NotNull
	@Min(1)
	public Integer getThumbnailCourseImageWidth() {
		return thumbnailCourseImageWidth;
	}

	/**
	 * 设置课程缩略图宽度
	 * 
	 * @param thumbnailProductImageWidth
	 *            课程缩略图宽度
	 */
	public void setThumbnailCourseImageWidth(Integer thumbnailCourseImageWidth) {
		this.thumbnailCourseImageWidth = thumbnailCourseImageWidth;
	}

	/**
	 * 获取课程缩略图高度
	 * 
	 * @return 课程缩略图高度
	 */
	@NotNull
	@Min(1)
	public Integer getThumbnailCourseImageHeight() {
		return thumbnailCourseImageHeight;
	}

	/**
	 * 设置课程缩略图高度
	 * 
	 * @param thumbnailProductImageHeight
	 *            课程缩略图高度
	 */
	public void setThumbnailCourseImageHeight(Integer thumbnailCourseImageHeight) {
		this.thumbnailCourseImageHeight = thumbnailCourseImageHeight;
	}

	/**
	 * 获取默认课程图片(大)
	 * 
	 * @return 默认课程图片(大)
	 */
	@NotEmpty
	@Length(max = 200)
	public String getDefaultLargeCourseImage() {
		return defaultLargeCourseImage;
	}

	/**
	 * 设置默认课程图片(大)
	 * 
	 * @param defaultLargeProductImage
	 *            默认课程图片(大)
	 */
	public void setDefaultLargeCourseImage(String defaultLargeCourseImage) {
		this.defaultLargeCourseImage = defaultLargeCourseImage;
	}

	/**
	 * 获取默认课程图片(小)
	 * 
	 * @return 默认课程图片(小)
	 */
	@NotEmpty
	@Length(max = 200)
	public String getDefaultMediumCourseImage() {
		return defaultMediumCourseImage;
	}

	/**
	 * 设置默认课程图片(小)
	 * 
	 * @param defaultMediumProductImage
	 *            默认课程图片(小)
	 */
	public void setDefaultMediumCourseImage(String defaultMediumCourseImage) {
		this.defaultMediumCourseImage = defaultMediumCourseImage;
	}

	/**
	 * 获取默认缩略图
	 * 
	 * @return 默认缩略图
	 */
	@NotEmpty
	@Length(max = 200)
	public String getDefaultThumbnailCourseImage() {
		return defaultThumbnailCourseImage;
	}

	/**
	 * 设置默认缩略图
	 * 
	 * @param defaultThumbnailProductImage
	 *            默认缩略图
	 */
	public void setDefaultThumbnailCourseImage(
			String defaultThumbnailCourseImage) {
		this.defaultThumbnailCourseImage = defaultThumbnailCourseImage;
	}

	/**
	 * 获取水印透明度
	 * 
	 * @return 水印透明度
	 */
	@NotNull
	@Min(0)
	@Max(100)
	public Integer getWatermarkAlpha() {
		return watermarkAlpha;
	}

	/**
	 * 设置水印透明度
	 * 
	 * @param watermarkAlpha
	 *            水印透明度
	 */
	public void setWatermarkAlpha(Integer watermarkAlpha) {
		this.watermarkAlpha = watermarkAlpha;
	}

	/**
	 * 获取水印图片
	 * 
	 * @return 水印图片
	 */
	public String getWatermarkImage() {
		return watermarkImage;
	}

	/**
	 * 设置水印图片
	 * 
	 * @param watermarkImage
	 *            水印图片
	 */
	public void setWatermarkImage(String watermarkImage) {
		this.watermarkImage = watermarkImage;
	}

	/**
	 * 获取水印位置
	 * 
	 * @return 水印位置
	 */
	@NotNull
	public WatermarkPosition getWatermarkPosition() {
		return watermarkPosition;
	}

	/**
	 * 设置水印位置
	 * 
	 * @param watermarkPosition
	 *            水印位置
	 */
	public void setWatermarkPosition(WatermarkPosition watermarkPosition) {
		this.watermarkPosition = watermarkPosition;
	}

	/**
	 * 获取价格精确位数
	 * 
	 * @return 价格精确位数
	 */
	@NotNull
	@Min(0)
	@Max(3)
	public Integer getPriceScale() {
		return priceScale;
	}

	/**
	 * 设置价格精确位数
	 * 
	 * @param priceScale
	 *            价格精确位数
	 */
	public void setPriceScale(Integer priceScale) {
		this.priceScale = priceScale;
	}

	/**
	 * 获取价格精确方式
	 * 
	 * @return 价格精确方式
	 */
	@NotNull
	public RoundType getPriceRoundType() {
		return priceRoundType;
	}

	/**
	 * 设置价格精确方式
	 * 
	 * @param priceRoundType
	 *            价格精确方式
	 */
	public void setPriceRoundType(RoundType priceRoundType) {
		this.priceRoundType = priceRoundType;
	}

	/**
	 * 获取是否前台显示市场价
	 * 
	 * @return 是否前台显示市场价
	 */
	@NotNull
	public Boolean getIsShowMarketPrice() {
		return isShowMarketPrice;
	}

	/**
	 * 设置是否前台显示市场价
	 * 
	 * @param isShowMarketPrice
	 *            是否前台显示市场价
	 */
	public void setIsShowMarketPrice(Boolean isShowMarketPrice) {
		this.isShowMarketPrice = isShowMarketPrice;
	}

	/**
	 * 获取默认市场价换算比例
	 * 
	 * @return 默认市场价换算比例
	 */
	@NotNull
	@Min(0)
	@Digits(integer = 3, fraction = 3)
	public Double getDefaultMarketPriceScale() {
		return defaultMarketPriceScale;
	}

	/**
	 * 设置默认市场价换算比例
	 * 
	 * @param defaultMarketPriceScale
	 *            默认市场价换算比例
	 */
	public void setDefaultMarketPriceScale(Double defaultMarketPriceScale) {
		this.defaultMarketPriceScale = defaultMarketPriceScale;
	}

	/**
	 * 获取禁用用户名
	 * 
	 * @return 禁用用户名
	 */
	@Length(max = 200)
	public String getDisabledUsername() {
		return disabledUsername;
	}

	/**
	 * 设置禁用用户名
	 * 
	 * @param disabledUsername
	 *            禁用用户名
	 */
	public void setDisabledUsername(String disabledUsername) {
		if (disabledUsername != null) {
			disabledUsername = disabledUsername.replaceAll("[,\\s]*,[,\\s]*",
					",").replaceAll("^,|,$", "");
		}
		this.disabledUsername = disabledUsername;
	}

	/**
	 * 获取用户名最小长度
	 * 
	 * @return 用户名最小长度
	 */
	@NotNull
	@Min(1)
	@Max(117)
	public Integer getUsernameMinLength() {
		return usernameMinLength;
	}

	/**
	 * 设置用户名最小长度
	 * 
	 * @param usernameMinLength
	 *            用户名最小长度
	 */
	public void setUsernameMinLength(Integer usernameMinLength) {
		this.usernameMinLength = usernameMinLength;
	}

	/**
	 * 获取用户名最大长度
	 * 
	 * @return 用户名最大长度
	 */
	@NotNull
	@Min(1)
	@Max(117)
	public Integer getUsernameMaxLength() {
		return usernameMaxLength;
	}

	/**
	 * 设置用户名最大长度
	 * 
	 * @param usernameMaxLength
	 *            用户名最大长度
	 */
	public void setUsernameMaxLength(Integer usernameMaxLength) {
		this.usernameMaxLength = usernameMaxLength;
	}

	/**
	 * 获取密码最小长度
	 * 
	 * @return 密码最小长度
	 */
	@NotNull
	@Min(1)
	@Max(117)
	public Integer getPasswordMinLength() {
		return passwordMinLength;
	}

	/**
	 * 设置密码最小长度
	 * 
	 * @param passwordMinLength
	 *            密码最小长度
	 */
	public void setPasswordMinLength(Integer passwordMinLength) {
		this.passwordMinLength = passwordMinLength;
	}

	/**
	 * 获取密码最大长度
	 * 
	 * @return 密码最大长度
	 */
	@NotNull
	@Min(1)
	@Max(117)
	public Integer getPasswordMaxLength() {
		return passwordMaxLength;
	}

	/**
	 * 设置密码最大长度
	 * 
	 * @param passwordMaxLength
	 *            密码最大长度
	 */
	public void setPasswordMaxLength(Integer passwordMaxLength) {
		this.passwordMaxLength = passwordMaxLength;
	}

	/**
	 * 获取注册初始积分
	 * 
	 * @return 注册初始积分
	 */
	@NotNull
	@Min(0)
	public Long getRegisterPoint() {
		return registerPoint;
	}

	/**
	 * 设置注册初始积分
	 * 
	 * @param registerPoint
	 *            注册初始积分
	 */
	public void setRegisterPoint(Long registerPoint) {
		this.registerPoint = registerPoint;
	}

	/**
	 * 获取注册协议
	 * 
	 * @return 注册协议
	 */
	@NotEmpty
	public String getRegisterAgreement() {
		return registerAgreement;
	}

	/**
	 * 设置注册协议
	 * 
	 * @param registerAgreement
	 *            注册协议
	 */
	public void setRegisterAgreement(String registerAgreement) {
		this.registerAgreement = registerAgreement;
	}

	/**
	 * 获取是否允许E-mail登录
	 * 
	 * @return 是否允许E-mail登录
	 */
	@NotNull
	public Boolean getIsEmailLogin() {
		return isEmailLogin;
	}

	/**
	 * 设置是否允许E-mail登录
	 * 
	 * @param isEmailLogin
	 *            是否允许E-mail登录
	 */
	public void setIsEmailLogin(Boolean isEmailLogin) {
		this.isEmailLogin = isEmailLogin;
	}

	/**
	 * 获取验证码类型
	 * 
	 * @return 验证码类型
	 */
	public CaptchaType[] getCaptchaTypes() {
		return captchaTypes;
	}

	/**
	 * 设置验证码类型
	 * 
	 * @param captchaTypes
	 *            验证码类型
	 */
	public void setCaptchaTypes(CaptchaType[] captchaTypes) {
		this.captchaTypes = captchaTypes;
	}

	/**
	 * 获取连续登录失败最大次数
	 * 
	 * @return 连续登录失败最大次数
	 */
	@NotNull
	@Min(1)
	public Integer getAccountLockCount() {
		return accountLockCount;
	}

	/**
	 * 设置连续登录失败最大次数
	 * 
	 * @param accountLockCount
	 *            连续登录失败最大次数
	 */
	public void setAccountLockCount(Integer accountLockCount) {
		this.accountLockCount = accountLockCount;
	}

	/**
	 * 获取自动解锁时间
	 * 
	 * @return 自动解锁时间
	 */
	@NotNull
	@Min(0)
	public Integer getAccountLockTime() {
		return accountLockTime;
	}

	/**
	 * 设置自动解锁时间
	 * 
	 * @param accountLockTime
	 *            自动解锁时间
	 */
	public void setAccountLockTime(Integer accountLockTime) {
		this.accountLockTime = accountLockTime;
	}

	/**
	 * 获取安全密匙有效时间
	 * 
	 * @return 安全密匙有效时间
	 */
	@NotNull
	@Min(0)
	public Integer getSafeKeyExpiryTime() {
		return safeKeyExpiryTime;
	}

	/**
	 * 设置安全密匙有效时间
	 * 
	 * @param safeKeyExpiryTime
	 *            安全密匙有效时间
	 */
	public void setSafeKeyExpiryTime(Integer safeKeyExpiryTime) {
		this.safeKeyExpiryTime = safeKeyExpiryTime;
	}

	/**
	 * 获取上传文件最大限制
	 * 
	 * @return 上传文件最大限制
	 */
	@NotNull
	@Min(0)
	public Integer getUploadMaxSize() {
		return uploadMaxSize;
	}

	/**
	 * 设置上传文件最大限制
	 * 
	 * @param uploadMaxSize
	 *            上传文件最大限制
	 */
	public void setUploadMaxSize(Integer uploadMaxSize) {
		this.uploadMaxSize = uploadMaxSize;
	}

	/**
	 * 获取允许上传图片扩展名
	 * 
	 * @return 允许上传图片扩展名
	 */
	@Length(max = 200)
	public String getUploadImageExtension() {
		return uploadImageExtension;
	}

	/**
	 * 设置允许上传图片扩展名
	 * 
	 * @param uploadImageExtension
	 *            允许上传图片扩展名
	 */
	public void setUploadImageExtension(String uploadImageExtension) {
		if (uploadImageExtension != null) {
			uploadImageExtension = uploadImageExtension
					.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "")
					.toLowerCase();
		}
		this.uploadImageExtension = uploadImageExtension;
	}

	/**
	 * 获取允许上传Flash扩展名
	 * 
	 * @return 允许上传Flash扩展名
	 */
	@Length(max = 200)
	public String getUploadFlashExtension() {
		return uploadFlashExtension;
	}

	/**
	 * 设置允许上传Flash扩展名
	 * 
	 * @param uploadFlashExtension
	 *            允许上传Flash扩展名
	 */
	public void setUploadFlashExtension(String uploadFlashExtension) {
		if (uploadFlashExtension != null) {
			uploadFlashExtension = uploadFlashExtension
					.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "")
					.toLowerCase();
		}
		this.uploadFlashExtension = uploadFlashExtension;
	}

	/**
	 * 获取允许上传媒体扩展名
	 * 
	 * @return 允许上传媒体扩展名
	 */
	@Length(max = 200)
	public String getUploadVideoExtension() {
		return uploadVideoExtension;
	}

	/**
	 * 设置允许上传视频扩展名
	 * 
	 * @param uploadMediaExtension
	 *            允许上传媒体扩展名
	 */
	public void setUploadVideoExtension(String uploadVideoExtension) {
		if (uploadVideoExtension != null) {
			uploadVideoExtension = uploadVideoExtension
					.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "")
					.toLowerCase();
		}
		this.uploadVideoExtension = uploadVideoExtension;
	}

	@Length(max = 200)
	public String getUploadAudioExtension() {
		return uploadAudioExtension;
	}

	public void setUploadAudioExtension(String uploadAudioExtension) {
		if (uploadAudioExtension != null) {
			uploadAudioExtension = uploadAudioExtension
					.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "")
					.toLowerCase();
		}
		this.uploadAudioExtension = uploadAudioExtension;
	}

	/**
	 * 获取允许上传文件扩展名
	 * 
	 * @return 允许上传文件扩展名
	 */
	@Length(max = 200)
	public String getUploadFileExtension() {
		return uploadFileExtension;
	}

	/**
	 * 设置允许上传文件扩展名
	 * 
	 * @param uploadFileExtension
	 *            允许上传文件扩展名
	 */
	public void setUploadFileExtension(String uploadFileExtension) {
		if (uploadFileExtension != null) {
			uploadFileExtension = uploadFileExtension
					.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "")
					.toLowerCase();
		}
		this.uploadFileExtension = uploadFileExtension;
	}

	/**
	 * 获取允许上传PPT文件扩展名
	 * 
	 * @return 允许上传PPT文件扩展名
	 */

	public String getUploadPPTExtension() {
		return uploadPPTExtension;
	}

	/**
	 * 设置允许上传PPT文件扩展名
	 * 
	 * @param uploadFileExtension
	 *            允许上传PPT文件扩展名
	 */
	public void setUploadPPTExtension(String uploadPPTExtension) {
		if (uploadPPTExtension != null) {
			uploadPPTExtension = uploadPPTExtension
					.replaceAll("[,\\s]*,[,\\s]*", ",").replaceAll("^,|,$", "")
					.toLowerCase();
		}
		this.uploadPPTExtension = uploadPPTExtension;
	}

	/**
	 * 获取文件上传路径
	 * @return
	 */
	@NotEmpty
	@Length(max = 200)
	public String getStoragePath() {
		return storagePath;
	}

	public void setStoragePath(String storagePath) {
		/*if (storagePath != null) {
			if (!storagePath.startsWith("/")) {
				storagePath = "/" + storagePath;
			}
			if (!storagePath.endsWith("/")) {
				storagePath += "/";
			}
		}*/
		this.storagePath = storagePath;
	}

	/**
	 * 获取图片上传路径
	 * 
	 * @return 图片上传路径
	 */
	@NotEmpty
	@Length(max = 200)
	public String getImageUploadPath() {
		return imageUploadPath;
	}

	/**
	 * 设置图片上传路径
	 * 
	 * @param imageUploadPath
	 *            图片上传路径
	 */
	public void setImageUploadPath(String imageUploadPath) {
		if (imageUploadPath != null) {
			if (!imageUploadPath.startsWith("/")) {
				imageUploadPath = "/" + imageUploadPath;
			}
			if (!imageUploadPath.endsWith("/")) {
				imageUploadPath += "/";
			}
		}
		this.imageUploadPath = imageUploadPath;
	}

	/**
	 * 获取Flash上传路径
	 * 
	 * @return Flash上传路径
	 */
	@NotEmpty
	@Length(max = 200)
	public String getFlashUploadPath() {
		return flashUploadPath;
	}

	/**
	 * 设置Flash上传路径
	 * 
	 * @param flashUploadPath
	 *            Flash上传路径
	 */
	public void setFlashUploadPath(String flashUploadPath) {
		if (flashUploadPath != null) {
			if (!flashUploadPath.startsWith("/")) {
				flashUploadPath = "/" + flashUploadPath;
			}
			if (!flashUploadPath.endsWith("/")) {
				flashUploadPath += "/";
			}
		}
		this.flashUploadPath = flashUploadPath;
	}

	/**
	 * 获取媒体上传路径
	 * 
	 * @return 媒体上传路径
	 */
	@NotEmpty
	@Length(max = 200)
	public String getMediaUploadPath() {
		return mediaUploadPath;
	}

	/**
	 * 设置媒体上传路径
	 * 
	 * @param mediaUploadPath
	 *            媒体上传路径
	 */
	public void setMediaUploadPath(String mediaUploadPath) {
		if (mediaUploadPath != null) {
			if (!mediaUploadPath.startsWith("/")) {
				mediaUploadPath = "/" + mediaUploadPath;
			}
			if (!mediaUploadPath.endsWith("/")) {
				mediaUploadPath += "/";
			}
		}
		this.mediaUploadPath = mediaUploadPath;
	}

	/**
	 * 获取文件上传路径
	 * 
	 * @return 文件上传路径
	 */
	@NotEmpty
	@Length(max = 200)
	public String getFileUploadPath() {
		return fileUploadPath;
	}

	/**
	 * 设置文件上传路径
	 * 
	 * @param fileUploadPath
	 *            文件上传路径
	 */
	public void setFileUploadPath(String fileUploadPath) {
		if (fileUploadPath != null) {
			if (!fileUploadPath.startsWith("/")) {
				fileUploadPath = "/" + fileUploadPath;
			}
			if (!fileUploadPath.endsWith("/")) {
				fileUploadPath += "/";
			}
		}
		this.fileUploadPath = fileUploadPath;
	}

	/**
	 * 获取热门搜索关键词
	 * 
	 * @return 热门搜索关键词
	 */
	public String[] getHotSearches() {
		return StringUtils.split(hotSearch, SEPARATOR);
	}

	/**
	 * 获取禁用用户名
	 * 
	 * @return 禁用用户名
	 */
	public String[] getDisabledUsernames() {
		return StringUtils.split(disabledUsername, SEPARATOR);
	}

	/**
	 * 获取允许上传图片扩展名
	 * 
	 * @return 允许上传图片扩展名
	 */
	public String[] getUploadImageExtensions() {
		return StringUtils.split(uploadImageExtension, SEPARATOR);
	}

	/**
	 * 获取允许上传Flash扩展名
	 * 
	 * @return 允许上传Flash扩展名
	 */
	public String[] getUploadFlashExtensions() {
		return StringUtils.split(uploadFlashExtension, SEPARATOR);
	}

	/**
	 * 获取允许上传视频扩展名
	 * 
	 * @return 允许上传视频扩展名
	 */
	public String[] getUploadVideoExtensions() {
		return StringUtils.split(uploadVideoExtension, SEPARATOR);
	}

	/**
	 * 获取允许上传音频扩展名
	 * 
	 * @return 允许上传音频扩展名
	 */
	public String[] getUploadAudioExtensions() {
		return StringUtils.split(uploadAudioExtension, SEPARATOR);
	}

	/**
	 * 获取允许上传文件扩展名
	 * 
	 * @return 允许上传文件扩展名
	 */
	public String[] getUploadFileExtensions() {
		return StringUtils.split(uploadFileExtension, SEPARATOR);
	}

	/**
	 * 获取允许上传PPT文件扩展名
	 * 
	 * @return 允许上传PPT文件扩展名
	 */
	public String[] getUploadPPTExtensions() {
		return StringUtils.split(uploadPPTExtension, SEPARATOR);
	}

	/**
	 * 设置精度
	 * 
	 * @param amount
	 *            数值
	 * @return 数值
	 */
	public BigDecimal setScale(BigDecimal amount) {
		if (amount == null) {
			return null;
		}
		int roundingMode;
		if (getPriceRoundType() == RoundType.roundUp) {
			roundingMode = BigDecimal.ROUND_UP;
		} else if (getPriceRoundType() == RoundType.roundDown) {
			roundingMode = BigDecimal.ROUND_DOWN;
		} else {
			roundingMode = BigDecimal.ROUND_HALF_UP;
		}
		return amount.setScale(getPriceScale(), roundingMode);
	}

	/**
	 * 获取Cookie路径
	 * 
	 * @return Cookie路径
	 */
	@NotEmpty
	@Length(max = 200)
	public String getCookiePath() {
		return cookiePath;
	}

	/**
	 * 设置Cookie路径
	 * 
	 * @param cookiePath
	 *            Cookie路径
	 */
	public void setCookiePath(String cookiePath) {
		if (cookiePath != null && !cookiePath.endsWith("/")) {
			cookiePath += "/";
		}
		this.cookiePath = cookiePath;
	}

	/**
	 * 获取Cookie作用域
	 * 
	 * @return Cookie作用域
	 */
	@Length(max = 200)
	public String getCookieDomain() {
		return cookieDomain;
	}

	/**
	 * 设置Cookie作用域
	 * 
	 * @param cookieDomain
	 *            Cookie作用域
	 */
	public void setCookieDomain(String cookieDomain) {
		this.cookieDomain = cookieDomain;
	}
}