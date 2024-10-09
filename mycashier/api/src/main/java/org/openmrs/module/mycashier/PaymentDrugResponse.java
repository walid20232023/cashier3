package org.openmrs.module.mycashier;

import java.time.LocalDateTime;

public class PaymentDrugResponse {
	
	private Integer paymentDrugId;
	
	private LocalDateTime datePayment;
	
	private String clientNom;
	
	private String clientPrenom;
	
	private String assurance;
	
	private Float reste;
	
	private Float montant;
	
	private String modePayment;
	
	private String agentName;
	
	private Float caisse;
	
	public PaymentDrugResponse() {
	}
	
	public Integer getPaymentDrugId() {
		return paymentDrugId;
	}
	
	public void setPaymentDrugId(Integer paymentDrugId) {
		this.paymentDrugId = paymentDrugId;
	}
	
	public LocalDateTime getDatePayment() {
		return datePayment;
	}
	
	public void setDatePayment(LocalDateTime datePayment) {
		this.datePayment = datePayment;
	}
	
	public String getClientNom() {
		return clientNom;
	}
	
	public void setClientNom(String clientNom) {
		this.clientNom = clientNom;
	}
	
	public String getClientPrenom() {
		return clientPrenom;
	}
	
	public void setClientPrenom(String clientPrenom) {
		this.clientPrenom = clientPrenom;
	}
	
	public String getAssurance() {
		return assurance;
	}
	
	public void setAssurance(String assurance) {
		this.assurance = assurance;
	}
	
	public Float getReste() {
		return reste;
	}
	
	public void setReste(Float reste) {
		this.reste = reste;
	}
	
	public Float getMontant() {
		return montant;
	}
	
	public void setMontant(Float montant) {
		this.montant = montant;
	}
	
	public String getModePayment() {
		return modePayment;
	}
	
	public void setModePayment(String modePayment) {
		this.modePayment = modePayment;
	}
	
	public String getAgentName() {
		return agentName;
	}
	
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
	public Float getCaisse() {
		return caisse;
	}
	
	public void setCaisse(Float caisse) {
		this.caisse = caisse;
	}
	
	// Convertisseur
	
	public static PaymentDrugResponse paymentToPaymentResponse(Payment payment) {
		
		PaymentDrugResponse paymentDrugResponse = new PaymentDrugResponse();
		paymentDrugResponse.setPaymentDrugId(payment.getId());
		paymentDrugResponse.setDatePayment(payment.getDatePayment());
		paymentDrugResponse.setClientNom(payment.getVenteDrug().getClient().getName());
		paymentDrugResponse.setClientPrenom(payment.getVenteDrug().getClient().getFirstnames());
		paymentDrugResponse.setAgentName(payment.getAgent().getUsername());
		paymentDrugResponse.setModePayment(payment.getModePayment());
		paymentDrugResponse.setMontant(payment.getMontant());
		paymentDrugResponse.setReste(payment.getVenteDrug().getReste());
		paymentDrugResponse.setAssurance(payment.getVenteDrug().getAssurance());
		paymentDrugResponse.setCaisse(payment.getAgent().getCaisse());
		
		return paymentDrugResponse;
		
	}
	
}
