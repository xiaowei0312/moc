package com.sram.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
/**
 * 大纲关联资料
 * @author zxd
 *
 */
@Entity
@Table(name = "moc_outline_category_material")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_moc_outline_category_material_sequence")
public class OutlineCategoryMaterial extends BaseEntity{
	
	private static final long serialVersionUID = 1L;

	/**
	 * 资料所属大纲Id
	 */
	private OutlineCategory outlineCategory;
	
	/**
	 * 资料标题
	 */
	private String title;
	/**
	 * 资料描述
	 */
	private String description ;
	/**
	 * 资料文件ID
	 */
	private UploadFiles uploadFiles;
	/**
	 * 资料文件URI
	 */
	private String fileUri ;
	
	/**
	 * 资料文件MIME
	 */
	private String fileMime;
	
	/**
	 * 资料文件大小
	 */
	private  Long fileSize ;
	
	/**
	 * 资料创建人ID
	 */
	private Admin admin;


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

	public String getFileUri() {
		return fileUri;
	}

	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}

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
	
	@ManyToOne(fetch=FetchType.LAZY,optional = true)
	@JoinColumn(nullable = false, name = "fileId")
	public UploadFiles getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(UploadFiles uploadFiles) {
		this.uploadFiles = uploadFiles;
	}
	
	@ManyToOne(fetch=FetchType.LAZY,optional=true)
	@JoinColumn(nullable=false,name="outlineCategoryId")
	public OutlineCategory getOutlineCategory() {
		return outlineCategory;
	}

	public void setOutlineCategory(OutlineCategory outlineCategory) {
		this.outlineCategory = outlineCategory;
	}
	
	@ManyToOne(fetch=FetchType.LAZY,optional = true)
	@JoinColumn(nullable=false,name="userId")
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
}
