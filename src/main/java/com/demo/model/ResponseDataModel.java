package com.demo.model;

public class ResponseDataModel {

	private int responseCode;
	private String responseMessage;
	private Object data;
	
	public ResponseDataModel() {
		// TODO Auto-generated constructor stub
	}
	
	public ResponseDataModel(int responseCode, String responseMessage) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}

	public ResponseDataModel(int responseCode, String responseMessage, Object data) {
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.data = data;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
