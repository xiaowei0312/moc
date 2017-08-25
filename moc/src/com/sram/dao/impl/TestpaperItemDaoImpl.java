package com.sram.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.TestpaperItemDao;
import com.sram.entity.Question;
import com.sram.entity.Testpaper;
import com.sram.entity.TestpaperChapter;
import com.sram.entity.TestpaperItem;
@Repository("testpaperItemDaoImpl")
public class TestpaperItemDaoImpl extends BaseDaoImpl<TestpaperItem, Long> implements TestpaperItemDao {

	public Page<Testpaper> findPage(Pageable pageable, String stem,
			String testpaperType, String difficulty, String IdTreePath) {
		return null;
	}

	public List<TestpaperItem> findAll() {
		return null;
	}

	public List<TestpaperItem> findParent(TestpaperItem testpaperItem) {
		return null;
	}

	public List<TestpaperItem> findTestpaperItem(Testpaper testpaper) {
		return null;
	}

	public List<TestpaperChapter> findTestpaperChapter(
			TestpaperItem testpaperItem) {
		return null;
	}

	public List<Testpaper> findTestpaper(TestpaperItem testpaperItem) {
		return null;
	}

	public List<Question> findQuestion(TestpaperItem testpaperItem) {
		return null;
	}
	
	public List<TestpaperChapter> findTestpaperChapterById(Long testpaperId) {
		String jpql="select tc from TestpaperChapter tc  " +
		" left join fetch tc.testpaperItems item" +
		" left join fetch item.question" +
		" where tc.id=:testpaperId";
		TypedQuery<TestpaperChapter> query = entityManager.createQuery(jpql, TestpaperChapter.class).setFlushMode(FlushModeType.COMMIT).setParameter("testpaperId", testpaperId);
		return query.getResultList();
	}

	public TestpaperItem find(Long testpaperChapterId, Long parentQuestionID) {
		String jpql="select tc from TestpaperItem tc  " +
		" where tc.testpaperChapter.id=:testpaperChapterId "+
		" and  tc.question.id=:parentQuestionID";
		TypedQuery<TestpaperItem> query = 
			entityManager.createQuery(jpql, TestpaperItem.class)
				.setParameter("testpaperChapterId", testpaperChapterId)
				.setParameter("parentQuestionID", parentQuestionID);
		return query.getSingleResult();
	}

	public List<TestpaperItem> findListBytestpaperId(Long testpaperId) {
		String jpql="select tc from TestpaperItem tc  " +
		" where tc.testpaper.id=:testpaperId";
		TypedQuery<TestpaperItem> query = entityManager.createQuery(jpql, TestpaperItem.class).setParameter("testpaperId", testpaperId);
		return query.getResultList();
	}
	
	public List<TestpaperItem> findChildren(Long parentId){
		String jpql="select item from TestpaperItem item where item.parent.id=:parentId";
		TypedQuery<TestpaperItem> typedQuery = entityManager.createQuery(jpql, TestpaperItem.class).setParameter("parentId", parentId);
		return typedQuery.getResultList();
	}
	
	/**
	 * <p>功能:根据id查找testpaperItem对象leftJoin题目</p> 
	 * @author 杨楷
	 * @date 2015年5月12日 下午3:48:47 
	 * @return
	 */
	public List<TestpaperItem> findTestpaperItemsWithQuestion(Long...ids){
		StringBuffer jp = new StringBuffer("select t from TestpaperItem t ");
		jp.append(" left join fetch t.question where 1=1 ");
		if(ids!=null && ids.length>0){
			jp.append(" and t.id in(");
			for (Long id : ids) {
				jp.append(id+",");
			}
			jp=new StringBuffer(jp.substring(0, jp.lastIndexOf(",")));
			jp.append(")");
			return entityManager.createQuery(jp.toString(),TestpaperItem.class).getResultList();
		}
		return new ArrayList<TestpaperItem>();
	}

}
