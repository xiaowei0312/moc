package com.sram.dao;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.Question;
import com.sram.entity.Testpaper;
import com.sram.entity.TestpaperChapter;

public interface TestpaperChapterDao extends BaseDao<TestpaperChapter, Long> {
	
	/**
	 * 根据试卷ID查询试卷的章节列表
	 * @param testpaperId
	 * @return
	 */
	public List<TestpaperChapter> findTestpaperChapterListByTestpaperId(Long testpaperId);
	/**
	 * 查找试卷章节分页
	 * 试卷种类
	 */
	Page<Testpaper> findPage(Pageable pageable, String stem,
			String testpaperType, String difficulty, String IdTreePath);
	/**
	 * 查询所有试卷
	 * @return
	 */
	public List<TestpaperChapter> findAll();
	/**
	 * 根据试卷查询试卷章节
	 * @param testpaper
	 * @return
	 */
	public List<TestpaperChapter> findTestpaperChapter(Testpaper testpaper); 
	/**
	 * 根据试卷章节查询试卷
	 * @param testpaper
	 * @return
	 */
	public List<Testpaper> findTestpaper(TestpaperChapter testpaperChapter);
	
	public TestpaperChapter findTestpaperChapterWithItem(Long chapterId);
	
	public List<TestpaperChapter> findTestpaperChapterByUserAndTestpaper(Long testpaperId,Long userId);
}
