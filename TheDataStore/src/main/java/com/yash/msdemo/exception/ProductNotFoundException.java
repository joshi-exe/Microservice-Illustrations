package com.yash.msdemo.exception;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
@SuppressWarnings("serial")
public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException() {
		super("Could not find any product");
	}

	public ProductNotFoundException(Long id) {
		super("Could not find product " + id);
	}

}
