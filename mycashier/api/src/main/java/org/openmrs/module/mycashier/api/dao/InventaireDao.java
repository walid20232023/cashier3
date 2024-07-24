package org.openmrs.module.mycashier.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.mycashier.Agent;
import org.openmrs.module.mycashier.DrugInventaire;
import org.openmrs.module.mycashier.Inventaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class InventaireDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Transactional
    public Inventaire getInventaireByUuid(String uuid) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Inventaire.class);
        criteria.add(Restrictions.eq("uuid", uuid));

        return (Inventaire) criteria.uniqueResult();
    }

    @Transactional
    public Inventaire getInventaireById(Integer inventaireId) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Inventaire.class);
        criteria.add(Restrictions.eq("id", inventaireId));

        return (Inventaire) criteria.uniqueResult();
    }


    @Transactional
    public List<Inventaire> getAllInventaires() {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(Inventaire.class);

        return criteria.list();
    }


    @Transactional
    public List<DrugInventaire> getAllDrugInventairesByDrug(Integer myDrugId) {
        Session session = sessionFactory.getCurrentSession();

        Criteria criteria = session.createCriteria(DrugInventaire.class);
        criteria.add(Restrictions.eq("myDrug.id", myDrugId));

        return criteria.list();
    }


    @Transactional
    public void addDrugInventaire(Integer inventaireId, Integer drugId, Integer realQuantity, String motif) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Check if the DrugInventaire already exists
            Criteria criteria = session.createCriteria(DrugInventaire.class);
            criteria.add(Restrictions.eq("id.myDrugId", drugId));
            criteria.add(Restrictions.eq("id.inventaireId", inventaireId));
            DrugInventaire existingDrugInventaire = (DrugInventaire) criteria.uniqueResult();

            if (existingDrugInventaire != null) {
                // Update existing DrugInventaire
                existingDrugInventaire.setRealQuantity(realQuantity);
                existingDrugInventaire.setMotif(motif);
                session.update(existingDrugInventaire);
            } else {
                // Create new DrugInventaire
                DrugInventaire newDrugInventaire = new DrugInventaire();
                DrugInventaire.DrugInventaireId id = new DrugInventaire.DrugInventaireId(drugId, inventaireId);
                newDrugInventaire.setId(id);
                newDrugInventaire.setRealQuantity(realQuantity);
                newDrugInventaire.setMotif(motif);
                session.save(newDrugInventaire);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e; // or handle exception as needed
        }
    }






    public Inventaire deleteInventaire(Inventaire inventaire, String motif, Agent agent) {
        return null;
    }


    @Transactional
    public void deleteDrugInventaire(Integer inventaireId, Integer drugId) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            // Check if the DrugInventaire exists
            Criteria criteria = session.createCriteria(DrugInventaire.class);
            criteria.add(Restrictions.eq("id.myDrugId", drugId));
            criteria.add(Restrictions.eq("id.inventaireId", inventaireId));
            DrugInventaire drugInventaire = (DrugInventaire) criteria.uniqueResult();

            if (drugInventaire != null) {
                // Delete the DrugInventaire
                session.delete(drugInventaire);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e; // or handle exception as needed
        }
    }

    public Inventaire saveInventaire(Inventaire inventaire) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(inventaire);
        return inventaire;
    }

}

