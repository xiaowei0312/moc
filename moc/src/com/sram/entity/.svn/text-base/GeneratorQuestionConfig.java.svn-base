package com.sram.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.sram.Constants.QuestionType;

/** 
 * <p>功能:</p> 
 * @author 杨楷
 * @date 2015-3-16 下午06:11:04
 */
@Entity
@Table(name = "moc_generator_question_config")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_generator_strategy_sequence")
public class GeneratorQuestionConfig extends BaseEntity {
	/**试题类型**/
	private QuestionType questionType;
	/**题目数量**/
	private int count;
	/**分数**/
	private float score;
	/**漏选得分**/
	private float missScore;
	/**策略**/
	private GeneratorStrategy generatorStrategy;
	/**章节名称**/
	private String chapterName;
	/**章节描述**/
	private String description;
	
	public QuestionType getQuestionType() {
		return questionType;
	}
	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public float getMissScore() {
		return missScore;
	}
	public void setMissScore(float missScore) {
		this.missScore = missScore;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "generatorStrategyId")
	public GeneratorStrategy getGeneratorStrategy() {
		return generatorStrategy;
	}
	public void setGeneratorStrategy(GeneratorStrategy generatorStrategy) {
		this.generatorStrategy = generatorStrategy;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
