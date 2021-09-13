package com.yash.msdemo.exception;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
@SuppressWarnings("serial")
public class InvalidUserIdException extends RuntimeException {

	public InvalidUserIdException(String id) {
		super("Invalid user id " + id);
	}
}
