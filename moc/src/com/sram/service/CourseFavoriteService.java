package com.sram.service;

import java.util.List;

import com.sram.entity.Course;
import com.sram.entity.CourseFavorite;


/**
 * Service - 课程收藏
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface CourseFavoriteService extends BaseService<CourseFavorite,Long>{

	boolean courseFavoriteExists(Long courseId, Long id);

	void deleteCourFav(Long courseId, Long id);

	List<CourseFavorite> findFavoriteByUser(Long userId);

}
