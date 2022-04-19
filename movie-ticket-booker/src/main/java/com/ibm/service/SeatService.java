package com.ibm.service;

import java.util.List;

import com.ibm.entity.Seat;
import com.ibm.exception.SeatNotFoundException;

public interface SeatService {

	public Seat addSeat(Seat seat) throws SeatNotFoundException;

	public List<Seat> viewSeatList() throws SeatNotFoundException;

	public Seat updateSeat(Seat seat);

	public Seat bookSeat(Seat seat);

	public Seat cancelSeatBooking(Seat seat);

	public Seat blockSeat(Seat seat); // not available for any booking

}
