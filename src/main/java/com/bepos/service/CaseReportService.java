package com.bepos.service;

import com.bepos.model.CaseFile;
import com.bepos.model.CaseReport;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@Dependent
public class CaseReportService {

    @Inject
    private EntityManager entityManager;

    public CaseReport get(Long id) {
        return entityManager.find(CaseReport.class, id);
    }

    public List<CaseReport> getAll() {
        return entityManager
                .createQuery("SELECT c FROM CaseReport c", CaseReport.class)
                .getResultList();
    }

    @Transactional
    public void create(CaseReport caseReport) {
        if (caseReport.getCaseFile() == null || caseReport.getCaseFile().getId() == null) {
            throw new IllegalArgumentException("Case report must be linked to an existing case file.");
        }

        CaseFile caseFile = entityManager.find(CaseFile.class, caseReport.getCaseFile().getId());
        if (caseFile == null) {
            throw new IllegalArgumentException("Case file not found.");
        }

        caseFile.addReport(caseReport);
        entityManager.persist(caseReport);
    }

    @Transactional
    public void delete(Long id) {
        CaseReport caseReport = entityManager.find(CaseReport.class, id);
        if (caseReport == null) {
            throw new IllegalArgumentException("Case report not found.");
        }

        if (caseReport.getCaseFile() != null) {
            caseReport.getCaseFile().removeReport(caseReport);
            return;
        }

        entityManager.remove(caseReport);
    }
}
