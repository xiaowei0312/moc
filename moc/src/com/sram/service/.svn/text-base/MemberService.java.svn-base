/*
 * .
 * Support: http://www.moc.cc
 * 
 */
package com.sram.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.sram.Page;
import com.sram.Pageable;
import com.sram.Principal;
import com.sram.entity.Admin;
import com.sram.entity.Member;
import com.sram.entity.MemberRank;
import com.sram.entity.QuestionUploadAnalyseReport;


/**
 * Service - 会员
 * 
 * @author Sram Team
 * @version 1.0
 */
public interface MemberService extends BaseService<Member, Long> {

	/**
	 * 判断用户名是否存在
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否存在
	 */
	boolean usernameExists(String username);
	

	/**
	 * 判断用户名是否禁用
	 * 
	 * @param username
	 *            用户名(忽略大小写)
	 * @return 用户名是否禁用
	 */
	boolean usernameDisabled(String username);

	/**
	 * 判断E-mail是否存在
	 * 
	 * @param email
	 *            E-mail(忽略大小写)
	 * @return E-mail是否存在
	 */
	boolean emailExists(String email);

	/**
	 * 判断E-mail是否唯一
	 * 
	 * @param previousEmail
	 *            修改前E-mail(忽略大小写)
	 * @param currentEmail
	 *            当前E-mail(忽略大小写)
	 * @return E-mail是否唯一
	 */
	boolean emailUnique(String previousEmail, String currentEmail);

	/**
	 * 保存会员
	 * 
	 * @param member
	 *            会员
	 * @param operator
	 *            操作员
	 */
	void save(Member member, Admin operator);

	/**
	 * 获取经验金币
	 * @param memberId
	 * @return
	 */
	Object[] getExperienceCoin(Long memberId);

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
	 * 判断会员是否登录
	 * 
	 * @return 会员是否登录
	 */
	boolean isAuthenticated();

	/**
	 * 获取当前登录会员
	 * 
	 * @return 当前登录会员，若不存在则返回null
	 */
	Member getCurrent();

	/**
	 * 获取当前登录用户名
	 * 
	 * @return 当前登录用户名，若不存在则返回null
	 */
	String getCurrentUsername();
    
	
	/**
	 * 更新会员
	 * 
	 * @param member
	 *            会员
	 * @param modifyPoint
	 *            修改积分
	 * @param modifyBalance
	 *            修改余额
	 * @param depositMemo
	 *            修改余额备注
	 * @param operator
	 *            操作员
	 */
	void update(Member member, Integer modifyPoint, BigDecimal modifyBalance, String depositMemo, Admin operator);
    
	/*
     * 查找所有符合条件的会员列表
     */
	Page<Member>  findPage(Pageable pageable, MemberRank memberRank);

	QuestionUploadAnalyseReport analyzeXLS(MultipartFile file,String registPath,MemberRank memberRank);

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
	 * 更新经验和学币
	 * @param experienceValue
	 * @param learningCoin
	 * @param id
	 */
	void updateExperienceCoin(Integer experienceValue, Integer learningCoin,
			Long id);


	void updateCoinByUserId(Long memberId, Integer lessonCoin);
	
	/**
	 * 设置报考地区和报考大纲
	 * @param examOutlineCategoryId
	 * @param examAreaId
	 */
	void updateExamAreaAndExamOutlineCategory(String userName,Long examOutlineCategoryId,Long examAreaId);

}