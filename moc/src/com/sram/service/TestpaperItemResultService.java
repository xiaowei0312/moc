package com.sram.service;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.sram.Constants.TestpaperType;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Testpaper;
import com.sram.entity.TestpaperItemResult;
import com.sram.entity.TestpaperResult;
/**
 * 试卷题目做题结果
 * @author Administrator
 *
 */
public interface TestpaperItemResultService extends BaseService<TestpaperItemResult, Long> {
	public void saveItemResults(List<TestpaperItemResult> list,TestpaperResult testpaperResult,TestpaperType testpaperType);
	/**
	 * <p>功能:</p> 
	 * @author 杨楷
	 * @date 2015-4-7 下午03:20:42 
	 * @param testpaper 试卷
	 * @param userId 用户id
	 * @param testpaperResult 考卷对象
	 */
	public void saveDefaultResults(Testpaper testpaper,Long userId,TestpaperResult testpaperResult,Map<Long, Long> questionOutlineCategorysMap);
	/**
	 * 根据大纲ID,用户ID查询错误题目
	 * @param outlineCategoryID
	 * @return
	 */
	public  List<Object[]> findWrongTestpaperItemResults(Long outlineCategoryId,Long userId);
	/**
	 * 我的题库---用户ID查询所有错误题目
	 * @param userId
	 * @return
	 */
	public List<Object[]> findWrongTestpaperItemResultsByUserId(Long userId,Integer page);
	/**
	 * 我的题库---根据用户ID查询所有错误题目总数
	 * @param userId
	 * @return
	 */
	public Long findWrongTestpaperItemResultsCountByUserId(Long userId);
	
	/**
	 * 根据testpaperResultId 和userId 查询 涉及考点
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
	 * <p>功能：更新考卷时间和题目答案</p> 
	 * @author 杨楷
	 * @date 2015年5月8日 下午2:32:38 
	 * @param elapsedTime
	 * @param testpaperResultId
	 * @param testpaperItemId
	 * @param userAnswer
	 * @return
	 */
	public boolean updateResult(String elapsedTime,Long testpaperResultId,Long testpaperItemId,String userAnswer);
}
