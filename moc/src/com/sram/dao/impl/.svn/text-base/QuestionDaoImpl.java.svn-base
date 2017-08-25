package com.sram.dao.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.sf.ehcache.search.expression.And;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.sram.Constants.Difficulty;
import com.sram.Constants.QuestionType;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.QuestionDao;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Question;
import com.sram.entity.TestpaperItemResult;
@Repository("questionDaoImpl")
public class QuestionDaoImpl extends BaseDaoImpl<Question, Long> implements QuestionDao{
	/**
	 * 设置值并保存
	 * @param product
	 *            商品
	 */
	@Override
	public void persist(Question question) {
		Assert.notNull(question);
		super.persist(question);
	}
	public Page<Question> findPage(Pageable pageable, String stem,
			String questionType, String difficulty, String IdTreePath) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Question> criteriaQuery = criteriaBuilder.createQuery(Question.class);
		Root<Question> root = criteriaQuery.from(Question.class);
		criteriaQuery.select(root);
		
		Predicate restrictions = criteriaBuilder.conjunction();
		if(stem !=null){
			restrictions = criteriaBuilder.and(restrictions,criteriaBuilder.like(root.<String>get("stem"), "%"+stem+"%"));
		}
		if(questionType !=null && questionType.equals("choices")){
			restrictions = criteriaBuilder.and(restrictions,criteriaBuilder.or(
			criteriaBuilder.equal(root.get("questionType"), QuestionType.single_choice.ordinal())
			,criteriaBuilder.equal(root.get("questionType"), QuestionType.choice.ordinal())
			,criteriaBuilder.equal(root.get("questionType"), QuestionType.uncertain_choice.ordinal())
			));
			
		}else if(questionType !=null){
			restrictions = criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("questionType"), QuestionType.valueOf(questionType).ordinal()));
		}
		if(difficulty != null){
			restrictions = criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("difficulty"), Difficulty.valueOf(difficulty).ordinal()));
		}
		if(IdTreePath != null){
			
			//temp是treePath,,第一位是自己的id
			String temp=IdTreePath.substring(IdTreePath.indexOf(","));
			if(temp.length()==1){
				//是根目录
				Long rootId=Long.parseLong(IdTreePath.substring(0, IdTreePath.indexOf(",")));
				restrictions = criteriaBuilder.or(criteriaBuilder.like(root.<String>get("treePath"), "%,"+rootId+",%")
						,criteriaBuilder.equal(root.get("outlineCategory"), rootId));
			}else{
				//是子节点
				restrictions = criteriaBuilder.and(restrictions,criteriaBuilder
						.like(root.<String>get("treePath"), "%"+temp+"%"));
			}
			
		}
		
		//将属于材料题的选择题和填空题排除在外---其parent为空
		restrictions = criteriaBuilder.and(restrictions,root.get("parent").isNull());
		
		criteriaQuery.where(restrictions);
		/**杨楷修改start**/
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("id")));
		/**杨楷修改end**/
		return super.findPage(criteriaQuery, pageable);
	}
	public Boolean hasQuestionByOutlineCategory(List<OutlineCategory> categories) {
		Long count;
		String jpql="select count(*) from Question q where q.outlineCategory=:OutlineCategory";
		for(OutlineCategory category:categories){
			count=entityManager.createQuery(jpql, Long.class)
			.setParameter("OutlineCategory", category)
			.getSingleResult();
			if(count>0){
				return true;
			}
		}
		return false;
	}
	public List<Question> findRootList(){
		String jpql = "select Question from Question Question where Question.parent=0";
		TypedQuery<Question> query = entityManager.createQuery(jpql, Question.class);
		return query.getResultList();
	}
	public List<OutlineCategory> findParents(OutlineCategory outlineCategory,
			Integer count) {
		if (outlineCategory == null || outlineCategory.getParent() == null) {
			return Collections.<OutlineCategory> emptyList();
		}
		String jpql = "select OutlineCategory from OutlineCategory OutlineCategory where OutlineCategory.id in (:ids) order by OutlineCategory.grade asc";
		TypedQuery<OutlineCategory> query = entityManager.createQuery(jpql, OutlineCategory.class).setFlushMode(FlushModeType.COMMIT).setParameter("ids", outlineCategory.getTreePath());
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}

	public List<Question> findChildren(Question question) {
		TypedQuery<Question> query;
		if (question != null) {
			String jpql = "select q from Question q where q.parent.id =:id order by q.order asc";
			query = entityManager.createQuery(jpql, Question.class).setFlushMode(FlushModeType.COMMIT).setParameter("id", question.getId());
		} else {
			String jpql = "select q from Question q order by q.order asc";
			query = entityManager.createQuery(jpql, Question.class).setFlushMode(FlushModeType.COMMIT);
		}
		return query.getResultList();
	}
	public List<Question> findAll() {
		TypedQuery<Question> query;
			String jpql = "select q from Question q where q.parent<>null";
			query = entityManager.createQuery(jpql, Question.class).setFlushMode(FlushModeType.COMMIT);
		return query.getResultList();
	}
	/**
	 * 排序商品分类
	 * 
	 * @param productCategories
	 *            商品分类
	 * @param parent
	 *            上级商品分类
	 * @return 商品分类
	 */
	private List<Question> sort(List<Question> questions, Question parent) {
		List<Question> result = new ArrayList<Question>();
		if (questions != null) {
			for (Question Question : questions) {
				if ((Question.getParent() != null && Question.getParent().equals(parent)) || (Question.getParent() == null && parent == null)) {
					result.add(Question);
					result.addAll(sort(questions, Question));
				}
			}
		}
		return result;
	}
	/**
	 * 修改问题列表的treePath
	 */
	public void updateTreePath(String oldTreePath, String newTreePath) {
		String jpql ="update Question set  treePath=REPLACE(treePath,'"+oldTreePath+"','"+newTreePath+"') where  treePath like '%"+oldTreePath+"%'";
		Query query = entityManager.createQuery(jpql);
		query.executeUpdate();
	}
	public List<Question> findAllQuestionOfChild(Long outlineCategoryId,String idStr,String questionType,String stem,Integer page) {
		List<Object> list = new ArrayList<Object>();
		String jpql = " select q from Question q  where " +
				" ( q.treePath like ?  or q.outlineCategory.id=? ) " +
				"  and  q.parent=null ";
		list.add("%,"+outlineCategoryId+",%");
		list.add(outlineCategoryId);
		if(StringUtils.isNotEmpty(idStr)){
			jpql+=" and q.id in(?)";
			list.add(idStr);
		}
		if(StringUtils.isNotEmpty(questionType)){
			jpql+=" and q.questionType=? ";
			list.add(QuestionType.valueOf(questionType));
		}
		if(StringUtils.isNotEmpty(stem)){
			jpql+=" and q.stem like ? ";
			list.add("%"+stem+"%");
		}
		
		return createQueryPage(jpql, page, 15,list);
	}
	public Long findAllQuestionOfChildCount(Long outlineCategoryId,String idStr, String questionType, String stem) {
		List<Object> list = new ArrayList<Object>();
		String jpql = " select count(*) from Question q  where " +
				" ( q.treePath like ?  or q.outlineCategory.id=? ) " +
				"  and  q.parent=null ";
		list.add("%,"+outlineCategoryId+",%");
		list.add(outlineCategoryId);
		if(StringUtils.isNotEmpty(idStr)){
			jpql+=" and q.id in(?)";
			list.add(idStr);
		}
		if(StringUtils.isNotEmpty(questionType)){
			jpql+=" and q.questionType=? ";
			list.add(QuestionType.valueOf(questionType));
		}
		if(StringUtils.isNotEmpty(stem)){
			jpql+=" and q.stem like ? ";
			list.add("%"+stem+"%");
		}
		return createQueryCount(jpql,list);
	}
	
	/**
	 * <p>功能:</p> 
	 * @author 杨楷
	 * @date 2015-3-23 上午09:13:13 
	 * @param outlineCategoryId 
	 * @param Difficulty 
	 * @param isExcludeChildren 
	 * @param QuestionType 
	 * @return 
	 */
	public List<Long> findIds(Long outlineCategoryId,Difficulty difficulty,boolean isExcludeChildren,QuestionType... questionTypes){
		StringBuffer jp= new StringBuffer("select q.id from Question q where (q.outlineCategory.id=:outlineCategoryId or q.treePath like :treePath) and q.questionType in(");
		for (QuestionType questionType : questionTypes) {
			jp.append(questionType.ordinal()+",");
		}
		String jpql=jp.toString();
		jpql=jpql.substring(0,jpql.length()-1)+")";
		
		if (isExcludeChildren) {
			jpql+=" and q.parent.id is null";
			}
		if (difficulty!=null) {
			jpql+=" and q.difficulty=:difficulty";
		return	entityManager.createQuery(jpql, Long.class).setParameter("outlineCategoryId", outlineCategoryId).setParameter("treePath", "%,"+outlineCategoryId+",%").setParameter("difficulty", difficulty).getResultList();
		}
		return entityManager.createQuery(jpql, Long.class).setParameter("outlineCategoryId", outlineCategoryId).setParameter("treePath", "%,"+outlineCategoryId+",%").getResultList();
	}
	/**
	 * <p>功能:根据父亲id查找特定题型子题</p> 
	 * @author 杨楷
	 * @date 2015-3-23 上午09:13:13 
	 * @param parentId 父亲id
	 * @param questionTypes 题目类型
	 * @return 
	 */
	public List<Question> findMaterialChildren(Long parentId,QuestionType...questionTypes){
		StringBuffer jp= new StringBuffer("select q from Question q where q.parent.id =:id and q.questionType in(");
		for (QuestionType questionType : questionTypes) {
			jp.append(questionType.ordinal()+",");
		}
		String jpql=jp.toString();
		jpql=jpql.substring(0,jpql.length()-1)+")";
		return entityManager.createQuery(jpql, Question.class).setFlushMode(FlushModeType.COMMIT).setParameter("id", parentId).getResultList();
	}
	
	/**
	 * 根据大纲id。查找questionID且排除特定id （补刀用）
	 */
	public List<Long> findIdsIgnoreIds(Long outlineCategoryId,Integer[] questionTypes,Long...ids){
		List<Object> list = new ArrayList<Object>();
		StringBuffer jp= new StringBuffer("select q.id from Question q where 1=1");
		if(questionTypes!=null && questionTypes.length>0){
			jp.append(" and q.questionType in(");
			for (Integer questionType : questionTypes) {
				jp.append(questionType+",");
			}
			jp=new StringBuffer(jp.substring(0, jp.lastIndexOf(",")));
			jp.append(")");
		}
		if(ids!=null && ids.length>0){
			jp.append(" and q.id not in(");
			for (Long ignoreId : ids) {
				jp.append(ignoreId+",");
			}
			jp=new StringBuffer(jp.substring(0, jp.lastIndexOf(",")));
			jp.append(")");
		}
		jp.append(" and (q.outlineCategory.id=:outlineCategoryId or q.treePath like :treePath) and q.parent.id is null");
		return entityManager.createQuery(jp.toString(), Long.class).setParameter("outlineCategoryId", outlineCategoryId).setParameter("treePath", "%,"+outlineCategoryId+",%").getResultList();
	}
	
	/**
	 * <p>功能:根据id查找试题list</p> 
	 * @author 杨楷
	 * @date 2015-4-8 上午10:01:30 
	 * @param ids
	 * @return
	 */
	public List<Question> findQuestions(Long...ids){
		StringBuffer jp = new StringBuffer("select q from Question q where 1=1");
		if(ids!=null && ids.length>0){
			jp.append(" and q.id in(");
			for (Long id : ids) {
				jp.append(id+",");
			}
			jp=new StringBuffer(jp.substring(0, jp.lastIndexOf(",")));
			jp.append(")");
			return entityManager.createQuery(jp.toString(),Question.class).getResultList();
		}
		return null;
	}
	/**
	 * 根据大纲ID,用户ID查询错误题目
	 * 
	 * @param outlineCategoryID
	 * @return
	 */
	public List<Question> findWrongQuestions(
			Long outlineCategoryId, Long userId) {
		String jpql = "";
		jpql+=" select q from Question q ";
		jpql+=" left join fetch q.wrongTestpaperItemResults itemResult";
		jpql+=" left join fetch itemResult.testpaperResult result";
		jpql+=" left join fetch itemResult.question q1";
		jpql+=" left join fetch q.outlineCategory outline ";
		jpql+= " where  itemResult.userId=:userId and itemResult.status!=:status1  and result.status=:status2";
		jpql+="  and (outline.treePath like :treePath  or outline.id=:outLineId)  ";
		jpql+=" ";
		jpql+=" ORDER BY q.parent asc,q.id asc ";
		EntityManager entityManager2 = entityManager.getEntityManagerFactory().createEntityManager();
		TypedQuery<Question> query = entityManager2
				.createQuery(jpql, Question.class)
				.setParameter("userId", userId)
				.setParameter("status1", com.sram.entity.TestpaperItemResult.Status.right)
				.setParameter("status2", com.sram.Constants.Status.finished)
				.setParameter("treePath", "%," + outlineCategoryId + ",%")
				.setParameter("outLineId", outlineCategoryId);
		List<Question> list = query.getResultList();
		Map<Long,Question> parentMap = new HashMap<Long,Question>();
		List<Question> childrenMap = new ArrayList<Question>();
		Map<Long,Long> parentIdMap = new HashMap<Long,Long>();
		
		for (Question question : list) {
			if(question.getParent()==null){
				parentMap.put(question.getId(), question);
			}else{
				if(!parentIdMap.containsKey(question.getParent().getId())){
					parentMap.get(question.getParent().getId()).setChildren(new HashSet<Question>());	
					parentIdMap.put(question.getParent().getId(), question.getParent().getId());
				}
				parentMap.get(question.getParent().getId()).getChildren().add(question);
				childrenMap.add(question);
			}
		}
		list.removeAll(childrenMap);
		
		return list;
	}
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void independentPersist(Question entity) {
		super.independentPersist(entity);
	}
	
	public Question findByKeyCode(String keyCode){
		List<Object> parameter=new ArrayList<Object>();
		String jpql="select q from Question q where q.keyCode=?";
		parameter.add(keyCode);
		List<Question> query = createQuery(jpql, parameter);
		return query.get(0);
	}
}
