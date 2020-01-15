package com.kh.sts13;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.sts13.entity.ProductDto;
import com.kh.sts13.repository.ProductDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@WebAppConfiguration
public class TestProduct {
	
	@Autowired
	private ProductDao productDao; 
	
//	@Test
//	public void insertTest() {
//		productDao.insert(ProductDto.builder()
//									.name("양파깡")
//									.price(1500)
//									.build());
//	}
	
	@Test
	public void searchTest() {
		List<ProductDto> list = productDao.search("감자깡");
		for (ProductDto productDto : list) {
			System.out.println(productDto);
		}
	}
	
}
