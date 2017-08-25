package com.sram.entity;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "moc_integral_rule")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_integral_rule")
public class IntegralRule extends BaseEntity {

	private String name;

	public enum TaskType {
		newTask, dailyTask, overallTask
	}

	private TaskType taskType;

	private Integer experienceValue;

	private Boolean isSetEvDailyLimit;

	private Integer evDailyLimit;

	private Integer learningCoin;

	private Boolean isSetLcDailyLimit;

	private Integer lcDailyLimit;

	private String description;

	private Integer taskCount;

	private String urlPath;

	private String appUrlPath;

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getExperienceValue() {
		return experienceValue;
	}

	public void setExperienceValue(Integer experienceValue) {
		this.experienceValue = experienceValue;
	}

	public Integer getEvDailyLimit() {
		return evDailyLimit;
	}

	public void setEvDailyLimit(Integer evDailyLimit) {
		this.evDailyLimit = evDailyLimit;
	}

	public Boolean getIsSetEvDailyLimit() {
		return isSetEvDailyLimit;
	}

	public void setIsSetEvDailyLimit(Boolean isSetEvDailyLimit) {
		this.isSetEvDailyLimit = isSetEvDailyLimit;
	}

	public Integer getLearningCoin() {
		return learningCoin;
	}

	public void setLearningCoin(Integer learningCoin) {
		this.learningCoin = learningCoin;
	}

	public Integer getLcDailyLimit() {
		return lcDailyLimit;
	}

	public void setLcDailyLimit(Integer lcDailyLimit) {
		this.lcDailyLimit = lcDailyLimit;
	}

	public Boolean getIsSetLcDailyLimit() {
		return isSetLcDailyLimit;
	}

	public void setIsSetLcDailyLimit(Boolean isSetLcDailyLimit) {
		this.isSetLcDailyLimit = isSetLcDailyLimit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public Integer getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	public String getUrlPath() {
		return urlPath;
	}

	public void setUrlPath(String urlPath) {
		this.urlPath = urlPath;
	}

	public String getAppUrlPath() {
		return appUrlPath;
	}

	public void setAppUrlPath(String appUrlPath) {
		this.appUrlPath = appUrlPath;
	}

}
