package com.example.demo.Service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.model.Show;
import com.example.demo.exceptions.MovieNotFoundException;
import com.example.demo.exceptions.ShowAlreadyExistException;
import com.example.demo.exceptions.ShowNotFoundException;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.ShowRepository;


@Service
public class ShowService implements IShowService{
	
	@Autowired
	private ShowRepository showRepository;
	
	@Autowired
	private MovieRepository movieRepository;

	public ShowService() {
		
	}
	
	
	/*
	 * 
	 * This method is used to add the show details for a specific movie. A movie can have many shows related to it 
	 * so the movieid first specified whose show is to be added. After that the Show details are specified as a JSON object.
	 * The return type is the object of Show entity
	 */
	@Override
	public Show addShowDetails(Long movieid, Show show) throws ShowAlreadyExistException,MovieNotFoundException {
		
		List<Show> shows =showRepository.findAll();
		for(Show i : shows) {
			if(i.getSlotNo()==show.getSlotNo()) {
				throw new ShowAlreadyExistException("Show already exists...");
			}
		}
			return movieRepository.findById(movieid).map(movie ->{
			show.setMovie(movie);
			return showRepository.save(show);
		}).orElseThrow(() -> new MovieNotFoundException("Movie not found"));
	}

	
	/*
	 * 
	 * This method is used to update the show details that are present for a specific movie.
	 * The show which is to be updated is identified by the movie id to which it is linked and the show id of the show.
	 * Then the updated information is passed as a JSON object and the return type is the show object
	 * 
	 */
	@Override
	public Show updateShowDetails(Long movieid, Long showid,Show show) throws ShowNotFoundException,MovieNotFoundException {
		if(!movieRepository.existsById(movieid)) {
			throw new MovieNotFoundException("movie with id: "+movieid+" not found..!!");
		}
		return showRepository.findById(showid).map(updateshow -> {
				updateshow.setSlotNo(show.getSlotNo());
				updateshow.setFromDate(show.getFromDate());
				updateshow.setToDate(show.getToDate());
				return showRepository.save(updateshow);
		}).orElseThrow(() -> new ShowNotFoundException("Showid id not found"));

	}
	
	
	/*
	 * 
	 * This method is used to view the details of all the shows related to a specific movie.
	 * The movie is specified by passing its movieid.
	 * The return type is a responseEntity of type Show.
	 * 
	 */
	@Override
	public ResponseEntity<Show> viewShowDetails(Long showid) throws ShowNotFoundException {        
        Show show = showRepository.findById(showid)
				.orElseThrow(() -> new ShowNotFoundException("Show not found :: " +showid));
		return ResponseEntity.ok().body(show);
	}
	
	
	/* 
	 * This method is used to delete the a particular show  related to a particular movie.
	 * The movieid is specified by passing the movieid of the movie.
	 * The show which is to be deleted is specified by passing the show id. 
	 */

	@Override
	public ResponseEntity<?> deleteShowDetails(Long movieid, Long showid) throws ShowNotFoundException,MovieNotFoundException {
		if(!movieRepository.existsById(movieid)) {
			throw new MovieNotFoundException("movie with id: "+movieid+" not found..!!");
		}
		return showRepository.findByIdAndMovieId(showid, movieid)
				.map(show ->{
					showRepository.delete(show);
					return ResponseEntity.ok().build();
				}).orElseThrow(
					() -> new ShowNotFoundException
					("show not found with id " + showid + "and movie id"+movieid));
		}

	
	/*
	 * This method is used to get all the shows related to all the movies present in the database.
	 * This method returns the list of type Show. 
	 */
	@Override
	public List<Show> getAllShows() {
		return showRepository.findAll();
	}
	

	/*
	 * This method is used to find the slots related to a specific movie.
	 * This movie is specified using its movieid.
	 * This method returns a list of type Show.
	 */
	@Override
	public List<Show> findByMovieId(Long movieid) throws MovieNotFoundException {
		if(!movieRepository.existsById(movieid)) {
			throw new MovieNotFoundException("movie with id: "+movieid+" not found..!!");
		}
		return showRepository.findByMovieId(movieid);
	}
	
	/* 
	 * This method is used the find a specific show related to a specific movie.
	 * The show and movie are specified by showid and movieid, respectively.
	 * It returns a Optional collection of type Show.
	 */

	@Override
	public Optional<Show> findByMovieIdAndShowId(Long movieId, Long showId) {
		return null;
	}

	/* 
	 * This method is used to find all the shows for a specific movie using the moviename.
	 * This method return a list of type show.
	 */
	@Override
	public List<Show> findShowByMovieName(String movieName) throws MovieNotFoundException {
		if(movieRepository.findBymovieName(movieName) != null) {
			throw new MovieNotFoundException("movie with name: "+movieName+" not found..!!");
		}
		return showRepository.findByMovieMovieName(movieName);
	}
	

	/*
	 * This method is used to find the show having show a specific slot number.
	 * The slot number is passed to match with the slot numbers of every show present in the DB.
	 * It returns the matched show. 
	 */
	@Override
	public List<Show> findSlotByslotNo(long slotNo) throws ShowNotFoundException {
		List<Show> lists = showRepository.findAll();
		for(Show i : lists) {
			if(i.getSlotNo()==slotNo) {
				return showRepository.findByslotNo(slotNo);
			}
		}
		
			throw new ShowNotFoundException("The show with Slot number: "+slotNo+" is not found");
	}

	
	/*
	 *This method is used to find the shows that are present on a particular date.
	 *It accepts the date and returns the list of shows present on that date. 
	 */
	@Override
	public List<Show> findByToDateContaining(String date) throws ShowNotFoundException{
		if(date.length()==0) {
			throw new ShowNotFoundException("The date entered by you is not in the correct format please try again..!!");
		}
		List<Show> list = showRepository.findByToDateContaining(date);
		if(list.isEmpty()) {
			throw new ShowNotFoundException("Sorry! No Show(s) available for this date. Please retry for another date.");
		}
		return list;
		
	}
}
