package com.example.demo.exceptions;

public class UserNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1983567271276674481L;

	public UserNotFoundException(String message) {

		super(message);
	}

}
