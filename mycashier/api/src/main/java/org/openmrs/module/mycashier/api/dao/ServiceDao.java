package org.openmrs.module.mycashier.api.dao;

import org.hibernate.SessionFactory;
import org.openmrs.module.mycashier.Service;
import org.openmrs.module.mycashier.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public TypeService deleteTypeService(TypeService typeService) {
        return null;
    }

    public TypeService saveTypeService(TypeService typeService) {
        return null;
    }

    public List<TypeService> getAllTypeServices() {
        return null;
    }

    public TypeService getTypeServiceById(Integer typeServiceId) {
        return null;
    }

    public TypeService getTypeServiceByUuid(String uuid) {
        return null;
    }

    public Service deleteService(Service service) {
        return null;
    }

    public Service saveService(Service service) {
        return null;
    }

    public List<Service> getAllServicesByTypeService(Integer typeServiceId) {
        return null;
    }

    public List<Service> getAllServices() {
        return null;
    }

    public Service getServiceByName(String serviceName) {
        return null;
    }

    public Service getServiceById(Integer serviceId) {
        return null;
    }

    public Service getServiceByUuid(String uuid) {
        return null;
    }
}
