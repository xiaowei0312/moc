package com.sram.controller.moc.member;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.Constants.TestpaperType;
import com.sram.Constants;
import com.sram.Principal;
import com.sram.controller.moc.BaseController;
import com.sram.entity.AccoutCatalog;
import com.sram.entity.GeneratorStrategy;
import com.sram.entity.Member;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Question;
import com.sram.entity.Testpaper;
import com.sram.entity.TestpaperItem;
import com.sram.entity.TestpaperItemResult;
import com.sram.entity.TestpaperResult;
import com.sram.service.AccoutCatalogService;
import com.sram.service.GeneratorStrategyService;
import com.sram.service.OutlineCategoryService;
import com.sram.service.QuestionFavoriteService;
import com.sram.service.QuestionService;
import com.sram.service.TestpaperChapterService;
import com.sram.service.TestpaperItemResultService;
import com.sram.service.TestpaperResultService;
import com.sram.service.TestpaperService;
import com.sram.util.DateUtils;
import com.sram.util.IntegralRuleHandleUtil;
import com.sram.util.JsonUtils;
/**
 * 前台考试
 * @author Administrator
 *
 */
@Controller("mocMemberQuestionController")
@RequestMapping("/member/question")
public class QuestionController extends BaseController{

	@Autowired 
	private QuestionService questionService;
	@Autowired 
	private QuestionFavoriteService questionFavoriteService;
	@Autowired
	private GeneratorStrategyService generatorStrategyService;
	@Autowired
	private OutlineCategoryService outlineCategoryService;
	@Resource(name="testpaperServiceImpl")
	private TestpaperService testpaperService;
	@Resource(name="testpaperChapterServiceImpl")
	private TestpaperChapterService testpaperChapterService;
	@Resource(name="testpaperResultServiceImpl")
	private TestpaperResultService testpaperResultService;
	@Resource(name="testpaperItemResultServiceImpl")
	private TestpaperItemResultService testpaperItemResultService;
	@Autowired
	private AccoutCatalogService accoutCatalogService;
	
	/**
	 * <p>功能:智能练习、考点专项练习、组卷模考</p> 
	 * @author 杨楷
	 * @date 2015-3-26 下午05:43:03 
	 * @param modelMap
	 * @param testpaperType
	 * @param outlineCategoryId
	 * @return
	 */
	@RequestMapping(value = "/exercise")
	public String exercise(HttpServletRequest request,ModelMap modelMap,TestpaperType testpaperType,Long outlineCategoryId,Long rootOutlineCategoryId){
		if (rootOutlineCategoryId==null) {
			rootOutlineCategoryId=outlineCategoryId;
		}
		GeneratorStrategy strategy;//策略
		Testpaper testpaper;//生成的试卷对象
		if (testpaperType==TestpaperType.intellexercise||testpaperType==TestpaperType.specialexercis) {
			strategy=generatorStrategyService.findByOutlineCategoryId(rootOutlineCategoryId, TestpaperType.intellexercise);
			if (strategy==null) {
				strategy=generatorStrategyService.findByOutlineCategoryId(null, TestpaperType.intellexercise);
			}
			testpaper = this.questionService.getQuestion(outlineCategoryId,strategy,testpaperType);
		}else {
			strategy=generatorStrategyService.findByOutlineCategoryId(rootOutlineCategoryId, TestpaperType.genrationexam);
			if (strategy==null) {
				strategy=generatorStrategyService.findByOutlineCategoryId(null, TestpaperType.genrationexam);
			}
			testpaper = testpaperService.getTestpaper(outlineCategoryId, strategy);
		}
		if (testpaper!=null) {
			// 获得当前登陆的用户Id
			Principal principal = (Principal) request.getSession().getAttribute(
					Member.PRINCIPAL_ATTRIBUTE_NAME);
			Long memberId = principal.getId();
			int resultCount = testpaperResultService.findTestpaperResultCount(memberId, outlineCategoryId, testpaperType);
			testpaper.setName(testpaper.getName()+memberId.toString()+"-"+resultCount);
			TestpaperResult testpaperResult = new TestpaperResult();
			testpaperResult.setUserId(memberId);//做卷人
			testpaperResult.setTestpaper(testpaper);//试卷ID
			testpaperResult.setPaperName(testpaper.getName());//试卷名字
			OutlineCategory currentOutlineCategory=new OutlineCategory();
			currentOutlineCategory.setId(outlineCategoryId);
			testpaperResult.setOutlineCategory(currentOutlineCategory);//当前大纲
			testpaperResult.setRootOutlineCategory(rootOutlineCategoryId);//顶级大纲
			Member member=new Member();
			member.setId(memberId);
			testpaper.setCreateMember(member);
			//存储试卷
			Map<String, Object> saveAutoGeneratedTestpaperMap = testpaperService.saveAutoGeneratedTestpaper(testpaper);
			Long testpaperId = (Long) saveAutoGeneratedTestpaperMap.get("testpaperId") ;
			Map<Long,Long> questionOutlineCategorysMap = (Map<Long, Long>) saveAutoGeneratedTestpaperMap.get("questionOutlineCategorysMap") ;
			Testpaper testpaper2 = testpaperService.find(testpaperId);
			//存储考卷
			testpaperResult.setTotalItemCount(testpaper2.getItemCount());//题目总数量
			testpaperResultService.save(testpaperResult);
			//存储空结果
			testpaperItemResultService.saveDefaultResults(testpaper2, memberId, testpaperResult,questionOutlineCategorysMap);
			//做卷时间
			testpaper2.setLimitedTime(strategy.getTimeLimit());
			List<AccoutCatalog> accoutCatalogs = accoutCatalogService
					.findTree();
			modelMap.addAttribute("accoutCatalogs", accoutCatalogs);
			modelMap.addAttribute("testpaper",testpaper2);
			modelMap.addAttribute("testpaperChapters",testpaper2.getTestpaperChapters());
			modelMap.addAttribute("testpaperResultId",testpaperResult.getId());
			modelMap.addAttribute("outlineCategoryId",outlineCategoryId);
			modelMap.put("questionFavoriteIds", questionFavoriteService.findFavorite(outlineCategoryId, memberId));
			return "/moc/question/oldexam";
		}else {
			return "/moc/question/nullTestpaper";
		}
	}
	
	
	/**
	 * 真题试卷练习
	 */
	@RequestMapping(value = "/generateoldexam", method = RequestMethod.GET)
	public String generateoldexam(HttpServletRequest request,ModelMap model,Long outlineCategoryId,Long testpaperID
			,RedirectAttributes redirectAttributes){
		Testpaper testpaper = testpaperService.find(testpaperID);
		// 获得当前登陆的用户Id
		Principal currentMember = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		TestpaperResult testpaperResult = new TestpaperResult();
		testpaperResult.setUserId(currentMember.getId());//做卷人
		testpaperResult.setTestpaper(testpaper);//试卷ID
		testpaperResult.setPaperName(testpaper.getName());//试卷名字
		testpaperResult.setTotalItemCount(testpaper.getItemCount());//题目总数量
		testpaperResult.setRootOutlineCategory(outlineCategoryId);//顶级大纲id
		OutlineCategory currentOutlineCategory=new OutlineCategory();
		currentOutlineCategory.setId(outlineCategoryId);
		testpaperResult.setOutlineCategory(currentOutlineCategory);//当前大纲
		
		//存储考卷
		testpaperResultService.save(testpaperResult);
		
		//存储空结果
		testpaperItemResultService.saveDefaultResults(testpaper, currentMember.getId(), testpaperResult,null);//TODO 缺一个questionOutlineCategorysMap
		return "redirect:oldexam.jhtml?outlineCategoryId="+outlineCategoryId+"&testpaperID="+testpaperID+"&testpaperResultId="+testpaperResult.getId();
	}
	
	
	/**
	 * 真题试卷练习
	 */
	@RequestMapping(value = "/oldexam", method = RequestMethod.GET)
	public String exam(HttpServletRequest request,ModelMap model,Long outlineCategoryId,Long testpaperID
			,Long testpaperResultId) {
		Testpaper testpaper = testpaperService.find(testpaperID);
		// 获得当前登陆的用户Id
		Principal currentMember = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		
		//做卷时间
		model.addAttribute("testpaper",testpaper);
		model.addAttribute("testpaperChapters",testpaperChapterService.findTestpaperChapterListByTestpaperId(testpaperID));
		model.addAttribute("testpaperResultId",testpaperResultId);
		model.addAttribute("outlineCategoryId",outlineCategoryId);
		model.put("questionFavoriteIds", questionFavoriteService.findFavorite(outlineCategoryId, currentMember.getId()));
		List<AccoutCatalog> accoutCatalogs = accoutCatalogService
				.findTree();
		model.addAttribute("accoutCatalogs", accoutCatalogs);
		return "/moc/question/oldexam";
	}
	
	
	
	/**
	 * <p>功能:保存做题结果</p> 
	 * @author 杨楷
	 * @date 2015年4月29日 下午5:48:25 
	 * @param testpaperId
	 * @param outlineCategoryId
	 * @param testpaperResultId
	 * @param usedTime
	 * @param testpaperType
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/saveOldexm", method = RequestMethod.POST)
	public String saveOldexm(HttpServletRequest request,HttpServletResponse response,
			Long testpaperId,Long outlineCategoryId,Long testpaperResultId,String usedTime,TestpaperType testpaperType,ModelMap modelMap
			,Integer lastusedTime) {
			if(lastusedTime!=null){
				usedTime=Integer.toString(Integer.parseInt(usedTime)+lastusedTime);
			}
			String submitTime=DateUtils.format(new java.util.Date(), "datetime","yyyy-MM-dd HH:mm");
			Enumeration parameterNames = request.getParameterNames();
			List<TestpaperItemResult> testpaperItemResults=new ArrayList<TestpaperItemResult>();
			Testpaper testpaper = new Testpaper();
			testpaper.setId(testpaperId);//试卷对象
			Member currentMember = super.currentMember();
			TestpaperResult testpaperResult =testpaperResultService.find(testpaperResultId);
			testpaperResult.setUsedTime(Integer.parseInt(usedTime));//用时
			while (parameterNames.hasMoreElements()) {
				Object element = parameterNames.nextElement();
				String testpaperItemStr = element.toString();
				if(testpaperItemStr.equals("token")||testpaperItemStr.equals("testpaperId")||testpaperItemStr.equals("testpaperResultId")||testpaperItemStr.equals("outlineCategoryId")||testpaperItemStr.equals("usedTime")||testpaperItemStr.equals("testpaperType")||testpaperItemStr.startsWith("entry_")
						|| testpaperItemStr.equals("lastusedTime")){
					continue;
				}
				String[] temp = testpaperItemStr.split("_");
				String itemId = temp[3];
				String questionId =temp[2];
				List<String> answers=new ArrayList<String>();
				for (String value : request.getParameterValues(element.toString())) {
					answers.add(value);
				}
				//结果明细
				TestpaperItemResult testpaperItemResult = new TestpaperItemResult();
				testpaperItemResult.setUserId(currentMember.getId());//做题人
				testpaperItemResult.setTestpaper(testpaper);
				testpaperItemResult.setTestpaperResult(testpaperResult);//考卷ID
				Question question = new Question();
				question.setId(Long.parseLong(questionId));
				testpaperItemResult.setQuestion(question);//题目ID
				
				TestpaperItem testpaperItem = new TestpaperItem();
				testpaperItem.setId(Long.parseLong(itemId));
				testpaperItemResult.setTestpaperItem(testpaperItem);//考卷条目
				testpaperItemResult.setAnswer(JsonUtils.toJson(answers));//答案
				testpaperItemResults.add(testpaperItemResult);
			}
			//阅卷并存储结果,算分update考卷结果。
			testpaperItemResultService.saveItemResults(testpaperItemResults,testpaperResult,testpaperType);
			Long rootOutlineCategoryId=outlineCategoryId;
			//如果是专项练习。则先查找该大纲的顶级大纲（因为策略是根据顶级大纲来配置的）
			if (testpaperType==TestpaperType.specialexercis&&outlineCategoryService.find(outlineCategoryId).getParent()!=null) {
				String treePath = outlineCategoryService.find(outlineCategoryId).getTreePath();
				rootOutlineCategoryId=Long.parseLong(treePath.substring(1, treePath.indexOf(",", 1)));
			}
			List<Object[]> outlineCategories = testpaperItemResultService.findTestpaperItemResults(rootOutlineCategoryId,testpaperResultId, currentMember.getId());
			//考点大纲
			modelMap.put("outlineCategories", outlineCategories);
			modelMap.put("testpaper", testpaperService.findTestpaperResult(testpaperResultId));//查找结果
			modelMap.put("submitTime", submitTime);
			modelMap.put("testpaperResultId", testpaperResultId);
			modelMap.put("outlineCategoryId", rootOutlineCategoryId);
			
			//处理积分任务
			IntegralRuleHandleUtil.dispatchTask(request, response,
					Constants.COURSE_EXERCISE_URL);
			
			return "/moc/question/questionReport";
	}
	
	/**
	 * 解析报告
	 */
	@RequestMapping(value = "/analysisCertificate", method = RequestMethod.GET)
	public String analysisCertificate(ModelMap modelMap,Long testpaperResultId) {
		TestpaperResult testpaperResult = testpaperResultService.findTestpaperResultById(testpaperResultId);
		modelMap.put("testpaperResultId", testpaperResultId);
		modelMap.put("testpaperResult", testpaperResult);
		modelMap.put("testpaper", testpaperService.findTestpaperResult(testpaperResultId));
		
		//2015.5.11----荣刚平
		modelMap.put("questionFavoriteIds", questionFavoriteService.findFavorite(testpaperResult==null?null:testpaperResult.getRootOutlineCategory(), super.currentMember().getId()));
		return "/moc/question/analysisCertificate";
	}
}
