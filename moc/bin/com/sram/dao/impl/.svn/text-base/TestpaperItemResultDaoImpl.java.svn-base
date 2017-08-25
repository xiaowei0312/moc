package com.sram.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.FlushModeType;

import org.springframework.stereotype.Repository;

import com.sram.Constants.QuestionType;
import com.sram.Page;
import com.sram.Pageable;
import com.sram.dao.TestpaperItemResultDao;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Question;
import com.sram.entity.Testpaper;
import com.sram.entity.TestpaperItemResult;
import com.sram.entity.TestpaperResult;

@Repository("testpaperItemResultDaoImpl")
public class TestpaperItemResultDaoImpl extends
		BaseDaoImpl<TestpaperItemResult, Long> implements
		TestpaperItemResultDao {

	public Page<Testpaper> findPage(Pageable pageable, String stem,
			String testpaperType, String difficulty, String IdTreePath) {
		return null;
	}

	public List<TestpaperItemResult> findAll() {
		return null;
	}

	public List<TestpaperItemResult> findTestpaperItemResult(Testpaper testpaper) {
		return null;
	}

	public TestpaperItemResult findTestpaperItemResult(Question question) {
		return null;
	}

	public List<TestpaperItemResult> findTestpaperItemResult(
			TestpaperResult testpaperResult) {
		return null;
	}

	/**
	 * <p>
	 * 功能:插入试题结果
	 * </p>
	 * @author 杨楷
	 * @date 2015-4-7 下午05:07:24
	 * @param status
	 * @param answer
	 * @param testpaperItemId
	 * @param testpaperResultId
	 */
	public void updateResult(TestpaperItemResult testpaperItemResult) {
		String buffer = "update TestpaperItemResult result "
				+ " set result.status=:status,result.answer=:answer,result.score=:score"
				+ " where result.testpaperItem.id=:testpaperItemId and result.testpaperResult.id=:testpaperResultId";
		entityManager
				.createQuery(buffer)
				.setParameter("status", testpaperItemResult.getStatus())
				.setParameter("answer", testpaperItemResult.getAnswer())
				.setParameter("testpaperItemId",
						testpaperItemResult.getTestpaperItem().getId())
				.setParameter("testpaperResultId",
						testpaperItemResult.getTestpaperResult().getId())
				.setParameter("score",
								testpaperItemResult.getScore())
				.setFlushMode(FlushModeType.COMMIT).executeUpdate();
	}

	/**
	 * 我的练习错题统计，  根据大纲ID,用户ID查询错误题目
	 * 
	 * @param outlineCategoryID
	 * @return
	 */
	public List<Object[]> findWrongTestpaperItemResults(
			Long outlineCategoryId, Long userId) {
		StringBuffer sb = new StringBuffer();
		sb.append("select	outline.id,	outline.name,	outline.parent,	outline.grade,	outline.orders,	outline.tree_path,	ifnull(cuowu, 0) cuowu ");
		sb.append("	from	moc_outline_category outline ");
		sb.append("		left join ( ");
		sb.append("			select	outline.id,	outline.name,outline.grade,outline.tree_path,outline.parent,	item.`status`,outline.orders,	count(*) cuowu ");
		sb.append("				from	moc_outline_category outline" );
		sb.append("				left join moc_question q on q.question_outline_category = outline.id   ");
		sb.append("				left join (select * from  moc_testpaper_item_result where user_id =? and `status`!=?  GROUP BY question_id) item on item.question_id = q.id ");
		sb.append("				left join moc_testpaper_result result on result.id= item.testpaper_result_id");
		sb.append("				where   result.`status`=? and item.root_outline_category=?	and q.question_type != ? " );
		sb.append("				group by outline.id order by 	outline.orders ASC");
		sb.append("		) b on outline.id = b.id ");
		sb.append("	where outline.id = ? or outline.tree_path like ? ");
		List<Object> list = new ArrayList<Object>();
			list.add(userId);
			list.add(com.sram.entity.TestpaperItemResult.Status.right.ordinal());
			list.add(com.sram.Constants.Status.finished.ordinal());
			list.add(outlineCategoryId);
			list.add(QuestionType.material.ordinal());
			list.add(outlineCategoryId);
			list.add("%,"+outlineCategoryId+",%");
			List<Object[]> srcList = this.createNativeQuery(sb.toString(), list);
			List<Object[]> destList = sort3(srcList, null);
			//循环计算每一层的答题量
			for (Object[] obj : destList) {
				wrong(destList,obj);
			}
			return destList;
	}

	/**
	 * 根据testpaperResultId 和userId 查询 涉及考点
	 * 
	 * @param testpaperResultId
	 *            考卷ID
	 * @param userId
	 *            用户ID
	 * @return
	 */
	public List<Object[]> findTestpaperItemResults(Long outlineCategoryId,Long testpaperResultId,
			Long userId) {
		StringBuffer sb = new StringBuffer();
		sb.append("	select outline.id,outline.name,outline.parent,outline.grade,outline.orders,outline.tree_path,"
				+ "	IFNULL(b.zq,0) zhengque,IFNULL(b.zs,0) cuowu "
				
				//2015,5,11----荣刚平－－－关联类型与关联id
				+" ,outline.course_chapter_type,outline.course_chapter_id "
				+ " from moc_outline_category outline ");
		sb.append("	LEFT JOIN ( ");
		sb.append("		SELECT	a.id, a.orders, a.tree_path, sum(a.zq) zq,sum(a.zs+a.zq) zs	 ");
		sb.append("	 		FROM (SELECT  outline.id,	outline. NAME,outline.grade,outline.tree_path,outline.parent,item.`status`,outline.orders, ");
		sb.append("						CASE item.`status` WHEN 1 THEN	count(*) ELSE   0	END 'zq',	 ");
		sb.append("						CASE item.`status`	WHEN 0 THEN  count(*) WHEN 2 THEN  count(*)	WHEN 3 THEN  count(*) ELSE 0  END 'zs',");
		sb.append("						item.testpaper_result_id	");
		sb.append("					FROM  moc_outline_category outline	");
		sb.append("	  					LEFT JOIN moc_question q ON q.question_outline_category = outline.id	 ");
		sb.append("	  					LEFT JOIN moc_testpaper_item_result item ON item.question_id = q.id		    ");
		sb.append("	     			 WHERE item.testpaper_result_id =?	AND item.user_id = ? and q.question_type != ? ");
		sb.append("	  				 GROUP BY outline.id,item.`status` " );
		sb.append("			     ) a GROUP BY a.id  ORDER BY	a.orders ASC) b  on outline.id=b.id ");
		sb.append("	  where outline.id = ? or outline.tree_path LIKE ?  ");

		List<Object> list = new ArrayList<Object>();
		list.add(testpaperResultId);
		list.add(userId);
		list.add(QuestionType.material.ordinal());
		list.add(outlineCategoryId);
		list.add("%,"+outlineCategoryId+",%");
		List<Object[]> srcList = this.createNativeQuery(sb.toString(), list);
		List<Object[]> destList = sort3(srcList, null);
		//循环计算每一层的答题量
		for (Object[] obj : destList) {
			calculateRate(destList,obj);
		}
		return destList;
	}

	/**
	 * 排序商品分类
	 * 
	 * @param productCategories
	 *            商品分类
	 * @param parent
	 *            上级商品分类
	 * @return 商品分类
	 */
	private List<OutlineCategory> sort(List<OutlineCategory> outlineCategorys,
			OutlineCategory parent) {
		List<OutlineCategory> result = new ArrayList<OutlineCategory>();
		if (outlineCategorys != null) {
			for (OutlineCategory outlineCategory : outlineCategorys) {
				if ((outlineCategory.getParent() != null && outlineCategory
						.getParent().equals(parent))
						|| (outlineCategory.getParent() == null && parent == null)) {
					result.add(outlineCategory);
					result.addAll(sort(outlineCategorys, outlineCategory));
				}
			}
		}
		return result;
	}

	/**
	 *能力评估的排序
	 * @param srcList
	 * @param obj
	 */
	private List<Object[]> sort3(List<Object[]> srcList, Object[] obj) {
		List<Object[]> result = new ArrayList<Object[]>();
		if (srcList != null) {
			for (Object[] tempObj : srcList) {
				if ((tempObj[2] != null && obj != null && tempObj[2]
						.equals(obj[0])) || (tempObj[2] == null && obj == null)) {
					result.add(tempObj);
					result.addAll(sort3(srcList, tempObj));
				}
			}
		}
		
		return result;
	}
	
	
	private List<Object[]> calculateRate(List<Object[]> srcList, Object[] obj) {
		List<Object[]> result = new ArrayList<Object[]>();
		int id = 0;
		int qTrueNums = 0;
		int qTotalNums = 0;
		if (obj != null) {
			id = Integer.parseInt(obj[0].toString());
			qTrueNums = Integer.parseInt(obj[6].toString());
			qTotalNums = Integer.parseInt(obj[7].toString());

		}
		if (srcList != null) {
			for (Object[] tempObj : srcList) {
				String treePath = tempObj[5].toString();// treePath
				if (treePath.contains("," + id + ",")) {
					qTrueNums += Integer.parseInt(tempObj[6].toString());
					qTotalNums += Integer.parseInt(tempObj[7].toString());
				}
			}
		}
		if (obj != null) {
			obj[6] = qTrueNums;
			obj[7] = qTotalNums;
		}
		return result;
	}
	/**
	 * 错题计算
	 * @param srcList
	 * @param obj
	 * @return
	 */
	private List<Object[]> wrong(List<Object[]> srcList, Object[] obj) {
		List<Object[]> result = new ArrayList<Object[]>();
		int id = 0;
		int wrongNums = 0;
		if (obj != null) {
			id = Integer.parseInt(obj[0].toString());
			wrongNums = Integer.parseInt(obj[6].toString());
		}
		if (srcList != null) {
			for (Object[] tempObj : srcList) {
				String treePath = tempObj[5].toString();// treePath
				if (treePath.contains("," + id + ",")) {
					wrongNums += Integer.parseInt(tempObj[6].toString());
				}
			}
		}
		if (obj != null) {
			obj[6] = wrongNums;
		}
		return result;
	}
	

	/**
	 * <p>
	 * 功能:查找根据考卷id查找条目答案
	 * </p>
	 * 
	 * @author 杨楷
	 * @date 2015-4-9 下午03:41:12
	 * @param testpaperResultId
	 * @return
	 */
	public List<TestpaperItemResult> findByTestpaperResultId(
			Long testpaperResultId) {
		String jpql = "select t from TestpaperItemResult t  "
				+ " where t.testpaperResult.id=:testpaperResultId";
		return entityManager.createQuery(jpql, TestpaperItemResult.class)
				.setParameter("testpaperResultId", testpaperResultId)
				.getResultList();
	}
	
	/**
	 * 根据大纲ID和userId 查询 涉及考点，能力图表
	 */
	public List<Object[]> findTestpaperItemResults(Long outlineCategoryId,Long userId) {
		StringBuffer sb = new StringBuffer();
		sb.append("	select outline.id,outline.name,outline.parent,outline.grade,outline.orders,outline.tree_path,"
				+ "	IFNULL(b.zq,0) zhengque,IFNULL(b.zs,0) zongshu  from moc_outline_category outline ");
		sb.append("	LEFT JOIN ( ");
		sb.append("		SELECT	a.id, a.orders, a.tree_path, sum(a.zq) zq,sum(a.zs+a.zq) zs	 ");
		sb.append("	 		FROM (SELECT  outline.id,	outline. NAME,outline.grade,outline.tree_path,outline.parent,item.`status`,outline.orders, ");
		sb.append("						CASE item.`status` WHEN 1 THEN	count(*) ELSE   0	END 'zq',	 ");
		sb.append("						CASE item.`status`	WHEN 0 THEN  count(*) WHEN 2 THEN  count(*)	WHEN 3 THEN  count(*) ELSE 0  END 'zs',");
		sb.append("						item.testpaper_result_id	");
		sb.append("					FROM  moc_outline_category outline	");
		sb.append("	  					LEFT JOIN moc_question q ON q.question_outline_category = outline.id	 ");
		sb.append("	  					LEFT JOIN moc_testpaper_item_result item ON item.question_id = q.id		    ");
		sb.append("	  					LEFT JOIN moc_testpaper_result result ON item.testpaper_result_id = result.id		    ");
		sb.append("	     			 WHERE  item.user_id = ? and q.question_type != ? and result.`status`=? ");
		sb.append("	  				 GROUP BY outline.id,item.`status`" );
		sb.append("			     ) a GROUP BY a.id  ORDER BY	a.orders ASC) b  on outline.id=b.id ");
		sb.append("	  where outline.id = ? or outline.tree_path LIKE ?   	");

		List<Object> list = new ArrayList<Object>();
		list.add(userId);
		list.add(QuestionType.material.ordinal());
		list.add(com.sram.Constants.Status.finished.ordinal());
		list.add(outlineCategoryId);
		list.add("%,"+outlineCategoryId+",%");
		List<Object[]> srcList = this.createNativeQuery(sb.toString(), list);
		List<Object[]> destList = sort3(srcList, null);
		//循环计算每一层的答题量
		for (Object[] obj : destList) {
			calculateRate(destList,obj);
		}
		return destList;
	}

	public List<Object[]> findWrongTestpaperItemResultsByUserId(Long userId,Integer page) {
		String sql = "";
		sql+=" SELECT outline.id,outline.name as outlineName,count(*),industry.name as industryName" +
				"  FROM (SELECT * from moc_testpaper_item_result GROUP BY question_id) itemResult";
		sql+=" LEFT JOIN moc_question q on q.id=itemResult.question_id";
		sql+=" LEFT JOIN moc_outline_category outline on itemResult.root_outline_category=outline.id";
		sql+=" left join moc_testpaper_result result on result.id= itemResult.testpaper_result_id";
		sql+=" LEFT JOIN moc_industry_category industry on industry.id=outline.industry_category_id";
		sql+=" where itemResult.user_id=?  and result.`status`=? and itemResult.`status`!=? and q.question_type!=?";
		sql+=" GROUP BY outline.id    ";
		List<Object> list= new ArrayList<Object>();
		list.add(userId);
		list.add(com.sram.Constants.Status.finished.ordinal());
		list.add( com.sram.entity.TestpaperItemResult.Status.right.ordinal());
		list.add(QuestionType.material.ordinal());
		return this.createNativeQueryPage(sql, page, 10, list);
	}
	public Long findWrongTestpaperItemResultsCountByUserId(Long userId) {
		String sql = "select count(*) from (";
		sql+=" SELECT outline.id FROM moc_testpaper_item_result itemResult";
		sql+=" LEFT JOIN moc_question q on q.id=itemResult.question_id";
		sql+=" left join moc_testpaper_result result on result.id= itemResult.testpaper_result_id";
		sql+=" LEFT JOIN moc_outline_category outline on itemResult.root_outline_category=outline.id";
		sql+=" where itemResult.user_id=? and result.`status`=? and itemResult.`status`!=? and q.question_type!=?";
		sql+=" GROUP BY outline.id ) wrong    ";
		List<Object> list= new ArrayList<Object>();
		list.add(userId);
		list.add(com.sram.Constants.Status.finished.ordinal());
		list.add( com.sram.entity.TestpaperItemResult.Status.right.ordinal());
		list.add(QuestionType.material.ordinal());
		List<Object[]> list2 = this.createNativeQuery(sql, list);
		Object objects = list2.get(0);
		return Long.parseLong(objects.toString());
	}
	/**
	 * <p>功能:更新答案</p> 
	 * @author 杨楷
	 * @date 2015年5月8日 上午11:43:32 
	 * @param testpaperResultId
	 * @param testpaeprItemId
	 * @param jsonUserAnswer
	 */
	public void updateUserAnswer(Long testpaperResultId,Long testpaeprItemId,String jsonUserAnswer){
		String buffer = "update TestpaperItemResult result "
				+ " set result.answer=:answer"
				+ " where result.testpaperItem.id=:testpaperItemId and result.testpaperResult.id=:testpaperResultId";
		entityManager
				.createQuery(buffer)
				.setParameter("answer", jsonUserAnswer)
				.setParameter("testpaperItemId",
						testpaeprItemId)
				.setParameter("testpaperResultId",
						testpaperResultId)
				.setFlushMode(FlushModeType.COMMIT).executeUpdate();
	}

}