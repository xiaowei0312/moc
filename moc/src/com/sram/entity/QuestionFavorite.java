package com.sram.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * 题目收藏
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "moc_question_favorite")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_question_favorite_sequence")
public class QuestionFavorite extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/** 题目ID */
	private Question question;
	/** 收藏人ID */
	private Long userId;
	/** 题目所属对象 */
	private String target;
	/**收藏时间*/
	private Integer createdTime;
	/**顶级大纲ID*/
	private Long rootOutlineCategory;
	/**当前大纲*/
	private OutlineCategory outlineCategory; 
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="questionId")
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Integer getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Integer createdTime) {
		this.createdTime = createdTime;
	}

	public Long getRootOutlineCategory() {
		return rootOutlineCategory;
	}

	public void setRootOutlineCategory(Long rootOutlineCategory) {
		this.rootOutlineCategory = rootOutlineCategory;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "outlineCategoryID")
	public OutlineCategory getOutlineCategory() {
		return outlineCategory;
	}

	public void setOutlineCategory(OutlineCategory outlineCategory) {
		this.outlineCategory = outlineCategory;
	}
}
