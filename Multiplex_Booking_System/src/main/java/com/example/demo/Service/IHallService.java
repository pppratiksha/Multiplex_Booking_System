package com.example.demo.Service;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.Hall;
import com.example.demo.exceptions.HallAlreadyExistException;
import com.example.demo.exceptions.HallNotFoundException;
import com.example.demo.exceptions.MovieNotFoundException;
import com.example.demo.exceptions.ShowAlreadyExistException;
import com.example.demo.exceptions.ShowNotFoundException;


public interface IHallService {
	
	public Hall addHallDetails(Hall hall) throws HallAlreadyExistException;
	
	public Hall updateHallDetails(Hall hall) throws HallNotFoundException;
	
	public void deleteHallDetails(Long hallid) throws HallNotFoundException;
	
	public List<Hall> getAllHalls();
	
	
	
	public ResponseEntity<Hall> viewHallDetails(Long hallid) throws HallNotFoundException;
	
	public ResponseEntity<?> deleteHallDetailsById(Long showid, Long hallid) throws ShowNotFoundException
	,MovieNotFoundException,HallNotFoundException;	
	
	public Hall updateHallDetailsById(Long movieid, Long showid, Long Halllid, Hall hall) throws ShowNotFoundException,
	MovieNotFoundException,HallNotFoundException;
	
	public Hall addHallDetailsById(Long showid, Hall hall) throws ShowAlreadyExistException,
	MovieNotFoundException, ShowNotFoundException;
	
	public List<Hall>findHallByShowId(Long showId)throws HallNotFoundException,ShowNotFoundException;
	
	public Hall subtractAvailableSeats(Long hallid,int count) throws HallNotFoundException;
	
	public Hall addAvailableSeats(Long hallid,int count) throws HallNotFoundException;

	
	
	
	

}

