package com.kh.sts28;

import java.net.URISyntaxException;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.sts28.service.PayService;
import com.kh.sts28.vo.KakaoPayReadyReturnVO;
import com.kh.sts28.vo.KakaoPayReadyVO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})	
@WebAppConfiguration
@Slf4j
public class Test02 {
	
	@Autowired
	private PayService payService;
	
	// 스프링 환경을 연동해서 KakaoPayService의 ready가 정상 작동하는지 테스트
	@Test
	public void test() throws URISyntaxException {
		KakaoPayReadyVO kvo = KakaoPayReadyVO.builder()
										.partner_order_id(UUID.randomUUID().toString())
										.partner_user_id("1000")
										.item_name("아파트")
										.quantity(1)
										.total_amount(999000)
										.vat_amount(99900)
									.build();
		
		KakaoPayReadyReturnVO result = (KakaoPayReadyReturnVO) payService.ready(kvo);
		
		log.info("주소 = {}", result.getNext_redirect_pc_url());
	}
}
