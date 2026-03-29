package com.bepos.service;

import com.bepos.model.MarineOfficer;
import com.bepos.model.MarineBase;
import com.bepos.model.CaseFile;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.HashSet;
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

    public List<MarineOfficer> getByRank(String rank) {
        return entityManager
                .createQuery("SELECT m FROM MarineOfficer m WHERE LOWER(m.rank) = LOWER(:rank)", MarineOfficer.class)
                .setParameter("rank", rank)
                .getResultList();
    }

    @Transactional
    public void create(MarineOfficer marine) {
        MarineBase marineBase = resolveMarineBase(marine);
        if (marineBase != null) {
            marineBase.addOfficer(marine);
        }
        entityManager.persist(marine);
    }

    @Transactional
    public MarineOfficer edit(MarineOfficer marine) {
        MarineOfficer existingMarine = entityManager.find(MarineOfficer.class, marine.getId());
        if (existingMarine == null) {
            throw new IllegalArgumentException("Marine officer not found.");
        }

        existingMarine.setFullName(marine.getFullName());
        existingMarine.setRank(marine.getRank());
        existingMarine.setBadgeNumber(marine.getBadgeNumber());

        MarineBase targetMarineBase = resolveMarineBase(marine);
        if (existingMarine.getMarineBase() != targetMarineBase) {
            if (existingMarine.getMarineBase() != null) {
                existingMarine.getMarineBase().removeOfficer(existingMarine);
            }
            if (targetMarineBase != null) {
                targetMarineBase.addOfficer(existingMarine);
            }
        }

        return existingMarine;
    }

    @Transactional
    public void delete(Long id) {
        MarineOfficer marineOfficer = entityManager.find(MarineOfficer.class, id);
        if (marineOfficer == null) {
            throw new IllegalArgumentException("Marine officer not found.");
        }

        for (CaseFile caseFile : new HashSet<>(marineOfficer.getCaseFiles())) {
            caseFile.removeMarineOfficer(marineOfficer);
        }

        if (marineOfficer.getMarineBase() != null) {
            marineOfficer.getMarineBase().removeOfficer(marineOfficer);
        }

        entityManager.remove(marineOfficer);
    }

    private MarineBase resolveMarineBase(MarineOfficer marine) {
        if (marine.getMarineBase() == null) {
            return null;
        }

        if (marine.getMarineBase().getId() == null) {
            throw new IllegalArgumentException("Marine officer must reference an existing marine base.");
        }

        MarineBase marineBase = entityManager.find(MarineBase.class, marine.getMarineBase().getId());
        if (marineBase == null) {
            throw new IllegalArgumentException("Marine base not found.");
        }

        return marineBase;
    }
}
