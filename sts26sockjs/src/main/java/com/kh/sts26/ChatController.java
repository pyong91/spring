package com.kh.sts26;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
	
	@GetMapping("/client")
	public String client() {
		return "client";
	}
}
