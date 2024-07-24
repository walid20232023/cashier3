package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.mycashier.Emballage;
import org.openmrs.module.mycashier.api.EmballageService;
import org.openmrs.module.mycashier.api.dao.ClientDao;
import org.openmrs.module.mycashier.api.dao.EmballageDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmballageServiceImpl extends BaseOpenmrsService  implements EmballageService {

    @Autowired
    EmballageDao dao;

    public void setDao( EmballageDao dao) {
        this.dao = dao;
    }
    @Override
    public Emballage getEmballageByUuid(String uuid) throws APIException {
        return dao.getEmballageByUuid(uuid);
    }

    @Override
    public Emballage getEmballageById(Integer emballageId) throws APIException {
        return dao.getEmballageById( emballageId) ;
    }

    @Override
    public List<Emballage> getAllEmballages() throws APIException {
        return dao.getAllEmballages();
    }

    @Override
    public Emballage saveEmballage(Emballage emballage) throws APIException {
        return dao.saveEmballage( emballage);
    }

    @Override
    public Emballage deleteEmballage(Emballage emballage) throws APIException {
        return dao.deleteEmballage(emballage);
    }
}
