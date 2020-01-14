package com.kh.sts11;

import org.junit.Test;

/*
 * JUnit과 Spring-Test 모듈을 이용한 단위 테스트
*/

public class Test01 {
	// 단위 기능 메소드 : public void 이름() 형태로 생성해야함
	@Test
	public void hello() {
		System.out.println("단위 테스트 수행!");
	}
	
	@Test
	public void hello2() {
		System.out.println("단위 테스트 수행!");
	}
}
