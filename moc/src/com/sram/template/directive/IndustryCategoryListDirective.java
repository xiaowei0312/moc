package com.sram.template.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sram.entity.IndustryCategory;
import com.sram.service.IndustryCategoryService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/** 
 * <p>功能:</p> 
 * @author 杨楷
 * @date 2015年4月24日 下午6:41:06
 */
@Component("industryCategoryListDirective")
public class IndustryCategoryListDirective  extends BaseDirective {
	
	@Autowired
	private IndustryCategoryService  industryCategoryService;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		List<IndustryCategory> list;
			list = industryCategoryService.findAllCategorys();
		setLocalVariable("rootIndustryCategorys", list, env, body);
	}

}
