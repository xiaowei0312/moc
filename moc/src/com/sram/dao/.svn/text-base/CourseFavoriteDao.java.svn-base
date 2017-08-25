package com.sram.dao;

import java.util.List;

import com.sram.dao.BaseDao;
import com.sram.entity.Course;
import com.sram.entity.CourseFavorite;

/**
 * Service - 课程收藏
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface CourseFavoriteDao  extends BaseDao<CourseFavorite,Long>{

	boolean courseFavoriteExists(Long courseId, Long memberId);

	void deleteCourFav(Long courseId, Long memberId);

	List<CourseFavorite> findFavoriteByUser(Long userId);

}
