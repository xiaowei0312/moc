/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao.impl;


import org.springframework.stereotype.Repository;

import com.sram.dao.AdPositionDao;
import com.sram.entity.AdPosition;

/**
 * Dao - 广告位
 * 
 * @author Sram Team
 * @version 1.0
 */
@Repository("adPositionDaoImpl")
public class AdPositionDaoImpl extends BaseDaoImpl<AdPosition, Long> implements AdPositionDao {

}