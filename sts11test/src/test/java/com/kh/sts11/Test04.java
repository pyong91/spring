package com.kh.sts11;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.sts11.entity.Student;
import com.kh.sts11.repository.StudentDao;

// 연동할 수 있는 환경 만들어주기
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})	
@WebAppConfiguration
public class Test04 {
	
	@Autowired
	private StudentDao studentDao;
	
//	@Test
//	public void test() { 
//		Student s = new Student();
//		s.setName("홍길은"); 
//		s.setScore(99);
//		studentDao.regist(s);
//	}

//	@Test
//	public void listTest() {
//		List<Student> list = studentDao.getList();
//		
//		for (Student student : list) {
//			System.out.println(student);
//		}
//	}
	
	@Test
	public void searchTest() {
		List<Student> list = studentDao.search("홍길은");
		for (Student student : list) {
			System.out.println(student);
		}
		
	}
}
