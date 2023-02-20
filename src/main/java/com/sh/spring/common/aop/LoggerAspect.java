package com.sh.spring.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/** pointcut 
 * - 조인포인트 선정룰 
 * 
 */

/** Aspect 클래스 
 *  - 한쌍의 pointcut, advice를 관리
 *  어노테이션 필수 / 빈으로관리해야되서 component 어노도 필요  
 */

/** advice (=메소드) : 주업무로직에 부가적으로 수행할 로직 
 *  - 지정할 포인트컷에 따라 특정 조인포인트에서 실행됨 (pointcut = 표현식)
 *  - 실행조건에 따라 before, around, after-returning, after, after-throwing 구분 
 *  - 표현식 "execution(* com.sh.spring.todo..*(..))"
 *  - 모든 리턴타입 
 *   - com.sh.spring.todo패키지 하위 모든 클래스의 모든 메소드 
 *   - (..) 모든매개변수타입 
 */
@Slf4j
@Aspect
@Component
public class LoggerAspect {
	
	
	@Pointcut("execution(* com.sh.spring.todo..*(..))")
	public void pointcut() {}
	
	
	
	@Around("pointcut()")
	public Object logger(ProceedingJoinPoint joinPoint) throws Throwable {
		//얘가 advice임 
		
		// 조인포인트에 대한 정보 가져오기  조인포인트는 메소드 .. 
		Signature signature = joinPoint.getSignature();
		System.out.println(   "메소드시그니처말하는거임 -> " + signature );
		String type = signature.getDeclaringTypeName();
		String methodName = signature.getName();

		
		///////////////   before  //////////////////////
		log.debug( "[■ before]{}.{}",type,methodName);

		Object obj = joinPoint.proceed();
		
		/////////////////// after ///////////////
		log.debug("[■ after]{}.{}", type,methodName);
		

		return obj;
		
	}
}
