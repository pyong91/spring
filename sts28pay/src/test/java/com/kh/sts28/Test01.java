package com.kh.sts28;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.kh.sts28.vo.KakaoPayReadyReturnVO;

import lombok.extern.slf4j.Slf4j;

//PG사(카카오페이)에 결제 준비를 요청
// - 문제 : 서버에서 다른 서버로 신호를 보내야 하는데... 어떻게?
@Slf4j
public class Test01 {

	@Test
	public void preparePay() throws URISyntaxException {
		log.info("결제요청시작!");
		
		// 요청 전송 및 응답 수신 도구
		// - 필요한 모든 정보를 설정한 뒤 전송(POST)
		// - 헤더 : Autorization 정보(admin 키 추가)
		// - 바디 : 결제와 관련된 정보(가맹점, 거래번호, 사용자번호, 상품명, ...)
		RestTemplate template = new RestTemplate();
		
		// 헤더
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK c8f85d09f1abf996726e921deb756ac3");
		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		
		// 바디
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", "TC0ONETIME"); // 가맹점번호(개발자용 테스트값 넣었음)
		body.add("partner_order_id", UUID.randomUUID().toString()); // 주문번호(테스트라 편의상 랜덤으로 생성해봄)
		body.add("partner_user_id", "1000");	// 사용자 번호(사용자 시퀀스 보내면 )
		body.add("item_name", "강남아파트");	// 상품명
		body.add("quantity", "1");	// 상품 수량
		body.add("total_amount", "999000");	// 판매가
		body.add("vat_amount", "99900"); 	// 부가세(생략가능)
		body.add("tax_free_amount", "0");	// 비과세
		body.add("approval_url", "http://localhost:8080/sts28/success");	// 성공주소
		body.add("fail_url", "http://localhost:8080/sts28/fail");		// 실패주소
		body.add("cancel_url", "http://localhost:8080/sts28/cancel");		// 취소주소
		
		// 헤더 + 바디 : HttpEntity
		HttpEntity<MultiValueMap<String, String>> entity = 
				new HttpEntity<MultiValueMap<String,String>>(body, headers);
		
		// 요청주소 생성
		URI uri = new URI("https://kapi.kakao.com/v1/payment/ready");
		
		// 전송, 저장
		KakaoPayReadyReturnVO vo = template.postForObject(uri, entity, KakaoPayReadyReturnVO.class);
		
		log.info("vo = {}", vo);
	}
	
}
