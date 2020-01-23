package com.kh.sts25.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatData {
	private int no;
	private String text;
	private int status; // 0(enter), 1(exit), 2(message)
}
