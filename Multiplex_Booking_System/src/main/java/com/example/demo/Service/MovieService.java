package com.example.demo.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Movie;
import com.example.demo.exceptions.MovieAlreadyExistException;
import com.example.demo.exceptions.MovieNotFoundException;
import com.example.demo.repository.MovieRepository;


@Service
public class MovieService implements IMovieService {
	
	@Autowired
	private MovieRepository movieRepository;

	public MovieService() {
		
	}
	
	
	
	
	
	
/*
 * 
 * This method is used to add movie details into the movie repository(table) using the object of movie in JSON format.
 * It return the Object of Movie.
 * 
 */
	@Override
	public Movie addMovieDetails(Movie movie) throws MovieAlreadyExistException {
		Optional<Movie> optionalMovie = movieRepository.findById(movie.getId());
		if(optionalMovie.isPresent()) {
			throw new MovieAlreadyExistException("Oops...!!! This movie already exists");
		}
		return movieRepository.save(movie);		
	}
	
	
	/*
	 * 
	 * This method is used to display all the movies and their details in JSON format. It returns the list of type Movie.
	 * 
	 */
	
	@Override
	public List<Movie> getAllMovies() {
		return movieRepository.findAll();
	}

	
	/*
	 * 
	 * This method is used to update the movie details using the unique id associated with the movie and the object of the movie 
	 * which will be stored in place of existing movie object. It return the ResponseBody of type Movie.
	 * 
	 */
	@Override
	public ResponseEntity<Movie> updateMovieDetails(Long movieid, Movie movie) throws MovieNotFoundException {
	
		Movie updateToMovie = movieRepository.findById(movieid)
				.orElseThrow(() -> new MovieNotFoundException("Movie not found :: " +movieid));
		updateToMovie.setMovieName(movie.getMovieName());
		updateToMovie.setMovieDescription(movie.getMovieDescription());
		final Movie updatedMovie = movieRepository.save(updateToMovie);
		return ResponseEntity.ok(updatedMovie);		
	}

	
	/*
	 * 
	 * This method is used to display the details of a particular movie by passing the movie id of the respective movie.
	 * It return the ResponseBody of type Movie.
	 * 
	 */
	@Override
	public ResponseEntity<Movie> showMovieDetails(Long movieId) throws MovieNotFoundException {
		
		Movie movie = movieRepository.findById(movieId)
				.orElseThrow(() -> new MovieNotFoundException("Movie not found :: " +movieId));
		return ResponseEntity.ok().body(movie);
    }
	
	
	/*
	 * 
	 * This method is used to delete the movie that is pre existing in the database. 
	 * It accepts the movie id and deletes the information of the matching movie.
	 * It returns a customised response in using a map collection.
	 * 
	 */

	@Override
	public Map<String, Boolean> deleteMovieDetails(Long movieId) throws MovieNotFoundException {
		
		Movie movie = movieRepository.findById(movieId)
				.orElseThrow(() -> new MovieNotFoundException("Movie not found :: " +movieId));
		
		movieRepository.delete(movie);
		 Map<String, Boolean> response = new HashMap<>();
		 response.put("Movie deleted", Boolean.TRUE);
		 return response;
		
	}
	
		
}

///---------------------------------------------Class Body Ends-------------------------------------------///

