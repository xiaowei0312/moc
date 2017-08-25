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
import com.sram.dao.OutlineCategoryDao;
import com.sram.entity.OutlineCategory;

@Repository("outlineCategoryDaoImpl")
public class OutlineCategoryDaoImpl extends BaseDaoImpl<OutlineCategory, Long>
		implements OutlineCategoryDao {

	public List<OutlineCategory> findRoots(Integer count) {
		String jpql = "select o from OutlineCategory o" +
				" left join fetch o.industryCategory industry" +
				" where o.parent is null order by industry.name,o.order asc";
		TypedQuery<OutlineCategory> query = entityManager.createQuery(jpql,OutlineCategory.class);
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	public List<OutlineCategory> findParents(OutlineCategory outlineCategory,Integer count) {
		if (outlineCategory == null || outlineCategory.getParent() == null) {
			return Collections.<OutlineCategory> emptyList();
		}
		String jpql = "select o from OutlineCategory o where o.id in (:ids) order by o.grade asc";
		TypedQuery<OutlineCategory> query = entityManager
				.createQuery(jpql, OutlineCategory.class)
				.setParameter("ids", outlineCategory.getTreePath());
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	public List<OutlineCategory> findChildren(OutlineCategory outlineCategory,
			Integer count) {
		TypedQuery<OutlineCategory> query;
		if (outlineCategory != null) {
			String jpql = "select o from OutlineCategory o where o.treePath like :treePath order by o.order asc,o.code asc";
			query = entityManager
					.createQuery(jpql, OutlineCategory.class)
					.setFlushMode(FlushModeType.COMMIT)
					.setParameter(
							"treePath",
							"%" + OutlineCategory.TREE_PATH_SEPARATOR
									+ outlineCategory.getId()
									+ OutlineCategory.TREE_PATH_SEPARATOR + "%");
		} else {
			String jpql = "select o from OutlineCategory o order by o.order asc,o.code asc";
			query = entityManager.createQuery(jpql, OutlineCategory.class);
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return sort(query.getResultList(), outlineCategory);
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
	private List<OutlineCategory> sort(List<OutlineCategory> outlineCategorys,
			OutlineCategory parent) {
		List<OutlineCategory> result = new ArrayList<OutlineCategory>();
		if (outlineCategorys != null) {
			for (OutlineCategory outlineCategory : outlineCategorys) {
				if ((outlineCategory.getParent() != null && outlineCategory
						.getParent().equals(parent))
						|| (outlineCategory.getParent() == null && parent == null)) {
					result.add(outlineCategory);
					result.addAll(sort(outlineCategorys, outlineCategory));
				}
			}
		}
		return result;
	}
	private void tempUpate(){
		//TODO
		String jpql = "select o from OutlineCategory o" +
				" where o.parent is null order by o.order asc";
		TypedQuery<OutlineCategory> query = entityManager.getEntityManagerFactory().createEntityManager().createQuery(jpql,OutlineCategory.class);
		 List<OutlineCategory> parentList = query.getResultList();
		 
		 for (OutlineCategory outlineCategory : parentList) {
			 String treePath="";
			 Integer order=1;
			 String jpql2 = "select o from OutlineCategory o" +
			 " where o.treePath like :treePath order by o.order asc";
			 TypedQuery<OutlineCategory> query2 = entityManager.getEntityManagerFactory().createEntityManager().createQuery(jpql2,OutlineCategory.class)
			.setFlushMode(FlushModeType.COMMIT)
			 .setParameter("treePath",
							"%" + OutlineCategory.TREE_PATH_SEPARATOR
									+ outlineCategory.getId()
									+ OutlineCategory.TREE_PATH_SEPARATOR + "%");
			 List<OutlineCategory> childrenList = sort(query2.getResultList(),outlineCategory);
			 
			 for (int i = 0; i < childrenList.size(); i++) {
				 OutlineCategory outlineCategory2 = childrenList.get(i);
				 if(i==0){
					 continue;
				 }else{
					 if(treePath.equals("")){
						 outlineCategory2.setOrder(1); 
					 }else{
						if(treePath.equals(outlineCategory2.getTreePath())){ 
							outlineCategory2.setOrder(++order); 
						}else{
							outlineCategory2.setOrder(1);
							order=1;
						}
					 }
					 treePath=outlineCategory2.getTreePath();
					 entityManager.getEntityManagerFactory().createEntityManager().merge(outlineCategory2);
				 }
			}
			 //还原变量
			 treePath="";
			 order=1;
		}
		 
	}
	
	/**
	 * 设置treePath、grade并保存
	 * 
	 * @param CourseCategory
	 *            商品分类
	 */
	@Override
	public void persist(OutlineCategory outlineCategory) {
		Assert.notNull(outlineCategory);
		setValue(outlineCategory);
		super.persist(outlineCategory);
	}

	/**
	 * 设置treePath、grade并更新
	 * 
	 * @param CourseCategory
	 *            商品分类
	 * @return 商品分类
	 */
	@Override
	public OutlineCategory merge(OutlineCategory outlineCategory) {
		Assert.notNull(outlineCategory);
		setValue(outlineCategory);
		for (OutlineCategory category : findChildren(outlineCategory, null)) {
			category.setIndustryCategoryID(outlineCategory
					.getIndustryCategoryID());
			setValue(category);
		}
		return super.merge(outlineCategory);
	}

	/**
	 * 设置值
	 * 
	 * @param CourseCategory
	 *            商品分类
	 */
	private void setValue(OutlineCategory outlineCategory) {
		if (outlineCategory == null) {
			return;
		}
		OutlineCategory parent = outlineCategory.getParent();
		if (parent != null) {
			outlineCategory.setTreePath(parent.getTreePath() + parent.getId()
					+ OutlineCategory.TREE_PATH_SEPARATOR);
		} else {
			outlineCategory.setTreePath(OutlineCategory.TREE_PATH_SEPARATOR);
		}
		outlineCategory.setGrade(outlineCategory.getTreePaths().size());
	}

	public String maxBm(Long id) {
		String jpsql = "";
		if (id == null) {
			jpsql = "SELECT LPAD(IFNULL(MAX(`code`),'0')+1,2,'0') FROM moc_outline_category where LENGTH(code)=2";
		} else {
			jpsql = "SELECT IFNULL( concat('0',(select MAX(`code`) from moc_outline_category where parent=+"
					+ id
					+ ")+1),concat(LPAD(code,(LENGTH(replace(tree_path,',','--'))-LENGTH(tree_path))*2,'01'),'01')) FROM moc_outline_category where id="
					+ id;
		}
		Query query = entityManager.createNativeQuery(jpsql).setFlushMode(
				FlushModeType.COMMIT);
		return query.getResultList().get(0).toString();
	}

	/**
	 * <p>
	 * 功能:条件查询
	 * </p>
	 * 
	 * @author 杨楷
	 * @date 2015-3-5 上午11:15:01
	 * @param code
	 * @return
	 */
	public OutlineCategory findByCode(String code) {
		try {
			return this.entityManager
					.createQuery(
							"select o from OutlineCategory o where lower(o.code) = lower(:code)",
							OutlineCategory.class)
					.setFlushMode(FlushModeType.COMMIT)
					.setParameter("code", code).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	/**
	 * 验证行业是否有大纲
	 */
	public boolean findIndustryCategory(Long industryCategoryID) {
		String jpql = "select o from OutlineCategory o where o.industryCategoryID=:industryCategoryID";
		Query query = entityManager.createQuery(jpql).setFirstResult(0)
				.setFlushMode(FlushModeType.COMMIT)
				.setParameter("industryCategoryID", industryCategoryID);
		return query.getResultList().size() > 0 ? true : false;
	}

	/**
	 * 验证行业大纲编码是否被引用
	 */
	public boolean findOutLineCategoryCode(String code) {
		try {
			String jpql = "select o from OutlineCategory o where o.code=:code";
			Object object = entityManager.createQuery(jpql)
					.setFlushMode(FlushModeType.COMMIT)
					.setParameter("code", code).getSingleResult();
			return object != null ? false : true;
		} catch (NoResultException e) {
			return true;
		}
	}
	
	public List<OutlineCategory> findRootsExcludeGenerator() {
		String jpql = "select o from OutlineCategory o where o.id not in(select distinct g.outlineCategory.id from GeneratorStrategy g  where g.outlineCategory.id is not null) and o.grade=0";
		return entityManager.createQuery(jpql)
		.getResultList();
	}

	public boolean updateOutlineOrder(Long currentNodeId, Long currentOrder,
			Long moveNodeId, Long moveOrder) {
		try {
			String jpql ="update OutlineCategory o set o.order="+moveOrder+" where o.id="+currentNodeId;
			Query query = entityManager.createQuery(jpql);
			query.executeUpdate();
			String jpql2 ="update OutlineCategory o set o.order="+currentOrder+" where o.id="+moveNodeId;
			Query query2 = entityManager.createQuery(jpql2);
			query2.executeUpdate();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Long findMaxParentOrder(Long industryCategoryID) {
		String jpql ="select IFNULL(max(o.order),0)+1 from OutlineCategory o where o.parent is null and o.industryCategoryID=?";
		List<Object> list = new ArrayList<Object>();
		list.add(industryCategoryID);
		return this.createQueryCount(jpql,list);
	}

	public Long findMaxChildrenOrder(Long parentId) {
		String jpql ="select IFNULL(max(o.order),0)+1 from OutlineCategory o where o.parent.id=?";
		List<Object> list = new ArrayList<Object>();
		list.add(parentId);
		return this.createQueryCount(jpql,list);
	}


	public List<OutlineCategory> findAllChildren(Long outlineCategoryId) {
		String jpql = "select o from OutlineCategory o where o.id=:outlineCategoryId or o.treePath like :treePath order by o.order asc";
		 TypedQuery<OutlineCategory> query = entityManager
				.createQuery(jpql, OutlineCategory.class)
				.setParameter("outlineCategoryId", outlineCategoryId)
				.setParameter(
						"treePath",
						"%" + OutlineCategory.TREE_PATH_SEPARATOR
								+ outlineCategoryId
								+ OutlineCategory.TREE_PATH_SEPARATOR + "%");
		 return sort(query.getResultList(), null);
	}

   public Page<OutlineCategory> findRootsPage(Pageable pageable,String firstIndustryName,String secondIndustryName) {
	  // tempUpate();//TODO
	   List<Object> conditionList=new ArrayList<Object>();
		String searchProperty = pageable.getSearchProperty();
		String searchValue = pageable.getSearchValue();
		StringBuffer jpql=new StringBuffer();
		jpql.append("select o from OutlineCategory o");
		jpql.append(" left join o.industryCategory industry");
	    jpql.append(" where o.parent is null");
	    if(firstIndustryName!=null && !("").equals(firstIndustryName)){
	    	jpql.append(" and industry.parent.name like ?");
	    	conditionList.add("%"+firstIndustryName+"%");
	    }
	    if(secondIndustryName!=null && !("").equals(secondIndustryName)){
	    	jpql.append(" and industry.name like ?");
	    	conditionList.add("%"+secondIndustryName+"%");
	    }
	    if (StringUtils.isNotEmpty(searchProperty) && StringUtils.isNotEmpty(searchValue)){
	    	if(searchProperty.equals("name")){
	    		jpql.append(" and o.name like ?");
	    		conditionList.add("%"+searchValue+"%");
	    	}
	    	if(searchProperty.equals("code")){
	    		jpql.append(" and o.code like ?");
	    		conditionList.add("%"+searchValue+"%");
	    	}
	    }
	    jpql.append(" order by industry.order,o.order asc");
		long total=countRootsCategory(searchProperty,searchValue,firstIndustryName,secondIndustryName);
		int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		List<OutlineCategory> list=createQueryPage(jpql.toString(),pageable.getPageNumber(),pageable.getPageSize()
				,conditionList);
		return new Page<OutlineCategory>(list,total,pageable);
	}

	private long countRootsCategory(String searchProperty,String searchValue,String firstIndustryName,String secondIndustryName) {
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer jpql=new StringBuffer();
		jpql.append("select count(*)");
		jpql.append(" from OutlineCategory o");
		jpql.append(" where o.parent is null");
		if(firstIndustryName!=null && !("").equals(firstIndustryName)){
	    	jpql.append(" and o.industryCategory.parent.name like ?");
	    	conditionList.add("%"+firstIndustryName+"%");
	    }
	    if(secondIndustryName!=null && !("").equals(secondIndustryName)){
	    	jpql.append(" and o.industryCategory.name like ?");
	    	conditionList.add("%"+secondIndustryName+"%");
	    }
	    if (StringUtils.isNotEmpty(searchProperty) && StringUtils.isNotEmpty(searchValue)){
	    	if(searchProperty.equals("name")){
	    		jpql.append(" and o.name like ?");
	    		conditionList.add("%"+searchValue+"%");
	    	}
	    	if(searchProperty.equals("code")){
	    		jpql.append(" and o.code like ?");
	    		conditionList.add("%"+searchValue+"%");
	    	}
	    }
		long count=createQueryCount(jpql.toString(),conditionList);
		return count;
	}

	public void updateChapItemName(String name, Long id) {
		// TODO Auto-generated method stub
		String jpql="update OutlineCategory o set o.name=:name, where o.id=:id";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("name", name);
		query.setParameter("id",id);
		query.executeUpdate();
	}

	public void relateCourseChapter(OutlineCategory outlineCategory) {
		String jpql = "update OutlineCategory o set o.course_chapter_type =:course_chapter_type,o.course_chapter_id =:course_chapter_id where o.id =:outlineCategoryId";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("course_chapter_type", outlineCategory.getCourse_chapter_type());
		query.setParameter("course_chapter_id", outlineCategory.getCourse_chapter_id());
		query.setParameter("outlineCategoryId", outlineCategory.getId());
		query.executeUpdate();
	}


}
