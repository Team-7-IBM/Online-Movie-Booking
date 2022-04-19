package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Screen;
import com.ibm.entity.Theatre;
import com.ibm.exception.ScreenNotFoundException;
import com.ibm.repo.ScreenRepository;
import com.ibm.repo.TheatreRepository;

/**
 * @author Mouli Roy
 * @version 1.0
 * @category ScreenService
 *
 */
@Service
public class ScreenServiceImpl implements ScreenService {

	@Autowired
	private ScreenRepository screenRepository;
	@Autowired
	private TheatreRepository theatreRepository;

	/**
	 * Add Screen object in database
	 *
	 * @param screen
	 * @return screen
	 * @throws ScreenNotFoundException
	 */

	@Override
	public Screen addScreen(Screen screen, Integer theatreId) throws ScreenNotFoundException {
		Theatre theatre = new Theatre();
		if (theatreId != null) {
			if (screenRepository.existsById(screen.getScreenId())) {
				throw new ScreenNotFoundException("Screen already exists");
			} else {
				theatre = theatreRepository.getById(theatreId);
				screen.setTheatre(theatre);
			}
			screenRepository.saveAndFlush(screen);
		}
		return screen;
	}

	/**
	 * View screen List
	 *
	 * @return screen
	 * @throws ScreenNotFoundException
	 */

	@Override
	public List<Screen> viewScreenList() throws ScreenNotFoundException {
		List<Screen> screen = screenRepository.findAll();
		if (screen.size() == 0)
			throw new ScreenNotFoundException("No screens found");
		return screen;
	}

	/**
	 * Update screen
	 * 
	 * @param screen
	 * @return screen
	 */

	@Override
	public Screen updateScreen(Screen screen, Integer theatreId) {
		Theatre theatre = new Theatre();
		if (theatreId != null) {
			theatre = theatreRepository.getById(theatreId);
			screen.setTheatre(theatre);
		}
		screenRepository.saveAndFlush(screen);
		return screen;
	}

	/**
	 * View screen by screenID
	 *
	 * @param screenId
	 * @return screen
	 * @throws ScreenNotFoundException
	 */

	@Override
	public Screen viewScreen(int screenId) throws ScreenNotFoundException {
		return screenRepository.findById(screenId).get();
	}

	/**
	 * Get theatre by screenID
	 *
	 * @param screenId
	 * @return theatre
	 * @throws ScreenNotFoundException
	 */

	@Override
	public Theatre getTheatre(int screenId) throws ScreenNotFoundException {
		Screen screen = screenRepository.findById(screenId).get();
		Theatre theatre = screen.getTheatre();
		return theatre;
	}

}
