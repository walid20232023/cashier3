package org.openmrs.module.mycashier.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.mycashier.AssuranceMyDrugPrice;
import org.openmrs.module.mycashier.Emballage;
import org.openmrs.module.mycashier.MyDrug;

import org.openmrs.module.mycashier.MyDrugEmballage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("mycashier.MyDrugDao")
public class MyDrugDao {
	
	@Autowired
	private DbSessionFactory sessionFactory;
	
	public DbSessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(DbSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public MyDrug getMyDrugByUuid(String uuid) {
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MyDrug.class);
		criteria.add(Restrictions.eq("uuid", uuid));
		return (MyDrug) criteria.uniqueResult();
	}
	
	@Transactional
	public MyDrug getMyDrugById(Integer myDrugId) {
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MyDrug.class);
		criteria.add(Restrictions.eq("id", myDrugId)); // Correction ici
		return (MyDrug) criteria.uniqueResult();
	}
	
	@Transactional
	public List<MyDrug> getAllMyDrugs() {
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MyDrug.class);
		return criteria.list();
	}
	
	@Transactional
	public List<MyDrug> getAllRetiredMyDrugs() {
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(MyDrug.class);
		criteria.add(Restrictions.eq("retired", 1)); // Assuming retired is marked with 1
		return criteria.list();
	}
	
	@Transactional
	public MyDrug saveMyDrug(MyDrug myDrug) {
		DbSession session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(myDrug);
		return myDrug;
	}
	
	@Transactional
	public void saveMyDrugEmballageUnits(Integer emballageId, Integer drugId, Integer units) {
		DbSession session = sessionFactory.getCurrentSession();
		
		// Check if MyDrugEmballage entry exists
		Criteria criteria = session.createCriteria(MyDrugEmballage.class);
		criteria.createAlias("myDrug", "md");
		criteria.createAlias("emballage", "e");
		criteria.add(Restrictions.eq("md.id", drugId));
		criteria.add(Restrictions.eq("e.id", emballageId));
		
		MyDrugEmballage myDrugEmballage = (MyDrugEmballage) criteria.uniqueResult();
		
		if (myDrugEmballage != null) {
			// Update unit number
			//myDrugEmballage.setUnitNumber(units);
			session.update(myDrugEmballage);
		} else {
			// Create new MyDrugEmballage entry
			MyDrug myDrug = (MyDrug) session.get(MyDrug.class, drugId);
			Emballage emballage = (Emballage) session.get(Emballage.class, emballageId);
			
			if (myDrug == null || emballage == null) {
				throw new IllegalArgumentException("Invalid MyDrug or Emballage ID");
			}
			
			MyDrugEmballage newMyDrugEmballage = new MyDrugEmballage();
			//newMyDrugEmballage.setId(new MyDrugEmballage.MyDrugEmballageId(drugId, emballageId));
			newMyDrugEmballage.setMyDrug(myDrug);
			newMyDrugEmballage.setEmballage(emballage);
			//newMyDrugEmballage.setUnitNumber(units);
			
			session.save(newMyDrugEmballage);
		}
	}
	
	public MyDrug deleteMyDrug(MyDrug myDrug) {
		DbSession session = sessionFactory.getCurrentSession();
		
		// Delete the MyDrug entity
		session.delete(myDrug);
		
		// Return the deleted entity or null if needed
		return myDrug;
	}
	
	@Transactional
	public List<MyDrug> searchDrugs(String query) {
		// Créer un objet Criteria pour MyDrug
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(MyDrug.class);
		
		// Créer une disjonction pour combiner les conditions OR
		Disjunction disjunction = Restrictions.disjunction();
		disjunction.add(Restrictions.ilike("name", "%" + query + "%"));
		disjunction.add(Restrictions.ilike("dci", "%" + query + "%"));
		disjunction.add(Restrictions.ilike("groupeTherap", "%" + query + "%"));
		
		// Ajouter la disjonction au critère
		criteria.add(disjunction);
		
		// Exécuter la requête et retourner les résultats
		return criteria.list();
	}
	
	@Transactional
	public MyDrugEmballage getMyDrugEmballageById(Integer myDrugEmballageId) {
		DbSession session = sessionFactory.getCurrentSession();
		return (MyDrugEmballage) session.get(MyDrugEmballage.class, myDrugEmballageId);
	}
	
	@Transactional
	public List<AssuranceMyDrugPrice> searchAssuranceMyDrugPrice(String medicament, String emballage, String forme,
	        String assurance) {
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(AssuranceMyDrugPrice.class, "assuranceMyDrugPrice");
		
		// Jointure sur MyDrugEmballage pour accéder aux propriétés de MyDrug et d'Emballage
		criteria.createAlias("assuranceMyDrugPrice.myDrugEmballage", "myDrugEmballage");
		criteria.createAlias("myDrugEmballage.myDrug", "myDrug");
		criteria.createAlias("myDrugEmballage.emballage", "emballage");
		criteria.createAlias("assuranceMyDrugPrice.assurance", "assurance");
		
		// Filtrer par nom de médicament, DCI ou groupe thérapeutique
		if (medicament != null && !medicament.isEmpty()) {
			criteria.add(Restrictions.or(
			    Restrictions.ilike("myDrug.name", "%" + medicament + "%"),
			    Restrictions.or(Restrictions.ilike("myDrug.dci", "%" + medicament + "%"),
			        Restrictions.ilike("myDrug.groupeTherap", "%" + medicament + "%"))));
		}
		
		if (emballage != null && !emballage.isEmpty()) {
			criteria.add(Restrictions.like("emballage.name", "%" + emballage + "%"));
		}
		
		if (forme != null && !forme.isEmpty()) {
			criteria.add(Restrictions.like("myDrug.forme", "%" + forme + "%"));
		}
		
		if (assurance != null && !assurance.isEmpty()) {
			criteria.add(Restrictions.like("assurance.name", "%" + assurance + "%"));
		}
		
		return criteria.list();
	}
	
	@Transactional
	public AssuranceMyDrugPrice getAssuranceMyDrugPriceByMyDrugEmballageAndAssuranceId(MyDrugEmballage myDrugEmballage,
	        Integer assuranceId) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(AssuranceMyDrugPrice.class);
		
		// Ajouter les restrictions pour filtrer par myDrugEmballage et assuranceId
		criteria.add(Restrictions.eq("myDrugEmballage", myDrugEmballage));
		criteria.add(Restrictions.eq("assurance.id", assuranceId));
		
		// Retourner le premier résultat trouvé ou null s'il n'existe pas
		return (AssuranceMyDrugPrice) criteria.uniqueResult();
	}
	
	@Transactional
	public void saveMyDrugEmballage(MyDrugEmballage myDrugEmballage) {
		// Obtenir la session courante
		DbSession session = sessionFactory.getCurrentSession();
		
		// Sauvegarder ou mettre à jour l'objet myDrugEmballage
		session.saveOrUpdate(myDrugEmballage);
	}
	
	@Transactional
	public void saveAssuranceMyDrugPrice(AssuranceMyDrugPrice assuranceMyDrugPrice) {
		// Obtenir la session courante
		DbSession session = sessionFactory.getCurrentSession();
		
		// Sauvegarder ou mettre à jour l'objet myDrugEmballage
		session.saveOrUpdate(assuranceMyDrugPrice);
	}
	
	@Transactional
	public void deleteAllAssuranceMyDrigPrice(Integer myDrugEmballageId) {
		// Créer une session Hibernate
		DbSession session = sessionFactory.getCurrentSession();
		
		// Exécuter une requête native pour supprimer toutes les assurances de prix de médicaments associées à myDrugEmballageId
		String sql = "DELETE FROM assurance_my_drug_price WHERE my_drug_emballage_id = :myDrugEmballageId";
		
		Query query = session.createSQLQuery(sql);
		query.setParameter("myDrugEmballageId", myDrugEmballageId);
		
		// Exécuter la suppression
		query.executeUpdate();
	}
	
	@Transactional
	public List<AssuranceMyDrugPrice> getAllAssuranceMyDrugPrices(Integer myDrugEmballageId) {
		// Créer une session Hibernate
		DbSession session = sessionFactory.getCurrentSession();
		
		// Créer un critère pour la recherche
		Criteria criteria = session.createCriteria(AssuranceMyDrugPrice.class);
		
		// Ajouter une restriction sur myDrugEmballageId
		criteria.add(Restrictions.eq("myDrugEmballage.id", myDrugEmballageId));
		
		// Exécuter la recherche et retourner la liste des résultats
		return criteria.list();
	}
}
