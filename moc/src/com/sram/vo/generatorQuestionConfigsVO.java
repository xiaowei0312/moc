package com.sram.vo;

import java.util.ArrayList;
import java.util.List;

import com.sram.entity.GeneratorQuestionConfig;
import com.sram.entity.GeneratorStrategy.GeneratorType;

/** 
 * <p>功能:试卷生成策略值对象</p> 
 * @author 杨楷
 * @date 2015-3-18 下午01:49:38
 */
public class generatorQuestionConfigsVO {
	public List<GeneratorQuestionConfig> configs=new ArrayList<GeneratorQuestionConfig>();
	public  long idVO;

	public List<GeneratorQuestionConfig> getConfigs() {
		return configs;
	}

	public void setConfigs(List<GeneratorQuestionConfig> configs) {
		this.configs = configs;
	}
	private GeneratorType generatorTypeVO;
	private int timeVO;

	public GeneratorType getGeneratorTypeVO() {
		return generatorTypeVO;
	}

	public void setGeneratorTypeVO(GeneratorType generatorTypeVO) {
		this.generatorTypeVO = generatorTypeVO;
	}

	public int getTimeVO() {
		return timeVO;
	}

	public void setTimeVO(int timeVO) {
		this.timeVO = timeVO;
	}
	private  String percentagesSimpleVO;
	private  String percentagesNormalVO;
	private  String percentagesDifficultyVO;

	public String getPercentagesSimpleVO() {
		return percentagesSimpleVO;
	}

	public void setPercentagesSimpleVO(String percentagesSimpleVO) {
		this.percentagesSimpleVO = percentagesSimpleVO;
	}

	public String getPercentagesNormalVO() {
		return percentagesNormalVO;
	}

	public void setPercentagesNormalVO(String percentagesNormalVO) {
		this.percentagesNormalVO = percentagesNormalVO;
	}

	public String getPercentagesDifficultyVO() {
		return percentagesDifficultyVO;
	}

	public void setPercentagesDifficultyVO(String percentagesDifficultyVO) {
		this.percentagesDifficultyVO = percentagesDifficultyVO;
	}

	public long getIdVO() {
		return idVO;
	}

	public void setIdVO(long idVO) {
		this.idVO = idVO;
	}

	
}
