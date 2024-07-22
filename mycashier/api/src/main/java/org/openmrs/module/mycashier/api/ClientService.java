package org.openmrs.module.mycashier.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.mycashier.Assurance;
import org.openmrs.module.mycashier.Client;
import org.openmrs.module.mycashier.MycashierConfig;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
public interface ClientService extends OpenmrsService {
	
	@Transactional(readOnly = true)
	Client getClientByUuid(String uuid) throws APIException;
	
	@Transactional(readOnly = true)
	Client getClientById(Integer clientId) throws APIException;
	
	@Authorized(MycashierConfig.MODULE_PRIVILEGE)
	@Transactional
	Client saveClient(Client client) throws APIException;
	
	@Transactional
	void addAssuranceToClient(Assurance assurance, Client client) throws APIException;
	
	@Transactional
	void deleteAssuranceFromClient(Assurance assurance, Client client) throws APIException;
	
	@Authorized(MycashierConfig.MODULE_PRIVILEGE)
	@Transactional
	Client deleteClient(Client client) throws APIException;
	
	@Transactional(readOnly = true)
	List<Assurance> getAssurancesByClient(Client client) throws APIException;
	
	@Transactional(readOnly = true)
	List<Client> getAllClients() throws APIException;
	
}
