package com.sram.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;

import com.sram.dao.CourseDao;
import com.sram.dao.CourseFavoriteDao;
import com.sram.entity.Course;
import com.sram.entity.CourseFavorite;
import com.sram.service.CourseFavoriteService;


/**
 * Service - 课程收藏
 * 
 * @author Sram Team
 * @version 1.0
 */
@Service("courseFavoriteServiceImpl")
public class CourseFavoriteServiceImpl extends BaseServiceImpl<CourseFavorite,Long> implements CourseFavoriteService,DisposableBean{
	@Resource(name = "courseFavoriteDaoImpl")
	private CourseFavoriteDao courseFavoriteDao;
	
	@Resource(name = "courseFavoriteDaoImpl")
	public void setBaseDao(CourseFavoriteDao courseFavoriteDao) {
		super.setBaseDao(courseFavoriteDao);
	}
	
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean courseFavoriteExists(Long courseId, Long memberId) {
		// TODO Auto-generated method stub
		return courseFavoriteDao.courseFavoriteExists(courseId,memberId);
	}


	public void deleteCourFav(Long courseId, Long memberId) {
		// TODO Auto-generated method stub
		courseFavoriteDao.deleteCourFav(courseId,memberId);
	}

	public List<CourseFavorite> findFavoriteByUser(Long userId) {
		// TODO Auto-generated method stub
		return courseFavoriteDao.findFavoriteByUser(userId);
	}

}
