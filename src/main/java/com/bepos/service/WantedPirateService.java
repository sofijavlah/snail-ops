package com.bepos.service;

import com.bepos.model.Crew;
import com.bepos.model.WantedPirate;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@Dependent
public class WantedPirateService {

    @Inject
    private EntityManager entityManager;

    public WantedPirate get(Long id) {
        return entityManager.find(WantedPirate.class, id);
    }

    public List<WantedPirate> getAll() {
        return entityManager
                .createQuery("SELECT w FROM WantedPirate w", WantedPirate.class)
                .getResultList();
    }

    public List<WantedPirate> getByFullName(String fullName) {
        return entityManager
                .createQuery(
                        "SELECT w FROM WantedPirate w WHERE LOWER(w.fullName) = LOWER(:fullName)",
                        WantedPirate.class
                )
                .setParameter("fullName", fullName)
                .getResultList();
    }

    public List<WantedPirate> getByCrewName(String crewName) {
        return entityManager
                .createQuery(
                        "SELECT w FROM WantedPirate w WHERE LOWER(w.crew.name) = LOWER(:crewName)",
                        WantedPirate.class
                )
                .setParameter("crewName", crewName)
                .getResultList();
    }

    @Transactional
    public WantedPirate assignCrew(Long pirateId, Long crewId) {
        WantedPirate wantedPirate = entityManager.find(WantedPirate.class, pirateId);
        if (wantedPirate == null) {
            throw new IllegalArgumentException("Wanted pirate not found.");
        }

        Crew crew = entityManager.find(Crew.class, crewId);
        if (crew == null) {
            throw new IllegalArgumentException("Crew not found.");
        }

        wantedPirate.setCrew(crew);
        return wantedPirate;
    }

    @Transactional
    public void delete(Long id) {
        WantedPirate wantedPirate = entityManager.find(WantedPirate.class, id);
        if (wantedPirate == null) {
            throw new IllegalArgumentException("Wanted pirate not found.");
        }
        if (wantedPirate.getCaseFile() != null) {
            throw new IllegalStateException("Cannot delete wanted pirate because it belongs to an open case file.");
        }
        entityManager.remove(wantedPirate);
    }
}
