package com.kh.sts25.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {

	@GetMapping("/chat")
	public String chat() {
		return "chat";
	}

	@GetMapping("/chat2")
	public String chat2() {
		return "chat2";
	}

	@GetMapping("/chat3")
	public String chat3() {
		return "chat3";
	}
	
	@GetMapping("/login")
	public String login(@RequestParam String id, HttpSession session) {
		session.setAttribute("id", id);
		return "redirect:chat3";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("id");
		return "redirect:chat3";
	}
}
