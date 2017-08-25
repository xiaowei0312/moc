package com.sram.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * Entity - 课程公告
 * 
 * @author Sram Team
 * @version 1.0
 */

@Entity
@Table(name = "moc_course_announcement")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_course_announcement_sequence")
public class CourseAnnouncement extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8637468177928731333L;
    //创建人Id
	private Long userId;
	//课程Id
	private Course course;
	//公告内容
	private String content;
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	
	@NotNull
    @ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(nullable = false,name="course_id")
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	@Lob
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
		
}
