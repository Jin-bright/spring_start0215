package com.sh.spring.log;

import org.apache.log4j.Logger;

/**
 * loggingFramework
 *  - 모드별 출력가능 
 *  - 개발-운영 변경 시,모드만 변경해서 성능저하문제해결 
 *  - log4j, logback, javt.util.logging, apache-commons-loggin 등등 
 *  - system.out.println()의 문제점  : 구분없이출력되어버려 서버 운영 효율을 떨어뜨림
 *  - 개발 - 운영 변경 시 출력코드 수정하다 처리되는코드도 주석되어 문제가 많이 발생 
 *  - slf4j 서비스계층역할을 할 의존을 중간에 끼워 스프링앱에서 사용

 *
 * 모드 
 * - fatal : 아주 심각한 오류 
 * - error : 프로그램 실행 시 오류가 발생하는경우 = exception 
 * - warn : 현재 실행에는 문제가 없지만, 이후 버전에서는 변경될수 있는 잠재적오류
 * - info : 정보성 메세지 
 * - debug : 개발,디버그 시 사용하는 로그 
 * - trace  : 디버그를 더 세분화 / 추적용으로 메소드의 시작과 끝 등을 체크
 */

public class Log4jStudy {
	
	private static final Logger log = Logger.getLogger(Log4jStudy.class);

	
	public static void main(String[] args) {
		log.fatal("message - fatal");
		log.error("message - error");			
		log.warn("message - warn");
		log.info("message - info"); //이거는 log4j.xml 파일에서 설정한 레벨에따라 출력되는게 다름 
		log.debug("message - debug"); // <level value="debug" /> 이걸로 설정했을 때 여기까지 나옴
		log.trace("message - trace");
	/** 출력값 
	    FATAL: com.sh.spring.log.Log4jStudy - message - fatal
		FATAL: com.sh.spring.log.Log4jStudy - message - error
		WARN : com.sh.spring.log.Log4jStudy - message - warn
		INFO : com.sh.spring.log.Log4jStudy - message - info
		debut, trace 안나오는 이유는 ?
	 * 
	 */
	}

}
