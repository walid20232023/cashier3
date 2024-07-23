package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.module.mycashier.Payment;
import org.openmrs.module.mycashier.api.PaymentService;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    @Override
    public Payment getPaymentByUuid(String uuid) throws APIException {
        return null;
    }

    @Override
    public Payment getPaymentById(Integer paymentId) throws APIException {
        return null;
    }

    @Override
    public List<Payment> getPaymentsByVenteService(Integer venteServiceId) throws APIException {
        return null;
    }

    @Override
    public List<Payment> getPaymentsByVenteDrug(Integer venteDrugId) throws APIException {
        return null;
    }

    @Override
    public List<Payment> getAllPayments() throws APIException {
        return null;
    }

    @Override
    public List<Payment> getAllRetiredPayments() throws APIException {
        return null;
    }

    @Override
    public Payment savePayment(Payment payment) throws APIException {
        return null;
    }

    @Override
    public Payment deletePayment(Payment payment) throws APIException {
        return null;
    }
}
