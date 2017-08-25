package com.sram.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONType;
import com.sram.Constants.Difficulty;
import com.sram.Constants.QuestionType;
import com.sram.util.JsonUtils;
import com.sram.util.MathUtil;
import com.sram.util.UnicodeConverter;

@Entity
@Table(name = "moc_question")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_question_sequence")
@JSONType(ignores = { "toAnswer", "stemToHtml", "toMetasChar" })
public class Question extends OrderEntity {

	private static final long serialVersionUID = 1L;

	/** 题目类型 */
	private QuestionType questionType;

	/** 所属类别的路径 */
	private String treePath;

	/** 题干 */
	private String stem;
	
	/** 分数 */
	private Float score=0F;
	/** 分数 */
	private Float missScore=0F;

	/** 答案 */
	private String answer;

	/** 答案解析 */
	private String analysis;

	/** 题目元--以josn格式存放所有选项 */
	private String metas;

	/** 类别名称--存放的是id */
	private OutlineCategory outlineCategory;

	/** 难度 */
	private Difficulty difficulty;

	/** 材料父 */
	private Question parent;

	/** 子题数量 */
	private Integer subCount;

	/** 完成次数 */
	private Integer finishedTimes;

	/** 成功次数 */
	private Integer passedTimes;

	/** 用户Id */
	private Long userId;
	/** 分数str */
	private String scoreStr;
	/** 分数str */
	private String missScoreStr;

	private String stemToHtml;
	
	private String keyCode;

	/** 难度 **/
	private String difficultyStr;
	/** 是否定项选择 */
	private String isUncertain;
	/** 材料题类型 **/
	private String materialType;
	/** 大纲编号 **/
	private String outlineCode;
	/** 工作表类型（题目类型）注明以哪种题型的解析方法解析 **/
	private String sheetName;
	/**真题来源**/
	private String origin;
	/**前台显示的答案**/
	private String formatAnswer;
	
	private String toCharAnswer;
	
	private String[][] entryAnswer;

	/**
	 * 父条目 的孩子
	 */
	private Set<Question> children = new HashSet<Question>();

	private String[] toMetas;
	private String[] toMetasChar;
	private String toAnswer;
	/** 将题干转成填空题形式 */
	private String stemToBlanks;

	private Set<QuestionFavorite> questionFavorites = new HashSet<QuestionFavorite>();
	private List<TestpaperItemResult> wrongTestpaperItemResults = new ArrayList<TestpaperItemResult>();

	private Set<TestpaperItemResult> itemResults = new HashSet<TestpaperItemResult>();
	
	@Column(length = 64)
	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	/**
	 * 题干
	 * 
	 * @return
	 */
	@Lob
	public String getStem() {
		return stem;
	}

	public void setStem(String stem) {
		this.stem = stem;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	@Column(columnDefinition = "text")
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Lob
	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	@Lob
	public String getMetas() {
		return metas;
	}

	public void setMetas(String metas) {
		this.metas = metas;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "question_outlineCategory")
	public OutlineCategory getOutlineCategory() {
		return outlineCategory;
	}

	public void setOutlineCategory(OutlineCategory outlineCategory) {
		this.outlineCategory = outlineCategory;
	}

	@Column(length = 64)
	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	public Question getParent() {
		return parent;
	}

	public void setParent(Question parent) {
		this.parent = parent;
	}

	public Integer getSubCount() {
		return subCount;
	}

	public void setSubCount(Integer subCount) {
		this.subCount = subCount;
	}

	public Integer getFinishedTimes() {
		return finishedTimes;
	}

	public void setFinishedTimes(Integer finishedTimes) {
		this.finishedTimes = finishedTimes;
	}

	public Integer getPassedTimes() {
		return passedTimes;
	}

	public void setPassedTimes(Integer passedTimes) {
		this.passedTimes = passedTimes;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}

	@Transient
	public String getStemToHtml() {
		if (this.getStem() == null) {
			return null;
		}
		stemToHtml = this.getStem().replaceAll("<!", "")
				.replaceAll("</?[^>]+>", ""); // 剔出<html>的标签
		stemToHtml = stemToHtml.replaceAll("<A>\\s*|\t|\r|\n</A>", "");// 去除字符串中的空格,回车,换行符,制表符
		return stemToHtml;
	}

	public void setStemToHtml(String stemToHtml) {
		this.stemToHtml = stemToHtml;
	}

	@Transient
	public String getIsUncertain() {
		return isUncertain;
	}

	public void setIsUncertain(String isUncertain) {
		this.isUncertain = isUncertain;
	}

	@Transient
	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	@Transient
	public String getOutlineCode() {
		return outlineCode;
	}

	public void setOutlineCode(String outlineCode) {
		this.outlineCode = outlineCode;
	}

	@Transient
	public String getDifficultyStr() {
		return difficultyStr;
	}

	public void setDifficultyStr(String difficultyStr) {
		this.difficultyStr = difficultyStr;
	}

	@Transient
	public String getScoreStr() {
		return scoreStr;
	}

	public void setScoreStr(String scoreStr) {
		this.scoreStr = scoreStr;
	}

	@Transient
	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	@Transient
	public String[] getToMetas() {
		String metas2 = this.getMetas();
		if (metas2 != null && !metas2.trim().equals("")) {
			metas2=UnicodeConverter.fromEncodedUnicode(this.metas.toCharArray(), 0, this.metas.length());
			List<String> object = JSON.parseArray(metas2, String.class);
			String[] strs = new String[object.size()];
			for (int i = 0; i < object.size(); i++) {
				String temp = UnicodeConverter.fromEncodedUnicode(object.get(i).toCharArray(), 0,object.get(i).toCharArray().length);
				strs[i] = (char) (65 + i) + "." + temp;
			}
			return strs;
		} else {
			return null;
		}
	}

	public void setToMetas(String[] toMetas) {
		this.toMetas = toMetas;
	}

	@Transient
	public String getToAnswer() {
		String metas2 = this.getAnswer();
		if(metas2==null || metas2.equals("")){
			return null;
		}
		metas2=UnicodeConverter.fromEncodedUnicode(metas2.toCharArray(), 0, metas2.length());
		List<String> object = JsonUtils.toObject(metas2, List.class);
		if (this.questionType==QuestionType.determine&&object.size() > 0) {
			return object.get(0).equals("false") ? "错误" : "正确";
		}
		if ((this.questionType==QuestionType.choice||this.questionType==QuestionType.single_choice||
				this.questionType==QuestionType.uncertain_choice||
				this.questionType==QuestionType.uncertain_singlechoice)&&(metas2 != null && !metas2.trim().equals(""))) {
			String temp = "";
			for (String string : object) {
				temp += (char) Integer.parseInt(string);
			}
			return temp;
		}
		if(this.questionType==QuestionType.essay){
			return object.get(0);
		}else {
			return null;
		}
	}

	public void setToAnswer(String toAnswer) {
		this.toAnswer = toAnswer;
	}

	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("order asc")
	public Set<Question> getChildren() {
		return children;
	}

	public void setChildren(Set<Question> children) {
		this.children = children;
	}

	@Transient
	public String[] getToMetasChar() {
		String metas2 = this.getMetas();
		if (metas2 != null && !metas2.trim().equals("")) {
			String[] strs = metas2.replace("\\[", "").replace("\\]", "").split(",");
			for (int i = 0; i < strs.length; i++) {
				strs[i] = (char) (65 + i) + "";
			}
			return strs;
		} else {
			return null;
		}
	}

	public void setToMetasChar(String[] toMetasChar) {
		this.toMetasChar = toMetasChar;
	}

	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
	public Set<QuestionFavorite> getQuestionFavorites() {
		return questionFavorites;
	}

	public void setQuestionFavorites(Set<QuestionFavorite> questionFavorites) {
		this.questionFavorites = questionFavorites;
	}
	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
	@OrderBy("id desc")
	public List<TestpaperItemResult> getWrongTestpaperItemResults() {
		return wrongTestpaperItemResults;
	}

	public void setWrongTestpaperItemResults(
			List<TestpaperItemResult> wrongTestpaperItemResults) {
		this.wrongTestpaperItemResults = wrongTestpaperItemResults;
	}
	
	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
	public Set<TestpaperItemResult> getItemResults() {
		return itemResults;
	}

	public void setItemResults(Set<TestpaperItemResult> itemResults) {
		this.itemResults = itemResults;
	}
	
	public Float getMissScore() {
		return missScore;
	}

	public void setMissScore(Float missScore) {
		this.missScore = missScore;
	}
	
	@Transient
	public String getStemToBlanks() {
		String stem = this.getStem();
		StringBuffer sb= new StringBuffer();
		if(stem!=null&&this.getQuestionType().equals(QuestionType.blanks)){
			List<String> answers = JsonUtils.toObject(this.getAnswer(), List.class);
			String[] str = stem.split("\\[(.*?)\\]");
			boolean lastflag=stem.lastIndexOf("]")!=stem.length()-1;
			for (int i = 0; i < str.length; i++) {
				sb.append(str[i]);
				//2015/5/15 zhaoxiaodong
				if(i==0||(i!=str.length-1&&lastflag)){
					sb.append("<input type='text' name='testpaperItem_' class='ex_text'/>");
				}
				// 2015/5/14 limin end
				//sb.append("<input type='text' name='testpaperItem' value='"+answers.get(i)+"'  class='ex_text'/>");
			}
		}
		return sb.toString();
	}
	public void setStemToBlanks(String stemToBlanks) {
		this.stemToBlanks = stemToBlanks;
	}
	@Transient
	public String getMissScoreStr() {
		return missScoreStr;
	}
	public void setMissScoreStr(String missScoreStr) {
		this.missScoreStr = missScoreStr;
	}
	
	@Column(unique=true,nullable=false)
	public String getKeyCode() {
		StringBuffer buffer=new StringBuffer(this.stem);
		if (this.metas!=null) {
			buffer.append("|"+this.metas);
		}
		//如果是材料题或者材料题的子题则加一个UUID表示不需要虑重
		if (this.parent!=null||this.questionType==QuestionType.material) {
			buffer.append(UUID.randomUUID());
		}
		return MathUtil.MD5Generator(buffer.toString());
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	@Transient
	public String getFormatAnswer() {
		if (this.questionType==QuestionType.single_choice||this.questionType==QuestionType.choice||this.questionType==QuestionType.uncertain_singlechoice||this.questionType==QuestionType.uncertain_choice) {
			this.answer.replaceAll("\"", "");
			Character[] answers = JsonUtils.toObject(this.answer.replaceAll("\"", ""), Character[].class);
	        Arrays.sort(answers);
	        return Arrays.toString(answers).replace("\\[", "").replace("\\]", "");
		}
		String answerString=UnicodeConverter.fromEncodedUnicode(this.answer.toCharArray(), 0,this.answer.length());
		if (questionType==questionType.determine) {
			if (this.answer.replaceAll("\"", "").replaceAll("\\[", "").replaceAll("\\]", "").trim().equals("true")) {
				return"正确";
			}else {
				return "错误";
			}
		}
		if (questionType==questionType.blanks||questionType==questionType.essay) {
			return answerString.replaceAll("\"", "").replaceAll("\\[", "").replaceAll("\\]", "").trim();
		}
		return answerString;
	}

	public void setFormatAnswer(String formatAnswer) {
		this.formatAnswer = formatAnswer;
	}
	@Transient
	public String getToCharAnswer() {
		Character[] answers = JsonUtils.toObject(this.answer.replaceAll("\"", ""), Character[].class);
		Arrays.sort(answers);
		return Arrays.toString(answers);
	}
	
	public void setToCharAnswer(String toCharAnswer) {
		this.toCharAnswer = toCharAnswer;
	}
	@Transient
	public String[][] getEntryAnswer() {
		String answerString=UnicodeConverter.fromEncodedUnicode(this.answer.toCharArray(), 0,this.answer.length());
			String[][] answers = JsonUtils.toObject(answerString,String[][].class);
			return answers;
	}

	public void setEntryAnswer(String[][] entryAnswer) {
		this.entryAnswer = entryAnswer;
	}

}
