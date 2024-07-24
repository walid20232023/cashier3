package org.openmrs.module.mycashier.api.dao;

import org.hibernate.SessionFactory;
import org.openmrs.module.mycashier.Approvisionnement;
import org.openmrs.module.mycashier.Entrepot;
import org.openmrs.module.mycashier.StockEntrepot;
import org.openmrs.module.mycashier.VenteDrug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class EntrepotDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Entrepot getEntrepotByUuid(String uuid) {
        return null;
    }

    public Entrepot getEntrepotById(Integer entrepotId) {
        return null;
    }

    public List<Entrepot> getAllEntrepots() {
        return null;
    }

    public List<StockEntrepot> getAllStockEntrepotsByEntrepot(Integer entrepotId) {

        return null;
    }

    public StockEntrepot getStockByEntrepotAndDrug(Integer entrepotId, Integer drugId) {
        return null;
    }

    public List<VenteDrug> getAllVenteDrugByEntrepot(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    public List<VenteDrug> getAllVenteDrugByEntrepotAndDrug(LocalDateTime start, LocalDateTime end, Integer myDrugId) {
        return null;
    }

    public List<VenteDrug> getAllDrugAvarieByEntrepot(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    public List<Approvisionnement> getAllDrugApprovisByEntrepot(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    public List<VenteDrug> getAllDrugApprovisByEntrepotAndDrug(LocalDateTime start, LocalDateTime end, Integer myDrugId) {
        return null;
    }

    public Entrepot saveEntrepot(Entrepot entrepot) {
        return null;
    }

    public Entrepot deleteEntrepot(Entrepot entrepot) {
        return null;
    }
}
