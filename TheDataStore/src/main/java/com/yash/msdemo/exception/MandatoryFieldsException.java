package com.yash.msdemo.exception;

/**
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
@SuppressWarnings("serial")
public class MandatoryFieldsException extends RuntimeException {

	public MandatoryFieldsException(String field) {
		super("Mandatory field: " + field);
	}

}
