package com.kh.sts08;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.kh.sts08.entity.MemberDto;
import com.kh.sts08.repository.MemberDao;

public class TestController implements Controller{
	// 이 클래스에 MemberDao가 들어올 수 있는 자리를 만들 계획
	// [1] memberDao
	// [2] memberDaoImpl
	
	private MemberDao memberDao;
	
	public void setMemberDaoImpl(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(request.getMethod().equalsIgnoreCase("get")) {
			ModelAndView mv = new ModelAndView();
			mv.setViewName("test");
			return mv;
		}
		else if(request.getMethod().equalsIgnoreCase("post")) {
			// 데이터 받고, 등록, 어딘가로 redirect
			request.setCharacterEncoding("UTF-8");
			MemberDto memberDto = new MemberDto();
			memberDto.setId(request.getParameter("id"));
			memberDto.setPw(request.getParameter("pw"));
			memberDto.setName(request.getParameter("name"));
			memberDto.setPhone(request.getParameter("phone"));
			
			memberDao.regist(memberDto);
			
			ModelAndView mv = new ModelAndView();
			mv.setViewName("redirect:/");
			return mv;
		}
		return null;
	}
}
