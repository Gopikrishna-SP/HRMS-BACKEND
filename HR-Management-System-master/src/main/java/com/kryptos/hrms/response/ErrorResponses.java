package com.kryptos.hrms.response;

import java.time.LocalDateTime;

public class ErrorResponses {

	private int status;
	private Enum error;
    private String message;
    private LocalDateTime timestamp;
    private String path;
    
	
	public ErrorResponses(int status, Enum error, String message, LocalDateTime timestamp, String path) {
		super();
		this.status = status;
		this.error = error;
		this.message = message;
		this.timestamp = timestamp;
		this.path = path;
	}

	public ErrorResponses() {
		// TODO Auto-generated constructor stub
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Enum getError() {
		return error;
	}

	public void setError(Enum error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
    
	

}
