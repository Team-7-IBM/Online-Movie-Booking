package com.ibm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
