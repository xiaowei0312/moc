/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.sram.dao.CourseCategoryDao;
import com.sram.entity.CourseCategory;

/**
 * Dao - 课程分类
 * 
 * @author Sram Team
 * @version 1.0
 */
@Repository("courseCategoryDaoImpl")
public class CourseCategoryDaoImpl extends BaseDaoImpl<CourseCategory, Long> implements CourseCategoryDao {

	public List<CourseCategory> findRoots(Integer count) {
		String jpql = "select c from CourseCategory c where c.parent is null order by c.order asc";
		TypedQuery<CourseCategory> query = entityManager.createQuery(jpql, CourseCategory.class).setFlushMode(FlushModeType.COMMIT);
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	public List<CourseCategory> findParents(CourseCategory courseCategory, Integer count) {
		if (courseCategory == null || courseCategory.getParent() == null) {
			return Collections.<CourseCategory> emptyList();
		}
		String jpql = "select c from CourseCategory c where c.id in (:ids) order by CourseCategory.grade asc";
		TypedQuery<CourseCategory> query = entityManager.createQuery(jpql, CourseCategory.class).setFlushMode(FlushModeType.COMMIT).setParameter("ids", courseCategory.getTreePath());
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	public List<CourseCategory> findChildren(CourseCategory courseCategory, Integer count) {
		TypedQuery<CourseCategory> query;
		if (courseCategory != null) {
			String jpql = "select c from CourseCategory c where c.treePath like :treePath order by c.order asc";
			query = entityManager.createQuery(jpql, CourseCategory.class).setFlushMode(FlushModeType.COMMIT).setParameter("treePath", "%" + CourseCategory.TREE_PATH_SEPARATOR + courseCategory.getId() + CourseCategory.TREE_PATH_SEPARATOR + "%");
		} else {
			String jpql = "select c from CourseCategory c order by c.order asc";
			query = entityManager.createQuery(jpql, CourseCategory.class).setFlushMode(FlushModeType.COMMIT);
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return sort(query.getResultList(), courseCategory);
	}

	/**
	 * 设置treePath、grade并保存
	 * 
	 * @param CourseCategory
	 *            课程分类
	 */
	@Override
	public void persist(CourseCategory courseCategory) {
		Assert.notNull(courseCategory);
		setValue(courseCategory);
		super.persist(courseCategory);
	}

	/**
	 * 设置treePath、grade并更新
	 * 
	 * @param CourseCategory
	 *            课程分类
	 * @return 课程分类
	 */
	@Override
	public CourseCategory merge(CourseCategory courseCategory) {
		Assert.notNull(courseCategory);
		setValue(courseCategory);
		for (CourseCategory category : findChildren(courseCategory, null)) {
			setValue(category);
		}
		return super.merge(courseCategory);
	}

	/**
	 * 清除课程属性值并删除
	 * 
	 * @param CourseCategory
	 *            课程分类
	 */
	@Override
	public void remove(CourseCategory courseCategory) {
		if (courseCategory != null) {
			super.remove(courseCategory);
		}
	}

	/**
	 * 排序课程分类
	 * 
	 * @param productCategories
	 *            课程分类
	 * @param parent
	 *            上级课程分类
	 * @return 课程分类
	 */
	private List<CourseCategory> sort(List<CourseCategory> courseCategories, CourseCategory parent) {
		List<CourseCategory> result = new ArrayList<CourseCategory>();
		if (courseCategories != null) {
			for (CourseCategory courseCategory : courseCategories) {
				if ((courseCategory.getParent() != null && courseCategory.getParent().equals(parent)) || (courseCategory.getParent() == null && parent == null)) {
					result.add(courseCategory);
					result.addAll(sort(courseCategories, courseCategory));
				}
			}
		}
		return result;
	}

	/**
	 * 设置值
	 * 
	 * @param CourseCategory
	 *            课程分类
	 */
	private void setValue(CourseCategory courseCategory) {
		if (courseCategory == null) {
			return;
		}
		CourseCategory parent = courseCategory.getParent();
		
		if (parent != null) {
			courseCategory.setTreePath(parent.getTreePath() + parent.getId() + CourseCategory.TREE_PATH_SEPARATOR);
		} else {
			courseCategory.setTreePath(CourseCategory.TREE_PATH_SEPARATOR);
		}
		courseCategory.setGrade(courseCategory.getTreePaths().size());
	}
    
	/**
	 * 查找一条热门话题
	 */
	public List<Object[]> findHotThread() {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append("select mc.id,mc.name,count(mt.id) num");
		sql.append(" from moc_course_category mc ");
		sql.append(" left join moc_course m ");
		sql.append(" on mc.id=m.course_category");
		sql.append(" left join  moc_course_thread mt");
		
		/**
		 * 荣刚平--2015.4.25--条件中需要有主表的id
		 */
	    sql.append(" on mc.id=mt.course_category_id");
	    
	    /**
	     * 荣刚平---2015，4，24----只查叶子类别
	     */
	    sql.append(" where mc.id not in (select mc.parent from moc_course_category mc where mc.parent is not null) ");
	    sql.append(" group by mc.id");
	    sql.append(" order by num desc");
		return createNativeQueryPage(sql.toString(),1,1,conditionList);
	}

	public List<CourseCategory> findSecondCategory() {
		// TODO Auto-generated method stub
		String jpql="select c from CourseCategory c where c.grade=1 order by c.order asc";
		TypedQuery<CourseCategory> query = entityManager.createQuery(jpql, CourseCategory.class).setFlushMode(FlushModeType.COMMIT);
		return query.getResultList();
	}

}