package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Seat;
import com.ibm.exception.SeatNotFoundException;
import com.ibm.pojo.SeatStatus;
import com.ibm.repo.SeatRepository;

/**
 * @author Sayan Sarkar
 * @version 1.0
 * @category SeatService
 *
 */

@Service
public class SeatServiceImpl implements SeatService {

	@Autowired
	private SeatRepository seatRepository;

	/**
	 * Add seat object
	 * 
	 * @param seat
	 * @return seat
	 * @throws SeatNotFoundException
	 */
	@Override
	public Seat addSeat(Seat seat) throws SeatNotFoundException {
		if (seat != null) {
			if (seatRepository.existsById(seat.getSeatId())) {
				throw new SeatNotFoundException("Seat with this id already exists");
			} else {
				seatRepository.saveAndFlush(seat);
			}
		}
		return seatRepository.getById(seat.getSeatId());
	}

	/**
	 * View list of seat
	 * 
	 * @return Listseat
	 * @throws SeatNotFoundException
	 */

	@Override
	public List<Seat> viewSeatList() throws SeatNotFoundException {
		List<Seat> li = seatRepository.findAll();
		/*
		 * if (li.size() == 0) throw new SeatNotFoundException("No seats found");
		 */
		return li;
	}

	/**
	 * Update seat
	 * 
	 * @param seat
	 * @return seat
	 */

	@Override
	public Seat updateSeat(Seat seat) {
		return seatRepository.saveAndFlush(seat);
	}

	/**
	 * Book seat
	 * 
	 * @param seat
	 * @return seat
	 */

	@Override
	public Seat bookSeat(Seat seat) {
		seat.setStatus(SeatStatus.BOOKED);
		return seatRepository.saveAndFlush(seat);
	}

	/**
	 * Cancel seat booking
	 * 
	 * @param seat
	 * @return seat
	 */

	@Override
	public Seat cancelSeatBooking(Seat seat) {
		seat.setStatus(SeatStatus.CANCELLED);
		return seatRepository.saveAndFlush(seat);
	}

	/**
	 * Block seat
	 * 
	 * @param seat
	 * @return seat
	 */

	@Override
	public Seat blockSeat(Seat seat) {
		seat.setStatus(SeatStatus.BLOCKED);
		return seatRepository.saveAndFlush(seat);
	}

}
