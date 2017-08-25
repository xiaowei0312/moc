package com.sram.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.Message;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.Constants.TestpaperType;
import com.sram.entity.GeneratorQuestionConfig;
import com.sram.entity.GeneratorStrategy;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Testpaper;
import com.sram.entity.GeneratorStrategy.GeneratorType;
import com.sram.service.GeneratorStrategyService;
import com.sram.service.OutlineCategoryService;
import com.sram.util.JsonUtils;
import com.sram.vo.generatorQuestionConfigsVO;

/** 
 * <p>功能:</p> 
 * @author 杨楷
 * @date 2015-3-20 下午09:36:22
 */
@Controller("GeneratorStrategyController")
@RequestMapping("/admin/generatorStrategy")
public class GeneratorStrategyController extends BaseController {
	
	@Autowired
	private GeneratorStrategyService generatorStrategyService;
	@Autowired
	private OutlineCategoryService outlineCategoryService;
	
	@RequestMapping(value = "/list")
	public String list(ModelMap model,Pageable pageable){
		List<GeneratorStrategy> generatorStrategys = generatorStrategyService.findByOutlineCategoryId(-1l);
		//modify limin 2015/04/21 start
		Page<GeneratorStrategy> page=generatorStrategyService.findPage(pageable,null);
		//modify limin 2015/04/21 end
		List<OutlineCategory> rootsExcludeGenerator = outlineCategoryService.findRootsExcludeGenerator();
		boolean flag=(generatorStrategys!=null&&generatorStrategys.size()!=0)?true:false;
		boolean flag2=(rootsExcludeGenerator!=null&&rootsExcludeGenerator.size()!=0)?true:false;
		//modify limin 2015/04/21 start
		model.addAttribute("page",page);
		//modify limin 2015/04/21 start
		//默认配置按钮是否被激活
		model.addAttribute("flag",flag);
		//增加大纲策略按钮是否被激活(true 激活 false 禁用)
		model.addAttribute("flag2",flag2);
		return "/admin/generator_strategy/list";
	}
	
	@RequestMapping(value = "/addStrategy")
	public String addStrategy(String strategyType,ModelMap model){
		//modify limin 2015/04/20 start
		String timeLimit="00:00:00";
		String timeVO="00:00:00";
		List<OutlineCategory> roots = outlineCategoryService.findRootsExcludeGenerator();
		model.addAttribute("roots",roots);
		model.addAttribute("timeLimitStr",timeLimit);
		model.addAttribute("timeVOStr",timeVO);

		//modify limin 2015/04/20 end
		model.addAttribute("strategyType",strategyType);
		return "/admin/generator_strategy/addStrategy";
	}
	
	
	@RequestMapping(value = "/saveStrategy")
	public String saveStrategy(generatorQuestionConfigsVO configsVO,GeneratorStrategy generatorStrategy,RedirectAttributes redirectAttributes,Long outlineCategoryId
			,String timeLimitStr,String timeVOStr){
		OutlineCategory outlineCategory=new OutlineCategory();
		if (outlineCategoryId!=null) {
			outlineCategory.setId(outlineCategoryId);
			generatorStrategy.setOutlineCategory(outlineCategory);
		}
		//智能出题，专项练习
		generatorStrategy.setTestpaperType(TestpaperType.intellexercise);
		if (generatorStrategy.getGeneratorType()==GeneratorType.DIFFICULTY) {
			Map<String, String> map= new HashMap<String, String>();
			map.put("easy", generatorStrategy.getPercentagesSimple());
			map.put("normal", generatorStrategy.getPercentagesNormal());
			map.put("hard", generatorStrategy.getPercentagesDifficulty());
			generatorStrategy.setDifficulty(JsonUtils.toJson(map));
		}
		List<GeneratorQuestionConfig> list=new ArrayList<GeneratorQuestionConfig>();
		GeneratorQuestionConfig config=new GeneratorQuestionConfig();
		config.setCount(Integer.valueOf(generatorStrategy.getQuestionCount()));
		list.add(config);
		generatorStrategy.setTimeLimit(switchStrToInt(timeLimitStr));
		generatorStrategyService.save(generatorStrategy, list);
		//组卷模考
		GeneratorStrategy generatorStrategy2=new GeneratorStrategy();
		generatorStrategy2.setTestpaperType(TestpaperType.genrationexam);
		generatorStrategy2.setTimeLimit(switchStrToInt(timeVOStr));
		generatorStrategy2.setGeneratorType(configsVO.getGeneratorTypeVO());
		if(generatorStrategy2.getGeneratorType()==GeneratorType.DIFFICULTY){
	        Map<String, String> map= new HashMap<String, String>();
	        map.put("easy", configsVO.getPercentagesSimpleVO());
	        map.put("normal", configsVO.getPercentagesNormalVO());
	        map.put("hard", configsVO.getPercentagesDifficultyVO());
	        generatorStrategy2.setDifficulty(JsonUtils.toJson(map));
		}
		if (outlineCategoryId!=null) {
			generatorStrategy2.setOutlineCategory(outlineCategory);
		}
		generatorStrategyService.save(generatorStrategy2, configsVO.getConfigs());
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}
	
	@RequestMapping(value = "/editStrategy")
	public String editStrategy(ModelMap model,long outlineCategoryId){
		List<GeneratorStrategy> generatorStrategys = generatorStrategyService.findByOutlineCategoryId(outlineCategoryId);
		GeneratorStrategy exercise=null;
		GeneratorStrategy exam=null;
		if (generatorStrategys.get(0).getTestpaperType()==TestpaperType.intellexercise) {
			exercise=generatorStrategys.get(0);
			if (exercise.getGeneratorType()==GeneratorType.DIFFICULTY) {
				HashMap jsMap = JsonUtils.toObject(generatorStrategys.get(0).getDifficulty(), HashMap.class);
				exercise.setPercentagesSimple(((String)jsMap.get("easy")).replaceAll("%", ""));
				exercise.setPercentagesNormal(((String)jsMap.get("normal")).replaceAll("%", ""));
				exercise.setPercentagesDifficulty(((String)jsMap.get("hard")).replaceAll("%", ""));
			}
			exam=generatorStrategys.get(1);
			if (exam.getGeneratorType()==GeneratorType.DIFFICULTY) {
				HashMap jsMap = JsonUtils.toObject(generatorStrategys.get(1).getDifficulty(), HashMap.class);
				exam.setPercentagesSimple(((String)jsMap.get("easy")).replaceAll("%", ""));
				exam.setPercentagesNormal(((String)jsMap.get("normal")).replaceAll("%", ""));
				exam.setPercentagesDifficulty(((String)jsMap.get("hard")).replaceAll("%", ""));
			}
		}else {
			exercise=generatorStrategys.get(1);
			if (exercise.getGeneratorType()==GeneratorType.DIFFICULTY) {
				HashMap jsMap = JsonUtils.toObject(generatorStrategys.get(1).getDifficulty(), HashMap.class);
				exercise.setPercentagesSimple(((String)jsMap.get("easy")).replaceAll("%", ""));
				exercise.setPercentagesNormal(((String)jsMap.get("normal")).replaceAll("%", ""));
				exercise.setPercentagesDifficulty(((String)jsMap.get("hard")).replaceAll("%", ""));
			}
			exam=generatorStrategys.get(0);
			if (exam.getGeneratorType()==GeneratorType.DIFFICULTY) {
				HashMap jsMap = JsonUtils.toObject(generatorStrategys.get(0).getDifficulty(), HashMap.class);
				exam.setPercentagesSimple(((String)jsMap.get("easy")).replaceAll("%", ""));
				exam.setPercentagesNormal(((String)jsMap.get("normal")).replaceAll("%", ""));
				exam.setPercentagesDifficulty(((String)jsMap.get("hard")).replaceAll("%", ""));
			}
		}
		String timeLimitStr=switchIntToTime(exercise.getTimeLimit());
		String timeVOStr=switchIntToTime(exam.getTimeLimit());
		model.addAttribute("exercise",exercise);
		model.addAttribute("exam",exam);
		model.addAttribute("timeLimitStr",timeLimitStr);
		model.addAttribute("timeVOStr", timeVOStr);
		return "/admin/generator_strategy/editStrategy";
	}
	
	@RequestMapping(value = "/updateStrategy")
	public String updateStrategy(generatorQuestionConfigsVO configsVO,GeneratorStrategy generatorStrategy,RedirectAttributes redirectAttributes
			,String timeLimitStr,String timeVOStr){
		//智能出题，专项练习
		generatorStrategy.setTestpaperType(TestpaperType.intellexercise);
		if (generatorStrategy.getGeneratorType()==GeneratorType.DIFFICULTY) {
			Map<String, String> map= new HashMap<String, String>();
			map.put("easy", generatorStrategy.getPercentagesSimple());
			map.put("normal", generatorStrategy.getPercentagesNormal());
			map.put("hard", generatorStrategy.getPercentagesDifficulty());
			generatorStrategy.setDifficulty(JsonUtils.toJson(map));
		}
		List<GeneratorQuestionConfig> list=new ArrayList<GeneratorQuestionConfig>();
		GeneratorQuestionConfig config=new GeneratorQuestionConfig();
		config.setCount(Integer.valueOf(generatorStrategy.getQuestionCount()));
		list.add(config);
		//modify 2015/04/24 limin start
		generatorStrategy.setTimeLimit(switchStrToInt(timeLimitStr));
		//modify 2015/04/24 limin end
		generatorStrategyService.updateCascade(generatorStrategy, list);
		//组卷模考
		GeneratorStrategy generatorStrategy2=new GeneratorStrategy();
		generatorStrategy2.setId(configsVO.getIdVO());
		generatorStrategy2.setTestpaperType(TestpaperType.genrationexam);
		//modify 2015/04/24 limin start
		generatorStrategy2.setTimeLimit(switchStrToInt(timeVOStr));
		//modify 2015/04/24 limin end
		generatorStrategy2.setGeneratorType(configsVO.getGeneratorTypeVO());
		if(generatorStrategy2.getGeneratorType()==GeneratorType.DIFFICULTY){
	        Map<String, String> map= new HashMap<String, String>();
	        map.put("easy", configsVO.getPercentagesSimpleVO());
	        map.put("normal", configsVO.getPercentagesNormalVO());
	        map.put("hard", configsVO.getPercentagesDifficultyVO());
	        generatorStrategy2.setDifficulty(JsonUtils.toJson(map));
		}
		generatorStrategyService.updateCascade(generatorStrategy2, configsVO.getConfigs());
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Message delete(Long[] ids) {
		Set<Long> set=new HashSet<Long>();
		for (Long outlineId : ids) {
			set.add(outlineId);
		}
		Long[] outlineCategoryIds=new Long[set.size()];
		int i=0;
		for (Long id : set) {
			outlineCategoryIds[i]=id;
		}
		List<Long> generatorStrategyIds=new ArrayList<Long>();
		for (Long outlineCategoryId : outlineCategoryIds) {
			List<GeneratorStrategy> generatorStrategysTemp = generatorStrategyService.findByOutlineCategoryId(outlineCategoryId);
			for (GeneratorStrategy generatorStrategy : generatorStrategysTemp) {
				generatorStrategyIds.add(generatorStrategy.getId());
			}
		}
		Long[] array = new Long[generatorStrategyIds.size()];
		for (int j = 0; j < generatorStrategyIds.size(); j++) {
			array[j]=generatorStrategyIds.get(j);
		}
		generatorStrategyService.delete(array);
		return SUCCESS_MESSAGE;
	}
	
	
	/**
	 * 计算时间秒
	 */
	public Integer switchStrToInt(String defineTime){
		int sum=0;
		if(defineTime!=null && !("").equals(defineTime)){
			String[] str=defineTime.split(":");
			for(int i=0;i<str.length;i++){
				if(i==0){
					sum+=Integer.parseInt(str[i])*60*60;
				}else if(i==1){
					sum+=Integer.parseInt(str[i])*60;
				}else{
					sum+=Integer.parseInt(str[i]);
				}
			}
			return new Integer(sum);
		}else{
			return new Integer(0);
		}
	}
	
	/**
	 * 秒转日期字符串
	 */
	public String switchIntToTime(Integer limitedTime){
		String defineTime="";
		Integer temp=0;
		if(limitedTime!=null && limitedTime!=0){
			for(int i=0;i<3;i++){
				if(i==0){
				   temp=limitedTime/(60*60);
				   limitedTime=limitedTime%(60*60);
				}else if(i==1){
				   temp=limitedTime/(60);
				   limitedTime=limitedTime%(60);
				}else{
				  temp=limitedTime;
				}
				defineTime+=switchTempToStr(temp);
			}
			return defineTime.substring(0, defineTime.length()-1);
		}else{
			return "00:00:00";
		}
	}
	
	
	/**
	 * 临时整型转字符串
	 */
	public String switchTempToStr(Integer temp){
		String defineTime="";
		if(temp<10){
			defineTime+="0"+temp.toString();
		}else{
			defineTime+=temp.toString();
		}
		return defineTime.trim()+":";
	}
	
}
