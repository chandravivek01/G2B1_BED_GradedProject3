package com.g2b1.ticketTracker.service;

import java.util.List;

import com.g2b1.ticketTracker.entity.Ticket;

public interface TicketService {

	List<Ticket> searchTickets(String query);

	public Ticket viewticketById(long id);

	public List<Ticket> viewAlltickets();

	public void saveTicket(Ticket ticket);

	public void updateTicket(Ticket ticket);

	public void removeticketById(long id);

	List<Ticket> removeTimestamp(List<Ticket> tickets);

}
