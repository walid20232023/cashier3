package org.openmrs.module.mycashier;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

public class DrugResponse {
	
	private Integer id;
	
	private Integer baseInam;
	
	private Integer price;
	
	private String name;
	
	private String cip;
	
	private String groupeTherap;
	
	private String dci;
	
	private LocalDateTime dateCreated;
	
	private String uuid;
	
	private Integer drugId;
	
	//---------Les attributs supplémentaires
	private Integer stockLocal;
	
	private Integer stockMag;
	
	private Integer quantity;
	
	public DrugResponse() {
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
	
	public Integer getPrice() {
		return price;
	}
	
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCip() {
		return cip;
	}
	
	public void setCip(String cip) {
		this.cip = cip;
	}
	
	public String getGroupeTherap() {
		return groupeTherap;
	}
	
	public void setGroupeTherap(String groupeTherap) {
		this.groupeTherap = groupeTherap;
	}
	
	public String getDci() {
		return dci;
	}
	
	public void setDci(String dci) {
		this.dci = dci;
	}
	
	public LocalDateTime getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public Integer getDrugId() {
		return drugId;
	}
	
	public void setDrugId(Integer drugId) {
		this.drugId = drugId;
	}
	
	public Integer getStockLocal() {
		return stockLocal;
	}
	
	public void setStockLocal(Integer stockLocal) {
		this.stockLocal = stockLocal;
	}
	
	public Integer getStockMag() {
		return stockMag;
	}
	
	//Constructeurs
	
	public void setStockMag(Integer stockMag) {
		this.stockMag = stockMag;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public static DrugResponse drugToResponse(MyDrug myDrug) {
		
		DrugResponse drugResponse = new DrugResponse();
		drugResponse.setId(myDrug.getId());
		drugResponse.setName(myDrug.getName());
		drugResponse.setDci(myDrug.getDci());
		drugResponse.setCip(myDrug.getCip());
		drugResponse.setBaseInam(myDrug.getBaseInam());
		drugResponse.setDateCreated(myDrug.getDateCreated());
		drugResponse.setGroupeTherap(myDrug.getGroupeTherap());
		drugResponse.setPrice(myDrug.getPrice());
		drugResponse.setUuid(myDrug.getUuid());
		drugResponse.setDrugId(myDrug.getDrugId());
		
		return drugResponse;
		
	}
	
}
