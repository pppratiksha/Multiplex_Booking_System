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

import com.example.demo.model.SeatType;
import com.example.demo.exceptions.HallNotFoundException;
import com.example.demo.exceptions.SeatTypeAlreadyExistException;
import com.example.demo.exceptions.SeatTypeNotFoundException;
import com.example.demo.Service.ISeatTypeService;

@RestController
@RequestMapping("/AppV1")
public class SeatTypeController {

	public SeatTypeController() {
	}
	
	@Autowired
	private ISeatTypeService seattypeService;
	
	
	@GetMapping("seattype/{seattypeid}")
	public ResponseEntity<SeatType> viewSeatTypeDetails(@PathVariable("seattypeid")Long seattypeid) throws SeatTypeNotFoundException{
		return seattypeService.viewSeatTypeDetails(seattypeid);
	}
		
		@PostMapping("/hall/{hallid}/seattype")
		public SeatType addSeatTypeDetails
		(@PathVariable("hallid")Long hallid,/*@ApiParam(value="Course object stores in the database ",required = true)*/ @RequestBody SeatType seattype)
				throws SeatTypeNotFoundException,HallNotFoundException, SeatTypeAlreadyExistException{
			
			return seattypeService.addSeatTypeDetails(hallid,seattype);
		}
		
		@PutMapping("/hall/{hallid}/seattype/{seattypeid}")
		public SeatType updateSeatTypeDetails(@PathVariable("hallid")Long hallid,
				@PathVariable("seattypeid")Long seattypeid,@RequestBody SeatType seattype) throws HallNotFoundException 
		, SeatTypeNotFoundException{
			return seattypeService.updateSeatTypeDetails(hallid, seattypeid, seattype);
		}
		
		@DeleteMapping("/hall/{hallid}/seattype/{seattypeid}")
		public  ResponseEntity<?> deleteSeatTypeDetails(@PathVariable("hallid")Long hallid,@PathVariable("seattypeid")Long seattypeid) throws HallNotFoundException, SeatTypeNotFoundException {
			return seattypeService.deleteSeatTypeDetails(hallid,seattypeid);
			}
		@GetMapping("hall/{hallid}/seattype")
		public List<SeatType> findByHallId(@PathVariable("hallid")Long hallid) throws HallNotFoundException{
			return seattypeService.findByHallId(hallid);
		}
		
		@GetMapping("/getallseattypes")
		public List<SeatType> getAllSeatTypes() {
			return seattypeService.getAllSeatTypes();
		}
		
		
	}


