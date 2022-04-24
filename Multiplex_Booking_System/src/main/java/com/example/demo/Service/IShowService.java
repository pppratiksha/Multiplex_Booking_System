
package com.example.demo.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.Show;
import com.example.demo.exceptions.MovieNotFoundException;
import com.example.demo.exceptions.ShowAlreadyExistException;
import com.example.demo.exceptions.ShowNotFoundException;

public interface IShowService {
	
	public ResponseEntity<Show> viewShowDetails(Long showid) throws ShowNotFoundException;
	
	public ResponseEntity<?> deleteShowDetails(Long movieid, Long showid) throws ShowNotFoundException,MovieNotFoundException;	
	
	public Show updateShowDetails(Long movieid, Long showid,Show show) throws ShowNotFoundException,MovieNotFoundException;
	
	public Show addShowDetails(Long movieid, Show show) throws ShowAlreadyExistException,MovieNotFoundException;
	
	List<Show> findByMovieId(Long movieid) throws MovieNotFoundException;
	
	Optional<Show> findByMovieIdAndShowId(Long movieId,Long showId);
	
	public List<Show> getAllShows();
	
	List<Show>findSlotByslotNo(long slotNo) throws ShowNotFoundException;
	
	List<Show>findShowByMovieName(String movieName) throws MovieNotFoundException;
	
	public List<Show> findByToDateContaining(String date) throws ShowNotFoundException;


}
