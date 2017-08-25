package com.sram.controller.moc;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sram.Constants.TestpaperType;
import com.sram.Principal;
import com.sram.Setting;
import com.sram.entity.AccoutCatalog;
import com.sram.entity.IndustryCategory;
import com.sram.entity.Member;
import com.sram.entity.Question;
import com.sram.entity.Testpaper;
import com.sram.entity.TestpaperChapter;
import com.sram.entity.TestpaperItem;
import com.sram.entity.TestpaperResult;
import com.sram.entity.UploadFiles;
import com.sram.service.AccoutCatalogService;
import com.sram.service.IndustryCategoryService;
import com.sram.service.OutlineCategoryService;
import com.sram.service.QuestionFavoriteService;
import com.sram.service.QuestionService;
import com.sram.service.TestpaperItemResultService;
import com.sram.service.TestpaperResultService;
import com.sram.service.TestpaperService;
import com.sram.service.UploadFilesService;
import com.sram.util.HttpClientUtil;
import com.sram.util.SettingUtils;
/**
 * Controller - 前台题库,个人中心的我的题库
 * 
 * @author Sram Team
 * @version 1.0
 */
@Controller("reportController")
@RequestMapping("/member/question")
public class ReportController extends BaseController{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ReportController.class);

	@Resource(name = "testpaperResultServiceImpl")
	private TestpaperResultService testpaperResultService;
	@Resource(name = "testpaperItemResultServiceImpl")
	private TestpaperItemResultService testpaperItemResultService;
	@Resource(name = "questionFavoriteServiceImpl")
	private QuestionFavoriteService questionFavoriteService;
	@Resource(name="uploadFilesServiceImpl")
	private UploadFilesService uploadFilesService;
	@Resource(name = "questionServiceImpl")
	private QuestionService questionService;
	@Autowired
	private IndustryCategoryService industryCategoryService;
	@Autowired
	private OutlineCategoryService outlineCategoryService;
	@Autowired
	private TestpaperService testpaperService;
	@Autowired
	private AccoutCatalogService accoutCatalogService;
	
	
	/**
	 * 能力评估报告
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value = "/list")
	public String list(HttpServletRequest request,ModelMap modelMap,Long industryCategoryID,Long outlineCategoryID) throws UnsupportedEncodingException{
		// 获得当前登陆的用户Id
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		Long memberId = principal.getId();
		/**debug**/
		List<IndustryCategory> findAllCategorys = industryCategoryService.findAllCategorys();
		/**debug**/
		//加入取得根大纲
		if(outlineCategoryService.find(outlineCategoryID).getParent()!=null){
			String treePath = outlineCategoryService.find(outlineCategoryID).getTreePath();
			outlineCategoryID=Long.parseLong(treePath.substring(1, treePath.indexOf(",", 1)));
		}
		modelMap.put("currentOutlineCategoryID", outlineCategoryID);
		modelMap.put("currentIndustryCategoryID", industryCategoryID);
		modelMap.put("outlineCategorys", testpaperItemResultService.findTestpaperItemResults(outlineCategoryID, memberId));
		//加载折线图数据
		modelMap.put("chartJson",JSON.toJSONString(testpaperResultService.findScores(memberId, outlineCategoryID)));
		//加载答题量排名
		int ranking = testpaperResultService.findRanking(memberId, outlineCategoryID);
		//加载预测分排名
		int scoreRanking = testpaperResultService.findScoreRanking(memberId, outlineCategoryID);
		//加载所有碰过该大纲的人的数量
		int allMember = testpaperResultService.findMemberCountByOutlineCategory(outlineCategoryID);
		//全站平均答题量+全站平均预测分
		Double[] avgCount = testpaperResultService.findAvgCountByOutlineCategory(outlineCategoryID);
		modelMap.put("ranking",ranking+1);
		modelMap.put("scoreRanking",scoreRanking);
		modelMap.put("allMember",allMember);
		modelMap.put("avgCount",avgCount);
		modelMap.put("myAvgScore",testpaperResultService.findAvgScoreByUserIdAndRootOutlineCategory(memberId, outlineCategoryID));//我的预测分
		return "/moc/report/abilityReport";
	}
	/**
	 * 我的练习
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/myExercise")
	public String myExercise(ModelMap model,Long industryCategoryID,Long outlineCategoryID) throws UnsupportedEncodingException{
		Long exerciseCount = testpaperResultService.findTestpaperResultCount(outlineCategoryID, super.currentMember().getId());
		List<Object[]> outlineCategorys = questionFavoriteService.findQuestionFavorites(outlineCategoryID, super.currentMember().getId());
		List<Object[]> wrongOutlineCategorys = testpaperItemResultService.findWrongTestpaperItemResults(outlineCategoryID, super.currentMember().getId());
		model.addAttribute("exerciseCount",exerciseCount);
		model.addAttribute("outlineCategorys",outlineCategorys);
		model.addAttribute("wrongOutlineCategorys",wrongOutlineCategorys);
		model.addAttribute("outlineCategoryID",outlineCategoryID);
		model.addAttribute("industryCategoryID", industryCategoryID);
		return "/moc/report/myExercise";
	}
	/**
	 * 我的练习记录列表
	 * @param model
	 * @param testpaperResultId
	 * @return
	 */
	@RequestMapping(value = "/showExerciseList")
	public String showExerciselist(ModelMap model,Long outlineCategoryID,Integer page){
		List<TestpaperResult> testpaperResults = testpaperResultService.findTestpaperResult(outlineCategoryID, super.currentMember().getId(),page);
		model.addAttribute("testpaperResults",testpaperResults);
		return "/moc/report/exerciselist";
	}
	/**
	 * 查看我的收藏题目
	 * @param model
	 * @param testpaperResultId
	 * @return
	 */
	@RequestMapping(value = "/showCollectionlist")
	public String showCollectionList(ModelMap model,Long outlineCategoryId){
		List<Question> questionFavorites = questionFavoriteService.findFavoriteQuestions(outlineCategoryId, super.currentMember().getId());
		model.addAttribute("outlineCategory", outlineCategoryService.find(outlineCategoryId).getName());
		model.addAttribute("questionFavorites", questionFavorites);
		model.addAttribute("questionFavoriteIds", questionFavoriteService.findFavorite(outlineCategoryId, super.currentMember().getId()));
		return "/moc/report/myCollection";
	}
	/**
	 * 查看错题本题目
	 * @param model
	 * @param testpaperResultId
	 * @return
	 */
	@RequestMapping(value = "/showErrorList")
	public String showErrorList(ModelMap model,Long outlineCategoryId){
		List<Question> errorQuestions = questionService.findWrongQuestions(outlineCategoryId, super.currentMember().getId());
		model.addAttribute("outlineCategory", outlineCategoryService.find(outlineCategoryId));
		model.addAttribute("errorQuestions", errorQuestions);
		model.put("questionFavoriteIds", questionFavoriteService.findFavorite(outlineCategoryId, super.currentMember().getId()));
		return "/moc/report/showError";
	}
	/**
	 * 查看解析
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/seeAnalysis")
	public String seeAnalysis(ModelMap model,Long testpaperResultId) throws UnsupportedEncodingException{
		TestpaperResult testpaperResult= testpaperResultService.findTestpaperResultById(testpaperResultId);
		model.addAttribute("testpaperResult",testpaperResult);
		return "/moc/report/seeAnalysis";
	}
	/**
	 * 查看报告
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/seeExamDetail")
	public String seeExamDetail(ModelMap modelMap,Long testpaperResultId,Long outlineCategoryId) throws UnsupportedEncodingException{
		Member currentMember = super.currentMember();
		//查找结果
		TestpaperResult testpaperResult = testpaperResultService.findTestpaperResultById(testpaperResultId);
		Testpaper testpaper = testpaperService.findTestpaperResult(testpaperResultId);
		//如果是专项练习。则先查找该大纲的顶级大纲
		if (testpaper.getTestpaperType()==TestpaperType.specialexercis&&outlineCategoryService.find(outlineCategoryId).getParent()!=null) {
			String treePath = outlineCategoryService.find(outlineCategoryId).getTreePath();
			outlineCategoryId=Long.parseLong(treePath.substring(1, treePath.indexOf(",", 1)));
		}
		List<Object[]> outlineCategories = testpaperItemResultService.findTestpaperItemResults(outlineCategoryId,testpaperResultId, currentMember.getId());
		//考点大纲
		modelMap.put("outlineCategories", outlineCategories);
		modelMap.put("testpaper", testpaper);
		modelMap.put("submitTime", testpaperResult.getEndDate());
		modelMap.put("testpaperResultId", testpaperResultId);
		modelMap.put("outlineCategoryId", outlineCategoryId);
		return "/moc/question/questionReport";
	}
	
	/**
	 * 我的题库 总数
	 */
	@RequestMapping(value = "/myQuestionList", method = RequestMethod.GET)
	public String myQuestionList(HttpServletRequest request,ModelMap model,Integer page) {
		//获得当前登陆的用户Id
		Principal principal=(Principal) request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
		Long userId =principal.getId();
		model.addAttribute("headImage",principal.getHeadImage());
		model.addAttribute("exerciseCount",testpaperResultService.findTestpaperResultCountByUserId(userId));
		model.addAttribute("favoritesCount",questionFavoriteService.findQuestionFavoritesCountByUserId(userId));
		model.addAttribute("wrongCount",testpaperItemResultService.findWrongTestpaperItemResultsCountByUserId(userId));
		return "/moc/member/question/myQuestionList";
	}
	/**
	 * 我的题库--练习记录列表
	 */
	@RequestMapping(value = "/exerciseData", method = RequestMethod.POST)
	public String exerciseCount(ModelMap model,Integer page) {
		Member currentMember = super.currentMember();
		List<TestpaperResult> testpaperResults = testpaperResultService.findTestpaperResultByUserId(currentMember.getId(),page);
		model.addAttribute("testpaperResults",testpaperResults);
		return "/moc/member/question/exerciseList";
	}
	
	/**
	 * 继续答题
	 */
	@RequestMapping(value = "/continueAnswerExam")
	public String continueAnswerExam(HttpServletRequest request,Long testpaperResultId,ModelMap model){
		TestpaperResult testpaperResult=testpaperResultService.find(testpaperResultId);
		if(testpaperResult!=null){
			com.sram.Constants.Status status=testpaperResult.getStatus();
			if(status==com.sram.Constants.Status.doing || status==com.sram.Constants.Status.paused){
				Integer lastusedTime=testpaperResult.getUsedTime();
				if(lastusedTime==null){
					lastusedTime=0;
				}
				// 获得当前登陆的用户Id
				Principal principal = (Principal) request.getSession().getAttribute(
						Member.PRINCIPAL_ATTRIBUTE_NAME);
				Long memberId = principal.getId();
				Testpaper testpaper=testpaperService.findTestpaperResult(testpaperResultId);
				//继续练习时间，需减去上次已耗费时间
				Integer limitedTime=testpaper.getLimitedTime()-lastusedTime;
				TestpaperType testpaperType=testpaper.getTestpaperType();
				Long outlineCategoryId=testpaper.getOutlineCategory().getId();
				//如果是专项练习。则先查找该大纲的顶级大纲（因为策略是根据顶级大纲来配置的）
				if (testpaperType==TestpaperType.specialexercis&&outlineCategoryService.find(outlineCategoryId).getParent()!=null) {
					outlineCategoryId=outlineCategoryService.find(outlineCategoryId).getParent().getId();
				}
				List<AccoutCatalog> accoutCatalogs = accoutCatalogService
				.findTree();
				model.addAttribute("accoutCatalogs", accoutCatalogs);
				model.addAttribute("testpaper",testpaper);
				model.addAttribute("testpaperChapters",testpaper.getTestpaperChapters());
				model.addAttribute("testpaperResultId",testpaperResultId);
				model.addAttribute("outlineCategoryId",outlineCategoryId);
				model.addAttribute("lastusedTime",lastusedTime);
				model.addAttribute("limitedTime", limitedTime);
				model.put("questionFavoriteIds", questionFavoriteService.findFavorite(outlineCategoryId, memberId));
				return "/moc/member/question/continueAnswerExam";
			}else{
				return "/moc/question/unContinueAnswerExam";
			}
		}else{
			return "/moc/question/unContinueAnswerExam";
		}
	}
	
	/**
	 * 我的题库--收藏列表
	 */
	@RequestMapping(value = "/favoritesData", method = RequestMethod.POST)
	public String favoritesCount(ModelMap model,Integer page) {
		Member currentMember = super.currentMember();
		List<Object[]> outlineCategorys = questionFavoriteService.findQuestionFavoritesByUserId(currentMember.getId(),page);
		model.addAttribute("outlineCategorys",outlineCategorys);
		return "/moc/member/question/favoritesList";
	}
	/**
	 * 我的题库---错误列表
	 */
	@RequestMapping(value = "/wrongData", method = RequestMethod.POST)
	public String wrongCount(ModelMap model,Integer page) {
		Member currentMember = super.currentMember();
		List<Object[]> wrongOutlineCategorys = testpaperItemResultService.findWrongTestpaperItemResultsByUserId(currentMember.getId(),page);
		model.addAttribute("wrongOutlineCategorys",wrongOutlineCategorys);
		return "/moc/member/question/wrongList";
	}
	
	/**
	 * 异步获取token和uploadFilesId
	 */
	@RequestMapping(value="/getTokenFileId",method=RequestMethod.GET)
	public @ResponseBody 
	String getTokenFileId(String targetType,Long targetId,HttpServletRequest request){
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		//获取uploadFilesId
		List<UploadFiles> uploadFiles=uploadFilesService.findALLByTarget(targetId, targetType);
		
		if(uploadFiles.size()<1){
			//return "{\"msg\":\"没有关联的文件\"}";
			return "sss";
		}
		
		// 获取token权限
		Setting setting = SettingUtils.get();
		String token = HttpClientUtil.sendGet(setting.getCloudServerSite()
				+ "/token/retrieveToken.jhtml?userName="
				+ principal.getUsername());
		
		if("question".equals(targetType)){
			return "{\"token\":\""+token+"\",\"id\":\""+uploadFiles.get(0).getId()+"\"}";
		}
		return null;
	}
	
}
