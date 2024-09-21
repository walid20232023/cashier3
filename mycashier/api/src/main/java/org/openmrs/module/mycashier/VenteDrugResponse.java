package org.openmrs.module.mycashier;

import java.time.LocalDateTime;
import java.util.List;

public class VenteDrugResponse {
	
	private Integer venteDrugId;
	
	private LocalDateTime dateVente;
	
	private String clientNom;
	
	private String clientPrenom;
	
	private String drug1;
	
	private String drug2;
	
	private String drug3;
	
	private String dci1;
	
	private String dci2;
	
	private String dci3;
	
	private String group1;
	
	private String group2;
	
	private String group3;
	
	// Constructeur
	
	public VenteDrugResponse() {
	}
	
	// Getters and setters
	
	public Integer getVenteDrugId() {
		return venteDrugId;
	}
	
	public LocalDateTime getDateVente() {
		return dateVente;
	}
	
	public String getClientNom() {
		return clientNom;
	}
	
	public String getClientPrenom() {
		return clientPrenom;
	}
	
	public String getDrug1() {
		return drug1;
	}
	
	public String getDrug2() {
		return drug2;
	}
	
	public String getDrug3() {
		return drug3;
	}
	
	public String getDci1() {
		return dci1;
	}
	
	public String getDci2() {
		return dci2;
	}
	
	public String getDci3() {
		return dci3;
	}
	
	public String getGroup2() {
		return group2;
	}
	
	public String getGroup3() {
		return group3;
	}
	
	public String getGroup1() {
		return group1;
	}
	
	public void setVenteDrugId(Integer venteDrugId) {
		this.venteDrugId = venteDrugId;
	}
	
	public void setDateVente(LocalDateTime dateVente) {
		this.dateVente = dateVente;
	}
	
	public void setClientNom(String clientNom) {
		this.clientNom = clientNom;
	}
	
	public void setClientPrenom(String clientPrenom) {
		this.clientPrenom = clientPrenom;
	}
	
	public void setDrug1(String drug1) {
		this.drug1 = drug1;
	}
	
	public void setDrug2(String drug2) {
		this.drug2 = drug2;
	}
	
	public void setDrug3(String drug3) {
		this.drug3 = drug3;
	}
	
	public void setDci2(String dci2) {
		this.dci2 = dci2;
	}
	
	public void setDci1(String dci1) {
		this.dci1 = dci1;
	}
	
	public void setDci3(String dci3) {
		this.dci3 = dci3;
	}
	
	public void setGroup1(String group1) {
		this.group1 = group1;
	}
	
	public void setGroup2(String group2) {
		this.group2 = group2;
	}
	
	public void setGroup3(String group3) {
		this.group3 = group3;
	}
	
	// Convertisseur
	
	public VenteDrugResponse venteToResponse(VenteDrug venteDrug, List<MyDrug> drugs) {
		
		VenteDrugResponse venteDrugResponse = new VenteDrugResponse();
		venteDrugResponse.setVenteDrugId(venteDrug.getId());
		venteDrugResponse.setDateVente(venteDrug.getDateVente());
		venteDrugResponse.setClientNom(venteDrug.getClient().getName());
		venteDrugResponse.setClientPrenom(venteDrug.getClient().getFirstnames());
		
		// Vérifier combien de lignes de vente existent et les assigner
		if (!drugs.isEmpty()) {
			MyDrug myDrug = drugs.get(0);
			venteDrugResponse.setDrug1(myDrug.getName());
			venteDrugResponse.setDci1(myDrug.getDci());
			venteDrugResponse.setGroup1(myDrug.getGroupeTherap());
		} else {
			venteDrugResponse.setDrug1("");
			venteDrugResponse.setDci1("");
			venteDrugResponse.setGroup1("");
		}
		
		if (drugs.size() > 1) {
			MyDrug myDrug = drugs.get(1);
			venteDrugResponse.setDrug2(myDrug.getName());
			venteDrugResponse.setDci2(myDrug.getDci());
			venteDrugResponse.setGroup2(myDrug.getGroupeTherap());
		} else {
			venteDrugResponse.setDrug2("");
			venteDrugResponse.setDci2("");
			venteDrugResponse.setGroup2("");
		}
		
		if (drugs.size() > 2) {
			MyDrug myDrug = drugs.get(2);
			venteDrugResponse.setDrug3(myDrug.getName());
			venteDrugResponse.setDci3(myDrug.getDci());
			venteDrugResponse.setGroup3(myDrug.getGroupeTherap());
		} else {
			venteDrugResponse.setDrug3("");
			venteDrugResponse.setDci3("");
			venteDrugResponse.setGroup3("");
		}
		
		return venteDrugResponse;
	}
	
}
