package com.kh.sts33.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

//@Configuration
public class DatabaseConfiguration {
	@Bean
	public DataSource dataSource() {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName("oracle.jdbc.OracleDriver");
		source.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
		source.setUsername("home");
		source.setPassword("home");
		
		source.setMaxTotal(20);
		source.setMaxIdle(10);
		source.setMaxWaitMillis(3000);
		
		return source;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dbcpSource) {
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dbcpSource);
		return template;
	}
}
