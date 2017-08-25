package com.sram.entity;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/*
 * 课程课时学习
 */
@Entity
@Table(name = "moc_course_lesson_learn")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_course_lesson_learn_sequence")
public class CourseLessonLearn extends BaseEntity{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//会员Id
	private Long userId;
	
	//课程Id
	private Course course;
	
	//课时Id
	private CourseLesson courseLesson;
	
	/** 学习状态*/
	public enum Status{
		/*正在学习*/
		learning,
		/*已完成*/
		finished
	}
	
	
	
	public CourseLessonLearn() {
		super();
	}
	
	
	//视频状态
	public enum VideoStatus{
		
	}
	
	//视频状态
	private VideoStatus videoStatus;
	
	//学习状态
	private Status status;
	//学习时长
	private Long learnTime;
	//观看时长
	private Long watchTime;
	
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@NotNull
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
    
	public Long getLearnTime() {
		return learnTime;
	}
	public void setLearnTime(Long learnTime) {
		this.learnTime = learnTime;
	}
	public Long getWatchTime() {
		return watchTime;
	}
	public void setWatchTime(Long watchTime) {
		this.watchTime = watchTime;
	}
	public VideoStatus getVideoStatus() {
		return videoStatus;
	}
	public void setVideoStatus(VideoStatus videoStatus) {
		this.videoStatus = videoStatus;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="lessonId")
	public CourseLesson getCourseLesson() {
		return courseLesson;
	}
	public void setCourseLesson(CourseLesson courseLesson) {
		this.courseLesson = courseLesson;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="courseId")
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
}
