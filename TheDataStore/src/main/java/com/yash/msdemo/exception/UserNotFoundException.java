package com.yash.msdemo.exception;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(Long id) {
		super("Could not find user " + id);
	}

	public UserNotFoundException() {
		super("No users found");
	}
}