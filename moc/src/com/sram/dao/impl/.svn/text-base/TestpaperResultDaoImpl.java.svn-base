package com.sram.dao.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sram.Constants;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.Constants.Status;
import com.sram.Constants.TestpaperType;
import com.sram.dao.TestpaperResultDao;
import com.sram.entity.TestpaperResult;
@Repository("testpaperResultDaoImpl")
public class TestpaperResultDaoImpl extends BaseDaoImpl<TestpaperResult, Long> implements TestpaperResultDao{
	
	public Page<TestpaperResult> findPage(Pageable pageable,String testpaperType,String testpaperStatu, String memberName) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TestpaperResult> criteriaQuery = criteriaBuilder.createQuery(TestpaperResult.class);
		Root<TestpaperResult> root = criteriaQuery.from(TestpaperResult.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if(memberName!=null && !("").equals(memberName)){
			restrictions=criteriaBuilder.and(restrictions,criteriaBuilder.like(root.get("member").<String> get("name"), "%"+memberName+"%"));
		}
		if(testpaperType!=null && !("").equals(testpaperType)){
			restrictions=criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("testpaper").<String> get("testpaperType"),TestpaperType.valueOf(testpaperType).ordinal()));
		}
		if(testpaperStatu!=null && !("").equals(testpaperStatu)){
			restrictions=criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("status"),Constants.Status.valueOf(testpaperStatu).ordinal()));
		}
		criteriaQuery.where(restrictions);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createDate")));
		return super.findPage(criteriaQuery, pageable);
	}

	public List<TestpaperResult> findAll() {
		TypedQuery<TestpaperResult> query;
		String jpql = "select t from TestpaperResult t ";
		query = entityManager.createQuery(jpql, TestpaperResult.class).setFlushMode(FlushModeType.COMMIT);
		return query.getResultList();
	}

	public TestpaperResult findTestpaperResultById(Long testpaperResultId) {
		String jpql="select t from TestpaperResult t  " +
		" left join fetch t.testpaper testpaper " +
		" left join fetch testpaper.outlineCategory " +
		" where t.id=:testpaperResultId";
		TypedQuery<TestpaperResult> query = entityManager.createQuery(jpql, TestpaperResult.class).setFlushMode(FlushModeType.COMMIT).setParameter("testpaperResultId", testpaperResultId);
		
		//2015,5,11－－－荣刚平－－－捕获异常
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 根据大纲ID,做卷人ID，试卷类型查询试卷
	 * @param outlineCategoryID
	 * @return
	 */
	public List<TestpaperResult> findTestpaperResult(Long outlineCategoryID,Long userId) {
		List<Object> list = new ArrayList<Object>();
		String jpql=" select t from TestpaperResult t  " +
		" left join t.testpaper.outlineCategory outline" +
		" where t.userId=? "+
		" and  ( outline.treePath like ?  or outline.id=? ) ";
		
		list.add(userId);
		list.add("%,"+outlineCategoryID+",%");
		list.add(outlineCategoryID);
		return this.createQuery(jpql, list);
	}
	
	public TestpaperResult findTestpaperWithTestpaper(Long testpaperResultId) {
		String jpql="select t from TestpaperResult t  " +
		" left join fetch t.testpaper" +
		" where t.id=:testpaperResultId";
		TypedQuery<TestpaperResult> query = entityManager.createQuery(jpql, TestpaperResult.class).setParameter("testpaperResultId", testpaperResultId);
		
		//2015,5,11---荣刚平---捕获异常
		try {
			return query.getResultList().get(0);
		} catch (Exception e) {
			return null;
		}
	}

	public List<TestpaperResult> findTestpaperResultByUserId(Long userId,Integer page) {
		String jpql=" select t from TestpaperResult t  " +
		" left join fetch t.testpaper testpaper" +
		" left join fetch testpaper.outlineCategory outline" +
		" where t.userId=? order by t.beginDate desc";
		
		List<Object> list = new ArrayList<Object>();
		list.add(userId);
		return this.createQueryPage(jpql, page, 10, list);
	}
	public Long findTestpaperResultCountByUserId(Long userId) {
		
		String jpql = "select count(*) from TestpaperResult t   where t.userId=:userId";
		Long count = entityManager.createQuery(jpql, Long.class).setParameter("userId", userId).getSingleResult();
		return count;
	}

	public void udpateTestpaperRulstEndTime(Long testpaperResultId) {
		TestpaperResult testpaperResult = entityManager.find(TestpaperResult.class, testpaperResultId);
		testpaperResult.setEndDate(new Date());
		entityManager.merge(testpaperResult);
	}
	
	public List<Object[]> findScores(Long userId,Long rootOutLineCategory){
		StringBuffer buffer=new StringBuffer("select (result.objectiveScore+result.subjectiveScore) from TestpaperResult result");
		buffer.append(" left join result.testpaper as paper ");
		buffer.append(" where result.member.id=:userId ");
		buffer.append(" and paper.outlineCategory.id=:rootOutLineCategory");
		buffer.append(" and result.status="+com.sram.Constants.Status.finished.ordinal());
		buffer.append(" and paper.testpaperType in(");
		buffer.append(TestpaperType.genrationexam.ordinal()+",");
		buffer.append(TestpaperType.oldexam.ordinal()+")");
		List<Object[]> list = this.entityManager.createQuery(buffer.toString(),Object[].class)
				.setParameter("userId",userId)
				.setParameter("rootOutLineCategory",rootOutLineCategory)
				.getResultList();
		return list;
	}
	
	public int findRanking(Long userId,Long rootOutLineCategory){
		
		StringBuffer buffer=new StringBuffer("SELECT COUNT(*) FROM ( ");
		buffer.append(" SELECT SUM(allResult.total_item_count)");
		buffer.append(" FROM moc_testpaper_result allResult");
		buffer.append(" WHERE allResult.root_outline_category =:root1 ");
		buffer.append(" and allResult.status="+com.sram.Constants.Status.finished.ordinal());
		buffer.append(" GROUP BY allResult.user_id ");
		buffer.append(" HAVING sum(allResult.total_item_count) >( ");
		buffer.append(" SELECT COALESCE(SUM(r.total_item_count),0) AS myscore ");
		buffer.append(" FROM moc_testpaper_result r ");
		buffer.append(" WHERE r.user_id =:userId AND root_outline_category=:root2 ");
		buffer.append(" and r.status="+com.sram.Constants.Status.finished.ordinal()+")) as beyond ");
 		Object singleResult = this.entityManager.createNativeQuery(buffer.toString())
		.setParameter("root1",rootOutLineCategory)
		.setParameter("userId",userId)
		.setParameter("root2",rootOutLineCategory)
		.getSingleResult();
 		BigInteger result=(BigInteger) singleResult;
 		return result.intValue();
	}
	
	public int findMemberCountByOutlineCategory(Long outlineCategoryID) {
		StringBuffer buffer=new StringBuffer("SELECT COUNT(*) FROM ( ");
		buffer.append(" SELECT m.id FROM moc_member m ");
		buffer.append("	RIGHT OUTER JOIN moc_testpaper_result result ON result.user_id = m.id");
		buffer.append("	WHERE result.root_outline_category=:outlineCategoryID");
		buffer.append(" and result.status="+com.sram.Constants.Status.finished.ordinal());
		buffer.append("	GROUP BY m.id ) AS t");
		Object singleResult = this.entityManager.createNativeQuery(buffer.toString())
				.setParameter("outlineCategoryID",outlineCategoryID)
				.getSingleResult();
		 		BigInteger result=(BigInteger) singleResult;
		 		return result.intValue();
	}

	public Double[] findAvgCountByOutlineCategory(Long outlineCategoryID) {
		List<Object> parameters = new ArrayList<Object>();
		parameters.add(outlineCategoryID);
		StringBuffer buffer=new StringBuffer("SELECT COALESCE(ROUND(SUM(t.usercount) / COUNT(*),0),0),COALESCE(ROUND(SUM(t.av) / COUNT(*),2),0) FROM(");
		buffer.append(" SELECT SUM(result.total_item_count) AS usercount,AVG(result.score / paper.score) av ");
		buffer.append(" FROM moc_member m ");
		buffer.append(" RIGHT OUTER JOIN moc_testpaper_result result ON result.user_id = m.id ");
		buffer.append(" RIGHT OUTER JOIN moc_testpaper paper on result.testpaper_id=paper.id ");
		buffer.append(" WHERE result.root_outline_category=?");
		buffer.append(" and result.status="+com.sram.Constants.Status.finished.ordinal());
		buffer.append(" 	GROUP BY m.id ) AS t ");
		List<Object[]> list = createNativeQuery(buffer.toString(), parameters);
		Object[] objects = list.get(0);
		BigDecimal avgCount=(BigDecimal) objects[0];
		Double avgScore=(Double) objects[1];
		Double[] data={avgCount.doubleValue(),avgScore};
		return data;
	}
	
	public double findAvgScoreByUserIdAndRootOutlineCategory(Long userId,Long rootOutlineCategory){
		StringBuffer buffer=new StringBuffer("SELECT COALESCE(ROUND(avg(myResult.score/paper.score),2),0) ");
		buffer.append(" FROM moc_testpaper_result myResult ");
		buffer.append(" LEFT  JOIN moc_testpaper  paper ON paper.id=myResult.testpaper_id");
		buffer.append(" WHERE myResult.user_id =:userId AND myResult.root_outline_category =:rootOutlineCategory");
		buffer.append(" and myResult.status="+com.sram.Constants.Status.finished.ordinal());
		buffer.append(" GROUP BY myResult.user_id");
		Object singleResult;
		try {
		 singleResult = this.entityManager.createNativeQuery(buffer.toString())
				.setParameter("userId",userId)
				.setParameter("rootOutlineCategory",rootOutlineCategory)
				.getSingleResult();
		} catch (NoResultException e) {
			return 0;
		}
				Double score=(Double) singleResult;
		 		return score;
	}
	/**
	 * <p>功能:查找当前用户预测分排名</p> 
	 * @author 杨楷
	 * @date 2015年4月21日 下午6:36:46 
	 * @param userId
	 * @param rootOutLineCategory
	 * @return
	 */
	public int findScoreRanking(Long userId,Long rootOutLineCategory){
		
		StringBuffer buffer=new StringBuffer("SELECT COUNT(*) FROM ( ");
		buffer.append(" SELECT AVG(allResult.score / allPaper.score)");
		buffer.append(" FROM moc_testpaper_result allResult");
		buffer.append(" LEFT OUTER JOIN moc_testpaper allPaper ON allResult.testpaper_id=allPaper.id");
		buffer.append(" WHERE allResult.root_outline_category =:root1 ");
		buffer.append(" and allResult.status="+com.sram.Constants.Status.finished.ordinal());
		buffer.append(" GROUP BY allResult.user_id ");
		buffer.append(" HAVING AVG((allResult.objective_score+allResult.subjective_score)/allResult.score) >( ");
		buffer.append(" SELECT COALESCE(AVG(r.score / myPaper.score), 0) AS myscore ");
		buffer.append(" FROM moc_testpaper_result r ");
		buffer.append(" LEFT OUTER JOIN moc_testpaper myPaper ON r.testpaper_id=myPaper.id ");
		buffer.append(" WHERE r.user_id =:userId AND root_outline_category=:root2 ");
		buffer.append(" and r.status="+com.sram.Constants.Status.finished.ordinal()+")) as beyond ");
		Object singleResult;
		try {
 		singleResult = this.entityManager.createNativeQuery(buffer.toString())
		.setParameter("root1",rootOutLineCategory)
		.setParameter("userId",userId)
		.setParameter("root2",rootOutLineCategory)
		.getSingleResult();
		} catch (NoResultException e) {
			return 0;
		}
 		BigInteger result=(BigInteger) singleResult;
 		return result.intValue();
	}

	public List<TestpaperResult> findTestpaperResult(Long outlineCategoryID,
			Long userId, Integer page) {
		String jpql=" select t from TestpaperResult t  " +
		" left join fetch t.testpaper testpaper" +
		" where t.userId=? and t.rootOutlineCategory=? order by t.beginDate desc";
		
		List<Object> list = new ArrayList<Object>();
		list.add(userId);
		list.add(outlineCategoryID);
		return this.createQueryPage(jpql, page, 10, list);
	}

	public Long findTestpaperResultCount(Long outlineCategoryID, Long userId) {
		String jpql = "select count(*) from TestpaperResult t" +
					"   where t.userId=:userId and (t.rootOutlineCategory=:outlineCategoryID )";
		Long count = entityManager.createQuery(jpql, Long.class)
		.setParameter("userId", userId)
		.setParameter("outlineCategoryID", outlineCategoryID)
		.getSingleResult();
		return count;
	}
	
	/**
	 * <p>功能:查找当前用户在特定大纲下和特定试卷类型下的试卷数量</p> 
	 * @author 杨楷
	 * @date 2015年5月4日21:53:07
	 * @return
	 */
	public int findTestpaperResultCount(Long userId,Long outLineCategoryID,TestpaperType testpaperType){
		
		StringBuffer buffer=new StringBuffer("SELECT COALESCE(COUNT(*),0) FROM ");
		buffer.append(" moc_testpaper_result r");
		buffer.append(" LEFT OUTER JOIN moc_testpaper t ON t.id = r.testpaper_id");
		buffer.append(" WHERE r.user_id =:userId ");
		buffer.append(" AND r.outline_categoryid =:outLineCategoryID ");
		buffer.append(" AND t.testpaper_type =:testpaperType");
		Object singleResult;
		try {
 		singleResult = this.entityManager.createNativeQuery(buffer.toString())
		.setParameter("userId",userId)
		.setParameter("outLineCategoryID",outLineCategoryID)
		.setParameter("testpaperType",testpaperType.ordinal())
		.getSingleResult();
		} catch (NoResultException e) {
			return 0;
		}
 		BigInteger result=(BigInteger) singleResult;
 		return result.intValue();
	}

	public Page<TestpaperResult> findTestpaperPage(Pageable pageable,
			Long outlineCategoryId, Long userId) {
		// TODO Auto-generated method stub
		String jpql=" select t from TestpaperResult t  " +
		" left join fetch t.outlineCategory outline" +
		" where t.userId=? and (outline.id=? or outline.treePath like ? ) order by t.beginDate desc";
		
		List<Object> list = new ArrayList<Object>();
		list.add(userId);
		list.add(outlineCategoryId);
		list.add("%,"+outlineCategoryId+",");
		long total=findTestpaperResultCount(outlineCategoryId,userId);
		int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		List<TestpaperResult> resultList=createQueryPage(jpql.toString(),pageable.getPageNumber(),pageable.getPageSize(),list);
		return new Page<TestpaperResult>(resultList,total,pageable);
	}
	
	/**
	 * <p>功能:更新做卷时间</p> 
	 * @author 杨楷
	 * @date 2015年5月8日 下午2:23:34 
	 * @param testpaperResultId
	 * @param usedTime
	 */
	public void updateUsedTime(Long testpaperResultId,Integer usedTime){
		String buffer = "update TestpaperResult result "
				+ " set result.usedTime=:usedTime"
				+ " where result.id=:testpaperResultId";
		entityManager
				.createQuery(buffer)
				.setParameter("usedTime", usedTime)
				.setParameter("testpaperResultId",
						testpaperResultId)
				.setFlushMode(FlushModeType.COMMIT).executeUpdate();
	}
}
