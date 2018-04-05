package com.pluto.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pluto.domain.Answer;
import com.pluto.domain.AnwserRepository;
import com.pluto.domain.Question;
import com.pluto.domain.QuestionRepository;
import com.pluto.domain.Result;
import com.pluto.domain.User;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class ApiAnswerController {
	@Autowired
	private AnwserRepository	answerRepository;
	
	@Autowired
	private QuestionRepository	qustionRepository;
	
	@PostMapping("")
	public Answer create(@PathVariable Long questionId, String contents, HttpSession session) {
		System.out.println("답변달기까지는 오나??");
		
		if(!HttpSessionUtils.isLoginUser(session)) {		
			return null;
		}
		
		User loginUser	=	HttpSessionUtils.getUserFromSession(session);
		Question	question	=	qustionRepository.findOne(questionId); 
		Answer answer	=	new Answer(loginUser, question, contents);
		question.addAnswer();
		return answerRepository.save(answer);
//		return String.format("redirect:/questions/%d", questionId);
		
		
	}
	
	@DeleteMapping("/{id}")
	public Result delete (@PathVariable Long questionId, @PathVariable Long id, HttpSession session) {
		System.out.println("aaaaaaaaa");
		
		if(!HttpSessionUtils.isLoginUser(session)) {		
			return Result.fail("로그인 후 가능합니다");
		}
		
		Answer answer	=	answerRepository.findOne(id);
		User loginUser	=	HttpSessionUtils.getUserFromSession(session);
		if(!answer.isSameWriter(loginUser)) {
			return Result.fail("자신의 글만 삭제가 가능합니다.");
		}
		
		answerRepository.delete(id);
		Question	question	=	qustionRepository.findOne(questionId); 
		question.deleteAnswer();
		qustionRepository.save(question);
		return Result.ok();
	}
}
