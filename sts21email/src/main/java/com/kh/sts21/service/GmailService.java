package com.kh.sts21.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kh.sts21.entity.CertDto;
import com.kh.sts21.repository.CertDao;

@Service
public class GmailService implements EmailService{

	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private CertDao certDao;
	
	@Autowired
	private RandomService randomService;

	@Override
	public String sendCertMessage(String email, String cert) {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			String[] to = {email};
			message.setTo(to);
			message.setSubject("[KH정보교육원] 인증을 위한 이메일입니다.");
			message.setText("인증번호 : " + cert);
			sender.send(message);
			return "success";
		}
		catch(Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

	@Transactional
	@Override
	public void sendChangePasswordMail(String email) throws MessagingException {
		//1. 랜덤번호를 3자리 생성
		String cert = randomService.generateCertificationNumber(3);
		
		//2. DB에 랜덤번호/이메일/시각을 저장
		certDao.regist(CertDto.builder().email(email).cert_no(cert).build());
		
		//3. 이메일 전송
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = 
				new MimeMessageHelper(message, true, "UTF-8");
		
		helper.setTo(email);
		helper.setSubject("[KH정보교육원] 비밀번호 변경 메일");
		
//				주소 생성
//				String url = "http://localhost:8080/sts21/pw/change?cert="+cert+"&email="+email;
		String url = ServletUriComponentsBuilder
									.fromCurrentContextPath()
									.port(8080)
									.path("/pw/change")
									.queryParam("cert", cert)
									.queryParam("email", email)
									.toUriString();
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<h1>비밀번호 변경을 위해 하단 링크를 누르세요</h1>");
		buffer.append("<h2>");
		buffer.append("<a href='");
		buffer.append(url);
		buffer.append("'>");
		buffer.append("이동");
		buffer.append("</a>");
		buffer.append("</h2>");
		
		helper.setText(buffer.toString(), true);
		
		sender.send(message);
	}
	
}