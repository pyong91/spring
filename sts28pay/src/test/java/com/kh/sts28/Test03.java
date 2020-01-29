package com.kh.sts28;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.sts28.entity.PayDto;

import lombok.extern.slf4j.Slf4j;

// SqlSession을 이용하여 ready 구문을 테스트
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})	
@WebAppConfiguration
@Slf4j
public class Test03 {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void ready() {
		PayDto payDto = PayDto.builder()
							.aid("A1243561423561234442")
							.tid("T2721326618155616844")
							.cid("TC0ONETIME")
							.partner_order_id("1234")
							.partner_user_id("1000")
							.process_time("2020-01-29T10:17:08")
							.item_name("강남아파트")
							.quantity(1)
							.total_amount(999000)
						.build();
		
		log.info("payDto = {}", payDto);
		
		sqlSession.insert("pay.ready", payDto);
	}
}
