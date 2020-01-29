package com.kh.sts28.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kh.sts28.entity.PayDto;
import com.kh.sts28.repository.PayDao;
import com.kh.sts28.vo.KakaoPayReadyReturnVO;
import com.kh.sts28.vo.KakaoPayRevokeReturnVO;
import com.kh.sts28.vo.KakaoPaySuccessReadyVO;
import com.kh.sts28.vo.KakaoPaySuccessReturnVO;
import com.kh.sts28.vo.PayReadyReturnVO;
import com.kh.sts28.vo.PayReadyVO;

public class KakaoPayService implements PayService {

	@Autowired
	private PayDao payDao;
	
	@Override
	public PayReadyReturnVO ready(PayReadyVO vo) throws URISyntaxException {
//		 여기서의 vo는 KakaoPayReadyVO이고
//		 반환되는 데이터는 KakaoPayReadyReturnVO이다
//		 Test01의 코드를 가져와서 수정한다
		
//		요청 전송 및 응답 수신 도구
//		- 필요한 모든 정보를 설정한 뒤 전송(POST)
//		- 헤더 : Authorization 정보(admin 키 추가)
//		- 바디 : 결제와 관련된 정보(가맹점, 거래번호, 사용자번호, 상품명, ...)
		RestTemplate template = new RestTemplate();
		
//		헤더 생성
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK c8f85d09f1abf996726e921deb756ac3");
//		headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
		headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE+"; charset=utf-8");
		headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
		
//		바디 생성
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", "TC0ONETIME");//가맹점번호(개발자용 테스트값)
		body.add("partner_order_id", vo.getPartner_order_id());//주문번호(랜덤)
		body.add("partner_user_id", vo.getPartner_user_id());//사용자번호
		body.add("item_name", vo.getItem_name());//상품명
		body.add("quantity", String.valueOf(vo.getQuantity()));//상품수량
		body.add("total_amount", String.valueOf(vo.getTotal_amount()));//상품판매가
		body.add("vat_amount", String.valueOf(vo.getVat_amount()));//부가세액(생략가능)
		body.add("tax_free_amount", String.valueOf(vo.getTax_free_amount()));//비과세액
		
//		주소 생성
		String baseUrl = ServletUriComponentsBuilder
										.fromCurrentContextPath()
										.port(8080)
										.path("/pay/kakao/")
										.toUriString();
		body.add("approval_url", baseUrl + "success");
		body.add("fail_url", baseUrl + "fail");
		body.add("cancel_url", baseUrl + "cancel");
		
//		테스트용 코드
//		body.add("approval_url", "http://localhost:8080/sts28/success");
//		body.add("fail_url", "http://localhost:8080/sts28/fail");
//		body.add("cancel_url", "http://localhost:8080/sts28/cancel");
		
//		헤더+바디
		HttpEntity<MultiValueMap<String, String>> entity
											= new HttpEntity<>(body, headers);
		
//		요청주소 생성
		URI uri = new URI("https://kapi.kakao.com/v1/payment/ready");
		
//		전송 및 응답 저장(KakaoPayReadyReturnVO)
		KakaoPayReadyReturnVO returnVO = 
				template.postForObject(uri, entity, KakaoPayReadyReturnVO.class);
		
//		DB관련 등록 처리(카카오페이에 결제준비 요청을 전송한 이후)
		PayDto payDto = PayDto.builder()
								.cid("TC0ONETIME")
								.tid(returnVO.getTid())
								.partner_order_id(vo.getPartner_order_id())
								.partner_user_id(vo.getPartner_user_id())
								.process_time(returnVO.getCreated_at())
								.item_name(vo.getItem_name())
								.quantity(vo.getQuantity())
								.total_amount(vo.getTotal_amount())
							.build();
		
		payDao.insertReady(payDto);
		
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
		PayDto payDto = PayDto.builder()
								.aid(returnVO.getAid())
								.cid(returnVO.getCid())
								.tid(returnVO.getTid())
								.partner_order_id(returnVO.getPartner_order_id())
								.partner_user_id(returnVO.getPartner_user_id())
								.item_name(returnVO.getItem_name())
								.process_time(returnVO.getCreated_at())
								.quantity(returnVO.getQuantity())
								.total_amount(returnVO.getAmount().getTotal())
							.build();
		
		payDao.insertSuccess(payDto);
		
		
		return returnVO;
	}
	
	@Override
	public KakaoPayRevokeReturnVO revoke(int no) throws URISyntaxException {
		// template = uri + entity(header + body)
		RestTemplate template = new RestTemplate();
		PayDto payDto = payDao.get(no);
		
		HttpHeaders header = new HttpHeaders();
		header.add("Authorization", "KakaoAK c8f85d09f1abf996726e921deb756ac3");
		header.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		header.add("Accept", "application/json; charset=UTF-8");
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("cid", payDto.getCid());
		body.add("tid", payDto.getTid());
		body.add("cancel_amount", String.valueOf(payDto.getTotal_amount()));
		body.add("cancel_tax_free_amount", "0");
//		body.add("cancel_vat_amount", "");
		body.add("cancel_available_amount", String.valueOf(payDto.getTotal_amount()));
		
		HttpEntity<MultiValueMap<String,String>> entity = 
				new HttpEntity<>(body, header);
		
		URI uri = new URI("https://kapi.kakao.com/v1/payment/cancel");
		
		KakaoPayRevokeReturnVO returnVO =
				template.postForObject(uri, entity, KakaoPayRevokeReturnVO.class);
		
		// 취소 등록
		PayDto payDto2 = PayDto.builder()
								.aid(returnVO.getAid())
								.cid(returnVO.getCid())
								.tid(returnVO.getTid())
								.partner_order_id(returnVO.getPartner_order_id())
								.partner_user_id(returnVO.getPartner_user_id())
								.item_name(returnVO.getItem_name())
								.process_time(returnVO.getCanceled_at())
								.quantity(returnVO.getQuantity())
								.total_amount(-1 * returnVO.getCanceled_amount().getTotal())
							.build();
		
		payDao.insertRevoke(payDto2);
		
		return returnVO;
	}
	
}
