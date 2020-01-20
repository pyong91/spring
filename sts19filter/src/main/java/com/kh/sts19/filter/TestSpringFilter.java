package com.kh.sts19.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

// 이 필터는 스프링에서 이용할 수 있도록 bean 형태로 생성
@Slf4j // 로거생성
@Service("testFilter")
public class TestSpringFilter implements Filter{
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("테스트 스프링 필터");
		chain.doFilter(request, response);
		
	}
}
