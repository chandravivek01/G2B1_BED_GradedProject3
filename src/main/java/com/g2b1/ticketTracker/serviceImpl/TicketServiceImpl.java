package com.g2b1.ticketTracker.serviceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.g2b1.ticketTracker.dao.TicketRepository;
import com.g2b1.ticketTracker.entity.Ticket;
import com.g2b1.ticketTracker.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private TicketRepository ticketRepository;

	// Fetch List of All-Such Tickets meeting the Search-Query
	@Override
	public List<Ticket> searchTickets(String query) {

		List<Ticket> tickets = ticketRepository.searchTickets(query);
		return tickets;
	}

	// Fetch a Ticket By ID from the Database
	@Override
	public Ticket viewticketById(long id) {

		Ticket ticket = ticketRepository.findById(id).get();

		if (ticket == null)
			throw new RuntimeException("Did not find the ticket id: " + id);
		else
			return ticket;

	}

	// Fetch All Tickets from the Database
	@Override
	public List<Ticket> viewAlltickets() {
		return ticketRepository.findAll();
	}

	// Saving a Ticket for the First Time with Created Date and Updated Date same as
	// the Current Timestamp
	@Override
	public void saveTicket(Ticket ticket) {

		String timestamp = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date());

		ticket.setDateCreated(timestamp);
		ticket.setDateUpdated(timestamp);

		ticketRepository.save(ticket);
	}

	// Saving a Ticket with Updated Date equals to the Current Timestamp of Updation
	// (with Created Date, the same as the Time of Creation)
	@Override
	public void updateTicket(Ticket ticket) {

		String timestamp = new SimpleDateFormat("dd-MM-yyyy HH.mm.ss").format(new Date());

		ticket.setDateUpdated(timestamp);
		ticketRepository.save(ticket);
	}

	// Delete a Ticket By ID from the Database
	@Override
	public void removeticketById(long id) {
		ticketRepository.deleteById(id);
	}

	// Accepts List of Tickets with Timestamp and return a List of Tickets by
	// chopping-off Timestamp of 'Date Creation'
	@Override
	public List<Ticket> removeTimestamp(List<Ticket> tickets) {

		for (Ticket ticket : tickets) {
			String timeStamp = ticket.getDateCreated();
			String[] string = timeStamp.split(" ");
			String[] s = string[0].split("-");
			String resultDate = s[0] + " " + s[1] + " " + s[2];
			ticket.setDateCreated(resultDate);
		}

		return tickets;
	}

}
