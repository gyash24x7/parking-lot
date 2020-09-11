package dev.yashgupta.parkit.repository;

import dev.yashgupta.parkit.entity.Ticket;

public class TicketRepository extends BaseRepository< Ticket > {
	public TicketRepository() {
		super( Ticket.class );
	}

	public void printTicketDetails( Ticket ticket ) {
		System.out.println( "Ticket ID: " + ticket.getId() );
		System.out.println( "Remember TicketID for future reference!\n" );
	}
}
