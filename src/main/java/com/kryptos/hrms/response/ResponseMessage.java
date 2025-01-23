package com.kryptos.hrms.response;

public class ResponseMessage<T> {

	private Boolean success;
	
	private int messageCode;

	private String message;

	private T data;

	public ResponseMessage() {
		// TODO Auto-generated constructor stub
	}

	public ResponseMessage(Boolean success, int messageCode, String message, T data) {
		super();
		this.success = success;
		this.messageCode = messageCode;
		this.message = message;
		this.data = data;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	
	public int getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(int messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
