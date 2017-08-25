package com.sram.dao;
import java.util.List;
import java.util.Map;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Question;
import com.sram.entity.QuestionFavorite;
public interface QuestionFavoriteDao extends BaseDao<QuestionFavorite, Long> {
	/**
	 * 查找收藏题目分页
	 */
	Page<QuestionFavorite> findPage(Pageable pageable);
	/**
	 * 查询所有收藏题目
	 * @return
	 */
	public List<QuestionFavorite> findAll();
	/**
	 * 根据收藏题目ID查询收藏的题目
	 * @param questionId
	 * @return
	 */
	public QuestionFavorite findQuestionFavoriteById(Long questionId);
	/**
	 * 我的练习     根据用户ID和大纲ID查询所有对应收藏的题目的大纲
	 */
	public  List<Object[]> findQuestionFavorites(Long outlineCategoryId,Long userId);
	/**
	 * 根据大纲ID和用户ID查询对应的收藏的题目
	 * @param outlineCategoryId userId
	 * @return
	 */
	public List<Question> findFavoriteQuestions(Long outlineCategoryId,Long userId);
	/**
	 * 根据大纲ID和用户ID查询对应的收藏的题目，List<Long>
	 * @param outlineCategoryId userId
	 * @return
	 */
	public List<Long> findFavorite(Long outlineCategoryId,Long userId);
	/**
	 * 根据题目id和用户id查找该用户的收藏
	 * @param questionId
	 * @param userId
	 * @return
	 */
	public QuestionFavorite findByQuestionIdAndUserId(Long questionId,Long userId);
	
	/**
	 * 取消收藏
	 * @param questionId
	 * @param userId
	 */
	public void removeByQuestionIdAndUserId(Long questionId,Long userId);
	/**
	 * 我的题库中收藏题目
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
