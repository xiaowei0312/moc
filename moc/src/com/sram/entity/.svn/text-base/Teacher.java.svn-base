package com.sram.entity;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.hibernate.annotations.Type;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sram.CommonAttributes;
import com.sram.entity.Course.Status;
import com.sram.util.FreemarkerUtils;

import freemarker.template.TemplateException;
/**
 * Entity - 教师
 * 
 * @author Sram Team
 * @version 1.0
 */
@Entity
@Table(name = "moc_teacher")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "moc_course_sequence")
public class Teacher extends BaseEntity{
	private static final long serialVersionUID = 1L;
	
	public enum Gender{
		male,
		female,
		secret
	}
	
	/**教师发布状态*/
	public enum Status {
		draft, published, closed
	}
	
	/** 静态路径 */
	private static String staticPath;
	
	private Status status;
	
	private Gender gender;
	
	private String truename;
	private String idcard;
	private Date birthday;
	private String city;
	private String mobile;
	private String qq;
	
	/**签名*/
	private String signature;
	
	/**自我介绍*/
	private String about;
	private String company;
	private String job;
	private String school;
	private String weibo;
	private String weixin;
	private String site;
	
	/**头像*/
	private String image;
	
	private Long adminId=(long)0;
	
	/**授课风格*/
	private String teachingStyle;
	
	static {
		try {
			File sramXmlFile = new ClassPathResource(
					CommonAttributes.SRAM_XML_PATH).getFile();
			org.dom4j.Document document = new SAXReader().read(sramXmlFile);
			
			//获取课程内容的模板
			org.dom4j.Element element = (org.dom4j.Element) document
					.selectSingleNode("/sram/template[@id='teacherContent']");
			
			//获取sram.xml的静态路径字符串
			staticPath = element.attributeValue("staticPath");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	@Type(type = "text")
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getWeibo() {
		return weibo;
	}
	public void setWeibo(String weibo) {
		this.weibo = weibo;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public Long getAdminId() {
		return adminId;
	}
	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
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
	
	public String getTeachingStyle() {
		return teachingStyle;
	}
	public void setTeachingStyle(String teachingStyle) {
		this.teachingStyle = teachingStyle;
	}
	
}
