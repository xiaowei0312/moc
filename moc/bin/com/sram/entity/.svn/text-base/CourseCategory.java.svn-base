package com.sram.entity;

/*
 * .
 * Support: http://www.moc.cc
 * 
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity - 课程分类
 * 
 * @author Sram Team
 * @version 1.0
 */
@Entity
@Table(name = "moc_course_category")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_course_category_sequence")
public class CourseCategory extends OrderEntity {
	private static final long serialVersionUID = 1L;

	/** 树路径分隔符 */
	public static final String TREE_PATH_SEPARATOR = ",";
	
	/** 名称 */
	private String name="";

	/** 页面标题 */
	private String seoTitle="";

	/** 页面关键词 */
	private String seoKeywords="";

	/** 页面描述 */
	private String seoDescription="";

	/** 树路径 */
	private String treePath;

	/** 层级 */
	private Integer grade=0;

	/** 上级分类 */
	private CourseCategory parent;
	
	/** 下级分类 */
	private Set<CourseCategory> children = new HashSet<CourseCategory>();
	
	/** 课程 */
	private Set<Course> courses = new HashSet<Course>();
	private String description;
	
	/**话题问答*/
	private Set<CourseThread> courseThreads=new HashSet<CourseThread>();
	
	
	
	@NotEmpty
	@Length(max = 200)
	@Column(nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 获取页面标题
	 * 
	 * @return 页面标题
	 */
	@Length(max = 200)
	public String getSeoTitle() {
		return seoTitle;
	}
	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}
	
	/**
	 * 获取页面关键词
	 * 
	 * @return 页面关键词
	 */
	@Length(max = 200)
	public String getSeoKeywords() {
		return seoKeywords;
	}
	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}
	

	
	/**
	 * 获取页面描述
	 * 
	 * @return 页面描述
	 */
	@Length(max = 200)
	public String getSeoDescription() {
		return seoDescription;
	}
	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}
	
	/**
	 * 获取树路径
	 * 
	 * @return 树路径
	 */
	@Column(nullable = false)
	public String getTreePath() {
		return treePath;
	}
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	/**
	 * 获取层级
	 * 
	 * @return 层级
	 */
	@Column(nullable = false)
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	
	/**
	 * 获取上级分类
	 * 
	 * @return 上级分类
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	public CourseCategory getParent() {
		return parent;
	}
	public void setParent(CourseCategory parent) {
		this.parent = parent;
	}
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("order asc")
	public Set<CourseCategory> getChildren() {
		return children;
	}
	public void setChildren(Set<CourseCategory> children) {
		this.children = children;
	}
	
	
	
	@OneToMany(mappedBy = "courseCategory", fetch = FetchType.LAZY)
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	
	
	/**
	 * 获取树路径
	 * 
	 * @return 树路径
	 */
	@Transient
	public List<Long> getTreePaths() {
		List<Long> treePaths = new ArrayList<Long>();
		String[] ids = StringUtils.split(getTreePath(), TREE_PATH_SEPARATOR);
		if (ids != null) {
			for (String id : ids) {
				treePaths.add(Long.valueOf(id));
			}
		}
		return treePaths;
	}
	
	@OneToMany(mappedBy = "courseCategory", fetch = FetchType.LAZY)
	public Set<CourseThread> getCourseThreads() {
		return courseThreads;
	}
	public void setCourseThreads(Set<CourseThread> courseThreads) {
		this.courseThreads = courseThreads;
	}
}