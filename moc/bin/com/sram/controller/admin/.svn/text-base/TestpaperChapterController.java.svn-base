package com.sram.controller.admin;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sram.Message;
import com.sram.entity.Testpaper;
import com.sram.entity.TestpaperChapter;
import com.sram.service.TestpaperChapterService;
import com.sram.service.TestpaperService;

/**
 * 试卷章节控制类
 */
@Controller("TestpaperChapterController")
@RequestMapping("/admin/testpaper_chapter")
public class TestpaperChapterController extends BaseController{
	@Resource(name = "testpaperServiceImpl")
	private TestpaperService testpaperService;
	@Resource(name = "testpaperChapterServiceImpl")
	private TestpaperChapterService testpaperChapterService;
	
	/**
	 * 添加试卷章节
	 * @param model
	 * @param outlineCategoryId
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String list(Long testpaperId,ModelMap model) {
		model.addAttribute("testpaperId",testpaperId);
		//章节列表
		model.addAttribute("testpaperChapterList",testpaperChapterService.findTestpaperChapterListByTestpaperId(testpaperId));
		return "/admin/test_paper_chapter/list";
	}
	/**
	 * 添加试卷章节
	 * @param model
	 * @param outlineCategoryId
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Message save(TestpaperChapter testpaperChapter,Long testpaperId,
			ModelMap model) {
		if (!isValid(testpaperChapter)) {
			return ERROR_MESSAGE;
		}
		Testpaper testpaper = new Testpaper();
		testpaper.setId(testpaperId);
		testpaperChapter.setTestpaper(testpaper);
		if(testpaperChapter.getId()!=null){
			TestpaperChapter testpaperChapter2 = testpaperChapterService.find(testpaperChapter.getId());
			testpaperChapter2.setName(testpaperChapter.getName());
			testpaperChapter2.setOrder(testpaperChapter.getOrder());
			testpaperChapter2.setDescription(testpaperChapter.getDescription());
			testpaperChapterService.update(testpaperChapter2);
		}else{
			testpaperChapterService.save(testpaperChapter);
		}
		return SUCCESS_MESSAGE;
	}
	/**
	 * 
	 * @param model
	 * @param testpaperId
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(ModelMap model,Long testpaperId) {
		Testpaper testpaper = testpaperService.find(testpaperId);
		
		model.addAttribute("testpaper",testpaper);
		return "/admin/test_paper_chapter/add";
	}
	@RequestMapping(value = "/findTestpaperChapter", method = RequestMethod.POST)
	public @ResponseBody TestpaperChapter findTestpaperChapter(Long id) {
		TestpaperChapter testpaperChapter = testpaperChapterService.find(id);
		return testpaperChapter;
	}
	
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	Message delete(Long[] ids) {
		try {
			for(Long id:ids){
				TestpaperChapter testpaperChapter = testpaperChapterService.find(id);
				if(testpaperChapter==null){
					return ERROR_MESSAGE;
				}
			}
			testpaperChapterService.delete(ids);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			//e.printStackTrace();
			return Message.error("admin.productCategory.deleteExistChildrenNotAllowed");
		}
		return SUCCESS_MESSAGE;
	}
}
