package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "approvisionnement", schema = "cashier")
public class Approvisionnement {
	
	@Id
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "date_approvisionnement")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateApprovisionnement;
	
	@Column(name = "quantite_approvis")
	private Integer quantiteApprovis;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "entrepot_source_id")
	private Entrepot entrepotSource;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "entrepot_cible_id")
	private Entrepot entrepotCible;
	
	@ManyToOne
	@JoinColumn(name = "agent_id")
	private Agent agent;
	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "date_creation")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;
	
	@PrePersist
	protected void onCreate() {
		if (this.dateCreation == null) {
			this.dateCreation = new Date();
		}
		if (this.uuid == null) {
			this.uuid = UUID.randomUUID().toString();
		}
	}
	
	// Getters and Setters
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getDateApprovisionnement() {
		return dateApprovisionnement;
	}
	
	public void setDateApprovisionnement(Date dateApprovisionnement) {
		this.dateApprovisionnement = dateApprovisionnement;
	}
	
	public Integer getQuantiteApprovis() {
		return quantiteApprovis;
	}
	
	public void setQuantiteApprovis(Integer quantiteApprovis) {
		this.quantiteApprovis = quantiteApprovis;
	}
	
	public Entrepot getEntrepotSource() {
		return entrepotSource;
	}
	
	public void setEntrepotSource(Entrepot entrepotSource) {
		this.entrepotSource = entrepotSource;
	}
	
	public Entrepot getEntrepotCible() {
		return entrepotCible;
	}
	
	public void setEntrepotCible(Entrepot entrepotCible) {
		this.entrepotCible = entrepotCible;
	}
	
	public Agent getAgent() {
		return agent;
	}
	
	public void setAgent(Agent agent) {
		this.agent = agent;
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
	
	@Override
	public String toString() {
		return "Approvisionnement{" + "id=" + id + ", dateApprovisionnement=" + dateApprovisionnement
		        + ", quantiteApprovis=" + quantiteApprovis + ", entrepotSource=" + entrepotSource + ", entrepotCible="
		        + entrepotCible + ", agent=" + agent + ", uuid='" + uuid + '\'' + ", dateCreation=" + dateCreation + '}';
	}
}
