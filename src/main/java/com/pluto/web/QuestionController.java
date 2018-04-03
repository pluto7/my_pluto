package com.pluto.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pluto.domain.Question;
import com.pluto.domain.QuestionRepository;
import com.pluto.domain.User;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/form")
	public String form(HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {		
			return "/user/login";
		}
		return "/qna/form";
	}
	@PostMapping("")
	public String create(String title, String contents, HttpSession session) {
		if(!HttpSessionUtils.isLoginUser(session)) {		
			return "/user/login";
		}
		
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		Question newQuestion	=	new Question(sessionUser, title, contents);
		questionRepository.save(newQuestion);
		return "redirect:/";
	}
	
	@GetMapping("/{id}")
	public String show(@PathVariable Long id, Model model) {
		System.out.println("답변달기의 답이 넘어오는가??_시작");
		model.addAttribute("question", questionRepository.getOne(id));
		System.out.println("답변달기의 답이 넘어오는가??_");
		return "/qna/show";
	}
	
	@GetMapping("/{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		try {
			Question question = questionRepository.getOne(id);		
			hasPermission(session, question);
				model.addAttribute("question", question);				
				return "/qna/updateForm";
//			}
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "/user/login";
		}
//		
//		
//		if(!HttpSessionUtils.isLoginUser(session)) {		
//			return "/users/loginForm";
//		}
//		
//		User sessionedUser	=	HttpSessionUtils.getUserFromSession(session);
//		
//		Question question = questionRepository.getOne(id);
//		
//		if (!question.isSameWrite(sessionedUser)) {
//			return "/users/loginForm"; 
//		}
		
		
	}
	
	private boolean hasPermission(HttpSession session, Question question) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			throw new IllegalStateException("로그인이 필요합니다."); 
		}
		
		User loginUser	=	HttpSessionUtils.getUserFromSession(session);
		if (!question.isSameWrite(loginUser)) {
			throw new IllegalStateException("로그인 정보가 상이합니다."); 
		}
		
		return true;
	}
	
	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String title, String contents, Model model, HttpSession session) {
		
		try {
			Question question = questionRepository.getOne(id);		
			hasPermission(session, question);
//				model.addAttribute("question", question);				
				question.update(title, contents);
				questionRepository.save(question);
				return String.format("redirect:/questions/%d",id);
//			}
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "/user/login";
		}
//		
//		if(!HttpSessionUtils.isLoginUser(session)) {		
//			return "/users/loginForm";
//		}
//		
//		User sessionedUser	=	HttpSessionUtils.getUserFromSession(session);
//		
//		Question question = questionRepository.getOne(id);
//		
//		if (!question.isSameWrite(sessionedUser)) {
//			return "/users/loginForm"; 
//		}
		
		
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, HttpSession session, Model model) {
		
		try {
			Question question = questionRepository.getOne(id);		
			hasPermission(session, question);
				questionRepository.deleteById(id);
			return "redirect:/";
//			}
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "/user/login";
		}
//		
//		if(!HttpSessionUtils.isLoginUser(session)) {		
//			return "/users/loginForm";
//		}
//		
//		User sessionedUser	=	HttpSessionUtils.getUserFromSession(session);
//		
//		Question question = questionRepository.getOne(id);
//		
//		if (!question.isSameWrite(sessionedUser)) {
//			return "/users/loginForm"; 
//		}
//		
//		questionRepository.deleteById(id);
//		return "redirect:/";
		
	}
}
