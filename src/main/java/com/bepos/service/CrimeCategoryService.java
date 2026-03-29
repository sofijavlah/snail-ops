package com.bepos.service;

import com.bepos.model.CrimeCategory;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@Dependent
public class CrimeCategoryService {

    @Inject
    private EntityManager entityManager;

    public CrimeCategory get(Long id) {
        return entityManager.find(CrimeCategory.class, id);
    }

    public List<CrimeCategory> getAll() {
        return entityManager
                .createQuery("SELECT c FROM CrimeCategory c", CrimeCategory.class)
                .getResultList();
    }

    @Transactional
    public void create(CrimeCategory crimeCategory) {
        entityManager.persist(crimeCategory);
    }
}
