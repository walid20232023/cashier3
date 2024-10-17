package org.openmrs.module.mycashier.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;

import org.openmrs.module.mycashier.LigneVenteDrug;
import org.openmrs.module.mycashier.MyDrug;
import org.openmrs.module.mycashier.VenteDrug;
import org.openmrs.module.mycashier.MycashierConfig;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Service
public interface VenteDrugService {
	
	@Transactional(readOnly = true)
	VenteDrug getVenteDrugByUuid(String uuid) throws APIException;
	
	@Transactional(readOnly = true)
	VenteDrug getVenteDrugById(Integer venteDrugId) throws APIException;
	
	@Transactional(readOnly = true)
	List<VenteDrug> getAllVenteDrugs() throws APIException;
	
	@Transactional(readOnly = true)
	List<VenteDrug> getAllVenteDrugs(LocalDateTime start, LocalDateTime end) throws APIException;
	
	@Transactional(readOnly = true)
	List<LigneVenteDrug> getAllLIgneVenteDrugsByDrug(Integer myDrugId) throws APIException;
	
	@Authorized(MycashierConfig.MODULE_PRIVILEGE)
	@Transactional
	VenteDrug saveVenteDrug(VenteDrug venteDrug) throws APIException;
	
	@Transactional
	void deleteLigneFromVenteDrug(MyDrug myDrug, VenteDrug venteDrug) throws APIException;
	
	@Authorized(MycashierConfig.MODULE_PRIVILEGE)
	@Transactional
	VenteDrug deleteVenteDrug(VenteDrug venteDrug) throws APIException;
	
	@Transactional
	List<Integer> getAllLignesByVenteDrug(VenteDrug venteDrug) throws APIException;
	
	@Transactional
	void addLigneToVenteDrug(VenteDrug venteDrug, LigneVenteDrug ligneVenteDrug);
	
	@Transactional
	LigneVenteDrug saveLigneVenteDrug(LigneVenteDrug ligneVenteDrug);
	
	@Transactional
	List<VenteDrug> searchVentes(LocalDateTime startDate, LocalDateTime endDate, String clientNom, String clientPrenom,
	        String query, Integer validate);
	
	@Transactional
	List<LigneVenteDrug> getAllLigneVenteDrugsByDrug(Integer id);
	
	@Transactional
	Integer getDrugQuantFromDrugId(Integer venteDrugId, Integer drugId);
	
	@Transactional
	void deleteAllLigneVente(Integer venteDrugId);
	
	List<LigneVenteDrug> getAllLigneVenteDrug(VenteDrug venteDrug);
}
