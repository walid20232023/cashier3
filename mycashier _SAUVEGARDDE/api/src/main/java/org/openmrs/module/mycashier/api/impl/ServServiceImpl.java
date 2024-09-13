package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.mycashier.Service;
import org.openmrs.module.mycashier.TypeService;
import org.openmrs.module.mycashier.api.ServService;
import org.openmrs.module.mycashier.api.dao.ProvisionDao;
import org.openmrs.module.mycashier.api.dao.ServiceDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServServiceImpl extends BaseOpenmrsService implements ServService {
	
	@Autowired
	ServiceDao dao;
	
	public void setDao(ServiceDao dao) {
		this.dao = dao;
	}
	
	@Override
	public Service getServiceByUuid(String uuid) throws APIException {
		return dao.getServiceByUuid(uuid);
	}
	
	@Override
	public Service getServiceById(Integer serviceId) throws APIException {
		return dao.getServiceById(serviceId);
	}
	
	@Override
	public Service getServiceByName(String serviceName) throws APIException {
		return dao.getServiceByName(serviceName);
	}
	
	@Override
	public List<Service> getAllServices() throws APIException {
		return dao.getAllServices();
	}
	
	@Override
	public List<Service> getAllServicesByTypeService(Integer typeServiceId) throws APIException {
		return dao.getAllServicesByTypeService(typeServiceId);
	}
	
	@Override
	public Service saveService(Service service) throws APIException {
		return dao.saveService(service);
	}
	
	@Override
	public Service deleteService(Service service) throws APIException {
		return dao.deleteService(service);
	}
	
	@Override
	public TypeService getTypeServiceByUuid(String uuid) throws APIException {
		return dao.getTypeServiceByUuid(uuid);
	}
	
	@Override
	public TypeService getTypeServiceById(Integer typeServiceId) throws APIException {
		return dao.getTypeServiceById(typeServiceId);
	}
	
	@Override
	public List<TypeService> getAllTypeServices() throws APIException {
		return dao.getAllTypeServices();
	}
	
	@Override
	public TypeService saveTypeService(TypeService typeService) throws APIException {
		return dao.saveTypeService(typeService);
	}
	
	@Override
	public TypeService deleteTypeService(TypeService typeService) throws APIException {
		return dao.deleteTypeService(typeService);
	}
}
