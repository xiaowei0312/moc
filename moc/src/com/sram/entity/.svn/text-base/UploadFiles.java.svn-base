package com.sram.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "moc_upload_files")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_upload_files_sequence")
public class UploadFiles extends BaseEntity {

	public enum ConvertStatus {
		none, waiting, doing, success, error
	}

	public enum Storage {
		local, cloud
	}

	public enum TARGET_TYPE {
		courselesson, coursematerial, outlinecategory, question
	}

	private String hashId;

	/** courseId */
	private Long targetId;

	/** courselesson,coursematerail */
	private String targetType;
	private String filename;
	private String ext;
	private Integer length;
	private String convertHash;
	private ConvertStatus convertStatus;
	private String convertParams;
	private String metas;
	private String metas2;
	private String fileType;
	private Boolean isPublic;
	private Boolean canDownload;
	private Integer usedCount;
	private Admin updatedUser;
	private Long createUserId;

	private Long size;

	public String getHashId() {
		return hashId;
	}

	public void setHashId(String hashId) {
		this.hashId = hashId;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public String getConvertHash() {
		return convertHash;
	}

	public void setConvertHash(String convertHash) {
		this.convertHash = convertHash;
	}

	public ConvertStatus getConvertStatus() {
		return convertStatus;
	}

	public void setConvertStatus(ConvertStatus convertStatus) {
		this.convertStatus = convertStatus;
	}

	public String getConvertParams() {
		return convertParams;
	}

	public void setConvertParams(String convertParams) {
		this.convertParams = convertParams;
	}

	@Type(type = "text")
	public String getMetas() {
		return metas;
	}

	public void setMetas(String metas) {
		this.metas = metas;
	}

	@Type(type = "text")
	public String getMetas2() {
		return metas2;
	}

	public void setMetas2(String metas2) {
		this.metas2 = metas2;
	}

	public Boolean getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Boolean getCanDownload() {
		return canDownload;
	}

	public void setCanDownload(Boolean canDownload) {
		this.canDownload = canDownload;
	}

	public Integer getUsedCount() {
		return usedCount;
	}

	public void setUsedCount(Integer usedCount) {
		this.usedCount = usedCount;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "updated_user_id")
	public Admin getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(Admin updatedUser) {
		this.updatedUser = updatedUser;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UploadFiles other = (UploadFiles) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}

}
