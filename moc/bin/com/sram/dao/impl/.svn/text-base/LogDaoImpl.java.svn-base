/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao.impl;

import javax.persistence.FlushModeType;


import org.springframework.stereotype.Repository;

import com.sram.dao.LogDao;
import com.sram.entity.Log;

/**
 * Dao - 日志
 * 
 * @author Sram Team
 * @version 1.0
 */
@Repository("logDaoImpl")
public class LogDaoImpl extends BaseDaoImpl<Log, Long> implements LogDao {

	public void removeAll() {
		String jpql = "delete from Log log";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).executeUpdate();
	}

}