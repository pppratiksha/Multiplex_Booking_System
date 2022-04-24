package com.example.demo.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.exceptions.BookingAlreadyExistException;
import com.example.demo.exceptions.BookingNotFoundException;
import com.example.demo.exceptions.CapacityExceededException;
import com.example.demo.exceptions.ErrorMessage;
import com.example.demo.exceptions.HallAlreadyExistException;
import com.example.demo.exceptions.HallNotFoundException;
import com.example.demo.exceptions.LessSeatsAvailableException;
import com.example.demo.exceptions.MovieAlreadyExistException;
import com.example.demo.exceptions.MovieNotFoundException;
import com.example.demo.exceptions.SeatTypeAlreadyExistException;
import com.example.demo.exceptions.SeatTypeNotFoundException;
import com.example.demo.exceptions.ShowAlreadyExistException;
import com.example.demo.exceptions.ShowNotFoundException;
import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.exceptions.UserNotFoundException;

@RestControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity handleException(Exception ex) {
		return new ResponseEntity(ex.getMessage(),HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleUserNotFoundException(UserNotFoundException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity(error,HttpStatus.OK);
		
	}
	
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<ErrorMessage> handleUserAlreadyExistException(UserAlreadyExistException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity(error,HttpStatus.OK);
		
	}
	
	@ExceptionHandler(ShowNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleShowNotFoundException(ShowNotFoundException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity(error,HttpStatus.OK);
	
}
	
	@ExceptionHandler(ShowAlreadyExistException.class)
	public ResponseEntity<ErrorMessage> handleShowAlreadyExistException(ShowAlreadyExistException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity(error,HttpStatus.OK);	
	
}
	@ExceptionHandler(SeatTypeNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleSeatTypeNotFoundException(SeatTypeNotFoundException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity(error,HttpStatus.OK);
		
	}
	
	@ExceptionHandler(SeatTypeAlreadyExistException.class)
	public ResponseEntity<ErrorMessage> handleSeatTypeAlreadyExistException(SeatTypeAlreadyExistException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity(error,HttpStatus.OK);
	}
	
	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleMovieNotFoundException(MovieNotFoundException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity(error,HttpStatus.OK);
	}
	@ExceptionHandler(MovieAlreadyExistException.class)
	public ResponseEntity<ErrorMessage> handleMovieAlreadyExistException(MovieAlreadyExistException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity(error,HttpStatus.OK);
	}
	
	@ExceptionHandler(LessSeatsAvailableException.class)
	public ResponseEntity<ErrorMessage> handleLessSeatsAvailableException(LessSeatsAvailableException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity(error,HttpStatus.OK);
	}
	
	@ExceptionHandler(HallNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleHallNotFoundtException(HallNotFoundException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity(error,HttpStatus.OK);
	}
	
	@ExceptionHandler(HallAlreadyExistException.class)
	public ResponseEntity<ErrorMessage> handleHallAlreadyExistException(HallAlreadyExistException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity(error,HttpStatus.OK);
	}
	
	@ExceptionHandler(CapacityExceededException.class)
	public ResponseEntity<ErrorMessage> handleCapacityExceededException(CapacityExceededException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity(error,HttpStatus.OK);
	}
	
	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleBookingNotFoundException(BookingNotFoundException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity(error,HttpStatus.OK);
	}
	
	@ExceptionHandler(BookingAlreadyExistException.class)
	public ResponseEntity<ErrorMessage> handleBookingAlreadyExistException(BookingAlreadyExistException ex) {
		ErrorMessage error = new ErrorMessage();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setErrorMessage(ex.getMessage());
		return new ResponseEntity(error,HttpStatus.OK);
	}


}

