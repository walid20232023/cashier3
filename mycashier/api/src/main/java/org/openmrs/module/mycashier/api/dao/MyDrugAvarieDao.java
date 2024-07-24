package org.openmrs.module.mycashier.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(MyDrugAvarie.class);
        criteria.add(Restrictions.eq("uuid", uuid));
        return (MyDrugAvarie) criteria.uniqueResult();
    }


    public MyDrugAvarie getMyDrugAvarieById(Integer myDrugAvarieId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(MyDrugAvarie.class);
        criteria.add(Restrictions.eq("id", myDrugAvarieId));
        return (MyDrugAvarie) criteria.uniqueResult();
    }


    public List<MyDrugAvarie> getAllMyDrugAvaries() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(MyDrugAvarie.class);
        return criteria.list();
    }


    public List<MyDrugAvarie> getAllMyDrugAvariesByEntrepot(Integer entrepotId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(MyDrugAvarie.class);
        criteria.add(Restrictions.eq("entrepot.id", entrepotId));
        return criteria.list();
    }


    public List<MyDrugAvarie> getAllMyDrugAvariesByDrug(Integer myDrugId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(MyDrugAvarie.class);
        criteria.add(Restrictions.eq("myDrug.id", myDrugId));
        return criteria.list();
    }

    public List<MyDrugAvarie> getAllMyDrugAvariesByEntrepotAndDrug(Integer myDrugId, Integer entrepotId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(MyDrugAvarie.class);
        criteria.add(Restrictions.eq("myDrug.id", myDrugId));
        criteria.add(Restrictions.eq("entrepot.id", entrepotId));
        return criteria.list();
    }


    public MyDrugAvarie saveMyDrugAvarie(MyDrugAvarie myDrugAvarie) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(myDrugAvarie);
        return myDrugAvarie;
    }


    public MyDrugAvarie deleteMyDrugAvarie(MyDrugAvarie myDrugAvarie) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(myDrugAvarie);
        return myDrugAvarie;
    }

}
