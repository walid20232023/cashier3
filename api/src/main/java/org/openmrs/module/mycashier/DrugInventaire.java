package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "drug_inventaire")
public class DrugInventaire {
	
	@EmbeddedId
	private DrugInventaireId id;
	
	@ManyToOne
	@MapsId("myDrugEmballageId")
	@JoinColumn(name = "my_drug_emballage_id", nullable = false)
	private MyDrugEmballage myDrugEmballage;
	
	@ManyToOne
	@MapsId("inventaireId")
	@JoinColumn(name = "inventaire_id", nullable = false)
	private Inventaire inventaire;
	
	@Column(name = "real_quantity")
	private Integer realQuantity;
	
	@Column(name = "ecart")
	private Integer ecart;
	
	// Default constructor
	public DrugInventaire() {
	}
	
	// Getters and Setters
	public DrugInventaireId getId() {
		return id;
	}
	
	public void setId(DrugInventaireId id) {
		this.id = id;
	}
	
	public MyDrugEmballage getMyDrugEmballage() {
		return myDrugEmballage;
	}
	
	public void setMyDrugEmballage(MyDrugEmballage myDrugEmballage) {
		this.myDrugEmballage = myDrugEmballage;
	}
	
	public Inventaire getInventaire() {
		return inventaire;
	}
	
	public void setInventaire(Inventaire inventaire) {
		this.inventaire = inventaire;
	}
	
	public Integer getRealQuantity() {
		return realQuantity;
	}
	
	public void setRealQuantity(Integer realQuantity) {
		this.realQuantity = realQuantity;
	}
	
	public Integer getEcart() {
		return ecart;
	}
	
	public void setEcart(Integer ecart) {
		this.ecart = ecart;
	}
	
	@Override
	public String toString() {
		return "DrugInventaire{" + "id=" + id + ", myDrugEmballage=" + myDrugEmballage + ", inventaire=" + inventaire
		        + ", realQuantity=" + realQuantity + ", ecart=" + ecart + '}';
	}
	
	@Embeddable
	public static class DrugInventaireId implements Serializable {
		
		@Column(name = "my_drug_emballage_id")
		private Integer myDrugEmballageId;
		
		@Column(name = "inventaire_id")
		private Integer inventaireId;
		
		// Default constructor
		public DrugInventaireId() {
		}
		
		// Parameterized constructor
		public DrugInventaireId(Integer myDrugEmballageId, Integer inventaireId) {
			this.myDrugEmballageId = myDrugEmballageId;
			this.inventaireId = inventaireId;
		}
		
		// Getters and Setters
		public Integer getMyDrugEmballageId() {
			return myDrugEmballageId;
		}
		
		public void setMyDrugEmballageId(Integer myDrugEmballageId) {
			this.myDrugEmballageId = myDrugEmballageId;
		}
		
		public Integer getInventaireId() {
			return inventaireId;
		}
		
		public void setInventaireId(Integer inventaireId) {
			this.inventaireId = inventaireId;
		}
		
		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			DrugInventaireId that = (DrugInventaireId) o;
			return Objects.equals(myDrugEmballageId, that.myDrugEmballageId)
			        && Objects.equals(inventaireId, that.inventaireId);
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(myDrugEmballageId, inventaireId);
		}
	}
}
