package com.sram.dao;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.AccoutCatalog;
public interface AccoutCatalogDao extends BaseDao<AccoutCatalog, Long>{
	/**
	 * 会计账目的数量
	 * 
	 * @param count
	 *            数量
	 * @return 顶级会计账目分类
	 */
	List<AccoutCatalog> findRoots(Integer count);
	
	/**
	 * 更新会计账目的排序号
	 * @param currentNodeId 当前会计账目ID
	 * @param currentOrder 当前会计账目排序号
	 * @param moveNodeId 移动会计账目ID
	 * @param moveOrder 移动会计账目排序号
	 * @return 
	 */
	public boolean updateAccoutCatalogOrder(Long currentNodeId,Long currentOrder,Long moveNodeId,Long moveOrder);

	/**
	 * 会计账目上级
	 * 
	 * @param AccoutCatalog
	 *            会计账目分类
	 * @param count
	 *            数量
	 * @return 上级会计账目分类
	 */
	List<AccoutCatalog> findParents(AccoutCatalog accoutCatalog, Integer count);

	/**
	 * 查找下级问题分类
	 * 
	 * @param AccoutCatalog
	 *            会计账目分类
	 * @param count
	 *            数量
	 * @return 下级会计账目分类
	 */
	List<AccoutCatalog> findChildren(AccoutCatalog accoutCatalog, Integer count);
	/**
	 * 根据parentID,取得1级下的2级大纲最大排序号
	 * @param parentId
	 * @return
	 */
	public Long findMaxChildrenOrder(Long parentId);
	/**
	 * 通过会计账目编号查询会计账目编号
	 * @param code
	 * @return
	 */
	boolean findAccoutCatalogCode(String code);
	/**
	 * 取得最大编码
	 * @return
	 */
	String maxBm(Long id);
	
   /**
	 * <p>功能:查找所有子节点</p> 
	 * @author 杨楷
	 * @date 2015年4月28日 下午2:12:05 
	 * @return
	 */
	public List<AccoutCatalog> findAllChildren();
	
    AccoutCatalog findByCode(String code);

    Page<AccoutCatalog> findRootsPage(Pageable pageable);
	
}
