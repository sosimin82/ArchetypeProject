package com.op.template.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor extends HandlerInterceptorAdapter {
	/**
	 * Interceptor를 이용해 MDC에 user 정보(userNo)를 저장하는 메서드
	 * logback에서 log level을 조정하기 위함
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// log level을 조절하기 위한 값 설정
		String userNo = request.getParameter("userNo");
		MDC.put("userNo", userNo);
		
		return super.preHandle(request, response, handler);
	}
	
	/**
	 * logback에서 사용된 userNo를 삭제하는 메서드
	 * @throws Exception 
	 * 
	 * **/
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		// log level을 조절하기 위한 값 삭제
		MDC.remove("userNo");
		MDC.clear();
		
		super.postHandle(request, response, handler, modelAndView);
	}
}
