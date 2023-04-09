package com.g2b1.ticketTracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.g2b1.ticketTracker.entity.Ticket;
import com.g2b1.ticketTracker.service.TicketService;

@Controller
@RequestMapping("/admin/tickets")
public class TicketController {

	// Refer to http://localhost/<PORT>/swagger-ui.html - for the API Documentation

	// GET - /admin/tickets - listTickets
	// POST - /admin/tickets/delete - deleteTicket
	// POST - /admin/tickets/edit/{id} - updateTicket
	// GET - /admin/tickets/newTicket - createTicket
	// POST - /admin/tickets/save - saveTicket
	// GET - /admin/tickets/search - searchTickets
	// POST - /admin/tickets/update/{id} - updateTicket
	// POST - /admin/tickets/view/{id} - viewTicket

	@Autowired
	private TicketService ticketService;

	// SEARCH a ticket by its 'title' and 'description'
	@GetMapping("/search")
	public String searchTickets(@RequestParam("query") String query, Model model) {

		List<Ticket> searchedTickets = ticketService.searchTickets(query);
		List<Ticket> searchedTicketsWithoutTimestamp = ticketService.removeTimestamp(searchedTickets);
		model.addAttribute("tickets", searchedTicketsWithoutTimestamp);
		return "tickets/list-tickets";
	}

	// Package the ticket object inside the Model
	@GetMapping("/newTicket")
	public String createticket(Model model) {

		Ticket ticket = new Ticket();
		model.addAttribute("ticket", ticket);
		return "tickets/ticket-form";
	}

	// READ (FETCH) all tickets
	@GetMapping("")
	public String listTickets(Model theModel) {

		List<Ticket> tickets = ticketService.viewAlltickets();
		List<Ticket> ticketsWithoutTimestamp = ticketService.removeTimestamp(tickets);
		theModel.addAttribute("tickets", ticketsWithoutTimestamp);
		return "tickets/list-tickets";
	}

	// CREATE a ticket
	@PostMapping("/save")
	public String saveTicket(@ModelAttribute("ticket") Ticket ticket) {

		ticketService.saveTicket(ticket);
		return "redirect:/admin/tickets";
	}

	// UPDATE a ticket by altering its attribute ('title', 'description', 'content')
	@PostMapping("/update/{id}")
	public String updateTicket(@PathVariable("id") long id, @ModelAttribute("ticket") Ticket ticket) {

		Ticket originalTicket = ticketService.viewticketById(id);
		ticket.setDateCreated(originalTicket.getDateCreated());
		ticketService.updateTicket(ticket);
		return "redirect:/admin/tickets";
	}

	// DELETE Tickets
	@PostMapping("/delete")
	public String deleteticket(@RequestParam("ticketId") long id) {

		ticketService.removeticketById(id);
		return "redirect:/admin/tickets";
	}

	// Unboxing the saved Ticket (Model) and Pre-Populating the form for UPDATE
	@PostMapping("/edit/{id}")
	public String updateTicket(@PathVariable("id") long id, Model model) {

		Ticket ticket = ticketService.viewticketById(id);
		model.addAttribute("ticket", ticket);
		return "tickets/update-ticket";
	}

	// VIEW a single ticket
	@PostMapping("/view/{id}")
	public String viewTicket(@PathVariable("id") long id, Model model) {

		Ticket ticket = ticketService.viewticketById(id);
		model.addAttribute("ticket", ticket);
		return "tickets/view-ticket";
	}

}
