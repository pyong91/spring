package com.kh.sts15.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.sts15.dto.MemberProfileDto;
import com.kh.sts15.vo.MemberVO;

@Repository
public class MemberProfileDaoImpl implements MemberProfileDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<MemberProfileDto> mapper = (rs, index)-> {
		return MemberProfileDto.builder()
									.profile_no(rs.getInt("profile_no"))
									.profile_uploadname(rs.getString("profile_uploadname"))
									.profile_size(rs.getLong("profile_size"))
									.member_no(rs.getInt("member_no"))
								.build();
	};
	
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
	
	@Override
	public MemberProfileDto get(int profile_no) {
		String sql = "select * from member_profile where profile_no = ?";
		Object[] param = {profile_no};
		return jdbcTemplate.query(sql,param,mapper).get(0);
	}
}
