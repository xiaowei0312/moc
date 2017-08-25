package com.sram.service;

import java.util.List;

import com.sram.entity.TestpaperItem;
/**
 * 试卷条目
 * @author Administrator
 *
 */
public interface TestpaperItemService extends BaseService<TestpaperItem, Long> {
	public boolean saveItem(Long testpaperId,String... testpaperItemStr);
	public TestpaperItem find(Long testpaperChapterId,Long parentQuestionID);
	public List<TestpaperItem> findListBytestpaperId(Long testpaperId);
}
