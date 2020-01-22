package com.kh.sts24;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

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
public class Test04 {
	
	@Autowired
	private EncryptService encryptService;
	
	@Test
	public void test() throws UnsupportedEncodingException, GeneralSecurityException {
		String origin = "hello";
		String offset = "kh";
		
		String result = encryptService.AES256Encrypt(origin, offset);
		String restore = encryptService.AES256Decrypt(result, offset);
		
		log.info("origin = {}", origin);
		log.info("result = {}", result);
		log.info("restore = {}", restore);
	}
}
