package com.document.entity;

import java.time.LocalDate;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
	@NotNull
	@Column(unique = true)
	private String email;
	@NotNull
	private String password;
	@NotNull
	private String roles;
	@NotNull
	private LocalDate dob;
	@NotNull
	private String gender;
	
	
	public User() {
		super();
	}
	public User(Long id, @NotNull String name, @NotNull String email, @NotNull String password, @NotNull String roles,
			@NotNull LocalDate dob, @NotNull String gender) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.dob = dob;
		this.gender = gender;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getRoles() {
		return roles;
	}
	public LocalDate getDob() {
		return dob;
	}
	public String getGender() {
		return gender;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

}
