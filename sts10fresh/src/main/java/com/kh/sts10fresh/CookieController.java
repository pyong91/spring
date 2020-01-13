package com.kh.sts10fresh;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ck")
public class CookieController {
	
	@GetMapping("/home")
	public String home() {
		return "cookie/home";
	}
	
	@GetMapping("/create")
	public String createCookie(HttpServletResponse response) throws UnsupportedEncodingException {
		// 쿠키 생성 절차 : 파일 형태이므로 파일 출력과 비슷하다
		// 1. 쿠키 객체를 생성한다.(기본생성자(파라미터없는생성자) 없음)
//		Cookie ck = new Cookie("id", "admin");
//		한글 넣는 법
		Cookie ck = new Cookie("id", URLEncoder.encode("글자", "UTF-8"));
		
		// 2. 객체에 정보를 설정한다.
		//  - 유효시간, 사용범위, ...
//		ck.setMaxAge(24*60*60); 하루
		ck.setMaxAge(5); // 유효시간(단위:초) 
		
		// 3. 응답 객체에 출력을 의뢰한다.
		response.addCookie(ck);
		
		return "redirect:home";
	}
	
	@GetMapping("/remove")
	public String removeCookie(HttpServletResponse response) throws UnsupportedEncodingException {
//		Cookie ck = new Cookie("id", "admin");
		Cookie ck = new Cookie("id", URLEncoder.encode("글자", "UTF-8"));

		ck.setMaxAge(0);
		response.addCookie(ck);
		
		return "redirect:home";
	}
	
	
	
}
