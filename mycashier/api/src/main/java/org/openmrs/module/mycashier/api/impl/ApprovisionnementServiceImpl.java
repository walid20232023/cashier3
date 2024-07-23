package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.module.mycashier.Approvisionnement;
import org.openmrs.module.mycashier.LigneApprovis;
import org.openmrs.module.mycashier.MyDrug;
import org.openmrs.module.mycashier.api.ApprovisionnementService;

import java.time.LocalDateTime;
import java.util.List;

public class ApprovisionnementServiceImpl implements ApprovisionnementService {
    @Override
    public Approvisionnement getApprovisionnementByUuid(String uuid) throws APIException {
        return null;
    }

    @Override
    public Approvisionnement getApprovisionnementById(Integer approvisionnementId) throws APIException {
        return null;
    }

    @Override
    public List<Approvisionnement> getAllApprovisionnements(LocalDateTime start, LocalDateTime end) throws APIException {
        return null;
    }

    @Override
    public List<LigneApprovis> getAllLigneApprovisByDrug(LocalDateTime start, LocalDateTime end) throws APIException {
        return null;
    }

    @Override
    public List<Approvisionnement> getAllApprovisionnementsByEntrepotSource(LocalDateTime start, LocalDateTime end, Integer entrepotSource) throws APIException {
        return null;
    }

    @Override
    public List<Approvisionnement> getAllApprovisionnementsByEntrepotCible(LocalDateTime start, LocalDateTime end, Integer entrepotCible) throws APIException {
        return null;
    }

    @Override
    public Approvisionnement saveApprovisionnement(Approvisionnement approvisionnement) throws APIException {
        return null;
    }

    @Override
    public void addLigneApprovisToApprovisionnement(Approvisionnement approvisionnement, MyDrug myDrug, Integer quantite) throws APIException {

    }

    @Override
    public void deleteLigneApprovisFRomApprovisionnement(Integer LigneApprovisId) throws APIException {

    }

    @Override
    public Approvisionnement deleteApprovisionnement(Approvisionnement approvisionnement, String motif) throws APIException {
        return null;
    }


}
