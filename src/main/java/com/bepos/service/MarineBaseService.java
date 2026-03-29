package com.bepos.service;

import com.bepos.model.MarineBase;
import com.bepos.model.MarineOfficer;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Dependent
public class MarineBaseService {

    @Inject
    private EntityManager entityManager;

    public MarineBase get(Long id) {
        return entityManager.find(MarineBase.class, id);
    }

    public List<MarineBase> getAll() {
        return entityManager
                .createQuery("SELECT m FROM MarineBase m", MarineBase.class)
                .getResultList();
    }

    public List<MarineOfficer> getOfficersByMarineBaseId(Long id) {
        return entityManager
                .createQuery("SELECT m FROM MarineOfficer m WHERE m.marineBase.id = :id", MarineOfficer.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Transactional
    public void create(MarineBase marineBase) {
        for (MarineOfficer officer : new ArrayList<>(marineBase.getOfficers())) {
            marineBase.addOfficer(officer);
        }
        entityManager.persist(marineBase);
    }

    @Transactional
    public void delete(Long id) {
        MarineBase marineBase = entityManager.find(MarineBase.class, id);
        if (marineBase == null) {
            throw new IllegalArgumentException("Marine base not found.");
        }
        entityManager.remove(marineBase);
    }
}
