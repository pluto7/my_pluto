package com.pluto.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private	Long id;
	
	private String writer;
	
	private String title;
	
	private String contents;
	
//	기본생성자를 꼭 만들어줘야한다.
	public Question() { }
	
	public Question(String writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}
	
	
}
