package com.g2b1.ticketTracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Application Starts Here !!
@SpringBootApplication
public class TicketTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketTrackerApplication.class, args);
		System.out.println("\n\nWelcome to the Ticket Tracker Application");
		System.out.println("\nHit localhost:<PORT> on your Web-Browser");
	}

}
