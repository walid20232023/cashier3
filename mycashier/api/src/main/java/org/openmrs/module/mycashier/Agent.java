package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;
import org.openmrs.User;

@Entity
@Table(name = "agent", schema = "cashier")
public class Agent {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "agent_creator_id")
	private User agentCreator;
	
	@Column(name = "date_creation")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;
	
	@Column(name = "uuid", unique = true, nullable = false, length = 38, updatable = false)
	private String uuid = UUID.randomUUID().toString();
	
	@Column(name = "caisse")
	private Long caisse;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	// Getters and Setters
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public User getAgentCreator() {
		return agentCreator;
	}
	
	public void setAgentCreator(User agentCreator) {
		this.agentCreator = agentCreator;
	}
	
	public Date getDateCreation() {
		return dateCreation;
	}
	
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public Long getCaisse() {
		return caisse;
	}
	
	public void setCaisse(Long caisse) {
		this.caisse = caisse;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Agent{" + "id=" + id + ", agentCreator=" + agentCreator + ", dateCreation=" + dateCreation + ", uuid='"
		        + uuid + '\'' + ", caisse=" + caisse + ", user=" + user + '}';
	}
}
