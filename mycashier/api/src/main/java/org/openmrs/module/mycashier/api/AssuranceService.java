package org.openmrs.module.mycashier.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.mycashier.Assurance;
import org.openmrs.module.mycashier.MycashierConfig;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface AssuranceService extends OpenmrsService {
	
	/**
	 * Retrieves an assurance by its UUID. It is fetched in a read-only transaction.
	 * 
	 * @param uuid the UUID of the assurance
	 * @return the Assurance with the specified UUID
	 * @throws APIException if there is an error retrieving the assurance
	 */
	@Transactional(readOnly = true)
	Assurance getAssuranceByUuid(String uuid) throws APIException;
	
	/**
	 * Retrieves an assurance by its ID. It is fetched in a read-only transaction.
	 * 
	 * @param assuranceId the ID of the assurance
	 * @return the Assurance with the specified ID
	 * @throws APIException if there is an error retrieving the assurance
	 */
	@Transactional(readOnly = true)
	Assurance getAssuranceById(Integer assuranceId) throws APIException;
	
	/**
	 * Retrieves all assurances. It is fetched in a read-only transaction.
	 * 
	 * @return a list of all Assurances
	 * @throws APIException if there is an error retrieving the assurances
	 */
	@Transactional(readOnly = true)
	List<Assurance> getAllAssurances() throws APIException;
	
	@Transactional(readOnly = true)
	List<Assurance> getAllClientsByAssurance(Assurance assurance) throws APIException;
	
	/**
	 * Saves an assurance. Sets the owner to superuser if it is not set. It can be called by users
	 * with this module's privilege. It is executed in a transaction.
	 * 
	 * @param assurance the Assurance to save
	 * @return the saved Assurance
	 * @throws APIException if there is an error saving the assurance
	 */
	@Authorized(MycashierConfig.MODULE_PRIVILEGE)
	@Transactional
	Assurance saveAssurance(Assurance assurance) throws APIException;
	
	/**
	 * Deletes an assurance. It can be called by users with this module's privilege. It is executed
	 * in a transaction.
	 * 
	 * @param assurance the Assurance to delete
	 * @return the deleted Assurance
	 * @throws APIException if there is an error deleting the assurance
	 */
	@Authorized(MycashierConfig.MODULE_PRIVILEGE)
	@Transactional
	Assurance deleteAssurance(Assurance assurance) throws APIException;
}
