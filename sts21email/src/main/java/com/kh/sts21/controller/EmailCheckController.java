package com.kh.sts21.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kh.sts21.service.EmailService;
import com.kh.sts21.service.RandomService;

@Controller
public class EmailCheckController {

	@Autowired
	private EmailService emailService;

	@Autowired
	private RandomService randomService;
	
	@GetMapping("/check")
	public String check() {
		return "check";
	}

	@GetMapping("/send")
	@ResponseBody
	public String send(@RequestParam String email, HttpSession session) {
		// 인증번호를 세션이든 DB든 어디에 저장
		
//		String cert = "123456";
		String cert = randomService.generateCertificationNumber(6);
		
		session.setAttribute("cert", cert);
		return emailService.sendCertMessage(email, cert);
	}

	@GetMapping("/validate")
	@ResponseBody
	public String validate(HttpSession session, @RequestParam String cert) {
		String value = (String) session.getAttribute("cert");
		session.removeAttribute("cert");
		if (value.equals(cert)) {
			return "success";
		} else {
			return "fail";
		}
	}

}
