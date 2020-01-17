package com.kh.home.repository;

import com.kh.home.entity.MemberDto;

public interface MemberDao {
	void regist(MemberDto memberDto);

	MemberDto login(MemberDto memberDto);

	void updateLastLogin(String id);
}
