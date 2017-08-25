package com.sram.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
 * 课程学习
 */
@Entity
@Table(name = "moc_course_material")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_course_material_sequence")
public class CourseMaterial extends BaseEntity {
	// 会员编号
	private Admin admin;
	// 课程编号
	private Course course;

	private CourseLesson courseLesson;

	private String title;

	private String description;

	private String link;

	private UploadFiles uploadFiles;

	private String fileUri;

	private String fileMime;

	private Long fileSize;
	@JsonProperty
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(nullable = false, name = "user_id")
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(nullable = false, name = "course_id")
	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(nullable = false, name = "lesson_id")
	public CourseLesson getCourseLesson() {
		return courseLesson;
	}

	public void setCourseLesson(CourseLesson courseLesson) {
		this.courseLesson = courseLesson;
	}

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(nullable = false, name = "file_id")
	@JsonProperty
	public UploadFiles getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(UploadFiles uploadFiles) {
		this.uploadFiles = uploadFiles;
	}
	@JsonProperty
	public String getFileUri() {
		return fileUri;
	}

	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}
	@JsonProperty
	public String getFileMime() {
		return fileMime;
	}

	public void setFileMime(String fileMime) {
		this.fileMime = fileMime;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

}
