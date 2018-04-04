package com.pluto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {	
	@Id
	@GeneratedValue
	@JsonProperty
	private Long	id;
	
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
	
	
//	public Long getId() {	
//		return id;
//	}
	
//	아이디값이 일치하는 지 확인하는 메소드
	public boolean matchId(Long newId) {
		if(newId == null) {
			return false;
		}		
		return newId.equals(id);
	}
	
	
//	public String getUserId() {
//		return userId;
//	}
//	패스워드 값이 일치하는 지 확인하는 메소드
	public boolean matchPassword(String newPassword) {
		if	(newPassword == null) {
			return false;
		}
		
		return newPassword.equals(passWord);
				
	}
	
//	public String getName() {
//		return name;
//	}
//	public String getEmail() {
//		return email;
//	}
//	
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	
	
}
