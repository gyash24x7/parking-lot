package dev.yashgupta.parkit.repository;

import dev.yashgupta.parkit.entity.Space;
import dev.yashgupta.parkit.entity.Ticket;

import java.util.Scanner;

public class SpaceRepository extends BaseRepository< Space > {
	static Scanner sc = new Scanner( System.in );

	public SpaceRepository() {
		super( Space.class );
	}

	public void occupySpace( Space space ) {
		TicketRepository ticketRepository = new TicketRepository();
		System.out.println( "Enter your vehicle number: " );
		String vehicleNumber = sc.nextLine();

		Ticket ticket = new Ticket( vehicleNumber );
		ticket.setSpace( space );
		ticketRepository.save( ticket );
		ticketRepository.printTicketDetails( ticket );

		space.setOccupied( true );
		this.save( space );
	}

	public void vacateSpace( Space space ) {
		space.setOccupied( false );
		this.save( space );
	}

}
