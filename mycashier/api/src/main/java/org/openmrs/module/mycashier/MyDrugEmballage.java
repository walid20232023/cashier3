package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "my_drug_emballage")
public class MyDrugEmballage implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "my_drug_id", nullable = false)
	private MyDrug myDrug;

	@ManyToOne
	@JoinColumn(name = "emballage_id", nullable = false)
	private Emballage emballage;

	@Column(name = "price", nullable = false)
	private Float price;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	// Constructeurs

	public MyDrugEmballage() {}



	// Getters and Setters

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MyDrug getMyDrug() {
		return myDrug;
	}

	public void setMyDrug(MyDrug myDrug) {
		this.myDrug = myDrug;
	}

	public Emballage getEmballage() {
		return emballage;
	}

	public void setEmballage(Emballage emballage) {
		this.emballage = emballage;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	// Méthode equals et hashCode pour comparaison d'objets

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		MyDrugEmballage that = (MyDrugEmballage) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(myDrug, that.myDrug) &&
				Objects.equals(emballage, that.emballage) &&
				Objects.equals(price, that.price);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, myDrug, emballage, price);
	}
}
