package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ligne_approvis")
public class LigneApprovis {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	//Les trois attributs suivants constituent la clé de cette classe
	@ManyToOne
	@JoinColumn(name = "approvisionnement_id", nullable = false)
	private Approvisionnement approvisionnement;
	
	@ManyToOne
	@JoinColumn(name = "my_drug_emballage_id", nullable = false)
	private MyDrugEmballage myDrugEmballage;
	
	@Column(name = "numero_lot", length = 100)
	private String numeroLot;
	
	// Fin Clé
	
	@Column(name = "quantite")
	private Integer quantite;
	
	@Column(name = "date_peremption", nullable = false)
	private LocalDate datePeremption;
	
	// Constructeurs
	
	public LigneApprovis() {
	}
	
	// Getters and Setters
	
	public Approvisionnement getApprovisionnement() {
		return approvisionnement;
	}
	
	public void setApprovisionnement(Approvisionnement approvisionnement) {
		this.approvisionnement = approvisionnement;
	}
	
	public MyDrugEmballage getMyDrugEmballage() {
		return myDrugEmballage;
	}
	
	public void setMyDrugEmballage(MyDrugEmballage myDrugEmballage) {
		this.myDrugEmballage = myDrugEmballage;
	}
	
	public Integer getQuantite() {
		return quantite;
	}
	
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
	
	public LocalDate getDatePeremption() {
		return datePeremption;
	}
	
	public void setDatePeremption(LocalDate datePeremption) {
		this.datePeremption = datePeremption;
	}
	
	public String getNumeroLot() {
		return numeroLot;
	}
	
	public void setNumeroLot(String numeroLot) {
		this.numeroLot = numeroLot;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
}
