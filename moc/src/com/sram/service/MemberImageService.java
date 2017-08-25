package com.sram.service;

import com.sram.entity.Member;
import com.sram.entity.Teacher;

public interface MemberImageService {

	/**
	 *	删除会员头像
	 * @param member
	 */
	void deleteMemberHeadImage(Member member);
}
