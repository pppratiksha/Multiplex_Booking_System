package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.SeatType;
import  com.example.demo.exceptions.HallNotFoundException;
import  com.example.demo.exceptions.SeatTypeAlreadyExistException;
import  com.example.demo.exceptions.SeatTypeNotFoundException;
import  com.example.demo.repository.HallRepository;
import  com.example.demo.repository.SeatTypeRepository;

@Service
public class SeatTypeService implements ISeatTypeService {

	public SeatTypeService() {
		
	}
	
	@Autowired
	private SeatTypeRepository seattypeRepository;
	@Autowired
	private HallRepository hallRepository;
	
	

	/*
	 * This method is used to view a specific seat type using seat type id.
	 * It uses the seat type id to identify the seat type which is to be viewed.
	 */
	@Override
	public ResponseEntity<SeatType> viewSeatTypeDetails(Long seattypeid) throws SeatTypeNotFoundException {
		SeatType seatType = seattypeRepository.findById(seattypeid)
				.orElseThrow(() -> new SeatTypeNotFoundException("SeatType with seat type id: "+seattypeid+" not found"));
		return ResponseEntity.ok().body(seatType);
	}

	
	/*
	 * This method is used to delete a seat type that is assigned to a hall.
	 * The seat type to be deleted is specified by the seat type id and the hall id of the hall to which it is related to.
	 */
	@Override
	public ResponseEntity<?> deleteSeatTypeDetails(Long hallid, Long seattypeid)
			throws HallNotFoundException, SeatTypeNotFoundException {
		if(!hallRepository.existsById(hallid)) {
			throw new HallNotFoundException("Hall with hall id: "+hallid+" not found");
		}
		return seattypeRepository.findByIdAndHallId(seattypeid, hallid)
				.map(seattype ->{
					seattypeRepository.delete(seattype);
					return ResponseEntity.ok().build();
				}).orElseThrow(
					() -> new SeatTypeNotFoundException
					("seattype not found with id " + seattypeid + "and hall id"+hallid));
		}
	
	
	
	
	/*
	 * This method is used to update the details of the seat type that is assigned to a hall.
	 * The hall to which seat type to be updated is related is identified using the hall id.
	 * The updated seat type is sent using the JSON object of hall type
	 */
	@Override
	public SeatType updateSeatTypeDetails(Long hallid, Long seattypeid, SeatType seattype)
			throws HallNotFoundException, SeatTypeNotFoundException {
		if(!hallRepository.existsById(hallid)) {
			throw new HallNotFoundException("hall id not found: "+ hallid);
		}
		return seattypeRepository.findById(seattypeid).map(updateseattype -> {
				updateseattype.setSeattypeid(seattype.getSeattypeid());
				updateseattype.setSeattypedesc(seattype.getSeattypedesc());
				updateseattype.setSeatcount(seattype.getSeatcount());
				updateseattype.setSeatfare(seattype.getSeatfare());
				return seattypeRepository.save(updateseattype);
		}).orElseThrow(() -> new SeatTypeNotFoundException("SeatType id not found: "+seattypeid));
	}
	
	
	
	/*
	 * This method is used to add the details of a specific seat type that is assigned to a particular hall id.
	 * It takes the object of Seat type as input and saves it in the DB.
	 * It returns the object of type Seat Type.
	 */
	@Override
	public SeatType addSeatTypeDetails(Long hallid, SeatType seattype)
			throws SeatTypeAlreadyExistException, HallNotFoundException {



		return hallRepository.findById(hallid).map(hall ->{
			seattype.setHall(hall);
			return seattypeRepository.save(seattype);
		}).orElseThrow(()-> new HallNotFoundException("Hall is Not Found: "+hallid));
		
	}
	
	
	/*
	 * This method is used to find the seat types related to a particular hall.
	 * It takes the hall id of the related hall as input.
	 * It returns a list of all the seat types related to a particular hall id. 
	 */
	@Override
	public List<SeatType> findByHallId(Long hallid) {
		
		return seattypeRepository.findByHallId(hallid);
	}

	@Override
	public Optional<SeatType> findByIdAndHallId(Long seattypeid, Long hallid) {
		
		return null;
	}
	
	/*
	 * This method is used to view all the seat types associated with all the halls.
	 */

	@Override
	public List<SeatType> getAllSeatTypes() {
		return seattypeRepository.findAll();

	}

}
