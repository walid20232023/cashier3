package org.openmrs.module.mycashier;

import javax.persistence.*;

@Entity
@Table(name = "assurance_my_drug_price")
public class AssuranceMyDrugPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "my_drug_emballage_id", nullable = false)
	private MyDrugEmballage myDrugEmballage;

	@ManyToOne
	@JoinColumn(name = "assurance_id", nullable = false)
	private Assurance assurance;

	private Integer price;

	private Integer taux;

	// Getters and Setters

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getTaux() {
		return taux;
	}

	public void setTaux(Integer taux) {
		this.taux = taux;
	}
}
