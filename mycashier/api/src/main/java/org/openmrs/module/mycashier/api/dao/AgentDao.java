package org.openmrs.module.mycashier.api.dao;


import org.hibernate.SessionFactory;
import org.openmrs.module.mycashier.Agent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgentDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Agent getAgentByUuid(String uuid) {

        return  null;
    }

    public Agent getAgentById(Integer agentId) {

        return null;
    }

    public Agent getAllClients() {

        return null;
    }

    public Agent saveAgent(Agent agent) {

        return null;
    }

    public Agent deleteAgent(Agent agent) {

        return null;
    }
}
