package com.kh.sts06;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.kh.sts06.beans.MemberDao;
import com.kh.sts06.beans.MemberDto;


public class ListController implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 의존성 주입이 이루어지도록 변수만 설정
//		private MemberDao memberDao; // 설정한 이름이랑 같아야함
//		
//		public void setMemberDao(MemberDao memberDao) {
//			this.memberDao = memberDao;
//		}
		
		// DB에 접근하여 목록을 가져오도록 처리
		
//		List<MemberDto> list = memberDao.search("id", null);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("list"); // WEB-INF/views/list.jsp
//		mv.addObject("list", list);
		
		return mv;
	}
}
