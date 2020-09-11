package dev.yashgupta.parkit.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Space {

	// Fields

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;

	private int spaceNumber;

	private boolean isPremium;

	private boolean isOccupied;

	@ManyToOne
	@JoinColumn
	private Floor floor;

	@OneToMany( cascade = CascadeType.ALL, mappedBy = "space" )
	private List< Ticket > tickets;

	// Constructors

	public Space() { }

	public Space( int spaceNumber, boolean isPremium ) {
		this.spaceNumber = spaceNumber;
		this.isPremium = isPremium;
		this.isOccupied = false;
	}

	// Getters and Setters

	public List< Ticket > getTickets() {
		return tickets;
	}

	public void setTickets( List< Ticket > tickets ) {
		this.tickets = tickets;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor( Floor floor ) {
		this.floor = floor;
	}

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public int getSpaceNumber() {
		return spaceNumber;
	}

	public void setSpaceNumber( int spaceNumber ) {
		this.spaceNumber = spaceNumber;
	}

	public boolean isPremium() {
		return isPremium;
	}

	public boolean isNotPremium() {
		return !isPremium;
	}

	public void setPremium( boolean premium ) {
		isPremium = premium;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public boolean isVacant() {
		return !isOccupied;
	}

	public void setOccupied( boolean occupied ) {
		isOccupied = occupied;
	}
}
