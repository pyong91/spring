package com.kh.sts15.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberProfileDto {
	private int profile_no;
	private String profile_uploadname;
	private int profile_size;
	private int member_no;
}
