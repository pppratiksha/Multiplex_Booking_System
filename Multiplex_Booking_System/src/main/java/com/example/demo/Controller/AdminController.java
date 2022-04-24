package com.example.demo.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
import com.example.demo.Service.AdminService;

@RestController
@RequestMapping("/AppV1")
public class AdminController {

	public AdminController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	AdminService adminService;
	
//	@PostMapping("addmoviedetailscascaded/movie/{movieid}/show")
//	public Object addMovieDetailsCascaded
//	(@RequestBody Movie movie,@PathVariable("movieid")Long movieid, @RequestBody Show show)
//			throws MovieNotFoundException,ShowNotFoundException, ShowAlreadyExistException{
//		
//		return adminService.addMovieDetailsCascaded(movie, movieid, show);
//	}
	
	
	//------------------Movie CRUD Start--------------------------//
	
	@PostMapping("adminaddmoviedetails/movie")
	public Movie addMovieDetails(@RequestBody Movie movie) throws MovieAlreadyExistException {
		return adminService.addMovieDetails(movie);
	}
	
	@PutMapping("adminupdatemoviedetails/{movieid}")
	public ResponseEntity<Movie> updateMovieDetails(@PathVariable("movieid")Long movieid,@RequestBody Movie movie) throws MovieNotFoundException {
		return adminService.updateMovieDetails(movieid, movie);
	}
	
	@GetMapping("/admingetallmovies")
	public List<Movie> getAllMovies() {
		return adminService.getAllMovies();
	}
	
	@GetMapping("/adminshowmoviedetails/{movieid}")
	public ResponseEntity<Movie> showMovieDetails(@PathVariable("movieid") Long movieid) throws MovieNotFoundException {
		return adminService.showMovieDetails(movieid);
	}
	
	@DeleteMapping("/admindeletemoviedetails/{movieid}")
	public Map<String, Boolean> deleteMovieDetails(@PathVariable("movieid") Long movieid) throws MovieNotFoundException {
		return adminService.deleteMovieDetails(movieid);
	}
	
	//-------------------Movie CRUD End------------------------------//
	
	
	
	//-----------------Show CRUD Start------------------------------//
	
	
	@PostMapping("adminaddshowdetails/movie/{movieid}/show")
	public Show addShowDetails
	(@PathVariable("movieid")Long movieid,@RequestBody Show show)
			throws MovieNotFoundException,ShowNotFoundException, ShowAlreadyExistException{
		
		return adminService.addShowDetails(movieid,show);
	}
	
	@DeleteMapping("adminaddmoviedetails/movie/{movieid}/show/{showid}")
	public  ResponseEntity<?> deleteShowDetails(@PathVariable("movieid")Long movieid,@PathVariable("showid")Long showid) throws ShowNotFoundException, MovieNotFoundException {
		return adminService.deleteShowDetails(movieid,showid);
		
	}
	
	@PutMapping("adminupdateshowdetails/movie/{movieid}/show/{showid}")
	public Show updateShowDetails(@PathVariable("movieid")Long movieid,
			@PathVariable("showid")Long showid,@RequestBody Show show) throws MovieNotFoundException 
	, ShowNotFoundException{
		return adminService.updateShowDetails(movieid, showid, show);
	}
	
	@GetMapping("adminviewshowdetails/show/{showid}")
	public ResponseEntity<Show> viewShowDetails(@PathVariable("showid")Long showid) throws ShowNotFoundException{
		return adminService.viewShowDetails(showid);
	}
	
	@GetMapping("adminfindshowbymoviename/movie/movieName/{movieName}/show")
	public List<Show> findShowByMovieName(@PathVariable("movieName")String moviename) throws MovieNotFoundException{
		return adminService.findShowByMovieName(moviename);
	}
	
	@GetMapping("getshowbymovie/movie/{movieid}/show")
	public List<Show> getShowByMovie(@PathVariable("movieid") Long movieid) throws MovieNotFoundException{
		return adminService.findByMovieId(movieid);
	}
	
	//-----------------Show CRUD Ends-------------------------//
	
	
	//-----------------User CRUD Begins---------------------------//
	
	@PutMapping("adminupdateuserdetailsbyid/user/{user}")
	public User updateUserDetailsById(@PathVariable("userid") Long userid,@RequestBody User user) throws UserNotFoundException {
		return adminService.updateUserDetailsById(userid, user);
	}
	
	@GetMapping("adminshowuserdetailsbyname/user/{username}")
	public User showUserDetailsByName(@PathVariable("username") String userid) throws UserNotFoundException {
		return adminService.showUserDetailsByName(userid);
	}
	
	@GetMapping("adminshowuserdetailsbyid/showuserdetails/{userid}")
	public User showUserDetails(@PathVariable("userid") Long userid) throws UserNotFoundException {
		return adminService.showUserDetails(userid);
	}
	
	@GetMapping("admingetallusers/getallusers")
	public List<User> getAllUsers() {
		return adminService.getAllUsers();
	}
	
	//----------------User CRUD Ends---------------------------------//
	
	
	//----------------Hall CRUD Start---------------------------------//
	
	@GetMapping("admingetallhalls/getallhalls")
	public List<Hall> getAllHalls() {
		return adminService.getAllHalls();
	}
	
	@GetMapping("adminviewallhalldetails/hall/{hallid}")
	public ResponseEntity<Hall> viewHallDetails(@PathVariable("hallid")Long hallid) throws HallNotFoundException{
		return adminService.viewHallDetails(hallid);
	}
	
	@DeleteMapping("admindeletehalldetailsbyid/show/{showid}/hall/{hallid}")
	public ResponseEntity<?> deleteHallDetailsById(@PathVariable("showid")Long showid,@PathVariable("hallid")Long hallid) throws HallNotFoundException, ShowNotFoundException, MovieNotFoundException {
		return adminService.deleteHallDetailsById(showid,hallid);
	}
	
	@PutMapping("adminupdatehalldetailsbyid/updatehalldetailsbyid/movie/{movieid}/show/{showid}/hall/{hallid}")
	public Hall updateHallDetailsById(@PathVariable("movieid")Long movieid,
			@PathVariable("showid")Long showid,@PathVariable("hallid")Long hallid,@RequestBody Hall hall) throws MovieNotFoundException 
	, ShowNotFoundException,HallNotFoundException{
		return adminService.updateHallDetailsById(movieid, showid, hallid,hall);
		}
	
	
	@PostMapping("adminaddhalldetailsbyid/show/{showid}/hall")
	public Hall addHallDetailsById
	(@PathVariable("showid")Long showid, @RequestBody Hall hall)
			throws MovieNotFoundException,ShowNotFoundException, ShowAlreadyExistException{
		
		return adminService.addHallDetailsById(showid,hall);
	}
	
	@GetMapping("adminfindhallbyshowid/show/{showid}/hall")
	public List<Hall> findHallByShowId(@PathVariable("showid") Long showid) throws HallNotFoundException, ShowNotFoundException{
		return adminService.findHallByShowId(showid);
		
	}
//--------------------Hall CRUD Ends-----------------------------------//
		
		
//-------------------SeatType CRUD Starts--------------------------------//
		
		@GetMapping("admingeatallseattypes/getallseattypes")
		public List<SeatType> getAllSeatTypes() {
			return adminService.getAllSeatTypes();
		}
		
		@GetMapping("adminviewseattypedetails/seattype/{seattypeid}")
		public ResponseEntity<SeatType> viewSeatTypeDetails(@PathVariable("seattypeid")Long seattypeid) throws SeatTypeNotFoundException{
			return adminService.viewSeatTypeDetails(seattypeid);
		}
		
		@DeleteMapping("admindeleteseattypedetails/hall/{hallid}/seattype/{seattypeid}")
		public  ResponseEntity<?> deleteSeatTypeDetails(@PathVariable("hallid")Long hallid,@PathVariable("seattypeid")Long seattypeid) throws HallNotFoundException, SeatTypeNotFoundException {
			return adminService.deleteSeatTypeDetails(hallid,seattypeid);
			}
		
		@PutMapping("adminupdateseattypedetails/hall/{hallid}/seattype/{seattypeid}")
		public SeatType updateSeatTypeDetails(@PathVariable("hallid")Long hallid,
				@PathVariable("seattypeid")Long seattypeid,@RequestBody SeatType seattype) throws HallNotFoundException 
		, SeatTypeNotFoundException{
			return adminService.updateSeatTypeDetails(hallid, seattypeid, seattype);
		}
		
		@PostMapping("adminaddseattypedetails/hall/{hallid}/seattype")
		public SeatType addSeatTypeDetails
		(@PathVariable("hallid")Long hallid,/*@ApiParam(value="Course object stores in the database ",required = true)*/ @RequestBody SeatType seattype)
				throws SeatTypeNotFoundException,HallNotFoundException, SeatTypeAlreadyExistException{
			
			return adminService.addSeatTypeDetails(hallid,seattype);
		}
		
		@GetMapping("adminfindbyhallid/hall/{hallid}/seattype")
		public List<SeatType> findByHallId(@PathVariable("hallid")Long hallid) throws HallNotFoundException{
			return adminService.findByHallId(hallid);
		}
		
		
}
