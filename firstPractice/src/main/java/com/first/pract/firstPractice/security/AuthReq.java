package com.first.pract.firstPractice.security;

public class AuthReq {

	private String email;
	private String password;
	@Override
	public String toString() {
		return "AuthReq [" + (email != null ? "email=" + email + ", " : "")
				+ (password != null ? "password=" + password : "") + "]";
	}
	private AuthReq(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	private AuthReq() {
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
