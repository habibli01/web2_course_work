package com.devsoftbd.personrestapi.exception;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ErrorDetails {

	private Date timestamp;
	private HttpStatus status;
	private String message;
	private List<String> errors;

	public ErrorDetails(Date timestamp, HttpStatus status, String message, List<String> errors) {
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

}
