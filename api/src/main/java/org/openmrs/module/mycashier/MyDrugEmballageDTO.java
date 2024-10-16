package org.openmrs.module.mycashier;

import java.time.LocalDate;

public class MyDrugEmballageDTO {

    private Integer id;

    private Float price;

    private  Float prixAssurance ;

    private  String name ;

    private LocalDate datePeremption ;

    private String numeroLot;

    private String forme ;

    private  Integer quantityStock ;

    private  Integer quantityApprovis   ;

    private  Integer quantityEmballage ;

    private Integer myDrugEmballageId;


    public MyDrugEmballageDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getQuantityStock() {
        return quantityStock;
    }

    public void setQuantityStock(Integer quantityStock) {
        this.quantityStock = quantityStock;
    }

    public Integer getQuantityApprovis() {
        return quantityApprovis;
    }

    public void setQuantityApprovis(Integer quantityApprovis) {
        this.quantityApprovis = quantityApprovis;
    }

    public Integer getMyDrugEmballageId() {
        return myDrugEmballageId;
    }

    public void setMyDrugEmballageId(Integer myDrugEmballageId) {
        this.myDrugEmballageId = myDrugEmballageId;
    }

    public Integer getQuantityEmballage() {
        return quantityEmballage;
    }

    public void setQuantityEmballage(Integer quantityEmballage) {
        this.quantityEmballage = quantityEmballage;
    }

    public Float getPrixAssurance() {
        return prixAssurance;
    }

    public void setPrixAssurance(Float prixAssurance) {
        this.prixAssurance = prixAssurance;
    }

    public String getForme() {
        return forme;
    }

    public void setForme(String forme) {
        this.forme = forme;
    }

    public  static  MyDrugEmballageDTO convertToDTO (MyDrugEmballage myDrugEmballage ) {

        MyDrugEmballageDTO myDrugEmballageDTO = new MyDrugEmballageDTO() ;

        myDrugEmballageDTO.setId(myDrugEmballage.getId());
        myDrugEmballageDTO.setPrice(myDrugEmballage.getPrice());
        myDrugEmballageDTO.setName( myDrugEmballage.getMyDrug().getName() + " " +
                                    myDrugEmballage.getMyDrug().getForme()+ " " +
                                    myDrugEmballage.getEmballage().getName() + "/" +
                                    myDrugEmballage.getQuantity());

        return  myDrugEmballageDTO ;

    }


}
