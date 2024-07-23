package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.module.mycashier.Provision;
import org.openmrs.module.mycashier.api.ProvisionService;

import java.util.List;

public class ProvisionServiceImpl implements ProvisionService {
    @Override
    public Provision getProvisionByUuid(String uuid) throws APIException {
        return null;
    }

    @Override
    public Provision getProvisionById(Integer provisionId) throws APIException {
        return null;
    }

    @Override
    public List<Provision> getAllProvisions() throws APIException {
        return null;
    }

    @Override
    public Provision saveProvision(Provision provision) throws APIException {
        return null;
    }

    @Override
    public Provision deleteProvision(Provision provision) throws APIException {
        return null;
    }
}
