package com.ibm.service;

import java.util.List;

import com.ibm.entity.Ticket;
import com.ibm.exception.TicketNotFoundException;

public interface TicketService {

	public Ticket addTicket(Ticket ticket, Integer bookingId) throws TicketNotFoundException;

	public Ticket findTicket(int ticketId);

	List<Ticket> viewTicketList() throws TicketNotFoundException;

}
