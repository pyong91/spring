package com.kh.sts12.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // new 사용 안하고 만들 수 있도록 도와줌, 생성자 있어야함
public class MemoDto {
	private int no;
	private String content;
	private String when;
}
