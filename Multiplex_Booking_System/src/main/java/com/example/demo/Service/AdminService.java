package com.example.demo.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Hall;
import com.example.demo.model.Movie;
import com.example.demo.model.SeatType;
import com.example.demo.model.Show;
import com.example.demo.model.User;
import com.example.demo.exceptions.HallNotFoundException;
import com.example.demo.exceptions.MovieAlreadyExistException;
import com.example.demo.exceptions.MovieNotFoundException;
import com.example.demo.exceptions.SeatTypeAlreadyExistException;
import com.example.demo.exceptions.SeatTypeNotFoundException;
import com.example.demo.exceptions.ShowAlreadyExistException;
import com.example.demo.exceptions.ShowNotFoundException;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.ShowRepository;

@Service
public class AdminService implements IAdminService {

	public AdminService() {
	}
	
	
	
	@Autowired
	 private MovieRepository movieRepository;
	@Autowired
	 private ShowRepository showRepository;
	@Autowired
	 private UserService userService;
	@Autowired
	 private MovieService movieService;
	@Autowired
	private ShowService showService;
	@Autowired
	private HallService hallService;
	@Autowired
	private SeatTypeService seattypeService;
	
	//-----------------------user------------------------------//
	@Override
	public User updateUserDetailsById(Long userid, User user) throws UserNotFoundException {
	return userService.updateUserDetailsById(userid, user);
	}
	@Override
	public User showUserDetails(Long userid) throws UserNotFoundException {
		return userService.showUserDetails(userid);
	}
	@Override
	public User showUserDetailsByName(String username) throws UserNotFoundException {
		return userService.showUserDetailsByName(username);
	}
	@Override
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	@Override
	public Movie viewAllMovieByName(String moviename) throws MovieNotFoundException {
		return userService.viewAllMovieByName(moviename);
	}
	@Override
	public List<Show> findShowByMovieName(String name) throws MovieNotFoundException {
		return userService.findShowByMovieName(name);
	}
	
	
	//---------------movie-----------------//
	@Override
	public List<Movie> getAllMovies() {
		
		return movieService.getAllMovies();
	}
	@Override
	public ResponseEntity<Movie> showMovieDetails(Long movieId) throws MovieNotFoundException {
		return movieService.showMovieDetails(movieId);
	}
	@Override
	public Movie addMovieDetails(Movie movie) throws MovieAlreadyExistException {
		return movieService.addMovieDetails(movie);
	}
	@Override
	public ResponseEntity<Movie> updateMovieDetails(Long movieid, Movie movie) throws MovieNotFoundException {
		return movieService.updateMovieDetails(movieid, movie);
	}
	@Override
	public Map<String, Boolean> deleteMovieDetails(Long movieId) throws MovieNotFoundException {
		return movieService.deleteMovieDetails(movieId);
	}
	
	
	//--------------------------show----------------------------//
	
	@Override
	public ResponseEntity<Show> viewShowDetails(Long showid) throws ShowNotFoundException {
		return showService.viewShowDetails(showid);
	}
	@Override
	public ResponseEntity<?> deleteShowDetails(Long movieid, Long showid)
			throws ShowNotFoundException, MovieNotFoundException {
		return showService.deleteShowDetails(movieid, showid);
	}
	@Override
	public Show updateShowDetails(Long movieid, Long showid, Show show)
			throws ShowNotFoundException, MovieNotFoundException {
		return showService.updateShowDetails(movieid, showid, show);
	}
	@Override
	public Show addShowDetails(Long movieid, Show show) throws ShowAlreadyExistException, MovieNotFoundException {
		return showService.addShowDetails(movieid, show);
	}
	@Override
	public List<Show> findByMovieId(Long movieid) throws MovieNotFoundException {
		return showService.findByMovieId(movieid);
	}
	@Override
	public List<Show> getAllShows() {
		return showService.getAllShows();
	}
	
	@Override
	public Object addMovieDetailsCascaded(Movie movie, Long movieid, Show show) throws MovieNotFoundException {
		movieRepository.save(movie);
		
		return movieRepository.findById(movieid).map(movies ->{
			show.setMovie(movies);
			return showRepository.save(show);
		}).orElseThrow(() -> new MovieNotFoundException("Movie not found"));
	}
	
	//---------------hall------------------------------------------------------------//
	
	@Override
	public List<Hall> getAllHalls() {
		return hallService.getAllHalls();
	}
	@Override
	public ResponseEntity<Hall> viewHallDetails(Long hallid) throws HallNotFoundException {
		return hallService.viewHallDetails(hallid);
	}
	@Override
	public ResponseEntity<?> deleteHallDetailsById(Long showid, Long hallid)
			throws ShowNotFoundException, MovieNotFoundException, HallNotFoundException {
		return hallService.deleteHallDetailsById(showid, hallid);
	}
	@Override
	public Hall updateHallDetailsById(Long movieid, Long showid, Long Halllid, Hall hall)
			throws ShowNotFoundException, MovieNotFoundException, HallNotFoundException {
		return hallService.updateHallDetailsById(movieid, showid, Halllid, hall);
	}
	@Override
	public Hall addHallDetailsById(Long showid, Hall hall)
			throws ShowAlreadyExistException, MovieNotFoundException, ShowNotFoundException {
		return hallService.addHallDetailsById(showid, hall);
	}
	@Override
	public List<Hall> findHallByShowId(Long showId) throws HallNotFoundException, ShowNotFoundException {
		return hallService.findHallByShowId(showId);
	}
	
	//----------------------SeatType---------------------------------//
	
	@Override
	public List<SeatType> getAllSeatTypes() {
		return seattypeService.getAllSeatTypes();
	}
	@Override
	public ResponseEntity<SeatType> viewSeatTypeDetails(Long seattypeid) throws SeatTypeNotFoundException {
		return seattypeService.viewSeatTypeDetails(seattypeid);
	}
	@Override
	public ResponseEntity<?> deleteSeatTypeDetails(Long hallid, Long seattypeid)
			throws HallNotFoundException, SeatTypeNotFoundException {
		return seattypeService.deleteSeatTypeDetails(hallid, seattypeid);
	}
	@Override
	public SeatType updateSeatTypeDetails(Long hallid, Long seattypeid, SeatType seattype)
			throws HallNotFoundException, SeatTypeNotFoundException {
		return seattypeService.updateSeatTypeDetails(hallid, seattypeid, seattype);
	}
	@Override
	public SeatType addSeatTypeDetails(Long hallid, SeatType seattype)
			throws SeatTypeAlreadyExistException, HallNotFoundException {
		return seattypeService.addSeatTypeDetails(hallid, seattype);
	}
	@Override
	public List<SeatType> findByHallId(Long hallid) {
		return seattypeService.findByHallId(hallid);
	}
	@Override
	public Optional<SeatType> findByIdAndHallId(Long seattypeid, Long hallid) {
		return seattypeService.findByIdAndHallId(seattypeid, hallid);
	}
	
	
	
	
	
	

}
