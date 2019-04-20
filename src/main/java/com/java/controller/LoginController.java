package com.java.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.dto.User;
import com.java.service.LoginService;

@Controller
public class LoginController {
	@Autowired
	LoginService service;

	@PostMapping("/login.do")
	public String login(HttpServletRequest request, HttpServletResponse response, @RequestParam String username, @RequestParam String password) {
		User user = null;
		user = service.login(username, password);
		if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
			request.getSession().setAttribute("User", user);
			return "forward:/home";
		}else {
			try {
				response.getWriter().println("Invalid login");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		return null;
	}
	
	@GetMapping("/login.do")
	public String testGet() {
		return "login";
	}
}