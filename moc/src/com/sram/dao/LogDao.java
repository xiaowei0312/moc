/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao;

import com.sram.entity.Log;

/**
 * Dao - 日志
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface LogDao extends BaseDao<Log, Long> {

	/**
	 * 删除所有日志
	 */
	void removeAll();

}