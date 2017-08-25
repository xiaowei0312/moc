package com.sram.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.alibaba.fastjson.annotation.JSONType;
/**
 * 大纲实体
 * @author Administrator
 *
 */
@Entity
@Table(name = "moc_outline_category")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_outline_category_sequence")
@JSONType(ignores = { 
		"treePaths","updatedAdmin","createAdmin",
		"seoTitle","seoKeywords","seoDescription",
		"questions","children","grade",
		"parent","description","grade",
		"createDate","modifyDate","treePath",
		"code","industryCategory","industryCategoryID"
	})
public class OutlineCategory extends OrderEntity{
	private static final long serialVersionUID = 1L;
	
	/** 树路径分隔符 */
	public static final String TREE_PATH_SEPARATOR = ",";
	/**所属行业*/
	private IndustryCategory industryCategory;
	/**
	 * 所属行业id
	 */
	private Long industryCategoryID;
	
	/**关联课程章节的类型*/
	public enum Course_chapter_type{
		course,chapter
	}
	private Course_chapter_type course_chapter_type;
	
	/**关联的id*/
	private Long course_chapter_id;
	
	/**编号*/
	private String code;
	/**名称*/
	private String name;
	/**描述*/
	private String description;
	/**上级分类*/
	private OutlineCategory parent;
	
	/**层级*/
	private Integer grade;
	
	/** 下级分类 */
	private Set<OutlineCategory> children = new HashSet<OutlineCategory>();
	
	/**问题*/
	private Set<Question> questions = new HashSet<Question>();
	
	/**页面描述*/
	private String seoDescription;
	
	/**页面关键字*/
	private String seoKeywords;
	
	/**页面标题*/
	private String seoTitle;
	/** 后台创建人 */
	private Admin createAdmin;
	
	/** 后台修改人 */
	private Admin updatedAdmin;
	
	/**树路径*/
	private String treePath;
	
	/**所有问题及子大纲的问题*/
	private Set<Question> allQuestions = new HashSet<Question>();
	
	/**课时*/
	private Set<CourseLesson> courseLessons=new HashSet<CourseLesson>();
	
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
	@Column(nullable = false)
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	@Length(max = 200)
	public String getSeoDescription() {
		return seoDescription;
	}
	public void setSeoDescription(String seoDescription) {
		this.seoDescription = seoDescription;
	}
	@Length(max = 200)
	public String getSeoKeywords() {
		return seoKeywords;
	}
	public void setSeoKeywords(String seoKeywords) {
		this.seoKeywords = seoKeywords;
	}
	@Length(max = 200)
	public String getSeoTitle() {
		return seoTitle;
	}
	
	public void setSeoTitle(String seoTitle) {
		this.seoTitle = seoTitle;
	}
	@Column(nullable = false)
	public String getTreePath() {
		return treePath;
	}
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
	@OneToMany(mappedBy = "outlineCategory", fetch = FetchType.LAZY)
	public Set<Question> getQuestions() {
		return questions;
	}
	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	public OutlineCategory getParent() {
		return parent;
	}
	public void setParent(OutlineCategory parent) {
		this.parent = parent;
	}
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("order asc")
	public Set<OutlineCategory> getChildren() {
		return children;
	}
	public void setChildren(Set<OutlineCategory> children) {
		this.children = children;
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
	@Column(nullable = false,unique=true)
	@Length(max = 30)
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="industry_category_id",insertable=false,updatable=false)
	public IndustryCategory getIndustryCategory() {
		return industryCategory;
	}
	public void setIndustryCategory(IndustryCategory industryCategory) {
		this.industryCategory = industryCategory;
	}
	@Column(name="industry_category_id",nullable=false)
	public Long getIndustryCategoryID() {
		return industryCategoryID;
	}
	public void setIndustryCategoryID(Long industryCategoryID) {
		this.industryCategoryID = industryCategoryID;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="createAdminId")
	public Admin getCreateAdmin() {
		return createAdmin;
	}
	public void setCreateAdmin(Admin createAdmin) {
		this.createAdmin = createAdmin;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="updatedAdminId")
	public Admin getUpdatedAdmin() {
		return updatedAdmin;
	}
	public void setUpdatedAdmin(Admin updatedAdmin) {
		this.updatedAdmin = updatedAdmin;
	}
	
	
	
	@Transient
	public Set<Question> getAllQuestions() {
		this.allQuestions.addAll(this.getQuestions());
		for (OutlineCategory outlineCategory : this.getChildren()) {
			this.allQuestions.addAll(outlineCategory.getQuestions());
			subOutlineCategoryChildren(outlineCategory);
		}
		return allQuestions;
	}
	public void setAllQuestions(Set<Question> allQuestions) {
		this.allQuestions = allQuestions;
	}
	@Transient
	private  Set<Question> subOutlineCategoryChildren(OutlineCategory outlineCategory){
		if(outlineCategory.getChildren().size()==0){
			return allQuestions;
		}
		for (OutlineCategory outlineCategory2 : outlineCategory.getChildren()) {
			this.allQuestions.addAll(outlineCategory2.getQuestions());
			subOutlineCategoryChildren(outlineCategory2);
		}
		
		return allQuestions;
		
	}
	
	@OneToMany(mappedBy = "outlineCategory", fetch = FetchType.LAZY)
	public Set<CourseLesson> getCourseLessons() {
		return courseLessons;
	}
	public void setCourseLessons(Set<CourseLesson> courseLessons) {
		this.courseLessons = courseLessons;
	}
	public Course_chapter_type getCourse_chapter_type() {
		return course_chapter_type;
	}
	public void setCourse_chapter_type(Course_chapter_type course_chapter_type) {
		this.course_chapter_type = course_chapter_type;
	}
	public Long getCourse_chapter_id() {
		return course_chapter_id;
	}
	public void setCourse_chapter_id(Long course_chapter_id) {
		this.course_chapter_id = course_chapter_id;
	}
}
