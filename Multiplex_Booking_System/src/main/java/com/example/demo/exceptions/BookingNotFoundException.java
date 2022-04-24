package com.example.demo.exceptions;

public class BookingNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6967556648658805284L;

	public BookingNotFoundException(String message) {
		super(message);
	}

}
