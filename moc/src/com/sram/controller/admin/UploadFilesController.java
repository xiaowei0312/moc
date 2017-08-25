package com.sram.controller.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sram.Message;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.Principal;
import com.sram.Setting;
import com.sram.entity.Admin;
import com.sram.entity.Member;
import com.sram.entity.UploadFiles;
import com.sram.service.CourseService;
import com.sram.service.UploadFilesService;
import com.sram.util.HttpClientUtil;
import com.sram.util.JsonUtils;
import com.sram.util.SettingUtils;

/**
 * Controller - 文件
 * 
 * @author Sram Team
 * @version 1.0
 */
@Controller("adminUploadFilesController")
@RequestMapping("/admin/uploadFile")
public class UploadFilesController extends BaseController {
	@Resource(name = "uploadFilesServiceImpl")
	private UploadFilesService uploadFilesService;
	@Resource(name = "courseServiceImpl")
	private CourseService courseService;
	
	/**
	 * 文件管理列表
	 */
	@RequestMapping(value = "/courseFileList", method = RequestMethod.GET)
	public String courseFileList(ModelMap model, Long courseId,
			String targetType, Pageable pageable) {
		model.addAttribute("courseId", courseId);
		model.addAttribute("courseTitle", courseId == null ? "all"
				: courseService.findCourseTitle(courseId));
		targetType = targetType == null ? "courselesson" : targetType;
		Page<UploadFiles> page = uploadFilesService.findPage(courseId,
				pageable, targetType);
		model.addAttribute("page", page);
		model.addAttribute("targetId", courseId);
		model.addAttribute("targetType", targetType);
		model.addAttribute("unitConversion",
				useStaticPacker("com.sram.util.UnitConversionUtil"));

		return "/admin/course_file/list";
	}

	/**
	 * 添加文件列表
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model, Long targetId, String targetType) {
		model.addAttribute("targetId", targetId);
		model.addAttribute("targetType", targetType);
		Admin admin = currentAdmin();
		model.addAttribute("userId", admin.getId());
		return "/admin/course_file/add";
	}

	/**
	 * 添加视频解析文件列表
	 */
	@RequestMapping(value = "/addQuestion", method = RequestMethod.GET)
	public String addQuestionFile(ModelMap model, Long targetId,
			String targetType) {
		model.addAttribute("targetId", targetId);
		model.addAttribute("targetType", targetType);
		Admin admin = currentAdmin();
		model.addAttribute("userId", admin.getId());
		return "/admin/question_file/add";
	}

	/**
	 * 视频解析文件列表
	 */
	@RequestMapping(value = "/questionFileList", method = RequestMethod.GET)
	public String questionFileList(ModelMap model, Long targetId,
			String targetType) {
		List<UploadFiles> fileList = uploadFilesService.findALLByTarget(
				targetId, targetType);

		model.addAttribute("fileList", fileList);
		model.addAttribute("targetId", targetId);
		model.addAttribute("targetType", targetType);
		model.addAttribute("unitConversion",
				useStaticPacker("com.sram.util.UnitConversionUtil"));

		return "/admin/question_file/list";
	}

	/**
	 * 删除课时文件
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		
		
		String filePath;
		UploadFiles uploadFiles;
		Map<String, String> paramMap = new HashMap<String, String>();
		Setting setting = SettingUtils.get();
		try {
			for(Long id:ids){
				List<String> filePaths = new ArrayList<String>();
				uploadFiles=uploadFilesService.find(id);
				
				uploadFilesService.delete(uploadFiles);
				
				if(!"flv".equals(uploadFiles.getExt())){
					//进行了视频转换
					filePath=File.separator+uploadFiles.getTargetType()
							+File.separator+uploadFiles.getTargetId()
							+File.separator+uploadFiles.getConvertHash()
							+".flv";
					filePaths.add(filePath);
				}
				
				//删除视频源文件及图片
				filePath = File.separator+uploadFiles.getTargetType()
						+File.separator+uploadFiles.getTargetId()
						+File.separator+uploadFiles.getHashId()
						+".";
				filePaths.add(filePath+"jpg");
				filePaths.add(filePath+uploadFiles.getExt());
				
				
				//进行删除
				paramMap.put("filePaths",JsonUtils.toJson(filePaths));
				String result = HttpClientUtil.sendPost(setting.getCloudServerSite()
						+ "/file/delete.jhtml",paramMap);
				//没有删除成功
				if(!SUCCESS_MESSAGE.getType().toString().equals(result)){
					return ERROR_MESSAGE;
				}
				
			}
			return SUCCESS_MESSAGE;
		} catch (Exception e) {
			return Message.error("部分文件与被关联不能删除");
		}

	}
}
