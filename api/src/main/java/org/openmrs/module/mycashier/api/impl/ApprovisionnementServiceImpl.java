package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.mycashier.Approvisionnement;

import org.openmrs.module.mycashier.LigneApprovis;
import org.openmrs.module.mycashier.MyDrug;
import org.openmrs.module.mycashier.StockEntrepot;
import org.openmrs.module.mycashier.api.ApprovisionnementService;
import org.openmrs.module.mycashier.api.dao.ApprovisionnementDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service("approvisionnementService")
public class ApprovisionnementServiceImpl implements ApprovisionnementService {
	
	@Autowired
	ApprovisionnementDao dao;
	
	public void setDao(ApprovisionnementDao dao) {
		this.dao = dao;
	}
	
	@Override
	public Approvisionnement getApprovisionnementByUuid(String uuid) throws APIException {
		return dao.getApprovisionnementByUuid(uuid);
	}
	
	@Override
	public Approvisionnement getApprovisionnementById(Integer approvisionnementId) throws APIException {
		return dao.getApprovisionnementById(approvisionnementId);
	}
	
	@Override
	public List<Approvisionnement> getAllApprovisionnements(LocalDateTime start, LocalDateTime end) throws APIException {
		return dao.getAllApprovisionnements(start, end);
	}
	
	/**
	 * @Override public List<LigneApprovis> getAllLigneApprovisByDrug(LocalDateTime start,
	 *           LocalDateTime end) throws APIException { return
	 *           dao.getAllLigneApprovisByDrug(start, end); }
	 **/
	@Override
	public List<Approvisionnement> getAllApprovisionnementsByEntrepotSource(LocalDateTime start, LocalDateTime end,
	        Integer entrepotSource) throws APIException {
		return dao.getAllApprovisionnementsByEntrepotSource(start, end, entrepotSource);
	}
	
	@Override
	public List<Approvisionnement> getAllApprovisionnementsByEntrepotCible(LocalDateTime start, LocalDateTime end,
	        Integer entrepotCible) throws APIException {
		return dao.getAllApprovisionnementsByEntrepotCible(start, end, entrepotCible);
	}
	
	@Override
	public Approvisionnement saveApprovisionnement(Approvisionnement approvisionnement) throws APIException {
		return dao.saveApprovisionnement(approvisionnement);
	}
	
	@Override
	public void addLigneApprovisToApprovisionnement(Approvisionnement approvisionnement, MyDrug myDrug, Integer quantite)
	        throws APIException {
		dao.addLigneApprovisToApprovisionnement(approvisionnement, myDrug, quantite);
	}
	
	@Override
	public void deleteLigneApprovisFRomApprovisionnement(Integer LigneApprovisId) throws APIException {
		dao.deleteLigneApprovisFRomApprovisionnement(LigneApprovisId);
	}
	
	@Override
	public Approvisionnement deleteApprovisionnement(Approvisionnement approvisionnement) throws APIException {
		return dao.deleteApprovisionnement(approvisionnement);
	}

	@Override
	public List<Integer> getMyDrugEmballageIdsByApprovisionnementId(Integer approvisionnementId) {
		return dao.getMyDrugEmballageIdsByApprovisionnementId(approvisionnementId);
	}

	@Override
	public List<Integer> getQuantitesByApprovisionnementId(Integer approvisionnementId) {
		return dao.getQuantitesByApprovisionnementId(approvisionnementId) ;
	}

	@Override
	public void deleteAllLignesApprovis(Integer approvisionnementId) {
      dao.deleteAllLignesApprovis(approvisionnementId)   ;
	}

	@Override
	public LigneApprovis saveLigneApprovisionnment(LigneApprovis ligneApprovis) {
		return dao.saveLigneApprovisionnment(ligneApprovis);
	}

	@Override
	public List<LigneApprovis> getAllLignesByApprovisionnementId(Integer approvisionnementId) {
		return dao.getAllLignesByApprovisionnementId(approvisionnementId);
	}

	@Override
	public List<LigneApprovis> searchLigneApprovis(String medicament, String dateDebut, String dateFin, String numeroLot, Integer entrepotSourceId, Integer entrepotCibleId, String perimeAvant) {
		return dao.searchLigneApprovis( medicament, dateDebut,  dateFin,  numeroLot, entrepotSourceId, entrepotCibleId,  perimeAvant);
	}


}
