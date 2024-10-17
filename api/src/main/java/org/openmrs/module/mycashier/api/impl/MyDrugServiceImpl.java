package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.mycashier.AssuranceMyDrugPrice;
import org.openmrs.module.mycashier.Emballage;
import org.openmrs.module.mycashier.MyDrug;
import org.openmrs.module.mycashier.MyDrugEmballage;
import org.openmrs.module.mycashier.api.MyDrugService;
import org.openmrs.module.mycashier.api.dao.MyDrugDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("MyDrugService")
public class MyDrugServiceImpl implements MyDrugService {
	
	@Autowired
	MyDrugDao dao;
	
	public void setDao(MyDrugDao dao) {
		this.dao = dao;
	}
	
	@Override
	public MyDrug getMyDrugByUuid(String uuid) throws APIException {
		return dao.getMyDrugByUuid(uuid);
	}
	
	@Override
	public MyDrug getMyDrugById(Integer myDrugId) throws APIException {
		return dao.getMyDrugById(myDrugId);
	}
	
	@Override
	public List<MyDrug> getAllMyDrugs() throws APIException {
		return dao.getAllMyDrugs();
	}
	
	@Override
	public List<MyDrug> getAllRetiredMyDrugs() throws APIException {
		return dao.getAllRetiredMyDrugs();
	}
	
	@Override
	public MyDrug saveMyDrug(MyDrug myDrug) throws APIException {
		return dao.saveMyDrug(myDrug);
	}
	
	@Override
	public void saveMyDrugEmballageUnits(Integer emballageId, Integer drugId, Integer units) throws APIException {
		dao.saveMyDrugEmballageUnits(emballageId, drugId, units);
	}
	
	@Override
	public Integer getMyDrugEmballageUnits(Emballage emballage, MyDrug myDrug) throws APIException {
		return 0;
	}
	
	@Override
	public MyDrug deleteMyDrug(MyDrug myDrug) throws APIException {
		return dao.deleteMyDrug(myDrug);
	}
	
	@Override
	public List<MyDrug> searchDrugs(String query) {
		return dao.searchDrugs(query);
	}
	
	@Override
	public MyDrugEmballage getMyDrugEmballageById(Integer myDrugEmballageId) {
		return dao.getMyDrugEmballageById(myDrugEmballageId);
	}
	
	@Override
	public List<AssuranceMyDrugPrice> searchAssuranceMyDrugPrice(String medicament, String emballage, String forme,
	        String assurance) {
		return dao.searchAssuranceMyDrugPrice(medicament, emballage, forme, assurance);
	}
	
	@Override
	public AssuranceMyDrugPrice getAssuranceMyDrugPriceByMyDrugEmballageAndAssuranceId(MyDrugEmballage myDrugEmballage,
	        Integer assuranceId) {
		return dao.getAssuranceMyDrugPriceByMyDrugEmballageAndAssuranceId(myDrugEmballage, assuranceId);
	}
	
	@Override
	public void saveMyDrugEmballage(MyDrugEmballage myDrugEmballage) {
		dao.saveMyDrugEmballage(myDrugEmballage);
	}
	
	@Override
	public void saveAssuranceMyDrugPrice(AssuranceMyDrugPrice assuranceMyDrugPrice) {
		dao.saveAssuranceMyDrugPrice(assuranceMyDrugPrice);
	}
	
	@Override
	public void deleteAllAssuranceMyDrigPrice(MyDrugEmballage myDrugEmballage) {
		dao.deleteAllAssuranceMyDrigPrice(myDrugEmballage.getId());
	}
	
	@Override
	public List<AssuranceMyDrugPrice> getAllAssuranceMyDrugPrices(Integer myDrugEmballageId) {
		return dao.getAllAssuranceMyDrugPrices(myDrugEmballageId);
	}
}
