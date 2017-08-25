package com.sram.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 话题的回复
 * @author 刚平
 *
 */
@Entity
@Table(name="moc_courseThread_post")
@SequenceGenerator(name="sequenceGenerator",sequenceName="moc_courseThread_post_sequence")
public class CourseThreadPost extends BaseEntity{
	private static final long serialVersionUID = -5712264959824180285L;
	
	private Long courseId;
	private Long lessonId;
	
	/**所属的话题*/
	private CourseThread courseThread;
	
	/**回复人*/
	private Member member;
	
	/**是否是精华*/
	private boolean isElite;
	
	/**正文*/
	private String content;
	
	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getLessonId() {
		return lessonId;
	}

	public void setLessonId(Long lessonId) {
		this.lessonId = lessonId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="threadId")
	public CourseThread getCourseThread() {
		return courseThread;
	}

	public void setCourseThread(CourseThread courseThread) {
		this.courseThread = courseThread;
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

	public boolean isElite() {
		return isElite;
	}

	public void setElite(boolean isElite) {
		this.isElite = isElite;
	}
	@JsonProperty
	@Lob
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
