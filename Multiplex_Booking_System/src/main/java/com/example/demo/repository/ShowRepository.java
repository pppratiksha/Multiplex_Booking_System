package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Show;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
	List<Show>findByMovieId(Long Movieid);
	
	Optional<Show> findByIdAndMovieId(Long showid,Long movieid);
	
	List<Show>findByMovieMovieName(String movieName);
	
	List<Show>findByslotNo(long slotNo);
	
	//List<Show>findBytoDate(Calendar date);
	
	public List<Show> findByToDateContaining(String date);

	

}
