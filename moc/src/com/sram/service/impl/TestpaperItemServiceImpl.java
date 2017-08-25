package com.sram.service.impl;

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sram.Constants.QuestionType;
import com.sram.dao.TestpaperChapterDao;
import com.sram.dao.TestpaperDao;
import com.sram.dao.TestpaperItemDao;
import com.sram.entity.Question;
import com.sram.entity.Testpaper;
import com.sram.entity.TestpaperChapter;
import com.sram.entity.TestpaperItem;
import com.sram.service.TestpaperItemService;
import com.sram.service.TestpaperService;
@Service("testpaperItemServiceImpl")
public class TestpaperItemServiceImpl extends BaseServiceImpl<TestpaperItem, Long> implements TestpaperItemService{
	@Resource(name = "testpaperItemDaoImpl")
	private TestpaperItemDao testpaperItemDao;
	
	@Resource(name = "testpaperDaoImpl")
	private TestpaperDao testpaperDao;
	
	@Resource(name = "testpaperChapterDaoImpl")
	private TestpaperChapterDao testpaperChapterDao;
	
	@Resource(name = "testpaperItemDaoImpl")
	public void setBaseDao(TestpaperItemDao testpaperItemDao) {
		super.setBaseDao(testpaperItemDao);
	}
	
	public List<TestpaperItem> findAll() {
		return testpaperItemDao.findAll();
	}

	public List<TestpaperItem> findParent(TestpaperItem testpaperItem) {
		return testpaperItemDao.findParent(testpaperItem);
	}

	public List<TestpaperItem> findTestpaperItem(Testpaper testpaper) {
		return testpaperItemDao.findTestpaperItem(testpaper);
	}

	public List<TestpaperChapter> findTestpaperChapter(
			TestpaperItem testpaperItem) {
		return testpaperItemDao.findTestpaperChapter(testpaperItem);
	}

	public List<Testpaper> findTestpaper(TestpaperItem testpaperItem) {
		return testpaperItemDao.findTestpaper(testpaperItem);
	}
	public List<Question> findQuestion(TestpaperItem testpaperItem) {
		return null;
	}

	public boolean saveItem(Long testpaperId, String... testpaperItemStr) {
		//${testpaperChapter.id}-${testpaperItem.id}-${testpaperItem.question.id}-${testpaperItem.questionType}-${testpaperItem.score}">
		List<TestpaperChapter> chapters = testpaperChapterDao.findTestpaperChapterListByTestpaperId(testpaperId); 
		Testpaper testpaper2 = testpaperDao.find(testpaperId);
		List<TestpaperItem> oldItems=this.findListBytestpaperId(testpaperId); 
		
		Float countScore=0f;
		List<TestpaperItem> newItems= new ArrayList<TestpaperItem>(); 
		if(testpaperItemStr!=null){
			for (int i = 0; i < testpaperItemStr.length; i++) {
				String[] item = testpaperItemStr[i].split("-");
				//非子题目
				if(item.length==6){
					TestpaperItem testpaperItem = new TestpaperItem();
					
					//构建试卷对象
					Testpaper testpaper = new Testpaper();
					testpaperItem.setTestpaper(testpaper);
					testpaper.setId(testpaperId);
					
					//构建章节对象
					TestpaperChapter testpaperChapter = new TestpaperChapter();
					testpaperChapter.setId(Long.valueOf(item[0]));
					
					//题目
					Question question = new Question();
					question.setId(Long.valueOf(item[2]));
					if(!item[1].equals("")){
					   testpaperItem.setId(Long.valueOf(item[1]));//设置ID
					}
					testpaperItem.setTestpaper(testpaper);//设置试卷
					testpaperItem.setTestpaperChapter(testpaperChapter);//设置章节
					testpaperItem.setQuestion(question);//设置题目
					testpaperItem.setQuestionType(QuestionType.valueOf(item[3]));//题目类型
					testpaperItem.setScore(Float.valueOf(item[4]));//分数
					testpaperItem.setOrder(i);
					testpaperItem.setMissScore(Float.valueOf(item[5]));//漏选分数，除了多选题其他应为0
					newItems.add(testpaperItem);
				}
				//子题
				if(item.length==7){
					TestpaperItem testpaperItem = new TestpaperItem();
					
					//构建试卷对象
					Testpaper testpaper = new Testpaper();
					testpaperItem.setTestpaper(testpaper);
					testpaper.setId(testpaperId);
					
					//构建章节对象
					TestpaperChapter testpaperChapter = new TestpaperChapter();
					testpaperChapter.setId(Long.valueOf(item[0]));//章节ID
					
					//题目
					Question question = new Question();
					testpaperItem.setParentQuestionID(Long.valueOf(item[2]));//父条目题
					question.setId(Long.valueOf(item[3]));//子条目题
					if(!item[1].equals("")){
					   testpaperItem.setId(Long.valueOf(item[1]));//设置条目ID
					}
					// value='"+chapterId+"-"testpaperItem.id"-"+questionID+"-"+children_id+"-"+children_type+"-0'>";
					//'${testpaperChapter.id}-${testpaperItem.id}-${testpaperItem.question.id}-${childrenItem.question.id}-${childrenItem.question.questionType}-${childrenItem.question.score}'>
					testpaperItem.setTestpaper(testpaper);//设置试卷
					testpaperItem.setTestpaperChapter(testpaperChapter);//设置章节
					testpaperItem.setQuestion(question);//设置题目
					testpaperItem.setQuestionType(QuestionType.valueOf(item[4]));//题目类型
					testpaperItem.setScore(Float.valueOf(item[5]));//分数
					testpaperItem.setMissScore(Float.valueOf(item[6]));
					testpaperItem.setOrder(i);
					newItems.add(testpaperItem);
				}
			}
		}
		
		List<TestpaperItem> addItems= new ArrayList<TestpaperItem>(newItems); 
		List<TestpaperItem> deleteItems= new ArrayList<TestpaperItem>(oldItems); 
		addItems.removeAll(oldItems);//增加
		deleteItems.removeAll(newItems);//删除
		newItems.retainAll(oldItems);//修改
		for (TestpaperItem testpaperItem : addItems) {
			if(testpaperItem.getParentQuestionID()==null){
				countScore+=testpaperItem.getScore();
			}
			if(testpaperItem.getParentQuestionID()!=null){
				TestpaperItem testpaperItem2 = this.find(testpaperItem.getTestpaperChapter().getId(), testpaperItem.getParentQuestionID());
				testpaperItem.setParent(testpaperItem2);
				this.update(testpaperItem2);
				testpaperItem.setTestpaperChapter(null);
			}
			this.save(testpaperItem);
		}
		for (TestpaperItem testpaperItem : newItems) {
			if(testpaperItem.getParentQuestionID()==null){
				countScore+=testpaperItem.getScore();
			}
			if(testpaperItem.getParentQuestionID()!=null){
				TestpaperItem testpaperItem2 = this.find(testpaperItem.getTestpaperChapter().getId(), testpaperItem.getParentQuestionID());
				testpaperItem.setParent(testpaperItem2);
				this.update(testpaperItem2);
				testpaperItem.setTestpaperChapter(null);
			}
			this.update(testpaperItem);
		}
		List<TestpaperItem> tempdeleteItems= new ArrayList<TestpaperItem>();
		for (TestpaperItem testpaperItem : deleteItems) {
			if(testpaperItem.getParent()==null){
				tempdeleteItems.add(testpaperItem);
				continue;
			}
			this.delete(testpaperItem);
		}
		for (TestpaperItem testpaperItem : tempdeleteItems) {
			this.delete(testpaperItem);
		}
		testpaper2.setItemCount(Integer.parseInt(testpaperDao.findTestpaperItemCount(testpaperId).toString()));
		testpaper2.setScore(countScore);
		testpaperDao.merge(testpaper2);
		return true;
	}

	public TestpaperItem find(Long testpaperChapterId, Long parentQuestionID) {
		return testpaperItemDao.find(testpaperChapterId, parentQuestionID);
	}

	public List<TestpaperItem> findListBytestpaperId(Long testpaperId) {
		return testpaperItemDao.findListBytestpaperId(testpaperId);
	}
}
