package com.yash.msdemo.exception;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
@SuppressWarnings("serial")
public class PasswordException extends RuntimeException {

	public PasswordException() {
		super("Passwords do not match.");
	}

}
