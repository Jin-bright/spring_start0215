package com.sh.spring.demo.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sh.spring.demo.model.dto.Dev;
import com.sh.spring.demo.model.dto.Gender;
import com.sh.spring.demo.model.service.DemoService;
/**
 * /**
 * @Controller 클래스의 handler메소드가 가질수 있는 매개변수 타입
 * 
 * HttpServletRequest --- ok
 * HttpServletResponse --- ok
 * HttpSession --- ok 
 * 
 * java.util.Locale : 요청에 대한 Locale
 * InputStream/Reader : 요청에 대한 입력스트림
 * OutputStream/Writer : 응답에 대한 출력스트림. ServletOutputStream, PrintWriter
 * 
 * 사용자입력값처리
 * Command객체 : http요청 파라미터를 커맨드객체에 저장한 VO객체
 * CommandMap :  HandlerMethodArgumentResolver에 의해 처리된 사용자입력값을 가진 Map객체
 * @Valid : 커맨드객체 유효성 검사객체
 * Error, BindingResult : Command객체에 저장결과(Command객체 바로 다음위치시킬것.)
 * @PathVariable : 요청url중 일부를 매개변수로 취할 수 있다.
 * @RequestParam : 사용자입력값을 자바변수에 대입처리(필수여부 설정)
 * @RequestHeader : 헤더값
 * @CookieValue : 쿠키값
 * @RequestBody : http message body에 작성된 json을 vo객체로 변환처리
 * 
 * 뷰에 전달할 모델 데이터 설정
 * ModelAndView
 * ModelMap 
 * Model
 
 * @ModelAttribute : model속성에 대한 getter
 * @SessionAttribute : session속성에 대한 getter(required여부 선택가능)
 * @SessionAttributes : session에서 관리될 속성명을 class-level에 작성
 * SessionStatus: @SessionAttributes로 등록된 속성에 대하여 사용완료(complete)처리. 세션을 폐기하지 않고 재사용한다.
 * 
 * 기타
 * MultipartFile : 업로드파일 처리 인터페이스. CommonsMultipartFile
 * RedirectAttributes : DML처리후 요청주소 변경을 위한 redirect시 속성처리 지원
 */

@Controller
public class DemoController {
	@Autowired
	private DemoService demoService;
	
	// handler 메서드 : @controller 하위의 사용자처리 메소드 // 무조건 이 ano밑에 있어야되고 , @RequestMapping 이런애들이 다 handler임 
	
	// 1  controller는 어노테이션이 있어야됨 component는 부모임 - 이걸 스캔할대 component도 해준다는거임  
    // 2 의존주입 필드 방법으로 함  @Autowired   private DemoService demoService; 
	
	// ano쓸때 문자열을 써도 value= 가 생략된거임  <--유일하게 생략가능 (path="//")
	// 이 value는 path속성에 대한 별칭임 
	// method는 생략 시 모든 전송 방식에 대해 처리함 / 근데 만약 특정메소드 등록시에는 해당 전송방식만 처리하게됨 method = RequestMethod.GET 
	@RequestMapping(path="/demo/devForm.do", method = RequestMethod.GET)
	public String devForm() {
		return "demo/devForm";
	}
	
	
	@RequestMapping("/demo/dev1.do")
	public String dev1(HttpServletRequest request, HttpServletResponse response, HttpSession session) {

		// 1. 사용자입력값 처리 
		String name = request.getParameter("name");
		int career = Integer.parseInt(  request.getParameter("career")) ; 
		String email = request.getParameter("email");
		String _gender = request.getParameter("gender");
		Gender gender = _gender != null ? Gender.valueOf(_gender) : null;
		
		
		
		String[] lang = request.getParameterValues("lang");
		
		Dev dev = new Dev(0,name,career,email, gender, lang, LocalDateTime.now());
		System.out.println( "■ dev : " + dev );
		
		//2
		 request.setAttribute("dev", dev);
		
		return "demo/devResult";
	}
	
}
