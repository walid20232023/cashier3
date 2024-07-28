package org.openmrs.module.mycashier.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
	
	// TypeService Methods
	
	public TypeService deleteTypeService(TypeService typeService) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(typeService);
		return typeService;
	}
	
	public TypeService saveTypeService(TypeService typeService) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(typeService);
		return typeService;
	}
	
	public List<TypeService> getAllTypeServices() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(TypeService.class);
		return criteria.list();
	}
	
	public TypeService getTypeServiceById(Integer typeServiceId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(TypeService.class);
		criteria.add(Restrictions.eq("id", typeServiceId));
		return (TypeService) criteria.uniqueResult();
	}
	
	public TypeService getTypeServiceByUuid(String uuid) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(TypeService.class);
		criteria.add(Restrictions.eq("uuid", uuid));
		return (TypeService) criteria.uniqueResult();
	}
	
	// Service Methods
	
	public Service deleteService(Service service) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(service);
		return service;
	}
	
	public Service saveService(Service service) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(service);
		return service;
	}
	
	public List<Service> getAllServicesByTypeService(Integer typeServiceId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Service.class);
		criteria.add(Restrictions.eq("typeService.id", typeServiceId));
		return criteria.list();
	}
	
	public List<Service> getAllServices() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Service.class);
		return criteria.list();
	}
	
	public Service getServiceByName(String serviceName) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Service.class);
		criteria.add(Restrictions.eq("name", serviceName));
		return (Service) criteria.uniqueResult();
	}
	
	public Service getServiceById(Integer serviceId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Service.class);
		criteria.add(Restrictions.eq("id", serviceId));
		return (Service) criteria.uniqueResult();
	}
	
	public Service getServiceByUuid(String uuid) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Service.class);
		criteria.add(Restrictions.eq("uuid", uuid));
		return (Service) criteria.uniqueResult();
	}
}
