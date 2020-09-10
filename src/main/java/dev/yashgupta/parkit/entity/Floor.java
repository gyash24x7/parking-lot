package dev.yashgupta.parkit.entity;

import javax.persistence.*;

@Entity
@Table
public class Floor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	private int floorNumber;
}
