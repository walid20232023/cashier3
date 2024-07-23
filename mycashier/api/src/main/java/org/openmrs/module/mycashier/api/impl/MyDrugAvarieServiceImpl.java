package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.module.mycashier.MyDrugAvarie;
import org.openmrs.module.mycashier.api.MyDrugAvarieService;

import java.util.List;

public class MyDrugAvarieServiceImpl implements MyDrugAvarieService {
    @Override
    public MyDrugAvarie getMyDrugAvarieByUuid(String uuid) throws APIException {
        return null;
    }

    @Override
    public MyDrugAvarie getMyDrugAvarieById(Integer myDrugAvarieId) throws APIException {
        return null;
    }

    @Override
    public List<MyDrugAvarie> getAllMyDrugAvaries() throws APIException {
        return null;
    }

    @Override
    public List<MyDrugAvarie> getAllMyDrugAvariesByEntrepot(Integer entrepotId) throws APIException {
        return null;
    }

    @Override
    public List<MyDrugAvarie> getAllMyDrugAvariesByDrug(Integer mydrugId) throws APIException {
        return null;
    }

    @Override
    public List<MyDrugAvarie> getAllMyDrugAvariesByEntrepotAndDrug(Integer mydrugId, Integer entrepotId) throws APIException {
        return null;
    }

    @Override
    public MyDrugAvarie saveMyDrugAvarie(MyDrugAvarie myDrugAvarie) throws APIException {
        return null;
    }

    @Override
    public MyDrugAvarie deleteMyDrugAvarie(MyDrugAvarie myDrugAvarie) throws APIException {
        return null;
    }
}
