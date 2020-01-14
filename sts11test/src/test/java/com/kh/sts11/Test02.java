package com.kh.sts11;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/*
 * 단순한 테스트가 아닌 내가 기존에 만들어놓은 환경과 연계해서 테스트
 * - root-context.xml (전역 설정)
 * - servlet-context.xml (서블릿 설정)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
public class Test02 {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	public void insert() {
		// jdbcTemplate에는 무엇이 들어있을까?
		System.out.println(jdbcTemplate);
		
		String sql = "insert into student values(student_seq.nextval, ?, ?)";
		
//		jdbcTemplate.update(sql, "홍길동", 50);
		Object[] param = {"홍길동", 50};
		jdbcTemplate.update(sql, param);
		//insert, update, delete 모두 update란 명령을 사용
		
	}
}
