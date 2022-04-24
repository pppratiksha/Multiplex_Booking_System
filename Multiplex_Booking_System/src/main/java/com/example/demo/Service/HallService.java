package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Hall;
import com.example.demo.exceptions.HallAlreadyExistException;
import com.example.demo.exceptions.HallNotFoundException;
import com.example.demo.exceptions.MovieNotFoundException;
import com.example.demo.exceptions.ShowAlreadyExistException;
import com.example.demo.exceptions.ShowNotFoundException;
import com.example.demo.repository.HallRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.ShowRepository;

@Service
public class HallService implements IHallService {
	
	@Autowired
	private HallRepository hallRepository;
	
	@Autowired
	private ShowRepository showRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	

	public HallService()  {
	}
	

	/*
	 * This method is used to add halls that are yet to be assigned any show.
	 * This method the JSON object of Hall as its input and stores the data in the database.
	 * It returns the Object of type Hall.
	 */
	@Override
	public Hall addHallDetails(Hall hall) throws HallAlreadyExistException{
		Optional<Hall> optionalHall =  hallRepository.findById(hall.getHallid());
        if(optionalHall.isPresent()) {
        	throw new HallAlreadyExistException("This hall already exists..!!");
        }
        return hallRepository.save(hall);
	}
	
	
	/*
	 * This method is used to update hall details of an existing hall is not assigned any show.
	 * This method the JSON object of Hall as its input and stores the data in the database.
	 * It returns the Object of type Hall.
	 */
	@Override
	public Hall updateHallDetails(Hall hall) throws HallNotFoundException {
		Optional<Hall> optionalHall =  hallRepository.findById(hall.getHallid());
        if(!(optionalHall.isPresent())) {
        	throw new HallNotFoundException("This hall does not exists..!! Please try again.");
        }
        return hallRepository.save(hall);
	}


	
	/*
	 * This method is used to delete an unassigned hall.
	 * This hall to be deleted is specified by passing the hall id as param.
	 */
	@Override
	public void deleteHallDetails(Long hallid) throws HallNotFoundException {
		Optional<Hall> optionalHall =  hallRepository.findById(hallid);
        if(!(optionalHall.isPresent())) {
        	throw new HallNotFoundException("The hall with hall id "+hallid+" does not exist. Please enter the correct hallid");  
        }
  		hallRepository.deleteById(hallid);
	}

	@Override
	public List<Hall> getAllHalls() {
		return hallRepository.findAll();

	}
	
		

	/*
	 * This method is used to delete a specific hall that has been assigned a show.
	 * The hall to be deleted is identified using the hall id and the show id to which the hall is related. 
	 */
	@Override
	public ResponseEntity<?> deleteHallDetailsById(Long showid, Long hallid)
			throws ShowNotFoundException, MovieNotFoundException, HallNotFoundException {
		 if(!showRepository.existsById(showid)) {
			throw new ShowNotFoundException("The show with show id"+showid+" not found..!! Please enter correct show id");
		}
		return hallRepository.findById(showid)
				.map(hall ->{
					hallRepository.delete(hall);
					return ResponseEntity.ok().build();
				}).orElseThrow(
					() -> new HallNotFoundException
					("Hall not found with id " + hallid + ", show id"+showid));
	}

	
	/*
	 * This method is used to update the details of the hall that has been assigned to a show.
	 * The show to be updated is specified by specifying the hall id of that particular hall, 
	 * the show id of the show to which the show is related
	 * and the movie id of the movie to which the show is related.
	 * It takes the to be updated information in the form of a JSON object on type Hall.
	 */
	@Override
	public Hall updateHallDetailsById(Long movieid, Long showid, Long Halllid, Hall hall)
			throws ShowNotFoundException, MovieNotFoundException, HallNotFoundException {
		if(!movieRepository.existsById(movieid)) {
			throw new MovieNotFoundException("The movie with movie id: "+movieid+" is not found..!! Please enter correct movieid");
		}
		else if(!showRepository.existsById(showid)) {
			throw new ShowNotFoundException("The show with showid id: "+showid+" is not found..!! Please enter correct movieid");
		}
			return hallRepository.findById(Halllid).map(updatehall -> {
				updatehall.setHallid(hall.getHallid());
				updatehall.setHalldesc(hall.getHalldesc());
				updatehall.setTotalcapacity(hall.getTotalcapacity());
				return hallRepository.save(updatehall);
		}).orElseThrow(() -> new HallNotFoundException("The hall with hall id: "+Halllid+" is not found..!!PLease enter correct hall id"));
		}
		
	

	/*
	 * This method is used to add the details of a hall that has been assigned to a show.
	 * The specific show for which the hall is to be assigned, is identified using the show id of the particular show.
	 * The method also takes an object of type hall.
	 * It stores the passed JSON object in the DB and return the same as a success result.
	 */
	@Override
	public Hall addHallDetailsById(Long showid, Hall hall) throws ShowAlreadyExistException, MovieNotFoundException, ShowNotFoundException {
			return showRepository.findById(showid).map(show ->{
			hall.setShow(show);
			return hallRepository.save(hall);
		}).orElseThrow(() -> new ShowNotFoundException("Show with show id: "+showid+" is not found..!! Please enter the correct show id."));
	}
	
	/*
	 * This method is used to find the hall using the show id. 
	 * This method is used to find the hall in which the show is running.
	 * This method accepts the show id of show to specify the show whose hall is to be searched for.
	 * This method returns a list of type hall.
	 */

	@Override
	public List<Hall> findHallByShowId(Long showId) throws HallNotFoundException, ShowNotFoundException {
		
		
		 if(!showRepository.existsById(showId)) {
			throw new ShowNotFoundException("Show with show id: "+showId+" is not found..!! Please enter the correct show id.");
		}
		return hallRepository.findByShowId(showId);
	}


	/*
	 * This method is used to find a specific hall specified with the hall id.
	 */
	@Override
	public ResponseEntity<Hall> viewHallDetails(Long hallid) throws HallNotFoundException {
		Hall hall = hallRepository.findById(hallid)
				.orElseThrow(() -> new HallNotFoundException("Hall with hall id: "+hallid+" is not found..!! "
						+ "Please add the correct hall id"));
		return ResponseEntity.ok().body(hall);
	}


	@Override
	public Hall subtractAvailableSeats(Long hallid,int count) throws HallNotFoundException {
		return hallRepository.findById(hallid).map(updatehall -> {
			updatehall.setAvailableSeats(updatehall.getAvailableSeats()-count);
			return hallRepository.save(updatehall);
	}).orElseThrow(() -> new HallNotFoundException("The hall with hall id: "+hallid+" is not found..!!PLease enter correct hall id"));

		
	}


	@Override
	public Hall addAvailableSeats(Long hallid, int count) throws HallNotFoundException {
		return hallRepository.findById(hallid).map(updatehall -> {
			updatehall.setAvailableSeats(updatehall.getAvailableSeats()+count);
			return hallRepository.save(updatehall);
	}).orElseThrow(() -> new HallNotFoundException("The hall with hall id: "+hallid+" is not found..!!PLease enter correct hall id"));

	}
	
	

}
