package org.openmrs.module.mycashier.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.mycashier.Entrepot;
import org.openmrs.module.mycashier.MyDrug;
import org.openmrs.module.mycashier.StockEntrepot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository("mycashier.EntrepotDao ")
public class EntrepotDao {
	
	@Autowired
	private DbSessionFactory sessionFactory;
	
	public DbSessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(DbSessionFactory sessionFactory) {
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
		criteria.createAlias("myDrug", "md");
		criteria.createAlias("entrepot", "e");
		criteria.add(Restrictions.eq("md.id", drugId));
		criteria.add(Restrictions.eq("e.id", entrepotId));
		
		return (StockEntrepot) criteria.uniqueResult();
	}
	
	/* @Transactional
	 * @SuppressWarnings("unchecked") public List<VenteDrug> getAllVenteDrugByEntrepot(LocalDateTime
	 *                                start, LocalDateTime end) { Criteria criteria =
	 *                                sessionFactory.
	 *                                getCurrentSession().createCriteria(VenteDrug.class);
	 *                                criteria.add(Restrictions.eq("entrepotId", start));
	 *                                criteria.add(Restrictions.le("dateModif", end)); return
	 *                                (List<VenteDrug>) criteria.list(); }
	 * @Transactional
	 * @SuppressWarnings("unchecked") public List<VenteDrug>
	 *                                getAllVenteDrugByEntrepotAndDrug(LocalDateTime start,
	 *                                LocalDateTime end, Integer myDrugId) { Criteria criteria =
	 *                                sessionFactory
	 *                                .getCurrentSession().createCriteria(VenteDrug.class);
	 *                                criteria.add(Restrictions.eq("entrepotId", start));
	 *                                criteria.add(Restrictions.le("dateModif", end));
	 *                                criteria.add(Restrictions.eq("myDrugId", myDrugId)); return
	 *                                (List<VenteDrug>) criteria.list(); }
	 * @Transactional
	 * @SuppressWarnings("unchecked") public List<VenteDrug>
	 *                                getAllDrugAvarieByEntrepot(LocalDateTime start, LocalDateTime
	 *                                end) { Criteria criteria =
	 *                                sessionFactory.getCurrentSession().createCriteria
	 *                                (VenteDrug.class); criteria.add(Restrictions.eq("entrepotId",
	 *                                start)); criteria.add(Restrictions.le("dateModif", end));
	 *                                return (List<VenteDrug>) criteria.list(); }
	 * @Transactional
	 * @SuppressWarnings("unchecked") public List<Approvisionnement>
	 *                                getAllDrugApprovisByEntrepot(LocalDateTime start,
	 *                                LocalDateTime end) { Criteria criteria =
	 *                                sessionFactory.getCurrentSession
	 *                                ().createCriteria(Approvisionnement.class);
	 *                                criteria.add(Restrictions.eq("entrepotId", start));
	 *                                criteria.add(Restrictions.le("dateModif", end)); return
	 *                                (List<Approvisionnement>) criteria.list(); }
	 * @Transactional
	 * @SuppressWarnings("unchecked") public List<VenteDrug>
	 *                                getAllDrugApprovisByEntrepotAndDrug(LocalDateTime start,
	 *                                LocalDateTime end, Integer myDrugId) { Criteria criteria =
	 *                                sessionFactory
	 *                                .getCurrentSession().createCriteria(VenteDrug.class);
	 *                                criteria.add(Restrictions.eq("entrepotId", start));
	 *                                criteria.add(Restrictions.le("dateModif", end));
	 *                                criteria.add(Restrictions.eq("myDrugId", myDrugId)); return
	 *                                (List<VenteDrug>) criteria.list(); }
	 **/
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
	
	@Transactional
	public void saveDrugQuantityForEntrepot(MyDrug myDrug, Entrepot entrepot, Integer quantity) {
		DbSession session = sessionFactory.getCurrentSession();
		
		// Check if StockEntrepot entry exists
		Criteria criteria = session.createCriteria(StockEntrepot.class);
		criteria.add(Restrictions.eq("id.entrepotId", entrepot.getId()));
		criteria.add(Restrictions.eq("id.myDrugId", myDrug.getId()));
		
		StockEntrepot stockEntrepot = (StockEntrepot) criteria.uniqueResult();
		
		if (stockEntrepot != null) {
			// Update quantity
			stockEntrepot.setQuantiteStock(quantity);
			stockEntrepot.setLocalDateTime(LocalDateTime.now());
			session.update(stockEntrepot);
		} else {
			// Create new StockEntrepot entry
			StockEntrepot newStockEntrepot = new StockEntrepot();
			newStockEntrepot.setQuantiteStock(quantity);
			newStockEntrepot.setLocalDateTime(LocalDateTime.now());
			
			session.save(newStockEntrepot);
		}
	}
	
	@Transactional
	public Integer getStockByMyDrugEmballage(Integer myDrugEmballageId, Integer entrepotId) {
		DbSession session = sessionFactory.getCurrentSession();
		
		Criteria criteria = session.createCriteria(StockEntrepot.class)
		        .add(Restrictions.eq("myDrugEmballage.id", myDrugEmballageId))
		        .add(Restrictions.eq("entrepot.id", entrepotId)).setProjection(Projections.property("quantiteStock"));
		
		return (Integer) criteria.uniqueResult();
	}
	
	@Transactional
	public StockEntrepot getStockEntrepotByDrugEmballageAndEntrepot(Integer myDrugEmballageId, Integer entrepotId,
	        String numeroLot) {
		DbSession session = sessionFactory.getCurrentSession();
		
		// Création de la requête Criteria avec les restrictions spécifiées
		Criteria criteria = session.createCriteria(StockEntrepot.class)
		        .add(Restrictions.eq("myDrugEmballage.id", myDrugEmballageId))
		        .add(Restrictions.eq("entrepot.id", entrepotId)).add(Restrictions.eq("numeroLot", numeroLot));
		
		// Retourner le premier résultat trouvé
		return (StockEntrepot) criteria.uniqueResult();
	}
	
	@Transactional
	public List<StockEntrepot> searchStockEntrepot(String medicament, Integer entrepotId, Integer assuranceId,
	        String numeroLot, Integer emballageId, String forme, String perimeAvant) {
		// Initialiser la session et la liste de résultats
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(StockEntrepot.class, "stockEntrepot");
		
		// Jointure pour accéder aux propriétés du médicament
		criteria.createAlias("stockEntrepot.myDrugEmballage", "myDrugEmballage");
		criteria.createAlias("myDrugEmballage.myDrug", "myDrug");
		
		// Filtrer par nom de médicament, DCI ou groupe thérapeutique
		if (medicament != null && !medicament.isEmpty()) {
			criteria.add(Restrictions.or(
			    Restrictions.ilike("myDrug.name", "%" + medicament + "%"),
			    Restrictions.or(Restrictions.ilike("myDrug.dci", "%" + medicament + "%"),
			        Restrictions.ilike("myDrug.groupeTherap", "%" + medicament + "%"))));
		}
		
		// Filtrer par entrepôt
		if (entrepotId != null) {
			criteria.add(Restrictions.eq("stockEntrepot.entrepot.id", entrepotId));
		}
		
		// Filtrer par assurance
		if (assuranceId != null) {
			criteria.createAlias("stockEntrepot.client", "client");
			criteria.createAlias("client.assuranceList", "assurance");
			criteria.add(Restrictions.eq("assurance.id", assuranceId));
		}
		
		// Filtrer par numéro de lot
		if (numeroLot != null && !numeroLot.isEmpty()) {
			criteria.add(Restrictions.ilike("stockEntrepot.numeroLot", "%" + numeroLot + "%"));
		}
		
		// Filtrer par emballage
		if (emballageId != null) {
			criteria.add(Restrictions.eq("myDrugEmballage.emballage.id", emballageId));
		}
		
		// Filtrer par forme
		if (forme != null && !forme.isEmpty()) {
			criteria.add(Restrictions.ilike("myDrug.forme", "%" + forme + "%"));
		}
		
		// Filtrer par date de péremption antérieure à perimeAvant
		if (perimeAvant != null && !perimeAvant.isEmpty()) {
			LocalDate expirationDate = LocalDate.parse(perimeAvant);
			criteria.add(Restrictions.lt("stockEntrepot.datePeremption", expirationDate));
		}
		
		// Exécuter la requête et retourner les résultats
		return criteria.list();
	}
	
}
