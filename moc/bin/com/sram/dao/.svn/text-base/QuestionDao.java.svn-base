package com.sram.dao;

import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.Constants.Difficulty;
import com.sram.Constants.QuestionType;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Question;
import com.sram.entity.TestpaperItemResult;
public interface QuestionDao extends BaseDao<Question, Long>{
	/**
	 * 查找题目分页
	 *      题目种类
	 */
	Page<Question> findPage(Pageable pageable, String stem,
			String questionType, String difficulty, String IdTreePath);
	
	public List<Question> findAll();

	/**
	 * outlineCategory类别下是否有question
	 * @param outlineCategories
	 */
	Boolean hasQuestionByOutlineCategory(List<OutlineCategory> categories);
	/**
	 * 根据材料ID查询该材料题的所有子题
	 * @param id
	 * @return
	 */
	public List<Question> findChildren(Question question); 
	
	/**
	 * 根据大纲ID查询所有题目及子大纲的所有题目，并且不包的含题目不显示
	 * @param outlineCategoryId
	 * @param idStr 不包的含题目id，如：1,2,4
	 * @return
	 */
	public List<Question> findAllQuestionOfChild(Long outlineCategoryId,String idStr,String questionType,String stem,Integer page);
	public Long findAllQuestionOfChildCount(Long outlineCategoryId,String idStr,String questionType,String stem);
	public void updateTreePath(String oldTreePath, String newTreePath);
	public List<Long> findIds(Long outlineCategoryId,Difficulty difficulty,boolean isExcludeChildren,QuestionType... questionTypes);
	public List<Question> findMaterialChildren(Long parentId,QuestionType...questionTypes);
	public List<Long> findIdsIgnoreIds(Long outlineCategoryId,Integer[] questionTypes,Long...ids);
	/**
	 * <p>功能:根据id查找试题list</p> 
	 * @author 杨楷
	 * @date 2015-4-8 上午10:01:30 
	 * @param ids
	 * @return
	 */
	public List<Question> findQuestions(Long...ids);
	/**
	 * 查询错题
	 * @param outlineCategoryId
	 * @param userId
	 * @return
	 */
	public List<Question> findWrongQuestions(Long outlineCategoryId, Long userId);
	public Question findByKeyCode(String keyCode);
}
