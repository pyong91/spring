package com.kh.sts15.dao;

import com.kh.sts15.dto.MemberProfileDto;
import com.kh.sts15.vo.MemberVO;

public interface MemberProfileDao {
	void insertMemberProfile(MemberVO memberVO);

	MemberProfileDto get(int profile_no);
}
