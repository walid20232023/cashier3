package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "versement", schema = "cashier")
public class Versement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date_versement")
    private Date dateVersement;

    @Column(name = "montant")
    private Integer montant;

    @ManyToOne
    @JoinColumn(name = "caisse_agent_source_id")
    private Agent caisseAgentSource;

    @ManyToOne
    @JoinColumn(name = "caisse_agent_cible_id")
    private Agent caisseAgentCible;

    @Column(name = "uuid", length = 38)
    private String uuid;

    @Column(name = "date_creation")
    private Date dateCreation;

    // Default constructor
    public Versement() {
    }

    // Getters and Setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateVersement() {
        return dateVersement;
    }

    public void setDateVersement(Date dateVersement) {
        this.dateVersement = dateVersement;
    }

    public Integer getMontant() {
        return montant;
    }

    public void setMontant(Integer montant) {
        this.montant = montant;
    }

    public Agent getCaisseAgentSource() {
        return caisseAgentSource;
    }

    public void setCaisseAgentSource(Agent caisseAgentSource) {
        this.caisseAgentSource = caisseAgentSource;
    }

    public Agent getCaisseAgentCible() {
        return caisseAgentCible;
    }

    public void setCaisseAgentCible(Agent caisseAgentCible) {
        this.caisseAgentCible = caisseAgentCible;
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
        return "Versement{" +
                "id=" + id +
                ", dateVersement=" + dateVersement +
                ", montant=" + montant +
                ", caisseAgentSource=" + caisseAgentSource +
                ", caisseAgentCible=" + caisseAgentCible +
                ", uuid='" + uuid + '\'' +
                ", dateCreation=" + dateCreation +
                '}';
    }
}
