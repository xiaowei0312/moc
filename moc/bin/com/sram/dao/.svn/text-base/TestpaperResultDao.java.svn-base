package com.sram.dao;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.Constants.TestpaperType;
import com.sram.entity.Question;
import com.sram.entity.Testpaper;
import com.sram.entity.TestpaperItem;
import com.sram.entity.TestpaperResult;

public interface TestpaperResultDao extends BaseDao<TestpaperResult, Long> {
	/**
	 * 查找试卷分页
	 * 试卷种类
	 * @param testpaperType 
	 * @param testpaperStatu 
	 * @param memberName 
	 */
	Page<TestpaperResult> findPage(Pageable pageable, String testpaperType, String testpaperStatu, String memberName);
	/**
	 * 查询所有试卷结果
	 * @return
	 */
	public List<TestpaperResult> findAll();
	/**
	 * 根据试卷ID查询试卷对象
	 * @param testpaperId
	 * @return
	 */
	public TestpaperResult findTestpaperResultById(Long testpaperId);
	
	/**
	 * 通过考卷的ID，设置试卷的交卷时间
	 * @param testpaperResultId
	 */
	public void udpateTestpaperRulstEndTime(Long testpaperResultId);
	
	/**
	 * 更具大纲ID查询试卷
	 * @param outlineCategoryID
	 * @return
	 */
	public List<TestpaperResult> findTestpaperResult(Long outlineCategoryID,Long userId);

	public TestpaperResult findTestpaperWithTestpaper(Long testpaperResultId);
	
	/**
	 * 我的练习中练习所有记录
	 * @param outlineCategoryID
	 * @param userId
	 * @return
	 */
	public List<TestpaperResult> findTestpaperResult(Long outlineCategoryID,Long userId,Integer page);
	/**
	 * 我的练习中练习所有记录总数
	 * @param outlineCategoryID
	 * @param userId
	 * @return
	 */
	public Long findTestpaperResultCount(Long outlineCategoryID,Long userId);
	
	/**
	 * 我的题库中练习所有记录
	 * @param userId
	 * @return
	 */
	public List<TestpaperResult> findTestpaperResultByUserId(Long userId,Integer page);
	/**
	 * 我的题库中练习所有记录总数
	 * @param userId
	 * @return
	 */
	public Long findTestpaperResultCountByUserId(Long userId);
	public List<Object[]> findScores(Long userId,Long rootOutLineCategory);
	
	/**
	 * <p>功能:查找当前用户在当前大纲下与所有用户做题数量相比的排名</p> 
	 * @author 杨楷
	 * @date 2015年4月20日 下午5:50:19 
	 * @param userId
	 * @param rootOutLineCategory
	 * @return
	 */
	public int findRanking(Long userId,Long rootOutLineCategory);
	
	public int findMemberCountByOutlineCategory(Long outlineCategoryID);
	public Double[] findAvgCountByOutlineCategory(Long outlineCategoryID);
	
	public double findAvgScoreByUserIdAndRootOutlineCategory(Long userId,Long rootOutlineCategory);
	/**
	 * <p>功能:查找当前用户预测分排名</p> 
	 * @author 杨楷
	 * @date 2015年4月21日 下午6:36:46 
	 * @param userId
	 * @param rootOutLineCategory
	 * @return
	 */
	public int findScoreRanking(Long userId,Long rootOutLineCategory);
	/**
	 * <p>功能:查找当前用户在特定大纲下和特定试卷类型下的试卷数量</p> 
	 * @author 杨楷
	 * @date 2015年5月4日21:53:07
	 * @return
	 */
	public int findTestpaperResultCount(Long userId,Long outLineCategoryID,TestpaperType testpaperType);
	
	
	Page<TestpaperResult> findTestpaperPage(Pageable pageable,
			Long outlineCategoryId, Long userId);
	/**
	 * <p>功能:更新做卷时间</p> 
	 * @author 杨楷
	 * @date 2015年5月8日 下午2:23:34 
	 * @param testpaperResultId
	 * @param usedTime
	 */
	public void updateUsedTime(Long testpaperResultId,Integer usedTime);
}
