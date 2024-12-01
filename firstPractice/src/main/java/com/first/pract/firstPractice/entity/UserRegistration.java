package com.first.pract.firstPractice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class UserRegistration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String email;
	@Column
	private String password;
	@Column
	private String role;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	private UserRegistration() {
		// TODO Auto-generated constructor stub
	}
	public UserRegistration(String email, String password, String role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserRegistration [" + (id != null ? "id=" + id + ", " : "")
				+ (email != null ? "email=" + email + ", " : "")
				+ (password != null ? "password=" + password + ", " : "") + (role != null ? "role=" + role : "") + "]";
	}
	
	
	
}
