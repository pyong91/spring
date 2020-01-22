package com.kh.sts24.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.sts24.entity.UsersDto;

@Controller
public class UsersController {
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private SqlSession sqlSession;
	
	@GetMapping("/join")
	public String join() {
		return "join";
	}
	
	@PostMapping("/join")
	public String join(@ModelAttribute UsersDto user) {
//		[1] user에 들어있는 pw를 암호화 한다(bcrypt)
//		String origin = user.getPw();
//		String result = encoder.encode(origin);
//		user.setPw(result);
		
		user.setPw(encoder.encode(user.getPw()));
		
//		[2] DB에 저장
		sqlSession.insert("users.join", user);
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute UsersDto user) {
		
//		[1] 검색을 해서 결과 유무를 확인한다(ID만으로 검색)
		UsersDto find = sqlSession.selectOne("users.get", user);
		
//		[2] 필요한 처리를 한다
		if(find==null) {
			return "redirect:/login?error";
		} else {
			boolean correct = encoder.matches(user.getPw(), find.getPw());
			if(correct) {
				return "redirect:/";
			} else {
				return "redirect:/login?error";
			}
		}
	}
	
}
