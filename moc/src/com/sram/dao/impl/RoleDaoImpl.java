/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao.impl;


import org.springframework.stereotype.Repository;

import com.sram.dao.RoleDao;
import com.sram.entity.Role;

/**
 * Dao - 角色
 * 
 * @author Sram Team
 * @version 1.0
 */
@Repository("roleDaoImpl")
public class RoleDaoImpl extends BaseDaoImpl<Role, Long> implements RoleDao {

}