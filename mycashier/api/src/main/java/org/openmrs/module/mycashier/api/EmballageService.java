package org.openmrs.module.mycashier.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.mycashier.Emballage;
import org.openmrs.module.mycashier.MycashierConfig;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface EmballageService {
	
	@Transactional(readOnly = true)
	Emballage getEmballageByUuid(String uuid) throws APIException;
	
	@Transactional(readOnly = true)
	Emballage getEmballageById(Integer emballageId) throws APIException;
	
	@Transactional(readOnly = true)
	List<Emballage> getAllEmballages() throws APIException;
	
	@Authorized(MycashierConfig.MODULE_PRIVILEGE)
	@Transactional
	Emballage saveEmballage(Emballage emballage) throws APIException;
	
	@Authorized(MycashierConfig.MODULE_PRIVILEGE)
	@Transactional
	Emballage deleteEmballage(Emballage emballage) throws APIException;
}
