package com.example.demo.exceptions;

public class MovieAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4669984861107485142L;

	public MovieAlreadyExistException(String message) {
		super(message);
	}

}

