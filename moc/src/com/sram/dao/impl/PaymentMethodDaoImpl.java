/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao.impl;


import org.springframework.stereotype.Repository;

import com.sram.dao.PaymentMethodDao;
import com.sram.entity.PaymentMethod;

/**
 * Dao - 支付方式
 * 
 * @author Sram Team
 * @version 1.0
 */
@Repository("paymentMethodDaoImpl")
public class PaymentMethodDaoImpl extends BaseDaoImpl<PaymentMethod, Long> implements PaymentMethodDao {

}