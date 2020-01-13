package com.kh.sts08.repository;

import com.kh.sts08.entity.MemberDto;

// MemberDao의 구현체
// - 실제로 수행할 코드를 가지게 됨

public class MemberDaoImpl2 implements MemberDao{

	@Override
	public void regist(MemberDto memberDto) {
		System.out.println("regist 실행");
		
	}
	
}
