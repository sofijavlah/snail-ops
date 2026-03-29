package com.bepos.service;

import com.bepos.model.CaseFile;
import com.bepos.model.CaseReport;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;

@Dependent
public class CaseFileService {

    @Inject
    private EntityManager entityManager;

    public CaseFile get(Long id) {
        return entityManager.find(CaseFile.class, id);
    }

    public List<CaseFile> getAll() {
        return entityManager
                .createQuery("SELECT c FROM CaseFile c", CaseFile.class)
                .getResultList();
    }

    public List<CaseReport> getReportsByCaseFileId(Long id) {
        return entityManager
                .createQuery("SELECT r FROM CaseReport r WHERE r.caseFile.id = :id", CaseReport.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<CaseFile> getByCaseStatus(String caseStatus) {
        return entityManager
                .createQuery(
                        "SELECT c FROM CaseFile c WHERE LOWER(c.caseStatus) = LOWER(:caseStatus)",
                        CaseFile.class
                )
                .setParameter("caseStatus", caseStatus)
                .getResultList();
    }

    @Transactional
    public void create(CaseFile caseFile) {
        if (caseFile.getWantedPirate() == null) {
            throw new IllegalArgumentException("Case file must include a wanted pirate.");
        }
        if (caseFile.getBounty() == null) {
            throw new IllegalArgumentException("Case file must include a bounty.");
        }

        for (CaseReport report : new ArrayList<>(caseFile.getReports())) {
            caseFile.addReport(report);
        }

        entityManager.persist(caseFile);
    }

    @Transactional
    public void delete(Long id) {
        CaseFile caseFile = entityManager.find(CaseFile.class, id);
        if (caseFile == null) {
            throw new IllegalArgumentException("Case file not found.");
        }
        entityManager.remove(caseFile);
    }
}
