package com.kh.sts21;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})	
@WebAppConfiguration
@Slf4j
public class Test03 {
	
	@Autowired
	private JavaMailSender sender;
	
	@Test
	public void sendMimeMessage() throws MessagingException, Exception {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		
		// helper를 이용하여 설정하면 message에 설정이 됨
		String[] to = {"rushhour9@naver.com"};
		helper.setTo(to);
		
		helper.setSubject("메시징 테스트");
		helper.setText("<h1>테스트!!</h1>", true);
		
		// 첨부파일 추가
		// - DataSource 형태의 파일 데이터가 필요(주의 : sql에서 쓰던것과 다름)
		// - javax.activation.DataSource
		DataSource source = new FileDataSource("D:/lion.png");
		
		helper.addAttachment(
				URLEncoder.encode("테스트", "UTF-8"),source);
		
		// 전송
		sender.send(message);
	}
}
