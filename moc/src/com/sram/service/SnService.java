/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service;

import com.sram.entity.Sn.Type;

/**
 * Service - 序列号
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface SnService {

	/**
	 * 生成序列号
	 * 
	 * @param type
	 *            类型
	 * @return 序列号
	 */
	String generate(Type type);

}