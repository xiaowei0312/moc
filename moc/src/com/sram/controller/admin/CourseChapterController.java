/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.Message;
import com.sram.entity.CourseChapter;
import com.sram.service.CourseChapterService;
import com.sram.service.CourseService;

/**
 * Controller - 章节
 * 
 * @author Ram Team
 * @version 1.0
 */
@Controller("adminCourseChapterController")
@RequestMapping("/admin/course_chapter")
public class CourseChapterController extends BaseController {
	@Resource(name = "courseChapterServiceImpl")
	private CourseChapterService courseChapterService;
	@Resource(name="courseServiceImpl")
	private CourseService courseService;
	
	
	/**
	 * pageNumber--课程列表的页码
	 * 
	 * @param model
	 * @param courseId
	 * @param pageNumber
	 * @return
	 */
	@RequestMapping(value = "/addChapter", method = RequestMethod.GET)
	public String addChapter(ModelMap model, Long courseId) {
		model.addAttribute("courseId", courseId);
		return "/admin/course_chapter/addChapter";
	}

	@RequestMapping(value = "/addUnit", method = RequestMethod.GET)
	public String addUnit(ModelMap model, Long courseId, Long parentId) {
		model.addAttribute("courseId", courseId);
		model.addAttribute("parent", courseChapterService.find(parentId));
		return "/admin/course_chapter/addUnit";
	}

	@RequestMapping(value = "/editChapter", method = RequestMethod.GET)
	public String editChapter(ModelMap model, Long id) {
		CourseChapter courseChapter = courseChapterService.find(id);
		model.addAttribute("courseChapter", courseChapter);
		return "/admin/course_chapter/editChapter";
	}

	@RequestMapping(value = "/editUnit", method = RequestMethod.GET)
	public String editUnit(ModelMap model, Long id, Integer pageNumber) {
		CourseChapter courseChapter = courseChapterService.find(id);
		model.addAttribute("courseChapter", courseChapter);
		model.addAttribute("pageNumber", pageNumber);
		return "/admin/course_chapter/editUnit";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(Long id, String title,String summary,
			RedirectAttributes redirectAttributes,HttpServletResponse response) throws IOException {
		CourseChapter courseChapter = courseChapterService.find(id);
		courseChapter.setTitle(title);
		courseChapter.setSummary(summary);
		courseChapterService.update(courseChapter);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		
		//redirect:"";会自动加参数----可能是因为在同一个controller的缘故---添加课时重定向到chapter列表时就没有自动追加
		response.sendRedirect("list.jhtml?courseId="+courseChapter.getCourse().getId()+"#chapter-"+courseChapter.getId());
	}

	/**
	 * 根据课程id---获取对应的章节，，课时 courseStatus--所属课程的状态
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap model,Long courseId) {

		// 根据课程id获得对应的chapter
		List<CourseChapter> courseChapters = courseChapterService
				.findChaptersByCourse(courseId);
		model.addAttribute("courseTitle", courseId==null?"all":courseService.findCourseTitle(courseId));
		model.addAttribute("chapters", courseChapters);
		model.addAttribute("courseId", courseId);
		return "/admin/course_chapter/list";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(CourseChapter courseChapter, Long parentId, RedirectAttributes redirectAttributes,HttpServletResponse response) throws IOException {
		courseChapterService.save(courseChapter, parentId);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		
		//redirect:"";会自动加参数----可能是因为在同一个controller的缘故---添加课时重定向到chapter列表时就没有自动追加
		response.sendRedirect("list.jhtml?courseId="+courseChapter.getCourse().getId()+"#chapter-"+courseChapter.getId());
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long id, String ids) {

		try {
			courseChapterService.delete(id, ids);
		} catch (Exception e) {
			if(e instanceof DataIntegrityViolationException){
				return Message.error("亲，该章节有关联的数据---无法删除哈");
			}
			return Message.error("无法删除"
					+"\r\n"+e.toString());
		}
		return SUCCESS_MESSAGE;
	}
	
	

	/**
	 * 排序
	 */
	@RequestMapping(value = "/sort", method = RequestMethod.POST)
	public @ResponseBody
	Message sort(String ids) {

		try {
			courseChapterService.sort(ids);
		} catch (Exception e) {
			return Message.error("课时必须在章节下面");
		}
		return SUCCESS_MESSAGE;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, Long courseLessonid, ModelMap model) {
		return "/admin/course_chapter/edit";
	}
}