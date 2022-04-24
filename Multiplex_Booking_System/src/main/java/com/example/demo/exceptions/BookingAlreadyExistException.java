package com.example.demo.exceptions;

public class BookingAlreadyExistException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5227974584310703048L;

	public BookingAlreadyExistException(String message) {
				super(message);
}

}