package com.sram.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.TestpaperChapterDao;
import com.sram.entity.Testpaper;
import com.sram.entity.TestpaperChapter;
@Repository("testpaperChapterDaoImpl")
public class TestpaperChapterDaoImpl extends BaseDaoImpl<TestpaperChapter, Long> implements TestpaperChapterDao{

	public Page<Testpaper> findPage(Pageable pageable, String stem,
			String testpaperType, String difficulty, String IdTreePath) {
		return null;
	}

	public List<TestpaperChapter> findAll() {
		return null;
	}

	public List<TestpaperChapter> findTestpaperChapter(Testpaper testpaper) {
		return null;
	}

	public List<Testpaper> findTestpaper(TestpaperChapter testpaperChapter) {
		return null;
	}
	
	public List<TestpaperChapter> findTestpaperChapterListByTestpaperId(Long testpaperId) {
		String jpql = "select o from TestpaperChapter o " +
				"where o.testpaper.id=:testpaperId order by o.order";
		TypedQuery<TestpaperChapter> query = entityManager.createQuery(jpql, TestpaperChapter.class).setParameter("testpaperId", testpaperId);
		return query.getResultList();
	}
	
	public TestpaperChapter findTestpaperChapterWithItem(Long chapterId){
		String jpql ="select c from TestpaperChapter c left join fetch c.testpaperItems  where  c.id=:chapterId";
		TypedQuery<TestpaperChapter> query = entityManager.createQuery(jpql, TestpaperChapter.class).setParameter("chapterId", chapterId);
		return query.getResultList().get(0);
	}
	
	
	public List<TestpaperChapter> findTestpaperChapterByUserAndTestpaper(Long testpaperId,Long userId) {
		String jpql = "select c from TestpaperChapter c " +
		"left join fetch  c.testpaperItems item " +
		"left join fetch  item.children   children" +
		"right join fetch item.testpaperItemResult result"+
				" where c.testpaper.id=:testpaperId and  result.userId=:userId and  result.testpaper.id=:testpaperId" +
				" order by c.order ";
		TypedQuery<TestpaperChapter> query = entityManager.createQuery(jpql, TestpaperChapter.class)
		.setParameter("testpaperId", testpaperId)
		.setParameter("userId", userId)
		.setParameter("testpaperId", testpaperId);
		return query.getResultList();
	}
}
