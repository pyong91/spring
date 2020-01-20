package com.kh.sts18.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
		@GetMapping("/error1")
		public String error1() {
			int a = 10 / 0;
			return "error1";
		}
		
		
//		@ExceptionHandler(Exception.class)	// 예외 처리기
//		public String handler(Exception ex) { // 예외 객체
//			logger.error("예외발생!", ex);
//			return "error/500";
//		}
}
