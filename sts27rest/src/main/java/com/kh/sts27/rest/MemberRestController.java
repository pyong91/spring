package com.kh.sts27.rest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @Controller + @ResponseBody 합친거
@RequestMapping("/rest") //거는거 필수(추천추천)
public class MemberRestController {

	@Autowired
	private SqlSession sqlSession;
	
	@GetMapping("/check")
	public String check(@RequestBody String id) {
		// 검사를 수행
		int count = sqlSession.selectOne("member.check", id);
		if(count>0) {
			return "Y";
		}
		else return "N";
	}
	
}
