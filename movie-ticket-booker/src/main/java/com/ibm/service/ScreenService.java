package com.ibm.service;

import java.util.List;

import com.ibm.entity.Screen;
import com.ibm.entity.Theatre;
import com.ibm.exception.ScreenNotFoundException;

public interface ScreenService {

	public Screen addScreen(Screen screen, Integer theatreId) throws ScreenNotFoundException;

	public List<Screen> viewScreenList() throws ScreenNotFoundException;

	public Screen updateScreen(Screen s, Integer theatreId);

	public Screen viewScreen(int screenId) throws ScreenNotFoundException;

	public Theatre getTheatre(int screenId) throws ScreenNotFoundException;

}
