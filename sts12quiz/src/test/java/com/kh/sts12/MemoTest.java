package com.kh.sts12;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.sts12.entity.MemoDto;
import com.kh.sts12.repository.MemoDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
public class MemoTest {
	
	@Autowired
	private MemoDao memoDao;
	
	@Test
	public void insertTest() {
//		MemoDto memoDto = new MemoDto();
//		memoDto.setContent("Test_1");
//		memoDao.insert(memoDto);
		
//		@Builder 사용하기.
//		MemoDto memoDto = MemoDto.builder().content("테스트 방명록").build();
		memoDao.insert(MemoDto.builder().content("테스트 방명록").build());
	}
	
	@Test
	public void listTest() {
		List<MemoDto> list = memoDao.getList();
		for (MemoDto memoDto : list) {
			System.out.println(memoDto);
			System.out.println(memoDto.getNo());
			System.out.println(memoDto.getContent());
			System.out.println(memoDto.getWhen());
		}
		
	}
}
