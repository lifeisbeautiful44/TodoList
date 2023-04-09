package com.in28minutes.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/*Like in servlet we need to extend httpServlet, in spring we can use
@Controller annotation to handle the request from the web browser.
*/

@Controller
@SessionAttributes("name")
public class TodoController {

	
	@Autowired
	TodoService service;
	
	
	
	
	@InitBinder
	protected void initBinder(WebDataBinder binder)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String listTodos(ModelMap model) {

		model.addAttribute("todos", service.retrieveTodos(retrivedLoggedInUser()));

		return "list-todos";
	}
	
	private String retrivedLoggedInUser()
	{
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showTodoPage(ModelMap model) {
	
		
		model.addAttribute("todo", new Todo());

		return "todo";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model,@ModelAttribute("todo")  @Valid Todo todo, BindingResult result) {
		
		if (result.hasErrors())
		{
			return "todo";
		}
		
		service.addTodo(retrivedLoggedInUser(), todo.getDesc(), new Date(), false);
		model.clear();// to prevent request parameter "name" to be passed
		return "redirect:/list-todos";
		
	}
	
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String updateTodo(Model model, @RequestParam int id) {
	             
		    Todo todo =  service.retrieveTodo(id);
		   
		model.addAttribute("todo", todo);
		return "todo";
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo( @Valid Todo todo, BindingResult result) {
		
		if (result.hasErrors())
		{
			return "todo";
		}
		
		todo.setUser(retrivedLoggedInUser());
		service.updateTodo(todo);
		
		
		return "redirect:/list-todos";
		
	}
	
	
	
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(ModelMap model,@RequestParam int  id) {
		
		service.deleteTodo(id);
		model.clear();
		return "redirect:list-todos";
		
	}

}
