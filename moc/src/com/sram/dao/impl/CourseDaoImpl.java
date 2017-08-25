/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.sram.Filter;
import com.sram.Order;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.CourseDao;
import com.sram.entity.Course;
import com.sram.entity.Course.DeadlineNotify;
import com.sram.entity.Course.SerializeMode;
import com.sram.entity.Course.ShowStudentNumType;
import com.sram.entity.Course.Status;
import com.sram.entity.Course.UserType;
import com.sram.entity.CourseCategory;
import com.sram.entity.Member;
import com.sram.entity.Tag;
import com.sram.entity.Teacher;

/**
 * Dao - 课程
 * 
 * @author Sram Team
 * @version 1.0
 */
@Repository("courseDaoImpl")
public class CourseDaoImpl extends BaseDaoImpl<Course, Long> implements CourseDao {
	
	public Page<Course> findPage(CourseCategory courseCategory, Status status,
			Integer maxStudentNum, Integer recommended, Integer vipLevelId,
			Float price, UserType userType, 
			SerializeMode serializeMode, ShowStudentNumType showStudentNumType, DeadlineNotify deadlineNotify,
			Pageable pageable) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		Root<Course> root = criteriaQuery.from(Course.class);
		criteriaQuery.select(root);
		
		Predicate restrictions = criteriaBuilder.conjunction();
		if (courseCategory != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("courseCategory"), courseCategory));
		}
		if (status != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("status"), status));
		}
		if (maxStudentNum !=null){
			
		}
		if (recommended !=null){
			//1是查找推荐的课程
			boolean b=recommended==1?true:false;
			restrictions=criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("isRecommend"), b));
		}
		if (vipLevelId !=null){
			
		}
		if (price !=null){
			if(price==0){
				
			}else if(price==1){
				
			}
		}
		if (userType !=null){
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("userType"),userType));
		}
		if (serializeMode != null){
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("serializeMode"), serializeMode));
		}
		if (showStudentNumType != null){
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("showStudentNumType"),showStudentNumType));
		}
		if (deadlineNotify != null){
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("deadlineNotify"),deadlineNotify));
		}
		criteriaQuery.where(restrictions);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createDate")));
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("order")));
		return super.findPage(criteriaQuery, pageable);
	}
	
	public List<Course> findByTeacher(Teacher teacher) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		Root<Course> root = criteriaQuery.from(Course.class);
		
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.<String>get("teacherIds"), "%"+teacher.getId()+"%"));
		criteriaQuery.where(restrictions);
		return super.findList(criteriaQuery, null, null, null, null);
	}
	
	/**
	 * 找到授课教师的ids
	 */
	public Set<Long> findTeacherIds() {
		Set<Long> teachers = new HashSet<Long>();
		
		String jpql = "select teacherIds from Course c where c.teacherIds != null and c.teacherIds != ''";
		List<String> teacherIds =entityManager.createQuery(jpql, String.class).setFlushMode(FlushModeType.COMMIT).getResultList();
		if(teacherIds != null && !teacherIds.isEmpty()){
			for(String ids:teacherIds){
				for(String id: ids.split(",")){
					teachers.add(Long.parseLong(id));
				}
			}
		}
		
		return teachers;
	}

	public void changeStatus(Course course) {
		String jpql = "update Course c set c.status = :status where c.id = :id";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT)
		.setParameter("status", course.getStatus())
		.setParameter("id", course.getId()).executeUpdate();
	}

	public Page<Object[]> findStaPage(Pageable pageable,Long courseCategoryId) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		String searchProperty = pageable.getSearchProperty();
		String searchValue = pageable.getSearchValue();
		StringBuffer jpql=new StringBuffer();
		jpql.append("select  c.id,c.title,c.lesson_num,c.student_num,c.finish_stu_num,sum(cl.learn_time)");
		jpql.append(" from 	moc_course c left join moc_course_lesson_learn cl");
		jpql.append(" on c.id = cl.course_id");
		jpql.append(" left join moc_admin a");
		jpql.append(" on c.user_id=a.id");
		jpql.append(" where 1=1 ");
		if(courseCategoryId!=null  && courseCategoryId!=0 ){
			jpql.append(" and (c.course_category=? or c.tree_path like ?)");
			conditionList.add(courseCategoryId);
			conditionList.add("%,"+courseCategoryId+",%");
		}
		if (StringUtils.isNotEmpty(searchProperty) && StringUtils.isNotEmpty(searchValue)) {
			if(searchProperty.equals("title")){
				jpql.append(" and c.title like '%"+searchValue+"%'");
			}
			if(searchProperty.equals("admin")){
				jpql.append(" and a.username like '%"+searchValue+"%'");
			}
		}
		jpql.append(" group by c.id");    
		long total=countStaData(searchProperty,searchValue,courseCategoryId);
		int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		List<Object[]> list=createNativeQueryPage(jpql.toString(),pageable.getPageNumber(),pageable.getPageSize(),conditionList);
		
		return new Page<Object[]>(list,total,pageable);
	}

	private long countStaData(String searchProperty,String searchValue,Long courseCategoryId) {
		// TODO Auto-generated method stub
		String jpql="select count(*) from Course c where 1=1";
		if(courseCategoryId!=null  && courseCategoryId!=0){
			jpql+=" and (c.courseCategory.id="+courseCategoryId+" or c.treePath like '%,"+courseCategoryId+",%')";
		}
		if (StringUtils.isNotEmpty(searchProperty) && StringUtils.isNotEmpty(searchValue)) {
			if(searchProperty.equals("title")){
				jpql+=" and c.title like '%"+searchValue+"%'";
			}
			if(searchProperty.equals("admin")){
				jpql+=" and c.admin.username like '%"+searchValue+"%'";
			}
		}
		long count=entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).getSingleResult();
		return count;
	}

	public List<Object[]> findByMemberId(Long memberId,String tabFlag) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT  m.id,m.create_date,m.source_image,m.title,ml.status,round(100*(ml.finish_lesson_num)/m.lesson_num)");
		sql.append(" FROM moc_course m left join moc_course_learn ml");
		sql.append(" on m.id=ml.course_id");
		sql.append(" WHERE 1=1");
		if(tabFlag!=null && !("").equals(tabFlag)){
			sql.append(" and ml.status=?");
		}
		sql.append(" and ml.user_id=?");
		sql.append(" GROUP BY m.id");
        if(("learning").equals(tabFlag)){
        	conditionList.add(0);
        }else if(("finished").equals(tabFlag)){
        	conditionList.add(1);
        }
        conditionList.add(memberId);
		List<Object[]> list=createNativeQuery(sql.toString(),conditionList);
		return list;
	}

	public List<Object[]> findfavByMemberId(Long memberId,String courseIds) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT m.id,m.create_date,m.source_image,m.title,ml.status,round(100*(ml.finish_lesson_num)/m.lesson_num)");
		sql.append(" FROM moc_course m LEFT OUTER JOIN moc_course_learn ml");
		sql.append(" ON m.id=ml.course_id");
		sql.append(" WHERE 1=1");
		sql.append(" AND m.id in "+courseIds+"");
		sql.append(" GROUP BY m.id");
		List<Object[]> list=createNativeQuery(sql.toString(),conditionList);
		return list;
	}

	public void updateFinishStuNum(Long finishStuNum, Long courseId) {
		// TODO Auto-generated method stub
		String jpql="update Course c set c.finishStuNum=:finishStuNum where c.id=:courseId";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT)
		.setParameter("finishStuNum", finishStuNum).setParameter("courseId", courseId).executeUpdate();
	}

	public Page<Object[]> findPublishedPage(Status published, Pageable pageable,String courseName,Long categoryId) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append("select c.id as id,c.source_image as sourceImage,c.title as title");
		sql.append(",c.lesson_num as lessonNum,c.student_num as studentNum,COALESCE(sum(mc.length),0) as lessonLength,");
		sql.append("c.create_date as createDate,c.show_student_num_type as showStudent");
		sql.append(" from moc_course c left  join moc_course_lesson mc");
		sql.append(" on c.id=mc.lesson_course ");
		sql.append(" where 1=1 ");
		if(courseName!=null && !("").equals(courseName)){
			sql.append(" and c.title like ?");
			conditionList.add("%"+courseName+"%");
		}
		if(categoryId!=null && categoryId!=0){
			sql.append(" and (c.course_category=? or c.tree_path like ?)");
			conditionList.add(categoryId);
			conditionList.add("%,"+categoryId+",%");
		}
		sql.append(" and c.status=1");
		sql.append(" group by c.id");   
		
		//修改人：荣刚平--2015.4.20-----增加c.order,
		sql.append(" order by c.orders,c.create_date desc");
		long total=countPublishedCourse(published,courseName,categoryId);
		int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		List<Object[]> list=createNativeQueryPage(sql.toString(),pageable.getPageNumber(),pageable.getPageSize(),conditionList);
		
		return new Page<Object[]>(list,total,pageable);
	}

	private long countPublishedCourse(Status published,String courseName,Long categoryId) {
		// TODO Auto-generated method stub
		StringBuffer jpql=new StringBuffer();
		jpql.append("select count(*) from Course c where 1=1");
		jpql.append(" and c.status=:status");
		if(courseName!=null && !("").equals(courseName)){
			jpql.append(" and c.title like '%"+courseName+"%'");
		}
		if(categoryId!=null  && categoryId!=0){
			jpql.append(" and (c.courseCategory.id="+categoryId+" or c.treePath like '%,"+categoryId+",%')");
		}
		long count=entityManager.createQuery(jpql.toString(), Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("status", published).getSingleResult();
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<Member> findLatestMember(Course course, Integer count) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("select learn.member from  Course c ")
		.append(" left join c.courseLearns learn ")
		.append(" where c =:course ")
		.append(" order by learn.createDate desc ");
	return	entityManager.createQuery(jpql.toString())
		.setMaxResults(count)
		.setFlushMode(FlushModeType.COMMIT)
		.setParameter("course", course).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findByTeacherId(Long teacherId,Status courseStatus) {
		// TODO Auto-generated method stub
		StringBuffer jpql=new StringBuffer();
		jpql.append("select c,sum(cl.length),count(cf.id),round(sum(cf.rating)/(count(cf.id)+0.0),1) from Course c ");
		jpql.append("left join fetch c.childrenLessons cl ");
		jpql.append("left join fetch c.courseReviews cf");
		jpql.append(" where 1=1 and (c.teacherIds like :teacherId" );
		jpql.append(" or c.teacherIds like :teacherIds)");
		jpql.append(" and c.status=:status");
		jpql.append(" group by c.id");
		jpql.append(" order by c.createDate desc");
		
		Query query = entityManager.createQuery(jpql.toString());
		
		query.setParameter("teacherId",teacherId+",%");
		query.setParameter("teacherIds", "%,"+teacherId+",%");
		query.setParameter("status",courseStatus);
		return query.getResultList();
	}

	public Status findStatus(Long courseId) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("select c.status from Course c ")
		.append(" where c.id =:courseId ");
		
		TypedQuery<Status> query = entityManager.createQuery(jpql.toString(),Status.class)
		.setFlushMode(FlushModeType.COMMIT).setParameter("courseId", courseId);
		return query.getSingleResult();
	}

	public Page<Object[]> findRecommendCourse(Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		Root<Course> root = criteriaQuery.from(Course.class);
		criteriaQuery.select(root);
		
		Predicate restrictions = criteriaBuilder.conjunction();
		
		restrictions=criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("isRecommend"), true));
		
		criteriaQuery.where(restrictions);
		
		long total = count(criteriaQuery, null);
		int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		
		
		StringBuffer jpql=new StringBuffer();
		jpql.append("select c,m.username,cgory.name from Course c ")
		.append(" left join c.admin m ")
		.append(" left join c.courseCategory cgory ")
		.append(" where c.isRecommend =:isRecommend ")
		.append(" order by c.recommendedSeq,c.order,c.createDate desc ");
		
		TypedQuery<Object[]> query = entityManager.createQuery(jpql.toString(),Object[].class).setFlushMode(FlushModeType.COMMIT);
		query.setParameter("isRecommend", true);
		query.setFirstResult((pageable.getPageNumber() - 1) * pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
		return new Page<Object[]>(query.getResultList(), total, pageable);
	}
	public List<Course> findListRecommendCourse(Long courseCategoryId,
			Integer count,Status status, List<Filter> filters) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		Root<Course> root = criteriaQuery.from(Course.class);
		criteriaQuery.select(root);
		
		Predicate restrictions = criteriaBuilder.conjunction();
		restrictions = criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("isRecommend"), true));
		
		if(courseCategoryId!=null){
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.equal(root.get("courseCategory"), courseCategoryId), criteriaBuilder.like(root.get("courseCategory").<String> get("treePath"), "%" + CourseCategory.TREE_PATH_SEPARATOR + courseCategoryId + CourseCategory.TREE_PATH_SEPARATOR + "%")));		
		}
		if(status !=null){
			restrictions = criteriaBuilder.and(restrictions,criteriaBuilder.equal(root.get("status"), status));
		}
		criteriaQuery.where(restrictions);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createDate")));
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("order")));
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("recommendedSeq")));
		return super.findList(criteriaQuery, null, count, filters, null);
	}
	public void changeRecommend(Course course) {
		StringBuffer jpql=new StringBuffer();
		jpql.append("update Course c set ")
		.append(" c.isRecommend=:isRecommend ")
		.append(" where c.id=:id");
		
		entityManager.createQuery(jpql.toString()).setFlushMode(FlushModeType.COMMIT)
		.setParameter("isRecommend", course.getIsRecommend())
		.setParameter("id", course.getId()).executeUpdate();
	}

	public void changeRecommendedSeq(Course course) {
		StringBuffer jpql=new StringBuffer();
		jpql.append("update Course c set ")
		.append(" c.recommendedSeq=:recommendedSeq ")
		.append(" ,c.recommendedTime=:recommendedTime")
		.append(" where c.id=:id");
		
		entityManager.createQuery(jpql.toString()).setFlushMode(FlushModeType.COMMIT)
		.setParameter("recommendedSeq", course.getRecommendedSeq())
		.setParameter("recommendedTime", new Date())
		.setParameter("id", course.getId()).executeUpdate();
	}

	public Integer getMaxRecommendedSeq() {
		String jpql = "select max(c.recommendedSeq) from Course c ";
		return entityManager.createQuery(jpql,Integer.class).setFlushMode(FlushModeType.COMMIT).getSingleResult();
	}

	public List<Course> findList(Long courseCategoryId, List<Tag> tags,
			BigDecimal startPrice, BigDecimal endPrice,Date beginDate, Date endDate,
			Integer first, Integer count,
			List<Filter> filters, List<Order> orders) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Course> criteriaQuery = criteriaBuilder.createQuery(Course.class);
		Root<Course> root = criteriaQuery.from(Course.class);
		criteriaQuery.select(root);
		
		Predicate restrictions = criteriaBuilder.conjunction();
		if(courseCategoryId!=null){
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.equal(root.get("courseCategory"), courseCategoryId), criteriaBuilder.like(root.get("courseCategory").<String> get("treePath"), "%" + CourseCategory.TREE_PATH_SEPARATOR + courseCategoryId + CourseCategory.TREE_PATH_SEPARATOR + "%")));
		}
		if (beginDate != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThanOrEqualTo(root.<Date> get("createDate"), beginDate));
		}
		if (endDate != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThanOrEqualTo(root.<Date> get("createDate"), endDate));
		}
		criteriaQuery.where(restrictions);
		criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createDate")));
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("order")));
		return super.findList(criteriaQuery, first, count, null, null);
	}

	public Page<Object[]> findMemsByCourId(Pageable pageable,Long courseId) {
		// TODO Auto-generated method stub
		String searchProperty = pageable.getSearchProperty();
		String searchValue = pageable.getSearchValue();
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append("select ml.id,ml.create_date,m.username,m.head_image,round(100*(ml.finish_lesson_num)/c.lesson_num),ml.user_id,ml.description");
		sql.append(" from moc_course  c inner join moc_course_learn ml");
		sql.append(" on c.id=ml.course_id");
		sql.append(" inner join moc_member m");
		sql.append(" on m.id=ml.user_id");
		sql.append(" where 1=1");
		if (StringUtils.isNotEmpty(searchProperty) && StringUtils.isNotEmpty(searchValue)){
			if(searchProperty.equals("username")){
			     sql.append(" and m.username like ?");
			     conditionList.add("%"+searchValue+"%");
			}
		}
		if(courseId!=null && courseId!=0){
			sql.append(" and c.id=?");
			conditionList.add(courseId);
		}
		long total=countMemsByCourId(searchProperty,searchValue,courseId);
		int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		List<Object[]> list=createNativeQueryPage(sql.toString(),pageable.getPageNumber(),pageable.getPageSize(),conditionList);
		return new Page<Object[]>(list,total,pageable);
	}

	private long countMemsByCourId(String searchProperty, String searchValue, Long courseId) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer jpql=new StringBuffer();
		jpql.append("select count(*) from CourseLearn c where 1=1");
		if (StringUtils.isNotEmpty(searchProperty) && StringUtils.isNotEmpty(searchValue)){
			if(searchProperty.equals("username")){
				jpql.append(" and c.member.username like ?");
				conditionList.add("%"+searchValue+"%");
			}
		}
		if(courseId!=null && courseId!=0){
			jpql.append(" and c.course.id=?");
			conditionList.add(courseId);
		}
		Query query=entityManager.createQuery(jpql.toString(), Long.class).setFlushMode(FlushModeType.COMMIT);
		for (int i = 0; i < conditionList.size(); i++) {
			query.setParameter(i+1, conditionList.get(i));
		}
		long count=(Long) query.getSingleResult();
		return count;
	}

	public Object[] getCourseReviewPoint(Long courseId) {
		StringBuffer jpql = new StringBuffer();
		jpql.append("select count(r),round(sum(r.rating)/(count(r)+0.0),1) ")
		.append(" from CourseReview r ")
		.append(" where r.course.id =:courseId ");
		@SuppressWarnings("unchecked")
		List<Object[]> list =entityManager.createQuery(jpql.toString())
		.setFlushMode(FlushModeType.COMMIT)
		.setParameter("courseId", courseId)
		.getResultList();
		
		return list.size()>0?list.get(0):null;
	}

	public void updateLessonNum(Course course) {
		// TODO Auto-generated method stub
		StringBuffer jpql=new StringBuffer();
		jpql.append("update Course c set ");
		jpql.append("c.lessonNum=:lessonNum");
		jpql.append(" where c.id=:id");
		entityManager.createQuery(jpql.toString()).setFlushMode(FlushModeType.COMMIT)
		.setParameter("lessonNum", course.getLessonNum()).setParameter("id", course.getId())
		.executeUpdate();
	}

	public String findCourseTitle(Long courseId) {
		String jpql="select c.title from Course c where c.id =:courseId";
		
		try {
			return (String) entityManager.createQuery(jpql)
					.setFlushMode(FlushModeType.COMMIT)
					.setParameter("courseId", courseId)
					.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	public void updateRatingNum(Long courseId) {
		String jpql ="update Course c set c.ratingNum =:ratingNum where c.id =:courseId";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT)
		.setParameter("ratingNum", getRatingNum(courseId))
		.setParameter("courseId", courseId)
		.executeUpdate();
	}
	private Integer getRatingNum(Long courseId){
		String jpql="select c.ratingNum from Course c where c.id =:courseId";
		
		return (Integer) entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT)
		.setParameter("courseId", courseId)
		.getSingleResult();
	}

	public void updateTreePath(String oldTreePath, String newTreePath) {
		// TODO Auto-generated method stub
		String jpql ="update Course set  treePath=REPLACE(treePath,'"+oldTreePath+"','"+newTreePath+"') where  treePath like '%"+oldTreePath+"%'";
		Query query = entityManager.createQuery(jpql);
		query.executeUpdate();
	}

	public List<Course> findList(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		StringBuffer jpql = new StringBuffer();
		jpql.append("from Course c where 1=1");
		if(beginDate!=null){
			jpql.append(" and c.createDate>=:beginDate");
		}
		if(endDate!=null){
			jpql.append(" and c.createDate<=:endDate");
		}
		jpql.append(" order by c.createDate desc");
		TypedQuery<Course> query=entityManager.createQuery(jpql.toString(),Course.class)
		.setFlushMode(FlushModeType.COMMIT);
		if(beginDate!=null){
			query.setParameter("beginDate", beginDate);
		}
		if(beginDate!=null){
			query.setParameter("endDate", endDate);
		}
		return query.getResultList();
	}

	public Page<Course> findPage(Pageable pageable, Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		StringBuffer jpql = new StringBuffer();
		jpql.append("from Course c where 1=1");
		if(beginDate!=null){
			jpql.append(" and c.createDate>=:beginDate");
		}
		if(endDate!=null){
			jpql.append(" and c.createDate<=:endDate");
		}
		//修改人：荣刚平--2015.4.20-----增加c.order,
		jpql.append(" order by c.order,c.createDate desc");
		TypedQuery<Course> query=entityManager.createQuery(jpql.toString(),Course.class)
		.setFlushMode(FlushModeType.COMMIT);
		if(beginDate!=null){
			query.setParameter("beginDate", beginDate);
		}
		if(beginDate!=null){
			query.setParameter("endDate", endDate);
		}
		long total=countAnalysisCourse(beginDate,endDate);
		int totalPages = (int) Math.ceil((double) total / (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		query.setFirstResult((pageable.getPageNumber()-1)*pageable.getPageSize());
		query.setMaxResults(pageable.getPageSize());
		List<Course> list=query.getResultList();
		return new Page<Course>(list,total,pageable);
	}

	private long countAnalysisCourse(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		StringBuffer jpql = new StringBuffer();
		jpql.append("select count(*) from Course c where 1=1");
		if(beginDate!=null){
			jpql.append(" and c.createDate>=:beginDate");
		}
		if(endDate!=null){
			jpql.append(" and c.createDate<=:endDate");
		}
		Query query=entityManager.createQuery(jpql.toString(), Long.class).setFlushMode(FlushModeType.COMMIT);
		if(beginDate!=null){
			query.setParameter("beginDate", beginDate);
		}
		if(endDate!=null){
			query.setParameter("endDate", endDate);
		}
		return (Long) query.getSingleResult();
	}

	public void updateStuNumLessonNum(Long id, Integer lessonNum) {
		// TODO Auto-generated method stub
		StringBuffer jpql=new StringBuffer();
		jpql.append("update Course c set ");
		jpql.append("c.lessonNum=:lessonNum,c.finishStuNum=0");
		jpql.append(" where c.id=:id");
		entityManager.createQuery(jpql.toString()).setFlushMode(FlushModeType.COMMIT)
		.setParameter("lessonNum", lessonNum).setParameter("id",id)
		.executeUpdate();
	}

	public List<Object[]> findAnalySisList(Date beginDate, Date endDate) {
		// TODO Auto-generated method stub
		List<Object> conditionList=new ArrayList<Object>();
		StringBuffer sql=new StringBuffer();
		sql.append("SELECT date_format(m.create_date,'%Y-%m-%d')  sub,count(m.id)");
		sql.append(" from moc_course m");
		sql.append(" where 1=1");
		if(beginDate!=null){
			sql.append(" and m.create_date>=?");
		}
		if(endDate!=null){
			sql.append(" and m.create_date<=?");
		}
		sql.append(" group by sub");
		sql.append(" order by sub asc");
		
		if(beginDate!=null){
			conditionList.add(beginDate);
		}
		if(endDate!=null){
			conditionList.add(endDate);
		}
		return createNativeQuery(sql.toString(),conditionList);
	}

	public Course findByChapter(Long course_chapter_id) {
		String jpql = "select c from Course c where c.id=(select chapter.course.id from CourseChapter chapter where chapter.id =:chapterId) ";
		
		TypedQuery<Course> query = entityManager.createQuery(jpql, Course.class).setFlushMode(FlushModeType.COMMIT);
		query.setParameter("chapterId", course_chapter_id);
		
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}