package com.pluto.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	@GetMapping("")
	public String welcome() {
		System.out.println("index 잘 넘어오나?");
		return "index";
	}
}
