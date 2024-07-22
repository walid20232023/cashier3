package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "provision", schema = "cashier")
public class Provision {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "agent_id")
	private Agent agent;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@Column(name = "somme")
	private Integer somme;
	
	@Column(name = "total")
	private Integer total;
	
	@Column(name = "uuid", unique = true, length = 38)
	private String uuid;
	
	@Column(name = "date_creation")
	private Date dateCreation;
	
	// Default constructor
	public Provision() {
	}
	
	// Getters and Setters
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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
	
	public Integer getSomme() {
		return somme;
	}
	
	public void setSomme(Integer somme) {
		this.somme = somme;
	}
	
	public Integer getTotal() {
		return total;
	}
	
	public void setTotal(Integer total) {
		this.total = total;
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
		return "Provision{" + "id=" + id + ", agent=" + agent + ", client=" + client + ", somme=" + somme + ", total="
		        + total + ", uuid='" + uuid + '\'' + ", dateCreation=" + dateCreation + '}';
	}
	
	@PrePersist
	protected void onCreate() {
		if (this.dateCreation == null) {
			this.dateCreation = new Date();
		}
		if (this.uuid == null) {
			this.uuid = java.util.UUID.randomUUID().toString();
		}
	}
}
