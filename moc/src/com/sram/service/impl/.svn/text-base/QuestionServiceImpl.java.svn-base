package com.sram.service.impl;



import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.Property;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.sram.Constants.Difficulty;
import com.sram.Constants.QuestionType;
import com.sram.Constants.TestpaperType;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.SramException;
import com.sram.dao.OutlineCategoryDao;
import com.sram.dao.QuestionDao;
import com.sram.entity.AccoutCatalog;
import com.sram.entity.GeneratorStrategy;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Question;
import com.sram.entity.QuestionUploadAnalyseReport;
import com.sram.entity.Testpaper;
import com.sram.entity.TestpaperChapter;
import com.sram.entity.TestpaperItem;
import com.sram.entity.TestpaperItemResult;
import com.sram.service.AccoutCatalogService;
import com.sram.service.QuestionService;
import com.sram.util.JsonUtils;
import com.sram.util.MathUtil;
import com.sram.util.UnicodeConverter;

@Service("questionServiceImpl")
public class QuestionServiceImpl extends BaseServiceImpl<Question, Long>
		implements QuestionService, DisposableBean {

	@Resource(name = "questionDaoImpl")
	private QuestionDao questionDao;

	@Resource(name = "questionDaoImpl")
	public void setBaseDao(QuestionDao questionDao) {
		super.setBaseDao(questionDao);
	}

	@Autowired
	private OutlineCategoryDao outlineCategoryDao;
	
	@Autowired
	private AccoutCatalogService accoutCatalogService;
	
	
	
	@Transactional(readOnly = true)
	public List<Question> findAll() {
		return questionDao.findList(null, null, null, null);
	}

	@Override
	@Transactional
	@CacheEvict(value = { "question" }, allEntries = true)
	public Question update(Question entity, String... ignoreProperties) {
		return null;
	}

	@Transactional(readOnly = true)
	public void destroy() throws Exception {
	}

	@Transactional(readOnly = true)
	public Page<Question> findPage(Pageable pageable, String stem,
			String questionType, String difficulty, String IdTreePath) {
		return questionDao.findPage(pageable, stem, questionType, difficulty,
				IdTreePath);
	}

	@Transactional(readOnly = true)
	public Boolean hasQuestionByOutlineCategory(List<OutlineCategory> categories) {
		return questionDao.hasQuestionByOutlineCategory(categories);
	}

	public List<Question> findRootList() {
		return null;
	}
	
	/**
	 * <p>功能:解析试题xls</p> 
	 * @author 杨楷
	 * @date 2015-3-10 下午06:16:23 
	 * @version 0.2  （增加sheetType 与sheetName对应。解决sheet名字对应解析类型的问题）  
	 * @version 0.3  （增加分值和漏选分值列）  
	 * @version 0.4  （增加分录题） 2015年4月27日17:53:19  
	 * @version 0.5   细化提示 2015年5月5日17:08:06
	 * @version 0.6  xls中增加类别标记，防止误导入（xls详细信息中的类别添加 testpaper或者question）
	 * @param multipartFile 待解析的文件
	 * @param sheetNames sheet名 数组
	 * @param sheetType  sheet名字对应的类型
	 * @param templates 模板集合
	 * @return 返回key为“reportList”的解析报告，如果report报告中没有错误信息。则返回两个待持久化的list集合 key为“questionList”和“materList” 类型为List<Question> 和List<List<Question>>
	 * @throws Exception 解析失败全局异常
	 */
	public Map<String, Object> analyzeXLS(MultipartFile multipartFile,String[] sheetNames, String[] sheetTypes,Map<String, String[]> templates) throws Exception {
		POIFSFileSystem fs;
		HSSFWorkbook workBook;
		Map<String, Object> map=new HashMap<String, Object>();
		List<QuestionUploadAnalyseReport> reportList = new ArrayList<QuestionUploadAnalyseReport>();
		List<Question> questionList=new ArrayList<Question>();
		List<List<Question>> materList=new ArrayList<List<Question>>();
			fs = new POIFSFileSystem(multipartFile.getInputStream());
			workBook = new HSSFWorkbook(fs);
			DocumentSummaryInformation documentSummaryInformation = workBook.getDocumentSummaryInformation();
			if (StringUtils.isBlank(documentSummaryInformation.getCategory())||
					(sheetTypes==null&&(!documentSummaryInformation.getCategory().equals("question")))||
					(sheetTypes!=null&&(!documentSummaryInformation.getCategory().equals("testpaper")))) {
				throw new SramException("导入模板不合法，请检查模板是否符合规范");
			}
			if (sheetTypes==null) {
				sheetTypes=sheetNames;
			}
			QuestionUploadAnalyseReport report;
			// 工作表遍历
			for (int i = 0; i < sheetNames.length; i++) {
				report = new QuestionUploadAnalyseReport();
				report.setFileName(multipartFile.getOriginalFilename());
				report.setSheet(sheetNames[i]);
				report.setOk(true);
				HSSFSheet sheet = workBook.getSheet(sheetNames[i]);
				if (sheet == null) {
					report.msg = sheetNames[i] + "表不存在。<br/>";
					reportList.add(report);
					continue;
				}
				int rows = sheet.getPhysicalNumberOfRows();
				if (rows > 1) {
					StringBuffer sb= new StringBuffer();
					// 总行数大于1，表明有数据，开始读取数据
					//存放一道材料题
					List<Question> materiaList=new ArrayList<Question>();
					for (int j = 1; j < rows; j++) { // 行循环
						HSSFRow row = sheet.getRow(j);
						if (row != null) {
							// 取得列数
							int cells = row.getLastCellNum();// 获得列数
							// 定义一个数组用来存放从单元格取出来的数据，长度为表头的长度
							String[] sj = new String[templates.get(sheetTypes[i]).length];
							// 通过类名称加载一个类
							Class cls = Class.forName("com.sram.entity.Question");
							Object o = cls.newInstance();
							for (int k = 0; k < templates.get(sheetTypes[i]).length; k++) { // 列循环
								String[] str = new String[2];
								str = templates.get(sheetTypes[i])[k].split(",");
								HSSFCell cell = row.getCell(k);
								Method setMethod = cls.getDeclaredMethod("set"+ str[1].substring(0, 1).toUpperCase()+ str[1].substring(1), String.class);
								if (cell != null) {
									cell.setCellType(HSSFCell.CELL_TYPE_STRING);
									sj[k] = cell.getStringCellValue().replaceAll("　", "").trim();
									setMethod.invoke(o, sj[k]);
								} else {
									setMethod.invoke(o,"");
								}
							}
							//一列值中是否有*空值
							boolean isNull=false;
							// 执行检查（过滤*空值）
							for (int m = 0; m < templates.get(sheetTypes[i]).length; m++) {
								String[] str = templates.get(sheetTypes[i])[m].split(",");
								if (templates.get(sheetTypes[i])[m].contains("*")) {
									Method method = cls.getMethod("get"+ str[1].substring(0, 1).toUpperCase()+ str[1].substring(1), null);
									if ((method.invoke(o, null) != null ? method.invoke(o, null).toString() : "").equals("")) {
										report.setOk(false);
										isNull=true;
										sb.append("第" + (j + 1) + "行存在"+ str[0] + "列值为空。<br/>");
									}
								}
							}
							//公共校验和分类校验
							if (isNull==false&&(o instanceof Question)) {
								Question question = (Question) o;
								// 射入章节名称
								question.setSheetName(sheetNames[i]);
								//校验大纲编码
								if (StringUtils.isNotBlank(question.getOutlineCode())) {
									 OutlineCategory outlineCategory = outlineCategoryDao.findByCode(question.getOutlineCode());
						                if(outlineCategory!=null){
						                	question.setOutlineCategory(outlineCategory);
						                	question.setTreePath(outlineCategory.getTreePath());
						                }else {
						                	report.setOk(false);
						                    sb.append("第" + (j + 1) + "行：大纲编号错误 <br/>");
						                    continue;
						                }
								}
					            //校验难度
					             if (StringUtils.isNotBlank(question.getDifficultyStr())) {
					            	 if(question.getDifficultyStr().trim().equals("简单")){
					            		 question.setDifficulty(Difficulty.easy);
					            	 }else if (question.getDifficultyStr().trim().equals("一般")){
					            		 question.setDifficulty(Difficulty.normal);
									}else if (question.getDifficultyStr().trim().equals("困难")){
					            		 question.setDifficulty(Difficulty.hard);
									}else {
										report.setOk(false);
					                    sb.append("第" + (j + 1) + "行：难度值不合法 <br/>");
					                    continue;
									}
								}
					             	//校验分值
					             if (StringUtils.isNotBlank(question.getScoreStr())) {
					            	 if (question.getScoreStr().matches("^[0-9]+(\\.[0-9]+)?$")) {
					            		 question.setScore(Float.valueOf(question.getScoreStr()));
									}else {
										report.setOk(false);
					                    sb.append("第" + (j + 1) + "行：分值不合法哟 <br/>");
					                    continue;
									}
					            	 
								}
								if (sheetNames[i].equals("选择题")||sheetTypes[i].equals("选择题")) {
									Map<String, Object> validateChoice = this.validateChoice(question, j+1);
									if(validateChoice.get("msg")!=null){
										report.setOk(false);
										sb.append(validateChoice.get("msg"));
										continue;
									}else {
										questionList.add((Question) validateChoice.get("question"));
									}
								}else if (sheetNames[i].equals("判断题")||sheetTypes[i].equals("判断题")) {
									Map<String, Object> validateDetermine = this.validateDetermine(question, j+1);
									if(validateDetermine.get("msg")!=null){
										report.setOk(false);
										sb.append(validateDetermine.get("msg"));
										continue;
									}else {
										questionList.add((Question) validateDetermine.get("question"));
									}
								}else if (sheetNames[i].equals("填空题")||sheetTypes[i].equals("填空题")) {
									Map<String, Object> validateBlanks = this.validateBlanks(question, j+1);
									if(validateBlanks.get("msg")!=null){
										report.setOk(false);
										sb.append(validateBlanks.get("msg"));
										continue;
									}else {
										questionList.add((Question) validateBlanks.get("question"));
									}
								}else if (sheetNames[i].equals("简答题")||sheetTypes[i].equals("简答题")) {
									question.setQuestionType(QuestionType.essay);
									List<String> list =new ArrayList<String>();
									list.add(question.getAnswer());
									question.setAnswer(JSON.toJSONString(list));
									questionList.add(question);
								}else if (sheetNames[i].equals("材料题")||sheetTypes[i].equals("材料题")) {
									if (!(question.getMaterialType().equals("材料题") || question.getMaterialType().equals("选择题")|| question.getMaterialType().equals("判断题") || question.getMaterialType().equals("填空题") || question.getMaterialType().equals("简答题")|| question.getMaterialType().equals("分录题"))) {
										report.setOk(false);
										sb.append("第" + (j + 1) + "行：材料题的类型值不合法 <br/>");
										continue;
									}
									if (question.getMaterialType().equals("材料题")) {
										question.setQuestionType(QuestionType.material);
										if(materiaList.size()==0){
											materiaList.add(question);
										}else if (materiaList.size()==1) {
											report.setOk(false);
											sb.append("第" + j + "行：材料题缺少题干<br/>");
											materiaList.remove(0);
											materiaList.add(question);
										}else {
											Map<String, Object> material =this.validateMaterial(materiaList, (j+1-materiaList.size()));
											if(material.get("msg")!=null){
												report.setOk(false);
												sb.append(material.get("msg"));
											}else {
												materList.add((List<Question>) material.get("questionList"));
											}
											materiaList=new ArrayList<Question>();
											materiaList.add(question);
										}
									}else {
										materiaList.add(question);
										if (j+1==rows) {
											Map<String, Object> material2 = this.validateMaterial(materiaList, (j+1-materiaList.size()+1));
											if(material2.get("msg")!=null){
												report.setOk(false);
												sb.append(material2.get("msg"));
											}else {
												materList.add((List<Question>) material2.get("questionList"));
											}
										}
									}
								}
							}
						}
					}
					report.setMsg(sb.toString());
				} else {
					report.setMsg("表中数据为空，未导入任何数据。<br/>");
				}
				reportList.add(report);
			}
			map.put("reportList", reportList);
			boolean isAllSuccess=true;
			for (QuestionUploadAnalyseReport analyseReport : reportList) {
				if (!analyseReport.isOk()) {
					isAllSuccess=false;
				}
			}
			if (isAllSuccess) {
				map.put("materList", materList);
				map.put("questionList", questionList);
			}
			return map;
	}
	/**
	 * <p>功能:校验选择题并赋值</p> 
	 * @author 杨楷
	 * @date 2015-3-10 下午06:16:23 
	 * @version 0.1 修改选择题选项中多余的回车换行符
	 * @param question 待验证的对象
	 * @param rowIndex 所在行数
	 * @return 校验出错返回key为“msg”的错误信息，通过校验则返回key为“question”的组装好的对象
	 */
    public Map<String, Object> validateChoice(Question question, int rowIndex) {
    	Map<String, Object> returnMap=new HashMap<String, Object>();
    	 // 对选项、答案进行验证
        String answer = question.getAnswer().replaceAll("\\,|\\s","").toUpperCase();
        if (!answer.matches("^[A-Z]+$")){
            returnMap.put("msg", ("第" + rowIndex + "答案的值不合法<br/>"));
        	return returnMap;
        }
        question.setMetas(question.getMetas().replaceAll("\n\n", "\n"));
        question.setMetas(question.getMetas().replaceAll("\n\n", "\n"));
        String[] metas = question.getMetas().split("\n");
        char[] answers = answer.toCharArray();
        Arrays.sort(answers);
        // 最后一个答案的值如果超过选项的个数
        if (answers[answers.length - 1] - 64 > metas.length){
        	 returnMap.put("msg", ("第" + rowIndex + "行：答案和选项不匹配(备选项有"+metas.length+"个,答案的最高索引是"+(answers[answers.length - 1] - 64)+")<br/>"));
         	return returnMap;
        }
        // 设置类型(单选、不定项单选、多选、不定项多选)
        if (answer.length() == 1) {
        	if (StringUtils.isBlank(question.getIsUncertain())) {
        		question.setQuestionType(QuestionType.single_choice);
			}else {
				if (question.getIsUncertain().equals("是")) {
	            	question.setQuestionType(QuestionType.single_choice);
	            } else {
	            	question.setQuestionType(QuestionType.uncertain_singlechoice);
	            }
			}
            
        } else {
        	if (StringUtils.isBlank(question.getIsUncertain())) {
        		question.setQuestionType(QuestionType.choice);
			}else {
				if (question.getIsUncertain().equals("是")) {
	            	question.setQuestionType(QuestionType.choice);
	            } else {
	            	question.setQuestionType(QuestionType.uncertain_choice);
	            }
			}
        }

        if (question.getQuestionType()==QuestionType.choice||question.getQuestionType()==QuestionType.uncertain_choice) {
        	if (StringUtils.isNotBlank(question.getMissScoreStr())) {
        		if (question.getMissScoreStr().matches("^[0-9]+(\\.[0-9]+)?$")) {
        			question.setMissScore(Float.valueOf(question.getMissScoreStr()));
    				}else {
    					returnMap.put("msg", ("第" + rowIndex + "行：多选题漏选分值不合法<br/>"));
					}
        	}else {
				returnMap.put("msg", ("第" + rowIndex + "行：多选题漏选分值不能为空<br/>"));
			}

		}
        // 设置选项
        question.setMetas(JsonUtils.toJson(metas));
        // 设置答案格式为 {"65":"阿斯蒂芬热尔","66":"阿斯蒂芬的"} ASCII码：答案
        List<Map<Integer, String>> jsonAnswers = new ArrayList<Map<Integer, String>>();
        Map<Integer, String> map = new HashMap<Integer, String>();
        List<String> answers2=new ArrayList<String>();
		for (Character value : answers) {
			answers2.add((int)value+"");
		}
        question.setAnswer(JsonUtils.toJson(answers2));
        returnMap.put("question", question);
        
        return returnMap;
    }
	public Map<String, Object> validateDetermine(Question question, int rowIndex) {
		Map<String, Object> returnMap=new HashMap<String, Object>();
        if (question.getAnswer().equals("正确")) {
        	question.setAnswer("[\"true\"]");
        } else if (question.getAnswer().equals("错误")) {
        	question.setAnswer("[\"false\"]");
        } else {
        	 returnMap.put("msg", ("第" + rowIndex + "行：答案值不合法，必须为[正确]或[错误] <br/>"));
        	 return returnMap;
        }
        question.setQuestionType(QuestionType.determine);
        returnMap.put("question", question);
	    return returnMap;
	}
	/**
	 * <p>功能:填空校验</p> 
	 * @author 杨楷
	 * @date 2015年4月28日 上午9:24:09 
	 * @version 0.1 修正题干中的答案匹配规则 2015年5月5日15:13:39
	 * @param question
	 * @param rowIndex
	 * @return
	 */
    public Map<String, Object> validateBlanks(Question question, int rowIndex) {
    	Map<String, Object> returnMap=new HashMap<String, Object>();
    	question.setQuestionType(QuestionType.blanks);
    	Pattern p = Pattern.compile("(\\[.*?\\])");
    	Matcher m = p.matcher(question.getStem());
    	List<String> list =new ArrayList<String>();
		while (m.find()) {
			String group = m.group();
			group=group.substring(1, group.length()-1);
			if (StringUtils.isBlank(group)) {
				list.removeAll(list);
				break;
			}
			list.add(group);
		}
		if (list.size()==0) {
			returnMap.put("msg", ("第" + rowIndex + "行：题干不合法，题干中没有包含答案 <br/>"));
       	 	return returnMap;
		}
    	question.setAnswer(JSON.toJSONString(list));
    	 returnMap.put("question", question);
 	    return returnMap;
    }
    
    /**
     * <p>功能:校验材料题</p> 
     * @author 杨楷
     * @date 2015年4月28日 上午9:21:29 增加分录题校验
     * @param materiaList
     * @param rowIndex
     * @return
     */
    public Map<String, Object> validateMaterial(List<Question> materiaList, int rowIndex) {
    	Map<String, Object> returnMap=new HashMap<String, Object>();
    	if(StringUtils.isBlank(materiaList.get(0).getDifficultyStr())){
    		returnMap.put("msg", ("第" + rowIndex + "行：材料题难度不能为空 <br/>"));
    		return returnMap;
    	}
    	StringBuffer buffer=new StringBuffer();
    	List<Question> list=new ArrayList<Question>();
    	list.add(materiaList.get(0));
    	for (int i = 1; i < materiaList.size(); i++) {
    		//分值校验
    		if (StringUtils.isNotBlank(materiaList.get(i).getScoreStr())) {
           	 if (materiaList.get(i).getScoreStr().matches("^[0-9]+(\\.[0-9]+)?$")) {
           		materiaList.get(i).setScore(Float.valueOf(materiaList.get(i).getScoreStr()));
				}else {
					buffer.append("第" + (rowIndex+i) + "行：分值不合法哟 <br/>");
				}
    		}else {
    				buffer.append("第" + (rowIndex+i) + "行：分值不能为空 <br/>");
			}
			if(materiaList.get(i).getMaterialType().equals("选择题")){
				if (StringUtils.isBlank(materiaList.get(i).getMetas())||StringUtils.isBlank(materiaList.get(i).getAnswer())) {
					buffer.append("第" + (rowIndex+i) + "行：材料题的子选择题选项或答案不能为空 <br/>");
				}else {
					Map<String, Object> choiceMap = this.validateChoice(materiaList.get(i), rowIndex+i);
					if (choiceMap.get("msg")!=null&&StringUtils.isNotBlank((String)choiceMap.get("msg"))) {
						buffer.append((String)choiceMap.get("msg"));
					}else {
						list.add((Question) choiceMap.get("question"));
					}
				}
			}else if (materiaList.get(i).getMaterialType().equals("填空题")) {
				Map<String, Object> blankMap = this.validateBlanks(materiaList.get(i), rowIndex+i);
				if (blankMap.get("msg")!=null&&StringUtils.isNotBlank((String)blankMap.get("msg"))) {
					buffer.append((String)blankMap.get("msg"));
				}else {
					list.add((Question) blankMap.get("question"));
				}
			}else if (materiaList.get(i).getMaterialType().equals("判断题")) {
				if (StringUtils.isBlank(materiaList.get(i).getAnswer())) {
					buffer.append("第" + (rowIndex+i) + "行：材料题的子判断题选答案不能为空 <br/>");
				}else {
					Map<String, Object> determineMap = this.validateDetermine(materiaList.get(i), rowIndex+i);
					if (determineMap.get("msg")!=null&&StringUtils.isNotBlank((String)determineMap.get("msg"))) {
						buffer.append((String)determineMap.get("msg"));
					}else {
						list.add((Question) determineMap.get("question"));
					}
				}
			}else if (materiaList.get(i).getMaterialType().equals("分录题")) {
				Map<String, Object> blankMap = this.validateEntry(materiaList.get(i), rowIndex+i);
				if (blankMap.get("msg")!=null&&StringUtils.isNotBlank((String)blankMap.get("msg"))) {
					buffer.append((String)blankMap.get("msg"));
				}else {
					list.add((Question) blankMap.get("question"));
				}
			} else if (materiaList.get(i).getMaterialType().equals("简答题")) {
				if (StringUtils.isBlank(materiaList.get(i).getAnswer())) {
					buffer.append("第" + (rowIndex+i) + "行：材料题的子简答题选答案不能为空 <br/>");
				} else {
					Question essay = materiaList.get(i);
					essay.setQuestionType(QuestionType.essay);
					List<String> answerList =new ArrayList<String>();
					answerList.add(essay.getAnswer());
					essay.setAnswer(JSON.toJSONString(answerList));
					list.add(essay);
				}
			}else {
				buffer.append("第" + (rowIndex+i) + "行：不能识别的题目类型 <br/>");
			}
		}
    	if (StringUtils.isNotBlank(buffer.toString())) {
			returnMap.put("msg", buffer.toString());
			return returnMap;
		}else {
			returnMap.put("questionList", list);
			return returnMap;
		}
    }
    /**
     * <p>功能:校验分录并赋值</p> 
     * @author 杨楷
     * @date 2015年4月28日 上午9:28:32 
     * @param question
     * @param rowIndex
     * @return
     */
    private Map<String, Object> validateEntry(Question question, int rowIndex) {
    	Map<String, Object> returnMap=new HashMap<String, Object>();
    	if (StringUtils.isBlank(question.getAnswer())) {
    		returnMap.put("msg", ("第" + rowIndex + "行：分录题答案不能为空<br/>"));
    		 return returnMap;
		}
    	String[] answers = question.getAnswer().replaceAll("：", ":").split("\n");
    	if (answers.length<=1) {
    		returnMap.put("msg", ("第" + rowIndex + "行：分录题答案不合法<br/>"));
    		 return returnMap;
		}
    	question.setQuestionType(QuestionType.entry);
    	StringBuffer buffer=new StringBuffer();
    	List<String[]> list=new ArrayList<String[]>();
    	for (int i = 0; i < answers.length; i++) {
			String mark="";
			String code="";
			float value=0f;
			String[] split = answers[i].split(":");
			if (split.length==2) {
				mark=split[0];
				String[] split2 = split[1].split(" ");
				if (split2.length==2) {
					code=split2[0];
					try {
						value=Float.parseFloat(split2[1]);
					} catch (java.lang.NumberFormatException e) {
						buffer.append("第" + rowIndex + "行：分录题答案第"+(i+1)+"行：分录答案不合法答案为非数字<br/>");
						continue;
					}
				}else {
					buffer.append("第" + rowIndex + "行：分录题答案第"+(i+1)+"行：分录答案不合法<br/>");
					continue;
				}
			}else {
				buffer.append("第" + rowIndex + "行：分录题答案第"+(i+1)+"行：分录答案不合法<br/>");
				continue;
			}
			if (!(mark.equals("借")||mark.equals("贷"))) {
				buffer.append("第" + rowIndex + "行：分录题答案第"+(i+1)+"行：分录答案第一个值必须为“借”或“贷”<br/>");
			}
			List<AccoutCatalog> allChildren = accoutCatalogService.findAllChildren();
			boolean flag=false;
			for (AccoutCatalog accoutCatalog : allChildren) {
				if (accoutCatalog.getName().equals(code)) {
					flag=true;
					code=accoutCatalog.getCode();
					break;
				}
			}
			if(!flag){
				buffer.append("第" + rowIndex + "行：分录题答案第"+(i+1)+"行：没有找到与“"+code+"”相对应的会计科目编码，请先录入<br/>");
			}
			String[] strs=new String[3];
			strs[0]=mark;
			strs[1]=code;
			strs[2]=String.valueOf(value);
			list.add(strs);
		}
    	
    	if (StringUtils.isNotBlank(buffer.toString())) {
    		returnMap.put("msg", buffer.toString());
		}else {
			question.setAnswer(JSON.toJSONString(list));
			returnMap.put("question", question);
		}
 	    return returnMap;
    }

	/**
     * 
     * 修改日期2015年4月27日15:50:01
     * 存储答案时统一录入为Unicode格式
     */
    public boolean saveXLS(List<Question> questions,List<List<Question>> materialList ) {
    	try {
    	for (int  i= 0;  i< questions.size(); i++) {
    		try {
    			Question question = questions.get(i);
    			if (question.getMetas()!=null) {
    				question.setMetas(UnicodeConverter.toEncodedUnicode(question.getMetas(), false));
				}
    			if (question.getAnswer()!=null) {
    				question.setAnswer(UnicodeConverter.toEncodedUnicode(question.getAnswer(), false));
    			}
    			questionDao.independentPersist(questions.get(i));
			} catch (org.hibernate.exception.ConstraintViolationException e) {
			}
		}
    	for (List<Question> list :  materialList) {
			Question topQuestion=list.get(0);
			try {
				questionDao.independentPersist(topQuestion);
			} catch (org.hibernate.exception.ConstraintViolationException e) {
			}
				for (int i = 1; i < list.size(); i++) {
					Question question=list.get(i);
					if (question.getMetas()!=null) {
	    				question.setMetas(UnicodeConverter.toEncodedUnicode(question.getMetas(), false));
					}
	    			if (question.getAnswer()!=null) {
	    				question.setAnswer(UnicodeConverter.toEncodedUnicode(question.getAnswer(), false));
	    			}
					question.setParent(topQuestion);
					question.setTreePath(topQuestion.getTreePath());
					try {
						questionDao.independentPersist(question);
					} catch (org.hibernate.exception.ConstraintViolationException e) {
					}
				}
		}
    	return true;			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

    public List<Question> findAllQuestionOfChild(Long outlineCategoryId,String idStr,String questionType,String stem,Integer page) {
		return questionDao.findAllQuestionOfChild(outlineCategoryId, idStr, questionType, stem, page);
	}

	public Long findAllQuestionOfChildCount(Long outlineCategoryId,String idStr, String questionType, String stem) {
		return questionDao.findAllQuestionOfChildCount(outlineCategoryId, idStr, questionType, stem);
	}
	
	/**
	 * <p>功能:只能出题和专项练习的两种生成策略（随机生成和根据难度策略生成）</p> 
	 * @author 杨楷
	 * @date 2015年3月24日 12:02:57
	 * @修改时间： 2015年4月18日16:25:13
	 * @version 0.2 对于智能出题和专项练习来说，本身策略中并没有配置分数信息，需要从question实体中获取分数存入到条目中去。
	 * @param outlineCategoryId 大纲id
	 * @return 
	 */
	public Testpaper getQuestion(Long outlineCategoryId,GeneratorStrategy strategy,TestpaperType testpaperType){
		String testpaperTypeStr=testpaperType==TestpaperType.intellexercise?"智能练习":"考点专项";
		//取出所有符合条件题目的id集合然后随机抽取
		List<Long> ids = questionDao.findIds(outlineCategoryId,null,false,QuestionType.choice,QuestionType.single_choice,QuestionType.uncertain_singlechoice,QuestionType.uncertain_choice);
		if (ids.size()==0) {
			return null;
		}
		//读取策略中的题目数量
		int questionCountStrategy=strategy.getGeneratorQuestionConfigs().iterator().next().getCount();
		//要取出的题目数量小于从数据库中取出来的题目数量才有必要去随机，否则有几道出几道
		if (questionCountStrategy<ids.size()) {
			int[] randomIndexs = MathUtil.randomCommon(0, ids.size(),questionCountStrategy);
			List<Long> temp=new ArrayList<Long>();
 			for (int i = 0; i < randomIndexs.length; i++) {
				temp.add(ids.get(randomIndexs[i]));
			}
			ids=temp;
		}
		Set<TestpaperChapter> testpaperChapters=new HashSet<TestpaperChapter>();
		Set<TestpaperItem> testpaperItems=new HashSet<TestpaperItem>();//普通题条目
		Set<TestpaperItem> materialTestpaperItems=new HashSet<TestpaperItem>();//材料题条目
		Set<Long> parentIds=new HashSet<Long>();//存放滤重的材料题ID
		List<Long> ignoreIds=new ArrayList<Long>();//存放已存的id。补刀的时候自动忽略
		//用随机抽取到的题目去数据库中获取题
		int itemOrder=0;
		for (Long questionId : ids) {
			Question question = this.questionDao.find(questionId);
			//如果是材料题则取出父题的id放入hashSet滤重;
			if (question.getParent()!=null) {
				parentIds.add(question.getParent().getId());
			}else {
				TestpaperItem testpaperItem=new TestpaperItem();
				testpaperItem.setQuestion(question);
				ignoreIds.add(question.getId());//排除id
				testpaperItem.setScore(question.getScore());
				if (question.getQuestionType()==QuestionType.choice||question.getQuestionType()==QuestionType.uncertain_choice) {
					testpaperItem.setMissScore(question.getMissScore());
				}
				testpaperItem.setQuestionType(question.getQuestionType());
				testpaperItem.setOrder(itemOrder+1);
				itemOrder++;
				testpaperItems.add(testpaperItem);
			}
		}
		
		//如果没有材料题就直接返回，如果有的话解析出材料题然后计算数量后返回
		if (parentIds.size()!=0) {
			Iterator<Long> iterator = parentIds.iterator();
			//最多取两道材料题撒（每道材料题最多放5个子题撒）；
			int materialCount=0;//材料题个数
			for (int i = 0; i < 2; i++) {
				if(iterator.hasNext()){
					//根据parentId查询材料题
	                List<Question> children = questionDao.findMaterialChildren(iterator.next(), QuestionType.choice,QuestionType.single_choice,QuestionType.uncertain_singlechoice,QuestionType.uncertain_choice);
	                TestpaperItem topTestpaperItem=new TestpaperItem();
	                topTestpaperItem.setQuestion(children.get(0).getParent());
	                topTestpaperItem.setQuestionType(QuestionType.material);
	                topTestpaperItem.setScore(children.get(0).getParent().getScore());
	                topTestpaperItem.setOrder(i+1);
	                materialTestpaperItems.add(topTestpaperItem);
                	for (int j=0; j<5 ; j++) {
                		if (j<children.size()) {
            				TestpaperItem childItem=new TestpaperItem();
							childItem.setQuestion(children.get(j));
							childItem.setOrder(j+1);
							childItem.setParent(topTestpaperItem);
							childItem.setScore(children.get(j).getScore());
							if (children.get(j).getQuestionType()==QuestionType.choice||children.get(j).getQuestionType()==QuestionType.uncertain_choice) {
								childItem.setMissScore(children.get(j).getMissScore());
							}
							childItem.setQuestionType(children.get(j).getQuestionType());
							materialTestpaperItems.add(childItem);
						}
					}
                	materialCount++;
				}
			}
			
			/**数量判断开始。第一、材料题数量加普通题数量超过策略数量。则从普通题中减去超出的数量
			 			第二、如果材料题的数量加普通题的数量少于策略数量，则再去数据库中取缺少的数量（查询时排除已经加入的题目id）并补充。	**/
			if (materialTestpaperItems.size()+testpaperItems.size()-materialCount>questionCountStrategy) {
				Set<TestpaperItem> remainItems =new HashSet<TestpaperItem>();//留下的普通题
				Iterator<TestpaperItem> iterator2 = testpaperItems.iterator();
				int	remainCount= questionCountStrategy-materialTestpaperItems.size()+materialCount;
				for (int i = 0; i < remainCount; i++) {
					TestpaperItem remainItem = iterator2.next();
					remainItem.setOrder(i+1);
					remainItems.add(remainItem);
				}
				testpaperItems=remainItems;
			}else if (materialTestpaperItems.size()+testpaperItems.size()<questionCountStrategy) {//补刀
				int shortfall=questionCountStrategy-(materialTestpaperItems.size()+testpaperItems.size());//缺少数量
				Long[] ignore =new Long[ignoreIds.size()];
				for (int i = 0; i < ignoreIds.size(); i++) {
					ignore[i]=ignoreIds.get(i);
				}
				Integer[] questionTypes={QuestionType.choice.ordinal(),QuestionType.single_choice.ordinal(),QuestionType.uncertain_singlechoice.ordinal(),QuestionType.uncertain_choice.ordinal()};
				List<Long> shortfallIds = questionDao.findIdsIgnoreIds(outlineCategoryId,questionTypes, ignore);
				if (shortfallIds!=null&&shortfallIds.size()!=0) {
					if (shortfall<shortfallIds.size()) {
						int[] randomIndexs = MathUtil.randomCommon(0, shortfallIds.size(),shortfall);
						List<Long> temp=new ArrayList<Long>();
			 			for (int i = 0; i < randomIndexs.length; i++) {
							temp.add(shortfallIds.get(randomIndexs[i]));
						}
			 			shortfallIds=temp;
					}
					int lastIndex=testpaperItems.size(); //第一次取题的最后一道题的order
					for (int i = 0; i < shortfallIds.size(); i++) {
						Question question = this.questionDao.find(shortfallIds.get(i));
						TestpaperItem testpaperItem=new TestpaperItem();
						testpaperItem.setQuestion(question);
						testpaperItem.setScore(question.getScore());
						if (question.getQuestionType()==QuestionType.choice||question.getQuestionType()==QuestionType.uncertain_choice) {
							testpaperItem.setMissScore(question.getMissScore());
						}
						testpaperItem.setQuestionType(question.getQuestionType());
						testpaperItem.setOrder(i+1+lastIndex);
						testpaperItems.add(testpaperItem);
					}
				}
			}
		}
		if (testpaperItems.size()>0) {
			TestpaperChapter testpaperChapter=new TestpaperChapter();//普通题章节
			testpaperChapter.setOrder(1);
			testpaperChapter.setName("选择题");
			testpaperChapters.add(testpaperChapter);
		    testpaperChapter.setTestpaperItems(testpaperItems);
		    testpaperChapters.add(testpaperChapter);
		}
		if (materialTestpaperItems.size()>0) {
			TestpaperChapter materialChapter =new TestpaperChapter();
			materialChapter.setTestpaperItems(materialTestpaperItems);
			materialChapter.setOrder(testpaperChapters.size()+1);
			materialChapter.setName("材料题");
			testpaperChapters.add(materialChapter);
		}
		if (testpaperChapters.size()>0) {
			Testpaper testpaper=new Testpaper();
			testpaper.setTestpaperChapters(testpaperChapters);
		    testpaper.setName(outlineCategoryDao.find(outlineCategoryId).getName()+"-"+testpaperTypeStr+"-");
		    testpaper.setTestpaperType(testpaperType);
		    testpaper.setLimitedTime(strategy.getTimeLimit());
		    OutlineCategory outlineCategory = new OutlineCategory();
		    outlineCategory.setId(outlineCategoryId);
		    testpaper.setOutlineCategory(outlineCategory);
	        return testpaper;
		}
		return null;
		//TODO 暂未实现难度策略
	}

	public List<Question> findWrongQuestions(Long outlineCategoryId, Long userId) {
		return questionDao.findWrongQuestions(outlineCategoryId, userId);
	}
}