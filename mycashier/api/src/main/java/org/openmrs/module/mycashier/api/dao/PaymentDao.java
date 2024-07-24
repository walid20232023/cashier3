package org.openmrs.module.mycashier.api.dao;

import org.hibernate.SessionFactory;
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


    public Payment getPaymentByUuid(String uuid) {
        return null;
    }

    public Payment getPaymentById(Integer paymentId) {
        return null;
    }

    public List<Payment> getPaymentsByVenteService(Integer venteServiceId) {
        return null;
    }

    public List<Payment> getPaymentsByVenteDrug(Integer venteDrugId) {
        return null;
    }

    public List<Payment> getAllPayments() {
        return null;
    }

    public List<Payment> getAllRetiredPayments() {
        return null;
    }

    public Payment savePayment(Payment payment) {
        return null;
    }

    public Payment deletePayment(Payment payment) {
        return null;
    }
}
