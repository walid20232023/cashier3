package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "stock_entrepot")
public class StockEntrepot implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "my_drug_emballage_id", nullable = false)
	private MyDrugEmballage myDrugEmballage;
	
	@ManyToOne
	@JoinColumn(name = "entrepot_id", nullable = false)
	private Entrepot entrepot;
	
	@Column(name = "date_modification")
	private LocalDateTime localDateTime = LocalDateTime.now();
	
	@Column(name = "quantite_stock")
	private Integer quantiteStock;
	
	@Column(name = "quantite_boite")
	private Integer quantiteBoite;
	
	@Column(name = "quantite_plaquette")
	private Integer quantitePlaquette;
	
	@Column(name = "quantite_unitaire")
	private Integer quantiteUnitaire;
	
	@Column(name = "date_peremption")
	private LocalDate datePeremption;
	
	@Column(name = "numero_lot")
	private String numeroLot;
	
	// Constructeurs
	
	public StockEntrepot() {
	}
	
	// Getters and Setters
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public MyDrugEmballage getMyDrugEmballage() {
		return myDrugEmballage;
	}
	
	public void setMyDrugEmballage(MyDrugEmballage myDrugEmballage) {
		this.myDrugEmballage = myDrugEmballage;
	}
	
	public Entrepot getEntrepot() {
		return entrepot;
	}
	
	public void setEntrepot(Entrepot entrepot) {
		this.entrepot = entrepot;
	}
	
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	
	public Integer getQuantiteStock() {
		return quantiteStock;
	}
	
	public void setQuantiteStock(Integer quantiteStock) {
		this.quantiteStock = quantiteStock;
	}
	
	public Integer getQuantiteBoite() {
		return quantiteBoite;
	}
	
	public void setQuantiteBoite(Integer quantiteBoite) {
		this.quantiteBoite = quantiteBoite;
	}
	
	public Integer getQuantitePlaquette() {
		return quantitePlaquette;
	}
	
	public void setQuantitePlaquette(Integer quantitePlaquette) {
		this.quantitePlaquette = quantitePlaquette;
	}
	
	public Integer getQuantiteUnitaire() {
		return quantiteUnitaire;
	}
	
	public void setQuantiteUnitaire(Integer quantiteUnitaire) {
		this.quantiteUnitaire = quantiteUnitaire;
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
}
