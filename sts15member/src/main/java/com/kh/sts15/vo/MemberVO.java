package com.kh.sts15.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberVO {
	private int member_no;
	private String member_id;
	private String member_pw;
	private String member_nick;
	private MultipartFile memberProfile;
}
