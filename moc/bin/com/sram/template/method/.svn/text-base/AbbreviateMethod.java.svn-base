/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.template.method;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 模板方法 - 字符串缩略
 * 
 * @author Sram Team
 * @version 1.0
 */
@Component("abbreviateMethod")
public class AbbreviateMethod implements TemplateMethodModel {

	/** 中文字符配比 */
	private static final Pattern PATTERN = Pattern.compile("[\\u4e00-\\u9fa5\\ufe30-\\uffa0]+$");

	@SuppressWarnings("rawtypes")
	public Object exec(List arguments) throws TemplateModelException {
		if (arguments != null && !arguments.isEmpty() && arguments.get(0) != null && StringUtils.isNotEmpty(arguments.get(0).toString())) {
			Integer width = null;
			String ellipsis = null;
			if (arguments.size() == 2) {
				if (arguments.get(1) != null) {
					width = Integer.valueOf(arguments.get(1).toString());
				}
			} else if (arguments.size() > 2) {
				if (arguments.get(1) != null) {
					width = Integer.valueOf(arguments.get(1).toString());
				}
				if (arguments.get(2) != null) {
					ellipsis = arguments.get(2).toString();
				}
			}
			return new SimpleScalar(abbreviate(arguments.get(0).toString(), width, ellipsis));
		}
		return null;
	}

	/**
	 * 
	 * 字符串缩略
	 * 
	 * @param str
	 *            原字符串
	 * @param width
	 *            宽度
	 * @param ellipsis
	 *            省略符
	 * @return 缩略字符
	 */
	private String abbreviate(String str, Integer width, String ellipsis) {
		if (width != null) {
			int strLength = 0;
			for (int strWidth = 0; strLength < str.length(); strLength++) {
				strWidth = PATTERN.matcher(String.valueOf(str.charAt(strLength))).find() ? strWidth + 2 : strWidth + 1;
				if (strWidth >= width) {
					break;
				}
			}
			
			//过滤html标签
			java.util.regex.Pattern p_html;
			java.util.regex.Matcher m_html;
			String regEx_html = "<[^>]+>"; 
			p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
	        m_html = p_html.matcher(str);
	        str = m_html.replaceAll(""); 
	        
	        
			if (strLength < str.length()) {
				if (ellipsis != null) {
					return str.substring(0, strLength + 1) + ellipsis;
				} else {
					return str.substring(0, strLength + 1);
				}
			} else {
				return str;
			}
		} else {
			if (ellipsis != null) {
				return str + ellipsis;
			} else {
				return str;
			}
		}
	}

}