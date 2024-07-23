package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "my_drug", schema = "cashier")
public class MyDrug {
	//Faire un vrai travail de correspondance au niveau de la table
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "base_inam")
	private Integer baseInam;
	
	@Column(name = "uuid", unique = true, nullable = false, length = 38, updatable = false)
	private String uuid = UUID.randomUUID().toString();
	
	@Column(name = "date_creation")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;

	@Column(name = "price")
	private Long price;
	
	@Column(name = "creator_id")
	private Integer creatorId;
	
	@Column(name = "drug_id")
	private Integer drugId;
	
	// Getters and Setters
	
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
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public Date getDateCreation() {
		return dateCreation;
	}
	
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	public Long getPrice() {
		return price;
	}
	
	public void setPrice(Long price) {
		this.price = price;
	}
	
	public Integer getCreatorId() {
		return creatorId;
	}
	
	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}
	
	public Integer getDrugId() {
		return drugId;
	}
	
	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
	}
	
	@Override
	public String toString() {
		return "MyDrug{" + "id=" + id + ", baseInam=" + baseInam + ", uuid='" + uuid + '\'' + ", dateCreation="
		        + dateCreation + ", price=" + price + ", creatorId=" + creatorId + ", drugId=" + drugId + '}';
	}
}
