package com.kh.sts28.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kh.sts28.vo.KakaoPayReadyReturnVO;
import com.kh.sts28.vo.KakaoPayReadyVO;
import com.kh.sts28.vo.KakaoPaySuccessReadyVO;
import com.kh.sts28.vo.KakaoPaySuccessReturnVO;
import com.kh.sts28.vo.PayReadyReturnVO;
import com.kh.sts28.vo.PayReadyVO;

public class KakaoPayService implements PayService {

	@Override
	public PayReadyReturnVO ready(PayReadyVO vo) throws URISyntaxException {
		// vo는 KakaoPayReadyVO
		// 리턴값은 KakaoPayReadyReturnVO
		// Test01의 코드를 가져와서 수정

		KakaoPayReadyVO obj = (KakaoPayReadyVO)vo;
		
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
		body.add("partner_order_id", obj.getPartner_order_id()); // 주문번호(테스트라 편의상 랜덤으로 생성해봄)
		body.add("partner_user_id", obj.getPartner_user_id()); // 사용자 번호(사용자 시퀀스 보내면 )
		body.add("item_name", obj.getItem_name()); // 상품명
		body.add("quantity", String.valueOf(obj.getQuantity())); // 상품 수량
		body.add("total_amount", String.valueOf(obj.getTotal_amount())); // 판매가
		body.add("vat_amount", String.valueOf(obj.getVat_amount())); // 부가세(생략가능)
		body.add("tax_free_amount", String.valueOf(obj.getTax_free_amount())); // 비과세
		
		// 주소 생성
		String baseUrl = ServletUriComponentsBuilder
							.fromCurrentContextPath()
							.port(8080)
							.path("/pay/kakao/")
							.toUriString();
		body.add("approval_url", baseUrl + "success");
		body.add("fail_url", baseUrl + "fail");
		body.add("cancel_url", baseUrl + "cancel");
	
		//테스트용 코드
//		body.add("approval_url", "http://localhost:8080/sts28/success"); // 성공주소
//		body.add("fail_url", "http://localhost:8080/sts28/fail"); // 실패주소
//		body.add("cancel_url", "http://localhost:8080/sts28/cancel"); // 취소주소

		// 헤더 + 바디 : HttpEntity
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(body, headers);

		// 요청주소 생성
		URI uri = new URI("https://kapi.kakao.com/v1/payment/ready");

		// 전송, 저장
		KakaoPayReadyReturnVO returnVO = template.postForObject(uri, entity, KakaoPayReadyReturnVO.class);
		return returnVO;
	}
	
	@Override
	public KakaoPaySuccessReturnVO approve(KakaoPaySuccessReadyVO vo) throws URISyntaxException {
		RestTemplate template = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK c8f85d09f1abf996726e921deb756ac3");
		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", vo.getCid());
		body.add("tid", vo.getTid());
		body.add("partner_order_id", vo.getPartner_order_id());
		body.add("partner_user_id", vo.getPartner_user_id());
		body.add("pg_token", vo.getPg_token());
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(body, headers);
		URI uri = new URI("https://kapi.kakao.com/v1/payment/approve");
		KakaoPaySuccessReturnVO returnVO = template.postForObject(uri, entity, KakaoPaySuccessReturnVO.class);
		
		// DB 승인 완료 처리 위치
		
		
		return returnVO;
	}
}
