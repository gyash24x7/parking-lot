package dev.yashgupta.parkit.entity;

import javax.persistence.*;

@Entity
@Table
public class Floor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	private int id;

	@Column
	private int floorNumber;

	public Floor() { }

	public Floor( int floorNumber ) {
		this.floorNumber = floorNumber;
	}

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber( int floorNumber ) {
		this.floorNumber = floorNumber;
	}
}
