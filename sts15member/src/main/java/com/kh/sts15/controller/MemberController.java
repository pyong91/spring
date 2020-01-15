package com.kh.sts15.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.sts15.service.RegistService;
import com.kh.sts15.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private RegistService registService;
	
	@GetMapping("/regist")
	public String regist() {
		return "regist";
	}
	
	@PostMapping("/regist")
	public String regist(@ModelAttribute MemberVO memberVO) throws IllegalStateException, IOException {
		registService.store(memberVO);
		return "regist";
	}
}
