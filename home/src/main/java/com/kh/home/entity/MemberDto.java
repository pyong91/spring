package com.kh.home.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto {
	private String id, pw, name, joindate, grade, post,
			basic_addr, extra_addr, phone, last_login;
	private int point;
}
