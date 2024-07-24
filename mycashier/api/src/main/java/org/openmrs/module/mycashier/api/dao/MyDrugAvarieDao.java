package org.openmrs.module.mycashier.api.dao;

import org.hibernate.SessionFactory;
import org.openmrs.module.mycashier.MyDrugAvarie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyDrugAvarieDao{
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public MyDrugAvarie getMyDrugAvarieByUuid(String uuid) {
        return null;
    }

    public MyDrugAvarie getMyDrugAvarieById(Integer myDrugAvarieId) {
        return null;
    }

    public List<MyDrugAvarie> getAllMyDrugAvaries() {
        return null;
    }

    public List<MyDrugAvarie> getAllMyDrugAvariesByEntrepot(Integer entrepotId) {
        return null;
    }

    public List<MyDrugAvarie> getAllMyDrugAvariesByDrug(Integer mydrugId) {
        return null;
    }

    public List<MyDrugAvarie> getAllMyDrugAvariesByEntrepotAndDrug(Integer mydrugId, Integer entrepotId) {
        return null;
    }

    public MyDrugAvarie saveMyDrugAvarie(MyDrugAvarie myDrugAvarie) {
        return null;
    }

    public MyDrugAvarie deleteMyDrugAvarie(MyDrugAvarie myDrugAvarie) {
        return null;
    }
}
