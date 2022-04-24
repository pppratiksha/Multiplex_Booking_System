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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Show;
import com.example.demo.exceptions.MovieNotFoundException;
import com.example.demo.exceptions.ShowAlreadyExistException;
import com.example.demo.exceptions.ShowNotFoundException;
import com.example.demo.Service.IShowService;



//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/AppV1")
public class ShowController {

	@Autowired
	private IShowService showService;
	
	
	
	

//@ApiOperation(value = "view courses by instructor",response = List.class)
@GetMapping("/movie/{movieid}/show")
public List<Show> getShowByMovie(@PathVariable("movieid") Long movieid) throws MovieNotFoundException{
	return showService.findByMovieId(movieid);
}

//@ApiOperation(value = "add courses to instructor",response = Course.class)
@PostMapping("/movie/{movieid}/show")
public Show addShowDetails
(@PathVariable("movieid")Long movieid,/*@ApiParam(value="Course object stores in the database ",required = true)*/ @RequestBody Show show)
		throws MovieNotFoundException,ShowNotFoundException, ShowAlreadyExistException{
	
	return showService.addShowDetails(movieid,show);
}

@PutMapping("/movie/{movieid}/show/{showid}")
public Show updateShowDetails(@PathVariable("movieid")Long movieid,
		@PathVariable("showid")Long showid,@RequestBody Show show) throws MovieNotFoundException 
, ShowNotFoundException{
	return showService.updateShowDetails(movieid, showid, show);
}



//@ApiOperation(value = "delete courses by instructor and course",response = ResponseEntity.class)
@DeleteMapping("/movie/{movieid}/show/{showid}")
public  ResponseEntity<?> deleteShowDetails(@PathVariable("movieid")Long movieid,@PathVariable("showid")Long showid) throws ShowNotFoundException, MovieNotFoundException {
	return showService.deleteShowDetails(movieid,showid);
	
}

@GetMapping("show/{showid}")
public ResponseEntity<Show> viewShowDetails(@PathVariable("showid")Long showid) throws ShowNotFoundException{
	return showService.viewShowDetails(showid);
}

@GetMapping("movie/movieName/{movieName}/show")
public List<Show> findShowByMovieName(@PathVariable("movieName")String moviename) throws MovieNotFoundException{
	return showService.findShowByMovieName(moviename);
}


@GetMapping("/findshowbydate")
public List<Show> findByDate(@RequestParam(name="date") String date) throws ShowNotFoundException{
	
	
	return showService.findByToDateContaining(date);
	
}

}
