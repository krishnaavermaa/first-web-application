package com.in28minutes.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
//@SessionAttributes("name")
public class LogoutController {
	
//	@Autowired
//	LoginService service;
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	//@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		//terminate authentication
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		if(auth!=null) new SecurityContextLogoutHandler().logout(request, response, auth);
		request.getSession().invalidate();
		return "redirect:/";
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
