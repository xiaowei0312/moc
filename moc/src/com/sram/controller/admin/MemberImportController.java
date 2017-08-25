package com.sram.controller.admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.FileInfo.FileType;
import com.sram.Message;
import com.sram.controller.moc.BaseController;
import com.sram.entity.QuestionUploadAnalyseReport;
import com.sram.service.FileService;
import com.sram.service.MemberRankService;
import com.sram.service.MemberService;
import com.sram.vo.QuestionUpload;


@Controller("adminMemberImportController")
@RequestMapping("/admin/memberImport")
public class MemberImportController extends BaseController{
   
	@Resource(name="memberServiceImpl")
	private MemberService memberService;
	@Resource(name = "fileServiceImpl")
	private FileService fileService;
	@Resource(name = "memberRankServiceImpl")
	private MemberRankService memberRankService;
	
	/*
	 * 导入会员页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model){
		return "/admin/member/importAdd";
	}
	
	/**
	 * 处理上传文件并解析
	 * 
	 * @param questionUpload
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(QuestionUpload questionUpload,HttpServletRequest request,
			RedirectAttributes redirectAttributes,ModelMap model) {
		//存放导入Excel用户日志信息
		List<QuestionUploadAnalyseReport> list=new ArrayList<QuestionUploadAnalyseReport>();
		//获得用户注册ip
		String registPath=request.getRemoteAddr();
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
			QuestionUploadAnalyseReport analyzeXLS =memberService.analyzeXLS(file,registPath,memberRankService.find((long) 1));
			list.add(analyzeXLS);
		}
		model.addAttribute("reports", list);
		addFlashMessage(redirectAttributes,
				Message.error("导入excel日志已生成请查看"));
		return "/admin/member/importReport";
	}
	
}
