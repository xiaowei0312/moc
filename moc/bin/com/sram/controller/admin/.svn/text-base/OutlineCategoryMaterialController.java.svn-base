package com.sram.controller.admin;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sram.Message;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.Admin;
import com.sram.entity.Course;
import com.sram.entity.CourseCategory;
import com.sram.entity.OutlineCategory;
import com.sram.entity.OutlineCategoryMaterial;
import com.sram.entity.UploadFiles;
import com.sram.entity.UploadFiles.TARGET_TYPE;
import com.sram.service.CourseCategoryService;
import com.sram.service.CourseService;
import com.sram.service.OutlineCategoryMaterialService;
import com.sram.service.UploadFilesService;

/**
 * 大纲关联资料
 * @author Administrator
 *
 */
@Controller("OutlineCategoryMaterialController")
@RequestMapping("/admin/outline_category_material")
public class OutlineCategoryMaterialController extends BaseController {
	
	@Resource(name = "uploadFilesServiceImpl")
	private UploadFilesService uploadFilesService;
	@Resource(name = "outlineCategoryMaterialServiceImpl")
	private OutlineCategoryMaterialService outlineCategoryMaterialService;
	@Resource(name="courseCategoryServiceImpl")
	private CourseCategoryService courseCategoryService;
	@Resource(name="courseServiceImpl")
	private CourseService courseService;
	/**
	 * 资料文件管理列表
	 */
	@RequestMapping(value = "/fileList", method = RequestMethod.GET)
	public String courseFileList(ModelMap model,Long outlineCategoryId,Pageable pageable) {
		List<OutlineCategoryMaterial> OutlineCategoryMaterials = outlineCategoryMaterialService.findList(outlineCategoryId);
		model.addAttribute("OutlineCategoryMaterials", OutlineCategoryMaterials);
		model.addAttribute("outlineCategoryId", outlineCategoryId);
		model.addAttribute("unitConversion",
				useStaticPacker("com.sram.util.UnitConversionUtil"));
		return "/admin/outline_category_file/fileList";
	}
	
	/**
	 * 添加视频文件列表
	 */
	@RequestMapping(value = "/addFile", method = RequestMethod.GET)
	public String add(ModelMap model, Long targetId, String targetType) {
		model.addAttribute("targetId", targetId);
		model.addAttribute("targetType", targetType);
		Admin admin = currentAdmin();
		model.addAttribute("userId", admin.getId());
		return "/admin/outline_category_file/addFile";
	}

	/**
	 * 删除视频文件
	 */
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	public @ResponseBody
	Message deleteFile(Long[] ids) {
		try {
			outlineCategoryMaterialService.delete(ids);
			return SUCCESS_MESSAGE;
		} catch (Exception e) {
			return Message.error("部分文件与大纲关联不能删除");
		}
	}
	
	/**
	 * 保存视频文件
	 * ids---uploadfile
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(Long[] ids,OutlineCategory outlineCategory){
		List<OutlineCategoryMaterial> outlineCategoryMaterials=new ArrayList<OutlineCategoryMaterial>();
		
		UploadFiles uploadFiles;
		
		for(Long id:ids){
			OutlineCategoryMaterial outlineCategoryMaterial=new OutlineCategoryMaterial();
			uploadFiles=uploadFilesService.find(id);
			if(uploadFiles==null){
				continue;
			}
			
			//资料文件
			outlineCategoryMaterial.setUploadFiles(uploadFiles);
			
			//资料所属大纲
			outlineCategoryMaterial.setOutlineCategory(outlineCategory);
			
			//资料标题---uploafiles的文件名
			outlineCategoryMaterial.setTitle(uploadFiles.getFilename());
			
			//uri
			outlineCategoryMaterial.setFileUri(uploadFiles.getTargetType() + "/"
							+ uploadFiles.getTargetId() + "/"
							+ uploadFiles.getHashId());
			
			outlineCategoryMaterial.setFileMime(uploadFiles.getExt());
			outlineCategoryMaterial.setFileSize(uploadFiles.getSize());
			outlineCategoryMaterial.setAdmin(currentAdmin());
			
			
			outlineCategoryMaterials.add(outlineCategoryMaterial);
		}
		outlineCategoryMaterialService.saveOutlineCategoryMaterials(outlineCategoryMaterials);
		return "redirect:fileList.jhtml?outlineCategoryId="+outlineCategory.getId();
	}
	
	
	/**
	 * 从课程文件中选择
	 */
	@RequestMapping(value="/fromCourseFile",method=RequestMethod.GET)
	public String fromCourseFile(ModelMap model){
		List<CourseCategory> courseCategories=courseCategoryService.findTree();
		
		model.addAttribute("courseCategories", courseCategories);
		return "/admin/outline_category_file/fromCourseFile";
	}
	
	/**
	 * 分页显示类别下的所有课程的课时文件
	 * targetId----courseId
	 * 
	 * obj[0]--uploadFiles
	 * obj[1]--对应的课程名
	 */
	@RequestMapping(value="/courseUploadFiles",method=RequestMethod.POST)
	public String courseUploadFiles(ModelMap model,Long targetId,Long courseCategoryId,String courseName,Pageable pageable){
		
		Page<Object[]> page=uploadFilesService.findPage(courseName,courseCategoryId,targetId, pageable, TARGET_TYPE.courselesson.toString());
		model.addAttribute("page", page);
		model.addAttribute("unitConversion",
				useStaticPacker("com.sram.util.UnitConversionUtil"));
		return "/admin/outline_category_file/courseFileList";
	}
	
	/**
	 * 选择类别或课程
	 */
	@RequestMapping(value="/categoryAndCourse",method=RequestMethod.GET)
	public @ResponseBody
	List<String> categoryAndCourse(Long parentId){
		List<String> categoryAndCourse = new ArrayList<String>();
		if(parentId==null){
			
			//获取课程类别
			List<CourseCategory> courseCategories=courseCategoryService.findTree();
			for (CourseCategory courseCategory:courseCategories) {
				categoryAndCourse.add(courseCategory.getGrade()+","+courseCategory.getId()+","+courseCategory.getName());
			}
		}else{
			
			//获取课程
			List<Course> courses = courseService.findList(parentId, null, null, null, null, null, null, null, null, null);
			for(Course course:courses){
				categoryAndCourse.add("0,"+course.getId()+","+course.getTitle());
			}
		}
		return categoryAndCourse;
	}
}
