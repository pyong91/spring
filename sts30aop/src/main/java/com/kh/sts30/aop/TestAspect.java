package com.kh.sts30.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * AOP의 간섭 객체
 */

@Aspect
@Component
@Slf4j
public class TestAspect {
	
	/*
	 * 간섭 메소드
	 *  - 특수한 경우가 아니라면 메소드의 형태로 마음대로 정할 수 없다.
	 *  - 매개변수는 없다
	 *  - 반환형은 void다
	 */
	
	/*
	 * pointcut 정의 표현식의 종류
	 *  - within : 특정 패키지 or 클래스 전체   -> within("com.kh.sts30")
	 *  - target : 특정 클래스와 자식 클래스 전체(상속)
	 *  - args : 특정 매개변수 형태의 메소드를 지정
	 *  - this : 특정 인터페이스를 구현하는 프록시 객체를 지정
	 *  - execution(중요) : 표현식으로 형태를 특정
	 *  
	 *  표현식 작성 규칙
	 *   - 접근제한자 생략해도 public
	 *   - .*과 ..*을 구분할 수 있어야 한다.
	 *   - .*은 해당하는 패키지의 내용만 포함
	 *   - .** 해당하는 패키지와 하위 패키지의 모든 내용을 포함
	 *   
	 *  Execution 표현식 예제
	 *   - execution(접근제한 반환형 패키지.클래스.메소드(매개변수));
	 *   
	 *   - execution(public int aaa.bbb.Test.hello());
	 *      > 접근제한 : public
	 *      > 반환값 : int
	 *      > 패키지 : aaa.bbb
	 *      > 클래스 : Test
	 *      > 메소드 : hello
	 *   
	 *   - execution(public int aaa.bbb.Test.hello(*));
	 *   	> 매개변수 : 1개 이상
	 *   
	 *   - execution(public int aaa.bbb.Test.hello(..));
	 *      > 매개변수 : 0개 이상
	 *      
	 *   - execution(public int aaa.bbb.Test.hello(int));
	 *      > 매개변수 : 1개이며 int   
	 *      
	 *   - execution(public int aaa.bbb.Test.he*(int));   
	 *      > 메소드 : he로 시작
	 *      > 매개변수 : 1개이며 int  
	 *      
	 *   - execution(public * aaa.bbb.Test.*(..));   
	 *   	> 메소드 : 모두
	 *   	> 매개변수 : 1개 이상
	 *   
	 *   - execution(public * aaa.*.*.*(..));
	 *   	> 패키지 : aaa 패키지와 그 모든 하위 패키지
	 *   	> 클래스 : 모두
	 *    	> 메소드 : 모두
	 *    
	 *   - execution(* com.kh.sts30.controller.*(..))
	 *   - execution(* com.kh.sts30.service.*(..))
	 *   - execution(* com.kh.sts30.repository.*(..))
	 */
	
	
//	@Before(value = "target(com.kh.sts30.HomeController)")
	public void before() {
		log.info("befoe 메소드 실행");
	}
	
//	@After("target(com.kh.sts30.HomeController)")
	public void after() {
		log.info("after 메소드 실행");
	}
	
//	Around AOP
//	 - 전반적인 간섭 메소드
//	 - 메소드 형태가 강제로 지정되어 있음
	@Around("target(com.kh.sts30.HomeController)")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
		
//		대상(joinPoint)분석
		log.info("------[정보분석]------");
		log.info("클래스명 = {}", joinPoint.getTarget());
		log.info("메소드명 = {}", joinPoint.getSignature());
		log.info("메소드명(투숏) = {}", joinPoint.getSignature().toShortString());
		
//		매개변수 분석(변조도 가능)
		Object[] args = joinPoint.getArgs();
		log.info("매개변수 개수 = {}", args.length);
		for (Object object : args) {
			log.info("매개변수 데이터 = {}", object);
		}
		
//		원본(joinPoint)을 호출
//		before 와 같은 효과
		long start = System.currentTimeMillis();
		try {
			Object result = joinPoint.proceed();
//			after returning 와 같은 효과
			log.info("반환값 = {}", result);
			return result;
		}
		catch(Throwable t) {
//			after throwing 와 같은 효과	
			throw t;
		}
		finally {
//			after 와 같은 효과
			long finish = System.currentTimeMillis();
			log.info("소요시간 : {} ms", finish - start);
		}
		
	}
	
}



