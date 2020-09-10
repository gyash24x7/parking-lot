package dev.yashgupta.parkit.entity;

import javax.persistence.*;

@Entity
@Table
public class Space {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	private int id;

	@Column
	private int spaceNumber;

	@Column
	private boolean isPremium;

	@Column
	private boolean isOccupied;

	public Space() { }

	public Space( int spaceNumber, boolean isPremium ) {
		this.spaceNumber = spaceNumber;
		this.isPremium = isPremium;
		this.isOccupied = false;
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

	public void setPremium( boolean premium ) {
		isPremium = premium;
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied( boolean occupied ) {
		isOccupied = occupied;
	}
}
