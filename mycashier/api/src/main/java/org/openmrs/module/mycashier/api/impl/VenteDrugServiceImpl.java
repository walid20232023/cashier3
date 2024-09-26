package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;

import org.openmrs.module.mycashier.LigneVenteDrug;
import org.openmrs.module.mycashier.MyDrug;
import org.openmrs.module.mycashier.VenteDrug;
import org.openmrs.module.mycashier.api.VenteDrugService;
import org.openmrs.module.mycashier.api.dao.VenteDrugDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service("venteDrugService")
public class VenteDrugServiceImpl implements VenteDrugService {
	
	@Autowired
	VenteDrugDao dao;
	
	public void setDao(VenteDrugDao dao) {
		this.dao = dao;
	}
	
	@Override
	public VenteDrug getVenteDrugByUuid(String uuid) throws APIException {
		return dao.getVenteDrugByUuid(uuid);
	}
	
	@Override
	public VenteDrug getVenteDrugById(Integer venteDrugId) throws APIException {
		return dao.getVenteDrugById(venteDrugId);
	}
	
	@Override
	public List<VenteDrug> getAllVenteDrugs() throws APIException {
		return dao.getAllVenteDrugs();
	}
	
	@Override
	public List<VenteDrug> getAllVenteDrugs(LocalDateTime start, LocalDateTime end) throws APIException {
		return dao.getAllVenteDrugs(start, end);
	}
	
	@Override
	public List<LigneVenteDrug> getAllLIgneVenteDrugsByDrug(Integer myDrugId) throws APIException {
		return dao.getAllLigneVenteDrugsByDrug(myDrugId);
	}
	
	@Override
	public VenteDrug saveVenteDrug(VenteDrug venteDrug) throws APIException {
		return dao.saveVenteDrug(venteDrug);
	}
	
	@Override
	public void deleteLigneFromVenteDrug(MyDrug myDrug, VenteDrug venteDrug) throws APIException {
		dao.deleteLigneFromVenteDrug(myDrug, venteDrug);
	}
	
	@Override
	public VenteDrug deleteVenteDrug(VenteDrug venteDrug) throws APIException {
		return dao.deleteVenteDrug(venteDrug);
	}
	
	@Override
	public List<Integer> getAllLignesByVenteDrug(VenteDrug venteDrug) throws APIException {
		return dao.getAllLignesByVenteDrug(venteDrug);
	}
	
	@Override
	public void addLigneToVenteDrug(VenteDrug venteDrug, LigneVenteDrug ligneVenteDrug) {
		dao.addLigneToVenteDrug(venteDrug, ligneVenteDrug);
		
	}
	
	@Override
	public LigneVenteDrug saveLigneVenteDrug(LigneVenteDrug ligneVenteDrug) {
		
		return dao.saveLigneVenteDrug(ligneVenteDrug);
	}
	
	@Override
	public List<VenteDrug> searchVentes(LocalDateTime startDate, LocalDateTime endDate, String clientNom,
	        String clientPrenom, String query) {
		return dao.searchVentes(startDate, endDate, clientNom, clientPrenom, query);
	}
	
	@Override
	public List<LigneVenteDrug> getAllLigneVenteDrugsByDrug(Integer id) {
		return dao.getAllLigneVenteDrugsByDrug(id);
	}
	
	@Override
	public Integer getDrugQuantFromDrugId(Integer venteDrugId, Integer drugId) {
		return dao.getDrugQuantFromDrugId(venteDrugId, drugId);
	}
	
	@Override
	public void deleteAllLigneVente(Integer venteDrugId) {
		dao.deleteAllLigneVente(venteDrugId);
	}
}
