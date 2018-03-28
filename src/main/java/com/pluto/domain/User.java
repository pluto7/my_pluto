package com.pluto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {	
	@Id
	@GeneratedValue
	private Long	id;
	
	@Column(nullable=false, length=20, unique=true)
	private	String userId;
	
	private String passWord;
	private String name;
	private String email;
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public Long getId() {	
		return id;
	}
	
//	아이디값이 일치하는 지 확인하는 메소드
	public boolean matchId(Long newId) {
		if(newId == null) {
			return false;
		}		
		return newId.equals(id);
	}
	
	
	public String getUserId() {
		return userId;
	}
//	패스워드 값이 일치하는 지 확인하는 메소드
	public boolean matchPassword(String newPassword) {
		if	(newPassword == null) {
			return false;
		}
		
		return newPassword.equals(passWord);
				
	}
	
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	
//	회원가입 수정
	public void update(User newUser) {
		// TODO Auto-generated method stub
		this.passWord	=	newUser.passWord;
		this.name		=	newUser.name;
		this.email		=	newUser.email;
	}	
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", passWord=" + passWord + ", name=" + name + ", email=" + email + "]";
	}
	
	
	
	
	
	
}
