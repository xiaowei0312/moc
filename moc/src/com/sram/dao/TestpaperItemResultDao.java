package com.sram.dao;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Question;
import com.sram.entity.Testpaper;
import com.sram.entity.TestpaperItemResult;
import com.sram.entity.TestpaperResult;

public interface TestpaperItemResultDao extends BaseDao<TestpaperItemResult, Long> {
	/**
	 * 查找试卷题目做题结果分页
	 * 试卷种类
	 */
	Page<Testpaper> findPage(Pageable pageable, String stem,
			String testpaperType, String difficulty, String IdTreePath);
	/**
	 * 查询所有卷题目做题结果
	 * @return
	 */
	public List<TestpaperItemResult> findAll();
	/**
	 * 根据试卷ID查询卷题目做题结果
	 * @return
	 */
	public List<TestpaperItemResult> findTestpaperItemResult(Testpaper testpaper);
	/**
	 * 根据问题ID查询卷题目做题结果
	 * @return
	 */
	public TestpaperItemResult findTestpaperItemResult(Question question);
	/**
	 * 根据试卷结果查询卷题目做题结果
	 * @return
	 */
	public List<TestpaperItemResult> findTestpaperItemResult(TestpaperResult testpaperResult);
	/**
	 * <p>功能:插入试题结果</p> 
	 * @author 杨楷
	 * @date 2015-4-7 下午05:07:24 
	 * @param status
	 * @param answer
	 * @param testpaperItemId
	 * @param testpaperResultId
	 */
	public void updateResult(TestpaperItemResult testpaperItemResult);
	/**
	 * 根据大纲ID,用户ID查询错误题目
	 * @param outlineCategoryID
	 * @return
	 */
	public  List<Object[]> findWrongTestpaperItemResults(Long outlineCategoryId,Long userId);
	
	/**
	 * <p>功能:查找根据考卷id查找条目答案</p> 
	 * @author 杨楷
	 * @date 2015-4-9 下午03:41:12 
	 * @param testpaperResultId
	 * @return
	 */
	public List<TestpaperItemResult> findByTestpaperResultId (Long testpaperResultId);
	
	/**
	 * 根据testpaperResultId 和userId 查询 涉及考点
	 * @param outlineCategoryId 大纲ID
	 * @param testpaperResultId 考卷ID
	 * @param userId 用户ID
	 * @return
	 */
	public  List<Object[]> findTestpaperItemResults(Long outlineCategoryId,Long testpaperResultId,Long userId);
	/**
	 * 根据大纲ID和userId 查询 涉及考点，能力图表
	 * @param outlineCategoryId 大纲ID
	 * @param userId 用户ID
	 * @return
	 */
	public  List<Object[]> findTestpaperItemResults(Long outlineCategoryId,Long userId);
	/**
	 * 我的题目---根据用户ID查询所有错误题目
	 * @param userId
	 * @return
	 */
	public List<Object[]> findWrongTestpaperItemResultsByUserId(Long userId,Integer page);
	/**
	 * 我的题目---根据用户ID查询所有错误题目总数
	 * @param userId
	 * @return
	 */
	public Long findWrongTestpaperItemResultsCountByUserId(Long userId);
	/**
	 * <p>功能:更新答案</p> 
	 * @author 杨楷
	 * @date 2015年5月8日 上午11:43:32 
	 * @param testpaperResultId
	 * @param testpaeprItemId
	 * @param jsonUserAnswer
	 */
	public void updateUserAnswer(Long testpaperResultId,Long testpaeprItemId,String jsonUserAnswer);
}
