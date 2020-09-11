package dev.yashgupta.parkit.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Floor {

	// Fields

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;

	private int floorNumber;

	@ManyToOne
	@JoinColumn
	private Building building;

	@OneToMany( cascade = CascadeType.ALL, mappedBy = "floor", fetch = FetchType.EAGER )
	private List< Space > spaces;

	// Constructors

	public Floor( int floorNumber ) {
		this.floorNumber = floorNumber;
	}

	public Floor() { }

	public List< Space > getSpaces() {
		return spaces;
	}

	// Getters and Setters

	public void setSpaces( List< Space > spaces ) {
		this.spaces = spaces;
	}

	public Building getBuilding() {
		return building;
	}

	public void setBuilding( Building building ) {
		this.building = building;
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

	@Override
	public String toString() {
		return "Floor{id=" + id + ", floorNumber=" + floorNumber + "}";
	}
}
