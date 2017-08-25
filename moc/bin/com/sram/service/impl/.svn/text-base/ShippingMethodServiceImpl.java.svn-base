/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service.impl;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;

import com.sram.dao.ShippingMethodDao;
import com.sram.entity.ShippingMethod;
import com.sram.service.ShippingMethodService;

/**
 * Service - 配送方式
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("shippingMethodServiceImpl")
public class ShippingMethodServiceImpl extends BaseServiceImpl<ShippingMethod, Long> implements ShippingMethodService {

	@Resource(name = "shippingMethodDaoImpl")
	public void setBaseDao(ShippingMethodDao shippingMethodDao) {
		super.setBaseDao(shippingMethodDao);
	}

}