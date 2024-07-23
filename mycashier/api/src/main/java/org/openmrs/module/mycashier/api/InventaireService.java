package org.openmrs.module.mycashier.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.mycashier.Agent;
import org.openmrs.module.mycashier.DrugInventaire;
import org.openmrs.module.mycashier.MycashierConfig;
import org.openmrs.module.mycashier.Inventaire;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface InventaireService extends OpenmrsService {

    @Transactional(readOnly = true)
    Inventaire getInventaireByUuid(String uuid) throws APIException;

    @Transactional(readOnly = true)
    Inventaire getInventaireById(Integer inventaireId) throws APIException;

    @Transactional(readOnly = true)
    List<Inventaire> getAllInventaires() throws APIException;


    @Transactional(readOnly = true)
    List<DrugInventaire> getAllDrugInventairesByDrug(Integer myDrugId) throws APIException;
    @Authorized(MycashierConfig.MODULE_PRIVILEGE)
    @Transactional
    Inventaire saveInventaire(Inventaire inventaire) throws APIException;


    @Transactional
    void addDrugInventaire (Integer inventaireId,
                            Integer drugIg,
                            Integer realQuantity,
                            String motif)  throws APIException;

    @Transactional
    void deleteDrugInventaire (Integer inventaireId, Integer drugId )  throws APIException;


    @Authorized(MycashierConfig.MODULE_PRIVILEGE)
    @Transactional
    Inventaire deleteInventaire(Inventaire inventaire, String motif, Agent agent) throws APIException;
}
