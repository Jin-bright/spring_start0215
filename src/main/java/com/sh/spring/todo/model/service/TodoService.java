package com.sh.spring.todo.model.service;

import java.util.List;
import java.util.Map;

import com.sh.spring.todo.model.dto.Todo;

public interface TodoService {

	//모든 todo리스트 보기 
	List<Todo> selectTodoList();

	
	//todo 할일추가 insert - dml
	int insertTodo(Todo todo);


	//
	int updateTodo(Map<String, Object> param);

}
