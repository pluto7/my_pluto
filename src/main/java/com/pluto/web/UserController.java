package com.pluto.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pluto.domain.User;
import com.pluto.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("")
	public	String create(User user)	{
		System.out.println("user = " + user); 
		userRepository.save(user);
		return "redirect:/users";
	}
	
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";				
	}
	
	@GetMapping("/form")
	public String form(Model model) {
		System.out.println("form = " + "이건 뭐다냐.");
		return "/user/form";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model) {
		System.out.println("이건??");
		User user	=	userRepository.getOne( id );
		System.out.println(user);
		model.addAttribute("user", user);
		System.out.println("저건??");
		return "/user/updateForm";		
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User newUser) {
		User user	=	userRepository.getOne(id);
		user.update(newUser);
		userRepository.save(user);
		return	"redirect:/users";
	}
	
}
