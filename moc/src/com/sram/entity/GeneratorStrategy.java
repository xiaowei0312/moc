package com.sram.entity;

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
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;


import com.sram.Constants.TestpaperType;
@Entity
@Table(name = "moc_generator_strategy")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_generator_strategy_sequence")
public class GeneratorStrategy extends BaseEntity {
	public enum GeneratorType{
		RANDOM,DIFFICULTY
	}
	/**试卷类型**/
	private TestpaperType testpaperType;
	/**生成方式**/
	private GeneratorType generatorType;
	/**时间显示（秒）**/
	private int timeLimit;
	/**难度**/
	private String difficulty;
	/**顶级大纲**/
	private OutlineCategory outlineCategory;
	/**难易度**/
	private  String percentagesSimple;
	private  String percentagesNormal;
	private  String percentagesDifficulty;
	/**题目数量**/
	private  String questionCount;
	
	/**滑块开始坐标**/
	private Integer sliderStart;
	/**滑块结束坐标**/
	private Integer sliderEnd;
	
	private Set<GeneratorQuestionConfig>  generatorQuestionConfigs=new HashSet<GeneratorQuestionConfig>();
	public TestpaperType getTestpaperType() {
		return testpaperType;
	}
	public void setTestpaperType(TestpaperType testpaperType) {
		this.testpaperType = testpaperType;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "outlineCategoryId")
	public OutlineCategory getOutlineCategory() {
		return outlineCategory;
	}
	public void setOutlineCategory(OutlineCategory outlineCategory) {
		this.outlineCategory = outlineCategory;
	}
	@OneToMany(mappedBy="generatorStrategy",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	public Set<GeneratorQuestionConfig> getGeneratorQuestionConfigs() {
		return generatorQuestionConfigs;
	}
	public void setGeneratorQuestionConfigs(
			Set<GeneratorQuestionConfig> generatorQuestionConfigs) {
		this.generatorQuestionConfigs = generatorQuestionConfigs;
	}
	@Transient
	public String getPercentagesSimple() {
		return percentagesSimple;
	}
	public void setPercentagesSimple(String percentagesSimple) {
		this.percentagesSimple = percentagesSimple;
	}
	@Transient
	public String getPercentagesNormal() {
		return percentagesNormal;
	}
	public void setPercentagesNormal(String percentagesNormal) {
		this.percentagesNormal = percentagesNormal;
	}
	@Transient
	public String getPercentagesDifficulty() {
		return percentagesDifficulty;
	}
	public void setPercentagesDifficulty(String percentagesDifficulty) {
		this.percentagesDifficulty = percentagesDifficulty;
	}
	public GeneratorType getGeneratorType() {
		return generatorType;
	}
	public void setGeneratorType(GeneratorType generatorType) {
		this.generatorType = generatorType;
	}
	@Transient
	public String getQuestionCount() {
		return questionCount;
	}
	public void setQuestionCount(String questionCount) {
		this.questionCount = questionCount;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	@Transient
	public Integer getSliderStart() {
		if (StringUtils.isNotBlank(this.getPercentagesSimple())) {
			return Integer.valueOf(this.getPercentagesSimple().replaceAll("%", ""));
		}
		return 0;
	}
	public void setSliderStart(Integer sliderStart) {
		this.sliderStart = sliderStart;
	}
	@Transient
	public Integer getSliderEnd() {
		if (this.getPercentagesSimple()!=null&&this.getPercentagesNormal()!=null) {
			return	Integer.valueOf(this.getPercentagesSimple().replaceAll("%", ""))+Integer.valueOf(this.getPercentagesNormal().replaceAll("%", ""));
		}
		return 0;
	}
	public void setSliderEnd(Integer sliderEnd) {
		this.sliderEnd = sliderEnd;
	}
	
}
