package com.sram.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.Constants.TestpaperType;
import com.sram.Message;
import com.sram.Message.Type;
import com.sram.Pageable;
import com.sram.Principal;
import com.sram.SramException;
import com.sram.entity.Admin;
import com.sram.entity.Area;
import com.sram.entity.GeneratorQuestionConfig;
import com.sram.entity.GeneratorStrategy;
import com.sram.entity.GeneratorStrategy.GeneratorType;
import com.sram.entity.Member;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Question;
import com.sram.entity.QuestionUploadAnalyseReport;
import com.sram.entity.Testpaper;
import com.sram.entity.Testpaper.Status;
import com.sram.entity.TestpaperResult;
import com.sram.service.AreaService;
import com.sram.service.GeneratorStrategyService;
import com.sram.service.OutlineCategoryService;
import com.sram.service.QuestionService;
import com.sram.service.TestpaperService;
import com.sram.util.JsonUtils;

/**
 * 试卷控制类
 */
@Controller("TestpaperController")
@RequestMapping("/admin/testpaper")
public class TestpaperController extends BaseController{

	@Resource(name = "testpaperServiceImpl")
	private TestpaperService testpaperService;
	@Autowired
	private AreaService areaService;
	@Resource(name="outlineCategoryServiceImpl")
	private OutlineCategoryService outlineCategoryService;
	@Autowired
	private QuestionService questionService;
	
	public static final String[] TITLE_XZT = { "大纲编号*,outlineCode","题干*,stem", "难度*,difficultyStr","选项*,metas", "是否定向*,isUncertain", "答案*,answer", "分值*,scoreStr","多选题漏选分值,missScoreStr","解析,analysis" };
	public static final String[] TITLE_PDT = { "大纲编号*,outlineCode","题干*,stem", "难度*,difficultyStr","答案*,answer","分值*,scoreStr","解析,analysis"};
	public static final String[] TITLE_TKT = { "大纲编号*,outlineCode","题干*,stem", "难度*,difficultyStr","分值*,scoreStr","解析,analysis"};
	public static final String[] TITLE_JDT = { "大纲编号*,outlineCode","题干*,stem", "难度*,difficultyStr","答案*,answer","分值*,scoreStr", "解析,analysis"};
	public static final String[] TITLE_CLT = { "大纲编号*,outlineCode","题型*,materialType","难度,difficultyStr", "题干*,stem", "选择题选项,metas", "答案,answer","分值*,scoreStr","多选题漏选分值,missScoreStr","解析,analysis"};
	
	/**
	 * 试卷列表
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap model,Pageable pageable,String beginDateStr,String endDateStr,String testpaperType,Long outlineCategoryId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date beginDate=null;
		Date endDate=null;
		try {
			if(beginDateStr!=null && !("").equals(beginDateStr)){
				beginDate = sdf.parse(beginDateStr);
			}
			if(endDateStr!=null && !("").equals(endDateStr)){
				endDate=sdf.parse(endDateStr);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("page",testpaperService.findPage(pageable,testpaperType,outlineCategoryId,beginDateStr,endDateStr));
		model.addAttribute("testpaperType",testpaperType);
		model.addAttribute("outlineCategoryTree", outlineCategoryService.findTree());
		model.addAttribute("outlineCategoryId",outlineCategoryId);
		model.addAttribute("beginDateStr",beginDate);
		model.addAttribute("endDateStr",endDate);
		return "/admin/test_paper/list";
	}
	
	/**
	 * 更改试卷状态
	 */
	@RequestMapping(value = "/updateTestPaperStatu", method = RequestMethod.POST)
	public @ResponseBody
	Message updateTestPaperStatu(Long testPaperId,Status status){
		try{
			//更改特定试卷的试卷状态
			testpaperService.updateStatusById(testPaperId,status);
		}catch(Exception e){
			e.printStackTrace();
			return Message.success("更改状态失败，请联系管理员");
		}
		return Message.success("更改试卷状态成功");
	}
	
	
	/**
	 * 添加试卷
	 * @param model
	 * @param testpaperType
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String addTestpaper(ModelMap model,String testpaperType) {
		String defineTime=switchIntToTime(new Integer(3600));
		model.addAttribute("defineTime", defineTime);
		if(testpaperType!=null){
			if(testpaperType.equals("intellexercise")){
				return "/admin/test_paper/add";
			}
			if(testpaperType.equals("specialexercis")){
				return "/admin/test_paper/add";
			}
			if(testpaperType.equals("genrationexam")){
				return "/admin/test_paper/add";
			}
			if(testpaperType.equals("oldexam")){
				return "/admin/test_paper/add";
			}
		}
		return "/admin/test_paper/add";
	}
	/**
	 * 编辑试卷
	 */
	@RequestMapping(value = "/editTestpaper", method = RequestMethod.GET)
	public String editTestpaper(Long id, ModelMap model) {
		Testpaper testpaper = testpaperService.find(id);
		if (!isValid(testpaper)) {
			return ERROR_VIEW;
		}
		//OutlineCategory parent = testpaper.getOutlineCategory();
		List<OutlineCategory> categories = outlineCategoryService.findTree();
		//categories.add(0, parent);
		Area area = testpaper.getArea();
		String defineTime="";
		if(testpaper.getLimitedTime()!=0){
			defineTime=switchIntToTime(testpaper.getLimitedTime());
		}else{
			defineTime=switchIntToTime(new Integer(3600));
		}
		List<Area> areas = areaService.findRoots();
		areas.add(0, area);
		model.addAttribute("testpaper", testpaper);
		model.addAttribute("areas", areas);
		model.addAttribute("outlineCategoryTree", categories);
		model.addAttribute("id",id);
		model.addAttribute("defineTime", defineTime);
		TestpaperType testpaperType=testpaper.getTestpaperType();
		if(testpaperType==TestpaperType.intellexercise){
			return "/admin/test_paper/edit";
		}
		if(testpaperType==TestpaperType.specialexercis){
			return "/admin/test_paper/edit";
		}
		if(testpaperType==TestpaperType.genrationexam){
			return "/admin/test_paper/edit";
		}
		if(testpaperType==TestpaperType.oldexam){
			return "/admin/test_paper/edit";
		}
		return "/admin/test_paper/edit";
	}
	/**
	 * 更新试卷
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request,Testpaper testpaper,Long areaId,Long outlineCategoryId,RedirectAttributes redirectAttributes
			,String defineTime) {
		if (!isValid(testpaper)) {
			return ERROR_VIEW;
		}
		OutlineCategory outlineCategory = new OutlineCategory();
		outlineCategory.setId(outlineCategoryId);
		testpaper.setOutlineCategory(outlineCategory);
		//modify limin 2015/04/21 start
		//前台传回来时分秒字符串 计算成秒
		testpaper.setLimitedTime(switchStrToInt(defineTime));
		//modify limin 2015/04/21 end
		if(areaId!=null){
			Area area = new Area();
			area.setId(areaId);
			testpaper.setArea(area);
		}

		testpaperService.update(testpaper);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}
	/**
	 * 保存试卷对象
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request,Testpaper testpaper,Long areaId,Long outlineCategoryId,RedirectAttributes redirectAttributes
			,String defineTime) {
		OutlineCategory outlineCategory = new OutlineCategory();
		outlineCategory.setId(outlineCategoryId);
		//modify limin 2015/04/21 start
		//前台传回来时分秒字符串 计算成秒
		testpaper.setLimitedTime(switchStrToInt(defineTime));
		//modify limin 2015/04/21 end
		testpaper.setOutlineCategory(outlineCategory);
		if(areaId!=null){
			Area area = new Area();
			area.setId(areaId);
			testpaper.setArea(area);
		}
		Admin currentAdmin = super.currentAdmin();
		testpaper.setCreateAdmin(currentAdmin);
		testpaperService.save(testpaper);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}
	/**
	 * 删除试卷
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		try {
			//判断要删除的题目是否子题
			Testpaper testpaper=null;
			for(Long id:ids){
				testpaper = testpaperService.find(id);
				TestpaperType testpaperType=testpaper.getTestpaperType();
				String paperType=testpaperType.name();
				if(testpaper==null){
					return ERROR_MESSAGE;
				}
				//oldexam(真题试卷),munualsimulation(模考试卷),other(其他试卷)
				if(("oldexam").equals(paperType) || ("munualsimulation").equals(paperType)
						|| ("other").equals(paperType)){
					Set<TestpaperResult> testpaperResults=testpaper.getTestpaperResults();
					if(testpaperResults!=null && testpaperResults.size()>0){
						return Message.error("该试卷被引用无法删除");
					}
				}else{
					return Message.error("该试卷被引用无法删除");
				}
			}
			testpaperService.delete(ids);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			//e.printStackTrace();
			return Message.error("该试卷被引用无法删除");
		}
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
	
	
	/**
	 * 导入试卷
	 * @param model
	 * @param testpaperType
	 * @return
	 */
	@RequestMapping(value = "/toAdd")
	public String toAdd(ModelMap model) {
		String defineTime=switchIntToTime(new Integer(3600));
		model.addAttribute("defineTime", defineTime);
		return "/admin/test_paper/import";
	}
	
	@RequestMapping(value = "/saveImport")
	public String saveImport(Testpaper testpaper,Long outlineCategoryId,MultipartFile file,HttpServletRequest request,RedirectAttributes redirectAttributes,Map<String, Object> map
			,String defineTime) {
		String parameter = request.getParameter("areaId");
		Area area = StringUtils.isNotEmpty(parameter) ? areaService.find(Long.valueOf(parameter)) : null;
		Map<String, Object> index;
		Map<String, Object> analyzeXLS;
		String[] sheetNames=null;
		String[] sheetTypes=null;
		//step1,检查试卷【章节】sheet的映射情况
		try {
			index = testpaperService.getIndex(file);
			QuestionUploadAnalyseReport report =(QuestionUploadAnalyseReport) index.get("report");
			if (report.isOk()) {
				sheetNames = (String[]) index.get("sheetNames");
				sheetTypes = (String[]) index.get("sheetTypes");
			}else {
				ArrayList<QuestionUploadAnalyseReport> list = new ArrayList<QuestionUploadAnalyseReport>();
				list.add(report);
				map.put("reportList",list);
				return "/admin/test_paper/report";
			}
		} catch (Exception e) {
			e.printStackTrace();
			addFlashMessage(redirectAttributes, Message.error("模板解析失败或没有上传文件，请检查模板是否符合规范"));
			return "redirect:toAdd.jhtml";
		}
		//step2,各章节中的题目解析
		Map<String, String[]> templateMap=new HashMap<String, String[]>();
		templateMap.put("选择题", TITLE_XZT);
		templateMap.put("填空题", TITLE_TKT);
		templateMap.put("判断题", TITLE_PDT);
		templateMap.put("简答题", TITLE_JDT);
		templateMap.put("材料题", TITLE_CLT);
		try {
		analyzeXLS = this.questionService.analyzeXLS(file, sheetNames, sheetTypes, templateMap);
		} catch (Exception e) {
			e.printStackTrace();
			addFlashMessage(redirectAttributes, Message.error("模板解析失败，请检查模板是否符合规范"));
			return "redirect:toAdd.jhtml";
		}
		boolean isAllPass=true;
		List<QuestionUploadAnalyseReport> reportList=(List<QuestionUploadAnalyseReport>) analyzeXLS.get("reportList");
		for (QuestionUploadAnalyseReport report : reportList) {
			if (!report.isOk()) {
				isAllPass=false;
				}
			if (report.getMsg().contains("表中数据为空")||report.getMsg().contains("表不存在")) {
				isAllPass=false;
			}
		}
		if(!isAllPass){
			for (QuestionUploadAnalyseReport report : reportList) {
				if (StringUtils.isBlank(report.getMsg())) {
					report.setMsg("校验通过，等待其他sheet校验通过后可插入\n");
				}
			}
			map.put("reportList",reportList);
			return "/admin/test_paper/report";
		}
		//step3.excel文件校验通过之后开始执行插入操作撒
		List<Question> questionList= (List<Question>) analyzeXLS.get("questionList");
	    List<List<Question>> materList =(List<List<Question>>) analyzeXLS.get("materList");
	    //modify limin 2015/04/21 start
		//前台传回来时分秒字符串 计算成秒
		testpaper.setLimitedTime(switchStrToInt(defineTime));
		//modify limin 2015/04/21 end
	    testpaper.setArea(area);
	    try {
	    	testpaperService.importTestPaper(testpaper, outlineCategoryService.find(outlineCategoryId), sheetNames, questionList, materList);
		} catch (SramException e) {
			addFlashMessage(redirectAttributes, new Message(Type.error,e.getMessage()));
			return "redirect:list.jhtml";
		}
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:list.jhtml";
	}
	
}
