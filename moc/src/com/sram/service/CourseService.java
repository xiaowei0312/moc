/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.sram.Filter;
import com.sram.Order;
import com.sram.Page;
import com.sram.Pageable;
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
 * Service - 课程
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface CourseService extends BaseService<Course, Long> {
	
	/**
	 * 获取该课程评论数和评分
	 * @param courseId
	 * @return
	 */
	Object[] getCourseReviewPoint(Long courseId);
	
	/**
	 * 根据课程，找最新学员
	 * count--学员人数
	 * @param course
	 * @return
	 */
	List<Member> findLatestMember(Course course,Integer count);
	/**
	 * 
	 * @param courseCategory
	 * @param status
	 * @param maxStudentNum
	 * @param recommended
	 * @param vipLevelId
	 * @param price
	 * @param userType
	 * @param serializeMode
	 * @param showStudentNumType
	 * @param deadlineNotify
	 * @param pageable
	 * @return
	 */
	Page<Course> findPage(CourseCategory courseCategory,Status status, Integer maxStudentNum, Integer recommended, Integer vipLevelId, Float price, UserType userType, SerializeMode serializeMode, ShowStudentNumType showStudentNumType, DeadlineNotify deadlineNotify, Pageable pageable);
	
	/**
	 * 根据教师查课程
	 * @param teacher
	 * @return
	 */
	List<Course> findByTeacher(Teacher teacher);
	

	
	/**
	 * 
	 * @param courseCategory
	 * @param tags
	 * @param startPrice
	 * @param endPrice
	 * @param beginDate
	 * @param endDate
	 * @param first
	 * @param count
	 * @param filters
	 * @param orders
	 * @return
	 */
	List<Course> findList(Long courseCategoryId,List<Tag> tags,
			BigDecimal startPrice, BigDecimal endPrice,Date beginDate, Date endDate, Integer first,
			Integer count, List<Filter> filters, List<Order> orders);
	

	
	/**
	 * 改变课程的状态
	 * @param course
	 */
	void changeStatus(Course course);
	Page<Object[]> findStaPage(Pageable pageable,Long courseCategoryId);
	List<Object[]> findByMemberId(Long memberId, String tabFlag);
	List<Object[]> findfavByMemberId(Long memberId,String courseIds);

	void updateFinishStuNum(Long finishStuNum, Long id);

	Page<Object[]> findPublishedPage(Status published, Pageable pageable,String courseName,Long categoryId);
	List<Object[]> findByTeacherId(Long id,Status courseStatus);
	
	/**
	 * 查找所有的推荐课程
	 * @param pageable 
	 * @return
	 */
	Page<Object[]> findRecommendCourse(Pageable pageable);
	
	/**
	 * 课程推荐的改变---推荐时加上默认的序列
	 * @param recommend
	 */
	void changeRecommend(Course course);
	
	/**
	 * 设置推荐序号---会更新推荐时间
	 * @param course
	 */
	void changeRecommendedSeq(Course course);
	Page<Object[]> findMemsByCourId(Pageable pageable,Long courseId);

	/**
	 * 查询推荐课程列表
	 * @param courseCategoryId
	 * @param count
	 * @param filters
	 * @return
	 */
	List<Course> findListRecommendCourse(Long courseCategoryId, Integer count,Status status,
			List<Filter> filters);

	List<Course> findListRecommendCourse(Long courseCategoryId, Integer count,
			List<Filter> filters, String cacheRegion);

	/**
	 * 查找课程的名字
	 * @param courseId
	 * @return
	 */
	String findCourseTitle(Long courseId);

	/**
	 * 课程的评论人数
	 * @param courseReview
	 */
	void updateRatingNum(Long courseId);

	Page<Course> findPage(Pageable pageable, Date beginDate, Date endDate);
	
	/**
	 * 新增课程统计折线图
	 * @param beginDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	List<Object[]> findAnalySisList(Date beginDate, Date endDate);

	/**
	 * 由章节获取所属课程
	 * @param course_chapter_id
	 * @return
	 */
	Course findByChapterId(Long course_chapter_id);

}