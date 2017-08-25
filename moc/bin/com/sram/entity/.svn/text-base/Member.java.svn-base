/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.entity;


import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sram.interceptor.MemberInterceptor;



/**
 * Entity - 会员
 * 
 * @author Sram Team
 * @version 1.0
 */
@Entity
@Table(name = "moc_member")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_member_sequence")
public class Member extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	/** "身份信息"参数名称 */
	public static final String PRINCIPAL_ATTRIBUTE_NAME = MemberInterceptor.class.getName() + ".PRINCIPAL";
	
	/** "用户名"Cookie名称 */
	public static final String USERNAME_COOKIE_NAME = "username";
	
	/** 会员注册项值属性名称前缀 */
	public static final String ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX = "attributeValue";
	

	/** 会员注册项值属性个数 */
	public static final int ATTRIBUTE_VALUE_PROPERTY_COUNT = 10;
	


	/** 实名认证状态*/
	public enum ApprovalStatus{
		
		/** 通过*/
		approve,
		
		/** 不通过*/
		unapprove
	}
    
	/** 性别*/
	public enum Gender {

		/** 男 */
		male,

		/** 女 */
		female
	}
	
	/**收藏课程*/
	private Set<CourseFavorite> courseFavorites=new HashSet<CourseFavorite>();
	
	/**话题的回复内容*/
	private Set<CourseThreadPost> courseThreadPosts= new HashSet<CourseThreadPost>();
	
	/**创建的话题*/
	private Set<CourseThread> courseThreads = new HashSet<CourseThread>();
	
	private Set<CourseThread> threads = new HashSet<CourseThread>();
	
	/**对课程的评论*/
	private Set<CourseReview> courseReviews = new HashSet<CourseReview>();
	
	/**接收的消息*/
	private Set<Message> inMessages = new HashSet<Message>();
	
	/**发送的消息*/
	private Set<Message> outMessages = new HashSet<Message>();
	
	/** 性别 */
	private Gender gender;
	
	/** 出生日期 */
	private Date birth;
	
	/** Email*/
	private String  email;
	
	/** 密码*/
	private String password ;
	
	/** 加盐哈希 */
	private String salt;
	
	/**用户url*/
	private String url;
	
	/** 用户名*/
	private String username;
	
	/** 头像*/
	private String headImage;
	
	/**登录方式---qq*/
	private String type;
	
	/**积分*/
	private Integer point=0;
	
	/**金币*/
	private Integer coin=0;
	
	/**邮箱是否验证*/
	private String emailVerified;
	
	/**是否初始化*/
	private Integer setup=1;
	
	/**是否推荐*/
	private Integer promoted=0;
	
	/**推荐时间*/
	private Date promotedTime;
	
	/**姓名*/
	private String name;
	
	/**是否锁定*/
	private boolean locked;
    
	/** 是否启用 */
	private Boolean isEnabled;
    
	/**手机*/
	private String mobile;
	
	/**地址*/
	private String address;
	
	/**实名认证时间*/
	private Date approvalTime;
	
	/**实名认证状态*/
	private ApprovalStatus approvalStatus;
	
	/**未读私信数*/
	private Integer newMessageNum=0;
	
	/**未读消息数*/
	private Integer newNotificationNum=0;
	
	/**注册IP*/
	private String registerIp;
	
	/**连续登陆失败次数*/
	private Integer loginFailureCount=0;
	
	/**锁定日期*/
	private Date lockedDate;
	
	/**最后登录ip*/
	private String loginIp;
	
	/**最后登录日期*/
	private Date loginDate;
	
	/**最后登录会话id*/
	private String loginSessionId;

		
	/**身份证号*/
	private String idCard;
	
	/**个人签名*/
	private String signature;
	
	/**自我介绍*/
	private String introduce;
	
	/**个人主页*/
	private String site;
	
	/**微博*/
	private String weibo;
	
	/**微信*/
	private String weixin;
	
	/**qq*/
	private String qq;
	
	/**公司*/
	private String company;
	
	/**职业*/
	private String occupation;
	
	/**头衔*/
	private String honor;
	
	/**消费金额*/
	private BigDecimal amount;
	
	/**余额*/
	private BigDecimal balance;
	
	/** 安全密匙 */
	private SafeKey safeKey;
	
	/** 地区 */
	private Area area;
	
	/** 会员等级 */
	private MemberRank memberRank;
	
	/** 经验值*/
	private Integer experienceValue;
	
	/**学币*/
	private Integer learningCoin;
	
	/** 笔记*/
	private Set<CourseNote> courseNotes = new HashSet<CourseNote>();

	/** 预存款 */
	private Set<Deposit> deposits = new HashSet<Deposit>();
	/**课程学习*/
	private Set<CourseLearn> courseLearns=new HashSet<CourseLearn>();
	
	/**
	 * 报考地区
	 */
	private Long  examAreaId;
	/**
	 * 报考大纲
	 */
	private Long examOutlineCategoryId;
	
	@OneToMany(mappedBy="member",fetch=FetchType.LAZY)
	@OrderBy("createDate desc")
	public Set<CourseReview> getCourseReviews() {
		return courseReviews;
	}
	public void setCourseReviews(Set<CourseReview> courseReviews) {
		this.courseReviews = courseReviews;
	}
	@OneToMany(mappedBy="member",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	@OrderBy("createDate desc")
	public Set<CourseThreadPost> getCourseThreadPosts() {
		return courseThreadPosts;
	}
	public void setCourseThreadPosts(Set<CourseThreadPost> courseThreadPosts) {
		this.courseThreadPosts = courseThreadPosts;
	}
	@OneToMany(mappedBy="member",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	@OrderBy("createDate desc")
	public Set<CourseThread> getCourseThreads() {
		return courseThreads;
	}
	public void setCourseThreads(Set<CourseThread> courseThreads) {
		this.courseThreads = courseThreads;
	}

	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	@NotEmpty
	@Email
	@Length(max = 200)
	@Column(nullable = false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@NotEmpty(groups = Save.class)
	@Pattern(regexp = "^[^\\s&\"<>]+$")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@JsonProperty
	@NotEmpty(groups = Save.class)
	@Pattern(regexp = "^[0-9a-z_A-Z\\u4e00-\\u9fa5]+$")
	@Column(nullable = false,unique = true,length = 100)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	@JsonProperty
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@NotNull(groups = Save.class)
	@Min(0)
	@Column(nullable = false)
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Integer getCoin() {
		return coin;
	}
	public void setCoin(Integer coin) {
		this.coin = coin;
	}
	
	public String getEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(String emailVerified) {
		this.emailVerified = emailVerified;
	}
	public Integer getSetup() {
		return setup;
	}
	public void setSetup(Integer setup) {
		this.setup = setup;
	}
	public Integer getPromoted() {
		return promoted;
	}
	public void setPromoted(Integer promoted) {
		this.promoted = promoted;
	}
	public Date getPromotedTime() {
		return promotedTime;
	}
	public void setPromotedTime(Date promotedTime) {
		this.promotedTime = promotedTime;
	}
	
	@Length(max = 200)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull
	@Column(nullable = false)
	public boolean getLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	
	@NotNull
	@Column(nullable = false)
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	@Length(max = 200)
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
    
	/**
	 * 获取地址
	 * 
	 * @return 地址
	 */
	@Length(max = 200)
	public String getAddress() {
		return address;
	}
	
	/**
	 * 设置地址
	 * 
	 * @param address
	 *            地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getApprovalTime() {
		return approvalTime;
	}
	public void setApprovalTime(Date approvalTime) {
		this.approvalTime = approvalTime;
	}
	public ApprovalStatus getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(ApprovalStatus approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public Integer getNewMessageNum() {
		return newMessageNum;
	}
	public void setNewMessageNum(Integer newMessageNum) {
		this.newMessageNum = newMessageNum;
	}
	public Integer getNewNotificationNum() {
		return newNotificationNum;
	}
	public void setNewNotificationNum(Integer newNotificationNum) {
		this.newNotificationNum = newNotificationNum;
	}
	public String getRegisterIp() {
		return registerIp;
	}
	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}
	
	@Column(nullable = false)
	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}
	
	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}
	public Date getLockedDate() {
		return lockedDate;
	}
	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getLoginSessionId() {
		return loginSessionId;
	}
	public void setLoginSessionId(String loginSessionId) {
		this.loginSessionId = loginSessionId;
	}
	

	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getWeibo() {
		return weibo;
	}
	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getHonor() {
		return honor;
	}
	public void setHonor(String honor) {
		this.honor = honor;
	}
	
	/**
	 * 获取消费金额
	 * 
	 * @return 消费金额
	 */
	@Column(nullable = false, precision = 27, scale = 12)
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	/**
	 * 获取余额
	 * 
	 * @return 余额
	 */
	@NotNull(groups = Save.class)
	@Min(0)
	@Digits(integer = 12, fraction = 3)
	@Column(nullable = false, precision = 27, scale = 12)
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	/**
	 * 获取安全密匙
	 * 
	 * @return 安全密匙
	 */
	@Embedded
	public SafeKey getSafeKey() {
		return safeKey;
	}
	
	/**
	 * 设置安全密匙
	 * 
	 * @param safeKey
	 *            安全密匙
	 */
	public void setSafeKey(SafeKey safeKey) {
		this.safeKey = safeKey;
	}
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false)
	public MemberRank getMemberRank() {
		return memberRank;
	}
	
	public void setMemberRank(MemberRank memberRank) {
		this.memberRank = memberRank;
	}
	

	/**
	 * 获取地区
	 * 
	 * @return 地区
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	public Area getArea() {
		return area;
	}
	
	/**
	 * 设置地区
	 * 
	 * @param area
	 *            地区
	 */
	public void setArea(Area area) {
		this.area = area;
	}
	
	
	@OneToMany(mappedBy="member",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	@OrderBy("createDate desc")
	public Set<CourseNote> getCourseNotes() {
		return courseNotes;
	}
	public void setCourseNotes(Set<CourseNote> courseNotes) {
		this.courseNotes = courseNotes;
	}
	/**
	 * 获取出生日期
	 * 
	 * @return 出生日期
	 */
	public Date getBirth() {
		return birth;
	}
	
	/**
	 * 设置出生日期
	 * 
	 * @param birth
	 *            出生日期
	 */
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	
	
	/**
	 * 获取预存款
	 * 
	 * @return 预存款
	 */
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	public Set<Deposit> getDeposits() {
		return deposits;
	}
	
	/**
	 * 设置预存款
	 * 
	 * @param deposits
	 *            预存款
	 */
	public void setDeposits(Set<Deposit> deposits) {
		this.deposits = deposits;
	}
	
	
	
	/**
	 * 移除所有会员注册项值
	 */
	@Transient
	public void removeAttributeValue() {
		setName(null);
		setGender(null);
		setMobile(null);
		for (int i = 0; i < ATTRIBUTE_VALUE_PROPERTY_COUNT; i++) {
			String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + i;
			try {
				PropertyUtils.setProperty(this, propertyName, null);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
	}
	
	@OneToMany(mappedBy="member")
	public Set<CourseFavorite> getCourseFavorites() {
		return courseFavorites;
	}
	
	public void setCourseFavorites(Set<CourseFavorite> courseFavorites) {
		this.courseFavorites = courseFavorites;
	}
	
	@OneToMany(mappedBy="member",fetch=FetchType.LAZY)
	public Set<CourseLearn> getCourseLearns() {
		return courseLearns;
	}
	public void setCourseLearns(Set<CourseLearn> courseLearns) {
		this.courseLearns = courseLearns;
	}
	
	/**
	 * 获取接收的消息
	 * 
	 * @return 接收的消息
	 */
	@OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	public Set<Message> getInMessages() {
		return inMessages;
	}

	/**
	 * 设置接收的消息
	 * 
	 * @param inMessages
	 *            接收的消息
	 */
	public void setInMessages(Set<Message> inMessages) {
		this.inMessages = inMessages;
	}

	/**
	 * 获取发送的消息
	 * 
	 * @return 发送的消息
	 */
	@OneToMany(mappedBy = "sender", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	public Set<Message> getOutMessages() {
		return outMessages;
	}

	/**
	 * 设置发送的消息
	 * 
	 * @param outMessages
	 *            发送的消息
	 */
	public void setOutMessages(Set<Message> outMessages) {
		this.outMessages = outMessages;
	}
	
	@OneToMany(mappedBy="latestPostMember",fetch=FetchType.LAZY)
	public Set<CourseThread> getThreads() {
		return threads;
	}
	public void setThreads(Set<CourseThread> threads) {
		this.threads = threads;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public Integer getExperienceValue() {
		return experienceValue;
	}
	public void setExperienceValue(Integer experienceValue) {
		this.experienceValue = experienceValue;
	}
	public Integer getLearningCoin() {
		return learningCoin;
	}
	public void setLearningCoin(Integer learningCoin) {
		this.learningCoin = learningCoin;
	}
	public Long getExamAreaId() {
		return examAreaId;
	}
	public void setExamAreaId(Long examAreaId) {
		this.examAreaId = examAreaId;
	}
	public Long getExamOutlineCategoryId() {
		return examOutlineCategoryId;
	}
	public void setExamOutlineCategoryId(Long examOutlineCategoryId) {
		this.examOutlineCategoryId = examOutlineCategoryId;
	}
}