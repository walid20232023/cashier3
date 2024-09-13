package org.openmrs.module.mycashier.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.mycashier.Assurance;
import org.openmrs.module.mycashier.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class AssuranceDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public Assurance getAssuranceByUuid(String uuid) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Assurance.class);
		criteria.add(Restrictions.eq("uuid", uuid.toLowerCase()));
		return (Assurance) criteria.uniqueResult();
	}
	
	@Transactional
	public Assurance getAssuranceById(Integer assuranceId) {
		return (Assurance) sessionFactory.getCurrentSession().get(Assurance.class, assuranceId);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Assurance> getAllAssurances() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Assurance.class);
		criteria.addOrder(Order.asc("name")); // Tri par nom, changez selon votre besoin
		return (List<Assurance>) criteria.list();
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Assurance> getAllClientsByAssurance(Assurance assurance) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Client.class, "client");
		criteria.createAlias("client.assurances", "assurance");
		criteria.add(Restrictions.eq("assurance.id", assurance.getId()));
		return (List<Assurance>) criteria.list();
	}
	
	@Transactional
	public Assurance saveAssurance(Assurance assurance) {
		sessionFactory.getCurrentSession().saveOrUpdate(assurance);
		return assurance;
	}
	
	@Transactional
	public Assurance deleteAssurance(Assurance assurance) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(assurance);
		return assurance;
	}
}
