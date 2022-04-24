package com.example.demo.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.cg.multiplexbookingsystem.entity.Booking;
//import com.cg.multiplexbookingsystem.entity.Movie;
//import com.cg.multiplexbookingsystem.entity.Show;
import com.example.demo.model.*;
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
import com.example.demo.repository.HallRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.SeatTypeRepository;
import com.example.demo.repository.ShowRepository;
//import com.cg.multiplexbookingsystem.repository.HallRepository;
//import com.cg.multiplexbookingsystem.repository.MovieRepository;
//import com.cg.multiplexbookingsystem.repository.SeatTypeRepository;
//import com.cg.multiplexbookingsystem.repository.ShowRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserService implements IUserService{

	public UserService() {
			}
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private ShowRepository showRepository;
	@Autowired
	private HallRepository hallRepository;
	@Autowired
	private SeatTypeRepository seattypeRepository;
	@Autowired
	private BookingService bookingService;
	

//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//	
	
	@Override
	public User addUserDetails(User user) throws UserAlreadyExistException {
		Optional<User> optionalUser =  userRepository.findById(user.getUserid());
        if(optionalUser.isPresent()) {
        	throw new UserAlreadyExistException("User already exists..!!");
        }
        
       // req.setPassword(passwordEncoder.encode(req.getPassword()));
        
      //  user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);

	}

	@Override
	public User updateUserDetails(User user) throws UserNotFoundException {
		Optional<User> optionalUser =  userRepository.findById(user.getUserid());
        if(!(optionalUser.isPresent())) {
        	throw new UserNotFoundException("This user does not exists..!! Please enter correct details");
        }
		return userRepository.save(user);

	}

	@Override
	public User showUserDetails(Long userid) throws UserNotFoundException {
		Optional<User> optionalUser =  userRepository.findById(userid);
        if(!(optionalUser.isPresent())) {
        	throw new UserNotFoundException("The with user id: "+userid+" does not exists..!! Please enter correct details");
        }
        return optionalUser.get();
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();

	}

	@Override
	public User updateUserDetailsById(Long userid, User user) throws UserNotFoundException {
		if(!(userRepository.existsById(userid))) {
        	throw new UserNotFoundException("The with user id: "+userid+" does not exists..!! Please enter correct details");
		}
		return userRepository.save(user);
	}

	@Override
	public User showUserDetailsByName(String username) throws UserNotFoundException {
		List<User> users =userRepository.findAll();
		for(User i : users) {
		if(i.getUsername()!=username){
			throw new UserNotFoundException("User with user name: "+username+" does not exist");
		}
		}
		return userRepository.findByUsername(username);
	}

	@Override
	public Movie viewAllMovieByName(String moviename) throws MovieNotFoundException {
		List<Movie>movies = movieRepository.findAll();
		for(Movie i : movies) {
			if(i.getMovieName()!=moviename) {
				throw new MovieNotFoundException("Movie with movie name: "+moviename+" does not exist");

			}
		}
		return movieRepository.findBymovieName(moviename);
	}

	
	@Override
	public List<Show> findShowByMovieName(String name) throws MovieNotFoundException {
		
		return showRepository.findByMovieMovieName(name);
	}	

	

	@Override
	public Map<String, Boolean> bookMovie(Long movieid, Long showid, Long hallid, Long seattypeid,
			Booking booking, long userid, int count) throws BookingAlreadyExistException, MovieNotFoundException,
			HallNotFoundException, SeatTypeNotFoundException, ShowNotFoundException, UserNotFoundException,
			CapacityExceededException, LessSeatsAvailableException {
		// TODO Auto-generated method stub
		return bookingService.addBookingDetails(movieid, showid, hallid, seattypeid, booking, userid, count);
	}

	@Override
	public Map<String, Boolean> cancelBooking(Long movieid, Long showid, Long hallid, Long seattypeid,
			Long bookingid, long userid) throws BookingAlreadyExistException, MovieNotFoundException,
			HallNotFoundException, SeatTypeNotFoundException, ShowNotFoundException, UserNotFoundException,
			CapacityExceededException, LessSeatsAvailableException, BookingDetailNotFoundException {
		
		return bookingService.deleteBookingDetails(movieid, showid, hallid, seattypeid, bookingid, userid);
	}
		
	}

