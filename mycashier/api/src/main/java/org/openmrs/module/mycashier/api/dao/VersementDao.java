package org.openmrs.module.mycashier.api.dao;

import org.hibernate.SessionFactory;
import org.openmrs.module.mycashier.Versement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class VersementDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Versement getVersementByUuid(String uuid) {
        return null;
    }

    public Versement getVersementById(Integer versementId) {
        return null;
    }

    public List<Versement> getAllVersements(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    public List<Versement> getAllVersementsByAgentSource(Integer agentSourceId, LocalDateTime start, LocalDateTime end) {
  return null;
    }

    public List<Versement> getAllVersementsByAgentCible(Integer agentCibleId, LocalDateTime start, LocalDateTime end) {
    return null;

    }

    public Versement saveVersement(Versement versement) {
   return null;
    }

    public Versement deleteVersement(Versement versement, String motif) {
    return null;

    }
}
