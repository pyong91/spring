package com.kh.sts28.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KakaoPayAmountVO {
	private int total, 
				tax_free, 
				vat, 
				point, 
				discount;
}
