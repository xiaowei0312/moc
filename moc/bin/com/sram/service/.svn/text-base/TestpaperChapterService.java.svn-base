package com.sram.service;

import java.util.List;

import com.sram.entity.TestpaperChapter;
/**
 * 试卷章节
 * @author Administrator
 *
 */
public interface TestpaperChapterService  extends BaseService<TestpaperChapter, Long>{
	
	/**
	 * 根据试卷ID查询试卷的章节列表
	 * @param testpaperId
	 * @return
	 */
	public List<TestpaperChapter> findTestpaperChapterListByTestpaperId(Long testpaperId);
	
	
	public List<TestpaperChapter> findTestpaperChapterByUserAndTestpaper(Long testpaperId,Long userId);
}
