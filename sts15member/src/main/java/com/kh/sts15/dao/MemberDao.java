package com.kh.sts15.dao;

import com.kh.sts15.vo.MemberVO;


public interface MemberDao {
	void insertMember(MemberVO memberVO);
	int getSequenceNumber(String id);
}
