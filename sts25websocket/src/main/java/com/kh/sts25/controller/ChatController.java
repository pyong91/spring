package com.kh.sts25.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
	
	@GetMapping("/chat")
	public String chat() {
		return "chat";
	}
}
