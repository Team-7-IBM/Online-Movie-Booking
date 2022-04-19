package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.entity.Booking;
import com.ibm.entity.Ticket;
import com.ibm.exception.TicketNotFoundException;
import com.ibm.repo.BookingRepository;
//import com.ibm.repo.SeatRepository;
import com.ibm.repo.TicketRepository;

/**
 * @author Sandeep Sharma
 * @version 1.0
 * @category Ticket
 *
 */
@Service
public class TicketServiceImpl implements TicketService {

	private TicketRepository ticketRepository;

	public TicketServiceImpl(TicketRepository ticketRepository) {
		this.ticketRepository = ticketRepository;
	}

	@Autowired
	private BookingRepository bookingRepository;

	/**
	 * Add ticket object
	 * 
	 * @param ticket
	 * @param transactionId
	 * @return ticket
	 * @throws TicketNotFoundException
	 */
	@Override
	public Ticket addTicket(Ticket ticket, Integer transactionId) throws TicketNotFoundException {
		Booking booking = new Booking();
		if (transactionId != null) {
			booking = bookingRepository.findById(transactionId).get();
			booking.setTransactionStatus("Completed");
			ticket.setBooking(booking);
		}
		ticketRepository.saveAndFlush(ticket);
		return ticket;
	}

	/**
	 * Find ticket by id
	 * 
	 * @param ticketId
	 * @return ticket
	 */

	@Override
	public Ticket findTicket(int ticketId) {
		return ticketRepository.findById(ticketId).get();
	}

	/**
	 * List of ticket
	 * 
	 * @return ticket
	 * @throws TicketNotFoundException
	 */
	@Override
	public List<Ticket> viewTicketList() throws TicketNotFoundException {
		List<Ticket> ti = ticketRepository.findAll();
		if (ti.size() == 0)
			throw new TicketNotFoundException("No tickets are avaliable");
		return ti;
	}

}
