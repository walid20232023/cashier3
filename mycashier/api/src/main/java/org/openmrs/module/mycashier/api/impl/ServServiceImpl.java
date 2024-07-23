package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.module.mycashier.Service;
import org.openmrs.module.mycashier.TypeService;
import org.openmrs.module.mycashier.api.ServService;

import java.util.List;

public class ServServiceImpl implements ServService {
    @Override
    public Service getServiceByUuid(String uuid) throws APIException {
        return null;
    }

    @Override
    public Service getServiceById(Integer serviceId) throws APIException {
        return null;
    }

    @Override
    public Service getServiceByName(String serviceName) throws APIException {
        return null;
    }

    @Override
    public List<Service> getAllServices() throws APIException {
        return null;
    }

    @Override
    public List<Service> getAllServicesByTypeService(Integer typeServiceId) throws APIException {
        return null;
    }

    @Override
    public Service saveService(Service service) throws APIException {
        return null;
    }

    @Override
    public Service deleteService(Service service) throws APIException {
        return null;
    }

    @Override
    public TypeService getTypeServiceByUuid(String uuid) throws APIException {
        return null;
    }

    @Override
    public TypeService getTypeServiceById(Integer typeServiceId) throws APIException {
        return null;
    }

    @Override
    public List<TypeService> getAllTypeServices() throws APIException {
        return null;
    }

    @Override
    public TypeService saveTypeService(TypeService typeService) throws APIException {
        return null;
    }

    @Override
    public TypeService deleteTypeService(TypeService typeService) throws APIException {
        return null;
    }
}
