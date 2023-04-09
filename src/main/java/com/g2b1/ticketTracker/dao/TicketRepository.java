package com.g2b1.ticketTracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.g2b1.ticketTracker.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	// JPQL - SEARCH a ticket by its 'title' and 'description'
	@Query("SELECT t FROM Ticket t WHERE t.title LIKE CONCAT('%', :query, '%')"
			+ " Or t.description LIKE CONCAT('%', :query, '%')")
	List<Ticket> searchTickets(String query);
}
