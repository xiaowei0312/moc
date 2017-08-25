package com.sram.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Entity - 课时
 * 
 * @author Sram Team
 * @version 1.0
 */

@Entity
@Table(name = "moc_courseLesson")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_courseLesson_sequence")
public class CourseLesson extends OrderEntity {
	private static final long serialVersionUID = 1L;

	public enum Status {
		unpublished, published
	}

	public enum ReplayStatus {
		ungenerated, generating, generated
	}

	private Course course;
	private CourseChapter courseChapter;

	/** 课时编号 */
	private Integer number;

	/** 是否为免费课时0 */
	private Integer free;
	private Status status;
	private String title;
	private String summary;

	/** 课时的类型 */
	private String type;

	/** 图文消息内容 */
	private String content;
	private Integer giveCredit;

	/** UploadFiles外键 */
	private UploadFiles uploadFiles;

	/** self,,,youku */
	private String mediaSource;
	private String mediaName;

	/** 媒体文件资源名 */
	private String mediaUri;

	/** 时长 */
	private Integer length = 0;

	/** 上传的资料数目 */
	private Integer materialNum;
	/**观看视频所需金币*/
	private Integer coin=0;

	/** 测验题目的数量 */
	private Integer quizNum;
	private Integer learnedNum;
	private Integer viewedNum;
	private Integer startTime;
	private Integer endTime;

	/** 直播时加入人数 */
	private Integer memberNum = 0;
	private ReplayStatus replayStatus;
	private Integer liveProvider;
	/** 课时发布人id */
	private Admin admin;

	/** 一个课时有多个话题 */
	private Set<CourseThread> courseThreads = new HashSet<CourseThread>();

	/** 一个课时有多个课时学习 */
	private Set<CourseLessonLearn> courseLessonLearns = new HashSet<CourseLessonLearn>();

	/** 资料 */
	private Set<CourseMaterial> courseMaterials = new HashSet<CourseMaterial>();

	// private CourseNote courseNote;----OneToOne无法使用懒加载
	private CourseLessonLearn courseLessonLearn;

	// 大纲类别
	private OutlineCategory outlineCategory;

	@OneToMany(mappedBy = "courseLesson", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy("createDate desc")
	public Set<CourseThread> getCourseThreads() {
		return courseThreads;
	}

	public void setCourseThreads(Set<CourseThread> courseThreads) {
		this.courseThreads = courseThreads;
	}

	@Transient
	public CourseLessonLearn getCourseLessonLearn() {
		return courseLessonLearn;
	}

	public void setCourseLessonLearn(CourseLessonLearn courseLessonLearn) {
		this.courseLessonLearn = courseLessonLearn;
	}

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(nullable = false, name = "lesson_course")
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(nullable = false, name = "lesson_chapter")
	public CourseChapter getCourseChapter() {
		return courseChapter;
	}

	public void setCourseChapter(CourseChapter courseChapter) {
		this.courseChapter = courseChapter;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getFree() {
		return free;
	}

	public void setFree(Integer free) {
		this.free = free;
	}

	@Column
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@JsonProperty
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Lob
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getGiveCredit() {
		return giveCredit;
	}

	public void setGiveCredit(Integer giveCredit) {
		this.giveCredit = giveCredit;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "media_id")
	public UploadFiles getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(UploadFiles uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public String getMediaSource() {
		return mediaSource;
	}

	public void setMediaSource(String mediaSource) {
		this.mediaSource = mediaSource;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getMediaUri() {
		return mediaUri;
	}

	public void setMediaUri(String mediaUri) {
		this.mediaUri = mediaUri;
	}

	@NotNull
	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getMaterialNum() {
		return materialNum;
	}

	public void setMaterialNum(Integer materialNum) {
		this.materialNum = materialNum;
	}

	public Integer getQuizNum() {
		return quizNum;
	}

	public void setQuizNum(Integer quizNum) {
		this.quizNum = quizNum;
	}

	@NotNull
	@Min(0)
	public Integer getLearnedNum() {
		return learnedNum;
	}

	public void setLearnedNum(Integer learnedNum) {
		this.learnedNum = learnedNum;
	}

	public Integer getViewedNum() {
		return viewedNum;
	}

	public void setViewedNum(Integer viewedNum) {
		this.viewedNum = viewedNum;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getEndTime() {
		return endTime;
	}

	public void setEndTime(Integer endTime) {
		this.endTime = endTime;
	}

	@NotNull
	@Min(0)
	public Integer getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(Integer memberNum) {
		this.memberNum = memberNum;
	}

	@Column(nullable = false, updatable = false)
	public ReplayStatus getReplayStatus() {
		return replayStatus;
	}

	public void setReplayStatus(ReplayStatus replayStatus) {
		this.replayStatus = replayStatus;
	}

	public Integer getLiveProvider() {
		return liveProvider;
	}

	public void setLiveProvider(Integer liveProvider) {
		this.liveProvider = liveProvider;
	}

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "user_id")
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@OneToMany(mappedBy = "courseLesson", fetch = FetchType.LAZY)
	public Set<CourseLessonLearn> getCourseLessonLearns() {
		return courseLessonLearns;
	}

	public void setCourseLessonLearns(Set<CourseLessonLearn> courseLessonLearns) {
		this.courseLessonLearns = courseLessonLearns;
	}

	@OneToMany(mappedBy = "courseLesson", cascade = CascadeType.REMOVE)
	public Set<CourseMaterial> getCourseMaterials() {
		return courseMaterials;
	}

	public void setCourseMaterials(Set<CourseMaterial> courseMaterials) {
		this.courseMaterials = courseMaterials;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "outline_categoryid", nullable = true)
	public OutlineCategory getOutlineCategory() {
		return outlineCategory;
	}

	public void setOutlineCategory(OutlineCategory outlineCategory) {
		this.outlineCategory = outlineCategory;
	}

	public Integer getCoin() {
		return coin;
	}

	public void setCoin(Integer coin) {
		this.coin = coin;
	}
	
}
