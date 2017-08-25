package com.sram.controller.admin;
import javax.annotation.Resource;
import javax.net.ssl.SSLEngineResult.Status;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sram.Constants;
import com.sram.Message;
import com.sram.Pageable;
import com.sram.Principal;
import com.sram.controller.moc.BaseController;
import com.sram.entity.Member;
import com.sram.entity.TestpaperResult;
import com.sram.service.TestpaperResultService;
/**
 * 考试结果控制类
 */
@Controller("TestpaperResultController")
@RequestMapping("/admin/testpaper_result")
public class TestpaperResultController extends BaseController{
	@Resource(name = "testpaperResultServiceImpl")
	private TestpaperResultService testpaperResultService;
	/**
	 * 考试试卷列表
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(ModelMap model,Pageable pageable,String testpaperType,String testpaperStatu
			,String memberName) {
		model.addAttribute("page",testpaperResultService.findPage(pageable,testpaperType,testpaperStatu,memberName));
		model.addAttribute("testpaperType", testpaperType);
		model.addAttribute("testpaperStatu", testpaperStatu);
		model.addAttribute("status",Constants.Status.values());
		model.addAttribute("memberName", memberName);
		return "/admin/test_paper_result/list";
	}
	/**
	 * 更新试卷
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(HttpServletRequest request,TestpaperResult testpaper,Long id,RedirectAttributes redirectAttributes) {
		if (!isValid(testpaper)) {
			return ERROR_VIEW;
		}
		Principal principal = (Principal) request.getSession().getAttribute(
				Member.PRINCIPAL_ATTRIBUTE_NAME);
		testpaperResultService.update(testpaper);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:questionList.jhtml";
	}
	/**
	 * 保存试卷对象
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(HttpServletRequest request,TestpaperResult testpaper,RedirectAttributes redirectAttributes) {
		//Admin currentAdmin = super.currentAdmin();
		//testpaper.setCreateAdmin(currentAdmin);
		testpaperResultService.save(testpaper);
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
			TestpaperResult testpaperResult=null;
			for(Long id:ids){
				testpaperResult = testpaperResultService.find(id);
				if(testpaperResult==null){
					return ERROR_MESSAGE;
				}
			}
			testpaperResultService.delete(ids);
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			//e.printStackTrace();
			return Message.error("admin.productCategory.deleteExistChildrenNotAllowed");
		}
		return SUCCESS_MESSAGE;
	}
}
