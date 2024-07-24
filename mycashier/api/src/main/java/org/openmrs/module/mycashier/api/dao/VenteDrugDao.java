package org.openmrs.module.mycashier.api.dao;

import org.hibernate.SessionFactory;
import org.openmrs.module.mycashier.LigneVenteDrug;
import org.openmrs.module.mycashier.VenteDrug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class VenteDrugDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public VenteDrug getVenteDrugByUuid(String uuid) {
        return null;
    }

    public VenteDrug getVenteDrugById(Integer venteDrugId) {
        return null;
    }

    public List<VenteDrug> getAllVenteDrugs() {
        return null;
    }

    public List<VenteDrug> getAllVenteDrugs(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    public List<LigneVenteDrug> getAllLIgneVenteDrugsByDrug(Integer myDrugId) {
        return null;
    }

    public VenteDrug saveVenteDrug(VenteDrug venteDrug) {
        return null;
    }

    public void addLigneToVenteDrug(Integer venteDrugId, Integer myDrugId, Integer quantity) {
    }

    public void deleteLigneFromVenteDrug(Integer venteDrugId, Integer myDrugId) {
    }

    public VenteDrug deleteVenteDrug(VenteDrug venteDrug) {
        return null;
    }
}
