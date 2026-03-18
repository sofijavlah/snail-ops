package com.bepos.service;

import com.bepos.model.Crew;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

@Dependent
public class CrewService {

    @Inject
    private EntityManager entityManager;

    public Crew getById(Long id) {
        return entityManager.find(Crew.class, id);
    }

    public List<Crew> getAll() {
        return entityManager
                .createQuery("SELECT c FROM Crew c", Crew.class)
                .getResultList();
    }

    @Transactional
    public void create(Crew crew) {
        entityManager.persist(crew);
    }

    @Transactional
    public Crew edit(Crew crew) {
        return entityManager.merge(crew);
    }
}
