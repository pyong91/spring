package com.kh.sts11;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.sts11.entity.Student;

/*
 * JdbcTemplate을 이용한 select 예제
 *  - RowMapper 인터페이스가 등장 
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
public class Test03 {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	//RowMapper = 변환기(Database row ---> DTO/VO
	private RowMapper<Student> mapper = new RowMapper<Student>() {
		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student s = new Student();
			s.setNo(rs.getInt("no"));
			s.setName(rs.getString("name"));
			s.setScore(rs.getInt("score"));
			
			return s;
		}
	};
	
	@Test
	public void select() {
		String sql = "select * from student order by no asc";
		List<Student> list = jdbcTemplate.query(sql, mapper);
		
		System.out.println("개수 : " + list.size());
		
		for(Student s : list) {
			System.out.println(s);
		}
	}
	
	@Test
	public void search() {
		String keyword = "홍길동";
		
		String sql = "select * from student where name = ?";
		List<Student> list = jdbcTemplate.query(sql, mapper, keyword);
	}
	
}
