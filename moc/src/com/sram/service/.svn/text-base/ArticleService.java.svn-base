/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service;

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
 * Service - 文章
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface ArticleService extends BaseService<Article, Long> {

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
	 * @return 仅包含已发布文章
	 */
	List<Article> findList(ArticleCategory articleCategory, List<Tag> tags, Integer count, List<Filter> filters, List<Order> orders);

	/**
	 * 查找文章(缓存)
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
	 * @param cacheRegion
	 *            缓存区域
	 * @return 仅包含已发布文章
	 */
	List<Article> findList(ArticleCategory articleCategory, List<Tag> tags, Integer count, List<Filter> filters, List<Order> orders, String cacheRegion);

	/**
	 * 查找文章----按时间排序desc
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
	 * @return 仅包含已发布文章
	 */
	List<Article> findList(ArticleCategory articleCategory, Date beginDate, Date endDate, Integer first, Integer count);

	/**
	 * 查找文章分页---按时间排序desc
	 * 
	 * @param articleCategory
	 *            文章分类
	 * @param tags
	 *            标签
	 * @param pageable
	 *            分页信息
	 * @return 仅包含已发布文章
	 */
	Page<Article> findPage(ArticleCategory articleCategory, List<Tag> tags, Pageable pageable);

	/**
	 * 查看并更新点击数
	 * 
	 * @param id
	 *            ID
	 * @return 点击数
	 */
	long viewHits(Long id);

	/**
	 * 查找前一个articl
	 * @param articleCategory --createDate--id
	 * @param id
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

	/**
	 * 0是文章---1是类别的名字
	 */
	Page<Object[]> findPageCategoryName(Pageable pageable);
}