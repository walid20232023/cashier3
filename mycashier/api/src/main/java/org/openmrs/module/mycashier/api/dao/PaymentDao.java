package org.openmrs.module.mycashier.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.mycashier.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository("mycashier.PaymentDao")
public class PaymentDao {
	
	@Autowired
	private DbSessionFactory sessionFactory;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public DbSessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(DbSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	// Get Payment by UUID
	public Payment getPaymentByUuid(String uuid) {
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Payment.class);
		criteria.add(Restrictions.eq("uuid", uuid));
		return (Payment) criteria.uniqueResult();
	}
	
	// Get Payment by ID
	public Payment getPaymentById(Integer paymentId) {
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Payment.class);
		criteria.add(Restrictions.eq("id", paymentId));
		return (Payment) criteria.uniqueResult();
	}
	
	// Get Payments by Vente Service
	public List<Payment> getPaymentsByVenteService(Integer venteServiceId) {
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Payment.class);
		criteria.add(Restrictions.eq("venteServiceId", venteServiceId));
		return criteria.list();
	}
	
	// Get Payments by Vente Drug
	public List<Payment> getPaymentsByVenteDrug(Integer venteDrugId) {
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Payment.class);
		criteria.add(Restrictions.eq("venteDrugId", venteDrugId));
		return criteria.list();
	}
	
	// Get All Payments
	public List<Payment> getAllPayments() {
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Payment.class);
		return criteria.list();
	}
	
	// Get All Retired Payments
	public List<Payment> getAllRetiredPayments() {
		DbSession session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Payment.class);
		criteria.add(Restrictions.eq("retired", true));
		return criteria.list();
	}
	
	// Save or Update Payment
	public Payment savePayment(Payment payment) {
		DbSession session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(payment);
		return payment;
	}
	
	// Delete Payment
	public Payment deletePayment(Payment payment) {
		DbSession session = sessionFactory.getCurrentSession();
		session.delete(payment);
		return payment;
	}
	
	@Transactional
	public List<Payment> searchPayments(LocalDateTime startDate, LocalDateTime endDate,
										String clientNom, String clientPrenom,
										String operationType,
										String agentName,
										String assurance) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Payment> cq = cb.createQuery(Payment.class);
		Root<Payment> payment = cq.from(Payment.class);
		List<Predicate> predicates = new ArrayList<>();

		// Filtre par date (période)
		if (startDate != null && endDate != null) {
			predicates.add(cb.between(payment.get("datePayment"), startDate, endDate));
		}

		// Filtre par nom du client (commence par)
		if (clientNom != null && !clientNom.isEmpty()) {
			predicates.add(cb.like(payment.get("venteDrug").get("client").get("name"), clientNom + "%"));
		}

		// Filtre par prénom du client (commence par)
		if (clientPrenom != null && !clientPrenom.isEmpty()) {
			predicates.add(cb.like(payment.get("venteDrug").get("client").get("firstnames"), clientPrenom + "%"));
		}

		// Filtre par type d'opération (exact)
		if (operationType != null && !operationType.isEmpty()) {
			predicates.add(cb.equal(payment.get("modePayment"), operationType));
		}

		// Filtre par nom de l'agent (commence par)
		if (agentName != null && !agentName.isEmpty()) {
			predicates.add(cb.like(payment.get("agent").get("name"), agentName + "%"));
		}

		// Filtre par assurance (exact)
		if (assurance != null && !assurance.isEmpty()) {
			predicates.add(cb.equal(payment.get("venteDrug").get("assurance"), assurance));
		}

		// Appliquer les prédicats au critère
		cq.where(predicates.toArray(new Predicate[0]));

		// Trier les résultats par date de paiement, du plus récent au plus ancien
		cq.orderBy(cb.desc(payment.get("datePayment")));

		// Exécuter la requête et retourner la liste des paiements
		TypedQuery<Payment> queryResult = entityManager.createQuery(cq);
		return queryResult.getResultList();
	}
}
