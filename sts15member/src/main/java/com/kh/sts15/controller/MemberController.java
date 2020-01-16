package com.kh.sts15.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.sts15.dto.MemberDto;
import com.kh.sts15.service.RegistService;
import com.kh.sts15.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private RegistService registService;
	
	@GetMapping("/regist")
	public String regist() {
		return "regist";
	}
	
	
//	강사님코드ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
//	@PostMapping("/regist")
//	public String regist(@ModelAttribute MemberDto memberDto
//							@RequestParam MultipartFile member_profile
//							) throws IllegalStateException, IOException {
//		등록을 위한 정보 생성
//		- 회원과 파일 모두 등록하려면 "회원번호"가 필요함
//		- 등록에 앞서서 회원번호를 먼저 구해온다
//		- 회원과 파일 정보를 각각 설정한 뒤 추가
//		- 실제 파일을 저장
//		
//		int no = memberDao.getSequence();
//		회원등록
//		memberDto.setMember_no(member_no);
//		
//		파일등록
//		MemberProfileDto profileDto = MemberProfileDto.builder()........
//		memberProfileDao.regist(profileDto)
//	
//		물리저장
//		File directory = new File("D:/upload/filetest");
//		directory.mkdirs();
//		File file = new File(directory, String.valueOf(member_no));
//		member_profile.transferTo(file);
//		return "";
//	}
// ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ

	@PostMapping("/regist")
	public String regist(@ModelAttribute MemberVO memberVO) throws IllegalStateException, IOException {
		registService.store(memberVO);
		return "regist";
	}
}
