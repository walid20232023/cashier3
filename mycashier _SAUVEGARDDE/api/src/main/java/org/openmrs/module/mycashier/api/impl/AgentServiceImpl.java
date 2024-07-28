package org.openmrs.module.mycashier.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.mycashier.Agent;
import org.openmrs.module.mycashier.api.AgentService;
import org.openmrs.module.mycashier.api.dao.AgentDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AgentServiceImpl extends BaseOpenmrsService implements AgentService {
	
	@Autowired
	AgentDao dao;
	
	public void setDao(AgentDao dao) {
		this.dao = dao;
	}
	
	@Override
	public Agent getAgentByUuid(String uuid) throws APIException {
		return dao.getAgentByUuid(uuid);
	}
	
	@Override
	public Agent getAgentById(Integer agentId) throws APIException {
		return dao.getAgentById(agentId);
	}
	
	@Override
	public List<Agent> getAllAgents() throws APIException {
		return null;
	}
	
	@Override
	public Agent saveAgent(Agent agent) throws APIException {
		return dao.saveAgent(agent);
	}
	
	@Override
	public Agent deleteAgent(Agent agent) throws APIException {
		return dao.deleteAgent(agent);
	}
	
}
