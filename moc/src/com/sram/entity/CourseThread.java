package com.sram.entity;

import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonProperty;



/**
 * 话题
 * @author 刚平
 *
 */
@Entity
@Table(name="moc_course_thread")
@SequenceGenerator(name = "sequenceGenerator",sequenceName="moc_course_thread_sequence")
public class CourseThread extends BaseEntity{

	private static final long serialVersionUID = -6923420452978497650L;

	/**话题所属课程*/
	private Course course;
	
	/**话题所属课时*/
	private CourseLesson courseLesson;
	
	/** 话题发布人*/
	private Member member;
	
	/**话题分类*/
	private CourseCategory courseCategory;
	
	/**话题回复集合*/
	private Set<CourseThreadPost> courseThreadPosts = new HashSet<CourseThreadPost>();
	public enum Type{
		discussion
		,question
	}
	private Type type=Type.discussion;
	
	/**是否置顶*/
	private boolean isStick=true;
	
	/**是否精华*/
	private boolean isElite=true;
	
	/** 是否关闭,0 false是公开，1 true是关闭*/
	private boolean isClosed=false;
	
	/** 话题标题*/
	private String title;
	
	/**话题内容 */
	private String content;
	
	/** 回复数*/
	private Integer postNum=0;
	
	/** 查看数*/
	private Integer hitNum=0;
	
	/** 关注数*/
	private Integer followNum=0;
	
	/** 最后回复人Id*/
	private Member latestPostMember;
	
	/**最后回复时间*/
	private Date latestPostTime;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="courseId")
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="lessonId")
	public CourseLesson getCourseLesson() {
		return courseLesson;
	}

	public void setCourseLesson(CourseLesson courseLesson) {
		this.courseLesson = courseLesson;
	}
	@JsonProperty
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	@OneToMany(mappedBy="courseThread",cascade=CascadeType.REMOVE)
	@OrderBy("createDate desc")
	public Set<CourseThreadPost> getCourseThreadPosts() {
		return courseThreadPosts;
	}

	public void setCourseThreadPosts(Set<CourseThreadPost> courseThreadPosts) {
		this.courseThreadPosts = courseThreadPosts;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	
	@JsonProperty
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@JsonProperty
	@Lob
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@JsonProperty
	public Integer getPostNum() {
		return postNum;
	}

	public void setPostNum(Integer postNum) {
		this.postNum = postNum;
	}
	@JsonProperty
	public Integer getHitNum() {
		return hitNum;
	}

	public void setHitNum(Integer hitNum) {
		this.hitNum = hitNum;
	}

	public Integer getFollowNum() {
		return followNum;
	}

	public void setFollowNum(Integer followNum) {
		this.followNum = followNum;
	}
    
	

	public Date getLatestPostTime() {
		return latestPostTime;
	}

	public void setLatestPostTime(Date latestPostTime) {
		this.latestPostTime = latestPostTime;
	}
    
	
	public boolean getIsClosed() {
		return isClosed;
	}

	public void setIsClosed(boolean isClosed) {
		this.isClosed = isClosed;
	}

	public boolean getIsStick() {
		return isStick;
	}

	public void setIsStick(boolean isStick) {
		this.isStick = isStick;
	}

	public boolean getIsElite() {
		return isElite;
	}

	public void setIsElite(boolean isElite) {
		this.isElite = isElite;
	}
    
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="latest_post_user_id")
	public Member getLatestPostMember() {
		return latestPostMember;
	}

	public void setLatestPostMember(Member latestPostMember) {
		this.latestPostMember = latestPostMember;
	}
    
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="courseCategoryId",nullable=true)
	public CourseCategory getCourseCategory() {
		return courseCategory;
	}

	public void setCourseCategory(CourseCategory courseCategory) {
		this.courseCategory = courseCategory;
	}
	
	
}
