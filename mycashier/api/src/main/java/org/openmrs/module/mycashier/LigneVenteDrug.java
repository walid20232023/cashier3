package org.openmrs.module.mycashier;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "ligne_vente_drug", schema = "cashier")
public class LigneVenteDrug {

    @EmbeddedId
    private LigneVenteDrugId id;

    @ManyToOne
    @MapsId("venteDrugId")
    @JoinColumn(name = "vente_drug_id")
    private VenteDrug venteDrug;

    @ManyToOne
    @MapsId("myDrugId")
    @JoinColumn(name = "my_drug_id")
    private MyDrug myDrug;

    // Getters and Setters

    public LigneVenteDrugId getId() {
        return id;
    }

    public void setId(LigneVenteDrugId id) {
        this.id = id;
    }

    public VenteDrug getVenteDrug() {
        return venteDrug;
    }

    public void setVenteDrug(VenteDrug venteDrug) {
        this.venteDrug = venteDrug;
    }

    public MyDrug getMyDrug() {
        return myDrug;
    }

    public void setMyDrug(MyDrug myDrug) {
        this.myDrug = myDrug;
    }

    @Override
    public String toString() {
        return "LigneVenteDrug{" +
                "id=" + id +
                ", venteDrug=" + venteDrug +
                ", myDrug=" + myDrug +
                '}';
    }

    @Embeddable
    public static class LigneVenteDrugId implements Serializable {

        @Column(name = "vente_drug_id")
        private Integer venteDrugId;

        @Column(name = "my_drug_id")
        private Integer myDrugId;

        // Default constructor

        public LigneVenteDrugId() {
        }

        // Parameterized constructor

        public LigneVenteDrugId(Integer venteDrugId, Integer myDrugId) {
            this.venteDrugId = venteDrugId;
            this.myDrugId = myDrugId;
        }

        // Getters and Setters

        public Integer getVenteDrugId() {
            return venteDrugId;
        }

        public void setVenteDrugId(Integer venteDrugId) {
            this.venteDrugId = venteDrugId;
        }

        public Integer getMyDrugId() {
            return myDrugId;
        }

        public void setMyDrugId(Integer myDrugId) {
            this.myDrugId = myDrugId;
        }




    }
}
