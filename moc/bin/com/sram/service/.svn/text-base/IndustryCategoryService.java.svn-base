package com.sram.service;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.IndustryCategory;

public interface IndustryCategoryService extends BaseService<IndustryCategory, Long>{
	
	/**
	 * 查找顶级行业
	 * 
	 * @return 顶级行业
	 */
	List<IndustryCategory> findRoots();
    
	/**
	 * 查找顶级行业分页
	 * 
	 * @return 顶级行业分页
	 */
	Page<IndustryCategory> findRootsPage(Pageable pageable);

	/**
	 * 查找上级行业分类
	 * 
	 * @param IndustryCategory
	 *            行业分类
	 * @return 上级行业
	 */
	List<IndustryCategory> findParents(IndustryCategory outlineCategory);


	/**
	 * 查找行业分类树
	 * 
	 * @return 行业分类树
	 */
	List<IndustryCategory> findTree();

	/**
	 * 查找下级行业分类
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
	 * @param industryCategoryId
	 * @return
	 */
	public Long findMaxOrder(Long parentId);

}
