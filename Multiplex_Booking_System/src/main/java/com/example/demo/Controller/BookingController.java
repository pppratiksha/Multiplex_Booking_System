package com.example.demo.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Booking;
import com.example.demo.exceptions.BookingAlreadyExistException;
import com.example.demo.exceptions.BookingDetailNotFoundException;
import com.example.demo.exceptions.CapacityExceededException;
import com.example.demo.exceptions.HallNotFoundException;
import com.example.demo.exceptions.LessSeatsAvailableException;
import com.example.demo.exceptions.MovieNotFoundException;
import com.example.demo.exceptions.SeatTypeNotFoundException;
import com.example.demo.exceptions.ShowNotFoundException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.Service.BookingService;

@RestController
@RequestMapping("/AppV1")
public class BookingController {

	public BookingController() {
	}
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/movie/{movieid}/show/{showid}/hall/{hallid}/seattype/{seattypeid}/booking")
	public Map<String,Boolean> addBookingDetails(@PathVariable("movieid") Long movieid,@PathVariable("showid") Long showid,
			@PathVariable("hallid") Long hallid, @PathVariable("seattypeid") Long seattypeid,@RequestBody Booking booking,@RequestParam(name="userid") Long userid,
			@RequestParam(name="count") int count) throws BookingAlreadyExistException, MovieNotFoundException, HallNotFoundException, SeatTypeNotFoundException, ShowNotFoundException, UserNotFoundException, CapacityExceededException, LessSeatsAvailableException{
		
		return bookingService.addBookingDetails(movieid, showid, hallid, seattypeid, booking, userid, count);
	
		}
	
	
	@DeleteMapping("/movie/{movieid}/show/{showid}/hall/{hallid}/seattype/{seattypeid}/booking/{bookingid}")
	public Map<String,Boolean> BookingDetails(@PathVariable("movieid") Long movieid,@PathVariable("showid") Long showid,
			@PathVariable("hallid") Long hallid, @PathVariable("seattypeid") Long seattypeid,@PathVariable("bookingid") Long bookingid, @RequestParam(name="userid") Long userid
			) throws BookingAlreadyExistException, MovieNotFoundException, HallNotFoundException, SeatTypeNotFoundException, ShowNotFoundException, UserNotFoundException, CapacityExceededException, LessSeatsAvailableException, BookingDetailNotFoundException{
		
		return bookingService.deleteBookingDetails(movieid, showid, hallid, seattypeid, bookingid, userid);
	
		}
	
}
	
