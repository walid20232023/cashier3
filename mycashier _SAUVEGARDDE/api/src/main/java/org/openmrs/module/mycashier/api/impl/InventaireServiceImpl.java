package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.mycashier.Agent;
import org.openmrs.module.mycashier.DrugInventaire;
import org.openmrs.module.mycashier.Inventaire;
import org.openmrs.module.mycashier.api.InventaireService;
import org.openmrs.module.mycashier.api.dao.EntrepotDao;
import org.openmrs.module.mycashier.api.dao.InventaireDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class InventaireServiceImpl extends BaseOpenmrsService implements InventaireService {
	
	@Autowired
	InventaireDao dao;
	
	public void setDao(InventaireDao dao) {
		this.dao = dao;
	}
	
	@Override
	public Inventaire getInventaireByUuid(String uuid) throws APIException {
		return dao.getInventaireByUuid(uuid);
	}
	
	@Override
	public Inventaire getInventaireById(Integer inventaireId) throws APIException {
		return dao.getInventaireById(inventaireId);
	}
	
	@Override
	public List<Inventaire> getAllInventaires() throws APIException {
		return dao.getAllInventaires();
	}
	
	@Override
	public List<DrugInventaire> getAllDrugInventairesByDrug(Integer myDrugId) throws APIException {
		return dao.getAllDrugInventairesByDrug(myDrugId);
	}
	
	@Override
	public Inventaire saveInventaire(Inventaire inventaire) throws APIException {
		return dao.saveInventaire(inventaire);
	}
	
	@Override
	public void addDrugInventaire(Integer inventaireId, Integer drugIg, Integer realQuantity, String motif)
	        throws APIException {
		dao.addDrugInventaire(inventaireId, drugIg, realQuantity, motif);
	}
	
	@Override
	public void deleteDrugInventaire(Integer inventaireId, Integer drugId) throws APIException {
		dao.deleteDrugInventaire(inventaireId, drugId);
	}
	
	@Override
	public Inventaire deleteInventaire(Inventaire inventaire, String motif, Agent agent) throws APIException {
		return dao.deleteInventaire(inventaire, motif, agent);
	}
}
