package com.kh.sts28.controller;

import java.net.URISyntaxException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.sts28.entity.PayDto;
import com.kh.sts28.repository.PayDao;
import com.kh.sts28.service.PayService;
import com.kh.sts28.vo.KakaoPayReadyReturnVO;
import com.kh.sts28.vo.KakaoPayReadyVO;
import com.kh.sts28.vo.KakaoPayRevokeReturnVO;
import com.kh.sts28.vo.KakaoPaySuccessReadyVO;
import com.kh.sts28.vo.KakaoPaySuccessReturnVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/pay/kakao")
public class KakaoPayController {
	
	@Autowired
	private PayService payService;
	
	@Autowired
	private PayDao payDao;
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", payDao.getList());
		return "pay/list";
	}
	
	// 결제 준비(요청)
	@GetMapping("/confirm")
	public String confirm() {
		return "pay/confirm";
	}
	
	@PostMapping("/confirm")
	public String confirm(@ModelAttribute KakaoPayReadyVO vo,
			HttpSession session) throws URISyntaxException {
		KakaoPayReadyReturnVO result = 
				(KakaoPayReadyReturnVO) payService.ready(vo);
		// DB에 필요한 내역을 저장하고 / success에서 쓸 수 있도록 값을 세션에 추가
		session.setAttribute("tid", result.getTid()); // trade id를 전달
		session.setAttribute("ready", vo); // 결제 요청정보 전달
		return "redirect:"+result.getNext_redirect_pc_url();
	}
	
	//성공, 실패, 취소 페이지에 대한 처리
	@GetMapping("/success")
	public String success(@RequestParam String pg_token,
			HttpSession session, Model model) throws URISyntaxException {
		// 세션에서 전달된 값을 꺼내고 세션의 값을 삭제
		String tid = (String)session.getAttribute("tid");
		KakaoPayReadyVO vo = (KakaoPayReadyVO)session.getAttribute("ready");
		
		session.removeAttribute("tid");
		session.removeAttribute("ready");
		
		log.info("tid = {}", tid);
		log.info("vo = {}", vo);
		
		log.info("pg_token = {}", pg_token);
		
		//payService를 이용하여 결제 승인 처리를 수행
		// - KakaoPaySuccessReadyVO 형태의 매개변수가 필요
		// - KakaoPaySuccessReturnVO 형태의 반환값 저장이 필요
		KakaoPaySuccessReadyVO data = KakaoPaySuccessReadyVO.builder()
										.cid("TC0ONETIME")
										.tid(tid)
										.partner_order_id(vo.getPartner_order_id())
										.partner_user_id(vo.getPartner_user_id())
										.pg_token(pg_token)
										.build();
		
		KakaoPaySuccessReturnVO result = payService.approve(data);
		
		// 사용자에게 결제정보를 보여주기 위해 모델에 저장
		model.addAttribute("result", result);
		
		
		
		return "pay/success";
	}
	
//	@GetMapping("/fail")
//	@GetMapping("/cancel")
	
	// 결제 후 취소
	@GetMapping("/revoke")
	public String revoke(@RequestParam int no) throws URISyntaxException {
		KakaoPayRevokeReturnVO vo = payService.revoke(no);
		
		return "redirect:list";
	}
	
	
	
}
