package com.in28minutes.login;

import javax.security.auth.login.LoginContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {
	
//	@Autowired
//	LoginService service;
	@RequestMapping(value = "/", method=RequestMethod.GET)
	//@ResponseBody
	public String sayHello(ModelMap mmap) {
		mmap.put("name",SecurityContextHolder.getContext().getAuthentication().getName());
		return "Welcome";
	}
	
//	@RequestMapping(value = "/login", method=RequestMethod.POST)
//	public String handleLoginRequest(@RequestParam String name,
//			@RequestParam String password,ModelMap mmap) {
//		if(!service.validateUser(name, password))
//		{
//			mmap.put("errMsg","Invalid credentials! Please retry.");
//			return "Login";
//		}
//		
//		mmap.put("name", name);
//		mmap.put("password", password);
//		return "Welcome";
//	}
	
}
