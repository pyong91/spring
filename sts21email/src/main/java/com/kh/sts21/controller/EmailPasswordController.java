package com.kh.sts21.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kh.sts21.entity.CertDto;
import com.kh.sts21.repository.CertDao;
import com.kh.sts21.service.EmailService;
import com.kh.sts21.service.RandomService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/pw")
@Slf4j
public class EmailPasswordController {
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private CertDao certDao;
	
	@GetMapping("/input")
	public String input() {
		return "pw/input";
	}
	
	@PostMapping("/input")
	public String input(@RequestParam String email) throws MessagingException {
		emailService.sendChangePasswordMail(email);
		return "redirect:result";
	}
	
	@GetMapping("/result")
	public String result() {
		return "pw/result";
	}
	
	@GetMapping("/change")
	public String change(
			@RequestParam String cert,
			@RequestParam String email,
			HttpServletResponse response) {
//		필요한 작업
//		- 사용자가 이메일에서 링크를 누르면 이곳으로 들어온다.
//		- 들어오면서 정상적인 링크라면 
//			cert라는 파라미터와 email이라는 파라미터를 가지고 온다
//		- 위의 두 파라미터를 받아서 DB에 검증을 실시
//		- 위의 인증결과와 무관하게 해당 이메일의 인증정보를 모두 삭제
		boolean enter = certDao.check(email, cert);
		log.info("enter = {}", enter);
		certDao.delete(email);
		if(!enter) {
			//에러 코드 송출
			response.setStatus(403);
		}
		return "pw/change";
	}
	
}