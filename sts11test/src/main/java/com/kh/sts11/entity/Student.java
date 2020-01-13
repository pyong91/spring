package com.kh.sts11.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // setter + getter + toString
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	private int no;
	private String name;
	private int score;
}
