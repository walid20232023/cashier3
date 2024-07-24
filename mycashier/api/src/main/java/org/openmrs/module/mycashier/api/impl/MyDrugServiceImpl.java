package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.mycashier.Emballage;
import org.openmrs.module.mycashier.MyDrug;
import org.openmrs.module.mycashier.api.MyDrugService;
import org.openmrs.module.mycashier.api.dao.MyDrugAvarieDao;
import org.openmrs.module.mycashier.api.dao.MyDrugDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyDrugServiceImpl extends BaseOpenmrsService  implements MyDrugService {

    @Autowired
    MyDrugDao dao;

    public void setDao(  MyDrugDao dao) {
        this.dao = dao;
    }
    @Override
    public MyDrug getMyDrugByUuid(String uuid) throws APIException {
        return dao.getMyDrugByUuid( uuid);
    }

    @Override
    public MyDrug getMyDrugById(Integer myDrugId) throws APIException {
        return dao.getMyDrugById( myDrugId) ;
    }

    @Override
    public List<MyDrug> getAllMyDrugs() throws APIException {
        return dao.getAllMyDrugs() ;
    }

    @Override
    public List<MyDrug> getAllRetiredMyDrugs() throws APIException {
        return dao.getAllRetiredMyDrugs() ;
    }

    @Override
    public MyDrug saveMyDrug(MyDrug myDrug) throws APIException {
        return dao.saveMyDrug(myDrug);
    }

    @Override
    public void saveMyDrugEmballageUnits(Emballage emballage, Integer units) throws APIException {
     dao.saveMyDrugEmballageUnits( emballage, units);
    }

    @Override
    public Integer getMyDrugEmballageUnits(Emballage emballage, MyDrug myDrug) throws APIException {
        return dao.getMyDrugEmballageUnits(emballage, myDrug);
    }

    @Override
    public MyDrug deleteMyDrug(MyDrug myDrug) throws APIException {
        return dao.deleteMyDrug(myDrug);
    }
}
