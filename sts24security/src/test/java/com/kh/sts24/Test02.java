package com.kh.sts24;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.sts24.service.EncryptService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})	
@WebAppConfiguration
public class Test02 {
	
	@Autowired
	private EncryptService encryptService;
	
	@Test
	public void test() {
		String origin = "hello";
		String result = encryptService.caesarEncrypt(origin, 5);
		
		log.info("origin = {}", origin);
		log.info("result = {}", result);
		
		result = encryptService.caesarDecrypt(result, 5);
		log.info("result = {}", result);
	}

}
