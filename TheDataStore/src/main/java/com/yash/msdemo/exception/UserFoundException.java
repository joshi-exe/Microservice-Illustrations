package com.yash.msdemo.exception;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
@SuppressWarnings("serial")
public class UserFoundException extends RuntimeException {

	public UserFoundException(Long id) {
		super("Found existing user with id " + id);
	}

	public UserFoundException(String username) {
		super("\'" + username + "\' already in use. Please select a different username.");
	}

}