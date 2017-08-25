/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.UploadFilesDao;
import com.sram.entity.UploadFiles;

/**
 * Dao - 上传文件
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("uploadFilesDaoImpl")
public class UploadFilesDaoImpl extends BaseDaoImpl<UploadFiles, Long>
		implements UploadFilesDao {

	public List<UploadFiles> findUploadFilesByTypeAndId(String targetType,
			Long targetId, String fileType) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UploadFiles> criteriaQuery = criteriaBuilder
				.createQuery(UploadFiles.class);
		Root<UploadFiles> root = criteriaQuery.from(UploadFiles.class);

		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(
				root.<String> get("targetType"), targetType));

		restrictions = criteriaBuilder.and(restrictions,
				criteriaBuilder.equal(root.<String> get("targetId"), targetId));
		if (fileType != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder
					.equal(root.<String> get("fileType"), fileType));
		}
		criteriaQuery.where(restrictions);

		return super.findList(criteriaQuery, null, null, null, null);
	}
	public Page<UploadFiles> findPage(Long courseId, Pageable pageable,
			String targetType) {
		// TODO Auto-generated method stub
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<UploadFiles> criteriaQuery = criteriaBuilder
				.createQuery(UploadFiles.class);
		Root<UploadFiles> root = criteriaQuery.from(UploadFiles.class);
		criteriaQuery.select(root);

		Predicate restrictions = criteriaBuilder.conjunction();
		if (courseId != null && courseId != 0) {
			restrictions = criteriaBuilder.and(restrictions,
					criteriaBuilder.equal(root.get("targetId"), courseId));
		}
		restrictions = criteriaBuilder.and(restrictions,
				criteriaBuilder.equal(root.get("targetType"), targetType));

		criteriaQuery.where(restrictions);
		return super.findPage(criteriaQuery, pageable);
	}
	public List<UploadFiles> findALLByTarget(Long targetId, String targetType) {
		String jpql=" select f from UploadFiles f where f.targetId=? and f.targetType=? ";
		List<Object> list = new ArrayList<Object>();
		list.add(targetId);
		list.add(targetType);
		return this.createQuery(jpql, list);
	}
	public Page<Object[]> findPage(String courseName, Long courseCategoryId,
			Long targetId, Pageable pageable, String targetType) {
		StringBuffer sqlcommon = new StringBuffer();
		String sqlheader="select f.id,f.filename,f.ext,f.size,f.convert_status,u.username,f.modify_date,c.title ";
		
		sqlcommon.append(" from moc_upload_files f ")
		.append(" left join moc_course c on f.target_id=c.id ")
		.append(" left join moc_admin u on f.updated_user_id=u.id ")
		.append(" where 1=1 ");
		
		if(targetId !=null){
			sqlcommon.append(" and f.target_id="+targetId);
		}
		if(targetType!=null){
			sqlcommon.append(" and f.target_type='"+targetType+"'");
		}
		if(courseCategoryId!=null){
			sqlcommon.append(" and f.target_id in ")
			.append(" (select tempc.id from moc_course tempc where tempc.course_category="+courseCategoryId+") ");
		}
		
		String searchProperty=pageable.getSearchProperty();
		String searchValue=pageable.getSearchValue();
		
		if(StringUtils.isNotEmpty(searchProperty) && StringUtils.isNotEmpty(searchValue)){
			if("fileName".equals(searchProperty)){
				sqlcommon.append(" and f.filename like '%"+searchValue+"%'");
			}else if("courseName".equals(searchProperty)){
				sqlcommon.append(" and c.title like '%"+searchValue+"%'");
			}
		}
		
		
		Long total = createNativeQueryCount("select count(*) "+sqlcommon, null);
		int totalPages = (int) Math.ceil((double) total
				/ (double) pageable.getPageSize());
		if (totalPages < pageable.getPageNumber()) {
			pageable.setPageNumber(totalPages);
		}
		
		List<Object[]> list = createNativeQueryPage(sqlheader+sqlcommon, 
				pageable.getPageNumber(), pageable.getPageSize(), null);
		return new Page<Object[]>(list,total,pageable);
	}
}