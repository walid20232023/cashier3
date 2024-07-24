package org.openmrs.module.mycashier.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.mycashier.LigneVenteService;
import org.openmrs.module.mycashier.Service;
import org.openmrs.module.mycashier.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class VenteServDao{
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public VenteService getVenteServiceByUuid(String uuid) {
        // Obtention de la session Hibernate actuelle
        Session session = sessionFactory.getCurrentSession();

        // Création d'une requête Criteria pour la classe VenteService
        Criteria criteria = session.createCriteria(VenteService.class);

        // Ajout d'une restriction pour filtrer par UUID
        criteria.add(Restrictions.eq("uuid", uuid));

        // Exécution de la requête et récupération du résultat
        return (VenteService) criteria.uniqueResult();
    }

    public VenteService getVenteServiceById(Integer venteServiceId) {
        // Obtention de la session Hibernate actuelle
        Session session = sessionFactory.getCurrentSession();

        // Création d'une requête Criteria pour la classe VenteService
        Criteria criteria = session.createCriteria(VenteService.class);

        // Ajout d'une restriction pour filtrer par ID
        criteria.add(Restrictions.eq("id", venteServiceId));

        // Exécution de la requête et récupération du résultat
        return (VenteService) criteria.uniqueResult();
    }

    public List<VenteService> getAllVenteServices() {
        // Obtention de la session Hibernate actuelle
        Session session = sessionFactory.getCurrentSession();

        // Création d'une requête Criteria pour la classe VenteService
        Criteria criteria = session.createCriteria(VenteService.class);

        // Exécution de la requête et récupération des résultats sous forme de liste
        return criteria.list();
    }

    public VenteService saveVenteService(VenteService venteService) {
        // Obtention de la session Hibernate actuelle
        Session session = sessionFactory.getCurrentSession();

        // Début de la transaction
        session.beginTransaction();

        // Sauvegarde ou mise à jour de l'objet VenteService
        session.saveOrUpdate(venteService);

        // Commit de la transaction
        session.getTransaction().commit();

        // Retourner l'objet VenteService sauvegardé/ mis à jour
        return venteService;
    }
    public void addLigneToVenteService(Integer venteServiceId, Integer serviceId) {
        Session session = sessionFactory.getCurrentSession();

        // Check if the LigneVenteService entry exists
        Criteria criteria = session.createCriteria(LigneVenteService.class);
        criteria.add(Restrictions.eq("venteService.id", venteServiceId));
        criteria.add(Restrictions.eq("service.id", serviceId));
        LigneVenteService existingLigne = (LigneVenteService) criteria.uniqueResult();

        if (existingLigne == null) {
            // Create new entry
            session.beginTransaction();
            LigneVenteService newLigne = new LigneVenteService();
            newLigne.setVenteService((VenteService) session.load(VenteService.class, venteServiceId));
            newLigne.setService((Service) session.load(Service.class, serviceId));
            session.save(newLigne);
            session.getTransaction().commit();
        }
    }


    public LigneVenteService getLigneVenteServiceById(Integer venteServiceId, Integer serviceId) {
        Session session = sessionFactory.getCurrentSession();

        // Création des critères pour la recherche
        Criteria criteria = session.createCriteria(LigneVenteService.class);
        criteria.add(Restrictions.eq("venteService.id", venteServiceId));
        criteria.add(Restrictions.eq("service.id", serviceId));

        // Exécution de la requête et récupération du résultat
        return (LigneVenteService) criteria.uniqueResult();
    }

    public void deleteLigneFromVenteService(VenteService venteService, Service service) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        // Création des critères pour la recherche de la ligne à supprimer
        Criteria criteria = session.createCriteria(LigneVenteService.class);
        criteria.add(Restrictions.eq("venteService", venteService));
        criteria.add(Restrictions.eq("service", service));

        // Récupération de la ligne à supprimer
        LigneVenteService ligne = (LigneVenteService) criteria.uniqueResult();

        if (ligne != null) {
            // Suppression de la ligne trouvée
            session.delete(ligne);
        }

        session.getTransaction().commit();
    }

    public VenteService deleteVenteService(VenteService venteService) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        // Récupération de l'entité VenteService à supprimer
        VenteService existingVenteService = (VenteService) session.get(VenteService.class, venteService.getId());

        if (existingVenteService != null) {
            // Suppression de l'entité trouvée
            session.delete(existingVenteService);
        }

        session.getTransaction().commit();

        // Retourner l'objet supprimé ou null si non trouvé
        return existingVenteService;
    }

    public List<LigneVenteService> getAllLigneVenteServices() {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        // Création d'une requête Criteria pour récupérer toutes les lignes de vente de services
        Criteria criteria = session.createCriteria(LigneVenteService.class);

        // Exécution de la requête et récupération des résultats
        List<LigneVenteService> lignes = criteria.list();

        session.getTransaction().commit();

        return lignes;
    }

    public LigneVenteService saveLigneVenteService(LigneVenteService ligneVenteService) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        // Sauvegarde ou mise à jour de l'objet LigneVenteService
        session.saveOrUpdate(ligneVenteService);

        session.getTransaction().commit();

        return ligneVenteService;
    }

    public LigneVenteService deleteLigneVenteService(LigneVenteService ligneVenteService) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        // Suppression de l'objet LigneVenteService
        session.delete(ligneVenteService);

        session.getTransaction().commit();

        return ligneVenteService;
    }

}
