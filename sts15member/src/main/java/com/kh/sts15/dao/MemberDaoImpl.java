package com.kh.sts15.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.sts15.vo.MemberVO;

@Repository
public class MemberDaoImpl implements MemberDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insertMember(MemberVO memberVO) {
		String sql = "insert into member values(member_seq.nextval, ?, ?, ?)";
		Object[] param = {memberVO.getMember_id(),
							memberVO.getMember_pw(),
							memberVO.getMember_nick()
		};
		
		jdbcTemplate.update(sql, param);
	}
	
	private RowMapper<Integer> mapper = (rs, rowNum)-> {
		return rs.getInt("member_no");
	};
	
	@Override
	public int getSequenceNumber(String id) {
		String sql = "select * from member where member_id = ?";
		return jdbcTemplate.query(sql, mapper, id).get(0);
	}

	
//	@Override	강사님코드
//	public int getSequence() {
//		String sql = "select member_seq.nextval from dual";
//		return jdbcTemplate.queryForObject(sql, int.class);
//	}
}
