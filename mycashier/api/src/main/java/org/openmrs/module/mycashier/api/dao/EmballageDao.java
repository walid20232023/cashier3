package org.openmrs.module.mycashier.api.dao;

import org.hibernate.SessionFactory;
import org.openmrs.module.mycashier.Emballage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmballageDao{
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Emballage getEmballageByUuid(String uuid) {
        return null;
    }

    public Emballage getEmballageById(Integer emballageId) {
        return null;
    }

    public List<Emballage> getAllEmballages() {
        return null;
    }

    public Emballage saveEmballage(Emballage emballage) {
        return null;
    }

    public Emballage deleteEmballage(Emballage emballage) {
        return null;
    }
}
