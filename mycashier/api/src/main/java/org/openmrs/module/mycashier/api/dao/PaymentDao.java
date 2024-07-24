package org.openmrs.module.mycashier.api.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.module.mycashier.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PaymentDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // Get Payment by UUID
    public Payment getPaymentByUuid(String uuid) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Payment.class);
        criteria.add(Restrictions.eq("uuid", uuid));
        return (Payment) criteria.uniqueResult();
    }

    // Get Payment by ID
    public Payment getPaymentById(Integer paymentId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Payment.class);
        criteria.add(Restrictions.eq("id", paymentId));
        return (Payment) criteria.uniqueResult();
    }

    // Get Payments by Vente Service
    public List<Payment> getPaymentsByVenteService(Integer venteServiceId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Payment.class);
        criteria.add(Restrictions.eq("venteServiceId", venteServiceId));
        return criteria.list();
    }

    // Get Payments by Vente Drug
    public List<Payment> getPaymentsByVenteDrug(Integer venteDrugId) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Payment.class);
        criteria.add(Restrictions.eq("venteDrugId", venteDrugId));
        return criteria.list();
    }

    // Get All Payments
    public List<Payment> getAllPayments() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Payment.class);
        return criteria.list();
    }

    // Get All Retired Payments
    public List<Payment> getAllRetiredPayments() {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Payment.class);
        criteria.add(Restrictions.eq("retired", true));
        return criteria.list();
    }

    // Save or Update Payment
    public Payment savePayment(Payment payment) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(payment);
        return payment;
    }

    // Delete Payment
    public Payment deletePayment(Payment payment) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(payment);
        return payment;
    }
}
