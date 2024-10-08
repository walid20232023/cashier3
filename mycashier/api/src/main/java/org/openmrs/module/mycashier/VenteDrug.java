package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "vente_drug")
public class VenteDrug implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "date_vente")
	private LocalDateTime dateVente;
	
	@Column(name = "uuid", unique = true, nullable = false, length = 38, updatable = false)
	private String uuid = UUID.randomUUID().toString();
	
	@ManyToOne
	@JoinColumn(name = "agent_id")
	private Agent agent;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn(name = "entrepot_id")
	private Entrepot entrepot;
	
	@Column(name = "assurance")
	private String assurance;
	
	@Column(name = "part_assurance")
	private Float partAssurance;
	
	@Column(name = "total")
	private Float total;
	
	@Column(name = "reste")
	private Float reste;
	
	@Column(name = "date_creation")
	private LocalDateTime dateCreation = LocalDateTime.now();
	
	@Column(name = "validate")
	private Integer validate;
	
	@Column(name = "validator_id")
	private Integer validatorId;
	
	//Constructeurs
	
	public VenteDrug() {
	}
	
	// Getters and Setters
	
	public void setAssurance(String assurance) {
		this.assurance = assurance;
	}
	
	public void setPartAssurance(Float partAssurance) {
		this.partAssurance = partAssurance;
	}
	
	public void setTotal(Float total) {
		this.total = total;
	}
	
	public String getAssurance() {
		return assurance;
	}
	
	public Float getPartAssurance() {
		return partAssurance;
	}
	
	public Float getTotal() {
		return total;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public LocalDateTime getDateVente() {
		return dateVente;
	}
	
	public void setDateVente(LocalDateTime dateVente) {
		this.dateVente = dateVente;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public Agent getAgent() {
		return agent;
	}
	
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public Entrepot getEntrepot() {
		return entrepot;
	}
	
	public void setEntrepot(Entrepot entrepot) {
		this.entrepot = entrepot;
	}
	
	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	public LocalDateTime getDateCreation() {
		return dateCreation;
	}
	
	public Integer getValidate() {
		return validate;
	}
	
	public void setValidate(Integer validate) {
		this.validate = validate;
	}
	
	public Integer getValidatorId() {
		return validatorId;
	}
	
	public void setValidatorId(Integer validatorId) {
		this.validatorId = validatorId;
	}
	
	public Float getReste() {
		return reste;
	}
	
	public void setReste(Float reste) {
		this.reste = reste;
	}
	
}
