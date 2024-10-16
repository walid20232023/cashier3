package org.openmrs.module.mycashier.api.dao;

import org.hibernate.Criteria;

import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.APIException;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.mycashier.Approvisionnement;

import org.openmrs.module.mycashier.LigneApprovis;
import org.openmrs.module.mycashier.MyDrug;
import org.openmrs.module.mycashier.StockEntrepot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository("mycashier.ApprovisionnementDao")
public class ApprovisionnementDao {
	
	@Autowired
	private DbSessionFactory sessionFactory;
	
	public DbSessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(DbSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public Approvisionnement getApprovisionnementByUuid(String uuid) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Approvisionnement.class);
		criteria.add(Restrictions.eq("uuid", uuid.toLowerCase()));
		return (Approvisionnement) criteria.uniqueResult();
	}
	
	@Transactional
	public Approvisionnement getApprovisionnementById(Integer approvisionnementId) {
		return (Approvisionnement) sessionFactory.getCurrentSession().get(Approvisionnement.class, approvisionnementId);
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Approvisionnement> getAllApprovisionnements(LocalDateTime start, LocalDateTime end) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Approvisionnement.class);
		criteria.add(Restrictions.between("dateApprovisionnement", start, end));
		return (List<Approvisionnement>) criteria.list();
	}
	
	/**
	 * @Transactional
	 * @SuppressWarnings("unchecked") public List<LigneApprovis>
	 *                                getAllLigneApprovisByDrug(LocalDateTime start, LocalDateTime
	 *                                end) { Criteria criteria =
	 *                                sessionFactory.getCurrentSession().createCriteria
	 *                                (LigneApprovis.class);
	 *                                criteria.add(Restrictions.between("dateApprovisionnement",
	 *                                start, end)); return (List<LigneApprovis>) criteria.list(); }
	 **/
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Approvisionnement> getAllApprovisionnementsByEntrepotSource(LocalDateTime start, LocalDateTime end,
	        Integer entrepotSource) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Approvisionnement.class);
		criteria.add(Restrictions.eq("entrepotSourceId", entrepotSource));
		criteria.add(Restrictions.between("dateApprovisionnement", start, end));
		return (List<Approvisionnement>) criteria.list();
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Approvisionnement> getAllApprovisionnementsByEntrepotCible(LocalDateTime start, LocalDateTime end,
	        Integer entrepotCible) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Approvisionnement.class);
		criteria.add(Restrictions.eq("entrepotCibleId", entrepotCible));
		criteria.add(Restrictions.between("dateApprovisionnement", start, end));
		return (List<Approvisionnement>) criteria.list();
	}
	
	@Transactional
	public Approvisionnement saveApprovisionnement(Approvisionnement approvisionnement) {
		sessionFactory.getCurrentSession().saveOrUpdate(approvisionnement);
		return approvisionnement;
	}
	

	
	@Transactional
	public void deleteLigneApprovisFromApprovisionnement(Integer ligneApprovisId) {
		LigneApprovis ligneApprovis = (LigneApprovis) sessionFactory.getCurrentSession().get(LigneApprovis.class,
		    ligneApprovisId);
		if (ligneApprovis != null) {
			sessionFactory.getCurrentSession().delete(ligneApprovis);
		}
	}
	
	@Transactional
	public Approvisionnement deleteApprovisionnement(Approvisionnement approvisionnement) {
		sessionFactory.getCurrentSession().delete(approvisionnement);
		return approvisionnement;
	}
	
	@Transactional
	public void deleteLigneApprovisFRomApprovisionnement(Integer ligneApprovisId) {
		DbSession session = sessionFactory.getCurrentSession();
		// Créer un critère pour trouver l'entité LigneApprovis à supprimer
		LigneApprovis ligneApprovis = (LigneApprovis) session.get(LigneApprovis.class, ligneApprovisId);
		// Vérifier si l'entité a été trouvée
		if (ligneApprovis != null) {
			// Supprimer l'entité de la base de données
			session.delete(ligneApprovis);
		} else {
			// Gérer le cas où l'entité n'existe pas (si nécessaire)
			throw new APIException("LigneApprovis avec l'ID " + ligneApprovisId + " n'existe pas.");
		}
	}

	@Transactional
	public List<Integer> getMyDrugEmballageIdsByApprovisionnementId(Integer approvisionnementId) {
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(LigneApprovis.class);
		criteria.add(Restrictions.eq("approvisionnement_id", approvisionnementId));
		criteria.setProjection(Projections.property("my_drug_emballage_id"));
		return criteria.list();
	}

	public List<Integer> getQuantitesByApprovisionnementId(Integer approvisionnementId) {
		// Ouvre une session Hibernate
		DbSession session = sessionFactory.getCurrentSession();

		// Crée un critère de recherche sur l'entité LigneApprovis
		Criteria criteria = session.createCriteria(LigneApprovis.class);

		// Ajoute la restriction pour filtrer par l'approvisionnement_id donné
		criteria.add(Restrictions.eq("approvisionnement_id", approvisionnementId));

		// Définit la projection pour ne récupérer que la colonne 'quantite'
		criteria.setProjection(Projections.property("quantite"));

		// Exécute la requête et retourne la liste des quantités
		return criteria.list();
	}

	@Transactional
	public void deleteAllLignesApprovis(Integer approvisionnementId) {
		// Créer une session Hibernate
		DbSession session = sessionFactory.getCurrentSession();

		// Définir la requête SQL pour supprimer les lignes associées à approvisionnementId
		String sql = "DELETE FROM ligne_approvis WHERE approvisionnement_id = :approvisionnementId";

		// Créer et paramétrer la requête
		Query query = session.createSQLQuery(sql);
		query.setParameter("approvisionnementId", approvisionnementId);

		// Exécuter la suppression
		query.executeUpdate();
	}

	public void addLigneApprovisToApprovisionnement(Approvisionnement approvisionnement, MyDrug myDrug, Integer quantite) {
	}



	@Transactional

	public LigneApprovis saveLigneApprovisionnment(LigneApprovis ligneApprovis) {

		DbSession session = sessionFactory.getCurrentSession();

		// Utilisation de saveOrUpdate pour sauvegarder ou mettre à jour l'entité
		session.saveOrUpdate(ligneApprovis);

		// Retourner l'objet sauvegardé ou mis à jour
		return ligneApprovis;
	}

	@Transactional
	public List<LigneApprovis> getAllLignesByApprovisionnementId(Integer approvisionnementId) {
		DbSession session = sessionFactory.getCurrentSession();
		// Utiliser Criteria pour récupérer les lignes d'approvisionnement
		List<LigneApprovis> lignes = session.createCriteria(LigneApprovis.class)
				.add(Restrictions.eq("approvisionnement.id", approvisionnementId))
				.list();

		return lignes;
	}

	@Transactional
	public List<LigneApprovis> searchLigneApprovis(String medicament, String dateDebut, String dateFin, String numeroLot, Integer entrepotSourceId, Integer entrepotCibleId, String perimeAvant) {
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(LigneApprovis.class, "ligneApprovis");

		// Joins with related entities
		criteria.createAlias("ligneApprovis.approvisionnement", "approvisionnement");
		criteria.createAlias("approvisionnement.entrepotSource", "entrepotSource");
		criteria.createAlias("approvisionnement.entrepotCible", "entrepotCible");
		criteria.createAlias("ligneApprovis.myDrugEmballage", "myDrugEmballage");
		criteria.createAlias("myDrugEmballage.myDrug", "myDrug");

		// Filtrer par nom de médicament, DCI ou groupe thérapeutique
		if (medicament != null && !medicament.isEmpty()) {
			criteria.add(Restrictions.or(
					Restrictions.ilike("myDrug.name", "%" + medicament + "%"),
					Restrictions.or(
							Restrictions.ilike("myDrug.dci", "%" + medicament + "%"),
							Restrictions.ilike("myDrug.groupeTherap", "%" + medicament + "%")
					)
			));
		}

		// Filter by dateDebut and dateFin
		if (dateDebut != null && !dateDebut.isEmpty()) {
			LocalDateTime startDate = LocalDateTime.parse(dateDebut);
			criteria.add(Restrictions.ge("approvisionnement.dateTimeApprovisionnement", startDate));
		}

		if (dateFin != null && !dateFin.isEmpty()) {
			LocalDateTime endDate = LocalDateTime.parse(dateFin);
			criteria.add(Restrictions.le("approvisionnement.dateTimeApprovisionnement", endDate));
		}

		// Filter by numeroLot
		if (numeroLot != null && !numeroLot.isEmpty()) {
			criteria.add(Restrictions.like("ligneApprovis.numeroLot", "%" + numeroLot + "%"));
		}

		// Filter by entrepotSourceId and entrepotCibleId
		if (entrepotSourceId != null) {
			criteria.add(Restrictions.eq("entrepotSource.id", entrepotSourceId));
		}

		if (entrepotCibleId != null) {
			criteria.add(Restrictions.eq("entrepotCible.id", entrepotCibleId));
		}

		// Filter by perimeAvant (expiration date)
		if (perimeAvant != null && !perimeAvant.isEmpty()) {
			LocalDate expirationDate = LocalDate.parse(perimeAvant);
			criteria.add(Restrictions.lt("ligneApprovis.datePeremption", expirationDate));
		}

		return criteria.list();
	}

}
