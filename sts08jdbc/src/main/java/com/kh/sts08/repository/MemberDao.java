package com.kh.sts08.repository;

import com.kh.sts08.entity.MemberDto;

//기존 DAO의 요약판
// - 제어를 위한 상위 형태
// - 추상화 구조를 사용하는 것을 권장
public interface MemberDao {
	void regist(MemberDto memberDto);
}
