/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.Deposit;
import com.sram.entity.Member;


/**
 * Service - 预存款
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface DepositService extends BaseService<Deposit, Long> {

	/**
	 * 查找预存款分页
	 * 
	 * @param member
	 *            会员
	 * @param pageable
	 *            分页信息
	 * @return 预存款分页
	 */
	Page<Deposit> findPage(Member member, Pageable pageable);

}