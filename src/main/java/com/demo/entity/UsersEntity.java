package com.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class UsersEntity {

	@Id
	@Column(name = "user_name")
	@NotNull
	private String userName;

	@Column(name = "password")
	private String password;

	public UsersEntity() {
		// TODO Auto-generated constructor stub
	}

	public UsersEntity(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

}
