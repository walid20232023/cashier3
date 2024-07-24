package org.openmrs.module.mycashier.api.dao;

import org.hibernate.SessionFactory;
import org.openmrs.module.mycashier.Approvisionnement;
import org.openmrs.module.mycashier.LigneApprovis;
import org.openmrs.module.mycashier.MyDrug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ApprovisionnementDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Approvisionnement getApprovisionnementByUuid(String uuid) {
        return null;
    }

    public Approvisionnement getApprovisionnementById(Integer approvisionnementId) {
        return null;
    }

    public List<Approvisionnement> getAllApprovisionnements(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    public List<LigneApprovis> getAllLigneApprovisByDrug(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    public List<Approvisionnement> getAllApprovisionnementsByEntrepotSource(LocalDateTime start, LocalDateTime end, Integer entrepotSource) {
   return null;
    }

    public List<Approvisionnement> getAllApprovisionnementsByEntrepotCible(LocalDateTime start, LocalDateTime end, Integer entrepotCible) {

    return null;
    }

    public Approvisionnement saveApprovisionnement(Approvisionnement approvisionnement) {

        return null;
    }

    public void addLigneApprovisToApprovisionnement(Approvisionnement approvisionnement, MyDrug myDrug, Integer quantite) {


    }

    public void deleteLigneApprovisFRomApprovisionnement(Integer ligneApprovisId) {
    }

    public Approvisionnement deleteApprovisionnement(Approvisionnement approvisionnement, String motif) {
        return null;
    }
}
