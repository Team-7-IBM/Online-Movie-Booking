package com.ibm.service;

import java.util.List;

import com.ibm.entity.Theatre;
import com.ibm.exception.TheatreNotFoundException;

public interface TheatreService {

	public List<Theatre> getAllTheatres() throws TheatreNotFoundException;

	public Theatre findTheatres(int theatreId);

	public Theatre addTheatre(Theatre t) throws TheatreNotFoundException;

	public List<Theatre> updateTheatre(Theatre t);

	public List<Theatre> deleteTheatreById(int theatreId);

	public List<Theatre> findTheatresByMovie(Integer movieId) throws TheatreNotFoundException;

}
