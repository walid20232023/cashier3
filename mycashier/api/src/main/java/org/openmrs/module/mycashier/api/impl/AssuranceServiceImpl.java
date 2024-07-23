package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.module.mycashier.Assurance;
import org.openmrs.module.mycashier.api.AssuranceService;

import java.util.List;

public class AssuranceServiceImpl implements AssuranceService {
    @Override
    public Assurance getAssuranceByUuid(String uuid) throws APIException {
        return null;
    }

    @Override
    public Assurance getAssuranceById(Integer assuranceId) throws APIException {
        return null;
    }

    @Override
    public List<Assurance> getAllAssurances() throws APIException {
        return null;
    }

    @Override
    public List<Assurance> getAllClientsByAssurance(Assurance assurance) throws APIException {
        return null;
    }

    @Override
    public Assurance saveAssurance(Assurance assurance) throws APIException {
        return null;
    }

    @Override
    public Assurance deleteAssurance(Assurance assurance) throws APIException {
        return null;
    }
}
