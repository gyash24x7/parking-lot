package dev.yashgupta.parkit.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Ticket {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;

	private String vehicleNumber;

	@CreationTimestamp
	private Timestamp generatedTime;

	@ManyToOne
	@JoinColumn
	private Space space;

	public Ticket() { }

	public Ticket( String vehicleNumber ) {
		this.vehicleNumber = vehicleNumber;
	}

	public Space getSpace() {
		return space;
	}

	public void setSpace( Space space ) {
		this.space = space;
	}

	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber( String vehicleNumber ) {
		this.vehicleNumber = vehicleNumber;
	}

	public Timestamp getGeneratedTime() {
		return generatedTime;
	}

	public void setGeneratedTime( Timestamp generatedTime ) {
		this.generatedTime = generatedTime;
	}
}
