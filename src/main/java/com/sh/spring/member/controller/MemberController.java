package com.sh.spring.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sh.spring.member.model.dto.Member;
import com.sh.spring.member.model.service.MemberService;

import lombok.extern.slf4j.Slf4j;

/**
 *  model 이란 ? 
 *   - view 단에서 사용할 데이터를 임시로 보관하는 Map객체 
 *   - viewName에 대한 처리도 포함할수있음 
 *   1) ModelAndView - view단에 대한 정보를 같이가지고있음/ view객체 또는 viewName :String에 작성가능  
 *                   - addObject로 속성을 추가함 
 *   2) ModelMap - view단처리별도 =
 *               - addAttribute로 속성추가 
 *   3) Model - view단처리별도
 *            - addAttribute로 속성추가 
 * @ModelAttribute 또는 @SessionAttribute 어노테이션을 통해 저장된 속상  참조가능 
 *  **modelAndView 와 RedirectAttributes는 함께 사용하지말것 
 */
@Slf4j
@Controller
@RequestMapping("/member")
@SessionAttributes({"loginMember"}) // 해당이름으로 model에 저장된 애는 다 session scope에 저장 // member 컨트롤러는 servlet-context에있다 
public class MemberController {

	//private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	//root-cotext에 bcrypt 등록해서  servlet-context 하위에 있는 memberContreoller에서도 쓸수있다 
	// ## password=$2a$10$soKnMW9wpPGzniTqjdADUu0.hZreFCYU0ECe/Fv0LLc1UF5wvQbHu,
	//  - 알고리즘 : $2a$ 혹은 $2b$
	//  - 옵션 :  10$ 이 값이 올라가면 보다 복잡한 연산을 수행 
	//  - 랜덤솔트값 : 22자리
	//  - hash값 :31자리  ( 점은 구분자가 아님 ) 
	
	//@GetMapping("/memberEnroll.do")
	//public String memberEnroll() {
		
	//	int = memberService.memberEnroll();
	//	return "member/memberEnroll";
	//}
	
	//메소드 레벨의 @ModelAttribute는 사용자의 요청 시마다 호출됨 -> view단에 데이터 전달가능 
	// - 요청들간의 공용데이터 처리에 사용하면 좋다
	// - jsp에서 namespace. 속성명 형식으로 참조가능 
	@ModelAttribute("common")
	public ModelMap common(ModelMap model) {
		log.debug("membercontoller - common이 호출됨  : " + model );
		model.addAttribute("email","admin@naver.com");
		model.addAttribute("tel","070-123-1234");
		return model;
	}
	
	 @GetMapping("/memberEnroll.do")
	  public void memberEnroll() { //이렇게 해도 찾는다 }
	  
	  }
	 

	 @PostMapping("/memberEnroll.do")
	  public String memberEnroll(Member member, RedirectAttributes redirectAttr) { //이렇게 해도 찾는다 }
		 log.trace("memberEnroll 시작!"); //범위를 한정해서 체크한다! 
		 try {
			log.debug("■■ member : " + member );
			 
			 String rawPassword = member.getPassword(); //원문pw
			 log.debug( "■■ member(pw에주목) : " + member );
			 String encodedPassword = passwordEncoder.encode(rawPassword);
			 member.setPassword(encodedPassword); //다시비번으로 셋팅 
			 log.debug( "■■ member(pw에주목/이렇게중괄호는 두개까지가능 ) = {} ", member );
			 int result = memberService.memberEnroll(member);
		} catch (Exception e) {
			log.error("회원가입오류!", e); // 단축키 alt shift z 
			throw e; // ㅇㅖ외페이지로 넘기기 
		}
		 return "redirect:/";
	  }
	 
	 
	 
	 //로그인시작 - 0217
	 @GetMapping("/memberLogin.do")
	 public String memberLogin() {
		 return "member/memberLogin";
	 }
	 
	 @PostMapping("/memberLogin.do")
	 public String memberLogin(String memberId, String password, Model model, RedirectAttributes redirectAttr) {
		 log.debug("■■ member(아이디/비번) : " + memberId + " || " + password );
		// System.out.println("■■ 인코드 password : " +  passwordEncoder.encode(password)  );
		 

		 //0. 먼저 회원1명 조회
		 Member member = memberService.selectOneMember(memberId); 
		 System.out.println( "■■ member : " +  member  );
		 //1. 해당 회원 로그인성공한경우 
		 // password - 원래 pw  // member.getPassword() : 변경한pw (en처리된거) 
		 if(memberId != null && passwordEncoder.matches(password, member.getPassword())) {
			 // 로그인성공했으면 먼저 세션에 저장해줘야됨 
			 // model에 저장을 하면 request에 저장됨 그래서 sessionscope 에저장할려면 ?
			 model.addAttribute("loginMember",member);
			 
		 //2 로그인실패한경우 (아이디/비번불일치)
		 }else {
			 redirectAttr.addFlashAttribute("msg", "사용자 아이디 혹은 비밀번호가 일치하지않아요 xx");			 
		 }
		 
		 //인증검사 - id가 not null 
		 return "redirect:/";
	 }
	 
	 
	 //로그아웃
	 // 우리 세션에 멤버정보 저장할때 : @Session ~  + model 통해서 했음 , 
	 // 원래는 로그인할때는 invalidate를 했다면 이제는 sessionStatus객체를 통해 사용완료처리를해야한다 
	 // - 장점 : session객체를 폐기하지 않고 재사용할수있음 ! 
	 @GetMapping("/memberLogout.do")
	 public String MemberLogout(SessionStatus status ) {
		 if(! status.isComplete()) {
			 status.setComplete();	 
		 }
		 return "redirect:/";
	 }
	 
	 
	 // @실습문제 - 0217 - 내정보보기 및 수정하기 기능구현
	 //  멤버 detail 마이페이지 	
	 //  GET /member/memberDetail.do  : 세션 loginMember 사용할것
	 
	 @GetMapping("/memberDetail.do")
	 public void memberDetail(String memberId, Model model) {
		 
		 Member member = memberService.selectOneMember(memberId); 
		 System.out.println( "■■ 해당 member : " +  member  );
		 
		 if(memberId != null) { 
			 model.addAttribute("loginMember",member);
		 }

	 }
	 //----------------------------쌤방법 
/*
 * @ModelAttribute("loginMember") Member loginMember
 * 
 * @GetMapping("/memberDetail.do") 
 * public ModelAndView memberDetail( @ModelAttribute("loginMember") Member loginMember, ModelAndView mav) {
 *   log.debug("loginMember = {}", loginMember);
 *   mav.setViewName("member/memberDetail"); 
 *  return mav; 
 * }
 */
	 
	 
	 // POST /member/memberUpdate.do : 내정보보기페이지로 리다이렉트
	 @PostMapping("/memberUpdate.do")
	 public String memberUpdate(Member member, RedirectAttributes reAttr, Model model) {
		 try {
			 int result = memberService.updateMember(member);
			 System.out.println("■■ update성공 : " + result  );
			 model.addAttribute("loginMember",member);
			 reAttr.addFlashAttribute("msg", "회원정보 업데이트에 성공했습니다😊");
		 }catch (Exception e) {
			 log.error("회원 정보 수정 오류!", e); 
			 throw e; 
		 }
		 return "redirect:/member/memberDetail.do?"+member.getMemberId();
	 }
	 
		/**
		 * 핸들러 파라미터에 세션속성 loginMember를 요청하면, 
		 * 사용자입력필드에 한해 사용자입력값이 반영되어 있다.
		 * 
		 * @param member
		 * @param redirectAttr
		 * @return
		 
		@PostMapping("/memberUpdate.do")
		public String memberUpdate(
				@ModelAttribute("loginMember") Member member,
				RedirectAttributes redirectAttr){
			try { 
				log.debug("member = {}", member);
				
				// 1.비지니스로직 실행
				int result = memberService.updateMember(member);
				
				// 2. 사용자피드백
				redirectAttr.addFlashAttribute("msg", "회원 정보 수정 성공!");
			} catch(Exception e) {
				log.error("회원 정보 수정 실패", e);
				throw e;
			}
			
			return "redirect:/member/memberDetail.do";
		}
		**/
	 
}
