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
import com.sram.Pageable;
import com.sram.entity.AccoutCatalog;
import com.sram.service.AccoutCatalogService;


@Controller("accoutCatalogController")
@RequestMapping("/admin/accout_catalog")
public class AccoutCatalogController extends BaseController {

	@Resource(name = "accoutCatalogServiceImpl")
	private AccoutCatalogService accoutCatalogService;
	/**
	 * 获取行业列表
	 */
	@RequestMapping(value = "/getAccoutCatalog", method = RequestMethod.GET)
    @ResponseBody
	public List<String> getAccoutCatalog() {
		List<AccoutCatalog> list  = accoutCatalogService.findAll();
		List<String> accoutCategorys = new ArrayList<String>();
		for (AccoutCatalog accoutCatalog:list) {
			accoutCategorys.add(accoutCatalog.getGrade()+","+accoutCatalog.getId()+","+accoutCatalog.getName());
		}
		return accoutCategorys;
	}
	
	/**
	 * 父列表添加 findTree查询所有级别
	 */
	@RequestMapping(value = "/addParent", method = RequestMethod.GET)
	public String addParent(ModelMap model) {
		model.addAttribute("accoutCatalogTree",accoutCatalogService.findTree());
		return "/admin/accout_catalog/addParent";
	}

	/**
	 * 子列表添加 id 父ID
	 */
	@RequestMapping(value = "/addChildren", method = RequestMethod.GET)
	public String addChildren(ModelMap model, Long id) {
		AccoutCatalog parent = accoutCatalogService.find(id);
		List<AccoutCatalog> childrens = accoutCatalogService.findChildren(
				parent, null);
		childrens.add(0, parent);
		model.addAttribute("accoutCatalogTree", childrens);
		model.addAttribute("rootId", id);
		return "/admin/accout_catalog/addChildren";
	}

	/**
	 * 父列表 findRoots查询所有父类 parentList 父类列表
	 */
	@RequestMapping(value = "/parentList", method = RequestMethod.GET)
	public String parentList(Pageable pageable,ModelMap model,Long id) {
		model.addAttribute("page",
				accoutCatalogService.findRootsPage(pageable));
		return "/admin/accout_catalog/parentList";
	}

	/**
	 * 子列表 id父节点ID childrenList 子类列表 parent 直接父节点 children parent下对应的所有子节点
	 */
	@RequestMapping(value = "/childrenList", method = RequestMethod.GET)
	public String childrenList(ModelMap model, Long id) {
		AccoutCatalog accoutCatalog=accoutCatalogService.find(id);
		List<AccoutCatalog> children = accoutCatalogService.findChildren(
				accoutCatalog, null);
		children.add(0, accoutCatalog);
		model.addAttribute("accoutCatalogTree", children);
		model.addAttribute("rootId", id);
		return "/admin/accout_catalog/childrenList";
	}
	
	/**
	 * 更新大纲的排序号
	 * @param currentNodeId 当前大纲ID
	 * @param currentOrder 当前大纲排序号
	 * @param moveNodeId 移动大纲ID
	 * @param moveOrder 移动大纲排序号
	 * @return 
	 */
	@RequestMapping(value = "/updateAccoutCatalogOrder", method = RequestMethod.POST)
	@ResponseBody
	public String updateAccoutCatalogOrder(Long currentNodeId,Long currentOrder,Long moveNodeId,Long moveOrder) {
		if(accoutCatalogService.updateAccoutCatalogOrder(currentNodeId, currentOrder, moveNodeId, moveOrder)){
			return "Y";
		}else{
			return "N";
		}
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(AccoutCatalog accoutCatalog, Long parentId,
			Long rootId, RedirectAttributes redirectAttributes) {
		accoutCatalog.setParent(accoutCatalogService.find(parentId));
		if (!isValid(accoutCatalog)) {
			return ERROR_VIEW;
		}
		if(parentId!=null){
			accoutCatalog.setOrder(Integer.parseInt(accoutCatalogService.findMaxChildrenOrder(parentId).toString()));	
		}
		accoutCatalogService.save(accoutCatalog);
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		if (rootId != null) {
			return "redirect:childrenList.jhtml?id=" + rootId;
		}
		return "redirect:parentList.jhtml";
	}

	/**
	 * 编辑最大父节点 maxParent 最大父节点
	 */
	@RequestMapping(value = "/editParent", method = RequestMethod.GET)
	public String editParent(Long id, ModelMap model, Long rootId) {
		AccoutCatalog maxParent = accoutCatalogService.find(id);
		model.addAttribute("accoutCatalog", maxParent);
		model.addAttribute("rootId", rootId);
		return "/admin/accout_catalog/editParent";
	}

	/**
	 * 编辑子节点
	 */
	@RequestMapping(value = "/editChildren", method = RequestMethod.GET)
	public String editChildren(Long id, ModelMap model, Long rootId) {
		// 当前编辑的节点
		AccoutCatalog children = accoutCatalogService.find(id);

		// 所属的一棵树的根节点----找到这棵树的所有子节点与根节点一起返回
		AccoutCatalog parent = accoutCatalogService.find(rootId);
		List<AccoutCatalog> categories = accoutCatalogService.findChildren(
				parent, null);
		categories.add(0, parent);
		model.addAttribute("accoutCatalogTree", categories);
		model.addAttribute("accoutCatalog", children);
		model.addAttribute("rootId", rootId);
		return "/admin/accout_catalog/editChildren";
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Message delete(Long id) {
		AccoutCatalog accoutCatalog = accoutCatalogService.find(id);
		if (accoutCatalog == null) {
			return ERROR_MESSAGE;
		}
		List<AccoutCatalog> childrens = accoutCatalogService.findChildren(
				accoutCatalog, null);
		if (childrens != null && !childrens.isEmpty()) {
			return DELETE_PARENT_ERROR_MESSAGE;
		}
		accoutCatalogService.delete(id);
		return SUCCESS_MESSAGE;
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(AccoutCatalog accoutCatalog, Long parentId,
			Long rootId, RedirectAttributes redirectAttributes) {
		accoutCatalog.setParent(accoutCatalogService.find(parentId));
		if (!isValid(accoutCatalog)) {
			return ERROR_VIEW;
		}
		if (accoutCatalog.getParent() != null) {
			AccoutCatalog parent = accoutCatalog.getParent();
			if (parent.equals(accoutCatalog)) {
				return ERROR_VIEW;
			}
		}
		//更新大纲
		accoutCatalogService.update(accoutCatalog, "treePath", "grade");
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);

		if (rootId != null) {
			return "redirect:childrenList.jhtml?id=" + rootId;
		}
		return "redirect:parentList.jhtml";
	}

	/**
	 * 验证编码是否被引用
	 * 
	 * @param response
	 * @param parentId
	 */
	@RequestMapping(value = "/chack_accoutCatalog_code", method = RequestMethod.GET)
	@ResponseBody
	public  boolean chackAccoutCatalogCode(String code,Long id) {
		if(id!=null){
			AccoutCatalog accoutCatalog = accoutCatalogService.find(id);
			if(accoutCatalog.getCode().equals(code)){
				return true;
			}
		}
		return accoutCatalogService.findAccoutCatalogCode(code);
	}
}
