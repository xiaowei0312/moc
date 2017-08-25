package com.sram.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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

import com.sram.Constants.TestpaperType;

/**
 * 试卷
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "moc_testpaper")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_testpaper_sequence")
public class Testpaper extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 试卷名称 */
	private String name;

	/** 试卷说明 */
	private String description;

	/** 限时(单位：秒) */
	private Integer limitedTime=0;

	public enum Status {
		draft, open, closed
	}

	/** 试卷状态 */
	private Status status = Status.draft;

	/** 总分 */
	private Float score;

	/** 通过考试的分数线 */
	private Float passedScore;

	/** 题目数量 */
	private Integer itemCount;
	
	/** 前台创建人 */
	private Member createMember;
	
	/** 前台修改人 */
	private Member updatedMember;
	
	/** 后台创建人 */
	private Admin createAdmin;
	
	/** 后台修改人 */
	private Admin updatedAdmin;
	
	/** 所属类别 */
	private OutlineCategory outlineCategory;
	
	/** 所属地区 */
	private Area area;
	/**
	 * 试卷类型
	 */
	private TestpaperType testpaperType;
	
	/**
	 * 真题的出卷年月
	 */
	private String oldYearMonth;
	
	/** 错误数量 */
	private int wrongCount;
	/** 题目总数量 */
	private int totalCount;
	
	/**考试结果*/
	private Set<TestpaperResult> testpaperResults=new HashSet<TestpaperResult>();
	
	/**章节列表*/
	private Set<TestpaperChapter> testpaperChapters = new HashSet<TestpaperChapter>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getLimitedTime() {
		return limitedTime;
	}

	public void setLimitedTime(Integer limitedTime) {
		this.limitedTime = limitedTime;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Float getPassedScore() {
		return passedScore;
	}

	public void setPassedScore(Float passedScore) {
		this.passedScore = passedScore;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "outlineCategoryID")
	public OutlineCategory getOutlineCategory() {
		return outlineCategory;
	}

	public void setOutlineCategory(OutlineCategory outlineCategory) {
		this.outlineCategory = outlineCategory;
	}
	/**
	 * 获取地区
	 * 
	 * @return 地区
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "areaID")
	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TestpaperType getTestpaperType() {
		return testpaperType;
	}

	public void setTestpaperType(TestpaperType testpaperType) {
		this.testpaperType = testpaperType;
	}

	@Column(length=10)
	public String getOldYearMonth() {
		return oldYearMonth;
	}

	public void setOldYearMonth(String oldYearMonth) {
		this.oldYearMonth = oldYearMonth;
	}
	/**
	 * 章节列表
	 * @return
	 */
	@OneToMany(mappedBy="testpaper",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	@OrderBy("order asc")
	public Set<TestpaperChapter> getTestpaperChapters() {
		return testpaperChapters;
	}

	public void setTestpaperChapters(Set<TestpaperChapter> testpaperChapters) {
		this.testpaperChapters = testpaperChapters;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="createMemberId")
	public Member getCreateMember() {
		return createMember;
	}

	public void setCreateMember(Member createMember) {
		this.createMember = createMember;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="updatedMemberId")
	public Member getUpdatedMember() {
		return updatedMember;
	}

	public void setUpdatedMember(Member updatedMember) {
		this.updatedMember = updatedMember;
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
	public int getWrongCount() {
		return wrongCount;
	}

	public void setWrongCount(int wrongCount) {
		this.wrongCount = wrongCount;
	}
	@Transient
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	@OneToMany(mappedBy="testpaper",fetch=FetchType.LAZY)
	public Set<TestpaperResult> getTestpaperResults() {
		return testpaperResults;
	}

	public void setTestpaperResults(Set<TestpaperResult> testpaperResults) {
		this.testpaperResults = testpaperResults;
	}
	
	
}
