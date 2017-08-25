package com.sram.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.Constants.TestpaperType;
import com.sram.dao.TestpaperItemResultDao;
import com.sram.dao.TestpaperResultDao;
import com.sram.entity.TestpaperResult;
import com.sram.service.TestpaperItemResultService;
import com.sram.service.TestpaperResultService;
@Service("testpaperResultServiceImpl")
public class TestpaperResultServiceImpl extends BaseServiceImpl<TestpaperResult, Long> implements TestpaperResultService{
	@Resource(name = "testpaperResultDaoImpl")
	private TestpaperResultDao testpaperResultDao;
	
	@Resource(name = "testpaperResultDaoImpl")
	public void setBaseDao(TestpaperResultDao testpaperResultDao) {
		super.setBaseDao(testpaperResultDao);
	}
	@Autowired
	private TestpaperItemResultDao testpaperItemResultDao;
	
	public List<TestpaperResult> findAll() {
		return testpaperResultDao.findAll();
	}

	public Page<TestpaperResult> findPage(Pageable pageable,String testpaperType,String testpaperStatu,String memberName) {
		return testpaperResultDao.findPage(pageable,testpaperType,testpaperStatu,memberName);
	}

	public TestpaperResult findTestpaperResultById(Long testpaperResultId) {
		return testpaperResultDao.findTestpaperResultById(testpaperResultId);
	}
	/**
	 * 更具大纲ID查询试卷
	 * @param outlineCategoryID
	 * @return
	 */
	public List<TestpaperResult> findTestpaperResult(Long outlineCategoryID,Long userId) {
		return testpaperResultDao.findTestpaperResult(outlineCategoryID,userId);
	}

	public TestpaperResult findTestpaperWithTestpaper(Long testpaperResultId){
		return this.testpaperResultDao.findTestpaperWithTestpaper(testpaperResultId);
	}

	public List<TestpaperResult> findTestpaperResultByUserId(Long userId,Integer page) {
		return testpaperResultDao.findTestpaperResultByUserId(userId,page);
	}
	public Long findTestpaperResultCountByUserId(Long userId) {
		return testpaperResultDao.findTestpaperResultCountByUserId(userId);
	}

	public void udpateTestpaperRulstEndTime(Long testpaperResultId) {
		testpaperResultDao.udpateTestpaperRulstEndTime(testpaperResultId);
		
	}
	
	public List<Object[]> findScores(Long userId,Long rootOutLineCategory){
		return this.testpaperResultDao.findScores(userId,rootOutLineCategory);
	}

	public int findRanking(Long userId, Long rootOutLineCategory) {
		return testpaperResultDao.findRanking(userId, rootOutLineCategory);
	}

	public int findMemberCountByOutlineCategory(Long outlineCategoryID) {
		return testpaperResultDao.findMemberCountByOutlineCategory(outlineCategoryID);
	}

	public Double[] findAvgCountByOutlineCategory(Long outlineCategoryID) {
		return testpaperResultDao.findAvgCountByOutlineCategory(outlineCategoryID);
	}

	public double findAvgScoreByUserIdAndRootOutlineCategory(Long userId,
			Long rootOutlineCategory) {
		return testpaperResultDao.findAvgScoreByUserIdAndRootOutlineCategory(userId, rootOutlineCategory);
	}

	public int findScoreRanking(Long userId, Long rootOutLineCategory) {
		return testpaperResultDao.findScoreRanking(userId, rootOutLineCategory);
	}

	public List<TestpaperResult> findTestpaperResult(Long outlineCategoryID,
			Long userId, Integer page) {
		return testpaperResultDao.findTestpaperResult(outlineCategoryID, userId, page);
	}

	public Long findTestpaperResultCount(Long outlineCategoryID, Long userId) {
		return testpaperResultDao.findTestpaperResultCount(outlineCategoryID, userId);
	}
	
	/**
	 * <p>功能:查找当前用户在特定大纲下和特定试卷类型下的试卷数量</p> 
	 * @author 杨楷
	 * @date 2015年5月4日21:53:07
	 * @return
	 */
	public int findTestpaperResultCount(Long userId,Long outLineCategoryID,TestpaperType testpaperType){
		return testpaperResultDao.findTestpaperResultCount(userId, outLineCategoryID, testpaperType);
	}

	public Page<TestpaperResult> findTestpaperPage(Pageable pageable,
			Long outlineCategoryId, Long userId) {
		// TODO Auto-generated method stub
		return testpaperResultDao.findTestpaperPage(pageable,outlineCategoryId,userId);
	}

}
