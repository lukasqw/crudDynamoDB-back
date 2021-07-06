package com.crud.dynamodb.exception;

import java.time.Instant;
import java.util.Map;


public class ValidationError {
	
	private Instant timestamp;
	private Integer status;
	private Map<String, String>  error;
	private String message;
	private String path;
	
	public Instant getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Map<String, String>  getError() {
		return error;
	}
	public void setError(Map<String, String>  error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public ValidationError() {
		
	}
	
	
	
}
