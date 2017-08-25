package com.sram.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.Length;
/**
 * 会计账目
 * @author Administrator
 *
 */
@Entity
@Table(name = "moc_accoutCatalog")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_accoutCatalog_sequence")
public class AccoutCatalog extends OrderEntity {
	private static final long serialVersionUID = 1L;
	/** 树路径分隔符 */
	public static final String TREE_PATH_SEPARATOR = ",";
	/**会计账目名称*/
	private String name;
	/**上级分类*/
	private AccoutCatalog parent;
	/**会计账目描述*/
	private String description;
	/**层级*/
	private Integer grade;
	/**树路径*/
	private String treePath;
	/**科目编号*/
	private String code;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	public AccoutCatalog getParent() {
		return parent;
	}
	public void setParent(AccoutCatalog parent) {
		this.parent = parent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}
	public String getTreePath() {
		return treePath;
	}
	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
	@Length(max = 8)
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * 获取树路径
	 * 
	 * @return 树路径
	 */
	@Transient
	public List<Long> getTreePaths() {
		List<Long> treePaths = new ArrayList<Long>();
		String[] ids = StringUtils.split(getTreePath(), TREE_PATH_SEPARATOR);
		if (ids != null) {
			for (String id : ids) {
				treePaths.add(Long.valueOf(id));
			}
		}
		return treePaths;
	}
	
}
