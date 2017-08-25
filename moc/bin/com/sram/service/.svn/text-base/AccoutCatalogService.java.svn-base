package com.sram.service;
import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.AccoutCatalog;
public interface AccoutCatalogService extends BaseService<AccoutCatalog, Long>{

	/**
	 * 查找顶级大纲
	 * 
	 * @return 顶级大纲
	 */
	List<AccoutCatalog> findRoots();
	
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
	 * 查找顶级大纲分页对象
	 * 
	 * @return 顶级大纲分页对象
	 */
	Page<AccoutCatalog> findRootsPage(Pageable pageable);

	/**
	 * 查找上级大纲分类
	 * 
	 * @param AccoutCatalog
	 *            大纲分类
	 * @return 上级大纲
	 */
	List<AccoutCatalog> findParents(AccoutCatalog accoutCatalog);


	/**
	 * 查找大纲分类树
	 * 
	 * @return 大纲分类树
	 */
	List<AccoutCatalog> findTree();

	/**
	 * 查找下级大纲分类
	 * 
	 * @param AccoutCatalog
	 *            大纲分类
	 * @param count
	 *            数量
	 * @return 下级大纲分类
	 */
	List<AccoutCatalog> findChildren(AccoutCatalog accoutCatalog, Integer count);
	/**
	 * 根据parentID,取得1级下的2级大纲最大排序号
	 * @param parentId
	 * @return
	 */
	public Long findMaxChildrenOrder(Long parentId);
	
	/**
	 * 取得最大编码
	 * @return
	 */
	public String maxBm(Long id);
	/**
	 * 通过大纲编号查询大纲编号
	 * @param code
	 * @return
	 */
	boolean findAccoutCatalogCode(String code);
	/**
	 * <p>功能:查找所有子节点</p> 
	 * @author 杨楷
	 * @date 2015年4月28日 下午2:12:05 
	 * @return
	 */
	public List<AccoutCatalog> findAllChildren();
}
