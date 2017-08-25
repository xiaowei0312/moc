package com.sram.template.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sram.entity.AccoutCatalog;
import com.sram.entity.IndustryCategory;
import com.sram.service.AccoutCatalogService;
import com.sram.service.IndustryCategoryService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/** 
 * <p>功能:</p> 
 * @author 杨楷
 * @date 2015年5月13日17:45:56
 */
@Component("accountCategoryListDirective")
public class AccountCategoryListDirective  extends BaseDirective {
	
	@Autowired
	private AccoutCatalogService accoutCatalogService;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		List<AccoutCatalog> list;
			list = accoutCatalogService.findAllChildren();
		setLocalVariable("accountCategorys", list, env, body);
	}

}
