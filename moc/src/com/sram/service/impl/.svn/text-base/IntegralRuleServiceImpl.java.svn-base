/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sram.dao.IntegralRuleDao;
import com.sram.entity.IntegralRule;
import com.sram.service.IntegralRuleService;

/**
 * Service - 积分规则
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("integralRuleServiceImpl")
public class IntegralRuleServiceImpl extends
		BaseServiceImpl<IntegralRule, Long> implements IntegralRuleService {

	@Resource(name = "integralRuleDaoImpl")
	private IntegralRuleDao integralRuleDao;

	@Resource(name = "integralRuleDaoImpl")
	public void setBaseDao(IntegralRuleDao integralRuleDao) {
		super.setBaseDao(integralRuleDao);
	}

	public List<IntegralRule> findIntegralRuleByUrlPath(String urlPath) {
		return integralRuleDao.findIntegralRuleByUrlPath(urlPath);
	}

	public List<Object[]> findAllTasks(Long memberId) {
		return integralRuleDao.findAllTasks(memberId);
	}

}