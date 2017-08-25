package com.sram.dao;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.OutlineCategory;


public interface OutlineCategoryDao extends BaseDao<OutlineCategory, Long>{
	/**
	 * 大纲的数量
	 * 
	 * @param count
	 *            数量
	 * @return 顶级大纲分类
	 */
	List<OutlineCategory> findRoots(Integer count);
	
	/**
	 * 根据大纲ID查询它自己所有子大纲，并且包括自己
	 * @param outlineCategoryId
	 * @return
	 */
	List<OutlineCategory> findAllChildren(Long outlineCategoryId);

	/**
	 * 大纲上级
	 * 
	 * @param OutlineCategory
	 *            大纲分类
	 * @param count
	 *            数量
	 * @return 上级大纲分类
	 */
	List<OutlineCategory> findParents(OutlineCategory outlineCategory, Integer count);

	/**
	 * 查找下级问题分类
	 * 
	 * @param OutlineCategory
	 *            大纲分类
	 * @param count
	 *            数量
	 * @return 下级大纲分类
	 */
	List<OutlineCategory> findChildren(OutlineCategory outlineCategory, Integer count);
	/**
	 * 通过大纲对象查询行业集合
	 * @param industryCategoryID
	 * @return
	 */
	boolean findIndustryCategory(Long industryCategoryID);
	/**
	 * 通过大纲编号查询大纲编号
	 * @param code
	 * @return
	 */
	boolean findOutLineCategoryCode(String code);
	/**
	 * 取得最大编码
	 * @return
	 */
   String maxBm(Long id);
   OutlineCategory findByCode(String code);
   public List<OutlineCategory> findRootsExcludeGenerator();
   /**
	 * 更新大纲的排序号
	 * @param currentNodeId 当前大纲ID
	 * @param currentOrder 当前大纲排序号
	 * @param moveNodeId 移动大纲ID
	 * @param moveOrder 移动大纲排序号
	 * @return 
	 */
	public boolean updateOutlineOrder(Long currentNodeId,Long currentOrder,Long moveNodeId,Long moveOrder);
	/**
	 * 根据行业id,取得1级大纲下最大排序号
	 * @param  industryCategoryID
	 * @return
	 */
	public Long findMaxParentOrder(Long industryCategoryID);
	
	/**
	 * 根据parentID,取得1级下的2级大纲最大排序号
	 * @param parentId
	 * @return
	 */
	public Long findMaxChildrenOrder(Long parentId);

	Page<OutlineCategory> findRootsPage(Pageable pageable, String firstIndustryName, String secondIndustryName);
	
	/**
	 * 修改指定id的类别名称
	 * @param name
	 * @param id
	 */
	void updateChapItemName(String name, Long id);

	/**
	 * 对大纲进行关联
	 * @param relateCourseChapter
	 * @param outlineCategoryId
	 */
	void relateCourseChapter(OutlineCategory outlineCategory);


}
