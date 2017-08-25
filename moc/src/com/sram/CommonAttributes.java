/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram;

/**
 * 公共参数
 * 
 * @author Sram Team
 * @version 1.0
 */
public final class CommonAttributes {

	/** 日期格式配比 */
	public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

	/** sram.xml文件路径 */
	public static final String SRAM_XML_PATH = "/sram.xml";

	/** sram.properties文件路径 */
	public static final String SRAM_PROPERTIES_PATH = "/sram.properties";

	/**
	 * 不可实例化
	 */
	private CommonAttributes() {
	}

}