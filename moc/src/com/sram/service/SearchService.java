/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.Article;


/**
 * Service - 搜索
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface SearchService {

	

	/**
	 * 创建索引
	 * 
	 * @param type
	 *            索引类型
	 */
	void index(Class<?> type);

	/**
	 * 创建索引
	 * 
	 * @param article
	 *            文章
	 */
	void index(Article article);




	/**
	 * 删除索引
	 * 
	 * @param type
	 *            索引类型
	 */
	void purge(Class<?> type);

	/**
	 * 删除索引
	 * 
	 * @param article
	 *            文章
	 */
	void purge(Article article);


	/**
	 * 搜索文章分页
	 * 
	 * @param keyword
	 *            关键词
	 * @param pageable
	 *            分页信息
	 * @return 文章分页
	 */
	Page<Article> search(String keyword, Pageable pageable);


}