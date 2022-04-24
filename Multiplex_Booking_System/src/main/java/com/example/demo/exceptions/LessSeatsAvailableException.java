package com.example.demo.exceptions;

public class LessSeatsAvailableException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7708707184901509342L;

	public LessSeatsAvailableException(String message) {
		super(message);
	}

}
