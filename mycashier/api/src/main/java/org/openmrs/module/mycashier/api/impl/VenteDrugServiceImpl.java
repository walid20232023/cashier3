package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.module.mycashier.LigneVenteDrug;
import org.openmrs.module.mycashier.VenteDrug;
import org.openmrs.module.mycashier.api.VenteDrugService;

import java.time.LocalDateTime;
import java.util.List;

public class VenteDrugServiceImpl implements VenteDrugService {
    @Override
    public VenteDrug getVenteDrugByUuid(String uuid) throws APIException {
        return null;
    }

    @Override
    public VenteDrug getVenteDrugById(Integer venteDrugId) throws APIException {
        return null;
    }

    @Override
    public List<VenteDrug> getAllVenteDrugs() throws APIException {
        return null;
    }

    @Override
    public List<VenteDrug> getAllVenteDrugs(LocalDateTime start, LocalDateTime end) throws APIException {
        return null;
    }

    @Override
    public List<LigneVenteDrug> getAllLIgneVenteDrugsByDrug(Integer myDrugId) throws APIException {
        return null;
    }

    @Override
    public VenteDrug saveVenteDrug(VenteDrug venteDrug) throws APIException {
        return null;
    }

    @Override
    public void addLigneToVenteDrug(Integer venteDrugId, Integer myDrugId, Integer quantity) throws APIException {

    }

    @Override
    public void deleteLigneFromVenteDrug(Integer venteDrugId, Integer myDrugId) throws APIException {

    }

    @Override
    public VenteDrug deleteVenteDrug(VenteDrug venteDrug) throws APIException {
        return null;
    }
}
