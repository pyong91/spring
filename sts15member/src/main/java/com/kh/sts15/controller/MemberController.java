package com.kh.sts15.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.sts15.dao.MemberProfileDao;
import com.kh.sts15.dao.PhysicalFileDao;
import com.kh.sts15.dto.MemberProfileDto;
import com.kh.sts15.service.RegistService;
import com.kh.sts15.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired
	private RegistService registService;
	
	@Autowired
	private MemberProfileDao memberProfileDao;
	
	@Autowired
	private PhysicalFileDao pyhsicalFileDao;
	
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
	
	// 다운로드를 위한 주소를 추가
	// - /download1 : 과거에 JSP/Servlet에서 했던 방식
	// - /download2 : Spring에서 추구하는 방식
	@GetMapping("/download1")
	public void download1(
			HttpServletResponse response,
			@RequestParam int profile_no
		) {
		
//		파일정보(DB)를 불러온다 : memberProfileDao
		MemberProfileDto memberProfileDto = memberProfileDao.get(profile_no);
		
//		실제 파일을 불러온다 : physicalFileDao
//		byte[] data = physicalFileDao.get(번호(파일이름));
		byte[] data = pyhsicalFileDao.get(memberProfileDto.getProfile_no());

		
//		헤더 설정
//		response.setHeader("헤더이름", "헤더값");
//		response.setHeader("Content-Type", "application/octet-stream; charset_UTF-8");
//		response.setHeader("Content-Disposition", "attachment; filename=\""+
//				URLEncoder.encode(memberProfileDto.getProfile_uploadname(), "UTF-8")+"\"");
//		response.setHeader("Content-Length", String.valueOf(memberProfileDto.getProfile_size()));
//		
		
//		다운로드 코드 / 파일데이터:byte[]
//		response.getOutputStream().write(파일데이터);
//		response.getOutputStream().write(data);
		
	}
	
	
//	@GetMapping("/download2")
//	public ResponseEntity<ByteArrayResource> download(@RequestParam int profile_no){
		// ResponseEntity : 스프링에서 응답해줄 데이터가 담긴 상자
		// ByteArrayResource : 스프링에서 관리할 수 있는 Byte 형식의 데이터셋
		
//		실제 파일을 불러온다 : physicalFileDao
//		byte[] data = physicalFileDao.get(번호(파일이름));
//		byte[] data = pyhsicalFileDao.get(memberProfileDto.getProfile_no());
		
//		헤더설정 및 전송은 스프링의 방식으로 진행
//		ByteArrayResource resource = new ByteArrayResource(data);
//		return = ResponseEntity.ok()
//					.contentType(MediaType.APPLICATION_OCTET_STREAM)
//					.contentLength(memberProfileDto.getProfile_size())
//					// makeDispositionString(filename)
//					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+
//					URLEncoder.encode(memberProfileDto.getProfile_uploadname(), "UTF-8")+"\"")
//					.header("Content-Length", String.valueOf(memberProfileDto.getProfile_size()))
//					.body(resource);
//	}
	
//	내부 기능
//	private String makeDispositionString(String filename) throws UnsupportedEncodingException {
//		StringBuffer buffer = new StringBuffer();
//		buffer.append("attachment;");
//		buffer.append("filename=");
//		buffer.append("\"");
//		buffer.append(URLEncoder.encode(filename,"UTF-8"));
//		buffer.append("\"");
//	}
}
