package com.sram.service;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.Constants.TestpaperType;
import com.sram.entity.OutlineCategory;
import com.sram.entity.TestpaperResult;
/**
 * 试卷结果
 * @author Administrator
 *
 */
public interface TestpaperResultService extends BaseService<TestpaperResult, Long> {
	public Page<TestpaperResult> findPage(Pageable pageable, String testpaperType, String testpaperStatu, String memberName);
	public TestpaperResult findTestpaperResultById(Long testpaperResultId);
	public List<TestpaperResult> findTestpaperResult(Long outlineCategoryID,Long userId);
	public TestpaperResult findTestpaperWithTestpaper(Long testpaperResultId);
	
	/**
	 * 通过考卷的ID，设置试卷的交卷时间
	 * @param testpaperResultId
	 */
	public void udpateTestpaperRulstEndTime(Long testpaperResultId);
	/**
	 * 我的练习中练习所有记录,分页
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
	
	/**
	 * <p>功能:查找当前用户在当前顶级大纲下的所有组卷模考和真题模考的成绩</p> 
	 * @author 杨楷
	 * @date 2015年4月19日 上午10:59:57 
	 * @param userId
	 * @param rootOutLineCategory 顶级大纲id
	 * @return
	 */
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
	/**
	 * <p>功能:查找所有碰过这个大纲的会员总数</p> 
	 * @author 杨楷
	 * @date 2015年4月21日 上午9:57:19 
	 * @param outlineCategoryID
	 * @return
	 */
	public int findMemberCountByOutlineCategory(Long outlineCategoryID);
	/**
	 * <p>功能:查找所有碰过这个大纲的会员平均答题量和平均分</p> 
	 * @author 杨楷
	 * @date 2015年4月21日 下午4:55:36 
	 * @param outlineCategoryID
	 * @return
	 */
	public Double[] findAvgCountByOutlineCategory(Long outlineCategoryID);
	
	/**
	 * <p>功能:查看我的预测分</p> 
	 * @author 杨楷
	 * @date 2015年4月21日 下午6:13:41 
	 * @param userId
	 * @param rootOutlineCategory
	 * @return
	 */
	public double findAvgScoreByUserIdAndRootOutlineCategory(Long userId,Long rootOutlineCategory);
	/**
	 * <p>功能:查找当前用户预测分</p> 
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
	
	/**
	 * 
	 * @param pageable
	 * @param id
	 * @param id2
	 * @return
	 */
	public Page<TestpaperResult> findTestpaperPage(Pageable pageable, Long outlineCategoryId,
			Long userId);
}
