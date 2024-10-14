package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ligne_vente_drug")
public class LigneVenteDrug {

	@EmbeddedId
	private LigneVenteDrugId id;

	@ManyToOne
	@MapsId("venteDrugId")
	@JoinColumn(name = "vente_drug_id", nullable = false)
	private VenteDrug venteDrug;

	@ManyToOne
	@MapsId("myDrugEmballageId")
	@JoinColumn(name = "my_drug_emballage_id", nullable = false)
	private MyDrugEmballage myDrugEmballage;

	@Column(name = "quantity", nullable = false)
	private Integer quantity;

	@Column(name = "price")
	private Integer price;

	// Constructeurs

	public LigneVenteDrug() {}

	// Getters and Setters

	public LigneVenteDrugId getId() {
		return id;
	}

	public void setId(LigneVenteDrugId id) {
		this.id = id;
	}

	public VenteDrug getVenteDrug() {
		return venteDrug;
	}

	public void setVenteDrug(VenteDrug venteDrug) {
		this.venteDrug = venteDrug;
	}

	public MyDrugEmballage getMyDrugEmballage() {
		return myDrugEmballage;
	}

	public void setMyDrugEmballage(MyDrugEmballage myDrugEmballage) {
		this.myDrugEmballage = myDrugEmballage;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "LigneVenteDrug{" +
				"id=" + id +
				", venteDrug=" + venteDrug +
				", myDrugEmballage=" + myDrugEmballage +
				", quantity=" + quantity +
				", price=" + price +
				'}';
	}

	@Embeddable
	public static class LigneVenteDrugId implements Serializable {

		@Column(name = "vente_drug_id")
		private Integer venteDrugId;

		@Column(name = "my_drug_emballage_id")
		private Integer myDrugEmballageId;

		// Default constructor
		public LigneVenteDrugId() {}

		// Parameterized constructor
		public LigneVenteDrugId(Integer venteDrugId, Integer myDrugEmballageId) {
			this.venteDrugId = venteDrugId;
			this.myDrugEmballageId = myDrugEmballageId;
		}

		// Getters and Setters
		public Integer getVenteDrugId() {
			return venteDrugId;
		}

		public void setVenteDrugId(Integer venteDrugId) {
			this.venteDrugId = venteDrugId;
		}

		public Integer getMyDrugEmballageId() {
			return myDrugEmballageId;
		}

		public void setMyDrugEmballageId(Integer myDrugEmballageId) {
			this.myDrugEmballageId = myDrugEmballageId;
		}




	}
}
