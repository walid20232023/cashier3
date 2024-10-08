package org.openmrs.module.mycashier.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.mycashier.LigneVenteDrug;
import org.openmrs.module.mycashier.MyDrug;
import org.openmrs.module.mycashier.VenteDrug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository("mycashier.VenteDrugDao")
public class VenteDrugDao {
	
	@Autowired
	private DbSessionFactory sessionFactory;
	
	public DbSessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(DbSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public VenteDrug getVenteDrugByUuid(String uuid) {
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(VenteDrug.class);
		criteria.add(Restrictions.eq("uuid", uuid));
		return (VenteDrug) criteria.uniqueResult();
	}
	
	@Transactional
	public VenteDrug getVenteDrugById(Integer venteDrugId) {
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(VenteDrug.class);
		criteria.add(Restrictions.eq("id", venteDrugId));
		return (VenteDrug) criteria.uniqueResult();
	}
	
	@Transactional
	public List<VenteDrug> getAllVenteDrugs() {
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(VenteDrug.class);
		return criteria.list();
	}
	
	@Transactional
	public List<VenteDrug> getAllVenteDrugs(LocalDateTime start, LocalDateTime end) {
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(VenteDrug.class);
		criteria.add(Restrictions.between("dateVente", start, end));
		return criteria.list();
	}
	
	@Transactional
	public List<LigneVenteDrug> getAllLigneVenteDrugsByDrug(Integer myDrugId) {
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(LigneVenteDrug.class);
		criteria.add(Restrictions.eq("myDrug.id", myDrugId));
		return criteria.list();
	}
	
	@Transactional
	public VenteDrug saveVenteDrug(VenteDrug venteDrug) {
		DbSession session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(venteDrug);
		return venteDrug;
	}
	
	@Transactional
	public void addLigneToVenteDrug(MyDrug myDrug, VenteDrug venteDrug, Integer quantity) {
		DbSession session = sessionFactory.getCurrentSession();
		
		// Check if LigneVenteDrug entry exists
		Criteria criteria = session.createCriteria(LigneVenteDrug.class);
		criteria.add(Restrictions.eq("id.venteDrugId", venteDrug.getId()));
		criteria.add(Restrictions.eq("id.myDrugId", myDrug.getId()));
		
		LigneVenteDrug ligneVenteDrug = (LigneVenteDrug) criteria.uniqueResult();
		
		if (ligneVenteDrug != null) {
			// Update quantity if the entry already exists
			// Here, you might need to implement a way to update the quantity.
			// For example, you can add a `quantity` field in the LigneVenteDrug class
			// and update it accordingly.
			// Assuming there's a `quantity` field in LigneVenteDrug:
			ligneVenteDrug.setQuantity(quantity);
			session.update(ligneVenteDrug);
		} else {
			// Create new LigneVenteDrug entry if it does not exist
			LigneVenteDrug newLigneVenteDrug = new LigneVenteDrug();
			LigneVenteDrug.LigneVenteDrugId ligneVenteDrugId = new LigneVenteDrug.LigneVenteDrugId(venteDrug.getId(),
			        myDrug.getId());
			newLigneVenteDrug.setId(ligneVenteDrugId);
			newLigneVenteDrug.setVenteDrug(venteDrug);
			newLigneVenteDrug.setMyDrug(myDrug);
			// Assuming there's a `quantity` field in LigneVenteDrug:
			newLigneVenteDrug.setQuantity(quantity);
			
			session.save(newLigneVenteDrug);
		}
	}
	
	@Transactional
	public VenteDrug deleteVenteDrug(VenteDrug venteDrug) {
		DbSession session = sessionFactory.getCurrentSession();
		session.delete(venteDrug);
		return venteDrug;
	}
	
	@Transactional
	public void deleteLigneFromVenteDrug(MyDrug myDrug, VenteDrug venteDrug) {
		DbSession session = sessionFactory.getCurrentSession();
		
		// Create Criteria to find the LigneVenteDrug entry for the given VenteDrug and MyDrug
		Criteria criteria = session.createCriteria(LigneVenteDrug.class);
		criteria.add(Restrictions.eq("id.venteDrugId", venteDrug.getId()));
		criteria.add(Restrictions.eq("id.myDrugId", myDrug.getId()));
		
		LigneVenteDrug ligneVenteDrug = (LigneVenteDrug) criteria.uniqueResult();
		
		// If the entry exists, delete it
		if (ligneVenteDrug != null) {
			session.delete(ligneVenteDrug);
		}
	}
	
	@Transactional
	public List<Integer> getAllLignesByVenteDrug(VenteDrug venteDrug) {
		// Vérification que venteDrug n'est pas null et qu'il a un ID valide
		if (venteDrug == null || venteDrug.getId() == null) {
			System.out.println("Empty list");
			return Collections.emptyList();
		}
		
		DbSession session = sessionFactory.getCurrentSession();
		
		// Utilisation de la requête SQL native pour récupérer les my_drug_id
		String sql = "SELECT my_drug_id FROM ligne_vente_drug WHERE vente_drug_id = :venteDrugId";
		
		// Exécution de la requête et récupération des résultats
		List<Integer> drugIds = session.createSQLQuery(sql).setParameter("venteDrugId", venteDrug.getId()).list();
		
		return drugIds;
	}
	
	@Transactional
	public void addLigneToVenteDrug(VenteDrug venteDrug, LigneVenteDrug ligneVenteDrug) {
		DbSession session = sessionFactory.getCurrentSession();
		
		// Assigner la venteDrug à la nouvelle ligne
		ligneVenteDrug.setVenteDrug(venteDrug);
		
		// Enregistrer la nouvelle ligne de vente
		session.save(ligneVenteDrug);
	}
	
	@Transactional
	public LigneVenteDrug saveLigneVenteDrug(LigneVenteDrug ligneVenteDrug) {
		// Vérifier si l'objet est valide (ex. vérifications custom si nécessaire)
		if (ligneVenteDrug == null) {
			throw new IllegalArgumentException("LigneVenteDrug ne peut pas être null");
		}
		
		// Sauvegarder ou mettre à jour l'entité
		sessionFactory.getCurrentSession().saveOrUpdate(ligneVenteDrug);
		
		// Retourner l'objet après la persistance
		return ligneVenteDrug;
	}
	
	@Transactional
	public List<VenteDrug> searchVentes(LocalDateTime startDate, LocalDateTime endDate, String clientNom, String clientPrenom, String query, Integer validate) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<VenteDrug> cq = cb.createQuery(VenteDrug.class);
		Root<VenteDrug> vente = cq.from(VenteDrug.class);
		List<Predicate> predicates = new ArrayList<>();

		// Filtre par date
		if (startDate != null && endDate != null) {
			predicates.add(cb.between(vente.get("dateVente"), startDate, endDate));
		}

		// Filtre par nom du client (start with)
		if (clientNom != null) {
			predicates.add(cb.like(vente.get("client").get("name"), clientNom + "%"));
		}

		// Filtre par prénom du client (start with)
		if (clientPrenom != null) {
			predicates.add(cb.like(vente.get("client").get("firstnames"), clientPrenom + "%"));
		}

		// Filtre par médicament (nom, DCI, groupe thérapeutique) avec "start with"
		if (query != null) {
			// Créer la sous-requête pour obtenir les IDs des ventes correspondantes
			Subquery<Integer> subquery = cq.subquery(Integer.class);
			Root<LigneVenteDrug> ligne = subquery.from(LigneVenteDrug.class);
			subquery.select(ligne.get("venteDrug").get("id")).distinct(true);
			subquery.where(
					cb.or(
							cb.like(ligne.get("myDrug").get("name"), query + "%"),
							cb.like(ligne.get("myDrug").get("dci"), query + "%"),
							cb.like(ligne.get("myDrug").get("groupeTherap"), query + "%")
					)
			);
			predicates.add(vente.get("id").in(subquery));
		}


		// Ajout du filtre pour validate
		if (validate != null) {
			if (validate == 1) {
				// Filtre les résultats où le champ "validate" est égal à 1
				predicates.add(cb.equal(vente.get("validate"), 1));
			}  else {

				predicates.add(cb.equal(vente.get("validate"), 0));

			}
		}
		cq.where(predicates.toArray(new Predicate[0]));

		// Trier par dateVente du plus récent au plus ancien
		cq.orderBy(cb.desc(vente.get("dateVente")));
		
		TypedQuery<VenteDrug> queryResult = entityManager.createQuery(cq);
		return queryResult.getResultList();
	}
	
	@Transactional
	public Integer getDrugQuantFromDrugId(Integer venteDrugId, Integer drugId) {
		// Création de la session Hibernate
		DbSession session = sessionFactory.getCurrentSession();
		
		try {
			// Utilisation de Criteria pour créer la requête
			Criteria criteria = session.createCriteria(LigneVenteDrug.class, "lv");
			
			// Ajout des restrictions (filtres) sur venteDrugId et myDrugId
			criteria.add(Restrictions.eq("id.venteDrugId", venteDrugId));
			criteria.add(Restrictions.eq("id.myDrugId", drugId));
			
			// Sélection de la propriété 'quantity'
			criteria.setProjection(Projections.property("quantity"));
			
			// Exécution de la requête et récupération du résultat
			Integer quantity = (Integer) criteria.uniqueResult();
			
			return quantity; // Retourne la quantité trouvée
		}
		catch (Exception e) {
			// Gérer l'exception si nécessaire
			e.printStackTrace();
			return null; // Retourne null en cas d'erreur
		}
	}
	
	public void deleteAllLigneVente(Integer venteDrugId) {
		// Créer une session Hibernate
		DbSession session = sessionFactory.getCurrentSession();
		
		// Exécuter une requête native pour supprimer toutes les lignes associées à venteDrugId
		String sql = "DELETE FROM ligne_vente_drug WHERE vente_drug_id = :venteDrugId";
		
		Query query = session.createSQLQuery(sql);
		query.setParameter("venteDrugId", venteDrugId);
		
		// Exécuter la suppression
		query.executeUpdate();
	}
	
}
