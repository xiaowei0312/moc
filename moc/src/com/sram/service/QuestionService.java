package com.sram.service;


import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.Constants.TestpaperType;
import com.sram.entity.GeneratorStrategy;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Question;
import com.sram.entity.Testpaper;
import com.sram.entity.TestpaperItemResult;
public interface QuestionService extends BaseService<Question, Long>{
	/**
	 * 条件查找、筛选返回分页列表
	 * @param pageable
	 * @param stem
	 * @param questionType
	 * @param difficulty
	 * @param IdTreePath(selfId,childrenId...)
	 * @return
	 */
	Page<Question> findPage(Pageable pageable, String stem, String questionType,
			String difficulty, String IdTreePath);

	/**
	 * outlineCategory是否有question
	 * @param outlineCategory
	 */
	Boolean hasQuestionByOutlineCategory(List<OutlineCategory> categories);
	/**
	 * 根据大纲ID查询所有题目及子大纲的所有题目，并且不包的含题目不显示
	 * @param outlineCategoryId
	 * @param idStr 不包的含题目id，如：1,2,4
	 * @return
	 */
	public List<Question> findAllQuestionOfChild(Long outlineCategoryId,String idStr,String questionType,String stem,Integer page);
	public Long findAllQuestionOfChildCount(Long outlineCategoryId,String idStr,String questionType,String stem);
	
    List<Question> findRootList();

    public Map<String, Object> analyzeXLS(MultipartFile multipartFile,String[] sheetNames, String[] sheetTypes,Map<String, String[]> templates) throws Exception;
    public boolean saveXLS(List<Question> questions,List<List<Question>> materialList );
    public Testpaper getQuestion(Long outlineCategoryId,GeneratorStrategy strategy,TestpaperType testpaperType);
    /**
	 * 查询错题
	 * @param outlineCategoryId
	 * @param userId
	 * @return
	 */
	public List<Question> findWrongQuestions(Long outlineCategoryId, Long userId);
}
