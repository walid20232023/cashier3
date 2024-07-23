package org.openmrs.module.mycashier;

import javax.persistence.*;
import org.openmrs.User;

@Entity
@Table(name = "agent", schema = "cashier")
public class Agent extends User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "caisse")
	private Long caisse;

	// Constructor that initializes Agent using User's attributes
	public Agent() {
		super();
	}

	public Agent(User user) {
		super();
		this.setUserId(user.getUserId());
		this.setSystemId(user.getSystemId());
		this.setUsername(user.getUsername());
		this.setCreator(user.getCreator());
		this.setDateCreated(user.getDateCreated());
		this.setChangedBy(user.getChangedBy());
		this.setDateChanged(user.getDateChanged());
		this.setPerson(user.getPerson());
		this.setRetired(user.getRetired());
		this.setRetiredBy(user.getRetiredBy());
		this.setDateRetired(user.getDateRetired());
		this.setRetireReason(user.getRetireReason());
		this.setUuid(user.getUuid());

	}

	// Getters and Setters for id and caisse
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getCaisse() {
		return caisse;
	}

	public void setCaisse(Long caisse) {
		this.caisse = caisse;
	}
}
