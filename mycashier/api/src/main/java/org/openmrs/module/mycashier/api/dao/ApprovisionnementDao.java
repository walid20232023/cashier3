package org.openmrs.module.mycashier.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.APIException;
import org.openmrs.module.mycashier.Approvisionnement;
import org.openmrs.module.mycashier.LigneApprovis;
import org.openmrs.module.mycashier.MyDrug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Approvisionnement getApprovisionnementByUuid(String uuid) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Approvisionnement.class);
        criteria.add(Restrictions.eq("uuid", uuid.toLowerCase()));
        return (Approvisionnement) criteria.uniqueResult();
    }

    @Transactional
    public Approvisionnement getApprovisionnementById(Integer approvisionnementId) {
        return (Approvisionnement) sessionFactory.getCurrentSession().get(Approvisionnement.class, approvisionnementId);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<Approvisionnement> getAllApprovisionnements(LocalDateTime start, LocalDateTime end) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Approvisionnement.class);
        criteria.add(Restrictions.between("dateApprovisionnement", start, end));
        return (List<Approvisionnement>) criteria.list();
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<LigneApprovis> getAllLigneApprovisByDrug(LocalDateTime start, LocalDateTime end) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(LigneApprovis.class);
        criteria.add(Restrictions.between("dateApprovisionnement", start, end));
        return (List<LigneApprovis>) criteria.list();
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<Approvisionnement> getAllApprovisionnementsByEntrepotSource(LocalDateTime start, LocalDateTime end, Integer entrepotSource) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Approvisionnement.class);
        criteria.add(Restrictions.eq("entrepotSourceId", entrepotSource));
        criteria.add(Restrictions.between("dateApprovisionnement", start, end));
        return (List<Approvisionnement>) criteria.list();
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<Approvisionnement> getAllApprovisionnementsByEntrepotCible(LocalDateTime start, LocalDateTime end, Integer entrepotCible) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Approvisionnement.class);
        criteria.add(Restrictions.eq("entrepotCibleId", entrepotCible));
        criteria.add(Restrictions.between("dateApprovisionnement", start, end));
        return (List<Approvisionnement>) criteria.list();
    }

    @Transactional
    public Approvisionnement saveApprovisionnement(Approvisionnement approvisionnement) {
        sessionFactory.getCurrentSession().saveOrUpdate(approvisionnement);
        return approvisionnement;
    }

    @Transactional
    public void addLigneApprovisToApprovisionnement(Approvisionnement approvisionnement, MyDrug myDrug, Integer quantite) {
        LigneApprovis ligneApprovis = new LigneApprovis();
        ligneApprovis.setApprovisionnement(approvisionnement);
        ligneApprovis.setMyDrug(myDrug);
        ligneApprovis.setQuantite(quantite);
        sessionFactory.getCurrentSession().saveOrUpdate(ligneApprovis);
    }

    @Transactional
    public void deleteLigneApprovisFromApprovisionnement(Integer ligneApprovisId) {
        LigneApprovis ligneApprovis = (LigneApprovis) sessionFactory.getCurrentSession().get(LigneApprovis.class, ligneApprovisId);
        if (ligneApprovis != null) {
            sessionFactory.getCurrentSession().delete(ligneApprovis);
        }
    }

    @Transactional
    public Approvisionnement deleteApprovisionnement(Approvisionnement approvisionnement) {
        sessionFactory.getCurrentSession().delete(approvisionnement);
        return approvisionnement;
    }


    @Transactional
    public void deleteLigneApprovisFRomApprovisionnement(Integer ligneApprovisId) {
        // Obtenir la session courante
        Session session = sessionFactory.getCurrentSession();

        // Créer un critère pour trouver l'entité LigneApprovis à supprimer
        LigneApprovis ligneApprovis = (LigneApprovis) session.get(LigneApprovis.class, ligneApprovisId);

        // Vérifier si l'entité a été trouvée
        if (ligneApprovis != null) {
            // Supprimer l'entité de la base de données
            session.delete(ligneApprovis);
        } else {
            // Gérer le cas où l'entité n'existe pas (si nécessaire)
            throw new APIException("LigneApprovis avec l'ID " + ligneApprovisId + " n'existe pas.");
        }
    }

}
