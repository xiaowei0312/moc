package com.sram.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.alibaba.fastjson.JSON;
import com.sram.Constants.QuestionType;
import com.sram.util.JsonUtils;
/**
 * 试卷结果
 * @author Administrator
 *
 */
@Entity
@Table(name = "moc_testpaper_item_result" ,uniqueConstraints={@UniqueConstraint(columnNames={"testpaperItemId","testpaperResultId"},name="fk_item_result")})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_testpaper_item_result_sequence")
public class TestpaperItemResult extends OrderEntity {
	private static final long serialVersionUID = 1L;
	/**试卷名称*/
	private String paperName;
	/**试卷条目ID*/
	private TestpaperItem testpaperItem;
	/**试卷ID*/
	private Testpaper testpaper;
	/**试卷结果ID*/
	private TestpaperResult testpaperResult;
	/**做题人ID*/
	private Long userId;
	/**题目ID*/
	private Question question;
	
	public  enum Status{
		none,right,partRight,wrong,noAnswer
	}
	/**结果状态 <br/>'none','right','partRight','wrong','noAnswer'*/
	private Status status;
	/**得分*/
	private Float score;
	
	/**回答*/
	private  String answer ;
	
	/**老师评价*/
	private  String eacherSay ;
	
	/**当前大纲**/
	private OutlineCategory outlineCategory;
	/**顶级大纲ID*/
	private Long rootOutlineCategory;
	/**分录题答案**/
	private String entryAnswer;
	/**选择题答案**/
	private String choiseAnswer;
	/**填空题答案**/
	private String blankAnswer;
	/**判断题答案**/
	private String determineAnswer;
	/**简答题答案**/
	private String essayAnswer;
	/** 将题干转成填空题形式 */
	private String stemToBlanks;
	/**分录题答案*/
	private List<String[]> entryAnswerArrList;
	
	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getEacherSay() {
		return eacherSay;
	}

	public void setEacherSay(String eacherSay) {
		this.eacherSay = eacherSay;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="testpaperId")
	public Testpaper getTestpaper() {
		return testpaper;
	}

	public void setTestpaper(Testpaper testpaper) {
		this.testpaper = testpaper;
	}

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="testpaperResultId")
	public TestpaperResult getTestpaperResult() {
		return testpaperResult;
	}

	public void setTestpaperResult(TestpaperResult testpaperResult) {
		this.testpaperResult = testpaperResult;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="testpaperItemId")
	public TestpaperItem getTestpaperItem() {
		return testpaperItem;
	}

	public void setTestpaperItem(TestpaperItem testpaperItem) {
		this.testpaperItem = testpaperItem;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "outlineCategoryID")
	public OutlineCategory getOutlineCategory() {
		return outlineCategory;
	}

	public void setOutlineCategory(OutlineCategory outlineCategory) {
		this.outlineCategory = outlineCategory;
	}

	public Long getRootOutlineCategory() {
		return rootOutlineCategory;
	}

	public void setRootOutlineCategory(Long rootOutlineCategory) {
		this.rootOutlineCategory = rootOutlineCategory;
	}

	@Transient
	public String getEntryAnswer() {
		StringBuffer buffer =new StringBuffer();
		if(this.answer!=null && !("").equals(this.answer)){
			ArrayList<String> userAnswer = JsonUtils.toObject(this.answer,ArrayList.class);
			for (String strings : userAnswer) {
				buffer.append(strings+";");
			}
		}
		return buffer.toString();
	}

	public void setEntryAnswer(String entryAnswer) {
		this.entryAnswer = entryAnswer;
	}

	@Transient
	public String getChoiseAnswer() {
		Character[] answers=null;
		try {
			this.answer.replaceAll("\"", "");
			answers = JsonUtils.toObject(this.answer, Character[].class);
			Arrays.sort(answers);
		} catch (Exception e) {
			return this.answer.replace("\\[", "").replace("\\]", "");
		}
        return Arrays.toString(answers).replace("\\[", "").replace("\\]", "");
	}

	public void setChoiseAnswer(String choiseAnswer) {
		this.choiseAnswer = choiseAnswer;
	}

	@Transient
	public String getBlankAnswer() {
		if(this.answer!=null && !("").equals(this.answer)){
			return this.answer.replaceAll("\"", "").replaceAll("\\[", "").replaceAll("\\]", "").trim();
		}else{
			return "";
		}
	}

	public void setBlankAnswer(String blankAnswer) {
		this.blankAnswer = blankAnswer;
	}

	@Transient
	public String getDetermineAnswer() {
		if (this.answer.replaceAll("\"", "").replaceAll("\\[", "").replaceAll("\\]", "").trim().equals("true")) {
			return"正确";
		}else {
			return "错误";
		}
	}

	public void setDetermineAnswer(String determineAnswer) {
		this.determineAnswer = determineAnswer;
	}

	@Transient
	public String getEssayAnswer() {
		if(this.answer!=null && !("").equals(this.answer)){
			return this.answer.replaceAll("\"", "").replaceAll("\\[", "").replaceAll("\\]", "").trim();
		}else{
			return "";
		}
	}

	public void setEssayAnswer(String essayAnswer) {
		this.essayAnswer = essayAnswer;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="questionId",nullable=false)
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
	@Transient
	public String getStemToBlanks() {
		String stem = this.question.getStem();
		if(stem!=null && this.getTestpaperItem().getQuestionType().equals(QuestionType.blanks)){
			//填空题答案
			String blankAnswer=this.getBlankAnswer();
			if(blankAnswer!=null && !("").equals(blankAnswer)){
				String[] answerArrs=blankAnswer.split(",");
				if(this.getTestpaperItem().getParent()!=null){
					for(String answerArr:answerArrs){
						stem = stem.replaceFirst("\\[(.*?)\\]", 
								"<input type='text' name='testpaperItem_"+this.getTestpaperItem().getParent().getTestpaperChapter().getId()+"_"+this.getTestpaperItem().getQuestion().getId()+"_"+this.getTestpaperItem().getId()
									+"' class='ex_text' value='"+answerArr+"'/>"); 
					}
				}else{
					for(String answerArr:answerArrs){
						stem = stem.replaceFirst("\\[(.*?)\\]", 
								"<input type='text' name='testpaperItem_"+this.getTestpaperItem().getTestpaperChapter().getId()+"_"+this.getTestpaperItem().getQuestion().getId()+"_"+this.getTestpaperItem().getId()
									+"' class='ex_text' value='"+answerArr+"'/>"); 
					}
				}
			}
			//填空题没有回答或者未回答后半部分时，将后面的[]中内容用横线替代，为了解决split拆分含空字符串时的问题
			if(this.getTestpaperItem().getParent()!=null){
				stem = stem.replaceAll("\\[(.*?)\\]", 
						"<input type='text' name='testpaperItem_"+this.getTestpaperItem().getParent().getTestpaperChapter().getId()+"_"+this.getTestpaperItem().getQuestion().getId()+"_"+this.getTestpaperItem().getId()
							+"' class='ex_text'/>");
			}else{
				stem = stem.replaceAll("\\[(.*?)\\]", 
						"<input type='text' name='testpaperItem_"+this.getTestpaperItem().getTestpaperChapter().getId()+"_"+this.getTestpaperItem().getQuestion().getId()+"_"+this.getTestpaperItem().getId()
							+"' class='ex_text'/>"); 
			}
		}
		return stem;
	}

	public void setStemToBlanks(String stemToBlanks) {
		this.stemToBlanks = stemToBlanks;
	}
	
	@Transient
	public List<String[]> getEntryAnswerArrList() {
		if(this.answer!=null && !("").equals(this.answer)){
			List<String> parseArray = JSON.parseArray(this.answer,String.class);
			List<String[]> list=new ArrayList<String[]>();
			for (String str : parseArray) {
				list.add(str.split(","));
			}
			return list;
		}else{
			return null;
		}
	}

	public void setEntryAnswerArrList(List<String[]> entryAnswerArrList) {
		this.entryAnswerArrList = entryAnswerArrList;
	}
	
	
}
