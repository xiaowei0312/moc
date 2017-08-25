/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.sram.Message;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.SramException;
import com.sram.dao.CourseDao;
import com.sram.dao.TeacherDao;
import com.sram.entity.Course;
import com.sram.entity.Teacher;
import com.sram.entity.Teacher.Status;
import com.sram.service.StaticService;
import com.sram.service.TeacherImageService;
import com.sram.service.TeacherService;

/**
 * Service - 教师
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("teacherServiceImpl")
public class TeacherServiceImpl extends BaseServiceImpl<Teacher, Long> implements TeacherService{
	
	@Resource(name="teacherImageServiceImpl")
	private TeacherImageService teacherImageService;
	
	@Resource(name = "teacherDaoImpl")
	private TeacherDao teacherDao;
	
	@Resource(name = "staticServiceImpl")
	private StaticService staticService;
	
	@Resource(name = "courseDaoImpl")
	private CourseDao courseDao;
	
	@Resource(name = "teacherDaoImpl")
	public void setBaseDao(TeacherDao teacherDao) {
		super.setBaseDao(teacherDao);
	}

	@Transactional(readOnly = true)
	public Page<Teacher> findPage(String truename,
			Set<Long> teacherIds,String recommended,
			Pageable pageable){
		return teacherDao.findPage(truename,teacherIds,recommended,pageable);
	}
	
	@Override
	@Transactional
	public void save(Teacher teacher) {
		Assert.notNull(teacher);
		teacher.setStatus(Status.draft);
		super.save(teacher);
		teacherDao.flush();
	}
    
	@Override
	@Transactional
	public Teacher update(Teacher teacher){
		Assert.notNull(teacher);
		teacher.setStatus(Status.draft);
        return super.update(teacher);
	}

	
	@Override
	public void delete(Long... ids) {
		//判断要删除的老师是否有教课是 删除老师的头像
		Teacher teacher;
		List<Course> courses;
		for(Long id:ids){
			teacher = super.find(id);
			
			courses = courseDao.findByTeacher(teacher);
			if(courses != null && courses.size()>0){	
				//该教师有授课
				throw new SramException("教师[" + teacher.getTruename()+"]有授课，请先更换教师");
			}
			teacherImageService.deleteTeacherImage(teacher);
			super.delete(id);
		}	
	}
	
	
	
	@Override
	@Transactional
	public Teacher update(Teacher teacher, String... ignoreProperties) {
		return super.update(teacher, ignoreProperties);
	}
    

	@Transactional(readOnly = true)
	public List<Course> findCourseByTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional
	public List<Teacher> findTeachersNotIds(Course course) {
		if(course.getTeacherIds()==null 
				|| course.getTeacherIds().isEmpty()){
			return findAll();
		}
		return teacherDao.findTeachersNotIds(course.getTeacherIds());
	}
	@Transactional
	public List<Teacher> findListByCourse(Course course) {
		if(course.getTeacherIds()==null || course.getTeacherIds().isEmpty()){
			return null;
		}
		String[] stringIds = course.getTeacherIds().split(",");
		Long[] ids = new Long[stringIds.length];
		for(int i=0;i<stringIds.length;i++){
			ids[i]=Long.parseLong(stringIds[i]);
		}
		return findList(ids);
	}

	@Transactional
	public boolean idcardExists(String idcard) {
		if(idcard ==null){
			return false;
		}
		return teacherDao.idcardExists(idcard);
	}
	@Transactional
	public boolean mobileExists(String mobile,Long id) {
		if(mobile ==null){
			return false;
		}
		return teacherDao.mobileExists(mobile,id);
	}
    
	@Transactional(readOnly = true)
	public void changeStatus(Teacher teacher) {
		// TODO Auto-generated method stub
		teacherDao.changeStatus(teacher);
		if(teacher.getStatus()==Status.published){
			//生成教师静态页
			staticService.build(teacherDao.find(teacher.getId()));
		}	
	}

	public List<Teacher> findAll(Integer first,Integer count) {
		// TODO Auto-generated method stub
		return teacherDao.findAll(first,count);
	}
	
	

}