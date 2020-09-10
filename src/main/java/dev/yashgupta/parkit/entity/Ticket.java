package dev.yashgupta.parkit.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	private int id;

	@Column
	private String vehicleNumber;

	@Column
	@CreationTimestamp
	private Timestamp generatedTime;

	public Ticket() { }

	public Ticket( String vehicleNumber ) {
		this.vehicleNumber = vehicleNumber;
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
