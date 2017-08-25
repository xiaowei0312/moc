package com.sram.service;
import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Question;
import com.sram.entity.QuestionFavorite;
/**
 * 试卷结果
 * @author Administrator
 *
 */
public interface QuestionFavoriteService extends BaseService<QuestionFavorite, Long> {
	public Page<QuestionFavorite> findPage(Pageable pageable);
	public QuestionFavorite findQuestionFavoriteById(Long questionFavoriteId);
	/**
	 * 我的练习收藏
	 * @param target
	 * @param userId
	 * @return
	 */
	public  List<Object[]> findQuestionFavorites(Long target,Long userId);
	
	public void addOrRemove(Long userId,Long questionId,String status);
	/**
	 * 根据大纲ID和用户ID查询对应的收藏的题目
	 * @param outlineCategoryId userId
	 * @return
	 */
	public List<Question> findFavoriteQuestions(Long outlineCategoryId,Long userId);
	/**
	 * 查找收藏的问题ID集合
	 * @param outlineCategoryId
	 * @param userId
	 * @return
	 */
	public List<Long> findFavorite(Long outlineCategoryId,Long userId);
	/**
	 * 我的题库中收藏题目列表
	 * @param userId
	 * @return
	 */
	public List<Object[]> findQuestionFavoritesByUserId(Long userId,Integer page);
	/**
	 * 我的题库中收藏题目总数
	 * @param userId
	 * @return
	 */
	public Long findQuestionFavoritesCountByUserId(Long userId);
}
