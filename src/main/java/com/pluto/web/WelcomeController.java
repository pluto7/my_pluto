package com.pluto.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pluto.domain.QuestionRepository;

@Controller
public class WelcomeController {
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/")
	public String welcome(Model model) {
		System.out.println("index 잘 넘어오나?");
		model.addAttribute("question", questionRepository.findAll());
		return "index";
	}
	
	
}
