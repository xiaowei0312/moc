package com.sram.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.Constants.QuestionType;
import com.sram.dao.QuestionFavoriteDao;
import com.sram.entity.OutlineCategory;
import com.sram.entity.Question;
import com.sram.entity.QuestionFavorite;
import com.sram.entity.TestpaperItemResult.Status;
@Repository("questionFavoriteDaoImpl")
public class QuestionFavoriteDaoImpl extends BaseDaoImpl<QuestionFavorite, Long> implements QuestionFavoriteDao{
	@Override
	public Page<QuestionFavorite> findPage(Pageable pageable) {
		return super.findPage(pageable);
	}

	public QuestionFavorite findQuestionFavoriteById(Long testpaperId) {
		String jpql="select t from QuestionFavorite t  " +
		" left join fetch t.outlineCategory " +
		" where t.id=:testpaperId";
		TypedQuery<QuestionFavorite> query = entityManager.createQuery(jpql, QuestionFavorite.class).setFlushMode(FlushModeType.COMMIT).setParameter("testpaperId", testpaperId);
		return query.getSingleResult();
	}

	public List<Object[]> findQuestionFavorites(Long outlineCategoryId,	Long userId) {
		StringBuffer sb = new StringBuffer();
		sb.append("select	outline.id,	outline.name,	outline.parent,	outline.grade,	outline.orders,	outline.tree_path,ifnull(favoriteNum, 0) favoriteNum ");
		sb.append("	from	moc_outline_category outline ");
		sb.append("		left join ( ");
		sb.append("			select	outline.id,	outline.name,outline.grade,outline.tree_path,outline.parent,outline.orders,	count(*) favoriteNum ");
		sb.append("				from	moc_outline_category outline" );
		sb.append("				left join moc_question q on q.question_outline_category = outline.id   ");
		sb.append("				left join moc_question_favorite f on q.id=f.question_id ");
		sb.append("				where f.user_id =?  and f.root_outline_category=?	" );
		sb.append("				group by outline.id order by 	outline.orders ASC");
		sb.append("		) b on outline.id = b.id ");
		sb.append("	where outline.id =? or outline.tree_path like ? ");
		List<Object> list = new ArrayList<Object>();
			list.add(userId);
			list.add(outlineCategoryId);
			list.add(outlineCategoryId);
			list.add("%,"+outlineCategoryId+",%");
//			Query query= entityManager.getEntityManagerFactory().createEntityManager().createNativeQuery(arg0);
//			query.setParameter("userId", "userId")
//			.setParameter("outlineCategoryId", "outlineCategoryId")
//			.setParameter("outlineCategoryId", "outlineCategoryId")
//			.setParameter("outlineCategoryId", "outlineCategoryId")
//			return query.getResultList();
			
			List<Object[]> srcList = this.createNativeQuery(sb.toString(), list);
			List<Object[]> destList = sort3(srcList, null);
			//循环计算每一层的答题量
			for (Object[] obj : destList) {
				favorite(destList,obj);
			}
		return destList;
	}	
	
	/**
	 *收藏大纲的排序
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
	
	/**
	 * 收藏计算
	 * @param srcList
	 * @param obj
	 * @return
	 */
	private List<Object[]> favorite(List<Object[]> srcList, Object[] obj) {
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
	
	public List<QuestionFavorite> findAll() {
		TypedQuery<QuestionFavorite> query;
		String jpql = "select q from QuestionFavorite q ";
		query = entityManager.createQuery(jpql, QuestionFavorite.class).setFlushMode(FlushModeType.COMMIT);
		return query.getResultList();
	}
	 
	public QuestionFavorite findByQuestionIdAndUserId(Long questionId,Long userId){
		String jpql="select q from QuestionFavorite q"
				+ " where q.userId=:userId and q.question.id=:questionId";
				TypedQuery<QuestionFavorite> query = entityManager.createQuery(jpql, QuestionFavorite.class)
				.setParameter("questionId", questionId)
				.setParameter("userId", userId);
				List<QuestionFavorite> resultList = query.getResultList();
				if (resultList!=null&&resultList.size()>0) {
					return resultList.get(0);
				}
				return null;
	}
	
	public void removeByQuestionIdAndUserId(Long questionId,Long userId){
		String jpql="delete from QuestionFavorite q"
				+ " where q.userId=:userId and q.question.id=:questionId";
		entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT)
		.setParameter("questionId", questionId)
		.setParameter("userId", userId)
		.executeUpdate();
	}
	

	public List<Question> findFavoriteQuestions(Long outlineCategoryId,
			Long userId) {
		String jpql="";
		jpql="select q from Question q " +
		" left join fetch q.questionFavorites f" +
		" where f.userId=:userId " +
		" and (q.treePath like :treePath  or q.outlineCategory.id=:outLineId) " +
		" group by q" +
		" ORDER BY q.order asc";
		TypedQuery<Question> query = entityManager.createQuery(jpql,Question.class)
		.setParameter("userId", userId)
		.setParameter("treePath", "%,"+outlineCategoryId+",%")
		.setParameter("outLineId", outlineCategoryId)
		.setFlushMode(FlushModeType.COMMIT);
		return query.getResultList();
	}
	
	/**
	 * 个人中心我的题库的收藏
	 */
	public List<Object[]> findQuestionFavoritesByUserId(Long userId,Integer page) {
		String sql="";
		sql+=" SELECT outline.id,outline.name as outlineName,count(*),industry.name as industryName FROM moc_question_favorite qf ";
		sql+=" LEFT JOIN moc_outline_category outline on qf.root_outline_category=outline.id ";
		sql+=" LEFT JOIN moc_industry_category industry on industry.id=outline.industry_category_id ";
		sql+=" where qf.user_id=?";
		sql+=" GROUP BY outline.id";
		List<Object> list= new ArrayList<Object>();
		list.add(userId);
		return this.createNativeQuery(sql, list);
	}

	public Long findQuestionFavoritesCountByUserId(Long userId) {
		String sql=" select count(*) from (";
		sql+=" SELECT outline.id FROM moc_question_favorite qf ";
		sql+=" LEFT JOIN moc_outline_category outline on qf.root_outline_category=outline.id ";
		sql+=" where qf.user_id=?";
		sql+=" GROUP BY outline.id )favorite ";
		List<Object> list= new ArrayList<Object>();
		list.add(userId);
		List<Object[]> list2 = this.createNativeQuery(sql, list);
		Object objects = list2.get(0);
		return Long.parseLong(objects.toString());
	}

	public List<Long> findFavorite(Long outlineCategoryId, Long userId) {
		String sql ="select qf.question_id FROM moc_question_favorite qf" +
				" left join  moc_question q on qf.question_id=q.id" +
				" where qf.user_id=? and (q.question_outline_category=? or q.tree_path like ?)";
		List<Object> list= new ArrayList<Object>();
		list.add(userId);
		list.add( outlineCategoryId);
		list.add("%,"+outlineCategoryId+",%");
		 List<Object[]> query = this.createNativeQuery(sql, list);
		 List<Long> list2 = new ArrayList<Long>();
		 for (Object objects : query) {
			 list2.add(Long.parseLong(objects.toString()));
		}
		 return list2;
	}
}
