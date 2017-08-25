package com.sram.entity;

import java.util.ArrayList;
import java.util.List;

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
import javax.validation.constraints.NotNull;

/**
 * Entity - 章节
 * 
 * @author Sram Team
 * @version 1.0
 */
@Entity
@Table(name = "moc_courseChapter")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_courseChapter_sequence")
public class CourseChapter extends OrderEntity {
	private static final long serialVersionUID = 1L;

	/** 树路径分隔符 */
	public static final String TREE_PATH_SEPARATOR = ",";

	public enum Type {
		chapter, unit
	}

	private Course course;

	/** 类型 */
	private Type type;

	/** 章的介绍*/
	private String summary;
	
	/** 章节编号标示一个课程的章 */
	private Integer number;

	/** 为一个课程的章节,小节排序 */
	private String title;

	private List<CourseLesson> courseLessons = new ArrayList<CourseLesson>();

	
	@OneToMany(mappedBy = "courseChapter", fetch = FetchType.LAZY)
	@OrderBy(value = "order asc")
	public List<CourseLesson> getCourseLessons() {
		return courseLessons;
	}

	public void setCourseLessons(List<CourseLesson> courseLessons) {
		this.courseLessons = courseLessons;
	}

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "chapter_course")
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Column(nullable = false, updatable = false)
	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Lob
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
