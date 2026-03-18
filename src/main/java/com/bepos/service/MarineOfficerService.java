package com.bepos.service;

import com.bepos.model.MarineOfficer;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@Dependent
public class MarineOfficerService {

    @Inject
    private EntityManager entityManager;

//    @Transactional
    public MarineOfficer get(Long id) {
        return entityManager.find(MarineOfficer.class, id);
    }

//    @Transactional
    public List<MarineOfficer> getAll() {
        return entityManager
                .createQuery("SELECT m FROM MarineOfficer m", MarineOfficer.class)
                .getResultList();
    }

    @Transactional
    public void create(MarineOfficer marine) {
        entityManager.persist(marine);
    }
}
