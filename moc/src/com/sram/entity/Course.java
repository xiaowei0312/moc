/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.annotations.Type;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.Length;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sram.CommonAttributes;
import com.sram.util.FreemarkerUtils;

import freemarker.template.TemplateException;

/**
 * Entity - 课程
 * 
 * @author Sram Team
 * @version 1.0
 */
@Entity
@Table(name = "moc_course")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_course_sequence")
public class Course extends OrderEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 点击数缓存名称 */
	public static final String HITS_CACHE_NAME = "courseHits";

	/** 点击数缓存更新间隔时间 */
	public static final int HITS_CACHE_INTERVAL = 600000;
	
	/** 静态路径 */
	private static String staticPath;
	
	/** 第二个课程页面的静态路径字符串*/
	private static String staticPath2;

	/** 笔记*/
	private Set<CourseNote> courseNotes = new HashSet<CourseNote>();
	private Set<CourseChapter> childrenChapters = new HashSet<CourseChapter>();
	private Set<CourseLesson> childrenLessons = new HashSet<CourseLesson>();
	private Set<CourseThread> courseThreads = new HashSet<CourseThread>();
	private Set<CourseLessonLearn> courseLessonLearns=new HashSet<CourseLessonLearn>();
	private Set<CourseReview> courseReviews = new HashSet<CourseReview>();
	
	/**收藏课程*/
	private Set<CourseFavorite> courseFavorites=new HashSet<CourseFavorite>();
	
	/**课程学习*/
	private Set<CourseLearn> courseLearns=new HashSet<CourseLearn>();
	
	/**课程公告*/
	private List<CourseAnnouncement> courseAnnouncements=new ArrayList<CourseAnnouncement>();
	
	/** 名称 */
	private String title;
	private String subtitle;

	/** 课程发布人类别*/
	public enum UserType {
		member, teacher, orgadmin
	}

	private UserType userType;

	/** 课程性名称前缀 */
	public enum Status {
		draft, published, closed
	}

	private Status status;

	/** 学生人数是否显示 */
	public enum ShowStudentNumType {
		opened, closed
	}

	private ShowStudentNumType showStudentNumType;

	/** 开启有效期通知*/
	public enum DeadlineNotify {
		none, active
	}

	private DeadlineNotify deadlineNotify;

	/** 连载模式*/
	public enum SerializeMode {
		none, serialize, finished
	}

	private SerializeMode serializeMode;
	private String type;
	private Integer maxStudentNum;
	
	/** 价格*/
	private Float price;
	private Float coinPrice;
	
	/** 过期天数*/
	private Integer expiryDay;

	private Float income;
	
	/** 学完所有课时，可获得的所有学分*/
	private Integer giveCredit;
	
	/**课时数*/
	private Integer lessonNum = 0;
	
	/** 排行分数*/
	private Integer rating;
	
	/** 投票人数*/
	private Integer ratingNum;
	
	/** 可以免费看的会员等级*/
	private Integer vipLevelId;
	private CourseCategory courseCategory;
	
	/** 展示图片 */
	private String sourceImage;
	private String largeImage;
	private String mediumImage;
	private String thumbnailImage;

	private String about;
	private String teacherIds="";
	private String goals;
	private String audiences;
	
	/**推荐课程*/
	private boolean isRecommend=false;
	private Integer recommendedSeq=0;
	private Date recommendedTime;
	
	/**完成课程人数*/
	private Long finishStuNum;
	
	/** 标签ids*/
	private String tags;
	
	/** 上课地区id*/
	private Long locationId;
	private String address;
	private Integer studentNum;
	private Integer hitNum;
	
	/** 课程发布人id*/
	private Admin admin;

	/** 剩余天数*/
	private Integer daysOfNotifyBeforeDeadline;
	private Date freeStartTime;
	private Date freeEndTime;
	/**课程类别treePath*/
	private String treePath;

	static {
		try {
			File sramXmlFile = new ClassPathResource(
					CommonAttributes.SRAM_XML_PATH).getFile();
			org.dom4j.Document document = new SAXReader().read(sramXmlFile);
			
			//获取课程内容的模板
			org.dom4j.Element element = (org.dom4j.Element) document
					.selectSingleNode("/sram/template[@id='courseContent']");
			org.dom4j.Element element2 = (Element) document.selectSingleNode("/sram/template[@id='courseContent2']");
			
			//获取sram.xml的静态路径字符串
			staticPath = element.attributeValue("staticPath");
			staticPath2 = element2.attributeValue("staticPath");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	


	@OneToMany(mappedBy="course",fetch=FetchType.LAZY)
	@OrderBy("createDate desc")
	public Set<CourseReview> getCourseReviews() {
		return courseReviews;
	}

	public void setCourseReviews(Set<CourseReview> courseReviews) {
		this.courseReviews = courseReviews;
	}

	@OneToMany(mappedBy="course",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	@OrderBy("createDate desc")
	public Set<CourseThread> getCourseThreads() {
		return courseThreads;
	}

	public void setCourseThreads(Set<CourseThread> courseThreads) {
		this.courseThreads = courseThreads;
	}

	@OneToMany(mappedBy="course",fetch = FetchType.LAZY)
	@OrderBy("createDate desc")
	public Set<CourseNote> getCourseNotes() {
		return courseNotes;
	}

	public void setCourseNotes(Set<CourseNote> courseNotes) {
		this.courseNotes = courseNotes;
	}

	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	public Set<CourseChapter> getChildrenChapters() {
		return childrenChapters;
	}

	public void setChildrenChapters(Set<CourseChapter> childrenChapters) {
		this.childrenChapters = childrenChapters;
	}
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
	public Set<CourseLesson> getChildrenLessons() {
		return childrenLessons;
	}

	public void setChildrenLessons(Set<CourseLesson> childrenLessons) {
		this.childrenLessons = childrenLessons;
	}
	
	@OneToMany(mappedBy = "course")
	public List<CourseAnnouncement> getCourseAnnouncements() {
		return courseAnnouncements;
	}

	public void setCourseAnnouncements(List<CourseAnnouncement> courseAnnouncements) {
		this.courseAnnouncements = courseAnnouncements;
	}

	@JsonProperty
	@Field(store = Store.YES, index = Index.NO)
	@Length(max = 200)
	public String getSourceImage() {
		return sourceImage;
	}

	public void setSourceImage(String sourceImage) {
		this.sourceImage = sourceImage;
	}
	
	public String getLargeImage() {
		return largeImage;
	}

	public void setLargeImage(String largeImage) {
		this.largeImage = largeImage;
	}

	public String getMediumImage() {
		return mediumImage;
	}

	public void setMediumImage(String mediumImage) {
		this.mediumImage = mediumImage;
	}

	public String getThumbnailImage() {
		return thumbnailImage;
	}

	public void setThumbnailImage(String thumbnailImage) {
		this.thumbnailImage = thumbnailImage;
	}
	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public Status getStatus() {
		return status;
	}
    
	@NotNull
	public Long getFinishStuNum() {
		return finishStuNum;
	}

	public void setFinishStuNum(Long finishStuNum) {
		this.finishStuNum = finishStuNum;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	public ShowStudentNumType getShowStudentNumType() {
		return showStudentNumType;
	}

	public void setShowStudentNumType(ShowStudentNumType showStudentNumType) {
		this.showStudentNumType = showStudentNumType;
	}
	public DeadlineNotify getDeadlineNotify() {
		return deadlineNotify;
	}

	public void setDeadlineNotify(DeadlineNotify deadlineNotify) {
		this.deadlineNotify = deadlineNotify;
	}
	public SerializeMode getSerializeMode() {
		return serializeMode;
	}

	public void setSerializeMode(SerializeMode serializeMode) {
		this.serializeMode = serializeMode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getMaxStudentNum() {
		return maxStudentNum;
	}

	public void setMaxStudentNum(Integer maxStudentNum) {
		this.maxStudentNum = maxStudentNum;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Float getCoinPrice() {
		return coinPrice;
	}

	public void setCoinPrice(Float coinPrice) {
		this.coinPrice = coinPrice;
	}

	public Integer getExpiryDay() {
		return expiryDay;
	}

	public void setExpiryDay(Integer expiryDay) {
		this.expiryDay = expiryDay;
	}

	public Float getIncome() {
		return income;
	}

	public void setIncome(Float income) {
		this.income = income;
	}
    
	@NotNull
	@Min(0)
	public Integer getLessonNum() {
		return lessonNum;
	}

	public void setLessonNum(Integer lessonNum) {
		this.lessonNum = lessonNum;
	}

	public Integer getGiveCredit() {
		return giveCredit;
	}

	public void setGiveCredit(Integer giveCredit) {
		this.giveCredit = giveCredit;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getRatingNum() {
		return ratingNum;
	}

	public void setRatingNum(Integer ratingNum) {
		this.ratingNum = ratingNum;
	}

	public Integer getVipLevelId() {
		return vipLevelId;
	}

	public void setVipLevelId(Integer vipLevelId) {
		this.vipLevelId = vipLevelId;
	}
	

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "course_category")
	public CourseCategory getCourseCategory() {
		return courseCategory;
	}

	public void setCourseCategory(CourseCategory courseCategory) {
		this.courseCategory = courseCategory;
	}
	@Lob
	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getTeacherIds() {
		return teacherIds;
	}

	public void setTeacherIds(String teacherIds) {
		this.teacherIds = teacherIds;
	}
	@Lob
	public String getGoals() {
		return goals;
	}

	public void setGoals(String goals) {
		this.goals = goals;
	}
	@Lob
	public String getAudiences() {
		return audiences;
	}

	public void setAudiences(String audiences) {
		this.audiences = audiences;
	}


	public Integer getRecommendedSeq() {
		return recommendedSeq;
	}

	public void setRecommendedSeq(Integer recommendedSeq) {
		this.recommendedSeq = recommendedSeq;
	}

	public Date getRecommendedTime() {
		return recommendedTime;
	}

	public void setRecommendedTime(Date recommendedTime) {
		this.recommendedTime = recommendedTime;
	}

	@Type(type = "text")
	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	@Length(max = 256)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
    
	@NotNull
	public Integer getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(Integer studentNum) {
		this.studentNum = studentNum;
	}

	public Integer getHitNum() {
		return hitNum;
	}

	public void setHitNum(Integer hitNum) {
		this.hitNum = hitNum;
	}
    
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false,name="user_id")
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Integer getDaysOfNotifyBeforeDeadline() {
		return daysOfNotifyBeforeDeadline;
	}

	public void setDaysOfNotifyBeforeDeadline(Integer daysOfNotifyBeforeDeadline) {
		this.daysOfNotifyBeforeDeadline = daysOfNotifyBeforeDeadline;
	}

	public Date getFreeStartTime() {
		return freeStartTime;
	}

	public void setFreeStartTime(Date freeStartTime) {
		this.freeStartTime = freeStartTime;
	}

	public Date getFreeEndTime() {
		return freeEndTime;
	}

	public void setFreeEndTime(Date freeEndTime) {
		this.freeEndTime = freeEndTime;
	}
	/**
	 * 获取访问路径
	 * 
	 * @return 访问路径
	 */
	@JsonProperty
	@Transient
	public String getPath() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", getId());
		model.put("createDate", getCreateDate());
		try {
			
			//sram.xml中的staticPath+model-----/course/content/201501/1.html
			return FreemarkerUtils.process(staticPath, model);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取第二个页面的访问路径
	 * 
	 * @return 访问路径
	 */
	@JsonProperty
	@Transient
	public String getPath2() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", getId());
		model.put("createDate", getCreateDate());
		model.put("modifyDate", getModifyDate());
		model.put("name", getTitle());
		model.put("seoTitle", getSubtitle());
		model.put("seoDescription", getAbout());
		model.put("courseCategory", getCourseCategory());
		try {
			return FreemarkerUtils.process(staticPath2, model);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return null;
	}
    
	@OneToMany(mappedBy = "course")
	public Set<CourseLessonLearn> getCourseLessonLearns() {
		return courseLessonLearns;
	}

	public void setCourseLessonLearns(Set<CourseLessonLearn> courseLessonLearns) {
		this.courseLessonLearns = courseLessonLearns;
	}
    
	@OneToMany(mappedBy = "course")
	public Set<CourseFavorite> getCourseFavorites() {
		return courseFavorites;
	}

	public void setCourseFavorites(Set<CourseFavorite> courseFavorites) {
		this.courseFavorites = courseFavorites;
	}
    
	@OneToMany(mappedBy = "course",fetch=FetchType.LAZY)
	public Set<CourseLearn> getCourseLearns() {
		return courseLearns;
	}

	public void setCourseLearns(Set<CourseLearn> courseLearns) {
		this.courseLearns = courseLearns;
	}

	public boolean getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(boolean isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
    
	
}