package com.pluto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User extends AbstractEntity{	
	
	
	@Column(nullable=false, length=20, unique=true)
	@JsonProperty
	private	String userId;
	
	@JsonIgnore
	private String passWord;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
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
		
//	패스워드 값이 일치하는 지 확인하는 메소드
	public boolean matchPassword(String newPassword) {
		if	(newPassword == null) {
			return false;
		}
		
		return newPassword.equals(passWord);
				
	}
	
//	아이디값이 일치하는 지 확인하는 메소드
	public boolean matchId(Long newId) {
		if(newId == null) {
			return false;
		}		
		return newId.equals(getId());
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
		return "User [" + super.toString() + ", passWord=" + passWord + ", name=" + name + ", email=" + email + "]";
	}
	
	
	
}
