package com.sram.entity;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sram.CommonAttributes;
import com.sram.util.FreemarkerUtils;

import freemarker.template.TemplateException;
@Entity
@Table(name = "moc_industryCategory")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_industryCategory_sequence")
public class IndustryCategory extends OrderEntity {
	private static final long serialVersionUID = 1L;
	/** 静态路径 */
	private static String staticPath;
	/** 树路径分隔符 */
	public static final String TREE_PATH_SEPARATOR = ",";
	/**名称*/
	private String name;
	
	/**上级分类*/
	private IndustryCategory parent;
	
	/**描述*/
	private String description;
	/**行业图片*/
	private String image;
	/**层级*/
	private Integer grade;
	
	/**树路径*/
	private String  treePath;
	
	/**顶级大纲*/
	private List<OutlineCategory> outlineCategories=new ArrayList<OutlineCategory>();
	
	
	/** 下级分类 */
	private Set<IndustryCategory> children = new HashSet<IndustryCategory>();
	
	
	static {
		try {
			File sramXmlFile = new ClassPathResource(
					CommonAttributes.SRAM_XML_PATH).getFile();
			org.dom4j.Document document = new SAXReader().read(sramXmlFile);
			
			//获取课程内容的模板
			org.dom4j.Element element = (org.dom4j.Element) document
					.selectSingleNode("/sram/template[@id='training']");
			
			//获取sram.xml的静态路径字符串
			staticPath = element.attributeValue("staticPath");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	@ManyToOne(fetch = FetchType.LAZY)
	public IndustryCategory getParent() {
		return parent;
	}
	public void setParent(IndustryCategory parent) {
		this.parent = parent;
	}
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("order asc")
	public Set<IndustryCategory> getChildren() {
		return children;
	}
	public void setChildren(Set<IndustryCategory> children) {
		this.children = children;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * 获取访问路径
	 * 
	 * @return 访问路径
	 */
	@JsonProperty
	@Transient
	public String getPath() {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("id", getId());
		model.put("createDate", getCreateDate());
		model.put("modifyDate", getModifyDate());
		try {
			//sram.xml中的staticPath+model-----/course/content/201501/1.html
			return FreemarkerUtils.process(staticPath, model);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return null;
	}
	@OneToMany(mappedBy ="industryCategory", fetch = FetchType.LAZY)
	public List<OutlineCategory> getOutlineCategories() {
		return outlineCategories;
	}
	public void setOutlineCategories(List<OutlineCategory> outlineCategories) {
		this.outlineCategories = outlineCategories;
	}
	

}
