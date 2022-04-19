package com.ibm.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Booking;
import com.ibm.entity.Seat;
import com.ibm.entity.Show;
import com.ibm.entity.Ticket;
import com.ibm.exception.BookingNotFoundException;
import com.ibm.repo.BookingRepository;
import com.ibm.repo.CustomerRepository;
import com.ibm.repo.MoviesRepository;
import com.ibm.repo.QueryClass;
import com.ibm.repo.ShowRepository;
import com.ibm.repo.TicketRepository;

/**
 * @author Satadru Banerjee
 * @version 1.0
 * @category BookingService
 *
 */
@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	MoviesRepository moviesRepository;
	@Autowired
	ShowRepository showRepository;
	@Autowired
	CustomerRepository custoRepository;
	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	QueryClass query;

	/**
	 * Add a booking
	 * 
	 * @param booking
	 * @param customerId
	 * @param showId
	 * @return booking
	 * @throws BookingNotFoundException
	 */
	@Override
	public Booking addBooking(Booking booking, Integer customerId, Integer showId) throws BookingNotFoundException {
		Show show = new Show();
		if (showId != null) {
			show = showRepository.findById(showId).get();
			show.setBooking(booking);
			booking.setShow(show);
		}
		bookingRepository.saveAndFlush(booking);
		showRepository.saveAndFlush(show);
		return bookingRepository.findById(booking.getTransactionId()).get();
	}

	@Override
	public List<Booking> viewBookingList() throws BookingNotFoundException {
		List<Booking> bk = bookingRepository.findAll();
		return bk;
	}

	/**
	 * Update booking
	 * 
	 * @param booking
	 * @return booking
	 * @throws BookingNotFoundException
	 */

	@Override
	public Booking updateBooking(Booking booking) throws BookingNotFoundException {
		bookingRepository.saveAndFlush(booking);
		return bookingRepository.getById(booking.getTransactionId());
	}

	/**
	 * Cancel a booking
	 * 
	 * @param bookingid
	 * @return booking
	 * @throws BookingNotFoundException
	 */

	@Override
	public Booking cancelBooking(int bookingid) throws BookingNotFoundException {
		Booking b = bookingRepository.getById(bookingid);
		bookingRepository.delete(b);
		return b;
	}

	/**
	 * Show all booking
	 * 
	 * @param movieid
	 * @return booking
	 * @throws BookingNotFoundException
	 */
	@Override
	public List<Booking> showAllBookings(int movieid) throws BookingNotFoundException {
		List<Booking> bk = query.getAllByMovieId(movieid);
		return bk;
	}

	@Override
	public Booking viewBooking(int bookingid) throws BookingNotFoundException {
		return bookingRepository.findById(bookingid).get();
	}

	/**
	 * Show all booking list
	 * 
	 * @param booking
	 * @return booking
	 * @throws BookingNotFoundException
	 */

	@Override
	public List<Booking> showAllBookings(LocalDate bookingdate) throws BookingNotFoundException {
		List<Booking> bkList = new ArrayList<>();
		for (Booking booking : bookingRepository.findAll()) {
			if (booking.getBookingDate() != null && booking.getBookingDate().isEqual(bookingdate)) {
				bkList.add(booking);
			}
		}
		if (bkList.size() == 0)
			throw new BookingNotFoundException("No bookings found");
		else {
			return bkList;
		}
	}

	/**
	 * Calculate the total cost
	 * 
	 * @param bookingid
	 * @return booking
	 */

	@Override
	public double calculateTotalCost(int bookingid) {
		List<Ticket> tickets = ticketRepository.findAll();
		Set<Seat> Seats = new HashSet<>();
		Seat seat = new Seat();
		for (Ticket ticket : tickets) {
			if (bookingid == ticket.getBooking().getTransactionId()) {
				Seats.addAll(ticket.getSeats());
			}
		}
		double amount = 0;
//		for (Seat seat : Seats) {
//			amount = amount + seat.getPrice();
//		}
		Ticket tick = new Ticket();
		int no = tick.getNoOfSeats();
		amount = no*(seat.getPrice());
		Booking booking = bookingRepository.getById(bookingid);
		booking.setTotalCost(amount);
		bookingRepository.saveAndFlush(booking);
		return amount;
	}

}
