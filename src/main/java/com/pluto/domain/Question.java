package com.pluto.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Question {
	@Id
	@GeneratedValue
	private	Long id;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;
//	private String writer;
	
	private String title;
	
	private String contents;
	
	private LocalDateTime createDate;
	
//	기본생성자를 꼭 만들어줘야한다.
	public Question() { }
	
	public Question(User writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		this.createDate = LocalDateTime.now();
		
	}
	
	
	
	public Long getId() {
		return id;
	}

	public String getContents() {
		return contents;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public String getTitle() {
		return title;
	}
	
	public User getWriter() {
		return writer;
	}


	public String getFormattedCreateDate() {
		if(createDate == null) {
			return "";
		}
		return createDate.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
	}

	public void update(String title, String contents) {
		// TODO Auto-generated method stub
		this.title	=	title;
		this.contents	=	contents;
	}
	
}
