package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "inventaire", schema = "cashier")
public class Inventaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "entrepot_id")
    private Entrepot entrepot;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @ManyToOne
    @JoinColumn(name = "superviseur_id")
    private Agent superviseur;

    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "uuid", length = 38)
    private String uuid;

    // Default constructor
    public Inventaire() {
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Agent getSuperviseur() {
        return superviseur;
    }

    public void setSuperviseur(Agent superviseur) {
        this.superviseur = superviseur;
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

    @PrePersist
    protected void onCreate() {
        if (this.dateCreation == null) {
            this.dateCreation = new Date();
        }
    }

    @Override
    public String toString() {
        return "Inventaire{" +
                "id=" + id +
                ", entrepot=" + entrepot +
                ", agent=" + agent +
                ", superviseur=" + superviseur +
                ", dateCreation=" + dateCreation +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
