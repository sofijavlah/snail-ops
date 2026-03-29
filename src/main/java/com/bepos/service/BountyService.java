package com.bepos.service;

import com.bepos.model.Bounty;
import com.bepos.model.CaseFile;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@Dependent
public class BountyService {

    @Inject
    private EntityManager entityManager;

    public Bounty get(Long id) {
        return entityManager.find(Bounty.class, id);
    }

    public List<Bounty> getAll() {
        return entityManager
                .createQuery("SELECT b FROM Bounty b", Bounty.class)
                .getResultList();
    }

    public List<Bounty> getByPirateName(String fullName) {
        return entityManager
                .createQuery(
                        "SELECT c.bounty FROM CaseFile c WHERE LOWER(c.wantedPirate.fullName) = LOWER(:fullName)",
                        Bounty.class
                )
                .setParameter("fullName", fullName)
                .getResultList();
    }

    @Transactional
    public void delete(Long id) {
        Bounty bounty = entityManager.find(Bounty.class, id);
        if (bounty == null) {
            throw new IllegalArgumentException("Bounty not found.");
        }
        if (bounty.getCaseFile() != null) {
            throw new IllegalStateException("Cannot delete bounty because it belongs to an open case file.");
        }
        entityManager.remove(bounty);
    }

    @Transactional
    public void updateHighThreatBounties() {

        List<CaseFile> openHighPrioCaseFiles = entityManager
                .createQuery("SELECT c FROM CaseFile c WHERE c.caseStatus = :caseStatus AND c.priorityLevel = :priorityLevel", CaseFile.class)
                .setParameter("caseStatus", "OPEN")
                .setParameter("priorityLevel", "EMPEROR_LEVEL")
                .getResultList();

        int increment = 10000;

        for (CaseFile caseFile: openHighPrioCaseFiles) {
            Bounty bounty = caseFile.getBounty();
            Long currentAmount = bounty.getCurrentAmount();
            Long newAmount = currentAmount + increment;
            bounty.setCurrentAmount(currentAmount + increment);

            System.out.println("Updated bounty for pirate "
                    + caseFile.getWantedPirate().getFullName()
                    + " from "
                    + currentAmount
                    + " to "
                    + newAmount);
        }
    }
}
