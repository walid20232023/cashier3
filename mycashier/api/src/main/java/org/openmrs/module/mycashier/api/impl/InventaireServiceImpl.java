package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.module.mycashier.Agent;
import org.openmrs.module.mycashier.DrugInventaire;
import org.openmrs.module.mycashier.Inventaire;
import org.openmrs.module.mycashier.api.InventaireService;

import java.util.List;

public class InventaireServiceImpl implements InventaireService {
    @Override
    public Inventaire getInventaireByUuid(String uuid) throws APIException {
        return null;
    }

    @Override
    public Inventaire getInventaireById(Integer inventaireId) throws APIException {
        return null;
    }

    @Override
    public List<Inventaire> getAllInventaires() throws APIException {
        return null;
    }

    @Override
    public List<DrugInventaire> getAllDrugInventairesByDrug(Integer myDrugId) throws APIException {
        return null;
    }

    @Override
    public Inventaire saveInventaire(Inventaire inventaire) throws APIException {
        return null;
    }

    @Override
    public void addDrugInventaire(Integer inventaireId, Integer drugIg, Integer realQuantity, String motif) throws APIException {

    }

    @Override
    public void deleteDrugInventaire(Integer inventaireId, Integer drugId) throws APIException {

    }

    @Override
    public Inventaire deleteInventaire(Inventaire inventaire, String motif, Agent agent) throws APIException {
        return null;
    }
}
