package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "my_drug_avarie", schema = "cashier")
public class MyDrugAvarie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "date_declaration")
	private Date dateDeclaration;
	
	@Column(name = "quantite")
	private Integer quantite;
	
	@ManyToOne
	@JoinColumn(name = "entrepot_id")
	private Entrepot entrepot;
	
	@ManyToOne
	@JoinColumn(name = "agent_id")
	private Agent agent;
	
	@ManyToOne
	@JoinColumn(name = "my_drug_id")
	private MyDrug myDrug;
	
	@Column(name = "uuid", length = 38)
	private String uuid;
	
	@Column(name = "date_creation")
	private Date dateCreation;
	
	// Default constructor
	public MyDrugAvarie() {
	}
	
	// Getters and Setters
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Date getDateDeclaration() {
		return dateDeclaration;
	}
	
	public void setDateDeclaration(Date dateDeclaration) {
		this.dateDeclaration = dateDeclaration;
	}
	
	public Integer getQuantite() {
		return quantite;
	}
	
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
	
	public Entrepot getEntrepot() {
		return entrepot;
	}
	
	public void setEntrepot(Entrepot entrepot) {
		this.entrepot = entrepot;
	}
	
	public Agent getAgent() {
		return agent;
	}
	
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	
	public MyDrug getMyDrug() {
		return myDrug;
	}
	
	public void setMyDrug(MyDrug myDrug) {
		this.myDrug = myDrug;
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
	
	@PrePersist
	protected void onCreate() {
		if (this.dateCreation == null) {
			this.dateCreation = new Date();
		}
	}
	
	@Override
	public String toString() {
		return "MyDrugAvarie{" + "id=" + id + ", dateDeclaration=" + dateDeclaration + ", quantite=" + quantite
		        + ", entrepot=" + entrepot + ", agent=" + agent + ", myDrug=" + myDrug + ", uuid='" + uuid + '\''
		        + ", dateCreation=" + dateCreation + '}';
	}
}
