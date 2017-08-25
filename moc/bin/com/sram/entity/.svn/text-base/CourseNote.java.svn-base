package com.sram.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "moc_course_note")
@SequenceGenerator(name = "sequenceGenerator",sequenceName="moc_course_note_sequence")
public class CourseNote extends BaseEntity{
	private static final long serialVersionUID = 7757901476357444090L;
	
	/** 发布人*/
	private Member member;
	
	/**所属课程*/
	private Course course;
	
	/** 所属课时---单向关联（oneToOne无法使用懒加载）*/
	private CourseLesson courseLesson;
	
	/** 笔记内容*/
	private String content;
	
	/** 笔记内容的字数*/
	private Integer length;
	
	/**笔记状态 0私有，1公开*/
	private Integer status=1;

	@JsonProperty
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	@JsonProperty
	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	@JsonProperty
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userId")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="courseId",nullable=false)
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="lessonId",nullable=false)
	public CourseLesson getCourseLesson() {
		return courseLesson;
	}

	public void setCourseLesson(CourseLesson courseLesson) {
		this.courseLesson = courseLesson;
	}
}
