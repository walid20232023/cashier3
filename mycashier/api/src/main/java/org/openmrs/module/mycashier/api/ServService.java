package org.openmrs.module.mycashier.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.mycashier.Service;
import org.openmrs.module.mycashier.MycashierConfig;
import org.openmrs.module.mycashier.TypeService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface ServService extends OpenmrsService {
	
	@Transactional(readOnly = true)
	Service getServiceByUuid(String uuid) throws APIException;
	
	@Transactional(readOnly = true)
	Service getServiceById(Integer serviceId) throws APIException;
	
	@Transactional(readOnly = true)
	Service getServiceByName(String serviceName) throws APIException;
	
	@Transactional(readOnly = true)
	List<Service> getAllServices() throws APIException;
	
	@Transactional(readOnly = true)
	List<Service> getAllServicesByTypeService(Integer typeServiceId) throws APIException;
	
	@Authorized(MycashierConfig.MODULE_PRIVILEGE)
	@Transactional
	Service saveService(Service service) throws APIException;
	
	@Authorized(MycashierConfig.MODULE_PRIVILEGE)
	@Transactional
	Service deleteService(Service service) throws APIException;
	
	@Transactional(readOnly = true)
	TypeService getTypeServiceByUuid(String uuid) throws APIException;
	
	@Transactional(readOnly = true)
	TypeService getTypeServiceById(Integer typeServiceId) throws APIException;
	
	@Transactional(readOnly = true)
	List<TypeService> getAllTypeServices() throws APIException;
	
	@Authorized(MycashierConfig.MODULE_PRIVILEGE)
	@Transactional
	TypeService saveTypeService(TypeService typeService) throws APIException;
	
	@Authorized(MycashierConfig.MODULE_PRIVILEGE)
	@Transactional
	TypeService deleteTypeService(TypeService typeService) throws APIException;
}
