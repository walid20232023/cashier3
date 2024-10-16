package org.openmrs.module.mycashier;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LigneApprovisDTO {
    private Integer id;

    private String medicament;

    private  LocalDateTime dateApprovisionnement ;

    private  Entrepot entrepotSource ;

    private  Entrepot entrepotCible ;

    private LocalDate datePeremption ;

    private String numeroLot;

    private Integer quantityApprovis   ;


    public LigneApprovisDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMedicament() {
        return medicament;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    public LocalDateTime getDateApprovisionnement() {
        return dateApprovisionnement;
    }

    public void setDateApprovisionnement(LocalDateTime dateApprovisionnement) {
        this.dateApprovisionnement = dateApprovisionnement;
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

    public LocalDate getDatePeremption() {
        return datePeremption;
    }

    public void setDatePeremption(LocalDate datePeremption) {
        this.datePeremption = datePeremption;
    }

    public String getNumeroLot() {
        return numeroLot;
    }

    public void setNumeroLot(String numeroLot) {
        this.numeroLot = numeroLot;
    }

    public Integer getQuantityApprovis() {
        return quantityApprovis;
    }

    public void setQuantityApprovis(Integer quantityApprovis) {
        this.quantityApprovis = quantityApprovis;
    }

    public  static  LigneApprovisDTO convertToDTO (LigneApprovis ligneApprovis ) {

        LigneApprovisDTO ligneApprovisDTO = new LigneApprovisDTO() ;

        ligneApprovisDTO.setId(ligneApprovis.getId());
        ligneApprovisDTO.setQuantityApprovis(ligneApprovis.getQuantite());
        ligneApprovisDTO.setNumeroLot(ligneApprovis.getNumeroLot()) ;
        ligneApprovisDTO.setMedicament( ligneApprovis.getMyDrugEmballage().getMyDrug().getName() + " " +
                ligneApprovis.getMyDrugEmballage().getMyDrug().getForme() + " " +
                ligneApprovis.getMyDrugEmballage().getEmballage().getName() + "/" +
                ligneApprovis.getMyDrugEmballage().getQuantity());

        return  ligneApprovisDTO ;
    }
}
