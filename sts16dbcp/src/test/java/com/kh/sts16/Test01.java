package com.kh.sts16;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

// DBCP 방식이 정상 작동하는지 보기 위해 jdbcTemplate을 테스트

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
public class Test01 {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void test() {
		String sql = "select count(*) from member";
		int count = jdbcTemplate.queryForObject(sql, int.class);
		System.out.println("count : " + count);
	}
}
