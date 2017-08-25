package com.sram.controller.admin;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.FileInfo.FileType;
import com.sram.Message;
import com.sram.SramException;
import com.sram.controller.moc.BaseController;
import com.sram.entity.Question;
import com.sram.entity.QuestionUploadAnalyseReport;
import com.sram.service.FileService;
import com.sram.service.QuestionService;
import com.sram.vo.QuestionUpload;
import com.sun.corba.se.spi.orb.StringPair;

/**
 * 试题导入类
 * 
 * @author kklives
 * 
 */
@Controller("adminQuestionImportController")
@RequestMapping("/admin/questionImport")
public class QuestionImportController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(QuestionImportController.class);

	@Resource(name = "fileServiceImpl")
	private FileService fileService;
	@Resource(name = "questionServiceImpl")
	private QuestionService questionService;
	
	// sheet名称
	private static final String[] SHEET_NAMES = { "选择题", "判断题", "填空题", "简答题","材料题" };
	// excel头部
	public static final String[] TITLE_XZT = { "大纲编号*,outlineCode","题干*,stem", "难度*,difficultyStr","选项*,metas", "是否定向*,isUncertain", "答案*,answer","分值*,scoreStr","多选题漏选分值,missScoreStr", "解析,analysis" };
	public static final String[] TITLE_PDT = { "大纲编号*,outlineCode","题干*,stem", "难度*,difficultyStr","答案*,answer","分值*,scoreStr", "解析,analysis" };
	public static final String[] TITLE_TKT = { "大纲编号*,outlineCode","题干*,stem", "难度*,difficultyStr","分值*,scoreStr","解析,analysis" };
	public static final String[] TITLE_JDT = { "大纲编号*,outlineCode","题干*,stem", "难度*,difficultyStr","答案*,answer","分值*,scoreStr","解析,analysis" };
	public static final String[] TITLE_CLT = { "大纲编号*,outlineCode","题型*,materialType","难度,difficultyStr", "题干*,stem", "选择题选项,metas", "答案,answer","分值,scoreStr","多选题漏选分值,missScoreStr","解析,analysis" };
	
	/**
	 * 添加页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Map<String, Object> map) {
		return "/admin/question/importQuestion";
	}

	/**
	 * 处理上传文件并解析
	 * 
	 * @param questionUpload
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(QuestionUpload questionUpload,
			RedirectAttributes redirectAttributes,Map<String, Object> map) {
		List<List<QuestionUploadAnalyseReport>> list=new ArrayList<List<QuestionUploadAnalyseReport>>();
		// 文件格式校验
		for (Iterator<MultipartFile> iterator = questionUpload.getFile()
				.iterator(); iterator.hasNext();) {
			MultipartFile file = iterator.next();
			if (file == null || file.isEmpty()) {
				iterator.remove();
				continue;
			}
			if (file != null && !file.isEmpty()) {
				if (!fileService.isValid(FileType.file, file)) {
					addFlashMessage(redirectAttributes,
							Message.error("admin.upload.invalid"));
					return "redirect:add.jhtml";
				}
			}
			Map<String, String[]> templateMap=new HashMap<String, String[]>();
			templateMap.put("选择题", TITLE_XZT);
			templateMap.put("填空题", TITLE_TKT);
			templateMap.put("判断题", TITLE_PDT);
			templateMap.put("简答题", TITLE_JDT);
			templateMap.put("材料题", TITLE_CLT);
		    Map<String, Object> analyzeXLS=null;
			try {
				try {
				analyzeXLS = this.questionService.analyzeXLS(file,SHEET_NAMES,null,templateMap);
				}catch(SramException e){
					addFlashMessage(redirectAttributes, Message.error(e.getMessage()));
					return "redirect:add.jhtml";
				}
			} catch (Exception e) {
				e.printStackTrace();
				addFlashMessage(redirectAttributes, Message.error("模板解析失败,可能是模板出错,请联系管理员"));
				return "redirect:add.jhtml";
			}
		    List<QuestionUploadAnalyseReport> reports=(List<QuestionUploadAnalyseReport>) analyzeXLS.get("reportList");
		    if (reports.get(0).isOk()&&reports.get(1).isOk()&&reports.get(2).isOk()&&reports.get(3).isOk()&&reports.get(4).isOk()) {
		    	List<Question> questionList= (List<Question>) analyzeXLS.get("questionList");
		    	List<List<Question>> materList =(List<List<Question>>) analyzeXLS.get("materList");
		    	boolean isSuccess=questionService.saveXLS(questionList, materList);
		    	for (QuestionUploadAnalyseReport report : reports) {
					if (isSuccess) {
						if (StringUtils.isBlank(report.getMsg())) {
							report.setMsg("保存成功");
						}
					}else {
						if (StringUtils.isBlank(report.getMsg())) {
							report.setMsg("保存失败");
						}
					}
				}
			}else {
				for (QuestionUploadAnalyseReport report : reports) {
					if (StringUtils.isBlank(report.getMsg())) {
						report.setMsg("校验通过，等待其他sheet校验通过后可插入\n");
					}
				}
			}
			list.add(reports);
		}
		map.put("report", list);
		return "/admin/question/report";
	}
	
}
