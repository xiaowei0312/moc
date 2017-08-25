package com.sram.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * 试卷章节
 * @author Administrator
 *
 */
@Entity
@Table(name = "moc_testpaper_chapter")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_testpaper_chapter_sequence")
public class TestpaperChapter extends OrderEntity {
	private static final long serialVersionUID = 1L;
	/**所属试卷*/ 
	private Testpaper testpaper;
	/**
	 * 试卷章节名称
	 */
	private String name;
	/**备注*/ 
	private String description;
	
	/**
	 * 试卷条目列表
	 */
	private Set<TestpaperItem> testpaperItems = new HashSet<TestpaperItem>();
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="testpaperId")
	public Testpaper getTestpaper() {
		return testpaper;
	}
	public void setTestpaper(Testpaper testpaper) {
		this.testpaper = testpaper;
	}
	@JsonProperty
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@JsonProperty
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 试卷条目列表
	 * @return
	 */
	@OneToMany(mappedBy="testpaperChapter",fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	@OrderBy("order asc")
	public Set<TestpaperItem> getTestpaperItems() {
		return testpaperItems;
	}
	public void setTestpaperItems(Set<TestpaperItem> testpaperItems) {
		this.testpaperItems = testpaperItems;
	}
	@Override
	public int hashCode() {
		return new Integer(12).hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if( obj instanceof TestpaperChapter){
			TestpaperChapter testpaperChapter =(TestpaperChapter) obj;
			if(testpaperChapter==null||this.getId()==null||testpaperChapter.getId()==null){
				return false;
			}else{
				return this.getId().equals(testpaperChapter.getId());
			}
		}
		else
			return false;
	}
}