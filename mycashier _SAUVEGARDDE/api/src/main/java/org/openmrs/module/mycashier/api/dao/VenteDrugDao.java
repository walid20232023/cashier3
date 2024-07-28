package org.openmrs.module.mycashier.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.mycashier.LigneVenteDrug;
import org.openmrs.module.mycashier.MyDrug;
import org.openmrs.module.mycashier.VenteDrug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class VenteDrugDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public VenteDrug getVenteDrugByUuid(String uuid) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(VenteDrug.class);
		criteria.add(Restrictions.eq("uuid", uuid));
		return (VenteDrug) criteria.uniqueResult();
	}
	
	public VenteDrug getVenteDrugById(Integer venteDrugId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(VenteDrug.class);
		criteria.add(Restrictions.eq("id", venteDrugId));
		return (VenteDrug) criteria.uniqueResult();
	}
	
	public List<VenteDrug> getAllVenteDrugs() {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(VenteDrug.class);
		return criteria.list();
	}
	
	public List<VenteDrug> getAllVenteDrugs(LocalDateTime start, LocalDateTime end) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(VenteDrug.class);
		criteria.add(Restrictions.between("dateVente", start, end));
		return criteria.list();
	}
	
	public List<LigneVenteDrug> getAllLigneVenteDrugsByDrug(Integer myDrugId) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(LigneVenteDrug.class);
		criteria.add(Restrictions.eq("myDrug.id", myDrugId));
		return criteria.list();
	}
	
	public VenteDrug saveVenteDrug(VenteDrug venteDrug) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(venteDrug);
		return venteDrug;
	}
	
	public void addLigneToVenteDrug(Integer venteDrugId, Integer myDrugId, Integer quantity) {
		Session session = sessionFactory.getCurrentSession();
		
		// Check if the LigneVenteDrug entry exists
		Criteria criteria = session.createCriteria(LigneVenteDrug.class);
		criteria.add(Restrictions.eq("venteDrug.id", venteDrugId));
		criteria.add(Restrictions.eq("myDrug.id", myDrugId));
		LigneVenteDrug ligneVenteDrug = (LigneVenteDrug) criteria.uniqueResult();
		
		if (ligneVenteDrug != null) {
			// If exists, handle updating quantity if needed (assumed to be handled elsewhere)
			// No implementation for quantity update in the current method
		} else {
			// Create new entry
			session.beginTransaction();
			LigneVenteDrug newLigneVenteDrug = new LigneVenteDrug();
			newLigneVenteDrug.setVenteDrug((VenteDrug) session.load(VenteDrug.class, venteDrugId));
			newLigneVenteDrug.setMyDrug((MyDrug) session.load(MyDrug.class, myDrugId));
			// Set quantity if needed (assumed to be handled elsewhere)
			session.save(newLigneVenteDrug);
			session.getTransaction().commit();
		}
	}
	
	public void deleteLigneFromVenteDrug(Integer venteDrugId, Integer myDrugId) {
		Session session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(LigneVenteDrug.class);
		criteria.add(Restrictions.eq("venteDrug.id", venteDrugId));
		criteria.add(Restrictions.eq("myDrug.id", myDrugId));
		LigneVenteDrug ligneVenteDrug = (LigneVenteDrug) criteria.uniqueResult();
		
		if (ligneVenteDrug != null) {
			session.beginTransaction();
			session.delete(ligneVenteDrug);
			session.getTransaction().commit();
		}
	}
	
	public VenteDrug deleteVenteDrug(VenteDrug venteDrug) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.delete(venteDrug);
		session.getTransaction().commit();
		return venteDrug;
	}
	
}
