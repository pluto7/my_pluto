package com.pluto.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Question extends AbstractEntity{
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	private User writer;
	
	private String title;
	
	@Lob
	private String contents;
	
	@JsonProperty
	private Integer countOfAnswer = 0;
	
	
	
	@OneToMany(mappedBy="question")
	@OrderBy("id ASC")
	private List<Answer> answers;
	
//	기본생성자를 꼭 만들어줘야한다.
	public Question() { }
	
	public Question(User writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
		
	}
	
	public String getContents() {
		return contents;
	}


	public String getTitle() {
		return title;
	}
	
	public User getWriter() {
		return writer;
	}
	

	public void update(String title, String contents) {
		// TODO Auto-generated method stub
		this.title	=	title;
		this.contents	=	contents;
	}

	public boolean isSameWrite(User longinUser) {
		// TODO Auto-generated method stub
		return this.writer.equals(longinUser);
	}

	public void addAnswer() {
		// TODO Auto-generated method stub
		this.countOfAnswer	+= 1;
		
	}

	public void deleteAnswer() {
		// TODO Auto-generated method stub
		this.countOfAnswer	-= 1;
		
	}

	
	
}
