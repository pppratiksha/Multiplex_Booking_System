package com.example.demo.Controller;

import java.util.List;

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
import com.example.demo.exceptions.HallAlreadyExistException;
import com.example.demo.exceptions.HallNotFoundException;
import com.example.demo.exceptions.MovieNotFoundException;
import com.example.demo.exceptions.ShowAlreadyExistException;
import com.example.demo.exceptions.ShowNotFoundException;
import com.example.demo.Service.IHallService;

@RestController
@RequestMapping("/AppV1")
public class HallController {

	public HallController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private IHallService hallService;
	
	
	
	@PostMapping("/addhalldetails")
	public Hall addHallDetails(@RequestBody Hall hall) throws HallAlreadyExistException {
		return hallService.addHallDetails(hall);
	}
	
	@GetMapping("/getallhalls")
	public List<Hall> getAllHalls() {
		return hallService.getAllHalls();
	}


	@DeleteMapping("/deletehalldetails/{hallid}")
	public void deleteHallDetails(@PathVariable("hallid") Long hallid) throws HallNotFoundException {
		hallService.deleteHallDetails(hallid);
	}

	@PutMapping("/updatehalldetails")
	public Hall updateHallDetails(@RequestBody Hall hall) throws HallNotFoundException {
		return hallService.updateHallDetails(hall);
	}
	
	@DeleteMapping("/show/{showid}/hall/{hallid}")
	public ResponseEntity<?> deleteHallDetailsById(@PathVariable("showid")Long showid,@PathVariable("hallid")Long hallid) throws HallNotFoundException, ShowNotFoundException, MovieNotFoundException {
		return hallService.deleteHallDetailsById(showid,hallid);
	}
	
	@PutMapping("/updatehalldetailsbyid/movie/{movieid}/show/{showid}/hall/{hallid}")
	public Hall updateHallDetailsById(@PathVariable("movieid")Long movieid,
			@PathVariable("showid")Long showid,@PathVariable("hallid")Long hallid,@RequestBody Hall hall) throws MovieNotFoundException 
	, ShowNotFoundException,HallNotFoundException{
		return hallService.updateHallDetailsById(movieid, showid, hallid,hall);
}
	
	@PostMapping("/show/{showid}/hall")
	public Hall addHallDetailsById
	(@PathVariable("showid")Long showid, @RequestBody Hall hall)
			throws MovieNotFoundException,ShowNotFoundException, ShowAlreadyExistException{
		
		return hallService.addHallDetailsById(showid,hall);
	}
	
	@GetMapping("/show/{showid}/hall")
	public List<Hall> findHallByShowId(@PathVariable("showid") Long showid) throws HallNotFoundException, ShowNotFoundException{
		return hallService.findHallByShowId(showid);
}
//	@GetMapping("/movie/{movieid}/show/{showid}/hall")
//	public List<Hall> findHallByMovieIdAndShowId(@PathVariable("movieid") Long movieid,@PathVariable("movieid") Long showid) throws HallNotFoundException, ShowNotFoundException, MovieNotFoundException{
//		return hallService.findByMovieIdAndShowId(movieid,showid);
//}
	@GetMapping("hall/{hallid}")
	public ResponseEntity<Hall> viewHallDetails(@PathVariable("hallid")Long hallid) throws HallNotFoundException{
		return hallService.viewHallDetails(hallid);
	}
}

//
//@GetMapping("/viewhalldetails/{hallid}")
//public Hall viewHallDetails(@PathVariable("hallid") Long hallid) throws HallNotFoundException {
//	return hallService.viewHallDetails(hallid);
//}