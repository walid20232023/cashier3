package org.openmrs.module.mycashier;

import org.openmrs.Drug;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "my_drug", schema = "cashier")
public class MyDrug extends Drug {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "base_inam")
	private Integer baseInam;

	@Column(name = "price")
	private Long price;

	public MyDrug() {
		super();
	}

	public MyDrug(Drug drug) {
		super();
		this.setDrugId(drug.getDrugId());
		this.setConcept(drug.getConcept());
		this.setName(drug.getName());
		this.setCombination(drug.getCombination());
		this.setDosageForm(drug.getDosageForm());
		this.setMaximumDailyDose(drug.getMaximumDailyDose());
		this.setMinimumDailyDose(drug.getMinimumDailyDose());
		this.setRoute(drug.getRoute());
		this.setCreator(drug.getCreator());
		this.setDateCreated(drug.getDateCreated());
		this.setRetired(drug.getRetired());
		this.setChangedBy(drug.getChangedBy());
		this.setDateChanged(drug.getDateChanged());
		this.setRetiredBy(drug.getRetiredBy());
		this.setDateRetired(drug.getDateRetired());
		this.setRetireReason(drug.getRetireReason());
		this.setUuid(drug.getUuid());
		this.setStrength(drug.getStrength());


	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBaseInam() {
		return baseInam;
	}

	public void setBaseInam(Integer baseInam) {
		this.baseInam = baseInam;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
}
