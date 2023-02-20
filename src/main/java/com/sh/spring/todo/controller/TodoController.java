package com.sh.spring.todo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sh.spring.todo.model.dto.Todo;
import com.sh.spring.todo.model.service.TodoService;

import lombok.extern.slf4j.Slf4j;

/** aop의 작동원리 
 *  - di 받은 객체는 우리가 만드 클래스베이스 객체가 아닌 스프링이 의도한 proxy객체임 
 *  - 의존주입 받아야할 객체가 인터페이스 구현클래스인 경우/// jdk 동적 proxy에서 생성한 객체가 주입됨 [com.sun.proxy~]
 *  - 인터페이서 구현클래스가 아니라면  // cglib패키지에서 제공된 객체가 주입 
 *   proxy cglib 뭔차이지 ? 
 * @author brigh
 *
 */

@Slf4j
@RequestMapping("/todo")
@Controller
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	@GetMapping("/todoList.do")
	public String SelectTodoList(Model model) {
	
		//	log.debug("★ todoservice = {}", todoService ); //★ todoservice = com.sh.spring.todo.model.service.TodoServiceImpl@23929e28
			log.debug("★ todoservice(Getclass) = {}", todoService.getClass() ); // ★ todoservice(Getclass) = class com.sun.proxy.$Proxy44

			List<Todo> todoList =  todoService.selectTodoList();
			log.debug( "todoList : " +  todoList);
			model.addAttribute("todoList", todoList);
		//	throw new RuntimeException("임의 예외 test");
			return "todo/todoList";
	}
	
	
	@PostMapping("/insertTodo.do")
	public String insertTodo(Todo todo, RedirectAttributes reAttr) {
		log.debug( "todo : " +  todo);
		int result = todoService.insertTodo(todo);
		log.debug( "result : " +  result);
		
		reAttr.addFlashAttribute("msg", "할일을 성공적으로 등록했습니다");
		
		return "redirect:/todo/todoList.do";
	}
	
	@PostMapping("/updateTodo.do")
	public String updateTodo(@RequestParam int no, 
			                 @RequestParam boolean isCompleted, RedirectAttributes reAttr) {
// 체크되면 1 /안되면 0 
// update todo set  completed_at = sysdate where  no = ?
// update todo set  completed_at = null where   no = ?

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("no", no);
		param.put("isCompleted", isCompleted);
		
		log.debug( "param : " +  param);
		int result = todoService.updateTodo( param );
		log.debug( "업뎃result : " +  result);
		
//		reAttr.addFlashAttribute("msg", "할일을 성공적으로 수정했습니다");
		
		return "redirect:/todo/todoList.do";
	}

	
}
