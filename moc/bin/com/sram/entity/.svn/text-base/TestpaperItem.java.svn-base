package com.sram.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import com.sram.Constants.QuestionType;
import com.sun.org.apache.bcel.internal.generic.NEW;


/**
 * 试卷条目
 * 
 * @author Administrator
 * 
 */
@Entity
@Table(name = "moc_testpaper_item",uniqueConstraints={@UniqueConstraint(columnNames={"testpaperID","questionID"},name="fk_testpaper_question")})
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_testpaper_item_sequence")
public class TestpaperItem extends OrderEntity {
	private static final long serialVersionUID = 1L;

	/** 所属试卷 */
	private Testpaper testpaper;
	/** 题目ID */
	private Question question;
	/** 父条目 */
	private TestpaperItem parent;

	/** 题目类型 */
	private QuestionType questionType;

	/** 分值 */
	private Float score;
	/** 漏选得分 */
	private Float missScore=(float) 0.0;
	/** 所属章节 */
	private TestpaperChapter testpaperChapter;
	/**
	 * 父条目 的孩子
	 */
	private Set<TestpaperItem> children = new HashSet<TestpaperItem>();
	
	private TestpaperItemResult testpaperItemResult;
	
	/** 临时父题目ID */
	private Long parentQuestionID;
	/** 将题干转成填空题形式 */
	private String stemToBlanks;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "testpaperID")
	public Testpaper getTestpaper() {
		return testpaper;
	}

	public void setTestpaper(Testpaper testpaper) {
		this.testpaper = testpaper;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "questionID")
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Float getMissScore() {
		return missScore;
	}

	public void setMissScore(Float missScore) {
		this.missScore = missScore;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "testpaperChapterId")
	public TestpaperChapter getTestpaperChapter() {
		return testpaperChapter;
	}

	public void setTestpaperChapter(TestpaperChapter testpaperChapter) {
		this.testpaperChapter = testpaperChapter;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parentID")
	public TestpaperItem getParent() {
		return parent;
	}

	public void setParent(TestpaperItem parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY,cascade=CascadeType.REMOVE)
	@OrderBy("order asc")
	public Set<TestpaperItem> getChildren() {
		return children;
	}

	public void setChildren(Set<TestpaperItem> children) {
		this.children = children;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	@Override
	public int hashCode() {
		return new Integer(12).hashCode();

	}

	@Override
	public boolean equals(Object obj) {
		if( obj instanceof TestpaperItem){
			TestpaperItem testpaperItem =(TestpaperItem) obj;
			if(testpaperItem==null||this.getId()==null||testpaperItem.getId()==null){
				return false;
			}else{
				return this.getId().equals(testpaperItem.getId());
			}
		}
		else
			return false;

	}

	@Override
	public String toString() {
		return "TestpaperItem [ID="+this.getId()+",score="+score+"]";
	}

	@Transient
	public Long getParentQuestionID() {
		return parentQuestionID;
	}

	public void setParentQuestionID(Long parentQuestionID) {
		this.parentQuestionID = parentQuestionID;
	}

	@Transient
	public String getStemToBlanks() {
		String stem = this.question.getStem();
		if(stem!=null&&this.getQuestionType().equals(QuestionType.blanks)){
			if(this.parent!=null){
				stem = stem.replaceAll("\\[(.*?)\\]", 
						"<input type='text' name='testpaperItem_"+this.getParent().getTestpaperChapter().getId()+"_"+this.question.getId()+"_"+this.getId()+"_"+this.getScore().intValue()+"' class='ex_text'/>"); 
			}else{
				stem = stem.replaceAll("\\[(.*?)\\]", 
						"<input type='text' name='testpaperItem_"+testpaperChapter.getId()+"_"+this.question.getId()+"_"+this.getId()+"_"+this.getScore().intValue()+"' class='ex_text'/>"); //TODO 数值转换问题
			}
		}
		return stem;
	}

	public void setStemToBlanks(String stemToBlanks) {
		this.stemToBlanks = stemToBlanks;
	}
	@Transient
	public TestpaperItemResult getTestpaperItemResult() {
		return testpaperItemResult;
	}

	public void setTestpaperItemResult(TestpaperItemResult testpaperItemResult) {
		this.testpaperItemResult = testpaperItemResult;
	}
	
}
