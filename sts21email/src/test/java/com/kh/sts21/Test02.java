package com.kh.sts21;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})	
@WebAppConfiguration
public class Test02 {
	@Autowired
	private JavaMailSenderImpl emailSender;
	
	@Test
	public void emailTest() {
		SimpleMailMessage message = new SimpleMailMessage();
		
		String[] to = {"rushhour9@naver.com"};
		message.setTo(to);
		
		message.setSubject("테스트메일2");
		message.setText("테스트메일2입니다.");
		
		emailSender.send(message);
	}
	
}
