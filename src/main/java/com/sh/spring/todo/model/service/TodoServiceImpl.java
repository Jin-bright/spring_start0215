package com.sh.spring.todo.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.spring.todo.model.dao.TodoDao;
import com.sh.spring.todo.model.dto.Todo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoDao todoDao;

	//모든todo 
	@Override
	public List<Todo> selectTodoList() {
		// TODO Auto-generated method stub
		log.debug("★ todo dao(서비스에서찍음) = {}", todoDao );
		return todoDao.selectTodoList();
	}

	//insert into todo values(seq_todo_no.nextval,?,default,default);
	@Override
	public int insertTodo(Todo todo) {
		// TODO Auto-generated method stub
		return todoDao.insertTodo( todo);
	}

	@Override
	public int updateTodo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return todoDao.updateTodo(param);
	}
		
	


}
