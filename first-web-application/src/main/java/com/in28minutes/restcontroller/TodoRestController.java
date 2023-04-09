package com.in28minutes.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.todo.Todo;
import com.in28minutes.todo.TodoService;

@RestController
public class TodoRestController {
	
	@Autowired
	TodoService service;
	

	@RequestMapping(value = "/todoList", method = RequestMethod.GET)
	public List<Todo> listAllTodos()
	{
		return service.retrieveTodos("in28Minutes");
	}
	
	@RequestMapping(value = "/todoList/{id}", method = RequestMethod.GET)
	public Todo listTodo(@PathVariable int id)
	{
		return service.retrieveTodo(id);
	}

}
