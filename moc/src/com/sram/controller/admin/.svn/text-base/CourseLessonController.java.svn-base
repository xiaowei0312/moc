/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.Message;
import com.sram.entity.Admin;
import com.sram.entity.CourseLesson;
import com.sram.entity.CourseLesson.Status;
import com.sram.entity.UploadFiles;
import com.sram.service.CourseLessonService;
import com.sram.service.CourseNoteService;
import com.sram.service.UploadFilesService;

/**
 * Controller - 课时
 * 
 * @author Sram Team
 * @version 1.0
 */
@Controller("adminCourseLessonController")
@RequestMapping("/admin/course_lesson")
public class CourseLessonController extends BaseController {
	@Resource(name = "courseLessonServiceImpl")
	private CourseLessonService courseLessonService;
	@Resource(name = "uploadFilesServiceImpl")
	private UploadFilesService uploadFilesService;
	@Resource(name="courseNoteServiceImpl")
	private CourseNoteService courseNoteService;
	/**
	 * 根据课程id---获取对应的章节，，课时
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap model, Long courseId) {
		return "/admin/course_lesson/list";
	}

	/**
	 * 添加
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model, Long courseId, Long chapterId) {
		Admin admin = currentAdmin();
		model.addAttribute("userId", admin.getId());
		model.addAttribute("courseId", courseId);
		model.addAttribute("chapterId", chapterId);

		//
		List<UploadFiles> courseLessonFiles = uploadFilesService
				.findUploadFilesByTypeAndId("courselesson", courseId, null);
		model.addAttribute("courseLessonFiles", courseLessonFiles);

		List<UploadFiles> courseMaterialFiles = uploadFilesService
				.findUploadFilesByTypeAndId("coursematerial", courseId, null);
		model.addAttribute("courseMaterialFiles", courseMaterialFiles);

		model.addAttribute("uniConversion",
				useStaticPacker("com.sram.util.UnitConversionUtil"));

		return "/admin/course_lesson/add";
	}

	/**
	 * 添加资料
	 */
	@RequestMapping(value = "/addMaterial", method = RequestMethod.GET)
	public String addMaterial(ModelMap model, Long courseId, Long lessonId) {
		//
		model.addAttribute("courseId", courseId);
		model.addAttribute("lessonId", lessonId);

		List<UploadFiles> courseMaterialFiles = uploadFilesService
				.findUploadFilesByTypeAndId("coursematerial", courseId, null);
		model.addAttribute("courseMaterialFiles", courseMaterialFiles);

		model.addAttribute("uniConversion",
				useStaticPacker("com.sram.util.UnitConversionUtil"));
		return "/admin/course_lesson/addMaterial";
	}

	/**
	 * 保存课时
	 * 
	 * @param courseLesson
	 * @param free
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(CourseLesson courseLesson, String[] free,
			RedirectAttributes redirectAttributes) {
		Admin admin = currentAdmin();
		courseLesson.setAdmin(admin);
		if (null != free && free.length == 1) {
			courseLesson.setFree(Integer.parseInt(free[0]));
		}
		//
		if (courseLesson.getUploadFiles() != null
				&& courseLesson.getUploadFiles().getId() != null) {

			UploadFiles uploadFiles = uploadFilesService.find(courseLesson
					.getUploadFiles().getId());
			if (uploadFiles != null) {
				courseLesson.setMediaUri(uploadFiles.getTargetType() + "/"
						+ uploadFiles.getTargetId() + "/"
						+ uploadFiles.getConvertHash() + ".flv");
				courseLesson.setMediaName(uploadFiles.getFilename());
			}
		}

		if ("text".equals(courseLesson.getType())) {
			courseLesson.setUploadFiles(null);
		}
		courseLessonService.save(courseLesson);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:/admin/course_chapter/list.jhtml?courseId="
				+ courseLesson.getCourse().getId()+"#lesson-"+courseLesson.getId();
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long id) {

		courseNoteService.deleteByLessonId(id);
		try {
			//笔记单向关联，，OneToOne无法懒加载
			courseLessonService.delete(id);
		} catch (Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				return Message.error("亲，该课时有关联的数据---无法删除哈");
			}
			return Message.error("无法删除" + "\r\n" + e.toString());
		}
		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 关联大纲
	 */
	@RequestMapping(value = "/relevanceCategory", method = RequestMethod.POST)
	public @ResponseBody
	Message relevanceCategory(Long lessonId, Long outlineCategoryId) {
		try{
		    courseLessonService.updateRelevanceById(lessonId,outlineCategoryId);
		}catch(Exception e){
			e.printStackTrace();
			return Message.error("课时大纲关联出现异常，请联系管理员");
		}
		return Message.success("课时大纲关联成功");
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(ModelMap model, Long id) {
		CourseLesson courseLesson = courseLessonService.find(id);
		Long courseId = courseLesson.getCourse().getId();
		Admin admin = currentAdmin();
		model.addAttribute("userId", admin.getId());
		model.addAttribute("courseLesson", courseLesson);

		model.addAttribute("uniConversion",
				useStaticPacker("com.sram.util.UnitConversionUtil"));

		if ("video".equals(courseLesson.getType())) {
			List<UploadFiles> videoUploadFiles = uploadFilesService
					.findUploadFilesByTypeAndId("courselesson", courseId,
							"video");
			model.addAttribute("videoUploadFiles", videoUploadFiles);
			return "/admin/course_lesson/editVideo";
		} else if ("audio".equals(courseLesson.getType())) {
			List<UploadFiles> audioUploadFiles = uploadFilesService
					.findUploadFilesByTypeAndId("courselesson", courseId,
							"audio");
			model.addAttribute("audioUploadFiles", audioUploadFiles);
			return "/admin/course_lesson/editAudio";
		} else if ("ppt".equals(courseLesson.getType())) {
			List<UploadFiles> pptUploadFiles = uploadFilesService
					.findUploadFilesByTypeAndId("courselesson", courseId, "ppt");
			model.addAttribute("pptUploadFiles", pptUploadFiles);
			return "/admin/course_lesson/editPPT";
		} else {
			return "/admin/course_lesson/editText";
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(CourseLesson courseLesson, String[] free,
			RedirectAttributes redirectAttributes) {
		CourseLesson tempCourseLesson = courseLessonService.find(courseLesson
				.getId());
		tempCourseLesson.setTitle(courseLesson.getTitle());
		tempCourseLesson.setSummary(courseLesson.getSummary());
		if (null != free && free.length == 1) {
			tempCourseLesson.setFree(Integer.parseInt(free[0]));
		}
		if ("text".equals(courseLesson.getType())) {
			tempCourseLesson.setContent(courseLesson.getContent());

		} else {
			UploadFiles uploadFiles = uploadFilesService.find(courseLesson
					.getUploadFiles().getId());
			// 将uploadFiles文件的名称\视频大小
			tempCourseLesson.setMediaName(uploadFiles.getFilename());
			tempCourseLesson.setLength(uploadFiles.getLength());
			tempCourseLesson.setUploadFiles(uploadFiles);
			tempCourseLesson.setMediaUri(uploadFiles.getTargetType() + "/"
					+ uploadFiles.getTargetId() + "/"
					+ uploadFiles.getConvertHash() + ".flv");

		}
		tempCourseLesson.setCoin(courseLesson.getCoin());
		courseLessonService.update(tempCourseLesson);

		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:/admin/course_chapter/list.jhtml?courseId="
				+ courseLesson.getCourse().getId()+"#lesson-"+courseLesson.getId();
	}

	/**
	 * 有course.id
	 * 
	 * @param courseLesson
	 * @return
	 */
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public @ResponseBody
	Message publish(CourseLesson courseLesson) {

		courseLessonService.changeStatus(courseLesson.getCourse().getId(),courseLesson.getId(),courseLesson.getStatus());

		return SUCCESS_MESSAGE;
	}
	
	/**
	 * 需要coursId以确定是否要重新生成静态页面
	 * @param selectIds
	 * @return
	 */
	@RequestMapping(value="/publishAll",method=RequestMethod.POST)
	public @ResponseBody
	Message publishAll(CourseLesson courseLesson,Long ...selectIds){
		
		try {
			for(Long id:selectIds){
				courseLessonService.changeStatus(courseLesson.getCourse().getId(), id, Status.published);
			}
		} catch (Exception e) {
			return ERROR_MESSAGE;
		}
		
		return SUCCESS_MESSAGE;
	}
}