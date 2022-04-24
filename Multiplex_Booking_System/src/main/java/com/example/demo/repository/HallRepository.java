package com.example.demo.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Hall;

@Repository
public interface HallRepository extends JpaRepository<Hall,Long> {
	List<Hall>findByShowId(Long showid);
	//List<Hall>findByMovieIdAndShowId(Long movieid,Long showid);
	Optional<Hall>findByIdAndShowId(Long hallid, Long showid);

	

}