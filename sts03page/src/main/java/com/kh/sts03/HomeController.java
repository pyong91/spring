package com.kh.sts03;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HomeController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 신규 생성할 페이지에서 해야할 일이 있다면 처리 수행
		// 처리가 끝나면 뷰에 전달하기 위해 request에 담아라(.setAttribute)
		// 뷰페이지 위치를 반환하면 자동으로 연동되서 출력
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home");
		
		return mv;
	}
	
}
