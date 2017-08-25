package com.sram.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="moc_course_review")
@SequenceGenerator(name = "sequenceGenerator",sequenceName="moc_course_review_sequence")
public class CourseReview extends BaseEntity{

	private static final long serialVersionUID = 6460901343731907223L;
	
	/** 评论人*/
	private Member member;
	
	/** 被评价的课程*/
	private Course course;
	
	/**评价的标题*/
	private String title;
	
	/**评论的内容*/
	private String content;
	
	/**评分*/
	private Integer rating;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="memberId")
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="courseId")
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
	@Length(max=255)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Lob
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
