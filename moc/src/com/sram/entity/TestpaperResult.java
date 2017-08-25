package com.sram.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sram.Constants.PassedStatus;
import com.sram.Constants.Status;
import com.sram.Constants.TestpaperType;

@Entity
@Table(name = "moc_testpaper_result")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_testpaper_result_sequence")
public class TestpaperResult extends OrderEntity {
	private static final long serialVersionUID = 1L;
	/**试卷名称*/
	private String paperName;
	/**试卷ID*/
	private Testpaper testpaper;
	/**做卷人ID*/
	private Long userId;

	private Member member;
	/**总分*/
	private Float score=0f;
	/**主观题得分*/
	private Float objectiveScore=0f;
	/**客观题得分*/
	private Float subjectiveScore=0f;
	/**老师评价*/
	private String teacherSay;
	/**正确题目数*/
	private Integer rightItemCount;
	/**考试通过状态，none表示该考试没有*/
	private PassedStatus passedStatus=PassedStatus.none;
	/**试卷限制时间(秒)*/
	private Integer limitedTime;
	/**开始时间*/
	private Date beginDate=new Date();
	/**结束时间*/
	private Date endDate;
	/**
	 * 提交试卷true ,未提交试卷false
	 */
	private  boolean active=false ;
	/**考试结果状态，'doing（考试中）','paused（考试暂停）','reviewing(待批阅)','finished(批阅结束)'*/
	private Status status=Status.doing;
	/**批卷老师ID*/
	private String checkTeacherId;
	/**批卷时间*/
	private Date checkedTime;
	/**用时*/
	private Integer usedTime=0;
	/**结果*/
	private Set<TestpaperItemResult> testpaperItemResults=new HashSet<TestpaperItemResult>();
	/**大纲**/
	private OutlineCategory outlineCategory;
	/**正确题目数*/
	private Integer totalItemCount;
	/**顶级大纲ID*/
	private Long rootOutlineCategory;
	@JsonProperty
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	@JsonProperty
	public Float getScore() {
		return score;
	}
	public void setScore(Float score) {
		this.score = score;
	}
	public Float getObjectiveScore() {
		return objectiveScore;
	}
	public void setObjectiveScore(Float objectiveScore) {
		this.objectiveScore = objectiveScore;
	}
	public Float getSubjectiveScore() {
		return subjectiveScore;
	}
	public void setSubjectiveScore(Float subjectiveScore) {
		this.subjectiveScore = subjectiveScore;
	}
	public String getTeacherSay() {
		return teacherSay;
	}
	public void setTeacherSay(String teacherSay) {
		this.teacherSay = teacherSay;
	}
	public Integer getRightItemCount() {
		return rightItemCount;
	}
	public void setRightItemCount(Integer rightItemCount) {
		this.rightItemCount = rightItemCount;
	}
	public Integer getLimitedTime() {
		return limitedTime;
	}
	public void setLimitedTime(Integer limitedTime) {
		this.limitedTime = limitedTime;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getCheckTeacherId() {
		return checkTeacherId;
	}
	public void setCheckTeacherId(String checkTeacherId) {
		this.checkTeacherId = checkTeacherId;
	}
	public Date getCheckedTime() {
		return checkedTime;
	}
	public void setCheckedTime(Date checkedTime) {
		this.checkedTime = checkedTime;
	}
	public Integer getUsedTime() {
		return usedTime;
	}
	public void setUsedTime(Integer usedTime) {
		this.usedTime = usedTime;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="testpaperId")
	public Testpaper getTestpaper() {
		return testpaper;
	}

	public void setTestpaper(Testpaper testpaper) {
		this.testpaper = testpaper;
	}
	public PassedStatus getPassedStatus() {
		return passedStatus;
	}
	public void setPassedStatus(PassedStatus passedStatus) {
		this.passedStatus = passedStatus;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	@ManyToOne
	@JoinColumn(name="userId",insertable=false,updatable=false)
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	@OneToMany(mappedBy="testpaperResult",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	public Set<TestpaperItemResult> getTestpaperItemResults() {
		return testpaperItemResults;
	}
	public void setTestpaperItemResults(
			Set<TestpaperItemResult> testpaperItemResults) {
		this.testpaperItemResults = testpaperItemResults;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "outlineCategoryID")
	public OutlineCategory getOutlineCategory() {
		return outlineCategory;
	}

	public void setOutlineCategory(OutlineCategory outlineCategory) {
		this.outlineCategory = outlineCategory;
	}
	public Integer getTotalItemCount() {
		return totalItemCount;
	}
	public void setTotalItemCount(Integer totalItemCount) {
		this.totalItemCount = totalItemCount;
	}
	public Long getRootOutlineCategory() {
		return rootOutlineCategory;
	}
	public void setRootOutlineCategory(Long rootOutlineCategory) {
		this.rootOutlineCategory = rootOutlineCategory;
	}
	@Override
	public String toString() {
		return "["+"paperName"+":"+paperName+","+"userId"+":"+userId+","
		+"score"+":"+score+","+"limitedTime"+":"+limitedTime+","
		+"endDate"+":"+endDate+",beginDate:"+beginDate+"]";
	}
}
