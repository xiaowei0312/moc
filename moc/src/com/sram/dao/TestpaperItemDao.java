package com.sram.dao;

import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.Question;
import com.sram.entity.Testpaper;
import com.sram.entity.TestpaperChapter;
import com.sram.entity.TestpaperItem;

public interface TestpaperItemDao extends BaseDao<TestpaperItem, Long> {
	/**
	 * 查找试卷条目分页
	 * 试卷种类
	 */
	Page<Testpaper> findPage(Pageable pageable, String stem,
			String testpaperType, String difficulty, String IdTreePath);
	/**
	 * 查询所有试卷条目
	 * @return
	 */
	public List<TestpaperItem> findAll();
	/**
	 * 根据试卷条目查询父类
	 * @return
	 */
	public List<TestpaperItem> findParent(TestpaperItem testpaperItem);
	/**
	 * 根据试卷查询试卷条目
	 * @param id
	 * @return
	 */
	public List<TestpaperItem> findTestpaperItem(Testpaper testpaper); 
	/**
	 * 根据试卷条目查询试卷章节
	 * @param id
	 * @return
	 */
	public List<TestpaperChapter> findTestpaperChapter(TestpaperItem testpaperItem); 
	/**
	 * 根据试卷条目查询试卷
	 * @param id
	 * @return
	 */
	public List<Testpaper> findTestpaper(TestpaperItem testpaperItem); 
	/**
	 * 根据试卷章节查询题目
	 * @param id
	 * @return
	 */
	public List<Question> findQuestion(TestpaperItem testpaperItem); 
	
	/**
	 * 根据章节ID和题目ID查询试卷条目
	 * @param testpaperId
	 * @return
	 */
	public TestpaperItem find(Long testpaperChapterId, Long parentQuestionID);
	
	/**
	 * 根据试卷ID查询题目列表
	 * @param testpaperId
	 * @return
	 */
	public List<TestpaperItem> findListBytestpaperId(Long testpaperId);
	
	public List<TestpaperItem> findChildren(Long parentId);
	
	/**
	 * <p>功能:根据id查找testpaperItem对象leftJoin题目</p> 
	 * @author 杨楷
	 * @date 2015年5月12日 下午3:48:47 
	 * @return
	 */
	public List<TestpaperItem> findTestpaperItemsWithQuestion(Long...ids);
	
}
