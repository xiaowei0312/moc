package com.sram.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * 收藏课程
 */
@Entity
@Table(name = "moc_course_favorite")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_course_favorite _sequence")
public class CourseFavorite extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1652500903900554583L;
	
    private Course course;
    
    private Member member;
    
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
    
}
