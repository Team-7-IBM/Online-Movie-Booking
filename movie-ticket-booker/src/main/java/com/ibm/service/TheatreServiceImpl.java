package com.ibm.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Movie;
import com.ibm.entity.Show;
import com.ibm.entity.Theatre;
import com.ibm.exception.TheatreNotFoundException;
import com.ibm.repo.MoviesRepository;
import com.ibm.repo.ScreenRepository;
import com.ibm.repo.TheatreRepository;

@Service
public class TheatreServiceImpl implements TheatreService {

	@Autowired
	private TheatreRepository theatrerepository;
	@Autowired
	ScreenRepository screenRepository;
	@Autowired
	private MoviesRepository moviesrepository;

	@Override
	public List<Theatre> getAllTheatres() throws TheatreNotFoundException {
		List<Theatre> the = theatrerepository.findAll();
		return the;
	}

	@Override
	public Theatre findTheatres(int theatreId) {
		if (theatrerepository.findById(theatreId).isPresent()) {
			return theatrerepository.findById(theatreId).get();
		} else
			return null;
	}

	@Override
	public Theatre addTheatre(Theatre m) throws TheatreNotFoundException {
		if (m != null) {
			if (theatrerepository.existsById(m.getTheatreId())) {
				throw new TheatreNotFoundException("Theatre already exists");
			} else {
				theatrerepository.saveAndFlush(m);
			}
		}
		return m;
	}

	@Override
	public List<Theatre> updateTheatre(Theatre m) {
		theatrerepository.saveAndFlush(m);
		return theatrerepository.findAll();
	}

	@Override
	public List<Theatre> deleteTheatreById(int theatreId) {
		theatrerepository.deleteById(theatreId);
		return theatrerepository.findAll();
	}

	@Override
	public List<Theatre> findTheatresByMovie(Integer movieId) throws TheatreNotFoundException {
		List<Theatre> theatreList = new ArrayList<>();
		Movie movie = moviesrepository.findById(movieId).get();
		Integer showwID = movie.getShow().getShowId();
		List<Theatre> theatres = theatrerepository.findAll();
		for (Theatre theatre : theatres) {
			List<Show> shows = theatre.getShow();
			for (Show show : shows) {
				if (show.getShowId() == showwID) {
					theatreList.add(theatre);
				}
			}
		}
		return theatreList;
	}

}
