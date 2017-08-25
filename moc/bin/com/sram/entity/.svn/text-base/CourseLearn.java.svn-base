package com.sram.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.sram.entity.CourseLessonLearn.Status;


/*
 * 课程学习
 */
@Entity
@Table(name = "moc_course_learn")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_course_learn_sequence")
public class CourseLearn extends BaseEntity{
    //会员编号
	private Member member;
	//课程编号
	private Course course;
	
	/** 学习状态*/
	public enum Status{
		/*正在学习*/
		learning,
		/*已完成*/
		finished
	}
	
	//课程学习状态
	private Status status;
	
	//已完成的课时数量
	private Integer finishLessonNum=0;
	
	//备注
	private String description;
	
	
	@NotNull
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="course_id",nullable=false)
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id",nullable=false)
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	@NotNull
	public Integer getFinishLessonNum() {
		return finishLessonNum;
	}
	public void setFinishLessonNum(Integer finishLessonNum) {
		this.finishLessonNum = finishLessonNum;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
