package com.kh.sts21.controller;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.sts21.entity.CertDto;
import com.kh.sts21.repository.CertDao;
import com.kh.sts21.service.RandomService;

@Controller
@RequestMapping("/pw")
public class EmailPasswordController {
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private RandomService randomService;
	
	@Autowired
	private CertDao certDao;
	
	@GetMapping("/input")
	public String input() {
		return "pw/input";
	}
	
	@PostMapping("/input")
	public String input(@RequestParam String email) throws Exception{
		// 1. 랜덤번호를 3자리 생성
		String cert = randomService.generageCertificationNumber(3); 
		
		// 2. DB에 랜덤번호/이메일/시각을 저장
		certDao.regist(CertDto.builder().email(email).cert_no(cert).build());
		
		// 3. 이메일 전송
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		helper.setTo(email);
		helper.setSubject("비밀번호 변경 테스트 메일입니다");
		
		StringBuffer buffer = new StringBuffer();
		
		buffer.append("<h1>비밀번호 변경을 위해 하단 링크를 누르세요</h1>");
		buffer.append("<h2>");
		buffer.append("<a href='http://localhost:8080/sts21/pw/change?cert=");
		buffer.append(cert+"'>");
		buffer.append("이동");
		buffer.append("</a>");
		buffer.append("</h2>");
		
		helper.setText(buffer.toString(), true);
		
		sender.send(message);
		
		// 4. 사용자 화면 전환 (이 페이지에서 새로고침 ㄴㄴ 이메일 계속 발송됨)
		
		return "redirect:result";
	}
	
	@GetMapping("/result")
	public String result() {
		return "/pw/result";
	}
	
	@GetMapping("/change")
	public String change() {
		return "/pw/change";
	}
}
