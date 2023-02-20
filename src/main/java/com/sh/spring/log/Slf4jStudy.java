package com.sh.spring.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class Slf4jStudy {
/**
 * Simple Logging Facade for java == PSA에 해당됨 (SPRING의 특징 중 - Portable Service abstracion  추상화 레이어 쌓는거 )
 * - slf4j를 통해서 구체화된 looging 의존을 제어함 
 * - 장점 : 레이어가 중간에 생겨서 나중에 수정할때 원본코드를 건드리지않음 
 * - 단점 : 
 * - 트레이스모드가 없다. 
 * - fatal레벨없음 
 * @param args
 */

	private static final Logger log = LoggerFactory.getLogger(Slf4jStudy.class);
	public static void main(String[] args) {
//		log.fatal("message - fatal"); 없음 
		log.error("message - error");			
		log.warn("message - warn");
		log.info("message - info"); //이거는 log4j.xml 파일에서 설정한 레벨에따라 출력되는게 다름 
		log.debug("message - debug"); // <level value="debug" /> 이걸로 설정했을 때 여기까지 나옴
		log.trace("message - trace");

	}

}
