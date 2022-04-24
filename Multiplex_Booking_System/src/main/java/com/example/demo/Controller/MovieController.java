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

import com.example.demo.model.Movie;
import com.example.demo.exceptions.MovieAlreadyExistException;
import com.example.demo.exceptions.MovieNotFoundException;
import com.example.demo.Service.IMovieService;

@RestController
@RequestMapping("/AppV1")
public class MovieController {
	
	@Autowired
	private IMovieService movieService;
	
	@PostMapping("/addmoviedetails")
	public Movie addMovieDetails(@RequestBody Movie movie) throws MovieAlreadyExistException {
		return movieService.addMovieDetails(movie);
	}
	
	@GetMapping("/getallmovies")
	public List<Movie> getAllMovies() {
		return movieService.getAllMovies();
	}
	
	@GetMapping("/showmoviedetails/{movieid}")
	public ResponseEntity<Movie> showMovieDetails(@PathVariable("movieid") Long movieid) throws MovieNotFoundException {
		return movieService.showMovieDetails(movieid);
	}
	
	@DeleteMapping("/deletemoviedetails/{movieid}")
	public Map<String, Boolean> deleteMovieDetails(@PathVariable("movieid") Long movieid) throws MovieNotFoundException {
		return movieService.deleteMovieDetails(movieid);
	}
	
	@PutMapping("/updatemoviedetails/{movieid}")
	public ResponseEntity<Movie> updateMovieDetails(@PathVariable("movieid")Long movieid,@RequestBody Movie movie) throws MovieNotFoundException {
		return movieService.updateMovieDetails(movieid, movie);
	}

	

}