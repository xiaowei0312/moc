/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao.impl;


import org.springframework.stereotype.Repository;

import com.sram.dao.AdDao;
import com.sram.entity.Ad;

/**
 * Dao - 广告
 * 
 * @author Sram Team
 * @version 1.0
 */
@Repository("adDaoImpl")
public class AdDaoImpl extends BaseDaoImpl<Ad, Long> implements AdDao {

}