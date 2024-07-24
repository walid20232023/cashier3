package org.openmrs.module.mycashier.api.dao;


import org.hibernate.SessionFactory;
import org.openmrs.module.mycashier.Emballage;
import org.openmrs.module.mycashier.MyDrug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyDrugDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public MyDrug getMyDrugByUuid(String uuid) {
        return null;
    }

    public MyDrug getMyDrugById(Integer myDrugId) {
        return null;
    }

    public List<MyDrug> getAllMyDrugs() {
        return null;
    }

    public List<MyDrug> getAllRetiredMyDrugs() {
        return null;
    }

    public MyDrug saveMyDrug(MyDrug myDrug) {
        return null;
    }

    public void saveMyDrugEmballageUnits(Emballage emballage, Integer units) {
    }

    public Integer getMyDrugEmballageUnits(Emballage emballage, MyDrug myDrug) {
        return null;
    }

    public MyDrug deleteMyDrug(MyDrug myDrug) {
        return null;
    }
}
