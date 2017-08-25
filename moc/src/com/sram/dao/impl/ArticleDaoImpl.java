/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;


import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sram.Filter;
import com.sram.Order;
import com.sram.Order.Direction;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.ArticleDao;
import com.sram.entity.Article;
import com.sram.entity.ArticleCategory;
import com.sram.entity.CourseLesson;
import com.sram.entity.CourseThread;
import com.sram.entity.Tag;

/**
 * Dao - 文章
 * 
 * @author Sram Team
 * @version 1.0
 */
@Repository("articleDaoImpl")
public class ArticleDaoImpl extends BaseDaoImpl<Article, Long> implements ArticleDao {

	public List<Article> findList(ArticleCategory articleCategory, List<Tag> tags, Integer count, List<Filter> filters, List<Order> orders) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
		Root<Article> root = criteriaQuery.from(Article.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isPublication"), true));
		if (articleCategory != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.equal(root.get("articleCategory"), articleCategory), criteriaBuilder.like(root.get("articleCategory").<String> get("treePath"), "%" + ArticleCategory.TREE_PATH_SEPARATOR + articleCategory.getId() + ArticleCategory.TREE_PATH_SEPARATOR + "%")));
		}
		if (tags != null && !tags.isEmpty()) {
			Subquery<Article> subquery = criteriaQuery.subquery(Article.class);
			Root<Article> subqueryRoot = subquery.from(Article.class);
			subquery.select(subqueryRoot);
			subquery.where(criteriaBuilder.equal(subqueryRoot, root), subqueryRoot.join("tags").in(tags));
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.exists(subquery));
		}
		criteriaQuery.where(restrictions);
//		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("isTop")));
		return super.findList(criteriaQuery, null, count, filters, orders);
	}

	public List<Article> findList(ArticleCategory articleCategory, Date beginDate, Date endDate, Integer first, Integer count) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
		Root<Article> root = criteriaQuery.from(Article.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isPublication"), true));
		if (articleCategory != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.equal(root.get("articleCategory"), articleCategory), criteriaBuilder.like(root.get("articleCategory").<String> get("treePath"), "%" + ArticleCategory.TREE_PATH_SEPARATOR + articleCategory.getId() + ArticleCategory.TREE_PATH_SEPARATOR + "%")));
		}
		if (beginDate != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThanOrEqualTo(root.<Date> get("createDate"), beginDate));
		}
		if (endDate != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThanOrEqualTo(root.<Date> get("createDate"), endDate));
		}
		criteriaQuery.where(restrictions);
//		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("isTop")));
		return super.findList(criteriaQuery, first, count, null, null);
	}
	
	/**
	 * 查出所有文章（包含类别
	 */
	public Page<Object[]> findPageCategoryName(Pageable pageable){
		StringBuffer jpql=new StringBuffer();
		jpql.append(" from Article a ")
		.append("left join a.articleCategory ac ");
		jpql.append(" where 1=1 ");
		
		String searchProperty =pageable.getSearchProperty();
		String searchValue = pageable.getSearchValue();
		if(StringUtils.isNotEmpty(searchProperty) && StringUtils.isNotEmpty(searchValue)){
			if("categoryName".equals(searchProperty)){
				jpql.append(" and ac.name like '%"+searchValue+"%'");
			}else{
				jpql.append(" and a.title like '%" + searchValue+"%'");
			}
		}
		
		
		
		long total =entityManager.createQuery("select count(*) "+jpql, Long.class).setFlushMode(FlushModeType.COMMIT).getSingleResult();
		int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		
		TypedQuery<Object[]> query =entityManager.createQuery("select a,ac.name "+jpql.toString(),Object[].class).setFlushMode(FlushModeType.COMMIT);
		query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
		return new Page<Object[]>(query.getResultList(),total,pageable);
	}
	public Page<Article> findPage(ArticleCategory articleCategory, List<Tag> tags, Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
		Root<Article> root = criteriaQuery.from(Article.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isPublication"), true));
		if (articleCategory != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.equal(root.get("articleCategory"), articleCategory), criteriaBuilder.like(root.get("articleCategory").<String> get("treePath"), "%" + ArticleCategory.TREE_PATH_SEPARATOR + articleCategory.getId() + ArticleCategory.TREE_PATH_SEPARATOR + "%")));
		}
		if (tags != null && !tags.isEmpty()) {
			Subquery<Article> subquery = criteriaQuery.subquery(Article.class);
			Root<Article> subqueryRoot = subquery.from(Article.class);
			subquery.select(subqueryRoot);
			subquery.where(criteriaBuilder.equal(subqueryRoot, root), subqueryRoot.join("tags").in(tags));
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.exists(subquery));
		}
		criteriaQuery.where(restrictions);
//		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("isTop")));
		return super.findPage(criteriaQuery, pageable);
	}

	/**
	 * 上一篇---最新的-----紧临当前文章（比当前的时间大）
	 */
	public Article findPrevArticle(Article article) {
		StringBuffer jpql=new StringBuffer();
		jpql.append("select a from Article a ")
		.append(" where a.createDate >=  :currentCreateDate ")
		.append(" and a.id <> :id ")
		.append("  and a.articleCategory =:articleCategory ")
		.append(" order by a.createDate asc ");
		TypedQuery<Article> query=entityManager.createQuery(jpql.toString(),Article.class)
		.setFlushMode(FlushModeType.COMMIT);
		
		query.setParameter("currentCreateDate", article.getCreateDate());
		query.setParameter("articleCategory", article.getArticleCategory());
		query.setParameter("id", article.getId());
		query.setFirstResult(0);
		query.setMaxResults(1);
		return query.getResultList().size()>0?query.getSingleResult():null;
	}

	public Article findNextArticle(Article article) {
		StringBuffer jpql=new StringBuffer();
		jpql.append("select a from Article a ")
		.append(" where a.createDate <=  :currentCreateDate ")
		.append("  and a.articleCategory =:articleCategory ")
		.append(" and a.id <> :id ")
		.append(" order by a.createDate desc ");
		TypedQuery<Article> query=entityManager.createQuery(jpql.toString(),Article.class)
		.setFlushMode(FlushModeType.COMMIT);
		
		query.setParameter("currentCreateDate", article.getCreateDate());
		query.setParameter("articleCategory", article.getArticleCategory());
		query.setParameter("id", article.getId());
		query.setFirstResult(0);
		query.setMaxResults(1);
		return query.getResultList().size()>0?query.getSingleResult():null;
	}

}