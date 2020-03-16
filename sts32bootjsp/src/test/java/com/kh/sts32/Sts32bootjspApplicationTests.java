package com.kh.sts32;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
class Sts32bootjspApplicationTests {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Test
	void contextLoads() {
		System.out.println(jdbcTemplate);
	}

}
