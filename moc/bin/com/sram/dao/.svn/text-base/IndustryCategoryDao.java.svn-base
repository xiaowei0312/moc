package com.sram.dao;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.IndustryCategory;


public interface IndustryCategoryDao extends BaseDao<IndustryCategory, Long>{
	/**
	 * 行业的数量
	 * 
	 * @param count
	 *            数量
	 * @return 顶级行业分类
	 */
	List<IndustryCategory> findRoots(Integer count);

	/**
	 * 行业上级
	 * 
	 * @param IndustryCategory
	 *            行业分类
	 * @param count
	 *            数量
	 * @return 上级行业分类
	 */
	List<IndustryCategory> findParents(IndustryCategory industryCategory, Integer count);

	/**
	 * 查找下级问题分类
	 * 
	 * @param IndustryCategory
	 *            行业分类
	 * @param count
	 *            数量
	 * @return 下级行业分类
	 */
	List<IndustryCategory> findChildren(IndustryCategory industryCategory, Integer count);
	
	public List<IndustryCategory> findAllCategorys();
	/**
	 * 更新行业的排序号
	 * @param currentNodeId 当前行业ID
	 * @param currentOrder 当前行业排序号
	 * @param moveNodeId 移动行业ID
	 * @param moveOrder 移动行业排序号
	 * @return 
	 */
	public boolean updateIndustryOrder(Long currentNodeId,Long currentOrder,Long moveNodeId,Long moveOrder);
	/**
	 * 取得1级行业下最大排序号
	 * @return
	 */
	public Long findMaxOrder();
	
	/**
	 * 取得1级下的2级行业最大排序号
	 * @param parentId
	 * @return
	 */
	public Long findMaxOrder(Long parentId);

	Page<IndustryCategory> findRootsPage(Pageable pageable);
}
