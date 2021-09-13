package com.yash.msdemo.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Convenient handler class to return custom exceptions as response entity.
 *
 * @author Yash Joshi
 * @date created on 12-Sep-2021
 */
@ControllerAdvice
class GenericExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseBody
	@ExceptionHandler(UserNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ResponseEntity<ErrorResponse> userNotFoundHandler(UserNotFoundException ex) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		details.add("code: 404. Not found.");
		details.add("reason: No records were found in the database with the given user id");
		details.add("action: Make sure the user id is correct");
		ErrorResponse er = new ErrorResponse(ex.getMessage(), details);
		return new ResponseEntity<ErrorResponse>(er, HttpStatus.NOT_FOUND);
	}

	@ResponseBody
	@ExceptionHandler(UserFoundException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	ResponseEntity<ErrorResponse> userFoundHandler(UserFoundException ex) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		details.add("reason: A user with the given username already exists in the record");
		details.add("action: Consider changing the username to something else");
		ErrorResponse er = new ErrorResponse(ex.getMessage(), details);
		return new ResponseEntity<ErrorResponse>(er, HttpStatus.CONFLICT);
	}

	@ResponseBody
	@ExceptionHandler(PasswordException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	ResponseEntity<ErrorResponse> passwordExHandler(PasswordException ex) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		details.add("action: Recheck both the passwords");
		ErrorResponse er = new ErrorResponse(ex.getMessage(), details);
		return new ResponseEntity<ErrorResponse>(er, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ResponseBody
	@ExceptionHandler(MandatoryFieldsException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	ResponseEntity<ErrorResponse> mandatoryFieldExHandler(MandatoryFieldsException ex) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		ErrorResponse er = new ErrorResponse(ex.getMessage(), details);
		return new ResponseEntity<ErrorResponse>(er, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ResponseBody
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	ResponseEntity<ErrorResponse> productNotFoundHandler(ProductNotFoundException ex) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		details.add("code: 404. Not found.");
		details.add("reason: No records were found in the database with the given product id");
		details.add("action: Make sure the product id is correct");
		ErrorResponse er = new ErrorResponse(ex.getMessage(), details);
		return new ResponseEntity<ErrorResponse>(er, HttpStatus.NOT_FOUND);
	}

	@ResponseBody
	@ExceptionHandler(InvalidUserIdException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	ResponseEntity<ErrorResponse> invalidUserIdExHandler(InvalidUserIdException ex) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		details.add("action: Make sure the user id is correct and a valid number");
		ErrorResponse er = new ErrorResponse(ex.getMessage(), details);
		return new ResponseEntity<ErrorResponse>(er, HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@ExceptionHandler(IllegalQtyException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	ResponseEntity<ErrorResponse> illegalQtyExHandler(IllegalQtyException ex) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		ErrorResponse er = new ErrorResponse(ex.getMessage(), details);
		return new ResponseEntity<ErrorResponse>(er, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ResponseBody
	@ExceptionHandler(ProductFoundException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	ResponseEntity<ErrorResponse> productFoundHandler(ProductFoundException ex) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		ErrorResponse er = new ErrorResponse(ex.getMessage(), details);
		return new ResponseEntity<ErrorResponse>(er, HttpStatus.CONFLICT);
	}

	@ResponseBody
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	ResponseEntity<ErrorResponse> nullPointerHandler(NullPointerException ex) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		ErrorResponse er = new ErrorResponse(ex.getMessage(), details);
		return new ResponseEntity<ErrorResponse>(er, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RuntimeException.class)
	ResponseEntity<ErrorResponse> allExceptionHandler(RuntimeException ex) {
		List<String> details = new ArrayList<String>();
		details.add(ex.getMessage());
		ErrorResponse er = new ErrorResponse(ex.getMessage(), details);
		return new ResponseEntity<ErrorResponse>(er, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
