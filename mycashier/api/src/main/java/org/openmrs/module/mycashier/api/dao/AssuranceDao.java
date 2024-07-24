package org.openmrs.module.mycashier.api.dao;
import org.hibernate.SessionFactory;
import org.openmrs.module.mycashier.Assurance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AssuranceDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Assurance getAssuranceByUuid(String uuid) {
        return null;
    }

    public Assurance getAssuranceById(Integer assuranceId) {
        return null;
    }

    public List<Assurance> getAllAssurances() {

        return null;
    }

    public List<Assurance> getAllClientsByAssurance(Assurance assurance) {
        return null;
    }

    public Assurance saveAssurance(Assurance assurance) {

        return null;
    }

    public Assurance deleteAssurance(Assurance assurance) {

        return null;
    }
}
