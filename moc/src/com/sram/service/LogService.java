/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service;

import com.sram.entity.Log;

/**
 * Service - 日志
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface LogService extends BaseService<Log, Long> {

	/**
	 * 清空日志
	 */
	void clear();

}