package org.openmrs.module.mycashier.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.mycashier.Approvisionnement;
import org.openmrs.module.mycashier.Entrepot;
import org.openmrs.module.mycashier.StockEntrepot;
import org.openmrs.module.mycashier.VenteDrug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class EntrepotDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public Entrepot getEntrepotByUuid(String uuid) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Entrepot.class);
		criteria.add(Restrictions.eq("uuid", uuid.toLowerCase()));
		return (Entrepot) criteria.uniqueResult();
	}
	
	@Transactional
	public Entrepot getEntrepotById(Integer entrepotId) {
		return (Entrepot) sessionFactory.getCurrentSession().get(Entrepot.class, entrepotId);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Entrepot> getAllEntrepots() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Entrepot.class);
		criteria.addOrder(Order.asc("name")); // Tri par nom, changez selon votre besoin
		return (List<Entrepot>) criteria.list();
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<StockEntrepot> getAllStockEntrepotsByEntrepot(Integer entrepotId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StockEntrepot.class);
		criteria.add(Restrictions.eq("entrepotId", entrepotId));
		return (List<StockEntrepot>) criteria.list();
	}
	
	@Transactional
	public StockEntrepot getStockByEntrepotAndDrug(Integer entrepotId, Integer drugId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(StockEntrepot.class);
		criteria.add(Restrictions.eq("entrepotId", entrepotId));
		criteria.add(Restrictions.eq("myDrugId", drugId));
		return (StockEntrepot) criteria.uniqueResult();
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<VenteDrug> getAllVenteDrugByEntrepot(LocalDateTime start, LocalDateTime end) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VenteDrug.class);
		criteria.add(Restrictions.eq("entrepotId", start));
		criteria.add(Restrictions.le("dateModif", end));
		return (List<VenteDrug>) criteria.list();
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<VenteDrug> getAllVenteDrugByEntrepotAndDrug(LocalDateTime start, LocalDateTime end, Integer myDrugId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VenteDrug.class);
		criteria.add(Restrictions.eq("entrepotId", start));
		criteria.add(Restrictions.le("dateModif", end));
		criteria.add(Restrictions.eq("myDrugId", myDrugId));
		return (List<VenteDrug>) criteria.list();
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<VenteDrug> getAllDrugAvarieByEntrepot(LocalDateTime start, LocalDateTime end) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VenteDrug.class);
		criteria.add(Restrictions.eq("entrepotId", start));
		criteria.add(Restrictions.le("dateModif", end));
		return (List<VenteDrug>) criteria.list();
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Approvisionnement> getAllDrugApprovisByEntrepot(LocalDateTime start, LocalDateTime end) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Approvisionnement.class);
		criteria.add(Restrictions.eq("entrepotId", start));
		criteria.add(Restrictions.le("dateModif", end));
		return (List<Approvisionnement>) criteria.list();
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<VenteDrug> getAllDrugApprovisByEntrepotAndDrug(LocalDateTime start, LocalDateTime end, Integer myDrugId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(VenteDrug.class);
		criteria.add(Restrictions.eq("entrepotId", start));
		criteria.add(Restrictions.le("dateModif", end));
		criteria.add(Restrictions.eq("myDrugId", myDrugId));
		return (List<VenteDrug>) criteria.list();
	}
	
	@Transactional
	public Entrepot saveEntrepot(Entrepot entrepot) {
		sessionFactory.getCurrentSession().saveOrUpdate(entrepot);
		return entrepot;
	}
	
	@Transactional
	public Entrepot deleteEntrepot(Entrepot entrepot) {
		sessionFactory.getCurrentSession().delete(entrepot);
		return entrepot;
	}
}
