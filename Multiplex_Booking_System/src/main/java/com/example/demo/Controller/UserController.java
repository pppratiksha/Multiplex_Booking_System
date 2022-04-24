package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//import com.cg.multiplexbookingsystem.entity.Booking;
//import com.cg.multiplexbookingsystem.entity.Movie;
//import com.cg.multiplexbookingsystem.entity.Show;
//import com.example.demo.model.*;
import com.example.demo.exceptions.BookingAlreadyExistException;
import com.example.demo.exceptions.BookingDetailNotFoundException;
import com.example.demo.exceptions.CapacityExceededException;
import com.example.demo.exceptions.HallNotFoundException;
import com.example.demo.exceptions.LessSeatsAvailableException;
import com.example.demo.exceptions.MovieNotFoundException;
import com.example.demo.exceptions.SeatTypeNotFoundException;
import com.example.demo.exceptions.ShowNotFoundException;
import com.example.demo.exceptions.UserAlreadyExistException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.model.Booking;
import com.example.demo.model.Movie;
import com.example.demo.model.Show;
import com.example.demo.model.User;
//import com.cg.multiplexbookingsystem.repository.HallRepository;
//import com.cg.multiplexbookingsystem.repository.MovieRepository;
//import com.cg.multiplexbookingsystem.repository.SeatTypeRepository;
//import com.cg.multiplexbookingsystem.repository.ShowRepository;
import com.example.demo.Service.IUserService;


@RestController
@RequestMapping("/AppV1")
public class UserController {

	public UserController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private IUserService userService;
	
	@PostMapping("/adduserdetails")
	public User addUserDetails(@RequestBody User user) throws UserAlreadyExistException {
		return userService.addUserDetails(user);
	}
	
	@GetMapping("/getallusers")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@GetMapping("/showuserdetails/{userid}")
	public User showUserDetails(@PathVariable("userid") Long userid) throws UserNotFoundException {
		return userService.showUserDetails(userid);
	}
	
	
	
	@PutMapping("/updateuserdetails")
	public User updateUserDetails(@RequestBody User user) throws UserNotFoundException {
		return userService.updateUserDetails(user);
	}
		
		
		//--------------------------------------------------------------------------------------------------------//
	@PutMapping("/user/{user}")
	public User updateUserDetailsById(@PathVariable("userid") Long userid,@RequestBody User user) throws UserNotFoundException {
		return userService.updateUserDetails(user);
	}
			
		
		@GetMapping("/user/{username}")
		public User showUserDetailsByName(@PathVariable("username") String userid) throws UserNotFoundException {
			return userService.showUserDetailsByName(userid);
		}
		
		@GetMapping("showmoviebyname/movie/{name}")
		public Movie showMovieDetailsByName(@PathVariable("name") String name) throws UserNotFoundException, MovieNotFoundException {
//			Movie m=restTemplate.getForObject("http://MOVIE-MICROSERVICE/AppV1/getallmovies", Movie.class);
			return userService.viewAllMovieByName(name);
		}
		
		@GetMapping("viewshowbymoviename/movie/{name}/show")
		public List<Show> findShowByMovieName(@PathVariable("name") String name) throws MovieNotFoundException {
			return userService.findShowByMovieName(name);
		}
		
	//----------------booking---------------------------//
		
		@PostMapping("userbookmovie/movie/{movieid}/show/{showid}/hall/{hallid}/seattype/{seattypeid}/booking/user/{userid}/{count}")
		public Map<String,Boolean> bookMovie(@PathVariable("movieid") Long movieid,@PathVariable("showid") Long showid,
				@PathVariable("hallid") Long hallid, @PathVariable("seattypeid") Long seattypeid,@RequestBody Booking booking,@PathVariable(name="userid") Long userid,
				@PathVariable(name="count") int count) throws BookingAlreadyExistException, MovieNotFoundException, HallNotFoundException, SeatTypeNotFoundException, ShowNotFoundException, UserNotFoundException, CapacityExceededException, LessSeatsAvailableException{
			
			return userService.bookMovie(movieid, showid, hallid, seattypeid, booking, userid, count);
		
			}
		@DeleteMapping("userdeletemovie/movie/{movieid}/show/{showid}/hall/{hallid}/seattype/{seattypeid}/booking/{bookingid}")
		public Map<String,Boolean> cancelBooking(@PathVariable("movieid") Long movieid,@PathVariable("showid") Long showid,
				@PathVariable("hallid") Long hallid, @PathVariable("seattypeid") Long seattypeid,@PathVariable("bookingid") Long bookingid, @RequestParam(name="userid") Long userid
				) throws BookingAlreadyExistException, MovieNotFoundException, HallNotFoundException, SeatTypeNotFoundException, ShowNotFoundException, UserNotFoundException, CapacityExceededException, LessSeatsAvailableException, BookingDetailNotFoundException{
			
			return userService.cancelBooking(movieid, showid, hallid, seattypeid, bookingid, userid);
		
			}
		
	}