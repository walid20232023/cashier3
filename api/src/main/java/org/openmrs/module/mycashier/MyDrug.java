package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "my_drug")
public class MyDrug {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	

	
	@Column(name = "name")
	private String name;
	
	@Column(name = "cip")
	private String cip;
	
	@Column(name = "groupe_therap")
	private String groupeTherap;

	@Column(name = "forme")
	private String forme;
	
	@Column(name = "dci")
	private String dci;
	
	@Column(name = "date_creation")
	private LocalDateTime dateCreated = LocalDateTime.now();
	
	@Column(name = "uuid", unique = true, nullable = false, length = 38, updatable = false)
	private String uuid = UUID.randomUUID().toString();
	
	@Column(name = "drug_id")
	private Integer drugId;
	
	public MyDrug() {
	}
	
	public String getCip() {
		return cip;
	}
	
	public String getGroupeTherap() {
		return groupeTherap;
	}
	
	public void setCip(String cip) {
		this.cip = cip;
	}
	
	public void setGroupeTherap(String groupeTherap) {
		this.groupeTherap = groupeTherap;
	}
	
	public void setDci(String dci) {
		this.dci = dci;
	}
	
	public String getDci() {
		return dci;
	}
	
	// Getters and setters for all fields
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public Integer getDrugId() {
		return drugId;
	}
	
	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
	}

	public String getForme() {
		return forme;
	}

	public void setForme(String forme) {
		this.forme = forme;
	}
}
