package com.kh.sts19.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebFilter(urlPatterns = "/*")
public class TestJSPFilter implements Filter{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("TestFilter가 실행되었습니다.");
		
		chain.doFilter(request, response); // 통과
		
	}
	
}
