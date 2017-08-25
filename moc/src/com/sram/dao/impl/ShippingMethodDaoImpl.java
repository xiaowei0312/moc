/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao.impl;


import org.springframework.stereotype.Repository;

import com.sram.dao.ShippingMethodDao;
import com.sram.entity.ShippingMethod;

/**
 * Dao - 配送方式
 * 
 * @author Sram Team
 * @version 1.0
 */
@Repository("shippingMethodDaoImpl")
public class ShippingMethodDaoImpl extends BaseDaoImpl<ShippingMethod, Long> implements ShippingMethodDao {

}