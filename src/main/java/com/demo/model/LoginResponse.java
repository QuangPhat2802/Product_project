package com.demo.model;

public class LoginResponse {

	private String accessToken;
	private String tokenType = "Bearer";
	
	public LoginResponse() {
		// TODO Auto-generated constructor stub
	}

	public LoginResponse(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	
}
