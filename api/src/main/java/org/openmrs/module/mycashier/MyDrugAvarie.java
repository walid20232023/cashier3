package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "my_drug_avarie")
public class MyDrugAvarie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "date_declaration")
	private LocalDateTime dateDeclaration;

	@Column(name = "quantite")
	private Integer quantite;

	@ManyToOne
	@JoinColumn(name = "entrepot_id")
	private Entrepot entrepot;

	@ManyToOne
	@JoinColumn(name = "agent_id")
	private Agent agent;

	@ManyToOne
	@JoinColumn(name = "my_drug_emballage_id")
	private MyDrugEmballage myDrugEmballage;

	@Column(name = "uuid", unique = true, nullable = false, length = 38, updatable = false)
	private String uuid = UUID.randomUUID().toString();

	@Column(name = "date_creation")
	private LocalDateTime dateCreation = LocalDateTime.now();

	// Default constructor
	public MyDrugAvarie() {}

	// Getters and Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDateDeclaration() {
		return dateDeclaration;
	}

	public void setDateDeclaration(LocalDateTime dateDeclaration) {
		this.dateDeclaration = dateDeclaration;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public Entrepot getEntrepot() {
		return entrepot;
	}

	public void setEntrepot(Entrepot entrepot) {
		this.entrepot = entrepot;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public MyDrugEmballage getMyDrugEmballage() {
		return myDrugEmballage;
	}

	public void setMyDrugEmballage(MyDrugEmballage myDrugEmballage) {
		this.myDrugEmballage = myDrugEmballage;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	@Override
	public String toString() {
		return "MyDrugAvarie{" +
				"id=" + id +
				", dateDeclaration=" + dateDeclaration +
				", quantite=" + quantite +
				", entrepot=" + entrepot +
				", agent=" + agent +
				", myDrugEmballage=" + myDrugEmballage +
				", uuid='" + uuid + '\'' +
				", dateCreation=" + dateCreation +
				'}';
	}
}
