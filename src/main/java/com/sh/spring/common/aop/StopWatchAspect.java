package com.sh.spring.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class StopWatchAspect {
	
	@Pointcut("execution(* com.sh.spring.todo.controller.TodoController.insertTodo(..))")
	public void check() {
		//execution 이렇게 쓰는거맞는지 확인하기 
	}
	
	@Around("check()")
    public Object StopWatch(ProceedingJoinPoint joinPoint) throws Throwable {
		
		StopWatch sw = new StopWatch(getClass().getSimpleName());
		try {
			// start stopwatch
			log.debug( "[■ start stopwatch ]");
			sw.start(joinPoint.getSignature().getName());			
			return joinPoint.proceed();
		}finally {
		    // stop stopwatch
			sw.stop();
		    log.debug("[■ stop stopwatch ] =  {}", sw.prettyPrint());	
		   
		}
		
		
/** 출력결과가 ns가 나오는게 맞는걸까 .. ? 
  <	출력결과 >
	ns (나노 세컨): 십억 분의 1초. RAM의 access속도 측정 
	---------------------------------------------
	ns         %     Task name
	---------------------------------------------
	025330100  100%  insertTodo
**/
		
    }

}
