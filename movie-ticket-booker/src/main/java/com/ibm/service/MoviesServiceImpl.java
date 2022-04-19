package com.ibm.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Movie;
import com.ibm.entity.Show;
import com.ibm.exception.MovieNotFoundException;
import com.ibm.repo.MoviesRepository;
import com.ibm.repo.QueryClass;
import com.ibm.repo.ShowRepository;
import com.ibm.repo.TheatreRepository;

/**
 * @author Satadru Banerjee
 * @version 1.0
 * @category MovieService
 *
 */
@Service
public class MoviesServiceImpl implements MoviesService {

	@Autowired
	private MoviesRepository moviesrepository;
	@Autowired
	TheatreRepository theatreRepository;
	@Autowired
	ShowRepository showrepository;
	@Autowired
	QueryClass query;

	/**
	 * Add Movie object in database
	 * 
	 * @param movie
	 * @return movie
	 * @throws MovieNotFoundException
	 */

	@Override
	public Movie addMovie(Movie movie) throws MovieNotFoundException {
		if (movie != null) {
			if (moviesrepository.existsById(movie.getMovieId())) {
				throw new MovieNotFoundException("Movie with this id already exists");
			} else {
				/*
				 * String fileName = StringUtils.cleanPath(file.getOriginalFilename());
				 * if(fileName.contains("..")) { System.out.println("not a a valid file"); } try
				 * { movie.setMovieImage(Base64.getEncoder().encodeToString(file.getBytes())); }
				 * catch (IOException e) { e.printStackTrace(); }
				 */
				moviesrepository.saveAndFlush(movie);
			}
		}
		return movie;
	}

	/**
	 * Remove Movie object
	 *
	 * @param movieid
	 * @return movie
	 */

	@Override
	public Movie removeMovie(int movieid) throws MovieNotFoundException {
		Movie m = moviesrepository.findById(movieid).get();
		List<Show> shows = showrepository.findAll();
		for (Show show : shows) {
			if (show.getMovie() != null && show.getMovie().getMovieId() == movieid) {
				show.setMovie(null);
			}
		}
		moviesrepository.delete(m);
		return m;
	}

	/**
	 * Update Movie object
	 *
	 * @param movie
	 * @return movie
	 */

	@Override
	public Movie updateMovie(Movie movie) throws MovieNotFoundException {
		moviesrepository.saveAndFlush(movie);
		return moviesrepository.getById(movie.getMovieId());
	}

	/**
	 * Add movie to show
	 *
	 * @param movie
	 * @param showId
	 * @return movie
	 */

	@Override
	public Movie addMovieToShow(Movie movie, Integer showId) throws MovieNotFoundException {
		Show show = new Show();
		if (showId != null) {
			show = showrepository.getById(showId);
			movie.setShow(show);
		}
		moviesrepository.saveAndFlush(movie);
		return moviesrepository.getById(movie.getMovieId());
	}

	/**
	 * View movie by movie ID
	 *
	 * @param movieid
	 * @return movie
	 */

	@Override
	public Movie viewMovie(int movieid) throws MovieNotFoundException {
		return moviesrepository.findById(movieid).get();
	}

	/**
	 * View movie List
	 * 
	 * @return m1
	 * @throws MovieNotFoundException
	 */

	@Override
	public List<Movie> viewMovieList() throws MovieNotFoundException {
		List<Movie> ml = moviesrepository.findAll();
		// if (ml.size() == 0) throw new MovieNotFoundException("Movies dosen't exist");
		return ml;
	}

	/**
	 * View movie List by theatreid
	 *
	 * @param theatreid
	 * @return movies
	 */

	@Override
	public List<Movie> viewMovieList(int theatreid) {
		List<Movie> movies = new ArrayList<>();
		List<Show> shows = showrepository.findAll();
		Set<Integer> showIds = new HashSet<>();
		for (Show s : shows) {
			if (s.getTheatre().getTheatreId() == theatreid) {
				showIds.add(s.getShowId());
			}
		}
		for (Integer id : showIds) {
			movies.add(showrepository.getById(id).getMovie());
		}
		return movies;
	}

	/**
	 * View movie List by localDate
	 *
	 * @param date
	 * @return movielist
	 */

	@Override
	public List<Movie> viewMovieList(LocalDate date) {
		List<Movie> mvList = new ArrayList<>();
		for (Movie movie : moviesrepository.findAll()) {
			if (movie.getMovieDate() != null && movie.getMovieDate().isEqual(date)) {
				mvList.add(movie);
			}
		}
		return mvList;
	}

}
