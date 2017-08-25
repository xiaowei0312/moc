package com.sram.service.impl;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.naming.java.javaURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.sram.Constants.QuestionType;
import com.sram.Constants.TestpaperType;
import com.sram.dao.QuestionDao;
import com.sram.dao.TestpaperItemDao;
import com.sram.dao.TestpaperItemResultDao;
import com.sram.dao.TestpaperResultDao;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Question;
import com.sram.entity.Testpaper;
import com.sram.entity.TestpaperChapter;
import com.sram.entity.TestpaperItem;
import com.sram.entity.TestpaperItemResult;
import com.sram.entity.TestpaperItemResult.Status;
import com.sram.entity.TestpaperResult;
import com.sram.service.TestpaperItemResultService;
import com.sram.util.JsonUtils;
import com.sram.util.UnicodeConverter;
@Service("testpaperItemResultServiceImpl")
public class TestpaperItemResultServiceImpl extends BaseServiceImpl<TestpaperItemResult, Long> implements TestpaperItemResultService{
	@Resource(name = "testpaperItemResultDaoImpl")
	private TestpaperItemResultDao testpaperItemResultDao;
	
	@Resource(name = "testpaperItemResultDaoImpl")
	public void setBaseDao(TestpaperItemResultDao testpaperItemResultDao) {
		super.setBaseDao(testpaperItemResultDao);
	}
	@Autowired
	private TestpaperItemDao testpaperItemDao;
	@Autowired
	private TestpaperResultDao testpaperResultDao;
	
	public List<TestpaperItemResult> findAll() {
		return testpaperItemResultDao.findAll();
	}

	public List<TestpaperItemResult> findTestpaperItemResult(Testpaper testpaper) {
		return testpaperItemResultDao.findTestpaperItemResult(testpaper);
	}

	public TestpaperItemResult findTestpaperItemResult(Question question) {
		return testpaperItemResultDao.findTestpaperItemResult(question);
	}

	public List<TestpaperItemResult> findTestpaperItemResult(
			TestpaperResult testpaperResult) {
		return testpaperItemResultDao.findTestpaperItemResult(testpaperResult);
	}
	
	@Transactional
	public void saveItemResults(List<TestpaperItemResult> list,TestpaperResult testpaperResult,TestpaperType testpaperType) {
		testpaperResult.setCheckedTime(new Date());//判卷时间
		testpaperResult.setEndDate(new Date());//结束时间
		testpaperResult.setStatus(com.sram.Constants.Status.finished);//试卷状态
		testpaperResult.setActive(true);//是否提交
		int rightItemCount=0;//正确题数
		float objectiveScore=0f;//客观题得分
		
		Long[] ids =new Long[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ids[i]=list.get(i).getTestpaperItem().getId();
		}
		//获取所有试题
		List<TestpaperItem> testpaperItemList = testpaperItemDao.findTestpaperItemsWithQuestion(ids);
		
		for (TestpaperItemResult testpaperItemResult : list) {
			for (TestpaperItem testpaperItem : testpaperItemList) {
				if (testpaperItemResult.getQuestion().getId().equals(testpaperItem.getQuestion().getId())) {
					testpaperItemResult.setOutlineCategory(testpaperItem.getQuestion().getOutlineCategory());
					if (testpaperItem.getQuestion().getQuestionType()!=QuestionType.material) {
						testpaperItemResult.setStatus(this.correctingExam(testpaperItemResult.getAnswer(), testpaperItem.getQuestion()));
						/**试卷评分开始**/
							if (testpaperItemResult.getStatus()==com.sram.entity.TestpaperItemResult.Status.right) {
								rightItemCount++;
								objectiveScore+=testpaperItem.getScore();
								testpaperItemResult.setScore(testpaperItem.getScore());
							}else if (testpaperItemResult.getStatus()==com.sram.entity.TestpaperItemResult.Status.partRight) {
								objectiveScore+=testpaperItem.getMissScore();
								testpaperItemResult.setScore(testpaperItem.getMissScore());
							}
						break;
					}
				}
			}
			//更新结果
			testpaperItemResultDao.updateResult(testpaperItemResult);
		}
		testpaperResult.setRightItemCount(rightItemCount);
		testpaperResult.setScore(objectiveScore);
		testpaperResult.setObjectiveScore(objectiveScore);
		testpaperResultDao.merge(testpaperResult);
	}
	/**
	 * <p>功能:保存默认结果集</p> 
	 * @author 杨楷
	 * @date 2015-4-7 下午03:20:42 
	 * @version 0.1 为了维护改对象上的当前大纲id 2015年5月12日17:38:50   
	 * @param testpaper 试卷
	 * @param userId 用户id
	 * @param testpaperResult 考卷对象
	 */
	@Transactional
	public void saveDefaultResults(Testpaper testpaper,Long userId,TestpaperResult testpaperResult,Map<Long, Long> questionOutlineCategorysMap){
		Iterator<TestpaperChapter> iterator = testpaper.getTestpaperChapters().iterator();
		while (iterator.hasNext()) {
			TestpaperChapter testpaperChapter = iterator.next();
			Iterator<TestpaperItem> iterator2 = testpaperChapter.getTestpaperItems().iterator();
			while (iterator2.hasNext()) {
				TestpaperItem testpaperItem = iterator2.next();
				//结果明细
				TestpaperItemResult testpaperItemResult = new TestpaperItemResult();
				testpaperItemResult.setUserId(userId);//做题人
				testpaperItemResult.setTestpaper(testpaper);
				testpaperItemResult.setPaperName(testpaper.getName());
				testpaperItemResult.setTestpaperResult(testpaperResult);//考卷ID
				testpaperItemResult.setQuestion(testpaperItem.getQuestion());//题目ID		
				testpaperItemResult.setTestpaperItem(testpaperItem);//考卷条目
				testpaperItemResult.setStatus(Status.none);
				testpaperItemResult.setScore(0f);
				testpaperItemResult.setRootOutlineCategory(testpaperResult.getRootOutlineCategory());
				OutlineCategory currentouOutlineCategory=new OutlineCategory();
				if (questionOutlineCategorysMap!=null) {
					currentouOutlineCategory.setId(questionOutlineCategorysMap.get(testpaperItem.getQuestion().getId()));
				}else {
					currentouOutlineCategory.setId(testpaperItem.getQuestion().getOutlineCategory().getId());
				}
				testpaperItemResult.setOutlineCategory(currentouOutlineCategory);
				if (testpaperItem.getQuestionType()==QuestionType.material) {
					List<TestpaperItem> children = testpaperItemDao.findChildren(testpaperItem.getId());
					for (TestpaperItem testpaperItem2 : children) {
						TestpaperItemResult testpaperItemResult2 = new TestpaperItemResult();
						testpaperItemResult2.setUserId(userId);//做题人
						testpaperItemResult2.setTestpaper(testpaper);
						testpaperItemResult2.setTestpaperResult(testpaperResult);//考卷ID
						testpaperItemResult2.setQuestion(testpaperItem2.getQuestion());//题目ID		
						testpaperItemResult2.setStatus(Status.none);
						testpaperItemResult2.setTestpaperItem(testpaperItem2);
						testpaperItemResult2.setScore(0f);
						testpaperItemResult2.setRootOutlineCategory(testpaperResult.getRootOutlineCategory());
						testpaperItemResult2.setOutlineCategory(testpaperResult.getOutlineCategory());
						testpaperItemResultDao.persist(testpaperItemResult2);
					}
				}
				testpaperItemResultDao.persist(testpaperItemResult);
			}
		}
	}
	
	/**
	 * <p>功能:判断正误</p> 
	 * @author 杨楷
	 * @date 2015-4-8 上午10:29:55 
	 * @param userJsonAnswer
	 * @param question
	 * @return
	 */
	private Status correctingExam(String userJsonAnswer,Question question){
		Status status=null;
		switch (question.getQuestionType()) {
		case single_choice:
			Character[] answers2 = JsonUtils.toObject(question.getAnswer().replaceAll("\"", ""), Character[].class);
			Character[] characters = JsonUtils.toObject(userJsonAnswer, Character[].class);
			if (Arrays.toString(characters).equals(Arrays.toString(answers2))) {
				status=Status.right;
			}else {
				status=Status.wrong;
			}
			break;
		case uncertain_singlechoice:
			Character[] answers3 = JsonUtils.toObject(question.getAnswer().replaceAll("\"", ""), Character[].class);
			Character[] characters1 = JsonUtils.toObject(userJsonAnswer, Character[].class);
			if (Arrays.toString(characters1).equals(Arrays.toString(answers3))) {
				status=Status.right;
			}else {
				status=Status.wrong;
			}
			break;
		case choice:
			Character[] answers = JsonUtils.toObject(question.getAnswer().replaceAll("\"", ""), Character[].class);
			Arrays.sort(answers);
			Character[] userAnswers=null;
			try {
				userAnswers = JsonUtils.toObject(userJsonAnswer, Character[].class);
				Arrays.sort(userAnswers);
			} catch (Exception e) {
				status=Status.wrong;
				break;
			}
			if (answers.length==userAnswers.length) {
				if (Arrays.toString(userAnswers).equals(Arrays.toString(answers))) {
					status=Status.right;//用户答案与标准答案长度和答案完全匹配
				}else {
					//长度相等，字符串不等，错选
					status=Status.wrong;
				}
			}else if (answers.length>userAnswers.length) {
				boolean flag = false;//错选标记
				for (int i = 0; i < userAnswers.length; i++) {
					if (Arrays.binarySearch(answers, userAnswers[i])<0) {
						flag=true;
						break;
					}
				}
				if (flag) {
					status=Status.wrong;//用户答案比标准答案少且有错选
				}else {
					status=Status.partRight;//用户答案比标准答案少且没有错选
				}
			}else {
				status=Status.wrong;//用户答案比标准答案多
			}
			break;
		case uncertain_choice:
			Character[] answers1 = JsonUtils.toObject(question.getAnswer().replaceAll("\"", ""), Character[].class);
			Arrays.sort(answers1);
			Character[] userAnswers1=null;
			try {
				userAnswers1 = JsonUtils.toObject(userJsonAnswer, Character[].class);
				Arrays.sort(userAnswers1);
			} catch (Exception e) {
				status=Status.wrong;
				break;
			}
			if (answers1.length==userAnswers1.length) {
				if (Arrays.toString(userAnswers1).equals(Arrays.toString(answers1))) {
					status=Status.right;//用户答案与标准答案长度和答案完全匹配
				}else {
					//长度相等，字符串不等，错选
					status=Status.wrong;
				}
			}else if (answers1.length>userAnswers1.length) {
				boolean flag = false;//错选标记
				for (int i = 0; i < userAnswers1.length; i++) {
					if (Arrays.binarySearch(answers1, userAnswers1[i])<0) {
						flag=true;
						break;
					}
				}
				if (flag) {
					status=Status.wrong;//用户答案比标准答案少且有错选
				}else {
					status=Status.partRight;//用户答案比标准答案少且没有错选
				}
			}else {
				status=Status.wrong;//用户答案比标准答案多
			}
			break;
		case material:
			status=status.noAnswer;
			break;
		case blanks:
			if (userJsonAnswer.equals(question.getAnswer())) {
				status=Status.right;
			}else {
				status=Status.wrong;
			}
			break;
		case determine:
			if (userJsonAnswer.equals(question.getAnswer())) {
				status=Status.right;
			}else {
				status=Status.wrong;
			}
			break;
		case entry:
			if (userJsonAnswer.equals("[\"\"]")) {
				status=status.noAnswer;
				break;
			}
			String[][] standard = JsonUtils.toObject(question.getAnswer(),String[][].class);
			List<String> standardAnswer=new ArrayList<String>();
			for (String[] strings : standard) {
				standardAnswer.add(Arrays.toString(strings));
			}
			ArrayList<String> userAnswer = JsonUtils.toObject(userJsonAnswer,ArrayList.class);
			if (userAnswer.size()<=1||userAnswer.size()!=standardAnswer.size()) {
				status=Status.wrong;
				break;
			}
			boolean flag=true;
			for (String string : userAnswer) {
				String[] split = string.split(",");
				if(split.length!=3||StringUtils.isBlank(split[0])||StringUtils.isBlank(split[1])||StringUtils.isBlank(split[2])){
					flag=false;
					break;
				}
				try {
					split[2]=String.valueOf(Float.parseFloat(split[2]));
				} catch (NumberFormatException e) {
					flag=false;
                    break;
				}
				if (!standardAnswer.contains(Arrays.toString(split))) {
					flag=false;
                    break;
				};
			}
			if (flag==false) {
				status=Status.wrong;
				break;
			}
			status=Status.right;
			break;
		default:
			status=Status.wrong;
			break;
		}
		return status;
	}
	
	/**
	 * 根据大纲ID,用户ID查询错误题目
	 * @param outlineCategoryID
	 * @return
	 */
	public  List<Object[]> findWrongTestpaperItemResults(Long outlineCategoryId,Long userId){
		return testpaperItemResultDao.findWrongTestpaperItemResults(outlineCategoryId, userId);
		
	}

	/**
	 * 根据testpaperResultId 和userId 查询 涉及考点
	 * @param testpaperResultId 考卷ID
	 * @param userId 用户ID
	 * @return
	 */
	public  List<Object[]> findTestpaperItemResults(Long outlineCategoryId,Long testpaperResultId, Long userId) {
		return this.testpaperItemResultDao.findTestpaperItemResults(outlineCategoryId,testpaperResultId, userId);
	}

	public List<Object[]> findTestpaperItemResults(Long outlineCategoryId,
			Long userId) {
		return this.testpaperItemResultDao.findTestpaperItemResults(outlineCategoryId, userId);
	}

	public List<Object[]> findWrongTestpaperItemResultsByUserId(Long userId,Integer page) {
		return this.testpaperItemResultDao.findWrongTestpaperItemResultsByUserId(userId,page);
	}
	public Long findWrongTestpaperItemResultsCountByUserId(Long userId) {
		return this.testpaperItemResultDao.findWrongTestpaperItemResultsCountByUserId(userId);
	}
	
	/**
	 * <p>功能：更新考卷时间和题目答案</p> 
	 * @author 杨楷
	 * @date 2015年5月8日 下午2:32:38 
	 * @param elapsedTime
	 * @param testpaperResultId
	 * @param testpaperItemId
	 * @param userAnswer
	 * @return
	 */
	@Transactional
	public boolean updateResult(String elapsedTime,Long testpaperResultId,Long testpaperItemId,String userAnswer) {
		try {
			testpaperResultDao.updateUsedTime(testpaperResultId, Integer.valueOf(elapsedTime));
			testpaperItemResultDao.updateUserAnswer(testpaperResultId, testpaperItemId, userAnswer);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
