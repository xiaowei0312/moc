package com.sram.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.sram.Constants.Difficulty;
import com.sram.Constants.Status;
import com.sram.Constants.TestpaperType;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.SramException;
import com.sram.Constants.QuestionType;
import com.sram.dao.OutlineCategoryDao;
import com.sram.dao.QuestionDao;
import com.sram.dao.TestpaperChapterDao;
import com.sram.dao.TestpaperDao;
import com.sram.dao.TestpaperItemDao;
import com.sram.dao.TestpaperItemResultDao;
import com.sram.dao.TestpaperResultDao;
import com.sram.entity.GeneratorQuestionConfig;
import com.sram.entity.GeneratorStrategy;
import com.sram.entity.GeneratorStrategy.GeneratorType;
import com.sram.entity.OrderEntity;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Question;
import com.sram.entity.QuestionUploadAnalyseReport;
import com.sram.entity.Testpaper;
import com.sram.entity.TestpaperChapter;
import com.sram.entity.TestpaperItem;
import com.sram.entity.TestpaperItemResult;
import com.sram.entity.TestpaperResult;
import com.sram.service.TestpaperService;
import com.sram.util.JsonUtils;
import com.sram.util.MathUtil;
import com.sram.util.SetSortUtil;
import com.sram.util.UnicodeConverter;
import com.sun.xml.internal.ws.api.server.SDDocument;

@Service("testpaperServiceImpl")
public class TestpaperServiceImpl extends BaseServiceImpl<Testpaper, Long>
		implements TestpaperService {
	@Resource(name = "testpaperDaoImpl")
	private TestpaperDao testpaperDao;
	@Resource(name = "testpaperChapterDaoImpl")
	private TestpaperChapterDao testpaperChapterDao;
	@Resource(name = "testpaperItemDaoImpl")
	private TestpaperItemDao testpaperItemDao;
	@Resource(name = "testpaperItemResultDaoImpl")
	private TestpaperItemResultDao testpaperItemResultDao;
	@Resource(name = "testpaperResultDaoImpl")
	private TestpaperResultDao testpaperResultDao;
	@Autowired
	private QuestionDao questionDao;
	@Autowired
	private OutlineCategoryDao outlineCategoryDao;
	
	@Resource(name = "testpaperDaoImpl")
	public void setBaseDao(TestpaperDao testpaperDao) {
		super.setBaseDao(testpaperDao);
	}

	public List<Testpaper> findAll() {
		return testpaperDao.findAll();
	}
	
	public void saveTestpaper(Testpaper testpaper) {
		super.save(testpaper);
	}

	public Page<Testpaper> findPage(Pageable pageable, String testpaperType,Long outlineCategoryId, String beginDateStr, String endDateStr) {
		return testpaperDao.findPage(pageable, testpaperType,outlineCategoryId,beginDateStr,endDateStr);
	}

	public Testpaper findTestpaperById(Long testpaperId) {
		return testpaperDao.findTestpaperById(testpaperId);
	}
	/**
	 * <p>功能:解析[章节]sheet</p> 
	 * @author 杨楷
	 * @date 2015-3-12 下午07:09:12 
	 * @param multipartFile 待解析的文件
	 * @return 解析成功后返回key为"sheetNames"和"sheetTypes"的String[]，解析失败返回key为"report"的解析报告
	 * @throws Exception
	 */
	public Map<String, Object> getIndex(MultipartFile multipartFile) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		QuestionUploadAnalyseReport report=new QuestionUploadAnalyseReport();
		report.setFileName(multipartFile.getOriginalFilename());
		report.setSheet("章节");
		report.setOk(true);
		POIFSFileSystem fs;
		HSSFWorkbook workBook;
		ArrayList<String> sheetNames = new ArrayList<String>();
		ArrayList<String> sheetTypes = new ArrayList<String>();
		StringBuffer buffer=new StringBuffer();
			fs = new POIFSFileSystem(multipartFile.getInputStream());
			workBook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workBook.getSheet("章节");
			if (sheet == null) {
				report.setOk(false);
				report.setMsg("导入失败：没有找到[章节]工作表\n");
				returnMap.put("report", report);
				return returnMap;
			}
			int rows = sheet.getPhysicalNumberOfRows();
			if (rows > 1) {
				for (int j = 1; j < rows; j++) { // 行循环
					HSSFRow row = sheet.getRow(j);
					if (row != null) {
						HSSFCell nameCell = row.getCell(0);
						HSSFCell typeCell= row.getCell(1);
						if (nameCell==null||StringUtils.isBlank(nameCell.getStringCellValue())) {
							report.setOk(false);
							buffer.append("第"+j+"行，章节名称不能为空");
							continue;
						}
						if (typeCell==null||StringUtils.isBlank(typeCell.getStringCellValue())) {
							report.setOk(false);
							buffer.append("第"+j+"行，章节类型不能为空");
						}else {
							if (!(typeCell.getStringCellValue().equals("选择题")||typeCell.getStringCellValue().equals("填空题")||typeCell.getStringCellValue().equals("判断题")||typeCell.getStringCellValue().equals("简答题")||typeCell.getStringCellValue().equals("材料题"))) {
								report.setOk(false);
								buffer.append("第"+j+"行，章节类型的值不合法");
							}
						}
						if (!report.isOk()) {
							continue;
						}
						sheetNames.add(nameCell.getStringCellValue());
						sheetTypes.add(typeCell.getStringCellValue());
					}
				}
			} else {
				report.setOk(false);
				report.setMsg("导入失败：[章节]工作表为空，无法解析\n");
				returnMap.put("report", report);
				return returnMap;
			}
			if(report.isOk()){
				String[] sheetNamesArray = (String[]) sheetNames.toArray(new String[sheetNames.size()] );
				String[] sheetTypesArray = (String[]) sheetTypes.toArray(new String[sheetTypes.size()]);
				returnMap.put("report", report);
				returnMap.put("sheetNames", sheetNamesArray);
				returnMap.put("sheetTypes", sheetTypesArray);
			}else{
				report.setMsg(buffer.toString());
				returnMap.put("report", report);
			}
			return returnMap;
	}
	/**
	 * 修改时间2015年4月27日16:09:29增加 选项、答案转Unicode
	 * 修改时间2015年5月4日18:14:48 增加试卷条目数量统计（不包含材料题的题干），维护材料题题干的分数
	 * 修改时间2015年5月18日09:48:43 在插入一份试卷之前增加试卷内题目重复判定
	 */
	@Transactional
	public boolean importTestPaper(Testpaper testpaper,OutlineCategory outlineCategory,String[] ChapterNames, List<Question> questionList,List<List<Question>> materList) {
		try {
			List<String> questionKeylList=new ArrayList<String>();
			for (Question question : questionList) {
				if (questionKeylList.contains(question.getKeyCode())) {
					throw new SramException("导入失败[当前试卷存在重复题目]："+question.getStem());
				}else {
					questionKeylList.add(question.getKeyCode());
				}
			}
			for (List<Question> questions : materList) {
				for (Question question : questions) {
					if (questionKeylList.contains(question.getKeyCode())) {
						throw new SramException("导入失败[当前试卷存在重复题目]："+question.getStem());
					}else {
						questionKeylList.add(question.getKeyCode());
					}
				}
			}
 		} catch (SramException e) {
			throw e;
		}
		try {
		//step1 持久化 试卷
		testpaper.setOutlineCategory(outlineCategory);
		testpaperDao.persist(testpaper);
		TestpaperChapter testpaperChapter;
		//声明一个计数器算题目总数itemCount
		int itemCount=0;
		//计算试卷总分数
		float testpapaerScore=0;
		for (int i = 0; i < ChapterNames.length; i++) {
			//声明一个计数器给题目计数order
			int counter=0;
			//step2持久化章节
			testpaperChapter=new TestpaperChapter();
			testpaperChapter.setName(ChapterNames[i]);
			testpaperChapter.setTestpaper(testpaper);
			testpaperChapter.setOrder(i+1);
			testpaperChapterDao.persist(testpaperChapter);
			//存储（选择、填空、判断、简答）
			for (int j = 0; j < questionList.size(); j++) {
				if (questionList.get(j).getSheetName().equals(ChapterNames[i])) {
					counter++;
					Question question = questionList.get(j);
					//step3持久化试题
					try {
						if (question.getMetas()!=null) {
		    				question.setMetas(UnicodeConverter.toEncodedUnicode(question.getMetas(), false));
						}
		    			if (question.getAnswer()!=null) {
		    				question.setAnswer(UnicodeConverter.toEncodedUnicode(question.getAnswer(), false));
		    			}
		    			if (testpaper.getTestpaperType()==TestpaperType.oldexam) {
							question.setOrigin(testpaper.getName());
						}
						questionDao.independentPersist(question);
					} catch (org.hibernate.exception.ConstraintViolationException e) {
						question=questionDao.findByKeyCode(question.getKeyCode());
					}
					//step4持久化试卷条目
					TestpaperItem  testpaperItem=new TestpaperItem();
					testpaperItem.setTestpaper(testpaper);
					testpaperItem.setOrder(counter);
					testpaperItem.setQuestion(question);
					testpaperItem.setScore(question.getScore());
					testpaperItem.setQuestionType(question.getQuestionType());
					testpaperItem.setTestpaperChapter(testpaperChapter);
					testpaperItemDao.persist(testpaperItem);
					itemCount++;
					testpapaerScore+=question.getScore();
				}
			}
				if (materList!=null) {
					//存储材料题
					for (List<Question> material : materList) {
						if (material.get(0).getSheetName().equals(ChapterNames[i])) {
							counter++;
							Question topQuestion = material.get(0);
							topQuestion.setTreePath(outlineCategory.getTreePath());
							topQuestion.setOutlineCategory(outlineCategory);
							//计算材料题的分数
							float materialScore=0f;
							for (int k = 1; k < material.size(); k++) {
								Question materialChildQuestion= material.get(k);
								materialScore+=materialChildQuestion.getScore();
							}
							topQuestion.setScore(materialScore);
							//step3 持久化材料题的题干部分
							try {
								if (testpaper.getTestpaperType()==TestpaperType.oldexam) {
									topQuestion.setOrigin(testpaper.getName());
								}
								questionDao.independentPersist(topQuestion);
							} catch (org.hibernate.exception.ConstraintViolationException e) {
								topQuestion=questionDao.findByKeyCode(topQuestion.getKeyCode());
							}
							//step4持久化试卷条目的父题
							TestpaperItem  topTestpaperItem=new TestpaperItem();
							topTestpaperItem.setTestpaper(testpaper);
							topTestpaperItem.setOrder(counter);
							topTestpaperItem.setQuestion(topQuestion);
							topTestpaperItem.setScore(topQuestion.getScore());
							topTestpaperItem.setQuestionType(topQuestion.getQuestionType());
							topTestpaperItem.setTestpaperChapter(testpaperChapter);
							testpaperItemDao.persist(topTestpaperItem);
							
							for (int j = 1; j < material.size(); j++) {
								Question childQuestion= material.get(j);
								childQuestion.setTreePath(outlineCategory.getTreePath());
								childQuestion.setOutlineCategory(outlineCategory);
								childQuestion.setParent(topQuestion);
								//step3持久化材料题的子题
								try {
									if (childQuestion.getMetas()!=null) {
										childQuestion.setMetas(UnicodeConverter.toEncodedUnicode(childQuestion.getMetas(), false));
									}
					    			if (childQuestion.getAnswer()!=null) {
					    				childQuestion.setAnswer(UnicodeConverter.toEncodedUnicode(childQuestion.getAnswer(), false));
					    			}
					    			if (testpaper.getTestpaperType()==TestpaperType.oldexam) {
					    				childQuestion.setOrigin(testpaper.getName());
									}
									questionDao.independentPersist(childQuestion);
								} catch ( org.hibernate.exception.ConstraintViolationException e) {
									childQuestion=questionDao.findByKeyCode(childQuestion.getKeyCode());
								}
								//step4持久化试卷条目的子题
								TestpaperItem  childTestpaperItem=new TestpaperItem();
								childTestpaperItem.setTestpaper(testpaper);
								childTestpaperItem.setOrder(j);
								childTestpaperItem.setParent(topTestpaperItem);
								childTestpaperItem.setQuestion(childQuestion);
								childTestpaperItem.setScore(childQuestion.getScore());
								childTestpaperItem.setQuestionType(childQuestion.getQuestionType());
//								childTestpaperItem.setTestpaperChapter(testpaperChapter);
								testpaperItemDao.persist(childTestpaperItem);
								materialScore+=childQuestion.getScore();
								itemCount++;
								testpapaerScore+=childQuestion.getScore();
							}
						}
					}
				}
		}
		testpaper.setItemCount(itemCount);
		testpaper.setScore(testpapaerScore);
		testpaperDao.merge(testpaper);
		return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * <p>功能:随机或者按照难度出卷考试</p> 
	 * @author 杨楷
	 * @date 2015-3-24 上午10:30:59 
	 * @version  0.2 修改智能组卷从策略中加载分数 
	 * @version  0.3 修正智能组卷条目上没有题目类型的问题+维护父题的分数 2015年4月30日15:17:45
	 * @param outlineCategoryId
	 * @param testpaper
	 * @return
	 */
	public Testpaper getTestpaper(Long outlineCategoryId,GeneratorStrategy strategy){
		Set<TestpaperChapter> testpaperChapters=new HashSet<TestpaperChapter>();
		float totalScore=0f;
		Set<GeneratorQuestionConfig> questionConfigs = strategy.getGeneratorQuestionConfigs();
		int chapterCount=0;
		//随机生卷开始
		if (strategy.getGeneratorType()==GeneratorType.RANDOM) {
			for (GeneratorQuestionConfig generatorQuestionConfig : questionConfigs) {
				int generatorQuestioncount = generatorQuestionConfig.getCount();
				//取出所有符合条件题目的id集合然后随机抽取
				List<Long> ids = questionDao.findIds(outlineCategoryId,null,true,generatorQuestionConfig.getQuestionType());
				if (ids.size()==0) {
					continue;
				}
				if (generatorQuestioncount<ids.size()) {
					int[] randomIndexs = MathUtil.randomCommon(0, ids.size(),generatorQuestioncount);
					List<Long> temp=new ArrayList<Long>();
		 			for (int i = 0; i < randomIndexs.length; i++) {
						temp.add(ids.get(randomIndexs[i]));
					}
					ids=temp;
				}
				TestpaperChapter testpaperChapter=new TestpaperChapter();
				testpaperChapter.setName(generatorQuestionConfig.getChapterName());
				Set<TestpaperItem> testpaperItems=new HashSet<TestpaperItem>();
				//用随机抽取到的题目去数据库中获取题
				for (int i = 0; i < ids.size(); i++) {
					TestpaperItem testpaperItem=new TestpaperItem();
					Question question = this.questionDao.find(ids.get(i));
					testpaperItem.setScore(generatorQuestionConfig.getScore());
					if (question.getQuestionType()==QuestionType.choice||question.getQuestionType()==QuestionType.uncertain_choice) {
						testpaperItem.setMissScore(generatorQuestionConfig.getMissScore());
					}
					testpaperItem.setQuestion(question);
					testpaperItem.setQuestionType(question.getQuestionType());
					testpaperItem.setOrder(i+1);
					totalScore+=generatorQuestionConfig.getScore();
					testpaperItems.add(testpaperItem);
					if (question.getQuestionType()==QuestionType.material) {
						List<Question> children = questionDao.findChildren(question);
						totalScore-=generatorQuestionConfig.getScore();
						testpaperItem.setScore(children.size()*generatorQuestionConfig.getScore());
						totalScore+=testpaperItem.getScore();
						int childOrder=1;
						for (Question child : children) {
							TestpaperItem childTestpaperItem=new TestpaperItem();
							childTestpaperItem.setQuestion(child);
							childTestpaperItem.setQuestionType(child.getQuestionType());
							childTestpaperItem.setScore(generatorQuestionConfig.getScore());
							if (child.getQuestionType()==QuestionType.choice||child.getQuestionType()==QuestionType.uncertain_choice) {
								childTestpaperItem.setMissScore(generatorQuestionConfig.getMissScore());
							}
							childTestpaperItem.setParent(testpaperItem);
							childTestpaperItem.setOrder(childOrder);
							childOrder++;
							testpaperItems.add(childTestpaperItem);
						}
					}
				}
				testpaperChapter.setTestpaperItems(testpaperItems);
				testpaperChapter.setOrder(chapterCount);
				chapterCount++;
				testpaperChapters.add(testpaperChapter);
			}
		}else {
			/**难度系数生成试卷开始**/
			//加载难度策略
			HashMap jsMap = JsonUtils.toObject(strategy.getDifficulty(), HashMap.class);
			String easyDegree=((String)jsMap.get("easy")).replaceAll("%", "");
			String normalDegree=((String)jsMap.get("normal")).replaceAll("%", "");
			String hardDegree=((String)jsMap.get("hard")).replaceAll("%", "");
			for (GeneratorQuestionConfig generatorQuestionConfig : questionConfigs) {
				int generatorQuestioncount = generatorQuestionConfig.getCount();
				int[] questionDifficultyCount = MathUtil.calcCount(generatorQuestioncount,Double.valueOf(easyDegree)/100, Double.valueOf(normalDegree)/100, Double.valueOf(hardDegree)/100);
				Set<TestpaperItem> testpaperItems=new HashSet<TestpaperItem>();
				Difficulty difficulty;
				int shortfall=0;//某种难度缺失的数量
				int questionCount=0;//条目（题目）编号
				List<Long> ignoreList=new ArrayList<Long>();//已经加入进来的题目，再去数据库中取题的时候要排除这些id
				for (int i = 0; i < questionDifficultyCount.length; i++) {//三种难度的题目循环
					if(questionDifficultyCount[i]==0) continue;
					if (i==Difficulty.easy.ordinal()) {
						difficulty=Difficulty.easy;
					}else if (i==Difficulty.normal.ordinal()) {
						difficulty=Difficulty.normal;
					}else {
						difficulty=Difficulty.hard;
					}
					//取出所有符合条件题目的id集合
					List<Long> ids = questionDao.findIds(outlineCategoryId,difficulty,true,generatorQuestionConfig.getQuestionType());
					if (ids.size()==0) {//某种难度的题目数量为0,则把该类型的题目数量累加到缺失题目数量中去
						shortfall+=questionDifficultyCount[i];
						continue;
					}
					if (questionDifficultyCount[i]<ids.size()) {
						int[] randomIndexs = MathUtil.randomCommon(0, ids.size(),questionDifficultyCount[i]);
						List<Long> temp=new ArrayList<Long>();
			 			for (int k = 0; k < randomIndexs.length; k++) {
							temp.add(ids.get(randomIndexs[k]));
						}
						ids=temp;
					}else if (questionDifficultyCount[i]>ids.size()) {
						shortfall+=(generatorQuestioncount-ids.size());
					}
					for (int j = 0; j < ids.size(); j++) {//每种难度的题型循环
						TestpaperItem testpaperItem=new TestpaperItem();
						Question question = this.questionDao.find(ids.get(j));
						testpaperItem.setScore(generatorQuestionConfig.getScore());
						if (question.getQuestionType()==QuestionType.choice||question.getQuestionType()==QuestionType.uncertain_choice) {
							testpaperItem.setMissScore(generatorQuestionConfig.getMissScore());
						}
						testpaperItem.setQuestion(question);
						testpaperItem.setQuestionType(question.getQuestionType());
						testpaperItem.setOrder(questionCount+1);
						questionCount++;
						totalScore+=generatorQuestionConfig.getScore();
						ignoreList.add(question.getId());
						testpaperItems.add(testpaperItem);
						if (question.getQuestionType()==QuestionType.material) {
							List<Question> children = questionDao.findChildren(question);
							totalScore-=generatorQuestionConfig.getScore();
							testpaperItem.setScore(children.size()*generatorQuestionConfig.getScore());
							totalScore+=testpaperItem.getScore();
							int childOrder=1;
							for (Question child : children) {
								TestpaperItem childTestpaperItem=new TestpaperItem();
								childTestpaperItem.setQuestion(child);
								childTestpaperItem.setQuestionType(child.getQuestionType());
								childTestpaperItem.setScore(generatorQuestionConfig.getScore());
								if (child.getQuestionType()==QuestionType.choice||child.getQuestionType()==QuestionType.uncertain_choice) {
									childTestpaperItem.setMissScore(generatorQuestionConfig.getMissScore());
								}
//								testpaperItem.setQuestion(question);
								childTestpaperItem.setParent(testpaperItem);
								childTestpaperItem.setOrder(childOrder);
								childOrder++;
								testpaperItems.add(childTestpaperItem);
							}
						}
					}
				}
				//如果某个难度的题目数量不够则再随机去题库中取出不限制难度的题目来进行补充
				if (testpaperItems.size()<generatorQuestioncount&&ignoreList.size()!=0) {
					Long[] array = new Long[ignoreList.size()];
					for (int j = 0; j < ignoreList.size(); j++) {
						array[j]=ignoreList.get(j);
					}
					//取出所有符合条件题目的id集合然后随机抽取
					Integer[] questionTypes={generatorQuestionConfig.getQuestionType().ordinal()};
					List<Long> ids = questionDao.findIdsIgnoreIds(outlineCategoryId,questionTypes,array);
					if (shortfall<ids.size()) {
						int[] randomIndexs = MathUtil.randomCommon(0, ids.size(),shortfall);
						List<Long> temp=new ArrayList<Long>();
			 			for (int i = 0; i < randomIndexs.length; i++) {
							temp.add(ids.get(randomIndexs[i]));
						}
						ids=temp;
					}
					//用随机抽取到的题目去数据库中获取题
					for (int i = 0; i < ids.size(); i++) {
						TestpaperItem testpaperItem=new TestpaperItem();
						Question question = this.questionDao.find(ids.get(i));
						testpaperItem.setScore(generatorQuestionConfig.getScore());
						if (question.getQuestionType()==QuestionType.choice||question.getQuestionType()==QuestionType.uncertain_choice) {
							testpaperItem.setMissScore(generatorQuestionConfig.getMissScore());
						}
						testpaperItem.setQuestion(question);
						testpaperItem.setQuestionType(question.getQuestionType());
						testpaperItem.setOrder(i+questionCount);
						totalScore+=generatorQuestionConfig.getScore();
						testpaperItems.add(testpaperItem);
						if (question.getQuestionType()==QuestionType.material) {
							List<Question> children = questionDao.findChildren(question);
							totalScore-=generatorQuestionConfig.getScore();
							testpaperItem.setScore(children.size()*generatorQuestionConfig.getScore());
							totalScore+=testpaperItem.getScore();
							int childOrder=1;
							for (Question child : children) {
								TestpaperItem childTestpaperItem=new TestpaperItem();
								childTestpaperItem.setQuestion(child);
								childTestpaperItem.setQuestionType(child.getQuestionType());
								childTestpaperItem.setScore(generatorQuestionConfig.getScore());
								if (child.getQuestionType()==QuestionType.choice||child.getQuestionType()==QuestionType.uncertain_choice) {
									childTestpaperItem.setMissScore(generatorQuestionConfig.getMissScore());
								}
								childTestpaperItem.setParent(testpaperItem);
								childTestpaperItem.setOrder(childOrder);
								childOrder++;
								testpaperItems.add(childTestpaperItem);
							}
						}
						}
				}
				if (testpaperItems.size()>0) {
					TestpaperChapter testpaperChapter=new TestpaperChapter();
					testpaperChapter.setName(generatorQuestionConfig.getChapterName());
					testpaperChapter.setTestpaperItems(testpaperItems);
					testpaperChapter.setOrder(chapterCount+1);
					chapterCount++;
					testpaperChapters.add(testpaperChapter);
				}
			}
		}
		if (testpaperChapters.size()>0) {
			Testpaper testpaper=new Testpaper();
			testpaper.setName(outlineCategoryDao.find(outlineCategoryId).getName()+"-组卷模考-");//TODO  试卷名称生成
			testpaper.setTestpaperChapters(testpaperChapters);
			testpaper.setScore(totalScore);
			testpaper.setTestpaperType(TestpaperType.genrationexam);
			testpaper.setLimitedTime(strategy.getTimeLimit());
			OutlineCategory outlineCategory=new OutlineCategory();
			outlineCategory.setId(outlineCategoryId);
			testpaper.setOutlineCategory(outlineCategory);
			return testpaper;
		}
		return null;
	}
	public List<Testpaper> findOldexamList(Long outlineCategoryID, Long areaID,Integer page) {
		return testpaperDao.findOldexamList(outlineCategoryID, areaID, page);
	}

	public Long findOldexamCount(Long outlineCategoryID, Long areaID) {
		return testpaperDao.findOldexamCount(outlineCategoryID, areaID);
	}
	/**
	 * 
	 * <p>功能:存储自动生成的试卷or试题</p> 
	 * @author 杨楷
	 * @date 2015-3-26 下午06:13:56 
	 * @version 0.2 维护score和itemCount 2015年5月6日14:22:20
	 * @version 0.3 为了维护testpaperItemResult里每道题目的当前大纲id字段且尽量避免少发sql，在存储试卷的同时返回每道题目的大纲id。返回数据结构为map。key为questionId，value为该题目的大纲id.
	 * @param testpaper
	 * @return key为testpaperId 值为持久化后的试卷id。key 为questionOutlineCategorysMap的值为 该试卷中每一道题的当前大纲的id集合。数据类型为map。key为questionId，value为该题目的大纲id.
	 */
	@Transactional
	public Map<String, Object> saveAutoGeneratedTestpaper(Testpaper testpaper){
		Map<String, Object> returnMap=new HashMap<String, Object>();
		HashMap<Long, Long> questionOutlineCategorysMap = new HashMap<Long, Long>();
		float score=0f;
		int itemCount=0;
		List<TestpaperItem> childrenItems=new ArrayList<TestpaperItem>();
		testpaper.setStatus(com.sram.entity.Testpaper.Status.open);
		testpaperDao.persist(testpaper);
		Set<TestpaperChapter> testpaperChapters = testpaper.getTestpaperChapters();
		Iterator<TestpaperChapter> iterator = testpaperChapters.iterator();
		while (iterator.hasNext()) {
			TestpaperChapter testpaperChapter = iterator.next();
			testpaperChapter.setTestpaper(testpaper);
			testpaperChapterDao.persist(testpaperChapter);
			Set<TestpaperItem> testpaperItems = testpaperChapter.getTestpaperItems();
			//如果是材料题先存父题，避免update；
			Iterator<TestpaperItem> iterator2 = testpaperItems.iterator();
			while (iterator2.hasNext()) {
				TestpaperItem testpaperItem = iterator2.next();
				testpaperItem.setTestpaper(testpaper);
				if (testpaperItem.getParent()==null) {
					testpaperItem.setTestpaperChapter(testpaperChapter);
					questionOutlineCategorysMap.put(testpaperItem.getQuestion().getId(),testpaperItem.getQuestion().getOutlineCategory().getId());
					testpaperItemDao.persist(testpaperItem);
					if (testpaperItem.getQuestionType()!=QuestionType.material) {
						itemCount++;
						score+=testpaperItem.getScore();
					}
				}else {
					childrenItems.add(testpaperItem);
				}
			}
			if (childrenItems.size()!=0) {
				for (TestpaperItem testpaperItem : childrenItems) {
					questionOutlineCategorysMap.put(testpaperItem.getQuestion().getId(),testpaperItem.getQuestion().getOutlineCategory().getId());
					testpaperItemDao.persist(testpaperItem);
					itemCount++;
					score+=testpaperItem.getScore();
				}
				childrenItems.removeAll(testpaperItems);
			}
		}
		Long id = testpaper.getId();
		testpaper.setItemCount(itemCount);
		testpaper.setScore(score);
		testpaperDao.merge(testpaper);
		testpaperDao.flush();
		testpaperDao.clear();
		testpaperChapterDao.clear();
		testpaperItemDao.clear();
		returnMap.put("testpaperId", id);
		returnMap.put("questionOutlineCategorysMap", questionOutlineCategorysMap);
		return returnMap;
	}
	
	/**
	 * <p>功能:查找考卷的结果集(对象组装)</p> 
	 * @author 杨楷
	 * @date 2015-4-3 上午10:40:09 
	 * @param testpaperResultId
	 * @return
	 */
	@Transactional
	public Testpaper findTestpaperResult(Long testpaperResultId){
		Integer totalItem=0;
		Integer wrongCount=0;
		TestpaperResult testpaperResult = this.testpaperResultDao.findTestpaperWithTestpaper(testpaperResultId);
		
		//2015,5,11---荣刚平
		Testpaper testpaper=testpaperResult==null?null:testpaperResult.getTestpaper();
		
		Testpaper testpaperWithChapters = this.testpaperDao.findTestpaperWithChapters(testpaper==null?null:testpaper.getId());
		
		if(testpaperWithChapters==null){
			testpaperWithChapters=new Testpaper();
		}
		
		Iterator<TestpaperChapter> iterator = testpaperWithChapters.getTestpaperChapters().iterator();
		//章节集合
		while (iterator.hasNext()) {
			TestpaperChapter testpaperChapter = iterator.next();
			Iterator<TestpaperItem> iterator2 = testpaperChapter.getTestpaperItems().iterator();
			List<TestpaperItemResult> TestpaperItemResults = this.testpaperItemResultDao.findByTestpaperResultId(testpaperResultId);
			while (iterator2.hasNext()) {
				TestpaperItem testpaperItem = iterator2.next();
				for (TestpaperItemResult testpaperItemResult : TestpaperItemResults) {
					//普通题对象组装
					if (testpaperItem.getId().equals(testpaperItemResult.getTestpaperItem().getId())){
						testpaperItem.setTestpaperItemResult(testpaperItemResult);
						/**材料题子题组装**/
						if (testpaperItem.getQuestionType()==QuestionType.material) {
							List<TestpaperItem> children = testpaperItemDao.findChildren(testpaperItem.getId());
							Set<TestpaperItem> childrenSet=new HashSet<TestpaperItem>();
							boolean isIncludeWrong=false;
							for (int i = children.size()-1; i>=0; i--) {
								totalItem++;
								for (TestpaperItemResult childItemResult : TestpaperItemResults) {//子题答案对象组装
									if (children.get(i).getId().equals(childItemResult.getTestpaperItem().getId())) {
										children.get(i).setTestpaperItemResult(childItemResult);
										if (childItemResult.getStatus()!=com.sram.entity.TestpaperItemResult.Status.right) {
											wrongCount++;
											isIncludeWrong=true;
										}
									}
								}
								childrenSet.add(children.get(i));
							}
							testpaperItem.setChildren(childrenSet);
							TestpaperItemResult parentTestpaperItemResult = testpaperItem.getTestpaperItemResult();
							if (isIncludeWrong) {
								parentTestpaperItemResult.setStatus(com.sram.entity.TestpaperItemResult.Status.wrong);
							}else {
								parentTestpaperItemResult.setStatus(com.sram.entity.TestpaperItemResult.Status.right);
							}
							testpaperItem.setTestpaperItemResult(parentTestpaperItemResult);
						}else {
							totalItem++;
							if (testpaperItem.getTestpaperItemResult().getStatus()!=com.sram.entity.TestpaperItemResult.Status.right) {
								wrongCount++;
							}
						}
					}
				}
			}
		}
		testpaperWithChapters.setTotalCount(totalItem);
		testpaperWithChapters.setWrongCount(wrongCount);
		return testpaperWithChapters;
	}

	public void updateStatusById(Long testPaperId,
			com.sram.entity.Testpaper.Status status) {
		// TODO Auto-generated method stub
		testpaperDao.updateStatusById(testPaperId,status);
	}
	
}
