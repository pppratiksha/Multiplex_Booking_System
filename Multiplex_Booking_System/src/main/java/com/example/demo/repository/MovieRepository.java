package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
	
	public Movie findBymovieName(String moviename);

}

