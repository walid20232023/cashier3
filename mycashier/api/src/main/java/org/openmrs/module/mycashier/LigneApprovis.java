package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ligne_approvis")
public class LigneApprovis {

	@EmbeddedId
	private LigneApprovisId id;

	@ManyToOne
	@MapsId("approvisionnementId")
	@JoinColumn(name = "approvisionnement_id", nullable = false)
	private Approvisionnement approvisionnement;

	@ManyToOne
	@MapsId("myDrugEmballageId")
	@JoinColumn(name = "my_drug_emballage_id", nullable = false)
	private MyDrugEmballage myDrugEmballage;

	@Column(name = "quantite")
	private Integer quantite;

	@Column(name = "date_peremption", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date datePeremption;

	@Column(name = "numero_lot", length = 100)
	private String numeroLot;

	// Constructeurs

	public LigneApprovis() {}

	// Getters and Setters

	public LigneApprovisId getId() {
		return id;
	}

	public void setId(LigneApprovisId id) {
		this.id = id;
	}

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

	public Date getDatePeremption() {
		return datePeremption;
	}

	public void setDatePeremption(Date datePeremption) {
		this.datePeremption = datePeremption;
	}

	public String getNumeroLot() {
		return numeroLot;
	}

	public void setNumeroLot(String numeroLot) {
		this.numeroLot = numeroLot;
	}

	@Override
	public String toString() {
		return "LigneApprovis{" +
				"id=" + id +
				", approvisionnement=" + approvisionnement +
				", myDrugEmballage=" + myDrugEmballage +
				", quantite=" + quantite +
				", datePeremption=" + datePeremption +
				", numeroLot='" + numeroLot + '\'' +
				'}';
	}

	@Embeddable
	public static class LigneApprovisId implements Serializable {

		@Column(name = "approvisionnement_id")
		private Integer approvisionnementId;

		@Column(name = "my_drug_emballage_id")
		private Integer myDrugEmballageId;

		// Default constructor
		public LigneApprovisId() {}

		// Parameterized constructor
		public LigneApprovisId(Integer approvisionnementId, Integer myDrugEmballageId) {
			this.approvisionnementId = approvisionnementId;
			this.myDrugEmballageId = myDrugEmballageId;
		}

		// Getters and Setters
		public Integer getApprovisionnementId() {
			return approvisionnementId;
		}

		public void setApprovisionnementId(Integer approvisionnementId) {
			this.approvisionnementId = approvisionnementId;
		}

		public Integer getMyDrugEmballageId() {
			return myDrugEmballageId;
		}

		public void setMyDrugEmballageId(Integer myDrugEmballageId) {
			this.myDrugEmballageId = myDrugEmballageId;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			LigneApprovisId that = (LigneApprovisId) o;
			return Objects.equals(approvisionnementId, that.approvisionnementId) &&
					Objects.equals(myDrugEmballageId, that.myDrugEmballageId);
		}

		@Override
		public int hashCode() {
			return Objects.hash(approvisionnementId, myDrugEmballageId);
		}
	}
}
