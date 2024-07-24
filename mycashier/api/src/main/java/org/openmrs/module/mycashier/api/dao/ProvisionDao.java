package org.openmrs.module.mycashier.api.dao;

import org.hibernate.SessionFactory;
import org.openmrs.module.mycashier.Provision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProvisionDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Provision getProvisionByUuid(String uuid) {
        return null;
    }

    public Provision getProvisionById(Integer provisionId) {
        return null;
    }

    public List<Provision> getAllProvisions() {
        return null;
    }

    public Provision saveProvision(Provision provision) {
        return null;
    }

    public Provision deleteProvision(Provision provision) {
        return null;
    }
}
