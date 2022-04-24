package com.example.demo.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.example.demo.repository.UserRepository;
public interface IUserService {
	public User updateUserDetailsById(Long userid,User user)throws UserNotFoundException;
	
	public User addUserDetails(User user) throws UserAlreadyExistException;
	
	public User updateUserDetails(User user) throws UserNotFoundException;
	
	public User showUserDetails(Long userid) throws UserNotFoundException;
	
	public User showUserDetailsByName(String username) throws UserNotFoundException;
	
	public List<User> getAllUsers();
	
	public Movie viewAllMovieByName(String moviename) throws MovieNotFoundException;

	public List<Show>findShowByMovieName(String name) throws MovieNotFoundException;
	
	public Map<String,Boolean> bookMovie(Long movieid,Long showid,Long hallid,Long seattypeid, Booking booking, long userid, int count) throws BookingAlreadyExistException, MovieNotFoundException, HallNotFoundException, SeatTypeNotFoundException, ShowNotFoundException, UserNotFoundException, CapacityExceededException, LessSeatsAvailableException;
	
	public Map<String,Boolean> cancelBooking(Long movieid,Long showid,Long hallid,Long seattypeid, Long bookingid, long userid) throws BookingAlreadyExistException, MovieNotFoundException, HallNotFoundException, SeatTypeNotFoundException, ShowNotFoundException, UserNotFoundException, CapacityExceededException, LessSeatsAvailableException, BookingDetailNotFoundException;

}


