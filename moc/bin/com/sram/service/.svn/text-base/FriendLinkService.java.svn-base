/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service;

import java.util.List;

import com.sram.Filter;
import com.sram.Order;
import com.sram.entity.FriendLink;
import com.sram.entity.FriendLink.Type;


/**
 * Service - 友情链接
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface FriendLinkService extends BaseService<FriendLink, Long> {

	/**
	 * 查找友情链接
	 * 
	 * @param type
	 *            类型
	 * @return 友情链接
	 */
	List<FriendLink> findList(Type type);

	/**
	 * 查找友情链接(缓存)
	 * 
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orders
	 *            排序
	 * @param cacheRegion
	 *            缓存区域
	 * @return 友情链接(缓存)
	 */
	List<FriendLink> findList(Integer count, List<Filter> filters, List<Order> orders, String cacheRegion);

}