package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.module.mycashier.Approvisionnement;
import org.openmrs.module.mycashier.Entrepot;
import org.openmrs.module.mycashier.StockEntrepot;
import org.openmrs.module.mycashier.VenteDrug;
import org.openmrs.module.mycashier.api.EntrepotService;

import java.time.LocalDateTime;
import java.util.List;

public class EntrepotServiceImpl implements EntrepotService {
    @Override
    public Entrepot getEntrepotByUuid(String uuid) throws APIException {
        return null;
    }

    @Override
    public Entrepot getEntrepotById(Integer entrepotId) throws APIException {
        return null;
    }

    @Override
    public List<Entrepot> getAllEntrepots() throws APIException {
        return null;
    }

    @Override
    public List<StockEntrepot> getAllStockEntrepotsByEntrepot(Integer entrepotId) throws APIException {
        return null;
    }

    @Override
    public StockEntrepot getStockByEntrepotAndDrug(Integer entrepotId, Integer drugId) throws APIException {
        return null;
    }

    @Override
    public List<VenteDrug> getAllVenteDrugByEntrepot(LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public List<VenteDrug> getAllVenteDrugByEntrepotAndDrug(LocalDateTime start, LocalDateTime end, Integer myDrugId) throws APIException {
        return null;
    }

    @Override
    public List<VenteDrug> getAllDrugAvarieByEntrepot(LocalDateTime start, LocalDateTime end) throws APIException {
        return null;
    }

    @Override
    public List<Approvisionnement> getAllDrugApprovisByEntrepot(LocalDateTime start, LocalDateTime end) throws APIException {
        return null;
    }

    @Override
    public List<VenteDrug> getAllDrugApprovisByEntrepotAndDrug(LocalDateTime start, LocalDateTime end, Integer myDrugId) throws APIException {
        return null;
    }

    @Override
    public Entrepot saveEntrepot(Entrepot entrepot) throws APIException {
        return null;
    }

    @Override
    public Entrepot deleteEntrepot(Entrepot entrepot) throws APIException {
        return null;
    }
}
