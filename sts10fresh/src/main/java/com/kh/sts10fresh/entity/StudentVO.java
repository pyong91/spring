package com.kh.sts10fresh.entity;

/*
 * DTO / VO
 * - DTO는 DB의 1row (DB에 없는 항목을 추가하면 안됨)
 * - VO는 더 광범위하게 필요할 때 쓰는 객체(Value Object)
 */
public class StudentVO {
	private String name;
	private int korean;
	private int english;
	
	@Override
	public String toString() {
		return "StudentVO [name=" + name + ", korean=" + korean + ", english=" + english + "]";
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getKorean() {
		return korean;
	}
	
	public void setKorean(int korean) {
		this.korean = korean;
	}
	
	public int getEnglish() {
		return english;
	}
	
	public void setEnglish(int english) {
		this.english = english;
	}
	
	
}
