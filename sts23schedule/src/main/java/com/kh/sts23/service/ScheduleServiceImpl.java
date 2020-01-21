package com.kh.sts23.service;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService{
	
	/*
	 * 크론 표현식
	 * 형식 : 초 분 시 일 월 요일
	 * 사용되는 기호 
	 *   * : 매번 
	 *   / : 주기(회차)
	 *   - : 범위
	 *   
	 *   
	 * 요일
	 *  월 : 1
	 *  화 : 2
	 *  
	 */
	
	
	@Override
	@Scheduled(fixedRate = 1000)//1초에 한번
	public void work() {
//		log.info("work 메소드 실행");
//		log.info("시각 ={}", LocalDateTime.now());
	}
	
	@Override
	@Scheduled(cron = "* * * * * *")
	public void work2() {
//		log.info("work2 : 매 초마다 실행");
	}
	
	@Override
	@Scheduled(cron = "* /5 * * * * *")
	public void work3() {
//		log.info("work3 : 매 분마다 실행");
	}
	
	@Override
	@Scheduled(cron = "5-10 * * * * *")
	public void work4() {
//		log.info("work4 : 매 5초~10초 사이에만 실행");
	}
	
	@Override
	@Scheduled(cron = "0 0 * * * *")
	public void work5() {
//		log.info("work5 : 매 정각마다");
	}
	
	@Override
	@Scheduled(cron = "* * * ? * tue 4")
	public void work6() {
		log.info("work6 실행!");
	}
}
