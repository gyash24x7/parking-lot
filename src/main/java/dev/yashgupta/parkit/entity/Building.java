package dev.yashgupta.parkit.entity;

import javax.persistence.*;

@Entity
@Table
public class Building {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	private int id;

	@Column
	private String name;

	public Building( String name ) {
		this.name = name;
	}

	public Building() { }

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
