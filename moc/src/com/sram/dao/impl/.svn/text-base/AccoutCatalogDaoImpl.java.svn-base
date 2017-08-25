package com.sram.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.AccoutCatalogDao;
import com.sram.entity.AccoutCatalog;
import com.sram.entity.OutlineCategory;
@Repository("accoutCatalogDaoImpl")
public  class AccoutCatalogDaoImpl extends BaseDaoImpl<AccoutCatalog, Long>
implements AccoutCatalogDao {

	public List<AccoutCatalog> findRoots(Integer count) {
		String jpql = "select accoutCatalog from AccoutCatalog accoutCatalog where accoutCatalog.parent is null order by accoutCatalog.order asc";
		TypedQuery<AccoutCatalog> query = entityManager.createQuery(jpql, AccoutCatalog.class).setFlushMode(FlushModeType.COMMIT);
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	public List<AccoutCatalog> findParents(AccoutCatalog accoutCatalog,
			Integer count) {
		if (accoutCatalog == null || accoutCatalog.getParent() == null) {
			return Collections.<AccoutCatalog> emptyList();
		}
		String jpql = "select AccoutCatalog from AccoutCatalog AccoutCatalog where AccoutCatalog.id in (:ids) order by AccoutCatalog.grade asc";
		TypedQuery<AccoutCatalog> query = entityManager.createQuery(jpql, AccoutCatalog.class).setFlushMode(FlushModeType.COMMIT).setParameter("ids", accoutCatalog.getTreePath());
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	public List<AccoutCatalog> findChildren(AccoutCatalog accoutCatalog,
			Integer count) {
		TypedQuery<AccoutCatalog> query;
		if (accoutCatalog != null) {
			String jpql = "select o from AccoutCatalog o where o.treePath like :treePath order by o.order asc";
			query = entityManager.createQuery(jpql, AccoutCatalog.class).setFlushMode(FlushModeType.COMMIT).setParameter("treePath", "%" + AccoutCatalog.TREE_PATH_SEPARATOR + accoutCatalog.getId() + AccoutCatalog.TREE_PATH_SEPARATOR + "%");
		} else {
			String jpql = "select o from AccoutCatalog o order by o.order asc";
			query = entityManager.createQuery(jpql, AccoutCatalog.class).setFlushMode(FlushModeType.COMMIT);
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return sort(query.getResultList(), accoutCatalog);
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
	private List<AccoutCatalog> sort(List<AccoutCatalog> accoutCatalogs, AccoutCatalog parent) {
		List<AccoutCatalog> result = new ArrayList<AccoutCatalog>();
		if (accoutCatalogs != null) {
			for (AccoutCatalog accoutCatalog : accoutCatalogs) {
				if ((accoutCatalog.getParent() != null && accoutCatalog.getParent().equals(parent)) || (accoutCatalog.getParent() == null && parent == null)) {
					result.add(accoutCatalog);
					result.addAll(sort(accoutCatalogs, accoutCatalog));
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
	public void persist(AccoutCatalog accoutCatalog) {
		Assert.notNull(accoutCatalog);
		setValue(accoutCatalog);
		super.persist(accoutCatalog);
	}

	/**
	 * 设置treePath、grade并更新
	 * 
	 * @param CourseCategory
	 *            商品分类
	 * @return 商品分类
	 */
	@Override
	public AccoutCatalog merge(AccoutCatalog accoutCatalog) {
		Assert.notNull(accoutCatalog);
		setValue(accoutCatalog);
		return super.merge(accoutCatalog);
	}

	/**
	 * 设置值
	 * 
	 * @param CourseCategory
	 *            商品分类
	 */
	private void setValue(AccoutCatalog accoutCatalog) {
		if (accoutCatalog == null) {
			return;
		}
		AccoutCatalog parent = accoutCatalog.getParent();
		if (parent != null) {
			accoutCatalog.setTreePath(parent.getTreePath() + parent.getId() + AccoutCatalog.TREE_PATH_SEPARATOR);
		} else {
			accoutCatalog.setTreePath(AccoutCatalog.TREE_PATH_SEPARATOR);
		}
		accoutCatalog.setGrade(accoutCatalog.getTreePaths().size());
	}
	
	public String maxBm(Long id) {
		String jpsql = "";
		if(id==null){
			jpsql="SELECT LPAD(IFNULL(MAX(`code`),'0')+1,2,'0') FROM moc_outline_category where LENGTH(code)=2";
		}else{
			jpsql="SELECT IFNULL( concat('0',(select MAX(`code`) from moc_outline_category where parent=+"+id+")+1),concat(LPAD(code,(LENGTH(replace(tree_path,',','--'))-LENGTH(tree_path))*2,'01'),'01')) FROM moc_outline_category where id="+id;
		}
		Query query = entityManager.createNativeQuery(jpsql).setFlushMode(FlushModeType.COMMIT);
		return query.getResultList().get(0).toString();
	}
	
	/**
	 * <p>功能:条件查询</p> 
	 * @author 杨楷
	 * @date 2015-3-5 上午11:15:01 
	 * @param code
	 * @return
	 */
	public AccoutCatalog findByCode(String code){
		try{
		return this.entityManager.createQuery("select o from AccoutCatalog o where lower(o.code) = lower(:code)", AccoutCatalog.class).setFlushMode(FlushModeType.COMMIT).setParameter("code", code).getSingleResult();}
		catch (NoResultException e) {
			return null;
		}
	}
	/**
	 * 验证行业大纲编码是否被引用
	 */
	public boolean findAccoutCatalogCode(String code) {
		try {
			String jpql="select o from AccoutCatalog o where o.code=:code";
			Object object = entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("code",code).getSingleResult();
			return object!=null?false:true;
		} catch (NoResultException e) {
			return true;
		}
	}
	/**
	 * <p>功能:查找所有子节点</p> 
	 * @author 杨楷
	 * @date 2015年4月28日 下午2:12:05 
	 * @return
	 */
	public List<AccoutCatalog> findAllChildren(){
		String jpql = "select AccoutCatalog from AccoutCatalog AccoutCatalog where AccoutCatalog.parent.id is not null";
		TypedQuery<AccoutCatalog> query = entityManager.createQuery(jpql, AccoutCatalog.class);
		return query.getResultList();
	}

	public Page<AccoutCatalog> findRootsPage(Pageable pageable) {
		// TODO Auto-generated method stub
		String searchProperty = pageable.getSearchProperty();
		String searchValue = pageable.getSearchValue();
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer jpql=new StringBuffer();
		jpql.append("select accoutCatalog from AccoutCatalog accoutCatalog where accoutCatalog.parent is null");
		if (StringUtils.isNotEmpty(searchProperty) && StringUtils.isNotEmpty(searchValue)){
	    	if(searchProperty.equals("name")){
	    		jpql.append(" and accoutCatalog.name like ?");
	    		conditionList.add("%"+searchValue+"%");
	    	}
	    	if(searchProperty.equals("code")){
	    		jpql.append(" and accoutCatalog.code like ?");
	    		conditionList.add("%"+searchValue+"%");
	    	}
	    }
		jpql.append(" order by accoutCatalog.order asc");
		long total=countRootsCatalog(searchProperty,searchValue);
		int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		List<AccoutCatalog> list=createQueryPage(jpql.toString(),pageable.getPageNumber(),pageable.getPageSize()
				,conditionList);
		return new Page<AccoutCatalog>(list,total,pageable);
	}

	private long countRootsCatalog(String searchProperty,String searchValue) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer jpql=new StringBuffer();
		jpql.append("select count(*) from AccoutCatalog a where a.parent is null");
		if (StringUtils.isNotEmpty(searchProperty) && StringUtils.isNotEmpty(searchValue)){
	    	if(searchProperty.equals("name")){
	    		jpql.append(" and a.name like ?");
	    		conditionList.add("%"+searchValue+"%");
	    	}
	    	if(searchProperty.equals("code")){
	    		jpql.append(" and a.code like ?");
	    		conditionList.add("%"+searchValue+"%");
	    	}
	    }
		long count=createQueryCount(jpql.toString(),conditionList);
		return count;
	}

	public boolean updateAccoutCatalogOrder(Long currentNodeId,
			Long currentOrder, Long moveNodeId, Long moveOrder) {
		try {
			String jpql ="update AccoutCatalog o set o.order="+moveOrder+" where o.id="+currentNodeId;
			Query query = entityManager.createQuery(jpql);
			query.executeUpdate();
			String jpql2 ="update AccoutCatalog o set o.order="+currentOrder+" where o.id="+moveNodeId;
			Query query2 = entityManager.createQuery(jpql2);
			query2.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Long findMaxChildrenOrder(Long parentId) {
		String jpql ="select IFNULL(max(o.order),0)+1 from AccoutCatalog o where o.parent.id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(parentId);
		return this.createQueryCount(jpql,list);
	}
}
