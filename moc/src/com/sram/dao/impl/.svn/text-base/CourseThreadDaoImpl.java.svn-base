package com.sram.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.CourseThreadDao;
import com.sram.entity.CourseThread;
import com.sram.entity.CourseThread.Type;

@Repository("courseThreadDaoImpl")
public class CourseThreadDaoImpl extends BaseDaoImpl<CourseThread, Long>
		implements CourseThreadDao {

	public Page<Object[]> findPage(Integer hasPosts, Type type,
			Integer isStick, Integer isElite, Integer isClosed, Long courseId,
			Pageable pageable) {
		boolean b;

		List<Object> conditionList = new ArrayList<Object>();
		String searchProperty = pageable.getSearchProperty();
		String searchValue = pageable.getSearchValue();
		StringBuffer sqlCommon = new StringBuffer();
		StringBuffer sqlHeader = new StringBuffer();
		sqlHeader
				.append("select t.id,t.title as title,t.content,t.is_closed,t.create_date,t.latest_post_time")
				.append(",m.username")
				.append(",c.title as ctitle")
				.append(",L.title as ltitle")
				.append(",tc.name ")
				;
		sqlCommon
				.append(" from moc_course_thread t ")
				.append(" left join moc_course_lesson L on t.lesson_id=L.id ")
				.append(" left join moc_course c on t.course_id = c.id ")
				.append(" left join moc_member m on t.user_id = m.id ")
				.append(" left join moc_course_category tc on t.course_category_id =tc.id ")
				.append(" where 1=1 ");
		if (courseId != null) {
			sqlCommon.append(" and c.id = ? ");
			conditionList.add(courseId);
		}
		if (hasPosts != null) {
			if (hasPosts == 0) {

				// 是0的话查询没有回复的、否则查询有回复的
				sqlCommon.append(" and (t.post_num=null or t.post_num=0) ");
			} else {

				// 有回复的
				sqlCommon.append(" and t.post_num>0 ");
			}
		}
		if (type != null) {
			sqlCommon.append(" and t.type =? ");
			conditionList.add(type);
		}
		if (isStick != null && (isStick == 1 || isStick == 0)) {

			// 1为置顶
			b = isStick == 1 ? true : false;
			sqlCommon.append(" and t.is_stick = ? ");
			conditionList.add(b);
		}
		if (isElite != null && (isElite == 1 || isElite == 0)) {

			// 1为精华
			b = isElite == 1 ? true : false;
			sqlCommon.append(" and t.is_elite =? ");
			conditionList.add(b);
		}
		if (isClosed != null && (isClosed == 1 || isClosed == 0)) {

			// 1是开启
			b = isClosed == 1 ? false : true;
			sqlCommon.append(" and t.is_closed =? ");
			conditionList.add(b);
		}

		if (StringUtils.isNotEmpty(searchProperty)
				&& StringUtils.isNotEmpty(searchValue)) {
			if (searchProperty.equals("course")) {
				sqlCommon.append(" and c.title like '%" + searchValue + "%'");
			} else if (searchProperty.equals("courseLesson")) {
				sqlCommon.append(" and L.title like '%" + searchValue + "%'");
			} else if (searchProperty.equals("member")) {
				sqlCommon
						.append(" and m.username like '%" + searchValue + "%'");
			} else if ("title".equals(searchProperty)
					|| "content".endsWith(searchProperty)) {
				sqlCommon.append(" and t." + searchProperty + " like '%"
						+ searchValue + "%'");
			}
		}
		sqlCommon
				.append(" order by t.create_date desc,t.latest_post_time desc");
		Long total = createNativeQueryCount("select count(*) " + sqlCommon,
				conditionList);
		int totalPages = (int) Math.ceil((double) total
				/ (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		List<Object[]> list = createNativeQueryPage(sqlHeader.toString()
				+ sqlCommon, pageable.getPageNumber(), pageable.getPageSize(),
				conditionList);
		return new Page<Object[]>(list, total, pageable);
	}

	public List<CourseThread> getThreads(Long memberId, Long lessonId,
			Long courseId, Integer count) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseThread> criteriqQuery = criteriaBuilder
				.createQuery(CourseThread.class);
		Root<CourseThread> root = criteriqQuery.from(CourseThread.class);
		criteriqQuery.select(root);

		Predicate restrictions = criteriaBuilder.conjunction();

		if (memberId != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("member"), memberId));
		}
		if (lessonId != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("courseLesson"), lessonId));
		}
		if (courseId != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("course"), courseId));
		}
		criteriqQuery.where(restrictions);
		return super.findList(criteriqQuery, null, count, null, null);
	}

	public Page<CourseThread> findMyQuestionPage(Pageable pageable,
			Long memberId) {
		// TODO Auto-generated method stub
		StringBuffer jpql = new StringBuffer();
		jpql.append("select c from CourseThread c where 1=1");
		jpql.append(" and c.isClosed=false  and c.member.id=:memberId");
		jpql.append(" order by createDate desc");
		long total = myQuestionCount(memberId);
		int totalPages = (int) Math.ceil((double) total
				/ (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		@SuppressWarnings("unchecked")
		List<CourseThread> list = entityManager
				.createQuery(jpql.toString())
				.setFlushMode(FlushModeType.COMMIT)
				.setParameter("memberId", memberId)
				.setFirstResult(
						(pageable.getPageNumber() - 1) * pageable.getPageSize())
				.setMaxResults(pageable.getPageSize()).getResultList();
		return new Page<CourseThread>(list, total, pageable);
	}

	private long myQuestionCount(Long memberId) {
		// TODO Auto-generated method stub
		StringBuffer jpql = new StringBuffer();
		jpql.append("select count(*) from CourseThread c where 1=1");
		jpql.append(" and c.isClosed=false and c.member.id=:memberId");
		long count = entityManager.createQuery(jpql.toString(), Long.class)
				.setFlushMode(FlushModeType.COMMIT)
				.setParameter("memberId", memberId).getSingleResult();
		return count;
	}

	public Page<Object[]> findMyAnswerPage(Pageable pageable, Long memberId) {
		// TODO Auto-generated method stub
		List<Object> conditionList = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT 	m.id as id,m.title as title,ml.title as lessonTitle,m.create_date as createDate,m.post_num as postNum,m.hit_num as hitNum,c.title as courseTitle");
		sql.append(",ml.id As lessonId,mf.id As mediaId,c.id As courseId");
		sql.append(" FROM moc_course_thread m LEFT JOIN moc_course_lesson ml");
		sql.append(" ON  ml.id=m.lesson_id");
		sql.append(" LEFT JOIN moc_course c");
		sql.append(" ON ml.lesson_course=c.id");
		sql.append(" LEFT JOIN moc_course_thread_post cp");
		sql.append(" ON m.id = cp.thread_id");
		sql.append(" LEFT JOIN moc_upload_files mf");
		sql.append(" ON ml.media_id=mf.id");
		sql.append(" WHERE 1=1 AND m.is_closed = 0");
		if (memberId != null && memberId != 0) {
			sql.append(" AND cp.user_id=?");
			conditionList.add(memberId);
		}
		sql.append(" group by m.id");
		sql.append(" order by m.create_date");
		long total = myAnswerCount(memberId);
		int totalPages = (int) Math.ceil((double) total
				/ (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		List<Object[]> list = createNativeQueryPage(sql.toString(),
				pageable.getPageNumber(), pageable.getPageSize(), conditionList);
		return new Page<Object[]>(list, total, pageable);
	}

	private long myAnswerCount(Long memberId) {
		// TODO Auto-generated method stub
		List<Object> conditionList = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT count(distinct m.id)");
		sql.append(" FROM 	moc_course_thread m LEFT JOIN moc_course_thread_post cp");
		sql.append(" ON m.id = cp.thread_id");
		sql.append(" WHERE 1=1 AND m.is_closed = 0");
		if (memberId != null && memberId != 0) {
			sql.append(" AND cp.user_id=?");
			conditionList.add(memberId);
		}
		return createNativeQueryCount(sql.toString(), conditionList);
	}

	public Page<Object[]> findAllQuestionPage(Pageable pageable,
			String threadContent, String tabFlag, Long categoryId) {
		// TODO Auto-generated method stub
		List<Object> conditionList = new ArrayList<Object>();
		StringBuffer jpql = new StringBuffer();
		jpql.append("select ct,m.username from CourseThread ct left join fetch ct.latestPostMember m");
		jpql.append(" where 1=1 ");
		if (threadContent != null && !("").equals(threadContent)) {
			jpql.append(" and ct.content like ?");
			conditionList.add("%" + threadContent + "%");
		}
		if (categoryId != null && categoryId != 0) {
			jpql.append(" and ct.courseCategory.id=?");
			conditionList.add(categoryId);
		}
		jpql.append(" and ct.isClosed=false");
		if (("waitReply").equals(tabFlag)) {
			jpql.append(" and ct.postNum=0");
		}
		if (("hotThread").equals(tabFlag)) {
			jpql.append(" order by ct.hitNum desc");
		} else {
			jpql.append(" order by ct.createDate desc");
		}
		long total = AllQuestionCount(threadContent, categoryId);
		int totalPages = (int) Math.ceil((double) total
				/ (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		List<Object[]> list = createQueryPageObject(jpql.toString(),
				pageable.getPageNumber(), pageable.getPageSize(), conditionList);
		return new Page<Object[]>(list, total, pageable);
	}

	private long AllQuestionCount(String threadContent, Long categoryId) {
		// TODO Auto-generated method stub
		StringBuffer jpql = new StringBuffer();
		List<Object> conditionList = new ArrayList<Object>();
		jpql.append("select count(*) from CourseThread c");
		jpql.append(" where 1=1 and c.isClosed=false");
		if (threadContent != null && !("").equals(threadContent)) {
			jpql.append(" and c.content like ?");
			conditionList.add(threadContent);
		}
		if (categoryId != null && categoryId != 0) {
			jpql.append(" and c.courseCategory.id=?");
			conditionList.add(categoryId);
		}
		long count = createQueryCount(jpql.toString(), conditionList);

		return count;
	}

	public void changeStatus(CourseThread courseThread) {
		String jpql = "update CourseThread c set c.isClosed = :isClosed where c.id = :id";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT)
				.setParameter("isClosed", courseThread.getIsClosed())
				.setParameter("id", courseThread.getId()).executeUpdate();
	}

	public void updateHitNum(CourseThread courseThread) {
		// TODO Auto-generated method stub
		String jpql = "update CourseThread c set c.hitNum=:hitNum where c.id=:id";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT)
				.setParameter("hitNum", courseThread.getHitNum() + 1)
				.setParameter("id", courseThread.getId()).executeUpdate();
	}

	public void updatePostNum(CourseThread courseThread, Long memberId) {
		// TODO Auto-generated method stub
		String jpql = "update CourseThread c set c.postNum=:postNum,c.latestPostMember.id"
				+ "=:memberId,c.latestPostTime=:latestPostTime   where c.id=:id";
		Integer postNum=getPostNum(courseThread.getId());
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT)
				.setParameter("postNum",  postNum==null?0:postNum+ 1)
				.setParameter("memberId", memberId)
				.setParameter("latestPostTime", new Date())
				.setParameter("id", courseThread.getId()).executeUpdate();
	}
	private Integer getPostNum(Long threadId){
		String jpql="select c.postNum from CourseThread c where c.id =:threadId ";
		
		return (Integer) entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT)
			.setParameter("threadId", threadId)
			.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<CourseThread> findHostThread() {
		// TODO Auto-generated method stub
		String jpql = "from CourseThread c order by c.hitNum desc";
		return entityManager.createQuery(jpql.toString())
				.setFlushMode(FlushModeType.COMMIT).setFirstResult(0)
				.setMaxResults(5).getResultList();
	}

	public Page<CourseThread> findList(Integer hasPosts, Type type,
			Integer isStick, Integer isElite, Integer isClosed, Long courseId,
			Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CourseThread> criteriaQuery = criteriaBuilder
				.createQuery(CourseThread.class);
		Root<CourseThread> root = criteriaQuery.from(CourseThread.class);
		criteriaQuery.select(root);

		Predicate restrictions = criteriaBuilder.conjunction();

		if (courseId != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("course"), courseId));
		}
		if (hasPosts != null) {
			if (hasPosts == 0) {

				// 是0的话查询没有回复的、否则查询有回复的
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder.or(
								root.get("postNum").isNull(),
								criteriaBuilder.le(
										root.<Integer> get("postNum"), 0)));
			} else {

				// 有回复的
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder.ge(root.<Integer> get("postNum"), 1));
			}
		}
		if (type != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("type"), type));
		}
		if (isStick != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.notEqual(root.get("isStick"), 0));
		}
		if (isElite != null) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.notEqual(root.get("isElite"), 0));
		}
		if (isClosed != null && (isClosed == 1 || isClosed == 0)) {

			// 1是开启
			boolean b = isClosed == 1 ? false : true;
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("isClosed"), b));
		}

		String searchProperty = pageable.getSearchProperty();
		String searchValue = pageable.getSearchValue();
		if (StringUtils.isNotEmpty(searchProperty)
				&& StringUtils.isNotEmpty(searchValue)) {
			if (searchProperty.equals("course")) {
				restrictions = criteriaBuilder.and(
						restrictions,
						criteriaBuilder.like(
								root.join("course").<String> get("title"), "%"
										+ searchValue + "%"));
			} else if (searchProperty.equals("courseLesson")) {
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder
								.like(root.join("courseLesson").<String> get(
										"title"), "%" + searchValue + "%"));
			} else if (searchProperty.equals("member")) {
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder.like(
								root.join("member").<String> get("username"),
								"%" + searchValue + "%"));
			} else {
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder.like(root.<String> get(searchProperty),
								"%" + searchValue + "%"));
			}
		}

		criteriaQuery.where(restrictions);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("latestPostTime")));
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createDate")));
		TypedQuery<CourseThread> query = entityManager.createQuery(
				criteriaQuery).setFlushMode(FlushModeType.COMMIT);
		query.setFirstResult((pageable.getPageNumber() - 1)
				* pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());

		// 2---无用，只是借用了page
		return new Page<CourseThread>(query.getResultList(), 2, pageable);
	}

	@SuppressWarnings("unchecked")
	public List<CourseThread> findRelateThreads(Long categoryId, int pageSize) {
		// TODO Auto-generated method stub
		StringBuilder jpql = new StringBuilder();
		jpql.append("from CourseThread c where c.courseCategory.id=:categoryId");
		Query query = entityManager.createQuery(jpql.toString()).setFlushMode(
				FlushModeType.COMMIT);
		query.setParameter("categoryId", categoryId);
		query.setFirstResult(0).setMaxResults(pageSize);
		return query.getResultList();
	}

	public String findThreadTitle(Long threadId) {
		String jpql = "select t.title from CourseThread t where t.id =:threadId";
		
		return (String) entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT)
				.setParameter("threadId", threadId).getSingleResult();
	}

}
