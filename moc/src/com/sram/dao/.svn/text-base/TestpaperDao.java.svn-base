package com.sram.dao;
import java.util.Date;
import java.util.List;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.entity.Testpaper;
import com.sram.entity.Testpaper.Status;

public interface TestpaperDao extends BaseDao<Testpaper, Long> {
	/**
	 * 查找试卷分页
	 * 试卷种类
	 * @param outlineCategoryId 
	 * @param endDate 
	 * @param beginDate 
	 */
	Page<Testpaper> findPage(Pageable pageable,String testpaperType, Long outlineCategoryId,String beginDateStr, String endDateStr);
	/**
	 * 查询所有试卷
	 * @return
	 */
	public List<Testpaper> findAll();
	
	/**
	 * 根据试卷ID查询试卷对象
	 * @param testpaperId
	 * @return
	 */
	public Testpaper findTestpaperById(Long testpaperId);
	/**
	 * 通过大纲ID和地区ID查询历年真题试卷列表
	 * @param outlineCategoryID
	 * @param areaID
	 * @return
	 */
	public List<Testpaper> findOldexamList(Long outlineCategoryID,Long areaID,Integer page);
	/**
	 * 通过大纲ID和地区ID查询历年真题试卷总数
	 * @param outlineCategoryID
	 * @param areaID
	 * @return
	 */
	public Long findOldexamCount(Long outlineCategoryID,Long areaID);
	
	public Testpaper findTestpaperWithChapters(Long testpaperId);
	
	/**
	 * 根据试卷ID，查找item总数且不包括材料题的题干
	 * @return
	 */
	public Long findTestpaperItemCount(Long testpaperId);
	
	void updateStatusById(Long testPaperId, Status status);
}
