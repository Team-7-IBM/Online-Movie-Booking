package com.ibm.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Screen;
import com.ibm.entity.Show;
import com.ibm.entity.Theatre;
import com.ibm.repo.ScreenRepository;
import com.ibm.repo.ShowRepository;
import com.ibm.repo.TheatreRepository;

/**
 * @author Sayan Sarkar
 * @version 1.0
 * @category Show
 *
 */
@Service
public class ShowServiceImpl implements ShowService {
	@Autowired
	private ShowRepository showrepository;
	@Autowired
	private TheatreRepository theatreRepository;
	@Autowired
	private ScreenRepository screenRepository;

	/**
	 * Add show object
	 * 
	 * @param show
	 * @param screenId
	 * @return show
	 */
	@Override
	public Show addShow(Show show, Integer theatreId, Integer screenId) {
		Theatre theatre = new Theatre();
		Screen screen = new Screen();
		if (theatreId != null) {
			theatre = theatreRepository.getById(theatreId);
			show.setTheatre(theatre);
		}
		if (screenId != null) {
			screen = screenRepository.getById(screenId);
			show.setScreen(screen);
		}
		showrepository.saveAndFlush(show);
		return show;
	}

	/**
	 * Update show object
	 * 
	 * @param show
	 * @param screenId
	 * @return show
	 */

	@Override
	public Show updateShow(Show show, Integer theatreId, Integer screenId) {
		Theatre theatre = new Theatre();
		Screen screen = new Screen();
		if (theatreId != null) {
			theatre = theatreRepository.getById(theatreId);
			show.setTheatre(theatre);
		}
		if (screenId != null) {
			screen = screenRepository.getById(screenId);
			show.setScreen(screen);
		}
		showrepository.saveAndFlush(show);
		return show;
	}

	/**
	 * Remove show object
	 * 
	 * @param showid
	 * @return show
	 */

	@Override
	public Show removeShow(int showid) {
		Show s = showrepository.findById(showid).get();
		showrepository.delete(s);
		return s;
	}

	/**
	 * View show list
	 * 
	 * @param showid
	 * @return show
	 */

	@Override
	public Show viewShow(int showid) {
		return showrepository.findById(showid).get();
	}

	/**
	 * List of shows
	 * 
	 * @return show
	 */

	@Override
	public List<Show> viewAllShows() {
		return showrepository.findAll();
	}

	/**
	 * List of shows
	 * 
	 * @param theatreid
	 * @return show
	 */

	@Override
	public List<Show> viewShowList(int theatreid) {
		return showrepository.getAllByTheatreId(theatreid);
	}

	/**
	 * List of shows by localDate
	 * 
	 * @param date
	 * @return show
	 */
	@Override
	public List<Show> viewShowList(LocalDate date) {
		List<Show> shList = new ArrayList<>();
		for (Show show : showrepository.findAll()) {
			if (show.getShowDate() != null && show.getShowDate().isEqual(date)) {
				shList.add(show);
			}
		}
		return shList;
	}

}
