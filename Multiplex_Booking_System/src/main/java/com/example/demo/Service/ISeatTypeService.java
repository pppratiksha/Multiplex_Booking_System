package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.SeatType;
import com.example.demo.exceptions.HallNotFoundException;
import com.example.demo.exceptions.SeatTypeAlreadyExistException;
import com.example.demo.exceptions.SeatTypeNotFoundException;

public interface ISeatTypeService {
		
	public ResponseEntity<SeatType> viewSeatTypeDetails(Long seattypeid) throws SeatTypeNotFoundException;
	
	public ResponseEntity<?> deleteSeatTypeDetails(Long hallid, Long seattypeid) throws HallNotFoundException,
	SeatTypeNotFoundException;	
	
	public SeatType updateSeatTypeDetails(Long hallid, Long seattypeid,SeatType seattype) throws HallNotFoundException,
	SeatTypeNotFoundException;
	
	public SeatType addSeatTypeDetails(Long hallid, SeatType seattype) throws SeatTypeAlreadyExistException,
	HallNotFoundException;
	
	List<SeatType> findByHallId(Long hallid);
	
	Optional<SeatType> findByIdAndHallId(Long seattypeid,Long hallid);
	
	public List<SeatType> getAllSeatTypes();
	
	
	

}
