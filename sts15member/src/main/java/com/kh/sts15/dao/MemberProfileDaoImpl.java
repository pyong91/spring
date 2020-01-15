package com.kh.sts15.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kh.sts15.vo.MemberVO;

@Repository
public class MemberProfileDaoImpl implements MemberProfileDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insertMemberProfile(MemberVO memberVO) {
		String sql = "insert into member_profile values("
				+ "member_profile_seq.nextval,?,?,?)";
		Object[] param = {
				memberVO.getMemberProfile().getOriginalFilename(),
				memberVO.getMemberProfile().getSize(),
				memberVO.getMember_no()
		};
		
		jdbcTemplate.update(sql, param);
	}
}
