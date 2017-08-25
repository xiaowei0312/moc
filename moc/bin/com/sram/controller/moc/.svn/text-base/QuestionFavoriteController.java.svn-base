package com.sram.controller.moc;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sram.entity.Member;
import com.sram.entity.QuestionFavorite;
import com.sram.service.QuestionFavoriteService;
/**
 * Controller - 题目收藏
 * @author Sram Team
 * @version 1.0
 */
@Controller("questionFavoriteController")
@RequestMapping("/member/question")
public class QuestionFavoriteController extends BaseController{
	@Resource(name = "questionFavoriteServiceImpl")
	private QuestionFavoriteService questionFavoriteService;
	/**
	 * 能力评估报告
	 */
	@RequestMapping(value = "/showlist")
	public String showlist(ModelMap model,Long testpaperResultId){
		List<QuestionFavorite> questionFavorites = questionFavoriteService.findAll();
		model.addAttribute("questionFavorites", questionFavorites);
		return "/moc/report/myCollection";
	}
	
	@RequestMapping(value="/addOrRemoveFavorite")
	public @ResponseBody String addOrRemoveFavorite(ModelMap modelMap,Long questionId,String status){
		Member currentMember = super.currentMember();
		try {
			questionFavoriteService.addOrRemove(currentMember.getId(), questionId, status);
			return "{\"msg\":\"Y\"}";
		} catch (Exception e) {
			e.printStackTrace();
			return	"{\"msg\":\"N\"}";
		}

	}
}
