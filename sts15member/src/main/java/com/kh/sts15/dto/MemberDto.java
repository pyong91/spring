package com.kh.sts15.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
	private int member_no;
	private String member_id;
	private String member_pw;
	private String member_nick;
}
