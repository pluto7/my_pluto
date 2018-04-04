package com.pluto.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";					
		}
		
		User	sessionedUser	=	HttpSessionUtils.getUserFromSession(session);
		
		if (!sessionedUser.matchId(id)) {
			throw new IllegalStateException("You can't update the anther user");
		}
		System.out.println("이건??");
		User user	=	userRepository.findOne( id );
		System.out.println(user);
		model.addAttribute("user", user);
		System.out.println("저건??");
		return "/user/updateForm";		
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, User updateUser, HttpSession session) {
		if(HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";					
		}
		
		User	sessionedUser	=	HttpSessionUtils.getUserFromSession(session);
		
		if (sessionedUser.matchId(id)) {
			throw new IllegalStateException("You can't update the anther user");
		}
		
		User user	=	userRepository.findOne(id);
		user.update(updateUser);
		userRepository.save(user);
		return	"redirect:/users";
	}
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		System.out.println("userId = " + userId + " password = "+ password);
		User user	=	userRepository.findByUserId(userId);
		if(user == null) {
			System.out.println("user_ID");
			return "redirect:/users/loginForm";
		}
		
		if (!user.matchPassword(password)) {
			System.out.println("password");
			return "redirect:/users/loginForm";
		}
		
		System.out.println("Login Success");
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
		
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
		return "redirect:/";
	}
}
