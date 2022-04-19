package com.ibm.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Movie;

public interface MoviesRepository extends JpaRepository<Movie, Integer> {

	List<Movie> getAllBymovieDate(LocalDate date);

}
