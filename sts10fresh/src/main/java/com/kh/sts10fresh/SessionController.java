package com.kh.sts10fresh;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/session")
public class SessionController {
	
//	@GetMapping("") >> /session
//	@GetMapping("/") >> /session/   마지막 / = endpoint라고 부름
//	@GetMapping({"","/"}) >> 둘다
	@GetMapping({"","/"})
	public String home() {
		return "session/home";
	}
	
	@GetMapping("/set")
	public String set(HttpSession session) {
		session.setAttribute("id", "admin");
		
//		return "redirect:./"; 현재위치
		return "redirect:/session/";
	}
	
	@GetMapping("/remove")
	public String remove(HttpSession session) {
		session.removeAttribute("id");
		return "redirect:./";
//		return "redirect:/session/";
		
	}
}
