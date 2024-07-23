package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.module.mycashier.Emballage;
import org.openmrs.module.mycashier.MyDrug;
import org.openmrs.module.mycashier.api.MyDrugService;

import java.util.List;

public class MyDrugServiceImpl implements MyDrugService {
    @Override
    public MyDrug getMyDrugByUuid(String uuid) throws APIException {
        return null;
    }

    @Override
    public MyDrug getMyDrugById(Integer myDrugId) throws APIException {
        return null;
    }

    @Override
    public List<MyDrug> getAllMyDrugs() throws APIException {
        return null;
    }

    @Override
    public List<MyDrug> getAllRetiredMyDrugs() throws APIException {
        return null;
    }

    @Override
    public MyDrug saveMyDrug(MyDrug myDrug) throws APIException {
        return null;
    }

    @Override
    public void saveMyDrugEmballageUnits(Emballage emballage, Integer units) throws APIException {

    }

    @Override
    public Integer getMyDrugEmballageUnits(Emballage emballage, MyDrug myDrug) throws APIException {
        return null;
    }

    @Override
    public MyDrug deleteMyDrug(MyDrug myDrug) throws APIException {
        return null;
    }
}
