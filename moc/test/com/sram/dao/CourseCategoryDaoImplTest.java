package com.sram.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.sram.dao.ArticleCategoryDao;
import com.sram.dao.ArticleDao;
import com.sram.dao.CourseCategoryDao;
import com.sram.dao.TagDao;
import com.sram.entity.Article;
import com.sram.entity.ArticleCategory;
import com.sram.entity.CourseCategory;
import com.sram.entity.Tag;
import com.sram.entity.Tag.Type;

/**
 * Test - 文章
 * 
 * @author Sram Team
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/applicationContext.xml" })
@Transactional
public class CourseCategoryDaoImplTest {

	@Resource(name = "courseCategoryDaoImpl")
	private CourseCategoryDao courseCategoryDao;

	/**
	 * 测试FindChildren
	 */
	@Test
	public void testFindChildren() {
		List<CourseCategory> children = courseCategoryDao.findChildren(null,
				null);

		for (CourseCategory courseCategory : children) {
			System.out.println(courseCategory.getName());
		}
	}

}