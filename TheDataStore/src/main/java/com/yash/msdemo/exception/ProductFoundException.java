package com.yash.msdemo.exception;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
@SuppressWarnings("serial")
public class ProductFoundException extends RuntimeException {

	public ProductFoundException() {
		super("A product with given details already exist.");
	}

}
