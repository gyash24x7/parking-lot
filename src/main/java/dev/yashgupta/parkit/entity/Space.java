package dev.yashgupta.parkit.entity;

import javax.persistence.*;

@Entity
@Table
public class Space {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	private int spaceNumber;

	@Column
	private boolean isPremium;

	@Column
	private boolean isOccupied;
}
