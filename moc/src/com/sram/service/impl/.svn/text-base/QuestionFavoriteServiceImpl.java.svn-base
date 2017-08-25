package com.sram.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.OutlineCategoryDao;
import com.sram.dao.QuestionDao;
import com.sram.dao.QuestionFavoriteDao;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Question;
import com.sram.entity.QuestionFavorite;
import com.sram.entity.BaseEntity.Save;
import com.sram.service.QuestionFavoriteService;
@Service("questionFavoriteServiceImpl")
public class QuestionFavoriteServiceImpl extends BaseServiceImpl<QuestionFavorite, Long> implements QuestionFavoriteService{
	@Resource(name = "questionFavoriteDaoImpl")
	private QuestionFavoriteDao questionFavoriteDao;
	@Resource(name = "questionDaoImpl")
	private QuestionDao questionDao;
	
	@Resource(name = "questionFavoriteDaoImpl")
	public void setBaseDao(QuestionFavoriteDao questionFavoriteDao) {
		super.setBaseDao(questionFavoriteDao);
	}
	/**
	 * 查询所有收藏的题目
	 */
	public List<QuestionFavorite> findAll() {
		return questionFavoriteDao.findAll();
	}
	/**
	 * 收藏题目分页
	 */
	public Page<QuestionFavorite> findPage(Pageable pageable) {
		return questionFavoriteDao.findPage(pageable);
	}
	/**
	 * 根据收藏题目ID查询对应题目
	 */
	public QuestionFavorite findQuestionFavoriteById(Long questionFavoriteId) {
		return questionFavoriteDao.findQuestionFavoriteById(questionFavoriteId);
	}
	/**
	 * 根据用户ID和大纲ID查询收藏的对应题目的大纲
	 */
	public  List<Object[]> findQuestionFavorites(Long outlineCategoryId,
			Long userId) {
		return questionFavoriteDao.findQuestionFavorites(outlineCategoryId, userId);
	}
	/**
	 * <p>功能: 添加、删除收藏</p> 
	 * @date 2015年4月15日 上午8:52:39 
	 * @param userId
	 * @param questionId
	 * @param status
	 */
	@Transactional
	public void addOrRemove(Long userId,Long questionId,String status){
		if (status.equals("add")) {
			QuestionFavorite favorite = questionFavoriteDao.findByQuestionIdAndUserId(questionId, userId);
			if (favorite!=null) return;
			QuestionFavorite questionFavorite=new QuestionFavorite();
			Question question=questionDao.find(questionId);
			String[] outlines=question.getTreePath().split(",");
			if(outlines.length==0){
				questionFavorite.setOutlineCategory(question.getOutlineCategory());
				questionFavorite.setRootOutlineCategory(question.getOutlineCategory().getId());
			}else{
				questionFavorite.setOutlineCategory(question.getOutlineCategory());
				questionFavorite.setRootOutlineCategory(Long.parseLong(outlines[1]));
			}
			questionFavorite.setQuestion(question);
			questionFavorite.setUserId(userId);
			questionFavoriteDao.persist(questionFavorite);
		}else {
			questionFavoriteDao.removeByQuestionIdAndUserId(questionId, userId);
		}
	}
	/**
	 * 根据大纲ID和用户ID查询对应收藏的题目
	 */
	public List<Question> findFavoriteQuestions(Long outlineCategoryId,Long userId) {
		return questionFavoriteDao.findFavoriteQuestions(outlineCategoryId, userId);
	}
	public List<Object[]> findQuestionFavoritesByUserId(Long userId,Integer page) {
		return questionFavoriteDao.findQuestionFavoritesByUserId(userId,page);
	}
	public Long findQuestionFavoritesCountByUserId(Long userId) {
		return questionFavoriteDao.findQuestionFavoritesCountByUserId(userId);
	}
	public List<Long> findFavorite(Long outlineCategoryId, Long userId) {
		return questionFavoriteDao.findFavorite(outlineCategoryId, userId);
	}

}
