package com.yash.msdemo.exception;

import java.util.List;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
public class ErrorResponse {

	public ErrorResponse(String message, List<String> details) {
		super();
		this.message = message;
		this.details = details;
	}

	private String message;
	private List<String> details;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

}