/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao;

import java.util.Date;
import java.util.List;

import com.sram.Filter;
import com.sram.Order;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.Article;
import com.sram.entity.ArticleCategory;
import com.sram.entity.Tag;


/**
 * Dao - 文章
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface ArticleDao extends BaseDao<Article, Long> {

	/**
	 * 查找文章
	 * 
	 * @param articleCategory
	 *            文章分类
	 * @param tags
	 *            标签
	 * @param count
	 *            数量
	 * @param filters
	 *            筛选
	 * @param orders
	 *            排序
	 * @return 文章
	 */
	List<Article> findList(ArticleCategory articleCategory, List<Tag> tags, Integer count, List<Filter> filters, List<Order> orders);

	/**
	 * 查找文章
	 * 
	 * @param articleCategory
	 *            文章分类
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @param first
	 *            起始记录
	 * @param count
	 *            数量
	 * @return 文章
	 */
	List<Article> findList(ArticleCategory articleCategory, Date beginDate, Date endDate, Integer first, Integer count);

	/**
	 * 查找文章分页
	 * 
	 * @param articleCategory
	 *            文章分类
	 * @param tags
	 *            标签
	 * @param pageable
	 *            分页信息
	 * @return 文章分页
	 */
	Page<Article> findPage(ArticleCategory articleCategory, List<Tag> tags, Pageable pageable);

	/**
	 * 0 是文章 ，1是类别名字
	 */
	Page<Object[]> findPageCategoryName(Pageable pageable);
	/**
	 * 查找前一个article
	 * @param articleCategory 
	 * @param articleId
	 * @param orders
	 * @return
	 */
	Article findPrevArticle(Article article);
	
	/**
	 * 查找后一个article
	 * @param articleId
	 * @param orders
	 * @return
	 */
	Article findNextArticle(Article article);

}