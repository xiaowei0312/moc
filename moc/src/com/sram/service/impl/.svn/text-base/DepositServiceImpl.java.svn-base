/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service.impl;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.DepositDao;
import com.sram.entity.Deposit;
import com.sram.entity.Member;
import com.sram.service.DepositService;

/**
 * Service - 预存款
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("depositServiceImpl")
public class DepositServiceImpl extends BaseServiceImpl<Deposit, Long> implements DepositService {

	@Resource(name = "depositDaoImpl")
	private DepositDao depositDao;

	@Resource(name = "depositDaoImpl")
	public void setBaseDao(DepositDao depositDao) {
		super.setBaseDao(depositDao);
	}

	@Transactional(readOnly = true)
	public Page<Deposit> findPage(Member member, Pageable pageable) {
		return depositDao.findPage(member, pageable);
	}

}