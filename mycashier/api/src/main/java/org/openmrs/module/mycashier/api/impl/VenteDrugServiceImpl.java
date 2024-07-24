package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.mycashier.LigneVenteDrug;
import org.openmrs.module.mycashier.VenteDrug;
import org.openmrs.module.mycashier.api.VenteDrugService;
import org.openmrs.module.mycashier.api.dao.ServiceDao;
import org.openmrs.module.mycashier.api.dao.VenteDrugDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class VenteDrugServiceImpl extends BaseOpenmrsService implements VenteDrugService {

    @Autowired
    VenteDrugDao dao;

    public void setDao( VenteDrugDao dao) {
        this.dao = dao;
    }
    @Override
    public VenteDrug getVenteDrugByUuid(String uuid) throws APIException {
        return dao.getVenteDrugByUuid( uuid) ;
    }

    @Override
    public VenteDrug getVenteDrugById(Integer venteDrugId) throws APIException {
        return dao.getVenteDrugById(venteDrugId) ;
    }

    @Override
    public List<VenteDrug> getAllVenteDrugs() throws APIException {
        return dao.getAllVenteDrugs();
    }

    @Override
    public List<VenteDrug> getAllVenteDrugs(LocalDateTime start, LocalDateTime end) throws APIException {
        return dao.getAllVenteDrugs(start, end) ;
    }

    @Override
    public List<LigneVenteDrug> getAllLIgneVenteDrugsByDrug(Integer myDrugId) throws APIException {
        return dao.getAllLIgneVenteDrugsByDrug( myDrugId);
    }

    @Override
    public VenteDrug saveVenteDrug(VenteDrug venteDrug) throws APIException {
        return dao.saveVenteDrug( venteDrug) ;
    }

    @Override
    public void addLigneToVenteDrug(Integer venteDrugId, Integer myDrugId, Integer quantity) throws APIException {
     dao.addLigneToVenteDrug(venteDrugId, myDrugId,  quantity);
    }

    @Override
    public void deleteLigneFromVenteDrug(Integer venteDrugId, Integer myDrugId) throws APIException {
    dao.deleteLigneFromVenteDrug( venteDrugId, myDrugId);
    }

    @Override
    public VenteDrug deleteVenteDrug(VenteDrug venteDrug) throws APIException {
        return dao.deleteVenteDrug(venteDrug) ;
    }
}
