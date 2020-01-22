package com.kh.sts24;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test05 {
	
	@Test
	public void test() {
		// 단방향 암호화 : bcrypt
		String origin = "hello";
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String result = encoder.encode(origin);
		
		log.info("origin = {}", origin);
		log.info("result = {}", result);
		log.info("length = {}", result.length());
		
		boolean same = encoder.matches(origin, result);
		log.info("same = {}", same);
	}
}
