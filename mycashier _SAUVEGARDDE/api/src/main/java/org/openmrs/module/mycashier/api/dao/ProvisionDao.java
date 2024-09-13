package org.openmrs.module.mycashier.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.mycashier.Provision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProvisionDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	// Get Provision by UUID
	public Provision getProvisionByUuid(String uuid) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Provision.class);
		criteria.add(Restrictions.eq("uuid", uuid));
		return (Provision) criteria.uniqueResult();
	}
	
	// Get Provision by ID
	public Provision getProvisionById(Integer provisionId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Provision.class);
		criteria.add(Restrictions.eq("id", provisionId));
		return (Provision) criteria.uniqueResult();
	}
	
	// Get All Provisions
	public List<Provision> getAllProvisions() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Provision.class);
		return criteria.list();
	}
	
	// Save or Update Provision
	public Provision saveProvision(Provision provision) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(provision);
		return provision;
	}
	
	// Delete Provision
	public Provision deleteProvision(Provision provision) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(provision);
		return provision;
	}
}
