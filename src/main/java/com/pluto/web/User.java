package com.pluto.web;

public class User {
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
	@Override
	public String toString() {
		return "User [userId=" + userId + ", passWord=" + passWord + ", name=" + name + ", email=" + email + "]";
	}
	
	
	
}
