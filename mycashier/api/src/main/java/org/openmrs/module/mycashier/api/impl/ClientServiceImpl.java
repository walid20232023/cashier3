package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.module.mycashier.Assurance;
import org.openmrs.module.mycashier.Client;
import org.openmrs.module.mycashier.api.ClientService;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    @Override
    public Client getClientByUuid(String uuid) throws APIException {
        return null;
    }

    @Override
    public Client getClientById(Integer clientId) throws APIException {
        return null;
    }

    @Override
    public Client saveClient(Client client) throws APIException {
        return null;
    }

    @Override
    public void addAssuranceToClient(Assurance assurance, Client client) throws APIException {

    }

    @Override
    public void deleteAssuranceFromClient(Assurance assurance, Client client) throws APIException {

    }

    @Override
    public Client deleteClient(Client client) throws APIException {
        return null;
    }

    @Override
    public List<Assurance> getAssurancesByClient(Client client) throws APIException {
        return null;
    }

    @Override
    public List<Client> getAllClients() throws APIException {
        return null;
    }
}
