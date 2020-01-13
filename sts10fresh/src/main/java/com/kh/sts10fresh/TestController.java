package com.kh.sts10fresh;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 새롭게 배우는 컨트롤러의 형태
 *  - 기존에는 상속을 통해서 만드는 형태로 구현
 *  - meta programming 방식을 사용(annotation)
 */

@Controller
public class TestController {
//	파라미터(필요한것만), 예외처리(필요한것만)
	@RequestMapping("/test")
	public String home() {
		return "test"; // WEB-INF-views/test.jsp
	}
	
//	정식표현 (value="/test2", 옵션2, 옵션3)
	@RequestMapping(value = "/test2")
	public String home2() {
		return "test";
	}
}

