package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "assurance_my_drug_price")
public class AssuranceMyDrugPrice implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "my_drug_emballage_id", nullable = false)
	private MyDrugEmballage myDrugEmballage;
	
	@ManyToOne
	@JoinColumn(name = "assurance_id", nullable = false)
	private Assurance assurance;
	
	@Column(name = "price")
	private Float price;
	
	@Column(name = "taux")
	private Float taux;
	
	// Constructeurs
	
	public AssuranceMyDrugPrice() {
	}
	
	public AssuranceMyDrugPrice(MyDrugEmballage myDrugEmballage, Assurance assurance, Float price, Float taux) {
		this.myDrugEmballage = myDrugEmballage;
		this.assurance = assurance;
		this.price = price;
		this.taux = taux;
	}
	
	// Getters et Setters
	
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
	
	public Assurance getAssurance() {
		return assurance;
	}
	
	public void setAssurance(Assurance assurance) {
		this.assurance = assurance;
	}
	
	public Float getPrice() {
		return price;
	}
	
	public void setPrice(Float price) {
		this.price = price;
	}
	
	public Float getTaux() {
		return taux;
	}
	
	public void setTaux(Float taux) {
		this.taux = taux;
	}
	
	// Méthode equals et hashCode pour comparaison d'objets
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		AssuranceMyDrugPrice that = (AssuranceMyDrugPrice) o;
		return Objects.equals(id, that.id) && Objects.equals(myDrugEmballage, that.myDrugEmballage)
		        && Objects.equals(assurance, that.assurance) && Objects.equals(price, that.price)
		        && Objects.equals(taux, that.taux);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, myDrugEmballage, assurance, price, taux);
	}
}
