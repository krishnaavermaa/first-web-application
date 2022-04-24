package com.in28minutes.todo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class TodoController {
	
	@Autowired
	TodoService service;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	@RequestMapping(value = "/list-todo", method=RequestMethod.GET)
	public String listTodos(ModelMap mmap) {
		mmap.clear();
		mmap.addAttribute("todos",service.retrieveTodos(retrievedLoggedinUserName()));
		//mmap.put("name", name);
		return "list-todo";
	}

	private String retrievedLoggedinUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

	@RequestMapping(value = "/add-todo", method=RequestMethod.GET)
	public String showTodoPage(ModelMap mmap) {
		
		//mmap.put("name", name);
		
		//passing todo to todo.jsp as attribute whose value is some
		//default implementation of a Todo class's object
		mmap.clear();
		mmap.addAttribute("todo",new Todo(0,retrievedLoggedinUserName(),"default description",new Date(),false));
		return "todo";
	} 
	
//	@RequestMapping(value = "/add-todo", method=RequestMethod.POST)

//	public String addTodo(@RequestParam(value="desc", defaultValue = "nothing") String desc) {
//		
//		//mmap.put("name", name);
//		service.addTodo("in28Minutes", desc, new Date(), false);
//		//return "list-todo"; instead of this use:
//		return "redirect:list-todo";
//	}
	//NEW WAY:
	@RequestMapping(value = "/add-todo", method=RequestMethod.POST)
	public String addTodo(ModelMap mmap,@Valid Todo todo,BindingResult res)
	{
		if(res.hasErrors()) return "todo";
		service.addTodo(retrievedLoggedinUserName(), todo.getDesc(), todo.getTargetDate(), false);
		mmap.clear();
		return "redirect:list-todo";
	}
	
	
	@RequestMapping(value="/delete-todo",method=RequestMethod.GET)
    public String deleteTodo(ModelMap mmap,@RequestParam int id) {
		
		//mmap.put("name", name);
		service.deleteTodo(id);
		//return "list-todo"; instead of this use:
		mmap.clear();
		return "redirect:list-todo";
	}
	
	@RequestMapping(value="/update-todo",method=RequestMethod.GET)
    public String updateTodo(ModelMap mmap,@RequestParam int id) {
		
		//mmap.put("name", name);
		Todo todo=service.retrieveTodo(id);
		mmap.clear();
		mmap.addAttribute(todo);
		//return "list-todo"; instead of this use:
		return "todo";
	}
	
	@RequestMapping(value="/update-todo",method=RequestMethod.POST)
    public String updateTodo(ModelMap mmap,@Valid Todo todo,BindingResult res) {
		
		if(res.hasErrors()) return "todo";
		todo.setUser((String)mmap.get("name"));
		service.updateTodo(todo);
		//return "list-todo"; instead of this use:
		mmap.clear();
		return "redirect:list-todo";
	}
}