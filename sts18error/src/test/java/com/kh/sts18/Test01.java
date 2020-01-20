package com.kh.sts18;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})	
@WebAppConfiguration
public class Test01 {
	/*
	 * 로깅(Logging)
	 *  - 기록을 남기는 행위
	 *  - sysout과 같은 행위
	 *  - log4j, logback 등이 대표적
	 *  - 로그 확인을 위한 각종 도구들(시간, 날짜, 형식, ...) 지원
	 *  
	 *  로깅 수준(Logging Level)
	 *   - 메시지의 심각도를 6단계로 구분해놓음(라이브러리마다 상이함)
	 *   - FATAL : 심각
	 *   - ERROR : 오류
	 *   - WARN  : 경고
	 *   - INFO  : 정보
	 *   - DEBUG : 개발용 
	 *   - TRACE : 모든 정보 잡다한거까지
	 *   
	 *   로거 설정
	 *   - log4j.xml
	 */
	
	// 도구 생성 (import 주의!!!!!!!!!!!!!)
//	 [1] Logger logger = LoggerFactory.getLogger(Test01.class);
	Logger logger = LoggerFactory.getLogger(getClass()); // this.getClass()
	
	@Test
	public void test() {
		logger.debug("debug 테스트");
		logger.info("info 테스트");
		logger.warn("warn test");
		logger.error("error test");
		
		int a = 10;
		int b = 20;
		
		logger.debug("a = {}, b = {}",a,b);
	}
}
