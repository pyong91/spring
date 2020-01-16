package com.kh.sts17;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.sts17.entity.MemoDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
public class Test01 {
	
	@Autowired
	private SqlSession sqlSession;
	
//	@Test
//	public void insert() {
//		MemoDto memoDto = MemoDto.builder().content("테스트").build();
//		sqlSession.insert("memo.insert", memoDto);
//	}
	
//	@Test
//	public void list() {
//		List<MemoDto> list = sqlSession.selectList("memo.getList");
//		System.out.println(list.size());
//	}
	
	@Test
	public void search() {
		Map<String, String> param = new HashMap<>();
		param.put("type", "content");
		param.put("keyword", "T");
		
		List<MemoDto> list = sqlSession.selectList("memo.search", param);
		System.out.println(list.size());
	}
	
}