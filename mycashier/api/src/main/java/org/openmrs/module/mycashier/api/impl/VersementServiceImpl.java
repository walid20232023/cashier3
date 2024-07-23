package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.module.mycashier.Versement;
import org.openmrs.module.mycashier.api.VersementService;

import java.time.LocalDateTime;
import java.util.List;

public class VersementServiceImpl implements VersementService {
    @Override
    public Versement getVersementByUuid(String uuid) throws APIException {
        return null;
    }

    @Override
    public Versement getVersementById(Integer versementId) throws APIException {
        return null;
    }

    @Override
    public List<Versement> getAllVersements(LocalDateTime start, LocalDateTime end) throws APIException {
        return null;
    }

    @Override
    public List<Versement> getAllVersementsByAgentSource(Integer agentSourceId, LocalDateTime start, LocalDateTime end) throws APIException {
        return null;
    }

    @Override
    public List<Versement> getAllVersementsByAgentCible(Integer agentCibleId, LocalDateTime start, LocalDateTime end) throws APIException {
        return null;
    }

    @Override
    public Versement saveVersement(Versement versement) throws APIException {
        return null;
    }

    @Override
    public Versement deleteVersement(Versement versement, String motif) throws APIException {
        return null;
    }
}
