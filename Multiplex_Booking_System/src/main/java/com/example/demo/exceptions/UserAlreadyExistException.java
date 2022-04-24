package com.example.demo.exceptions;

public class UserAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4842082509030870624L;

	public UserAlreadyExistException(String message) {
		super(message);
	}

}