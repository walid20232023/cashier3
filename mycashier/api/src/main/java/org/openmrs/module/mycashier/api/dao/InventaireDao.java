package org.openmrs.module.mycashier.api.dao;

import org.hibernate.SessionFactory;
import org.openmrs.module.mycashier.Agent;
import org.openmrs.module.mycashier.DrugInventaire;
import org.openmrs.module.mycashier.Inventaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InventaireDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Inventaire getInventaireByUuid(String uuid) {
        return null;
    }

    public Inventaire getInventaireById(Integer inventaireId) {
        return null;
    }

    public List<Inventaire> getAllInventaires() {
        return null;
    }

    public List<DrugInventaire> getAllDrugInventairesByDrug(Integer myDrugId) {
        return null;
    }

    public Inventaire saveInventaire(Inventaire inventaire) {
        return null;
    }

    public void addDrugInventaire(Integer inventaireId, Integer drugIg, Integer realQuantity, String motif) {
    }

    public void deleteDrugInventaire(Integer inventaireId, Integer drugId) {
    }

    public Inventaire deleteInventaire(Inventaire inventaire, String motif, Agent agent) {
        return null;
    }
}
