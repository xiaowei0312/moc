package com.sram.controller.moc;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sram.service.TestpaperItemResultService;
import com.sram.service.TestpaperService;

/**
 * 前台考试
 * @author Administrator
 *
 */
@Controller("mocQuestionController")
@RequestMapping("/question")
public class QuestionController {
	
	@Resource(name="testpaperServiceImpl")
	private TestpaperService testpaperService;
	@Autowired
	private TestpaperItemResultService testpaperItemResultService;
	/**
	 * 真题试卷总数
	 */
	@RequestMapping(value = "/oldexamCount", method = RequestMethod.POST)
	@ResponseBody
	public Long count(Long outlineCategoryID,Long areaID) {
		return testpaperService.findOldexamCount(outlineCategoryID, areaID);
	}
	
	/**
	 * 真题试卷列表
	 */
	@RequestMapping(value = "/oldexamList", method = RequestMethod.POST)
	public String list(ModelMap model,Long outlineCategoryID,Long areaID,Integer page) {
		model.addAttribute("testpapers",testpaperService.findOldexamList(outlineCategoryID, areaID,page));
		model.addAttribute("outlineCategoryID",outlineCategoryID);
		return "/moc/question/oldexamList";
	}
	
	/**
	 * <p>功能:ajax提交试题</p> 
	 * @author 杨楷
	 * @date 2015年5月8日 上午9:17:18 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveQuestion", method = RequestMethod.POST)
	public @ResponseBody String  saveQuestion(HttpServletRequest request,String elapsedTime,Long testpaperResultId,Long testpaperItemId,String userAnswer) {
		String userJsonAnswer="";
		if (StringUtils.isNotBlank(userAnswer)) {
			String[] answers=userAnswer.split("\\|");
			List<String> list=new ArrayList<String>();
			for (String answer : answers) {
				list.add(answer);
			}
			userJsonAnswer=JSON.toJSONString(list);
		}
		if (testpaperItemResultService.updateResult(elapsedTime, testpaperResultId, testpaperItemId, userJsonAnswer)) {
			return "Y";
		}else {
			return "N";
		}
	}
}
