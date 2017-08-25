/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.Message;
import com.sram.entity.Course;
import com.sram.entity.CourseLesson;
import com.sram.entity.CourseMaterial;
import com.sram.entity.UploadFiles;
import com.sram.service.CourseMaterialService;
import com.sram.service.UploadFilesService;

/**
 * Controller - 课时
 * 
 * @author Sram Team
 * @version 1.0
 */
@Controller("adminCourseMaterialController")
@RequestMapping("/admin/course_material")
public class CourseMaterialController extends BaseController {
	@Resource(name = "courseMaterialServiceImpl")
	private CourseMaterialService courseMaterialService;
	@Resource(name = "uploadFilesServiceImpl")
	private UploadFilesService uploadFilesService;

	/**
	 * 添加页面
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model, Long courseId, Long lessonId) {
		//
		model.addAttribute("courseId", courseId);
		model.addAttribute("lessonId", lessonId);

		// 该课时已有的资料
		List<CourseMaterial> courseLessonMaterials = courseMaterialService
				.findListByLessonId(lessonId,null);
		model.addAttribute("courseLessonMaterials", courseLessonMaterials);

		// 该课程的所有资料
		List<UploadFiles> courseMaterials = uploadFilesService
				.findUploadFilesByTypeAndId("coursematerial", courseId, null);

		// 移除掉已选的资料
		for (CourseMaterial courseMaterial : courseLessonMaterials) {
			UploadFiles uploadFiles = courseMaterial.getUploadFiles();
			if (courseMaterials.contains(uploadFiles)) {
				courseMaterials.remove(uploadFiles);
			}
		}

		model.addAttribute("courseMaterialFiles", courseMaterials);

		model.addAttribute("unitConversion",
				useStaticPacker("com.sram.util.UnitConversionUtil"));
		return "/admin/course_material/add";

	}

	/**
	 * 添加课时资料
	 * 
	 * @param courseLesson
	 * @param free
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Long[] ids, Long courseId, Long lessonId,
			RedirectAttributes redirectAttributes) {

		List<CourseMaterial> courseMaterials = new ArrayList<CourseMaterial>();

		Course course = new Course();
		course.setId(courseId);

		CourseLesson courseLesson = new CourseLesson();
		courseLesson.setId(lessonId);

		for (Long id : ids) {
			CourseMaterial courseMaterial = new CourseMaterial();
			courseMaterial.setAdmin(currentAdmin());
			courseMaterial.setCourse(course);
			courseMaterial.setCourseLesson(courseLesson);
			UploadFiles uploadFiles = uploadFilesService.find(id);
			courseMaterial.setUploadFiles(uploadFiles);
			courseMaterial.setTitle(uploadFiles.getFilename());
			courseMaterial.setFileSize(uploadFiles.getSize());
			courseMaterial
					.setFileUri(uploadFiles.getTargetType() + "/"
							+ uploadFiles.getTargetId() + "/"
							+ uploadFiles.getHashId());
			courseMaterial.setFileMime(uploadFiles.getExt());
			courseMaterials.add(courseMaterial);
		}

		courseMaterialService.saveCourseMaterials(courseMaterials);

		return "redirect:/admin/course_material/add.jhtml?courseId=" + courseId
				+ "&lessonId" + lessonId;
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		courseMaterialService.delete(ids);
		return SUCCESS_MESSAGE;
	}

}