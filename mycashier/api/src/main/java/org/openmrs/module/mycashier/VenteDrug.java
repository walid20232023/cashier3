package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import org.openmrs.User;

@Entity
@Table(name = "vente_drug", schema = "cashier")
public class VenteDrug implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "date_vente")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateVente;
	
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
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "date_creation")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;
	
	// Getters and Setters
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getDateVente() {
		return dateVente;
	}
	
	public void setDateVente(Date dateVente) {
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
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getDateCreation() {
		return dateCreation;
	}
	
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	@PrePersist
	protected void onCreate() {
		if (this.dateCreation == null) {
			this.dateCreation = new Date();
		}
	}
	
	@Override
	public String toString() {
		return "VenteDrug{" + "id=" + id + ", dateVente=" + dateVente + ", uuid='" + uuid + '\'' + ", agent=" + agent
		        + ", client=" + client + ", entrepot=" + entrepot + ", user=" + user + ", dateCreation=" + dateCreation
		        + '}';
	}
}
