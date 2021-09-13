package com.yash.msdemo.exception;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
@SuppressWarnings("serial")
public class IllegalQtyException extends RuntimeException {

	public IllegalQtyException() {
		super("Quantity should be between 1 and 5.");
	}

}
