/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service.impl;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.sram.dao.LogDao;
import com.sram.entity.Log;
import com.sram.service.LogService;

/**
 * Service - 日志
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("logServiceImpl")
public class LogServiceImpl extends BaseServiceImpl<Log, Long> implements LogService {

	@Resource(name = "logDaoImpl")
	private LogDao logDao;

	@Resource(name = "logDaoImpl")
	public void setBaseDao(LogDao logDao) {
		super.setBaseDao(logDao);
	}

	public void clear() {
		logDao.removeAll();
	}

}