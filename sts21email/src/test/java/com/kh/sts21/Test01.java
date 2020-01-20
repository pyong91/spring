package com.kh.sts21;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
//연동할 수 있는 환경 만들어주기
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})	
@WebAppConfiguration
public class Test01 {
	
	private JavaMailSender sender;
	
	@Before
	public void prepare() {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		senderImpl.setHost("smtp.gmail.com");
		senderImpl.setPort(587);
		senderImpl.setUsername("pyong91test@gmail.com");
		senderImpl.setPassword("dnjfydaos1!");
		
		Properties prop = new Properties();
		prop.put("mail.smtp.auth",true);
		prop.put("mail.smtp.starttls.enable", true);
		
		senderImpl.setJavaMailProperties(prop);
		
		sender = senderImpl;
	}
	
	@Test
	public void sendMail() {
		// 메시지 객체 생성
		SimpleMailMessage message = new SimpleMailMessage();
		
		// 정보 설정 : 대상정보, 제목, 내용
		String[] to = {"rushhour9@naver.com"};
		message.setTo(to);
		
		String[] cc = {"rushhour9@naver.com"};
		message.setCc(cc);
		
		String[] bcc = {"rushhour9@naver.com"};
		message.setBcc(bcc);
		
		message.setSubject("테스트 메일");
		message.setText("테스트 메일입니다.");
		
		sender.send(message);
	}
	
}
