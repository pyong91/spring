package com.kh.sts28.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KakaoPaySuccessReturnVO {
	private String aid, tid, cid, sid;
	private String partner_order_id, partner_user_id, payment_method_type;
	private String item_name, item_code;
	private int quantity;
	private String created_at, approved_at;
	private String payload;
	private KakaoPaySuccessAmountVO amount;
	private KakaoPaySuccessCardInfoVO card_info;
}
