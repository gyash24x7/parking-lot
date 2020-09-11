package dev.yashgupta.parkit.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Building extends BaseEntity< Building > {

	// Fields

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;

	private String name;

	@OneToMany( cascade = CascadeType.ALL, mappedBy = "building", fetch = FetchType.EAGER )
	private List< Floor > floors;

	// Constructors

	public Building( String name ) {
		super( Building.class );
		this.name = name;
	}

	public Building() {
		super( Building.class );
	}

	// Getters and Setters

	public List< Floor > getFloors() {
		return floors;
	}

	public void setFloors( List< Floor > floors ) {
		this.floors = floors;
	}

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Building {" + "id=" + id + ", name='" + name + "'" + "}";
	}
}
