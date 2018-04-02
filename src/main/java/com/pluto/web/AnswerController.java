package com.pluto.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pluto.domain.Answer;
import com.pluto.domain.AnwserRepository;
import com.pluto.domain.Question;
import com.pluto.domain.QuestionRepository;
import com.pluto.domain.User;

@Controller
@RequestMapping("//questions/{questionId}/answers")
public class AnswerController {
	@Autowired
	private AnwserRepository	answerRepository;
	
	@Autowired
	private QuestionRepository	qustionRepository;
	
	@PostMapping("")
	public String create(@PathVariable Long questionId, String contents, HttpSession session) {
		System.out.println("답변달기까지는 오나??");
		
		if(!HttpSessionUtils.isLoginUser(session)) {		
			return "/users/loginForm";
		}
		
		User loginUser	=	HttpSessionUtils.getUserFromSession(session);
		Question	question	=	qustionRepository.getOne(questionId); 
		Answer answer	=	new Answer(loginUser, question, contents);
		answerRepository.save(answer);
		return String.format("redirect:/questions/%d", questionId);
		
		
	}
}
