package com.kh.sts20.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

/*
 * 인터셉터(Interceptor) 
 * - 요청을 가로채는 도구
 * - 총 3군데를 가로챌 수 있음
 * - preHandle() : DispatcherServlet 도착 전
 * 		return : true(속생), false(차단)
 * - postHandle : Controller 수행 후
 * - afterCompletion : View 생성 후
 */
@Slf4j
public class TestInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("TestInterceptor 실행");
		log.info("handler = {}", handler);
		
		// 매개변수 handler에 뭐가 들어있는지 보자
		if(handler instanceof HandlerMethod) {
			HandlerMethod h = (HandlerMethod)handler;
			log.info("method = {}", h.getMethod().getName());
			log.info("annotation = {}", h.getMethodAnnotation(RequestMapping.class));
		}
		//리다이렉트 후 리턴 false로 거부
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("postHandler");
		log.info("handler = {}", handler);
		log.info("model&view = {}", modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("afterCompletion");
		log.info("handler = {}", handler);
		log.info("exception = {}", ex); // view 작업 시 발생하는 예외를 확인할 수 있다.
	}
}
