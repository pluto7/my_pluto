package com.pluto.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	@PostMapping("/create")
	public	String create(User user)	{
		System.out.println("user = " + user);
		return "index";
	}
}