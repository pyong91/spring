package com.kh.sts13.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.sts13.entity.ProductDto;

@Repository
public class ProductDaoImpl implements ProductDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
//	private RowMapper<ProductDto> mapper = new RowMapper<ProductDto>() {
//		@Override
//		public ProductDto mapRow(ResultSet rs, int rowNum) throws SQLException {
//			return ProductDto.builder()
//								.no(rs.getInt("no"))
//								.name(rs.getString("name"))
//								.price(rs.getInt("price"))
//								.when(rs.getString("when"))
//								.build();
//		}
//	};
	
//	# 람다식
	private RowMapper<ProductDto> mapper = (rs, rowNum)-> {
		return ProductDto.builder()
							.no(rs.getInt("no"))
							.name(rs.getString("name"))
							.price(rs.getInt("price"))
							.when(rs.getString("when"))
							.build();
	};
	
	
	
	
	
	
	
	
	
	@Override
	public void insert(ProductDto productDto) {
		String sql = "insert into product values"
				+ "(product_seq.nextval, ?, ?, sysdate)";
		Object[] param = {productDto.getName(),
							productDto.getPrice()};
		jdbcTemplate.update(sql, param);
	}
	
	@Override
	public List<ProductDto> search(String name) {
		String sql = "select * from product where name like '%'||?||'%'"
				+ " and when between sysdate-30 and when";
		return jdbcTemplate.query(sql, mapper, name);
	}
}
