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
	
	@Column(nullable=false, length=20)
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
	public String getUserId() {
		return userId;
	}
	public String getPassWord() {
		return passWord;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", passWord=" + passWord + ", name=" + name + ", email=" + email + "]";
	}
	
	
	
}
