package org.openmrs.module.mycashier.api.dao;

import org.hibernate.SessionFactory;
import org.openmrs.module.mycashier.Assurance;
import org.openmrs.module.mycashier.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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


    public Client getClientByUuid(String uuid) {
        return null;
    }

    public Client getClientById(Integer clientId) {

        return null;
    }

    public Client saveClient(Client client) {

        return null;
    }

    public void addAssuranceToClient(Assurance assurance, Client client) {
    }

    public void deleteAssuranceFromClient(Assurance assurance, Client client) {
    }

    public Client deleteClient(Client client) {
        return null;
    }

    public List<Assurance> getAssurancesByClient(Client client) {
        return null;
    }

    public List<Client> getAllClients() {
        return null;
    }
}
