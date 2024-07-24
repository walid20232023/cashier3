package org.openmrs.module.mycashier.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.mycashier.Assurance;
import org.openmrs.module.mycashier.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ClientDao {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public Client getClientByUuid(String uuid) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Client.class);
        criteria.add(Restrictions.eq("uuid", uuid.toLowerCase()));
        return (Client) criteria.uniqueResult();
    }

    @Transactional
    public Client getClientById(Integer clientId) {
        return (Client) sessionFactory.getCurrentSession().get(Client.class, clientId);
    }

    @Transactional
    public Client saveClient(Client client) {
        sessionFactory.getCurrentSession().saveOrUpdate(client);
        return client;
    }

    @Transactional
    public void addAssuranceToClient(Assurance assurance, Client client) {
        Session session = sessionFactory.getCurrentSession();
        // Ajout d'une assurance au client via la table client_assurance
        session.createSQLQuery("INSERT INTO client_assurance (client_id, assurance_id) VALUES (:clientId, :assuranceId)")
                .setParameter("clientId", client.getId())
                .setParameter("assuranceId", assurance.getId())
                .executeUpdate();
    }

    @Transactional
    public void deleteAssuranceFromClient(Assurance assurance, Client client) {
        Session session = sessionFactory.getCurrentSession();
        // Suppression de l'assurance du client via la table client_assurance
        session.createSQLQuery("DELETE FROM client_assurance WHERE client_id = :clientId AND assurance_id = :assuranceId")
                .setParameter("clientId", client.getId())
                .setParameter("assuranceId", assurance.getId())
                .executeUpdate();
    }

    @Transactional
    public Client deleteClient(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(client);
        return client;
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<Assurance> getAssurancesByClient(Client client) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Assurance.class, "assurance");
        criteria.createAlias("clientAssurances", "clientAssurance");
        criteria.add(Restrictions.eq("clientAssurance.client.id", client.getId()));
        return (List<Assurance>) criteria.list();
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<Client> getAllClients() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Client.class);
        criteria.addOrder(Order.asc("name")); // Tri par nom, changez selon votre besoin
        return (List<Client>) criteria.list();
    }
}
