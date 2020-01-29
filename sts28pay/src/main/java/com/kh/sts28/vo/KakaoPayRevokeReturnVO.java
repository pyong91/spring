package com.kh.sts28.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 카카오페이 결제 취소시 반환되는 값을 저장할 VO
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KakaoPayRevokeReturnVO {
	private String aid;
	private String tid;
	private String cid;
	private String status;
	private String partner_order_id;
	private String partner_user_id;
	private String payment_method_type;
	private KakaoPayAmountVO amount;
	private KakaoPayAmountVO canceled_amount;
	private KakaoPayAmountVO cancel_available_amount;
	private String item_name;
	private String item_code;
	private int quantity;
	private String created_at;
	private String approved_at;
	private String canceled_at;
	private String payload;
	
}
