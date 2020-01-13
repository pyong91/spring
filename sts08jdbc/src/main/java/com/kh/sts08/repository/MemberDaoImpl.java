package com.kh.sts08.repository;

import org.springframework.jdbc.core.JdbcTemplate;

import com.kh.sts08.entity.MemberDto;

// MemberDao의 구현체
// - 실제로 수행할 코드를 가지게 됨

public class MemberDaoImpl implements MemberDao{
	
	//DB 처리를 도와줄 도구 자리를 생성(주입용)
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void regist(MemberDto memberDto) {
		String sql = "insert into member values("
				+ "?, ?, ?, sysdate, '일반회원', 0, ?, ?, ?, ?, null)";
		Object[] param = {
			memberDto.getId(),
			memberDto.getPw(),
			memberDto.getName(),
			memberDto.getPost(),
			memberDto.getBasic_addr(),
			memberDto.getExtra_addr(),
			memberDto.getPhone()
		};
		jdbcTemplate.update(sql, param); // 실행
	}
	
	
}
