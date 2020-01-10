package com.kh.sts05;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class DiceController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 해야할 일 : 주사위 값 한개를 생성해서 뷰에 전달
		Random r = new Random();
		int dice = r.nextInt(6) + 1;
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("dice");
		mv.addObject("dice", dice);
		
		return mv;
	}
	
	
}
