package com.kh.sts12.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.sts12.entity.MemoDto;

@Repository
public class MemoDaoImpl implements MemoDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<MemoDto> mapper = new RowMapper<MemoDto>() {
		@Override
		public MemoDto mapRow(ResultSet rs, int rowNum) throws SQLException {
//			MemoDto memoDto = new MemoDto();
//			memoDto.setNo(rs.getInt("no"));
//			memoDto.setContent(rs.getString("content"));
//			memoDto.setWhen(rs.getString("when"));
//			return memoDto;
			
			return MemoDto.builder()
					.no(rs.getInt("no"))
					.content(rs.getString("content"))
					.when(rs.getString("when"))
					.build();
		}
	};
	
	@Override
	public List<MemoDto> getList() {
		String sql = "select * from memo order by no desc";
		return jdbcTemplate.query(sql, mapper);
	}
	
	@Override
	public void insert(MemoDto memoDto) {
		String sql = "insert into memo values(memo_seq.nextval, ?, sysdate)";
		Object[] param = {memoDto.getContent()};
		jdbcTemplate.update(sql, param);
	}
}
