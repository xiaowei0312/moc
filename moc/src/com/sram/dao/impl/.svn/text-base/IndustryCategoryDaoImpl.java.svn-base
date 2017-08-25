package com.sram.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.IndustryCategoryDao;
import com.sram.entity.AccoutCatalog;
import com.sram.entity.IndustryCategory;
@Repository("industryCategoryDaoImpl")
public class IndustryCategoryDaoImpl extends BaseDaoImpl<IndustryCategory, Long>
implements IndustryCategoryDao {

	public List<IndustryCategory> findRoots(Integer count) {
		String jpql = "select o from IndustryCategory o where o.parent is null order by o.order asc";
		TypedQuery<IndustryCategory> query = entityManager.createQuery(jpql, IndustryCategory.class).setFlushMode(FlushModeType.COMMIT);
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	public List<IndustryCategory> findParents(IndustryCategory industryCategory,
			Integer count) {
		if (industryCategory == null || industryCategory.getParent() == null) {
			return Collections.<IndustryCategory> emptyList();
		}
		String jpql = "select o from IndustryCategory o where o.id in (:ids) order by IndustryCategory.grade asc";
		TypedQuery<IndustryCategory> query = entityManager.createQuery(jpql, IndustryCategory.class).setFlushMode(FlushModeType.COMMIT).setParameter("ids", industryCategory.getTreePath());
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	public List<IndustryCategory> findChildren(IndustryCategory industryCategory,
			Integer count) {
		TypedQuery<IndustryCategory> query;
		if (industryCategory != null) {
			String jpql = "select o from IndustryCategory o where o.treePath like :treePath order by o.order asc";
			query = entityManager.createQuery(jpql, IndustryCategory.class).setFlushMode(FlushModeType.COMMIT).setParameter("treePath", "%" + IndustryCategory.TREE_PATH_SEPARATOR + industryCategory.getId() + IndustryCategory.TREE_PATH_SEPARATOR + "%");
		} else {
			String jpql = "select o from IndustryCategory o order by o.order asc";
			query = entityManager.createQuery(jpql, IndustryCategory.class).setFlushMode(FlushModeType.COMMIT);
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return sort(query.getResultList(), industryCategory);
	}
	/**
	 * 排序商品分类
	 * 
	 * @param productCategories
	 *            商品分类
	 * @param parent
	 *            上级商品分类
	 * @return 商品分类
	 */
	private List<IndustryCategory> sort(List<IndustryCategory> industryCategorys, IndustryCategory parent) {
		List<IndustryCategory> result = new ArrayList<IndustryCategory>();
		if (industryCategorys != null) {
			for (IndustryCategory industryCategory : industryCategorys) {
				if ((industryCategory.getParent() != null && industryCategory.getParent().equals(parent)) || (industryCategory.getParent() == null && parent == null)) {
					result.add(industryCategory);
					result.addAll(sort(industryCategorys, industryCategory));
				}
			}
		}
		return result;
	}
	/**
	 * 设置treePath、grade并保存
	 * 
	 * @param CourseCategory
	 *            商品分类
	 */
	@Override
	public void persist(IndustryCategory industryCategory) {
		Assert.notNull(industryCategory);
		setValue(industryCategory);
		super.persist(industryCategory);
	}

	/**
	 * 设置treePath、grade并更新
	 * 
	 * @param CourseCategory
	 *            商品分类
	 * @return 商品分类
	 */
	@Override
	public IndustryCategory merge(IndustryCategory industryCategory) {
		Assert.notNull(industryCategory);
		setValue(industryCategory);
		for (IndustryCategory category : findChildren(industryCategory, null)) {
			setValue(category);
		}
		return super.merge(industryCategory);
	}

	/**
	 * 设置值
	 * 
	 * @param industryCategory
	 */
	private void setValue(IndustryCategory industryCategory) {
		if (industryCategory == null) {
			return;
		}
		IndustryCategory parent = industryCategory.getParent();
		if (parent != null) {
			industryCategory.setTreePath(parent.getTreePath() + parent.getId() + IndustryCategory.TREE_PATH_SEPARATOR);
		} else {
			industryCategory.setTreePath(IndustryCategory.TREE_PATH_SEPARATOR);
		}
		industryCategory.setGrade(industryCategory.getTreePaths().size()); 
	}
	
	public List<IndustryCategory> findAllCategorys(){
		String jpql = "select category from IndustryCategory as category left join fetch category.outlineCategories as o where o.grade=0 or o.id is null"
				+ " order by category.order,o.order asc";
		TypedQuery<IndustryCategory> query = entityManager.createQuery(jpql, IndustryCategory.class);
		return query.getResultList();
	}

	public boolean updateIndustryOrder(Long currentNodeId, Long currentOrder,
			Long moveNodeId, Long moveOrder) {
		try {
			String jpql ="update IndustryCategory o set o.order="+moveOrder+" where o.id="+currentNodeId;
			Query query = entityManager.createQuery(jpql);
			query.executeUpdate();
			String jpql2 ="update IndustryCategory o set o.order="+currentOrder+" where o.id="+moveNodeId;
			Query query2 = entityManager.createQuery(jpql2);
			query2.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	}

	public Long findMaxOrder() {
		String jpql ="select IFNULL(max(o.order),0)+1 from IndustryCategory o where o.parent is null";
		return this.createQueryCount(jpql,null);
	}

	public Long findMaxOrder(Long parentId) {
		String jpql ="select IFNULL(max(o.order),0)+1 from IndustryCategory o where o.parent.id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(parentId);
		return this.createQueryCount(jpql,list);
	}

	public Page<IndustryCategory> findRootsPage(Pageable pageable) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		String searchProperty = pageable.getSearchProperty();
		String searchValue = pageable.getSearchValue();
		StringBuffer jpql=new StringBuffer();
		jpql.append("select o from IndustryCategory o where o.parent is null");
	    if (StringUtils.isNotEmpty(searchProperty) && StringUtils.isNotEmpty(searchValue)){
	    	if(searchProperty.equals("name")){
	    		jpql.append(" and o.name like ?");
	    		conditionList.add("%"+searchValue+"%");
	    	}
	    }
	    jpql.append(" order by o.order asc");
		long total=countRootsCategory(searchProperty,searchValue);
		int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		List<IndustryCategory> list=createQueryPage(jpql.toString(),pageable.getPageNumber(),pageable.getPageSize()
				,conditionList);
		return new Page<IndustryCategory>(list,total,pageable);
	}

	private long countRootsCategory(String searchProperty,String searchValue) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer jpql=new StringBuffer();
		jpql.append("select count(*) from IndustryCategory a where a.parent is null");
		if (StringUtils.isNotEmpty(searchProperty) && StringUtils.isNotEmpty(searchValue)){
	    	if(searchProperty.equals("name")){
	    		jpql.append(" and a.name like ?");
	    		conditionList.add("%"+searchValue+"%");
	    	}
	    }
		long count=createQueryCount(jpql.toString(),conditionList);
		return count;
	}
}
