package com.sram.controller.admin;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.sram.Message;
import com.sram.Pageable;
import com.sram.entity.IndustryCategory;
import com.sram.service.IndustryCategoryService;

/**
 * 行业
 * @author Administrator
 *
 */
@Controller("adminIndustryCategoryController")
@RequestMapping("/admin/industry_category")
public class IndustryCategoryController extends BaseController {
	@Resource(name = "industryCategoryServiceImpl")
	private IndustryCategoryService industryCategoryService;
	/**
	 * 父列表添加
	 * findTree查询所有级别
	 */
	@RequestMapping(value = "/addParent", method = RequestMethod.GET)
	public String addParent(ModelMap model) {
		model.addAttribute("maxOrder", industryCategoryService.findMaxOrder());	
		return "/admin/industry_category/addParent";
	}

	/**
	 * 子列表添加
	 * id 父ID
	 */
	@RequestMapping(value = "/addChildren", method = RequestMethod.GET)
	public String addChildren(ModelMap model, Long id) {
		IndustryCategory parent = industryCategoryService.find(id);
		List<IndustryCategory> childrens = industryCategoryService
				.findChildren(parent,null);
		childrens.add(0, parent);
		model.addAttribute("industryCategoryTree", childrens);
		model.addAttribute("rootId",id);
		return "/admin/industry_category/addChildren";
	}

	/**
	 * 父列表
	 * findRoots查询所有父类
	 * parentList 父类列表
	 */
	@RequestMapping(value = "/parentList", method = RequestMethod.GET)
	public String parentList(Pageable pageable,ModelMap model) {
		model.addAttribute("page",
				industryCategoryService.findRootsPage(pageable));
		return "/admin/industry_category/parentList";
	}
	/**
	 * 子列表
	 * id父节点ID
	 *childrenList 子类列表 
	 *parent 直接父节点
	 *children parent下对应的所有子节点
	 */
	@RequestMapping(value = "/childrenList", method = RequestMethod.GET)
	public String childrenList(ModelMap model, Long id) {
		IndustryCategory parent = industryCategoryService.find(id);
		List<IndustryCategory> children = industryCategoryService
				.findChildren(parent,null);
		children.add(0, parent);
		model.addAttribute("industryCategoryTree", children);
		model.addAttribute("rootId",id);
		return "/admin/industry_category/childrenList";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(IndustryCategory industryCategory, Long parentId, Long rootId,
			 RedirectAttributes redirectAttributes) {
		industryCategory.setParent(industryCategoryService.find(parentId));
		if (!isValid(industryCategory)) {
			return ERROR_VIEW;
		}
		if(parentId!=null){
			industryCategory.setOrder(Integer.parseInt(industryCategoryService.findMaxOrder(parentId).toString()));	
		}
		industryCategoryService.save(industryCategory);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		if(rootId != null){
			return "redirect:childrenList.jhtml?id=" + rootId;
		}
		return "redirect:parentList.jhtml";
	}

	/**
	 * 编辑最大父节点
	 * maxParent 最大父节点
	 */
	@RequestMapping(value = "/editParent", method = RequestMethod.GET)
	public String editParent(Long id, ModelMap model,Long rootId) {
		IndustryCategory maxParent = industryCategoryService.find(id);
		model.addAttribute("industryCategory", maxParent);
		model.addAttribute("rootId",rootId);
		return "/admin/industry_category/editParent";
	}
	
	/**
	 * 编辑子节点
	 */
	@RequestMapping(value = "/editChildren", method = RequestMethod.GET)
	public String editChildren(Long id, ModelMap model,Long rootId) {
		//当前编辑的节点
		IndustryCategory children= industryCategoryService.find(id);
		
		//所属的一棵树的根节点----找到这棵树的所有子节点与根节点一起返回
		IndustryCategory parent = industryCategoryService.find(rootId);
		List<IndustryCategory> categories=industryCategoryService.findChildren(parent,null);
		categories.add(0, parent);
		model.addAttribute("industryCategoryTree",categories);
		model.addAttribute("industryCategory", children);
		model.addAttribute("rootId",rootId);
		return "/admin/industry_category/editChildren";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Message delete(Long id) {
		IndustryCategory industryCategory = industryCategoryService.find(id);
		if (industryCategory == null) {
			return ERROR_MESSAGE;
		}
		 List<IndustryCategory> childrens = industryCategoryService.findChildren(industryCategory,null);
		 if (childrens != null && !childrens.isEmpty()) {
		 return
		 DELETE_PARENT_ERROR_MESSAGE;
		 }
		industryCategoryService.delete(id);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(IndustryCategory industryCategory, Long parentId,
			Long rootId, RedirectAttributes redirectAttributes) {
		industryCategory.setParent(industryCategoryService.find(parentId));
		if (!isValid(industryCategory)) {
			return ERROR_VIEW;
		}
		if (industryCategory.getParent() != null) {
			IndustryCategory parent = industryCategory.getParent();
			if (parent.equals(industryCategory)) {
				return ERROR_VIEW;
			}
		}
		industryCategoryService.update(industryCategory, "treePath", "grade");
		
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		
		if(rootId != null){
			return "redirect:childrenList.jhtml?id=" + rootId;
		}
		return "redirect:parentList.jhtml";
	}
	
	/**
	 * 更新大纲的排序号
	 * @param currentNodeId 当前大纲ID
	 * @param currentOrder 当前大纲排序号
	 * @param moveNodeId 移动大纲ID
	 * @param moveOrder 移动大纲排序号
	 * @return 
	 */
	@RequestMapping(value = "/updateIndustryOrder", method = RequestMethod.POST)
	@ResponseBody
	public String updateIndustryOrder(Long currentNodeId,Long currentOrder,Long moveNodeId,Long moveOrder) {
		if(industryCategoryService.updateIndustryOrder(currentNodeId, currentOrder, moveNodeId, moveOrder)){
			return "Y";
		}else{
			return "N";
		}
		
	}
}
