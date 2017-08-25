/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.dao;

import java.util.Date;
import java.util.List;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.Principal;
import com.sram.entity.Member;
import com.sram.entity.MemberRank;


/**
 * Dao - 会员
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface MemberDao extends BaseDao<Member, Long> {

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);

	/**
	 * 判断E-mail是否存在
	 * 
	 * @param email
	 *            E-mail(忽略大小写)
	 * @return E-mail是否存在
	 */
	boolean emailExists(String email);

	/**
	 * 根据用户名查找会员
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 会员，若不存在则返回null
	 */
	Member findByUsernameOrPhone(String username);

	/**
	 * 根据E-mail查找会员
	 * 
	 * @param email
	 *            E-mail(忽略大小写)
	 * @return 会员，若不存在则返回null
	 */
	List<Member> findListByEmail(String email);

	/**
	 * 查找会员消费信息
	 * 
	 * @param beginDate
	 *            起始日期
	 * @param endDate
	 *            结束日期
	 * @param count
	 *            数量
	 * @return 会员消费信息
	 */
	List<Object[]> findPurchaseList(Date beginDate, Date endDate, Integer count);

	Page<Member>  findPage(Pageable pageable, MemberRank memberRank);

	List<Object[]> findHotMember();

	/**
	 * 查找头象
	 * @param principal
	 * @return
	 */
	String findHeadImage(Principal principal);

	List<Member> findList(Date beginDate, Date endDate);

	Page<Member> findPage(Pageable pageable, Date beginDate, Date endDate);

	List<Object[]> findAnalySisList(Date beginDate, Date endDate);

	/**
	 * 更新经验值和学币 
	 * @param experienceValue
	 * @param learningCoin
	 * @param memberId
	 */
	void updateExperienceCoin(Integer experienceValue, Integer learningCoin,
			Long memberId);

	Object[] getExperienceCoin(Long memberId);

	void updateCoinByUserId(Long memberId, Integer lessonCoin);
	/**
	 * 设置报考地区和报考大纲
	 * @param examOutlineCategoryId
	 * @param examAreaId
	 */
	void updateExamAreaAndExamOutlineCategory(String userName,Long examOutlineCategoryId,Long examAreaId);


}