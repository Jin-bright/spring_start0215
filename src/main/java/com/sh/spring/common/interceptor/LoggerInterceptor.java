package com.sh.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j  //servletcontext에서 등록해야됨 
public class LoggerInterceptor extends HandlerInterceptorAdapter {
	/**
	 *  preHandle : 전처리 
	 *  핸들러 호출전에 실행하게됨 
	 */
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		//log.debug("================================================================");
		//log.debug( request.getRequestURI() + "  || " + request.getRequestURI());
		//log.debug("--------------------------------------");
		
		return super.preHandle(request, response, handler);
	}
	
	/**
	 *  postHandle : 후처리 
	 *  핸들러 메소드가 리턴 후 
	 * 
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		// controller에서 모델에 담은 내용을 여기서는 다 확인가능함 
		// 최종적으로 ModelAndView 여기담기기때문 
		
		//log.debug("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		log.debug(" ■ modelAndView : " + modelAndView );
		log.debug("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		
		super.postHandle(request, response, handler, modelAndView);
	}
	
	/**
	 *  afterCompletion  : view단 응답처리 후  
	 */
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		
		log.debug("ㅡㅡㅡㅡㅡㅡㅡㅡ 여기부터는 view ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
		super.afterCompletion(request, response, handler, ex);
		log.debug("================================  끝 ==============================");
		log.debug("");
	}
}
