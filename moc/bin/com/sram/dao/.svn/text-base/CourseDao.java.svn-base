/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
 * Dao - 课程
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface CourseDao extends BaseDao<Course, Long> {
	/**
	 * 查找课程分页
	 *      课程种类
	 *      课程状态
	 *      是不是直播课程maxStudentNum
	 *      推荐课程
	 *      会员课程
	 *      是否免费
	 * @param deadlineNotify 
	 * @param showStudentNumType 
	 * @param serializeMode 
	 */
	Page<Course> findPage(CourseCategory courseCategory,
			Status status,	
			Integer maxStudentNum, Integer recommended, Integer vipLevelId, Float price, UserType userType, SerializeMode serializeMode, ShowStudentNumType showStudentNumType, DeadlineNotify deadlineNotify, Pageable pageable
	);
	
	/**
	 * 根据老师找课程
	 * @param teacher
	 * @return
	 */
	List<Course> findByTeacher(Teacher teacher);
	
	/**
	 * 找到授课教师的ids
	 * @return
	 */
	Set<Long> findTeacherIds();

	/**
	 * 改变课程的状态
	 * @param course
	 */
	void changeStatus(Course course);
	
	/**
	 * 找该课程下的最新学员
	 * count---学员人数
	 * @param course
	 * @param count
	 * @return
	 */
	List<Member> findLatestMember(Course course, Integer count);
	
	Page<Object[]> findStaPage(Pageable pageable,Long courseCategoryId);

	List<Object[]> findfavByMemberId(Long memberId,String courseIds);

	void updateFinishStuNum(Long finishStuNum, Long courseId);

	Page<Object[]> findPublishedPage(Status published, Pageable pageable,String courseName, Long categoryId);

	List<Object[]> findByTeacherId(Long teacherId, Status courseStatus);

	/**
	 * 根据课程id查找状态
	 * @param courseId
	 * @return
	 */
	com.sram.entity.Course.Status findStatus(Long courseId);

	/**
	 * 查找所有的推荐课程
	 * @param pageable 
	 * @return
	 */
	Page<Object[]> findRecommendCourse(Pageable pageable);

	void changeRecommend(Course course);

	/**
	 * 设置推荐序号
	 * @param course
	 */
	void changeRecommendedSeq(Course course);

	/**
	 * 获取最大的推荐序号
	 */
	Integer getMaxRecommendedSeq();

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
	List<Course> findList(Long courseCategoryId, List<Tag> tags,
			BigDecimal startPrice, BigDecimal endPrice,Date beginDate, Date endDate, Integer first, Integer count,
			List<Filter> filters, List<Order> orders);

	Page<Object[]> findMemsByCourId(Pageable pageable,Long courseId);

	/**
	 * 获取该课程的评价数--评分
	 * @param courseId
	 * @return
	 */
	Object[] getCourseReviewPoint(Long courseId);


	void updateLessonNum(Course course);

	/**
	 * 查询课程列表
	 * @param courseCategoryId
	 * @param count
	 * @param status 
	 * @param filters
	 * @return
	 */
	List<Course> findListRecommendCourse(Long courseCategoryId, Integer count,
			Status status, List<Filter> filters);

	/**
	 * 查找课程的名字
	 * @param courseId
	 * @return
	 */
	String findCourseTitle(Long courseId);

	/**
	 * 课程评价人数
	 * @param courseId
	 */
	void updateRatingNum(Long courseId);

	void updateTreePath(String oldTreePath, String newTreePath);

	List<Object[]> findByMemberId(Long memberId, String tabFlag);

	List<Course> findList(Date beginDate, Date endDate);

	Page<Course> findPage(Pageable pageable, Date beginDate, Date endDate);

	void updateStuNumLessonNum(Long id, Integer lessonNum);
	
	/**
	 * 新增课程统计折线图
	 * @param beginDate 开始日期
	 * @param endDate 结束日期
	 * @return
	 */
	List<Object[]> findAnalySisList(Date beginDate, Date endDate);

	/**
	 * 由章节ID获取所属课程
	 * @param course_chapter_id
	 * @return
	 */
	Course findByChapter(Long course_chapter_id);

}